package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import animations.AnimationRunner;
import animations.Sprite;
import biuoop.KeyboardSensor;
import gamelogic.HighScoresTable;
import gamelogic.LevelInformation;
import levelbuilder.LevelSpecificationReader;

/**.
 * @author David Geda
 * ID: 313237182
 * SubMenuBuilder class
 */
public class SubMenuBuilder implements Task<Void> {

    private Menu<Task<Void>> subMenu;
    private KeyboardSensor keySense;
    private AnimationRunner runner;
    private HighScoresTable scores;
    private File file;
    private String levelSetsPath;

   /**.
   * constructor
    * @param name ,
    * @param background ,
    * @param keySense1 ,
    * @param runner1 ,
    * @param scores1 ,
    * @param file1 ,
    * @param levelSetsPath1 ,
    */
    public SubMenuBuilder(String name, Sprite background, KeyboardSensor keySense1,
            AnimationRunner runner1, HighScoresTable scores1, File file1, String levelSetsPath1) {
        this.subMenu = new MenuAnimation<Task<Void>>(name, background, keySense1);
        this.runner = runner1;
        this.scores = scores1;
        this.file = file1;
        this.keySense = keySense1;
        this.levelSetsPath = levelSetsPath1;
        this.getLevelSets();
    }

   /**.
    * get level sets
    */
    private void getLevelSets() {
        LineNumberReader reader = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.levelSetsPath);
        if (is == null) {
            System.out.println("level sets file could not be found");
            System.exit(1);
        }
           reader = new LineNumberReader(new BufferedReader(new InputStreamReader(is)));
           String line;
        try {
            line = reader.readLine();
            while (line != null) {
                List<LevelInformation> levels = null;
                String[] keyVal = null;
                if ((reader.getLineNumber() % 2) != 0) {
                    keyVal = this.getKeyVal(line);
                    line = reader.readLine();
                    levels = this.getLevels(line);
                    this.subMenu.addSelection(keyVal[0], keyVal[1],
                            new RunGameTask(levels, this.runner,
                                    this.keySense, this.scores, this.file, 7));
                } else {
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("level sets file could not be read");
            System.exit(1);
        }
        try {
            is.close();
        } catch (IOException e) {
            System.out.println("unnable to close file");
            System.exit(1);
        }
    }

   /**.
    * @param line ,
    * @return String[] ,
    */
    private String[] getKeyVal(String line) {
        String[] keyVal = line.split(":");
        if (keyVal[0].length() != 1) {
            System.out.println("only single char allowed in selection");
            System.exit(1);
        }
        return keyVal;
    }

   /**.
   * @param path ,
    * @return List<LevelInformation> ,
    */
    private List<LevelInformation> getLevels(String path) {
        LevelSpecificationReader lev = new LevelSpecificationReader();
           InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
           BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<LevelInformation> levels = lev.fromReader(reader);
        try {
            is.close();
        } catch (IOException e) {
            System.out.println("unnable to close file");
            System.exit(1);
        }
        return levels;
    }

   /**.
    * @return Void ,
    */
    @Override
    public Void run() {
        this.runner.run(this.subMenu);
        Task<Void> t = this.subMenu.getStatus();
        t.run();
        return null;
    }

}
