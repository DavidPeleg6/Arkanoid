package shapes;
import java.util.List;

/**.
 * @author David Geda
 * Line class
 * an object representing a 2D line.
 * also responsible for finding its length and comparing itself to other lines.
 */

public class Line {
    private Point start, end;

      /**.
     * constructor method of the class
     * @param start , the start Point of the line.
     * @param end , the end Point of the line
     */
    public Line(Point start , Point end) {
        this.start = start;
        this.end = end;
    }

      /**.
     * constructor method of the class
     * @param x1 , the X value of the start Point.
     * @param y1 , the Y value of the start Point.
     * @param x2 , the X value of the end Point.
     * @param y2 , the Y value of the end Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

      /**.
     * a method for finding the length of the line
     * @return double , the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

      /**.
     * a method for finding the middle point of the line
     * @return Point , the middle Point of the line
     */
    public Point middle() {
        double midx, midy;
        midx = (this.start.getX() + this.end.getX()) / 2;
        midy = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midx, midy);
    }

      /**.
     * access method for getting the start Point of the line
     * @return Point , the start Point of the line
     */
    public Point start() {
        return this.start;
    }

      /**.
     * access method for getting the end Point of the line
     * @return Point , the end Point of the line
     */
    public Point end() {
        return this.end;
    }

      /**.
     * method for checking whether a line is equal to a different line
     * @param other , a line for comparison.
     * @return boolean , true if they're equal, false otherwise.
     */
    public boolean equals(Line other) {
        boolean x = (this.start.getX() == other.start.getX());
        boolean y = (this.start.getY() == other.start.getY());
        return x && y;
    }

      /**.
     * method for checking whether 2 lines intersect
     * @param other , a line for comparison.
     * @return boolean , true if they intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

      /**.
     * method for finding the Point of intersection between 2 lines
     * @param other , a line for comparison.
     * @return Point , the Point of intersection (or null if it doesnt exist).
     */
    public Point intersectionWith(Line other) {
        Point intersection;
        double a1, a2, b1, b2, c1, c2;
        double denominator;
        double x, y;
   //creating A,B of the linear equations
        a1 = this.end.getY() - this.start.getY();
        b1 = this.start.getX() - this.end.getX();
        a2 = other.end.getY() - other.start.getY();
    b2 = other.start.getX() - other.end.getX();
    //creating denominator of the intersection between lines
    denominator = a1 * b2 - a2 * b1;
    //if denominator is 0, check wether the line segments are co-linear
        if (denominator == 0) {
            return checkCoLinear(other);
        }
   //create C of the linear equations
        c1 = a1 * this.start.getX() + b1 * this.start.getY();
        c2 = a2 * other.start.getX() + b2 * other.start.getY();
   //divide by denominator to get x, y values
        x = (c1 * b2 - c2 * b1) / denominator;
        y = (a1 * c2 - a2 * c1) / denominator;
        intersection = new Point(x, y);
   //if lines intersect, check wether the intersection point is in the line segments
        intersection = checkIntersection(intersection, other);
        return intersection;
    }

      /**.
     * method for checking whether a Point is contained in 2 line segments
     * @param intersection , a Point for comparison
     * @param other , a line for comparison.
     * @return Point , the same point if the Point is contained, null otherwise.
     */
    private Point checkIntersection(Point intersection, Line other) {
        double ratioX0, ratioY0, ratioX1, ratioY1;
   //using simple distance calculations, we find the ratio between the Point and the line segments
        ratioX0 = (intersection.getX() - this.start.getX())
              / (this.end.getX() - this.start.getX());
        ratioY0 = (intersection.getY() - this.start.getY())
              / (this.end.getY() - this.start.getY());
        ratioX1 = (intersection.getX() - other.start.getX())
              / (other.end.getX() - other.start.getX());
        ratioY1 = (intersection.getY() - other.start.getY())
              / (other.end.getY() - other.start.getY());
   //if the ratio is negative or greater than 1 the Point must be outside the line segment
        if (((ratioX0 >= 0 && ratioX0 <= 1) || (ratioY0 >= 0 && ratioY0 <= 1))
        && ((ratioX1 >= 0 && ratioX1 <= 1) || (ratioY1 >= 0 && ratioY1 <= 1))) {
                return intersection;
        }
        return null;
    }

      /**.
     * method for checking whether 2 line segments are co-linear and intersect in a single Point.
     * (complete each other to 1 long Line)
     * @param other , a line for comparison.
     * @return Point , the intersection Point if line segments are co-linear, null otherwise.
     */
    private Point checkCoLinear(Line other) {
        Point intersection;
        if (this.start.equals(other.end)) {
            intersection = new Point(this.start.getX(), this.start.getY());
            return intersection;
        } else if (this.end.equals(other.start)) {
            intersection = new Point(this.end.getX(), this.end.getY());
            return intersection;
        }
        return null;
    }

    /**.
   * a method to find the closest intersection point the rectangle
   *  to this start of line
   * @param rect , a Rectangle to compare with
   * @return Point , the closest intersection Point to the start of this line
   */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //get the 2 intersection points ith the rectangle
        List<Point> intersections = rect.intersectionPoints(new Line(this.start, this.end));
        if (intersections == null) {
            return null;
        }
        if (intersections.size() == 1) {
            return (Point) intersections.get(0);
        }
        Point a = (Point) intersections.get(0);
        Point b = (Point) intersections.get(1);
        //compare them to find the one closest to the start of the line
        if (a.distance(this.start) < b.distance(this.start)) {
            return a;
        } else {
            return b;
        }
    }
}
