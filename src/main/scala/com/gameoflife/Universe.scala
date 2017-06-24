package com.gameoflife

import com.gameoflife.Universe.Cell

/**
  * Created by sujit on 6/24/17.
  */

object Universe {
  type Cell = (Int, Int)
}

case class Universe(liveCells: Set[Cell]) {

  private def getNeighbors(cell: Cell): Set[Cell] = {
    val distance = Set(0, -1, 1)
    val neighbors =
      for {
        d1 <- distance
        d2 <- distance
        if !(d1 == 0 && d2 == 0)
      } yield (cell._1 - d1, cell._2 - d2)
    neighbors
  }

  def nextGeneration: Universe = {

    def getLiveNeighborsCount(cell: Cell) = {
      getNeighbors(cell).count(x => liveCells.contains(x))
    }

    def getDeadNeighbors(cell: Cell) = {
      getNeighbors(cell).diff(liveCells)
    }

    val cells = liveCells.filter(cell => {
      val liveCells = getLiveNeighborsCount(cell)
      liveCells == 2 || liveCells == 3
    })

    val toBeRevivedCells = liveCells.flatMap(x => getDeadNeighbors(x).filter(d => getLiveNeighborsCount(d) == 3))

    Universe(cells ++ toBeRevivedCells)
  }

  override def toString: String = {
    val maxX = liveCells.maxBy(_._1)._1
    val maxY = liveCells.maxBy(_._2)._2

    val grid = Array.ofDim[String](maxX + 1, maxY + 1)

    for (x <- 0 to maxX) {
      for (y <- 0 to maxY) {
        if (liveCells.contains((x, y)))
          grid(x)(y) = "X"
        else
          grid(x)(y) = "-"
      }
    }

    grid.map(r => r.mkString(" ")).mkString("\n")

  }

}
