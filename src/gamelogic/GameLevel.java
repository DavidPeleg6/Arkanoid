package gamelogic;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.Counter;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import animations.Paddle;
import animations.PauseScreen;
import animations.Sprite;
import animations.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levelbuilder.Background;
import levelbuilder.ColorBackground;
import levelsbackground.Header;
import shapes.Ball;
import shapes.Block;
import shapes.Point;
import shapes.Rectangle;

/**.
 * @author David Geda
 * GameLevel class
 * an object managing the gameLevel.
 * also responsible for innitializing and moving all the game objects.
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemov;
    private BallRemover ballRemov;
    private ScoreTrackingListener scoreStat;
    private ScoreIndicator scoreInd;
    private Paddle paddle;
    private LivesIndicator lives;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation lvlInfo;
    private KeyboardSensor keySense;
    private Map<Integer, Background> borderColor;

    /**.
   * constructor method of the class.
   * @param runner , an animation runner
   * @param lvlInfo , the level information
   * @param keySense , a keyboard sensor
   * @param scoreInd , a score indicator
   * @param lives , the current amount of lives
   */
    public GameLevel(AnimationRunner runner, LevelInformation lvlInfo, KeyboardSensor keySense,
            ScoreIndicator scoreInd, LivesIndicator lives) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        lvlInfo.getBackground().addToGame(this);
        this.blockRemov = new BlockRemover(this, new Counter(lvlInfo.getBlocks().size()));
        this.keySense = keySense;
        this.scoreInd = scoreInd;
        this.scoreStat = new ScoreTrackingListener(new Counter(scoreInd.getScore()));
        this.lives = lives;
        this.runner = runner;
        this.running  = true;
        this.lvlInfo = lvlInfo;
        this.borderColor = new HashMap<Integer, Background>();
        this.borderColor.put(0, new ColorBackground(Color.BLUE));
    }

    /**.
   * a method add another Collidable to the Game
   * @param c , the new Collidable to be added.
   */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**.
   * a method add another Sprite to the Game
   * @param s , the new Sprite to be added.
   */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**.
    * a method to innitialize all the Game objects required
    */
    public void initialize() {
        //create Borders , Collidables who gained immortality
        this.createBorders(this.lvlInfo.getWidth(), this.lvlInfo.getHeight());
        //create Blocks
        for (Block b : this.lvlInfo.getBlocks()) {
            b.addHitListener(this.scoreStat);
            b.addHitListener(this.blockRemov);
            b.addToGame(this);
        }
        //add score counter
        this.scoreInd.addToGame(this);
        //add life counter
        this.lives.addToGame(this);
        Header head = new Header(this.scoreInd, this.lives, this.lvlInfo.getLevelName());
        head.addToGame(this);
    }

    /**.
    * a method to to run all the innitialized objects in the game
    */
    public void playOneTurn() {
        this.createPaddleAndBalls(this.lvlInfo.getWidth(), this.lvlInfo.getHeight());
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.runner.run(this);
    }

    /**.
    * a method to to create the paddle and the balls in the game
    * @param width , the border of this game
    * @param height , the border of this game
    */
    private void createPaddleAndBalls(int width, int height) {
        //create Paddle
        this.paddle = new Paddle(this.keySense, width, height, this.lvlInfo.getPaddleWidth());
        this.paddle.setPaddleSpeed(this.lvlInfo.getPaddleSpeed());
        this.paddle.addToGame(this);
        Block deathBlock = new Block(new Rectangle(new Point(0, height), width + 20, 30), 0);
        deathBlock.setBackground(this.borderColor);
        deathBlock.addToGame(this);
        this.ballRemov = new BallRemover(this, new Counter(this.lvlInfo.getNumberOfBalls()));
        deathBlock.addHitListener(this.ballRemov);
        //create balls
        Ball[] balls = new Ball[this.lvlInfo.getNumberOfBalls()];
        for (int i = 0; i < this.lvlInfo.getNumberOfBalls(); i++) {
            balls[i] = new Ball((width / 2) - 50, (height / 2) + 200, 4, java.awt.Color.WHITE);
            balls[i].setVelocity(this.lvlInfo.getInitialBallVelocities().get(i));
            balls[i].setGameEnvironment(this.environment);
            balls[i].addToGame(this);
        }
    }

    /**.
    * a method to create the borders of the Game
    * @param width , the borders of the game
    * @param height , the borders of the game
    */
    public void createBorders(int width, int height) {
        Block[] blocks = new Block[3];
        //borders
        blocks[0] = new Block(new Rectangle(new Point(0, 0), width + 50, 40), 0);
        blocks[1] = new Block(new Rectangle(new Point(-40, 0), 40, height + 50), 0);
        blocks[2] = new Block(new Rectangle(new Point(width, 0), 40, height + 50), 0);
        for (int i = 0; i < 3; i++) {
            blocks[i].setBackground(this.borderColor);
            blocks[i].addToGame(this);
        }
    }

    /**.
   * a method for removing a sprite fro mthe game
   * @param c , a collidable to remove from the game
   */
    public void removeCollidable(Collidable c) {
        this.environment.removeFromEnv(c);
    }

    /**.
   * a method for removing a sprite fro mthe game
   * @param s , a sprite to remove
   */
    public void removeSprite(Sprite s) {
        this.sprites.removeFromSprites(s);
    }

       /**.
       * a method for running a frame
       * @param d , a draw surface to draw on
       * @param dt , the rate of frames
       */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        biuoop.KeyboardSensor keyboard = this.runner.getGui().getKeyboardSensor();
        if (keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(new EndScreen())));
         }
        //draw all Sprites
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        this.scoreInd.setScore(scoreStat.getCurScore());
        if (this.blockRemov.getRemainingBlocks() == 0) {
              this.scoreStat.setScoreListen(this.scoreStat.getCurScore() + 100);
              this.scoreInd.setScore(this.scoreStat.getCurScore());
              this.sprites.drawAllOn(d);
              this.running = false;
        } else if (this.ballRemov.getNumberOfBalls() == 0) {
              this.paddle.removePaddleFromGame(this);
              this.lives.setLives(this.lives.getLives() - 1);
              if (this.lives.getLives() == 0) {
                  this.sprites.drawAllOn(d);
                  this.running = false;
              } else if (this.blockRemov.getRemovedAmount() >= this.lvlInfo.getNumberOfBlocksToRemove()) {
                  this.lives.setLives(this.lives.getLives() + 1);
                  this.sprites.drawAllOn(d);
                  this.running = false;
              } else {
                  this.createPaddleAndBalls(this.lvlInfo.getWidth(), this.lvlInfo.getHeight());
                  this.runner.run(new CountdownAnimation(6, 3, this.sprites));
              }
        }
    }

    /**.
   * a method for deciding whether the loop should stop
   * @return boolean , should or shouldnt
   */
    @Override
    public boolean shouldStop() {
        return this.running;
    }

}
