package levelbuilder;

import java.util.HashMap;
import java.util.Map;

import shapes.Block;

/**.
 * @author David Geda
 * ID: 313237182
 * BlocksFromSymbolsFactory class
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

   /**.
     * the constructor of the class
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators = new HashMap<String, BlockCreator>();
    }

   /**.
     * @param s ,
     * @param x ,
     */
    public void addSpaceSymbol(String s, Integer x) {
        if (this.spacerWidths.containsKey(s)) {
            System.out.println("spacer " + s + " is already defined");
            System.exit(1);
        }
        this.spacerWidths.put(s, x);
    }

   /**.
     * @param s ,
     * @param block ,
     */
    public void addBlockCreator(String s, BlockCreator block) {
        if (this.blockCreators.containsKey(s)) {
            System.out.println("block " + s + " is already defined");
            System.exit(1);
        }
        this.blockCreators.put(s, block);
    }

   /**.
     * @param s ,
     * @return boolean
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

   /**.
     * @param s ,
     * @return boolean
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

   /**.
     * @param s ,
     * @return int
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

   /**.
     * @param s ,
     * @param xpos ,
     * @param ypos ,
     * @return Block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }
}
