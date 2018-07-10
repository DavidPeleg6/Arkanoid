package levelbuilder;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import animations.Sprite;
import gamelogic.LevelInformation;
import gamelogic.Velocity;
import shapes.Block;

/**.
 * @author David Geda
 * ID: 313237182
 * LevelSpecificationReader class
 */
public class LevelSpecificationReader {

    private String levelName, blockDefinitions;
    private List<Velocity> ballVelocities;
    private Sprite background;
    private double paddleSpeed;
    private int paddleWidth, rowHeight, numBlocks, startX, startY;
    private List<Block> blocks;

  /**.
     * constructor
     */
    public LevelSpecificationReader() {
        this.resetValues();
    }

  /**.
     * reset
     */
    private void resetValues() {
        this.levelName = null;
        this.blockDefinitions = null;
        this.ballVelocities = new LinkedList<Velocity>();
        this.background = null;
        this.paddleSpeed = -1;
        this.paddleWidth = -1;
        this.rowHeight = -1;
        this.numBlocks = -1;
        this.startX = -1;
        this.startY = -1;
        this.blocks = new ArrayList<Block>();
    }

  /**.
     * @return boolean ,
     */
    private boolean checkValid() {
        if ((this.levelName == null)
                || (this.blockDefinitions == null)
                || (this.ballVelocities == null)
                || (this.background == null)
                || (this.paddleSpeed == -1)
                || (this.paddleWidth == -1)
                || (this.rowHeight == -1)
                || (this.numBlocks == -1)
                || (this.startX == -1)
                || (this.startY == -1)
                || (this.blocks == null)) {
            return false;
        }
        return true;
    }

  /**.
     * @param s ,
     */
    private void errorMessage(String s) {
        System.out.println(s);
        System.exit(1);
    }

  /**.
     * @param reader ,
     * @return List<LevelInformation> ,
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new LinkedList<LevelInformation>();
        String[] levelSegment = this.getLevels(reader);
        for (String level : levelSegment) {
            this.resetValues();
            String[] lines = level.split("[\\r\\n]+");
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].startsWith("#")) {
                    continue;
                }
                String[] splits = lines[i].split(":");
                switch (splits[0]) {
                case "paddle_speed":
                    this.paddleSpeed = Integer.parseInt(splits[1]);
                    break;

                case "paddle_width":
                    this.paddleWidth = Integer.parseInt(splits[1]);
                    break;

                case "level_name":
                    this.levelName = new String(splits[1]);
                    break;

                case "block_definitions":
                    this.blockDefinitions = new String(splits[1]);
                    break;

                case "blocks_start_x":
                    this.startX = Integer.parseInt(splits[1]);
                    break;

                case "blocks_start_y":
                    this.startY = Integer.parseInt(splits[1]);
                    this.startY += 30;
                    break;

                case "row_height":
                    this.rowHeight = Integer.parseInt(splits[1]);
                    break;

                case "num_blocks":
                    this.numBlocks = Integer.parseInt(splits[1]);
                    break;

                case "ball_velocities":
                    String[] split2 = splits[1].split(" ");
                    for (String s : split2) {
                        String[] split3 = s.split(",");
                        double angle, speed;
                        try {
                            if (split3.length == 2) {
                                angle = Double.parseDouble(split3[0]);
                                speed = Double.parseDouble(split3[1]);
                                Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                                this.ballVelocities.add(v);
                            } else {
                                this.errorMessage("incorrect ball speed format");
                            }
                        } catch (Exception e) {
                            this.errorMessage("incorrect ball speed format");
                        }
                    }
                    break;
                case "background":
                    int index = splits[1].indexOf('(');
                    int endIndex = splits[1].lastIndexOf(')');
                    String type = splits[1].substring(0, index);
                    String info = splits[1].substring(index + 1, endIndex);
                    try {
                        if (type.equalsIgnoreCase("image")) {
                            this.background = new ImageBackground(info);
                        }
                        if (type.equalsIgnoreCase("color")) {
                            Color c = ColorsParser.colorFromString(info);
                            ColorBackground back = new ColorBackground(c);
                            back.setX(800);
                            back.setY(600);
                            this.background = back;
                        }
                    } catch (NumberFormatException e) {
                        this.errorMessage("color values not allegible");
                    } catch (IOException e) {
                        this.errorMessage("Image not Found");
                    }
                    break;
                case "START_BLOCKS":
                    BlocksDefinitionReader bReader = new BlocksDefinitionReader();
                    InputStream is = ClassLoader.getSystemClassLoader()
                            .getResourceAsStream(this.blockDefinitions);
                    BufferedReader blocksReader = new BufferedReader(new InputStreamReader(is));
                    BlocksFromSymbolsFactory blockFact = bReader.fromReader(blocksReader);
                    for (int j = i + 1; j < lines.length; j++) {
                        if (lines[j].equals("END_BLOCKS")) {
                            break;
                        }
                        this.createBlockLine(lines[j], blockFact);
                    }
                    try {
                        is.close();
                    } catch (IOException e) {
                        this.errorMessage("unnable to close reader");
                    }
                    break;
         default:
                }
            }
            if (!this.checkValid()) {
                this.errorMessage("missing definitions check file");
            }
      Level temp = new Level(this.ballVelocities.size(), this.paddleWidth,
                    this.numBlocks, this.paddleSpeed,
                    this.ballVelocities, this.blocks,
                    this.background);
      temp.setLevelName(this.levelName);
            levels.add(temp);
        }
        if (levels.isEmpty()) {
            this.errorMessage("no definitions");
        }
        return levels;
    }

  /**.
     * @param line ,
     * @param blockFact ,
     */
    private void createBlockLine(String line, BlocksFromSymbolsFactory blockFact) {
        int x = this.startX;
        String[] symbols = line.split("");
        for (int i = 1; i < symbols.length; i++) {
            if (blockFact.isBlockSymbol(symbols[i])) {
                Block b = blockFact.getBlock(symbols[i], x, this.startY);
                this.blocks.add(b);
                x += b.getCollisionRectangle().getWidth();
                continue;
            }
            if (blockFact.isSpaceSymbol(symbols[i])) {
                x += blockFact.getSpaceWidth(symbols[i]);
                continue;
            }
        }
        this.startY += this.rowHeight;
    }

  /**.
     * @param reader ,
     * @return String[] ,
     */
    private String[] getLevels(java.io.Reader reader) {
                int intValueOfChar;
                String text = "";
                try {
                    while ((intValueOfChar = reader.read()) != -1) {
                        text += (char) intValueOfChar;
                    }
                } catch (IOException e) {
                    this.errorMessage("unnable to read from reader");
                }
        String[] tempLevels = text.split("START_LEVEL");
        List<String> levels = new LinkedList<String>();
        for (String level : tempLevels) {
            if (level.contains("END_LEVEL")) {
                levels.add(level);
            }
        }
        String[] levelsFinal = new String[1];
        levelsFinal = levels.toArray(levelsFinal);
        return levelsFinal;
    }
}