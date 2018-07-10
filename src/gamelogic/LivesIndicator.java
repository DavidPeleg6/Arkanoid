package gamelogic;
import java.awt.Color;

import animations.Sprite;
import biuoop.DrawSurface;

/**.
 * @author David Geda
 * LivesIndicator class
 * a listener for the amount of lives
 */
public class LivesIndicator implements Sprite {

    private int lives;
    private int size;
    private Color color;

    /**.
    * constructor method of the class
    * @param lives , the number of wanted lives
    * @param size , the wanted size of display
    * @param color , the color to display in
    */
    public LivesIndicator(int lives, int size, Color color) {
        this.lives = lives;
        this.size = size;
        this.color = color;
    }

    /**.
    * a method to draw the object on a given DrawSurface
    * @param d , a DrawSurface to draw on
    */
    @Override
    public void drawOn(DrawSurface d) {
        String s = "Lives: " + Integer.toString(this.lives);
        d.setColor(this.color);
        d.drawText(d.getWidth() / 20, 20, s, this.size);
    }

    /**.
    * to notify the object time has passed and make it move
    * @param dt , the rate of frames
    */
    @Override
    public void timePassed(double dt) {
    }

    /**.
    * a method to make the object add itself to the current Game
    * @param g , the game level to add to
    */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**.
    * a getter to the amount of lives left
    * @return int , an amount of life remaining
    */
    public int getLives() {
        return this.lives;
    }

    /**.
    * a method to set the amount of lives left
    * @param lives1 , the number of lives
    */
    public void setLives(int lives1) {
        this.lives = lives1;
    }

}
