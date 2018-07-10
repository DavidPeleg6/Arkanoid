
import java.io.File;
import java.io.IOException;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.HighScoresAnimation;
import biuoop.KeyboardSensor;
import gamelogic.HighScoresTable;
import levelsbackground.Background2;
import menu.Menu;
import menu.MenuAnimation;
import menu.QuitGameTask;
import menu.ShowHiScoreTask;
import menu.SubMenuBuilder;
import menu.Task;

/**.
 * @author David Geda
 * ID: 313237182
 * Ass6Game class
 * the class ontaining the main method
 */
public class Ass6Game {

    /**.
     * the main method for running this game
   * @param args , arguments from user will define the levels played
   * and their order
   */
    public static void main(String[] args) {
        String path = null;
        if (args.length > 0) {
            path = args[0];
        } else {
            path = "level_sets.txt";
        }
        File file = new File("highscores");
        HighScoresTable scores = HighScoresTable.loadFromFile(file);
        if (scores.getHighScores().isEmpty()) {
            //the table is empty
            try {
                scores.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AnimationRunner runner = new AnimationRunner();
        KeyboardSensor keySense = runner.getGui().getKeyboardSensor();
        HighScoresAnimation hiS = new HighScoresAnimation(scores, new EndScreen());
        SubMenuBuilder subMenu = new SubMenuBuilder("choose difficulty", new Background2(), keySense,
                runner, scores, file, path);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("arkenoid", new Background2(), keySense);
        menu.addSelection("q", "quit", new QuitGameTask(runner));
        menu.addSelection("h", "highscore table", new ShowHiScoreTask(runner, hiS, keySense));
        menu.addSelection("s", "start new game", subMenu);
        while (true) {
            runner.run(menu);
            Task<Void> t = menu.getStatus();
            t.run();
        }
    }
}
