package animations;
import biuoop.DrawSurface;
import gamelogic.GameLevel;

/**.
 * @author David Geda
 * Sprite interface
 * an interface to manage the different sprite-like objects in the program.
 */
public interface Sprite {
    /**.
   * a method to make the sprite draw itself on the DrawSurface
   * @param d , the DrawSurface to draw on
   */
       void drawOn(DrawSurface d);
           /**.
   * a method to notify the sprite time has passed
   * @param dt , the rate of frames
   */
       void timePassed(double dt);
           /**.
   * a method to make the sprite add itself to the Game
   * @param g , a Game to add the sprite to
   */
       void addToGame(GameLevel g);

}
