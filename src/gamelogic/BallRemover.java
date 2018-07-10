package gamelogic;
import animations.Counter;
import shapes.Ball;
import shapes.Block;

/**.
 * @author David Geda
 * ID: 313237182
 * BallRemover class
 * a listener
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**.
   * constructor method of the class
   * @param game , a game to listen to
   * @param removedBalls , the number of balls to be removed
   */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

        /**.
        * a method responsible for marking a hit event
        * @param beingHit , the block that is currently hit
        * @param hitter , the violent ball
        */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeBallFromGame(this.game);
        if (this.remainingBalls.getValue() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
        }
    }

       /**.
       * a getter for the number of remaining balls in game
       * @return int , number of remaining balls
       */
    public int getNumberOfBalls() {
        return this.remainingBalls.getValue();
    }

       /**.
       * a setter for the number of remaining balls
       * @param c , the counter
       */
    public void setCounter(Counter c) {
        this.remainingBalls = c;
    }

}
