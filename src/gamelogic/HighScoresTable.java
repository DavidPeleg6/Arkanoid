package gamelogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**.
 * @author David Geda
 * ID: 313237182
 * HighScoresTable class
 * an object for containing the high scores table
 */
public class HighScoresTable implements Serializable {

    private static final long serialVersionUID = 1L;
    private int maxSize;
    private List<ScoreInfo> scores;

    /**.
     * Create an empty high-scores table with the specified size.
     *  The size means that the table holds up to size top scores.
     *  @param size ,
     */
    public HighScoresTable(int size) {
        this.maxSize = size;
        this.scores = new ArrayList<ScoreInfo>();
    }

    /**.
     * Create an empty high-scores table with the specified size.
     *  The size means that the table holds up to size top scores.
     *  @param scoreTable ,
     */
    public HighScoresTable(List<ScoreInfo> scoreTable) {
        this.maxSize = scoreTable.size();
        this.scores = scoreTable;
    }

    /**.
     *  add a new highscore to the table
     *  @param score ,
     */
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        if (rank > this.maxSize) {
            return;
        }
        this.scores.add(rank - 1, score);
        if (this.scores.size() > this.maxSize) {
            this.scores.remove(this.maxSize);
        }
    }

    /**.
     *  return the size of the table
     *  @return int , the size
     */
    public int size() {
        return this.maxSize;
    }

    /**.
     * clears table
     */
    public void clear() {
        scores.clear();
    }

      /** return the rank of the current score: where will it
       * be on the list if added?
       * Rank 1 means the score will be highest on the list.
       * Rank `size` means the score will be lowest.
       * Rank > `size` means the score is too low and will not
       * be added to the list.
       * @param score ,
       * @return int , the rank of score
       */
    public int getRank(int score) {
        if (this.scores.isEmpty()) {
            return 1;
        } else {
            ScoreInfo info;
            int counter = 1;
            Iterator<ScoreInfo> iterator = this.scores.iterator();
            while (iterator.hasNext()) {
                info = iterator.next();
                if (score <= info.getScore()) {
                    counter++;
                } else {
                    return counter;
                }
            }
            return counter;
        }
    }

     /**.
     * return the sorted list.
     * @return List , a sorted scores list
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /** Load table data from file.
     * Current table data is cleared.
     * @param filename , the highScores table
     * @throws IOException , if failed
     */
    @SuppressWarnings("unchecked")
    public void load(File filename) throws IOException {
        this.clear();
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                                    new FileInputStream(filename));

            // permitted necessary down casting
            this.scores = (List<ScoreInfo>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename);
        } catch (ClassNotFoundException e) {
            System.err.println("no such object in file: " + filename);
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /** Save table data to the specified file.
     * @param filename , the file to save in system
     * @throws IOException , if failed for some reason
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                                   new FileOutputStream(filename));
            objectOutputStream.writeObject(this.scores);
        } catch (IOException e) {
            System.err.println("Unable to save file");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                //any other reason for failure
            } catch (IOException e) {
                System.err.println("Unable to save file: " + filename);
            }
        }
    }

    /** Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param filename , the file to read
     * @return File ,
     */
    @SuppressWarnings("unchecked")
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream objectInputStream = null;
        HighScoresTable hs;
        try {
            objectInputStream = new ObjectInputStream(
                        new FileInputStream(filename));
            hs = new HighScoresTable((List<ScoreInfo>) objectInputStream.readObject());
            return hs;

        }  catch (FileNotFoundException e) {
            // Can't find file to open
            return new HighScoresTable(10);
        } catch (ClassNotFoundException e) {
            // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: "
                                                                   + filename);
            return new HighScoresTable(10);
        } catch (IOException e) {
            return new HighScoresTable(10);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

}
