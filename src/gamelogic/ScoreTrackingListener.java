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
public class ScoreTrackingListener implements HitListener {

       private Counter currentScore;

        /**.
        * constructor method of the class
        * @param scoreCounter , a counter for we score
        */
       public ScoreTrackingListener(Counter scoreCounter) {
          this.currentScore = scoreCounter;
       }

        /**.
        * a method responsible for marking a hit event
        * @param beingHit , the block that is currently hit
        * @param hitter , the violent ball
        */
       public void hitEvent(Block beingHit, Ball hitter) {
           this.currentScore.increase(5);
           if (beingHit.getHitStatus() == 0) {
               this.currentScore.increase(5);
               beingHit.removeHitListener(this);
           }
       }

        /**.
        * a getter for the current score
        * @return int , the current score
        */
       public int getCurScore() {
           return this.currentScore.getValue();
       }

        /**.
        * a setter for the score
        * @param score , a new score to be saved
        */
       public void setScoreListen(int score) {
           this.currentScore = new Counter(score);
       }
}
