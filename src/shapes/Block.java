package shapes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import animations.Sprite;
import biuoop.DrawSurface;
import gamelogic.Collidable;
import gamelogic.GameLevel;
import gamelogic.HitListener;
import gamelogic.HitNotifier;
import gamelogic.Velocity;
import levelbuilder.Background;

/**.
 * @author David Geda
 * ID: 313237182
 * Block class
 * an object representing a Block.
 * also responsible for drawing itself.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Map<Integer, Background> fills;
    private Rectangle rect;
    private int hitPoints;

    /**.
   * constructor method of the class
   * @param rect , the Rectangle the Block represents
   * @param hitPoints , the amount of HP the Block will have
   */
    public Block(Rectangle rect, int hitPoints) {
        this.rect = rect;
        this.hitPoints = hitPoints;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**.
   * @param stroke ,
   */
    public void setStroke(Color stroke) {
        this.rect.setColor(stroke);
    }

    /**.
   * @param fills1 ,
   */
    public void setBackground(Map<Integer, Background> fills1) {
        this.fills = fills1;
    }

    /**.
   * the accesor method to get the current amount of HP the Block has
   * @return int , the HP value of the Block.
   */
    public int getHitStatus() {
        return this.hitPoints;
    }

    /**.
   * the accesor method to get the Rectangle this Block represents
   * @return Rectangle , the Rectangle this Block represents.
   */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**.
   * a method to to notify the Block that it has been hit
   * @param hitter , the ball involved in the hit
   * @param collisionPoint , the Point of collision with this Block
   * @param currentVelocity , the current Velocity of the object hitting this Block
   * @return Velocity , the new Velocity the colliding object should have
   */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = null;
        //reduce the Block HP
        this.hitPoints--;
        //check which side of this Block the object hit and change the object Velocity accordingly
        if ((Math.round(collisionPoint.getX()) == Math.round(this.rect.getUpperLeft().getX()))
    || (Math.round(collisionPoint.getX())
    == Math.round(this.rect.getUpperLeft().getX() + this.rect.getWidth()))) {
            v = new Velocity(currentVelocity.getDX() * (-1), currentVelocity.getDY());
        } else if ((Math.round(collisionPoint.getY()) == Math.round(this.rect.getUpperLeft().getY()))
    || (Math.round(collisionPoint.getY())
                            == Math.round(this.rect.getUpperLeft().getY())
              + Math.round(this.rect.getHeight()))) {
            v = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * (-1));
        } else {
            this.notifyHit(hitter);
            return currentVelocity;
        }
        this.notifyHit(hitter);
        return v;
    }

    /**.
   * a method to make the Block draw itself on the DrawSurface
   * @param d , the DrawSurface to draw on
   */
    public void drawOn(DrawSurface d) {
        //draw the borders of this Block
        if (this.fills.containsKey(this.hitPoints)) {
            if (this.fills.get(this.hitPoints) != null) {
            this.fills.get(this.hitPoints).drawBack(d,
                    (int) this.rect.getUpperLeft().getX(),
                    (int) this.rect.getUpperLeft().getY());
            }
        } else {
            this.fills.get(0).drawBack(d,
                    (int) this.rect.getUpperLeft().getX(),
                    (int) this.rect.getUpperLeft().getY());
        }
        this.rect.drawRectangle(d);
        //draw this Block's HP
        //d.setColor(Color.WHITE);
        //if (this.hitPoints > 0) {
            //d.drawText((int) (this.rect.getUpperLeft().getX() +
      // this.rect.getWidth() / 2), (int) (this.rect.getUpperLeft().getY()
      //+ 2 * this.rect.getHeight() / 3)
                //    , "" + this.hitPoints, 20);
        //}

    }

    /**.
   * a mysterious method to notify the Block time has passed. it has great potential yet to be revealed
   * @param dt , the rate of frames
   */
    public void timePassed(double dt) {

    }

    /**.
   * a method to make the Block add itself to the Game
   * @param g , a Game to add the Block to
   */
    public void addToGame(GameLevel g) {
        Sprite s = this;
        Collidable c = this;
        g.addCollidable(c);
        g.addSprite(s);
    }

    /**.
     * a method for removing this block from the game
     * @param game , a game to remove from
    */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**.
     * a method for adding a hit listener to this block
     * @param hl , a hit listener to add
    */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**.
     * a method for removing a hit listener from this block
     * @param hl , a hit listener to remove
    */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**.
     * a method for notifying an object its been hit through the hit listener
     * @param hitter , the ball which interacted with this block
    */
     private void notifyHit(Ball hitter) {
          // Make a copy of the hitListeners before iterating over them.
          List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
          // Notify all listeners about a hit event:
          for (HitListener hl : listeners) {
             hl.hitEvent(this, hitter);
          }
       }

}
