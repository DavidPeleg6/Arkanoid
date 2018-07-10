package levelsbackground;
import java.awt.Color;

import animations.Sprite;
import biuoop.DrawSurface;
import gamelogic.GameLevel;
import gamelogic.LivesIndicator;
import gamelogic.ScoreIndicator;

/**.
 * @author David Geda
 * Header class
 * an object for displaying all the information of the game
 */
public class Header implements Sprite {

    private ScoreIndicator s;
    private LivesIndicator l;
    private String lvlName;

    /**.
   * constructor method of the class
   * @param s , a score indicator of the game
   * @param l , a lives indicator
   * @param lvlName , the name of the current level
   */
    public Header(ScoreIndicator s, LivesIndicator l, String lvlName) {
        this.s = s;
        this.l = l;
        this.lvlName = lvlName;
    }

    /**.
   * a method to make the Block draw itself on the DrawSurface
   * @param d , the DrawSurface to draw on
   */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), 40);
        s.drawOn(d);
        l.drawOn(d);
        d.setColor(Color.BLACK);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() - 250, 20, this.lvlName, 20);
    }

    /**.
       * a mysterious method to notify the Block time has passed. it has great potential yet to be revealed
       * @param dt , the rate of frames
       */
    @Override
    public void timePassed(double dt) {
    }

    /**.
   * a method to make the Block add itself to the Game
   * @param g , a Game to add the Block to
   */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
