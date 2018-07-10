package shapes;

import java.awt.Color;

import animations.Sprite;
import biuoop.DrawSurface;
import gamelogic.Collidable;
import gamelogic.CollisionInfo;
import gamelogic.GameEnvironment;
import gamelogic.GameLevel;
import gamelogic.Velocity;

/**.
 * @author David Geda
 * Ball class
 * an object representing a ball.
 * also responsible for moving and drawing itself.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnv;
    private Point hitPoint;
    private Collidable collisionObj;
    private Line trajectory;

      /**.
     * constructor method of the class
     * @param center , the center Point of the Ball.
     * @param r , the radius of the Ball
     * @param color , the color of the Ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.hitPoint = null;
    }

      /**.
     * constructor method of the class
     * @param x , the x value of the starting Point of the Ball.
     * @param y , the y value of the starting Point of the Ball.
     * @param r , the radius of the Ball
     * @param color , the color of the Ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.hitPoint = null;
    }

      /**.
     * a method to set the object's velocity
     * @param dx , the pace of location change in X axis.
     * @param dy , the pace of location change in y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

      /**.
     * a method to set the object's velocity
     * @param v , the object's wanted Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

   /**.
   * a method to set the object's game environment
   * @param game , the object's wanted GameEnvironment.
   */
    public void setGameEnvironment(GameEnvironment game) {
        this.gameEnv = game;
    }

      /**.
     * the accesor method to get the current x value of the center of the Ball
     * @return int , the x value of the center of the Ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

      /**.
     * the accesor method to get the current y value of the center of the Ball
     * @return int , the y value of the center of the Ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

      /**.
     * the accesor method to get the radius of the Ball
     * @return int , the radius value of the Ball.
     */
    public int getSize() {
        return this.radius;
    }

      /**.
     * the accesor method to get the color of the Ball
     * @return java.awt.Color , the color of the Ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

      /**.
     * the accesor method to get the Velocity of the Ball
     * @return Velocity , the Velocity of the Ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

      /**.
     * a method to make the Ball move one step in its Velocity direction
     * @param dt , the rate of frames
     */
    public void moveOneStep(double dt) {
        CollisionInfo info;
    //check the Ball has velocity, if not set default Velocity
        if (this.velocity == null) {
            this.setVelocity(1, 1);
        }
    //cumpute trajectory and get the next hitPoint from it
        this.trajectory = this.computeTrajectory(dt);
        info = this.gameEnv.getClosestCollision(this.trajectory);
        if (info != null) {
            this.hitPoint = info.collisionPoint();
            this.collisionObj = info.collisionObject();
            }
        if (this.checkIfHit(dt)) {
              this.moveToValid(dt);
              this.velocity = this.collisionObj.hit(this, this.hitPoint, this.velocity);
              this.hitPoint = null;
            }
        this.center = this.velocity.applyToPoint(this.center, dt);
    }

  /**.
   * a method to make the Ball return to its last valid location
   * @param dt , the rate of frames
   */
    private void moveToValid(double dt) {
        Velocity tempVelocity = new Velocity(-(this.velocity.getDX()), -(this.velocity.getDY()));
        Point tempCenter = tempVelocity.applyToPoint(this.center, dt);
        this.center = new Point(tempCenter.getX(), tempCenter.getY());
        }

      /**.
     * a method to draw the Ball on a given DrawSurface
     * @param surface , a DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

      /**.
     * a method to check whether the Ball bounced through the hit Point
     * @param dt , the rate of frames
     * @return boolean , true if center within boundaries
     */
    public boolean checkIfHit(double dt) {
        if (this.hitPoint == null) {
            return false;
        }
        Point tempCenter = this.center;
        double difX = tempCenter.getX() - this.hitPoint.getX();
        double difY = tempCenter.getY() - this.hitPoint.getY();
        tempCenter = this.velocity.applyToPoint(tempCenter, dt);
        double newDifX = tempCenter.getX() - this.hitPoint.getX();
        double newDifY = tempCenter.getY() - this.hitPoint.getY();
        //check that the ratio between the ball old location and its new one is still positive
        if (((difX / newDifX) <= 0) || ((difY / newDifY) <= 0)) {
            return true;
        }
           return false;
    }

    /**.
    * a method to to compute the trajectory of the ball by calculating its movement 20 steps forward
    * @param dt , the rate of frames
    * @return Line , the trajectory of the ball
    */
    private Line computeTrajectory(double dt) {
        Velocity tempVelocity = new Velocity(20 * this.velocity.getDX(), 20 * this.velocity.getDY());
        Point tempCenter = tempVelocity.applyToPoint(this.center, dt);
        return new Line(this.center.getX(), this.center.getY(), tempCenter.getX(), tempCenter.getY());
    }

    /**.
    * to notify the ball time has passed and make it move
    * @param dt , the rate of frames
    */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**.
    * a method to make the Ball class add itself to the current Game
    *@param g , a gamelevel to add the ball to
    */
    public void addToGame(GameLevel g) {
        Sprite s = this;
        g.addSprite(s);
    }

    /**.
    * a method to remove the ball from the game
    *@param game , a gamelevel to remove the ball from
    */
    public void removeBallFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
