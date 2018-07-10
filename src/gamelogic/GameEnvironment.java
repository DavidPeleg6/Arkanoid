package gamelogic;
import java.util.List;

import biuoop.DrawSurface;
import shapes.Line;
import shapes.Point;

import java.util.ArrayList;

/**.
 * @author David Geda
 * GameEnvironment class
 * an object managing all the Collidables in the game.
 * responsible for linking between the Ball and the Collidables.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**.
   * constructor method of the class. creates an array of Collidables
   */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**.
   * a method to add a new Collidable to the List
   * @param c , a Collidable to add to the GameEnvironment
   */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**.
   * a method to to iterate through the List , find collisionPoints, and return the one
   * closest to the given Line
   * @param trajectory , the trajectory of a given object used to compare
   * @return CollisionInfo , the Point of colision and the Collidable that was involved
   */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collision = trajectory.end();
        Collidable collisionShape = this.collidables.get(0);
        boolean didHit = false;
        for (int i = 0; i < this.collidables.size(); i++) {
            Point temp = trajectory.closestIntersectionToStartOfLine(
      this.collidables.get(i).getCollisionRectangle());
            if (temp != null) {
                didHit = true;
                if (collision.distance(trajectory.start()) > temp.distance(trajectory.start())) {
                    collision = temp;
                    collisionShape = (Collidable) this.collidables.get(i);
                }
            }
        }
        if (!didHit) {
            return null;
        } else {
            return new CollisionInfo(collision, collisionShape);
        }
    }

    /**.
   * a method to to to draw all the Collidables in the GameEnvironment
   * @param d , the DrawSurface to draw the objects on
   */
    public void drawCollidables(DrawSurface d) {
        for (int i = 0; i < collidables.size(); i++) {
            collidables.get(i).drawOn(d);
        }
    }

       /**.
       * a method for removing a collidable from the game environment
       * @param c , a Collidable to remove from the game
       */
    public void removeFromEnv(Collidable c) {
        this.collidables.remove(c);
    }

}
