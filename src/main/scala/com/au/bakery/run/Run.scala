package com.au.bakery.run

import com.au.bakery.service.ProcessCart
import com.au.bakery.utils.{RunnerUtil, Utils}
import org.slf4j.LoggerFactory

object Run {

  private val logger = LoggerFactory.getLogger(Run.getClass)

  def main(args: Array[String]): Unit = {
    val options = RunnerUtil.nextOption(Map(), args.toList)
    if (options.isEmpty) {
      logger.error(s"Invalid options supplied")
      logger.info(
        s"""Please run program using
           |<program-name> --input-path <path-to-file>
         """.stripMargin)
      sys.exit(1)
    }
    val filePath = options.get('inputPath).get

    val pc = new ProcessCart
    val utils = new Utils

    val validPath = utils.validatePath(filePath)

    if (!validPath) {
      logger.error(s"File path does't exist")
      sys.exit(1)
    }
    val input = pc.processFile(filePath)
    val filteredRecords = pc.filterRecords(input.toList)
    pc.processOrders(filteredRecords)

    logger.info(s"Program executed successfully...")
  }

}
