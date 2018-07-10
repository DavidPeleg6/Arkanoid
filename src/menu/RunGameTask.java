package menu;

import java.io.File;
import java.io.IOException;
import java.util.List;

import animations.AnimationRunner;
import biuoop.KeyboardSensor;
import gamelogic.GameFlow;
import gamelogic.HighScoresTable;
import gamelogic.LevelInformation;

/**.
 * @author David Geda
 * ID: 313237182
 * RunGameTask class
 */
public class RunGameTask implements Task<Void> {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private int lives;
    private HighScoresTable table;
    private File file;
    private List<LevelInformation> levels;

   /**.
   * constructor
    * @param levels1 ,
    * @param ar1 ,
    * @param ks1 ,
    * @param table1 ,
    * @param file1 ,
    * @param lives1 ,
    */
    public RunGameTask(List<LevelInformation> levels1,
            AnimationRunner ar1, KeyboardSensor ks1,
            HighScoresTable table1, File file1, int lives1) {
        this.table = table1;
        this.file = file1;
        this.levels = levels1;
        this.ar = ar1;
        this.ks = ks1;
        this.lives = lives1;
    }

   /**.
    * @return Void ,
    */
    @Override
    public Void run() {
        GameFlow gFlow = new GameFlow(this.ar, this.ks, this.lives);
        this.table = gFlow.runLevels(this.levels, this.table);
        try {
            this.table.save(file);
        } catch (IOException e) {
            System.out.println("unnable to save game call tech support");
        }
        return null;
    }

}
