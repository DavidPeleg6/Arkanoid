package shapes;
/**.
 * @author David Geda
 * Point class
 * contains a 2D Point and also responsible for comparing it to other points.
 */
public class Point {
    private double x, y;

     /**.
     * constructor of the class.
     * @param x , the location of the point on the X axis.
     * @param y , the location of the point on the Y axis.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

       /**.
     * method for checking the distance between this point and another one
     * @param other , a point for comparison.
     * @return distance , the distance between the 2 Points
     */
    public double distance(Point other) {
    //use distance equation to determine d^2
        double dist = Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2);
    //return square root of d^2
        return (Math.sqrt(dist));
    }

       /**.
     * method for checking whether this point is equal to another one
     * @param other , a point for comparison.
     * @return boolean , true if equals , false otherwise
     */
    public boolean equals(Point other) {
        return ((this.x == other.x) && (this.y == other.y));
    }

       /**.
     * access method for the x value of the object
     * @return x , the x value of the Point.
     */
    public double getX() {
        return this.x;
    }

       /**.
     * access method for the y value of the object
     * @return y , the y value of the Point.
     */
    public double getY() {
        return this.y;
    }
}
