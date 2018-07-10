package animations;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**.
 * @author David Geda
 * SpriteCollection class
 * contains all the Sprites in the program.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**.
    * constructor of the class.
    * creates a new array list for the sprites
    */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**.
     * a method for adding a new sprite to the list
     * @param s , a Sprite to add to the list
    */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**.
    * to notify all the objects that time has passed
    * @param dt , the rate of frames
    */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.sprites.size(); i++) {
            sprites.get(i).timePassed(dt);
        }
    }

    /**.
   * a method to draw all the objects
   * @param d , a DrawSurface to draw on
   */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }

    /**.
   * a method to remove a sprite from sprites
   * @param s , we will miss you
   */
    public void removeFromSprites(Sprite s) {
        this.sprites.remove(s);
    }

}
