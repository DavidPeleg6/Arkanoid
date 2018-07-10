package animations;
/**.
 * @author David Geda
 * ID: 313237182
 * Counter class
 */
public class Counter {

    private int counter;

    /**.
   * constructor method of the class
   * @param innitial , the number to start from
   */
    public Counter(int innitial) {
        this.counter = innitial;
    }

    /**.
   * add a number to the current count
   * @param number , a number to add
   */
    public void increase(int number) {
        this.counter += number;
    }

    /**.
   * subtract a number to the current count
   * @param number , a number to subtract
   */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**.
   * a getter for the current value
   * @return int , the value of the counter
   */
    public int getValue() {
        return this.counter;
    }
}
