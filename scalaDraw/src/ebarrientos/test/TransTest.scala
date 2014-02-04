package ebarrientos.test

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import ebarrientos.draw.DrawPanel
import ebarrientos.draw.shape.Rectangle
import ebarrientos.draw.shape.TransformedShape
import ebarrientos.draw.shape.util.AffineTransform2d
import ebarrientos.draw.shape.Oval
import java.awt.Color
import ebarrientos.draw.shape.Point
import java.awt.Dimension
import ebarrientos.draw.shape.FilledRect
import ebarrientos.draw.shape.Line
import ebarrientos.draw.shape.TransformedShape
import ebarrientos.draw.shape.Oval
import scala.math.Pi
import ebarrientos.util.Matrix

object TransTest extends SimpleSwingApplication {

  override def top = new MainFrame {
    title = "Transformed shape test"
    preferredSize = new Dimension(300, 300)
      
    val p = new DrawPanel(this)
    contents = p
    
    val s = new TransformedShape(new FilledRect(Point(0, 0), Point(50, 50), Color.BLUE), AffineTransform2d() scalex 2)
    p add (s movedBy (25, 25))
    
    p add Line(Point(25, 0), Point(25, 60))
    p add Line(Point(50, 0), Point(50, 60))
    p add Line(Point(75, 0), Point(75, 60))
    p add Line(Point(100, 0), Point(100, 60))
    
    p add Line(Point(25, 50), Point(75, 50))
    
    
    p add (TransformedShape(Oval(Point(50, 50), 25, 25, Color.RED), AffineTransform2d() rotate Pi rotate -Pi))
    
    val at = AffineTransform2d() rotate Pi/2
    val vect = Matrix(3, 1)
    vect(0, 0) = 50; vect(1, 0) = 0; vect(2, 0) = 1
    val res = at.m * vect
    val p1 = Point(res(0, 0), res(1, 0))
    
    println(p1)
    println(at.inv(p1))
    
    centerOnScreen
  }
}