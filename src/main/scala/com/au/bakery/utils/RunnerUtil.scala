package com.au.bakery.utils

import org.slf4j.LoggerFactory

object RunnerUtil {
  private val logger = LoggerFactory.getLogger(RunnerUtil.getClass)
  type OptionMap = Map[Symbol, String]

  def nextOption(map: OptionMap, list: List[String]): OptionMap = {
    list match {
      case Nil => map
      case "--input-path" :: value :: tail =>
        nextOption(map ++ Map('inputPath -> value.toString), tail)
      case option :: _ => logger.info("Unknown option " + option)
        sys.exit(1)
    }
  }
}
