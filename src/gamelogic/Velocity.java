package gamelogic;
import shapes.Point;

/**.
 * @author David Geda
 * Velocity class
 * contains the velocity of a given object and also responsible for its movement.
 */
public class Velocity {

    private Point velocity;
   /**.
     * constructor of the class.
     * @param dx , the pace of change in X axis.
     * @param dy , the pace of change in Y axis.
     */
    public Velocity(double dx, double dy) {
        if (dx == 0) {
            dx = 0.01;
        }
        this.velocity = new Point(dx, dy);
    }

   /**.
     * Velocity dx accesor method
     * @return x , the pace of change in X axis
     */
    public double getDX() {
        return this.velocity.getX();
    }

   /**.
     * Velocity dy accesor method
     * @return y , the pace of change in Y axis
     */
    public double getDY() {
        return this.velocity.getY();
    }

   /**.
     * method for converting Velocity from polar coordinates to cartesian
     * @param angle , the angle of movement.
     * @param speed , the pace of change in R axis.
     * @return Velocity a new velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle);
        double dx, dy;
        dx = speed * Math.sin(angleRad);
        dy = speed * Math.cos(angleRad);
        return new Velocity(dx, dy);
    }

   /**.
     * method for making a point advance by a certain amount depending on Velocity
     * @param p , the point which we want to move.
     * @param dt , the rate of frames
     * @return Point newPoint , the point after movement
     */
    public Point applyToPoint(Point p, double dt) {
        Point newPoint = new Point(p.getX() + (this.velocity.getX() * dt)
                         , p.getY() + (this.velocity.getY() * dt));
        return newPoint;
    }

}
