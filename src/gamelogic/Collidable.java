package gamelogic;
import biuoop.DrawSurface;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;

/**.
 * @author David Geda
 * Collidable interface
 * an interface to help manage all the things you could collide into
 */
public interface Collidable {
    /**.
   * the accesor method to get the Rectangle this Block represents
   * @return Rectangle , the Rectangle this Block represents.
   */
    Rectangle getCollisionRectangle();

    /**.
   * a method to to notify the Block that it has been hit
   * @param hitter , the ball involved in the hit
   * @param collisionPoint , the Point of collision with this Block
   * @param currentVelocity , the current Velocity of the object hitting this Block
   * @return Velocity , the new Velocity the colliding object should have
   */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**.
   * a method to make the Block draw itself on the DrawSurface
   * @param d , the DrawSurface to draw on
   */
    void drawOn(DrawSurface d);

}
