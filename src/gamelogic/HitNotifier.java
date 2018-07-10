package gamelogic;
/**
 * @author dudug
 * Hitnotifier interface
 * a listener
 */
public interface HitNotifier {

          /**.
     * a method for adding a hit listener to this block
     * @param hl , a hit listener to add
    */
       void addHitListener(HitListener hl);

    /**.
     * a method for removing a hit listener from this block
     * @param hl , a hit listener to remove
    */
       void removeHitListener(HitListener hl);
}
