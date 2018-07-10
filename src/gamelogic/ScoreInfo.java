package gamelogic;

import java.io.Serializable;

/**.
 * @author David Geda
 * ID: 313237182
 * ScoreInfo class
 */
public class ScoreInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**.
     * Create a new high score info
     *  @param name1 , the name of scorer
     *  @param score1 , the high score
     */
    public ScoreInfo(String name1, int score1) {
        this.name = name1;
        this.score = score1;
    }

    /**.
     * a getter for name
     *  @return String , the name of scorer
     */
    public String getName() {
        return this.name;
    }

    /**.
     * a getter for score
     *  @return int , the score
     */
    public int getScore() {
        return this.score;
    }

}
