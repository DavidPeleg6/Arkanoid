package gamelogic;
import animations.Counter;
import shapes.Ball;
import shapes.Block;

/**.
 * @author David Geda
 * ID: 313237182
 * BlockRemover class
 * an listener object
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;
    private Counter removedBlocks;

    /**.
   * constructor method of the class
   * @param game , the game to listen to
   * @param removedBlocks , the amount removed from the game?
   */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.removedBlocks = new Counter(0);
    }

    /**.
   * a method responsible for marking a hit event
   * @param beingHit , the block that is currently hit
   * @param hitter , the violent ball
   */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitStatus() < 1) {
            this.remainingBlocks.decrease(1);
            this.removedBlocks.increase(1);
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
        }

    }

    /**.
   * a getter to recieve the remaining blocks in game
   * @return int , the remaining blocks
   */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }

    /**.
   * a getter to recieve the remaining blocks in game
   * @return int , the remaining blocks
   */
    public int getRemovedAmount() {
        return this.removedBlocks.getValue();
    }

    /**.
   * a setter to to set the counter of this game
   * @param c , a counter to add
   */
    public void setCounter(Counter c) {
        this.remainingBlocks = c;
    }

}
