package animations;

import biuoop.DrawSurface;

/**.
 * @author David Geda
 * ID: 313237182
 * EndScreen class
 * an object for showing the screen at the end of the entire game
 */
public class EndScreen implements Animation {

    /**.
   * constructor method of the class
   */
    public EndScreen() {
    }

       /**.
       * a method for running a frame
       * @param d , a draw surface to draw on
       * @param dt , the rate of frames
       */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
    }

    /**.
   * a method for deciding whether the loop should stop
   * @return boolean , should or shouldnt
   */
    @Override
    public boolean shouldStop() {
        return true;
    }

}
