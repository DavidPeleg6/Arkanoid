package levelbuilder;

import shapes.Block;

/**.
 * @author David Geda
 * ID: 313237182
 * BlockCreator interface
 */
public interface BlockCreator {

   /**.
   * @param xpos ,
   * @param ypos ,
   * @return Block ,
   */
    Block create(int xpos, int ypos);
}
