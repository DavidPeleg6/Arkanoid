package menu;

import animations.Animation;

/**.
 * @author David Geda
 * ID: 313237182
 * Menu Interface
 * an object for showing the menu of the game
 * @param <T>
 */
public interface Menu<T> extends Animation {

    /**.
    * @param key ,
    * @param message ,
    * @param returnVal ,
    */
    void addSelection(String key, String message, T returnVal);

   /**.
    * @return <T> ,
    */
    T getStatus();
}
