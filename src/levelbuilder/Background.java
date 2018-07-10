package levelbuilder;

import biuoop.DrawSurface;

/**.
 * @author David Geda
 * ID: 313237182
 * Background interface
 */
public interface Background {

    /**.
   * @param x ,
   */
    void setX(int x);

    /**.
   * @param y ,
   */
    void setY(int y);

    /**.
   * @param d ,
   * @param x ,
   * @param y ,
   */
    void drawBack(DrawSurface d, int x, int y);
}
