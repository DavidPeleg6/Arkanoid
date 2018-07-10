package levelbuilder;

import shapes.Block;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.Map;

/**.
 * @author David Geda
 * ID: 313237182
 * BlocksCreator class
 */
public class BlocksCreator implements BlockCreator {

    private int height, width, hitPoints;
    private Map<Integer, Background> backgrounds;
    private Color stroke;

   /**.
   * @param height1 ,
   * @param width1 ,
   * @param hitpoints1 ,
   * @param backgrounds1 ,
   * @param stroke1 ,
   */
    public BlocksCreator(int height1, int width1,
            int hitpoints1, Map<Integer, Background> backgrounds1,
            Color stroke1) {
        this.height = height1;
        this.width = width1;
        this.hitPoints = hitpoints1;
        this.backgrounds = backgrounds1;
        this.stroke = stroke1;
    }

   /**.
   * @param xpos ,
   * @param ypos ,
   * @return Block ,
   */
    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rect = new Rectangle(new Point(xpos, ypos), this.width, this.height);
        Block block = new Block(rect, this.hitPoints);
        block.setStroke(this.stroke);
        block.setBackground(this.backgrounds);
        return block;
    }
}
