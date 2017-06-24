package com.gameoflife

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by sujit on 6/24/17.
  */
class UniverseSpec extends FlatSpec with Matchers {

  it should "create a universe with initial seed" in {
    val universe = Universe(Set((1, 1), (1, 2)))
    universe should be(Universe(Set((1, 1), (1, 2))))
  }

  "Any live cell with fewer than two live neighbours" should "die" in {
    val universe = Universe(Set((1, 1), (1, 2)))
    universe.nextGeneration should be(Universe(Set()))
  }

  "Any live cell with more than three live neighbours" should "die" in {
    val universe = Universe(Set((1, 1), (1, 2), (1, 3), (2, 2), (3, 2)))
    universe.nextGeneration should be(Universe(Set((0, 2), (1, 1), (1, 2), (1, 3))))
  }

  "Any live cell with two or three live neighbours" should "live" in {
    val universe = Universe(Set((1, 1), (1, 2), (1, 3), (2, 2)))
    universe.nextGeneration should be(Universe(Set((1, 1), (1, 2), (1, 3), (2, 2), (2, 1), (2, 3), (0, 2))))
  }

  "Any dead cell with exactly three live neighbours" should "come to life" in {
    val universe = Universe(Set((1, 1), (1, 2), (2, 1)))
    universe.nextGeneration should be(Universe(Set((1, 1), (1, 2), (2, 1), (2, 2))))
  }

  it should "Show still life - block pattern" in {
    var universe = Universe(Set((1, 1), (1, 2), (2, 1), (2, 2)))

    universe = universe.nextGeneration
    universe should be(Universe(Set((1, 1), (1, 2), (2, 1), (2, 2))))

    universe = universe.nextGeneration
    universe should be(Universe(Set((1, 1), (1, 2), (2, 1), (2, 2))))
  }

  it should "Show still life - boat pattern" in {
    var universe = Universe(Set((0, 1), (1, 0), (2, 1), (0, 2), (1, 2)))

    universe = universe.nextGeneration
    universe should be(Universe(Set((0, 1), (1, 0), (2, 1), (0, 2), (1, 2))))

    universe = universe.nextGeneration
    universe should be(Universe(Set((0, 1), (1, 0), (2, 1), (0, 2), (1, 2))))
  }

  it should "Show blinker pattern - oscillator" in {
    var universe = Universe(Set((1, 1), (1, 0), (1, 2)))

    universe = universe.nextGeneration
    universe should be(Universe(Set((1, 1), (0, 1), (2, 1))))

    universe = universe.nextGeneration
    universe should be(Universe(Set((1, 1), (1, 0), (1, 2))))
  }

  it should "Show toad pattern -  two phase oscillator" in {
    var universe = Universe(Set((1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (2, 4)))

    universe = universe.nextGeneration
    universe should be(Universe(Set((0, 2), (1, 1), (1, 4), (2, 1), (2, 4), (3, 3))))
  }

  it should "convert universe in string" in {
   val universe = Universe(Set((1, 1), (1, 2), (2, 1), (2, 2)))
    universe.toString should be("- - -\n- X X\n- X X")
  }

}
