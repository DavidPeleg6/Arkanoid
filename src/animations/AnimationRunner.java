package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**.
 * @author David Geda
 * ID: 313237182
 * AnimationRunner class
 * an object responsible for running the animations.
 */
public class AnimationRunner {

       private GUI gui;
       private int framesPerSecond;

        /**.
        * constructor method of the class
        */
       public AnimationRunner() {
            this.gui = new GUI("Arkanoid", 800, 600);
            this.framesPerSecond = 60;
       }

        /**.
        * constructor method of the class
        * @param gui , the gui to work with
        */
       public AnimationRunner(GUI gui) {
           this.gui = gui;
           this.framesPerSecond = 60;
       }

        /**.
        * a getter of the class
        * @return GUI , this class gui
        */
       public GUI getGui() {
           return this.gui;
       }

        /**.
        * a getter of this class
        * @return int , the amount of fframes per second
        */
       public int getFramesPerSec() {
           return this.framesPerSecond;
       }

       /**.
       * a setter of this class
       * @param frames , the amount of frames per sec
       */
       public void setFramesPerSec(int frames) {
           this.framesPerSecond = frames;
       }

        /**.
        * a method for running a given animation
        * @param animation , an animation to run
        */
       public void run(Animation animation) {
          Sleeper sleeper = new Sleeper();
          int millisecondsPerFrame = 1000 / this.framesPerSecond;
          while (animation.shouldStop()) {
             long startTime = System.currentTimeMillis(); // timing
             DrawSurface d = gui.getDrawSurface();
             animation.doOneFrame(d, (1.0 / this.framesPerSecond));
             gui.show(d);
             long usedTime = System.currentTimeMillis() - startTime;
             long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
             if (milliSecondLeftToSleep > 0) {
                 sleeper.sleepFor(milliSecondLeftToSleep);
             }
          }
       }
}