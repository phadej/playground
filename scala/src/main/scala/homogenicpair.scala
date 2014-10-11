package homogenicpair

object HPair {
  def apply[A](fst: A, snd: A): Tuple2[A,A] = new Tuple2[A, A](fst, snd)
  def pure[A](x: A) = apply(x, x)
}

package object implicits {
  implicit class HPair[A](pair: Tuple2[A, A]) {
    val _1: A = pair._1
    val _2: A = pair._2

    def map[B](f: A => B): Tuple2[B, B] = new Tuple2[B, B](f(pair._1), f(pair._2))

    def flatMap[B](f: A => HPair[B]): Tuple2[B, B] = 
      new Tuple2[B, B](f(pair._1)._1, f(pair._2)._2)

    override def toString: String = pair.toString
  }
}

/*
scala> :paste
// Entering paste mode (ctrl-D to finish)

import homogenicpair.implicits._

val a: Tuple2[Int, Int] = (2, 3)
def f(x: Int): Tuple2[Int, Int] = (x, x * x)

val x = a flatMap f

// Exiting paste mode, now interpreting.

import homogenicpair.implicits._
a: (Int, Int) = (2,3)
f: (x: Int)(Int, Int)
x: (Int, Int) = (2,9)
*/
