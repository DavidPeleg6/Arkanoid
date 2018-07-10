package menu;

import animations.AnimationRunner;

/**.
 * @author David Geda
 * ID: 313237182
 * QuitGameTask Interface
 */
public class QuitGameTask implements Task<Void> {

    private AnimationRunner runner;

   /**.
   * constructor
    * @param runner1 ,
    */
    public QuitGameTask(AnimationRunner runner1) {
        this.runner = runner1;
    }

   /**.
    * @return Void ,
    */
    @Override
    public Void run() {
        this.runner.getGui().close();
        System.exit(0);
        return null;
    }

}
