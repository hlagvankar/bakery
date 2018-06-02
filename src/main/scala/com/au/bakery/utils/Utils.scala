package com.au.bakery.utils

import java.nio.file.{Files, Paths}

import scala.io.Source._

class Utils {

  def validateCodes(productCode: String): Boolean = {
    val appConfig = AppConfig
    if(!appConfig.validCodes.contains(productCode))
      false
    else
      true
  }

  def validatePath(path: String): Boolean = {
    Files.exists(Paths.get(path))
  }

  def readFile(file: String): Iterator[Items] = {
    for {
      line <- fromFile(file).getLines()
      values = line.split('|').map(_.trim)
    } yield Items(values(0).toInt, values(1))
  }
}

case class Items(numberOfItems: Int, productCode: String)