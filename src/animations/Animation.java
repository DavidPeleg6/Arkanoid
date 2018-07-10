package animations;
import biuoop.DrawSurface;

/**.
 * @author David Geda
 * Animation interface
 * contains the animations in
 */
public interface Animation {

    /**.
     * a method for running a single frame
    * @param d , a DrawSurface to draw on
    * @param dt , the rate of frames
    */
       void doOneFrame(DrawSurface d, double dt);

        /**.
         * a method for deciding wether a loop should keep running
         * @return boolean ,
        */
       boolean shouldStop();
}
