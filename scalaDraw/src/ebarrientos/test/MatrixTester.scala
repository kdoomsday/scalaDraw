package ebarrientos.test
import ebarrientos.util.Matrix
import ebarrientos.draw.shape.util.AffineTransform2d
import ebarrientos.draw.shape.Point

object MatrixTester extends App {

//  val mat = Matrix.identity(3)
//  mat(0, 0) = 2
////  mat(1) = Array(1.0, 3.0, 3.0)
//  
//  println(mat)
//  println("-----")
//  println(mat.invert)
//  println("------")
//  println(mat * mat.invert)
  
  /* ***** ***** */
  
  val p = new Point(2, 1)
  val t = new AffineTransform2d() scalex 2
  
  println(t transform p)
  println(t inv p)
}