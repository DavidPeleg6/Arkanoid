package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * @author David Geda
 * ID: 313237182
 * KeyPressStoppableAnimation class
 * an object for handling the logic of the screens
 */
public class KeyPressStoppableAnimation extends ScreenDecorator {

    private KeyboardSensor sensor;
    private String key;
    private boolean shouldStop;
    private boolean isAlreadyPressed;

    /**.
   * constructor method of the class
   * @param sensor , a keyboard sensor
   * @param key , the key for stopping
   * @param animation , a the parent animation
   */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        super(animation);
        this.sensor = sensor;
        this.key = key;
        this.isAlreadyPressed = true;
        this.shouldStop = false;
    }

    /**.
    * a method for running a frame
    * @param d , a draw surface to draw on
    * @param dt , the rate of frames
    */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        super.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.shouldStop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**.
   * a method for deciding whether the loop should stop
   * @return boolean , should or shouldnt
   */
    @Override
    public boolean shouldStop() {
        return !this.shouldStop;
    }

}
