package shapes;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**.
 * @author David Geda
 * Rectangle class
 * contains a Rectangle and also responsible for coloring it.
 */
public class Rectangle {
    private Point upperLeft;
    private Line[] rectLines;
    private double height, width;
    private java.awt.Color color;

       /**.
     * constructor of the class, object is created with a default color gray.
     * @param upperLeft , the upperLeft Point of the Rectangle.
     * @param width , the width of the Rectangle.
     * @param height , the height of the Rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        this.createLines();
    }

       /**.
     * access method to get the upperLeft Point of the rectangle
     * @return Point , the top left Point of the Rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**.
  * access method to get the width of the Rectangle
  * @return double , the width of the Rectangle.
  */
    public double getWidth() {
        return this.width;
    }

    /**.
  * access method to get the height of the Rectangle
  * @return double , the height of the Rectangle.
  */
    public double getHeight() {
        return this.height;
    }

    /**.
  * access method to get the border lines of the Rectangle
  * @return Line[] , a Line array containing the border lines
  */
    public Line[] getLines() {
        return this.rectLines;
    }

    /**.
  * a method to get all the intersecttion Points of this Rectangle with a given Line
  * @param line , the intersection line
  * @return java.util.List , a Point array containing the intersection Points with this Rectangle
  */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point intersection;
        List<Point> intersections = new ArrayList<Point>();
        for (int i = 0; i < 4; i++) {
            intersection = line.intersectionWith(this.rectLines[i]);
            if (intersection != null) {
                intersections.add(intersection);
            }
        }
        if (!intersections.isEmpty()) {
            return intersections;
        } else {
            return null;
        }
    }

       /**.
     * method for drawing the rectangle
     * @param d , a DrawSurface to draw the rectangle
     */
    public void drawRectangle(DrawSurface d) {
        if (this.color == null) {
            return;
        }
        d.setColor(this.color);
        for (int i = 0; i < 4; i++) {
            d.drawLine((int) rectLines[i].start().getX(), (int) rectLines[i].start().getY(),
       (int) rectLines[i].end().getX(),
       (int) rectLines[i].end().getY());
        }
    }

       /**.
     * method for setting the color of the rectangle
     * @param color1 , a color for setting the color of the rectangle.
     */
    public void setColor(java.awt.Color color1) {
        this.color = color1;
    }

    /**.
  * a method to create all the border lines of this Rectangle
  */
    private void createLines() {
        this.rectLines = new Line[4];
        this.rectLines[0] = new Line(this.upperLeft, new Point(this.upperLeft.getX()
       + this.width, this.upperLeft.getY()));
        this.rectLines[1] = new Line(new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height),
       new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height));
        this.rectLines[2] = new Line(this.upperLeft, new Point(this.upperLeft.getX(),
       this.upperLeft.getY() + this.height));
        this.rectLines[3] = new Line(new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()),
       new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + height));
    }

}
