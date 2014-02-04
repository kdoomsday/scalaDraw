package ebarrientos.draw.shape
import ebarrientos.draw.shape.util.AffineTransform2d
import java.awt.Graphics2D

case class TransformedShape(s: Shape, t: AffineTransform2d = AffineTransform2d()) extends Shape {

  override def draw(g: Graphics2D) = {
    val foo = g.getTransform()
    g setTransform t
    s paint g
    g setTransform foo
  }
  
//  override def at(p: Point) = TransformedShape(s at t.inv(p), t)
  def at(p: Point) = TransformedShape(s at p, t)
  
  override def movedBy(x: Double, y: Double) = {
//    val pp = t inv (x, y)
    val pp = t transform (x, y)
    // This is vector and not pos. Need to calculate
    super.movedBy(pp.x, pp.y)
//    TransformedShape(s movedBy (pp.x, pp.y), t)
  }
  
  override def isAt(p: Point) = s isAt (t transform p)
//  def isAt(p: Point) = s isAt (t transform p)
  
  override def controlPoint = s.controlPoint
}