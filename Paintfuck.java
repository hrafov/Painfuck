// https://www.codewars.com/kata/5868a68ba44cfc763e00008d/train/java
// https://esolangs.org/wiki/Paintfuck
public class Paintfuck {
    public static String interpreter(String code, int iterations, int width, int height) {
//        System.out.println("--- code: " + code); // check input
//        System.out.println("--- iterations = " + iterations + " width = " + width + " height = " + height + "\n");
        PointXY currentPoint = new PointXY('U', 0, 0);
        int[][] data = new int[height][width];
        if (iterations == 0) return prepareDataForReturn(data, width, height); // prepare Data For Return this is if (iterations == 0)
        int count; // for nested loops
        code = removeAllNonCommandCharacters(code);
        int someSum = 0; // for summing iterations
              for (int i = 0; i < code.length() && someSum <= iterations; i++) {
                someSum++;
                switch (code.charAt(i)) {
                    case ('n'): // n - Move data pointer north (up) - like '^'
                        if (currentPoint.getY() == 0) currentPoint = new PointXY('a', currentPoint.getX(),height - 1);
                        else currentPoint = new PointXY('b', currentPoint.getX(), currentPoint.getY() - 1);
                        break;
                    case ('e'): // e - Move data pointer east (right) - like '>'
                        if (currentPoint.getX() == width - 1) currentPoint = new PointXY('c', 0, currentPoint.getY());
                        else currentPoint = new PointXY('d', currentPoint.getX() + 1, currentPoint.getY());
                        break;
                    case ('s'): // s - Move data pointer south (down)
                        if (currentPoint.getY() == height - 1) currentPoint = new PointXY('e', currentPoint.getX(),0);
                        else currentPoint = new PointXY('f', currentPoint.getX(), currentPoint.getY() + 1);
                        break;
                    case ('w'): // w - Move data pointer west (left)
                        if (currentPoint.getX() == 0) currentPoint = new PointXY('g', width - 1, currentPoint.getY());
                        else currentPoint = new PointXY('h', currentPoint.getX() - 1, currentPoint.getY());
                        break;
                    case ('*'): // * - Flip the bit at the current cell
                        data[currentPoint.getY()][currentPoint.getX()] = - data[currentPoint.getY()][currentPoint.getX()] + 1;
                        break;
                    // Regarding iterations, the act of skipping to the matching ] when a [ is encountered (or vice versa) is considered to be one iteration
                    // regardless of the number of command characters in between. The next iteration then commences at the command right after the matching ] (or [).
                    case ('['): // [ - Jump past matching ] if bit under current pointer is 0 (same as in Smallfuck)
                    if (data[currentPoint.getY()][currentPoint.getX()] == 0) {
                        count = 1;
                        do {
                            i++;
                            if (code.charAt(i) == '[') count++;
                            if (code.charAt(i) == ']') count--;
                        } while (count != 0);
                    }
                        break;
                    case (']'): //] - Jump back to the matching [ (if bit under current pointer is nonzero) (same as in Smallfuck)
                    if (data[currentPoint.getY()][currentPoint.getX()] != 0) {
                        count = 1;
                        do {
                            i--;
                            if (code.charAt(i) == ']') count++;
                            if (code.charAt(i) == '[') count--;
                        } while (count != 0);
                    }
                    break;
                    default:
                        break;
                }
            }
        //printData(data, width, height);
        return prepareDataForReturn(data, width, height);
    }
    private static String removeAllNonCommandCharacters(String code) {
        StringBuilder without = new StringBuilder();
        for (int i = 0; i < code.length(); i++)
            if (code.charAt(i) == 'n' || code.charAt(i) == 'e' || code.charAt(i) == 's' || code.charAt(i) == 'w' ||
                    code.charAt(i) == '[' || code.charAt(i) == ']' || code.charAt(i) == '*') without.append(code.charAt(i));
        //System.out.println("=== after removing n e s w * [ ]: " + without);
        return without.toString();
    }
    private static class PointXY {
        private char c;
        private final int x;
        private final int y;
        public PointXY(char c, int x, int y) {
            this.c = c;
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return "char " + c + " (" + x + ", " + y + ")";
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public char getC() {
            return c;
        }
        public void setC(char c) {
            this.c = c;
        }
    }
    private static void printData(int[][] data, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(data[i][j]);
            System.out.println();
        }
    }
    private static String prepareDataForReturn(int[][] data, int width, int height) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                sb.append(data[i][j]);
            if (i < height - 1) sb.append("\r\n");
        }
        return sb.toString();
    }
}
