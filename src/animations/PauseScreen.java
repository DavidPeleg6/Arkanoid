package animations;
import java.awt.Color;

import biuoop.DrawSurface;

/**.
 * @author David Geda
 * ID: 313237182
 * PauseScreen class
 * an object for stopping the game
 */
public class PauseScreen extends ScreenDecorator {

    private String message;

     /**.
      * constructor method of the class
      *@param animation , an animation to pause
      */
       public PauseScreen(Animation animation) {
           super(animation);
           this.message = "Game Paused.  press space to continue";
       }

       /**.
       * a method for running a frame
       * @param d , a draw surface to draw on
       * @param dt , the rate of frames
       */
       @Override
       public void doOneFrame(DrawSurface d, double dt) {
           d.setColor(Color.BLACK);
           d.drawText(10, d.getHeight() / 2, this.message, 32);
       }
}
