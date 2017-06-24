package com.gameoflife

import com.gameoflife.Universe.Cell

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
  * Created by sujit on 6/24/17.
  */
object GameOfLife {

  /**
    * Gilder Pattern seed:
    * 0,1
    * 1,2
    * 2,0
    * 2,1
    * 2,2
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    val grid: ListBuffer[Cell] = new mutable.ListBuffer[Universe.Cell]
    var line = ""
    while ({line = StdIn.readLine(); !line.isEmpty}) {
      val cell: Array[String] = line.split(",")
      grid.append((cell(0).trim.toInt, cell(1).trim.toInt))
    }
    var universe = Universe(grid.toSet)
    while (true) {
      println(universe + "\n\n")
      universe = universe.nextGeneration
      Thread.sleep(1000)
    }
  }

}
