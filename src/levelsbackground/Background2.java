package levelsbackground;
import java.awt.Color;

import animations.Sprite;
import biuoop.DrawSurface;
import gamelogic.GameLevel;
import gamelogic.Velocity;
import shapes.Line;
import shapes.Point;

/**.
 * @author David Geda
 * ID: 313237182
 * Background2 class
 * an object responsible for managing the background
 */
public class Background2 implements Sprite {

    private Point ball;
    private int radius;
    private Line[] sun;
    private Velocity[] velocities;

    /**.
    * constructor method of the class
    */
    public Background2() {
        this.ball = new Point(400, 50);
        this.radius = 120;
        this.sun = new Line[30];
        this.velocities = new Velocity[this.sun.length];
        this.createLines(this.sun.length);
    }

    /**.
    * a method for creating the sun rays for the background
    * @param amount , the amount of lines to add
    */
    private void createLines(int amount) {
        for (int i = 0; i < amount; i++) {
            double x = i * 27;
            double y = 600;
            this.sun[i] = new Line(this.ball, new Point(x, y));
        }
        for (int i = 0; i < amount / 2; i++) {
            this.velocities[i] = new Velocity(100, -100);
        }
        for (int i = amount / 2; i < amount; i++) {
            this.velocities[i] = new Velocity(-100, -100);
        }
    }

    /**.
    * a method for drawing the background to the draw surface
    * @param d , a draw surface to draw on
    */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.YELLOW);
        d.fillCircle((int) this.ball.getX(), (int) this.ball.getY(), this.radius);
        for (int i = 0; i < this.sun.length; i++) {
            Line temp = this.sun[i];
            d.drawLine((int) temp.start().getX(), (int) temp.start().getY(),
                    (int) temp.end().getX(), (int) temp.end().getY());
        }
        d.setColor(Color.ORANGE);
        d.fillCircle((int) this.ball.getX(), (int) this.ball.getY() , this.radius - 50);
        d.setColor(Color.BLUE);
        d.fillCircle((int) this.ball.getX(), (int) this.ball.getY() , this.radius - 80);
    }

    /**.
    * a method for notifying all the lines that time has passed
    * @param dt , the rate of frames
    */
    @Override
    public void timePassed(double dt) {
        for (int i = 0; i < this.sun.length; i++) {
            this.sun[i] = new Line(this.ball, this.velocities[i].applyToPoint(this.sun[i].end(), dt));
            if (this.sun[i].end().getY() <= this.sun[i].start().getY() + 200) {
                this.velocities[i] = new Velocity((-1) * this.velocities[i].getDX(),
         (-1) * this.velocities[i].getDY());
            }
            if (this.sun[i].end().getY() >= 610) {
                this.velocities[i] = new Velocity((-1) * this.velocities[i].getDX(),
         (-1) * this.velocities[i].getDY());
            }
        }
    }

    /**.
    * a method for adding this background to the game
    * @param g , a game level to add itself to
    */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
