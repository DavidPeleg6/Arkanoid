package gamelogic;
import java.util.List;

import animations.Sprite;
import shapes.Block;

/**.
 * @author David Geda
 * LevelInformation interface
 * an interface for all levels
 */
public interface LevelInformation {
    /**.
   * a getter for the number of wanted balls
   * @return int , the number of balls
   */
   int getNumberOfBalls();

       /**.
       * a getter for the velocities of all the balls
       * @return List , a list of speeds
       */
   List<Velocity> getInitialBallVelocities();
          /**.
       * a getter for the wanted speed of the paddle
       * @return double , the wanted paddle speed
       */
   double getPaddleSpeed();
          /**.
       * a getter for the wanted width of the paddle
       * @return int , the width of the paddle
       */
   int getPaddleWidth();
       /**.
   * a getter the name of the level
   * @return String , a level name
   */
   String getLevelName();
          /**.
       * a getter for the background of this game
       * @return Sprite , a new background
       */
   Sprite getBackground();
   /**.
   * a getter for the blocks to be added to the game
   * @return List , a list of blocks
   */
   List<Block> getBlocks();
    /**.
   * a getter for the wanted number of blocks in game
   * @return List , a list of blocks
   */
   int getNumberOfBlocksToRemove();
          /**.
       * a getter for the height of the game level
       * @return int , the height of the level
       */
   int getHeight();

    /**.
   * a getter for the width of the game level
   * @return int , the width of the level
   */
   int getWidth();
}