package ebarrientos.collection

import scala.collection.Iterable

class Deque[A] extends Iterable[A] {
  
  /**
   * Data structure used to hold information about the Dequeue
   */
  private[this] case class Item[A](
      var value: A,
      var next: Option[Item[A]],
      var prev: Option[Item[A]] )
  {
    def this(value: A) = this(value, None, None)
  }
  private[this] object Item {
    def apply[A](aval: A): Item[A] = return new Item(aval)
  }
  
  
  // Iterator for Dequeueues
  private[this] class Iter[A](var it: Option[Item[A]],
                val left2right: Boolean) extends Iterator[A]
  {
    override def hasNext =  it match {
      case None => false
      case _ => true
    }
    
    override def next: A =
      it match {
        case Some(x) => {
          val res = x.value
          if (left2right) it = x.next else it = x.prev
          res
        }
        case None => throw new RuntimeException("No value!")
      }
  }


  private[this] var left: Option[Item[A]] = None
  private[this] var right: Option[Item[A]] = None
  private[this] var length = 0
  
  
  def iterator: Iterator[A] = new Iter(left, true)
}