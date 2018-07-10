package levelbuilder;

import java.awt.Color;

import animations.Sprite;
import biuoop.DrawSurface;
import gamelogic.GameLevel;

/**.
 * @author David Geda
 * ID: 313237182
 * ColorBackground class
 */
public class ColorBackground implements Background, Sprite {

    private Color color;
    private int height, width;

  /**.
     * the constructor of the class
     * @param color1 ,
     */
    public ColorBackground(java.awt.Color color1) {
        this.color = color1;
    }

  /**.
     * @param d ,
     * @param x ,
     * @param y ,
     */
    @Override
    public void drawBack(DrawSurface d, int x, int y) {
        d.setColor(this.color);
        d.fillRectangle(x, y, this.width, this.height);
    }

  /**.
     * @param d ,
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, this.width, this.height);
    }

  /**.
     * @param dt ,
     */
    @Override
    public void timePassed(double dt) {
    }

  /**.
     * @param g ,
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

  /**.
     * @param x ,
     */
    @Override
    public void setX(int x) {
        this.width = x;
    }

  /**.
     * @param y ,
     */
    @Override
    public void setY(int y) {
        this.height = y;
    }
}
