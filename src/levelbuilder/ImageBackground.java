package levelbuilder;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import animations.Sprite;
import biuoop.DrawSurface;
import gamelogic.GameLevel;

/**.
 * @author David Geda
 * ID: 313237182
 * ImageBackground class
 */
public class ImageBackground implements Background, Sprite {

    private Image img;

  /**.
     * the constructor of the class
     * @param path ,
     * @throws IOException ,
     */
    public ImageBackground(String path)
    throws IOException {
        InputStream is = ClassLoader.getSystemClassLoader().
                                    getResourceAsStream(path);
        this.img = ImageIO.read(is);
    }

  /**.
     * @param d ,
     * @param x ,
     * @param y ,
     */
    @Override
    public void drawBack(DrawSurface d, int x, int y) {
        d.drawImage(x, y, img);
    }

  /**.
     * @param x ,
     */
    @Override
    public void setX(int x) {
    }

  /**.
     * @param y ,
     */
    @Override
    public void setY(int y) {
    }

  /**.
     * @param d ,
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, img);
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
}
