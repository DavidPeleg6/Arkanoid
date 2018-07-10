package levelbuilder;

import java.awt.Color;

/**.
 * @author David Geda
 * ID: 313237182
 * ColorsParser class
 */
public class ColorsParser {

  /**.
     * @param s ,
     * @return Color ,
     * @throws NumberFormatException ,
     */
    public static Color colorFromString(String s)
            throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException();
        }
        if (s.equalsIgnoreCase("black")) {
            return Color.BLACK;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.BLUE;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.CYAN;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.GRAY;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.LIGHT_GRAY;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.GREEN;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.ORANGE;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.PINK;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.RED;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.WHITE;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.YELLOW;
        }
        int index = s.indexOf('(');
        int endIndex = s.lastIndexOf(')');
        if (index == -1 || endIndex == -1) {
            //failed
            return null;
        }
        String rgb = s.substring(0, index);
        if (rgb.equalsIgnoreCase("RGB")) {
            String values = s.substring(index + 1, endIndex);
            String[] split2 = values.split(",");
            int x = Integer.parseInt(split2[0]);
            int y = Integer.parseInt(split2[1]);
            int z = Integer.parseInt(split2[2]);
            return new Color(x, y, z);
        }
        throw new NumberFormatException();
    }
}
