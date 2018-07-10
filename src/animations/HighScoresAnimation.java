package animations;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import gamelogic.HighScoresTable;
import gamelogic.ScoreInfo;

/**.
 * @author David Geda
 * ID: 313237182
 * HighScoresAnimation class
 * an object for showing the high scores table
 */
public class HighScoresAnimation extends ScreenDecorator {

    private HighScoresTable scores;

    /**.
   * constructor method of the class
   * @param animation , the parent animation
   * @param score , a high score table
   */
    public HighScoresAnimation(HighScoresTable score, Animation animation) {
        super(animation);
        this.scores = score;
    }

    /**.
    * a method for running a frame
    * @param d , a draw surface to draw on
    * @param dt , the rate of frames
    */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 20, "High Scores: ", 32);
        if (this.scores.getHighScores().isEmpty()) {
            d.drawText(10, d.getHeight() / 2, "No high scores... YET ", 32);
        } else {
            List<ScoreInfo> highS = this.scores.getHighScores();
            for (int i = 1; i <= highS.size(); i++) {
                String name = i + ". " + highS.get(i - 1).getName() + ":";
                String curScore = Integer.toString(highS.get(i - 1).getScore());
                d.drawText(10, (d.getHeight() / 20) + (25 * i), name, 20);
                d.drawText(300, (d.getHeight() / 20) + (25 * i), curScore, 20);
            }
        }
    }
}
