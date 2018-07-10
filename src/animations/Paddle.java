package animations;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamelogic.Collidable;
import gamelogic.GameLevel;
import gamelogic.Velocity;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;

/**.
 * @author David Geda
 * Paddle class
 * contains the rectangle responsible for the paddle.
 */
public class Paddle implements Collidable, Sprite {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int width, paddleSize;
    private Velocity paddleSpeed;

    /**.
    * constructor of the class.
    * @param keyboard , the keyboard to recieve hits from the user.
    * @param width , the wanted width of the paddle.
    * @param height , the wanted height of the paddle.
    * @param paddleSize , the wanted size of the paddle
    */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int height, int paddleSize) {
        this.keyboard = keyboard;
        this.width = width;
        this.paddleSize = paddleSize;
        this.paddleSpeed = new Velocity(5.0, 0.0);
        this.createRectPaddle(new Point((width / 2) - (paddleSize / 2) - 40, height - 20));
    }

    /**.
    * a method to make the speed of the paddle
    * @param paddleSpeed1 , the wanted speed of the paddle
    */
    public void setPaddleSpeed(Velocity paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }

    /**.
    * a method to make the speed of the paddle
    * @param paddleSpeedDx , the wanted speed of the paddle
    */
    public void setPaddleSpeed(double paddleSpeedDx) {
        this.paddleSpeed = new Velocity(paddleSpeedDx, 0.0);
    }
    /**.
   * method for moving the paddle to the left by one step
   * @param dt , the rate of frames
   */
    public void moveLeft(double dt) {
        //check if it reached the border (the border is 20 for now)
        if (this.paddle.getUpperLeft().getX() <= 0) {
            return;
        }
        if (this.paddleSpeed.getDX() > 0) {
            this.setPaddleSpeed((-1) * this.paddleSpeed.getDX());
        }
        this.createRectPaddle(this.paddleSpeed.applyToPoint(paddle.getUpperLeft(), dt));
    }

    /**.
   * method for moving the paddle to the right by one step
   * @param dt , the rate of frames
   */
    public void moveRight(double dt) {
        //check if it reached the border (the border is 20 for now)
        if (this.paddle.getUpperLeft().getX() + this.paddleSize >= this.width) {
            return;
        }
        if (this.paddleSpeed.getDX() < 0) {
            this.setPaddleSpeed((-1) * this.paddleSpeed.getDX());
        }
        this.createRectPaddle(this.paddleSpeed.applyToPoint(paddle.getUpperLeft(), dt));
    }

    /**.
   * method for notifying the paddle that time has passed
   * @param dt , the rate of frames
   */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**.
   * method for adding this paddle to the given Game
   * @param g , a Game to add this paddle to
   */
    public void addToGame(GameLevel g) {
        Sprite s = this;
        Collidable c = this;
        g.addCollidable(c);
        g.addSprite(s);
    }

    /**.
   * accessor method for getting the rectangle this paddle represents
   * @return Rectangle , the paddle's Rectangle
   */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**.
   * method for notifying the paddle that an object has collided with it. and decide
   * the objects new Velocity.
   * @param hitter , the hitter ball
   * @param collisionPoint , a collision point
   * @param currentVelocity , the current velocity
   * @return Velocity , the new velocity
   */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //check the object has hit the top of the paddle
        if ((Math.round(collisionPoint.getY()) == Math.round(this.paddle.getUpperLeft().getY()))
    && (collisionPoint.getX() > this.paddle.getUpperLeft().getX())
     && (collisionPoint.getX() < (this.paddle.getUpperLeft().getX()
      + this.paddle.getWidth()))) {
            //if it hit part 1 of the paddle
            if (collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + this.paddleSize / 5) {
                double speed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
         + Math.pow(currentVelocity.getDY(), 2));
                Velocity velocity = Velocity.fromAngleAndSpeed(240, speed);
                return velocity;
                //if it hit part 2 of the paddle
            } else if ((collisionPoint.getX() > this.paddle.getUpperLeft().getX()
       + this.paddleSize / 5)
                    && (collisionPoint.getX() <= this.paddle.getUpperLeft().getX()
           + 2 * this.paddleSize / 5)) {
                double speed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
         + Math.pow(currentVelocity.getDY(), 2));
                Velocity velocity = Velocity.fromAngleAndSpeed(210, speed);
                return velocity;
                //if it hit part 3 of the paddle
            } else if ((collisionPoint.getX() > this.paddle.getUpperLeft().getX()
       + 2 * this.paddleSize / 5)
                    && (collisionPoint.getX() <= this.paddle.getUpperLeft().getX()
           + 3 * this.paddleSize / 5)) {
                double speed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
         + Math.pow(currentVelocity.getDY(), 2));
                Velocity velocity = Velocity.fromAngleAndSpeed(180, speed);
                return velocity;
                //if it hit part 4 of the paddle
            } else if ((collisionPoint.getX() > this.paddle.getUpperLeft().getX()
       + 3 * this.paddleSize / 5)
                    && (collisionPoint.getX() <= this.paddle.getUpperLeft().getX()
           + 4 * this.paddleSize / 5)) {
                double speed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
         + Math.pow(currentVelocity.getDY(), 2));
                Velocity velocity = Velocity.fromAngleAndSpeed(150, speed);
                return velocity;
                //if it hit part 5 of the paddle
            } else {
                double speed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
         + Math.pow(currentVelocity.getDY(), 2));
                Velocity velocity = Velocity.fromAngleAndSpeed(120, speed);
                return velocity;
            }
        }
            return new Velocity(currentVelocity.getDX() * (-1), currentVelocity.getDY());
    }

    /**.
   * method for drawing the paddle on the given draw surface
   * @param d , a DrawSurface to draw on.
   */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                this.paddleSize, 10);
        //draw the paddle's borders
        this.paddle.drawRectangle(d);
    }

    /**.
   * method for creating a new paddle
   * @param upperLeft , the upperleft point of the paddle
   */
    private void createRectPaddle(Point upperLeft) {
        this.paddle = new Rectangle(upperLeft, this.paddleSize, 10);
        this.paddle.setColor(Color.BLACK);
    }

    /**.
     * a method for removing a hit listener from this block
     * @param game , a game to remove from
    */
    public void removePaddleFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

}
