package com.au.bakery.service


import com.au.bakery.model.BakeryModel.Packs
import com.au.bakery.utils.{AppConfig, Items, Utils}
import org.slf4j.LoggerFactory
import scala.math._

class ProcessCart {
  private val logger = LoggerFactory.getLogger(classOf[ProcessCart])
  val appConfig = AppConfig
  val utils = new Utils

  def processFile(filePath: String): Iterator[Items] = {
    utils.readFile(filePath)
  }

  def filterRecords(items: Seq[Items]): Seq[Items] = {
    items.filter(x => utils.validateCodes(x.productCode))
  }

  def processOrders(records: Seq[Items]): Unit = {
    var totalItems = 0.0
    var totalPrice = 0.0

    records.foreach { lineItem =>
      val orderQty = lineItem.numberOfItems
      val productCode = lineItem.productCode
      val itemInStock = appConfig.bakeryConfig.filter(_.itemCode.contains(productCode)).flatMap(x => x.packs)

      val orderLine = calculatePackages(itemInStock, orderQty)
      totalItems += orderLine.totalQuantity
      totalPrice += orderLine.totalPrice

      logger.info(s"$orderQty, $productCode" + "$" + f"${orderLine.totalPrice}%2.2f")
      val packs = orderLine.packages
      packs.foreach { p =>
        logger.info(s"            " + s"${p.quantity}" + " x " + s"${p.packSize}" + " $" + s"${p.price}")
      }

    }

    if (totalItems == 0) {
      logger.info(s"Your order cannot be fulfilled at this time due to incorrect pack size")
    } else {
      logger.info(s"Total number of items in order: ${totalItems.toInt}")
      logger.info(s"Total price of items: " + "$" + s"$totalPrice")
    }
  }

  def calculatePackages(packages: Seq[Packs], quantity: Int): ShoppingCart = {
    val packageSort = packages.sortBy(a => a.packSize).reverse
    var totalPrice = 0.0
    var flag = false
    val orderLineObject = ShoppingCart(0, Seq(), 0)

    if (packageSort.nonEmpty && quantity != 0) {
      packageSort.zipWithIndex.foreach { case (currentPack, idx) =>
        if (!flag) {
          val value = floor(quantity / currentPack.packSize).toInt
          val remainder = quantity % currentPack.packSize

          if (remainder == 0) {
            val linePrice = value * currentPack.price
            totalPrice += linePrice
            orderLineObject.addItemPack(value, currentPack.packSize, currentPack.price)
            flag = true
          } else {
            val furtherPackages = packageSort.drop(idx + 1)
            furtherPackages.foreach { fPackage =>
              if (!flag) {
                val childValue = remainder / fPackage.packSize
                if (childValue % 1 == 0 && (currentPack.packSize * value + fPackage.packSize * childValue) == quantity) {
                  orderLineObject.addItemPack(value, currentPack.packSize, currentPack.price)
                  orderLineObject.addItemPack(childValue, fPackage.packSize, fPackage.price)
                  flag = true
                }
              }
            }
          }
        }
      }
    }
    orderLineObject
  }
}

case class ShoppingCart(var totalPrice: Double, var packages: Seq[Package], var totalQuantity: Int) {

  def addItemPack(quantity: Int, packSize: Int, unitPrice: Double): Unit = {
    this.totalQuantity += quantity * packSize
    this.totalPrice += unitPrice * quantity
    this.packages ++= Seq(Package(quantity, packSize, unitPrice))
  }
}

case class Package(quantity: Int, packSize: Int, price: Double)
