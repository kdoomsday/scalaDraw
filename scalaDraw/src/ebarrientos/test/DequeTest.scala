package ebarrientos.collection.test
import ebarrientos.collection.Dequeue

object DequeTest {

  def main(args: Array[String]): Unit = {
    val deq = new Dequeue[Int]

    println(deq)
    
    1 +: 2 +: 3 +: 3 +: deq :+ 4 :+ 5
    
    println(deq)
    if (deq findL2R 3) {
      deq.goPrev
      pcurr(deq)
    }
    
    if (deq findR2L 3) {
      deq.goNext
      pcurr(deq)
    }
  }

  
  private def pcurr[A](xs: Dequeue[A]) = {
    xs getCurrent match {
      case Some(x) => println("Current: " + x)
      case None => println("No current element")
    }
  }

}