package menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animations.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**.
 * @author David Geda
 * ID: 313237182
 * MenuAnimation class
 * an object for showing the menu of the game
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {

    private List<Triplets<T>> tasks;
    private Sprite background;
    private T status;
    private String menuTitle;
    private KeyboardSensor sense;
    private boolean shouldStop;

   /**.
   * constructor
    * @param menuTitle1 ,
    * @param back ,
    * @param sense1 ,
    */
    public MenuAnimation(String menuTitle1, Sprite back, KeyboardSensor sense1) {
        this.tasks = new ArrayList<Triplets<T>>();
        this.background = back;
        this.sense = sense1;
        this.shouldStop = false;
        this.menuTitle = menuTitle1;
    }

   /**.
    * @param d ,
    * @param dt ,
    */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.background.drawOn(d);
        this.drawPairs(d);
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.sense.isPressed(tasks.get(i).getKey())) {
                this.status = this.tasks.get(i).getTask();
                this.shouldStop = true;
            }
        }
    }

   /**.
    * @param d ,
    */
    private void drawPairs(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 20, this.menuTitle, 25);
        for (int i = 0; i < tasks.size(); i++) {
            String message1 = "press " + this.tasks.get(i).getKey();
            String message2 = this.tasks.get(i).getName();
            d.drawText(10, (d.getHeight() / 10) + 20 + (i * 30), message1, 25);
            d.drawText(200, (d.getHeight() / 10) + 20 + (i * 30), message2, 25);
        }
    }

   /**.
    * @return boolean ,
    */
    @Override
    public boolean shouldStop() {
        return !this.shouldStop;
    }

   /**.
    * @param key ,
    * @param message ,
    * @param returnVal ,
    */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.tasks.add(new Triplets<T>(key, message, returnVal));
    }

   /**.
    * @return T ,
    */
    @Override
    public T getStatus() {
        this.shouldStop = false;
        return this.status;
    }
}
