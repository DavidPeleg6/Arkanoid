package levelbuilder;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**.
 * @author David Geda
 * ID: 313237182
 * BlocksDefinitionReader class
 */
public class BlocksDefinitionReader {

    private int defWidth;
    private int defHeight;
    private int defHitpoints;
    private Background defBack;
    private Color defStroke;

    private String symbol;
    private int height;
    private int width;
    private int hitpoints;
    private Map<Integer, Background> backgrounds;
    private Color stroke;
    private String spacerSymbol;
    private int spacerWidth;

    /**.
     * the constructor of the class
     */
    public BlocksDefinitionReader() {
        this.resetDefault();
        this.resetValues();
    }

    /**.
     * reset the default values
     */
    private void resetDefault() {
        this.defWidth = 0;
        this.defHeight = 0;
        this.defHitpoints = 0;
        this.defBack = null;
        this.defStroke = null;
    }

    /**.
     * reset the temp values
     */
    private void resetValues() {
        this.symbol = null;
        this.spacerSymbol = null;
        this.height = this.defHeight;
        this.width = this.defWidth;
        this.hitpoints = this.defHitpoints;
        this.backgrounds = new HashMap<Integer, Background>();
        this.backgrounds.put(0, this.defBack);
        this.stroke = this.defStroke;
        this.spacerWidth = this.defWidth;
    }

    /**.
     * @return boolean ,
     */
    private boolean checkBlockFields() {
        if ((this.symbol != null) && (this.height > 0) && (this.width > 0)
       && (this.hitpoints > 0) && this.checkBackground()
                && !this.backgrounds.isEmpty()) {
            return true;
        }
        return false;
    }

    /**.
     * @param s ,
     */
    private void errorMessage(String s) {
        System.out.println(s);
        System.exit(1);
    }

    /**.
     * @param line ,
     */
    private void setDefaults(String line) {
        String[] splits = line.split(" ");
        for (int i = 1; i < splits.length; i++) {
            String[] keyVal = splits[i].split(":");
            switch (keyVal[0]) {
            case "width":
                this.defWidth = Integer.parseInt(keyVal[1]);
                break;
            case "height":
                this.defHeight = Integer.parseInt(keyVal[1]);
                break;
            case "hit_points":
                this.defHitpoints = Integer.parseInt(keyVal[1]);
                break;
            case "fill":
                this.defBack = this.getBackground(keyVal[1]);
                if (this.defBack == null) {
                    this.errorMessage("inavlid fill type in defaults in block data");
                }
                break;
            case "stroke":
                this.defStroke = this.getStroke(keyVal[1]);
                if (this.defStroke == null) {
                    this.errorMessage("invalid type stroke in block data");
                }
                break;
            default:
            }
        }
    }

    /**.
     * @param expression ,
     * @return Background ,
     */
    private Background getBackground(String expression) {
        int index = expression.indexOf('(');
        int endIndex = expression.lastIndexOf(')');
        String type = expression.substring(0, index);
        String info = expression.substring(index + 1, endIndex);
        if (type.equals("image")) {
            try {
                return new ImageBackground(info);
            } catch (IOException e) {
                this.errorMessage("unnable to find image in block data");
            }
        }
        if (type.equals("color")) {
            return new ColorBackground(ColorsParser.colorFromString(info));
        }
        return null;
    }

    /**.
     * @param expression ,
     * @return Color ,
     */
    private Color getStroke(String expression) {
        int index = expression.indexOf('(');
        int endIndex = expression.lastIndexOf(')');
        String type = expression.substring(0, index);
        String info = expression.substring(index + 1, endIndex);
        if (type.equals("color")) {
            return ColorsParser.colorFromString(info);
        }
        return null;
    }

    /**.
     * @return boolean ,
     */
    private boolean checkBackground() {
        if (this.backgrounds.get(0) != null) {
            return true;
        }
        for (int i = 1; i <= this.hitpoints; i++) {
            if (!this.backgrounds.containsKey(i)) {
                return false;
            }
        }
        return true;
    }

