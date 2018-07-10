package menu;

/**.
 * @author David Geda
 * ID: 313237182
 * Triplets class
 * @param <T>
 */
public class Triplets<T> {

    private String key, name;
    private T func;

   /**.
   * constructor
    * @param key1 ,
    * @param name1 ,
    * @param func1 ,
    */
    public Triplets(String key1, String name1, T func1) {
        this.name = name1;
        this.key = key1;
        this.func = func1;
    }

   /**.
    * @return String ,
    */
    public String getName() {
        return this.name;
    }

   /**.
    * @return String ,
    */
    public String getKey() {
        return this.key;
    }

   /**.
    * @return T ,
    */
    public T getTask() {
        return this.func;
    }
}
