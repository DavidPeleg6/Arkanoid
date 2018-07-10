package gamelogic;
import java.awt.Color;

import animations.Sprite;
import biuoop.DrawSurface;

/**.
 * @author David Geda
 * ID: 313237182
 * ScoreIndicator class
 * a class for keeping score
 */
public class ScoreIndicator implements Sprite {

    private int score;
    private int size;
    private Color color;

    /**.
   * constructor method of the class
   * @param score , the starting score
   * @param size , the size of score to be printed
   * @param color , the color to be printed
   */
    public ScoreIndicator(int score, int size, Color color) {
        this.score = score;
        this.size = size;
        this.color = color;
    }

    /**.
   * a method to make the object draw itself on the DrawSurface
   * @param d , the DrawSurface to draw on
   */
    @Override
    public void drawOn(DrawSurface d) {
        String s = "Score: " + Integer.toString(this.score);
        d.setColor(this.color);
        d.drawText(d.getWidth() / 2 - 50, 20, s, this.size);
    }

    /**.
       * a mysterious method to notify the object time has passed. it has great potential yet to be revealed
       * @param dt , the rate of frames
       */
    @Override
    public void timePassed(double dt) {
    }

       /**.
       * a method to make the indicator add itself to the Game
       * @param g , a Game to add the indicator to
       */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }

       /**.
       * a setter for the current score
       * @param score1 , a score to add in to
       */
    public void setScore(int score1) {
        this.score = score1;
    }

       /**.
       * a getter to recieve the current score
       * @return int , a score to return
       */
    public int getScore() {
        return this.score;
    }

}