    /**.
     * sets the borders of the background
     */
    private void setBackgroundBorders() {
        for (int i = 0; i <= this.hitpoints; i++) {
            if (this.backgrounds.containsKey(i)) {
                if (this.backgrounds.get(i) != null) {
                    this.backgrounds.get(i).setX(this.width);
                    this.backgrounds.get(i).setY(this.height);
                }
            }
        }
    }

    /**.
     * @param line ,
     * @return BlockCreator ,
     */
    private BlockCreator setBlocks(String line) {
        this.resetValues();
        String[] split = line.split(" ");
        for (int i = 1; i < split.length; i++) {
            String[] keyVal = split[i].split(":");
            switch (keyVal[0]) {
            case "symbol":
                if (keyVal[1].length() == 1) {
                    this.symbol = keyVal[1];
                } else {
                    this.errorMessage("invalid symbol in blocks data");
                }
                break;
            case "width":
                this.width = Integer.parseInt(keyVal[1]);
                break;
            case "height":
                this.height = Integer.parseInt(keyVal[1]);
                break;
            case "hit_points":
                this.hitpoints = Integer.parseInt(keyVal[1]);
                break;
            case "stroke":
                this.stroke = this.getStroke(keyVal[1]);
                if (this.stroke == null) {
                    this.errorMessage("Invalid data line in bdef, stroke.");
                }
                break;
            default:
                if (keyVal[0].startsWith("fill")) {
                    Integer backNumber = 0;
                    if (keyVal[0].contains("-")) {
                        String[] value = keyVal[0].split("-");
                        backNumber = Integer.parseInt(value[1]);
                    }
                    Background back = this.getBackground(keyVal[1]);
                    if (back != null) {
                        this.backgrounds.put(backNumber, back);
                    } else {
                        this.errorMessage("invalid fill in blocks data");
                    }
                break;
                }
            }
        }
        if (!checkBlockFields()) {
            this.errorMessage("missing information in block data");
        }
        this.setBackgroundBorders();
        return new BlocksCreator(this.height, this.width, this.hitpoints,
                this.backgrounds, this.stroke);
    }

    /**.
     * @param line ,
     */
    private void setSpacer(String line) {
        this.resetValues();
        String[] split = line.split(" ");
        for (int i = 1; i < split.length; i++) {
            String[] keyVal = split[i].split(":");
            switch (keyVal[0]) {
            case "symbol":
                if (keyVal[1].length() == 1) {
                    this.spacerSymbol = keyVal[1];
                } else {
                    this.errorMessage("spacer symbol value should be a char");
                }
                break;
            case "width":
                this.spacerWidth = Integer.parseInt(keyVal[1]);
                break;
            default:
            }
        }
        if ((this.spacerSymbol == null)
                || this.spacerSymbol.equals(" ")) {
            this.errorMessage("unidentified spacer symbol in block data");
        }
        if (this.spacerWidth <= 0) {
            this.errorMessage("invalid spacer width");
        }
    }

    /**.
     * @param reader ,
     * @return String[] ,
     */
    private String[] getLines(java.io.Reader reader) {
        //getting a string representation of text
        int intValueOfChar;
        String text = "";
        try {
            while ((intValueOfChar = reader.read()) != -1) {
                text += (char) intValueOfChar;
            }
        } catch (IOException e) {
            this.errorMessage("unnable to read from reader");
        }
        //split text by lines
        String[] lines = text.split("[\\r\\n]+");
        return lines;
    }

    /**.
     * @param reader ,
     * @return BlocksFromSymbolsFactory ,
     */
    public BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory blocks = new BlocksFromSymbolsFactory();
        String[] lines = this.getLines(reader);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith("#")) {
                continue;
            }
            if (lines[i].startsWith("default")) {
                this.setDefaults(lines[i]);
            }
            if (lines[i].startsWith("bdef")) {
                BlockCreator blockCreate = this.setBlocks(lines[i]);
                blocks.addBlockCreator(this.symbol, blockCreate);
            }
            if (lines[i].startsWith("sdef")) {
                this.setSpacer(lines[i]);
                blocks.addSpaceSymbol(this.spacerSymbol, this.spacerWidth);
            }
        }
        return blocks;
    }
}