package menu;

import animations.Animation;
import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

/**.
 * @author David Geda
 * ID: 313237182
 * ShowHiScoreTask class
 */
public class ShowHiScoreTask implements Task<Void> {

    private AnimationRunner runn;
    private Animation highScoresAnimation;
    private KeyboardSensor keySense;

   /**.
   * constructor
    * @param runner ,
    * @param highScoresAnimation1 ,
    * @param keySense1 ,
    */
    public ShowHiScoreTask(AnimationRunner runner,
   Animation highScoresAnimation1, KeyboardSensor keySense1) {
        this.runn = runner;
        this.highScoresAnimation = highScoresAnimation1;
        this.keySense = keySense1;
        }

   /**.
    * @return Void ,
    */
    @Override
    public Void run() {
        Animation animation = new KeyPressStoppableAnimation(keySense,
     KeyboardSensor.SPACE_KEY, highScoresAnimation);
        this.runn.run(animation);
        return null;
    }

}
