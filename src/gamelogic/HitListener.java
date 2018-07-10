package gamelogic;
import shapes.Ball;
import shapes.Block;

/**
 * @author dudug
 * HitListener interface
 * a listener interface
 */
public interface HitListener {
        /**.
        * a method responsible for marking a hit event
        * @param beingHit , the block that is currently hit
        * @param hitter , the violent ball
        */
       void hitEvent(Block beingHit, Ball hitter);
}
