package gamelogic;
import shapes.Point;

/**.
 * @author David Geda
 * CollisionInfo class
 * an object for holding the information about a certain collision Point.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionShape;

    /**.
   * constructor method of the class
   * @param shape , the Collidable involved in the collision.
   * @param collision , the Point of collision with an object
   */
    public CollisionInfo(Point collision, Collidable shape) {
        this.collisionPoint = collision;
        this.collisionShape = shape;
    }

    /**.
   * the accesor method to get the collisionPoint
   * @return Point , the collisionPoint
   */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**.
   * the accesor method to get the collisionObject
   * @return Collidable , the Collidable involved
   */
    public Collidable collisionObject() {
        return this.collisionShape;
    }
}
