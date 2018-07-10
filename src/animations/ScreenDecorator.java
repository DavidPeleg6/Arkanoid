package animations;

import biuoop.DrawSurface;

/**.
 * @author David Geda
 * ID: 313237182
 * ScreenDecorator class
 */
public abstract class ScreenDecorator implements Animation {

    private Animation decorated;

     /**.
      * constructor method of the class.
      *@param decorated ,
      */
    protected ScreenDecorator(Animation decorated) {
        this.decorated = decorated;
    }

       /**.
       * a method for running a frame
       * @param d , a draw surface to draw on
       * @param dt , the rate of frames
       */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        decorated.doOneFrame(d, dt);

    }

       /**.
       * a method for running a frame
       *@return boolean
       */
    @Override
    public boolean shouldStop() {
        return decorated.shouldStop();
    }

}
