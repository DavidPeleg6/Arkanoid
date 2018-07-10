package levelbuilder;

import java.util.List;

import animations.Sprite;
import gamelogic.LevelInformation;
import gamelogic.Velocity;
import shapes.Block;

/**.
 * @author David Geda
 * ID: 313237182
 * Level class
 * the class ontaining the main logic the levels
 */
public class Level implements LevelInformation {


    private int numOfBalls, paddleWidth, numBlocksRemove, width, height;
    private double paddleSpeed;
    private List<Velocity> intitialBallVel;
    private List<Block> blocks;
    private Sprite background;
    private String levelName;

  /**.
     * the constructor of the class
     * @param numOfBalls1 ,
     * @param paddleWidth1 ,
     * @param numBlocksRemove1 ,
     * @param paddleSpeed1 ,
     * @param intitialBallVel1 ,
     * @param blocks1 ,
     * @param background1 ,
     */
    public Level(int numOfBalls1, int paddleWidth1,
            int numBlocksRemove1, double paddleSpeed1,
            List<Velocity> intitialBallVel1, List<Block> blocks1,
            Sprite background1) {
        this.numOfBalls = numOfBalls1;
        this.paddleWidth = paddleWidth1;
        this.numBlocksRemove = numBlocksRemove1;
        this.paddleSpeed = paddleSpeed1;
        this.intitialBallVel = intitialBallVel1;
        this.blocks = blocks1;
        this.background = background1;
      this.height = 600;
        this.width = 800;
    }

  /**.
     * @param levelName1 ,
     */
  public void setLevelName(String levelName1) {
    this.levelName = levelName1;
  }

  /**.
     * @return int ,
     */
    @Override
    public int getNumberOfBalls() {
        // TODO Auto-generated method stub
        return this.numOfBalls;
    }

  /**.
     * @return List<Velocity> ,
     */
    @Override
    public List<Velocity> getInitialBallVelocities() {
        // TODO Auto-generated method stub
        return this.intitialBallVel;
    }

  /**.
     * @return double ,
     */
    @Override
    public double getPaddleSpeed() {
        // TODO Auto-generated method stub
        return this.paddleSpeed;
    }

  /**.
     * @return int ,
     */
    @Override
    public int getPaddleWidth() {
        // TODO Auto-generated method stub
        return this.paddleWidth;
    }

  /**.
     * @return String ,
     */
    @Override
    public String getLevelName() {
        // TODO Auto-generated method stub
        return this.levelName;
    }

  /**.
     * @return Sprite ,
     */
    @Override
    public Sprite getBackground() {
        // TODO Auto-generated method stub
        return this.background;
    }

  /**.
     * @return List<Block> ,
     */
    @Override
    public List<Block> getBlocks() {
        // TODO Auto-generated method stub
        return this.blocks;
    }

  /**.
     * @return int ,
     */
    @Override
    public int getNumberOfBlocksToRemove() {
        // TODO Auto-generated method stub
        return this.numBlocksRemove;
    }

  /**.
     * @return int ,
     */
    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return this.height;
    }

  /**.
     * @return int ,
     */
    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return this.width;
    }
}
