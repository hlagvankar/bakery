package com.au.bakery.utils

import java.util

import com.au.bakery.model.BakeryModel._
import com.typesafe.config.{Config, ConfigFactory}
import play.api.libs.json._

object AppConfig {

  val config: Config = ConfigFactory.load()

  lazy val bakeryConfig: List[BakeryItems] = parseBakeryItems(config.getString("bakery"))
  lazy val validCodes: util.List[String] = config.getStringList("validCodes")

  def parseBakeryItems(str: String): List[BakeryItems] = {
    Json.parse(str).as[List[BakeryItems]]
  }
}