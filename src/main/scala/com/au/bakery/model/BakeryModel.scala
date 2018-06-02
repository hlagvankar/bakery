package com.au.bakery.model

import play.api.libs.json._

object BakeryModel {

  case class Packs(packSize: Int, price: Double)
  case class BakeryItems(name: String, itemCode: String, packs: Seq[Packs])

  implicit val packReads: Format[Packs] = Json.format[Packs]
  implicit val bakeryReads: Format[BakeryItems] = Json.format[BakeryItems]
}
