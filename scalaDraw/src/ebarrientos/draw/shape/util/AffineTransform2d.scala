package ebarrientos.draw.shape.util

import java.awt.geom.AffineTransform

import scala.math._

import ebarrientos.draw.shape.Point
import ebarrientos.util.Matrix

case class InvalidTransformException() extends Exception

class AffineTransform2d(val m: Matrix = Matrix.identity(3))
{
  if (m.w != m.h || m.w != 3) throw new InvalidTransformException
  
  private[AffineTransform2d] val matrix = m
  
  /**
   * Compose two matrices. This is accomplished by multiplying them.
   */
  def compose(that: AffineTransform2d) = new AffineTransform2d(matrix * that.matrix)
  
  
  /**
   * Scale transformation in X axis.
   * @param amnt Ratio of transformation. It is transformed into positive if it is not.
   * @return An AffineTransform equivalent to this one with the scale transformation applied
   */
  def scalex(amnt: Double): AffineTransform2d = {
    var res = Matrix.identity(matrix.w)
    val sx = abs(amnt)
    res(0, 0) = sx
    
    AffineTransform2d(matrix * res)
  }
  
  
  /**
   * Scale transformation in Y axis.
   * @param amnt Ratio of transformation. It is transformed into positive if it is not.
   * @return An AffineTransform equivalent to this one with the scale transformation applied
   */
  def scaley(amnt: Double): AffineTransform2d = {
    var res = Matrix.identity(matrix.w)
    val sy = abs(amnt)
    res(1, 1) = sy
    
    AffineTransform2d(matrix * res)
  }
  
  
  /**
   * Rotate the transformation
   * @param amnt Degrees in radians by which it should be rotated.
   * @return An AffineTransform equivalent to this one with the rotation applied
   */
  def rotate(amnt: Double): AffineTransform2d = {
    var res = Matrix.identity(matrix.w)
    res(0, 0) = cos(amnt)
    res(0, 1) = sin(amnt)
    res(1, 0) = -sin(amnt)
    res(1, 1) = cos(amnt)
    
    AffineTransform2d(matrix * res)
  }
  
  
  
  /** FIXME It doesn't appear to work. Maybe not implemented in JAffineTransform?
   * Move the transformation
   * @param x Translation in the X axis.
   * @param y Translation in the Y axis.
   * @return an AffineTransform equivalent to this one, translated by the given amounts.
   */
  def move(x: Double = 0, y: Double = 0): AffineTransform2d = {
    val res = Matrix.identity(matrix.w)
    res(0, 2) = x
    res(1, 2) = y
    
    AffineTransform2d(matrix * res)
  }
  
  
  /**
   * Transform a point using this transform.
   * @param p Point to be transformed
   * @return Transformed point
   */
  def transform(p: Point): Point = {
    val vector = Matrix(3, 1)
    vector(0, 0) = p.x; vector(1, 0) = p.y; vector(2, 0) = 1
    
    val res = this.matrix * vector
    (res(0, 0), res(1, 0))
  }
  
  
  /**
   * Apply the inverse of this transform to a point
   * @param p Point to be transformed
   * @return The point corresponding to p in the pre-transformed space
   */
  def inv(p: Point): Point = {
    val vector = Matrix(3, 1)
    vector(0, 0) = p.x; vector(1, 0) = p.y; vector(2, 0) = 1
    
    val res = matrix.invert * vector
    (res(0, 0), res(1, 0))
  }
  
  
  def apply(i: Int, j: Int) = matrix(i, j)
}

/**
 * Companion object. Provides facilities for creating AffineTransform2d instances and for
 * converting to the java equivalent AffineTransform.
 */
object AffineTransform2d {
  def apply(m: Matrix = Matrix.identity(3)) = new AffineTransform2d(m)
  
  implicit def AffineTransform2dToJAffineTransform(at2d: AffineTransform2d): AffineTransform =
    new AffineTransform(at2d(0, 0), at2d(0, 1), at2d(1, 0), at2d(1, 1), at2d(2, 0), at2d(2, 1))
}