package animations;

import java.awt.Color;

import biuoop.DrawSurface;
import gamelogic.ScoreIndicator;

/**.
 * @author David Geda
 * ID: 313237182
 * YouLoseScreen class
 */
public class YouLoseScreen extends ScreenDecorator {

    private String message;

    /**.
   * constructor method of the class
   *@param ind ,
   *@param animation ,
   */
    public YouLoseScreen(ScoreIndicator ind, Animation animation) {
        super(animation);
        this.message = "Game Over.  Your score is: " + ind.getScore();
    }

    /**.
    * a method for running a frame
    * @param d , a draw surface to draw on
    * @param dt , the rate of frames
    */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.RED);
        d.drawText(10, d.getHeight() / 2, this.message, 32);
        d.drawText(10, d.getHeight() / 2 + 50, "press space to continue", 20);
    }
}
