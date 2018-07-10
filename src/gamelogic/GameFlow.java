package gamelogic;
import java.awt.Color;
import java.util.List;

import animations.Animation;
import animations.AnimationRunner;
import animations.EndScreen;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.YouLoseScreen;
import animations.YouWinScreen;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;

/**.
 * @author David Geda
 * ID: 313237182
 * GameFlow class
 * an object responsible for moving through the levels
 */
public class GameFlow {

    private AnimationRunner animRun;
    private KeyboardSensor keySense;
    private LivesIndicator lives;
    private ScoreIndicator scoreInd;

    /**.
   * constructor method of the class
   * @param ar , an animationRunner
   * @param ks , a KeyboardSensor
   * @param lives , the number of wanted lives
   */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives) {
        this.animRun = ar;
        this.keySense = ks;
        this.scoreInd = new ScoreIndicator(0, 20, Color.BLACK);
        this.lives = new LivesIndicator(lives, 20, Color.BLACK);
    }

    /**.
   * the method for running all the levels
   * @param levels , the different levels of this game
   * @param highS , the current score table
   * @return HighScoresTable , the table of new high scores
   * will run according to the arrange in which they were put in
   */
    public HighScoresTable runLevels(List<LevelInformation> levels, HighScoresTable highS) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.animRun, levelInfo,
                    this.keySense, this.scoreInd, this.lives);
            level.initialize();
            level.playOneTurn();
            if (this.lives.getLives() == 0) {
                break;
            }
        }
        Animation end;
        if (this.lives.getLives() == 0) {
            end = new YouLoseScreen(this.scoreInd, new EndScreen());
        } else {
            end = new YouWinScreen(this.scoreInd, new EndScreen());
        }
        Animation endScreen1 = new KeyPressStoppableAnimation(this.keySense, KeyboardSensor.SPACE_KEY, end);
        this.animRun.run(endScreen1);
        int thisScore = this.scoreInd.getScore();
        if (highS.getRank(thisScore) <= highS.size()) {
            DialogManager dialog = this.animRun.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo newHigh = new ScoreInfo(name, thisScore);
            highS.add(newHigh);
            //System.out.println(name);
        }
        Animation highScore = new HighScoresAnimation(highS, new EndScreen());
        Animation endScreen2 = new KeyPressStoppableAnimation(this.keySense, KeyboardSensor.SPACE_KEY, highScore);
        this.animRun.run(endScreen2);
        return highS;
    }

}
