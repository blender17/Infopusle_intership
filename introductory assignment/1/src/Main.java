import java.util.Arrays;
import java.util.Random;
import static java.lang.Math.*;

public class Main {


    private final static int arrSize = 4;
    private final static double c = -1.49;
    private final static double d = 23.4;

    public static void main(String[] args) {
    
        Random random = new Random(System.currentTimeMillis());

        double y;
        int maxElem;

        int[][] array = Arrays.stream(new int[arrSize][arrSize])
                        .map(row -> Arrays.stream(row)
                        .map(col -> col = random.nextInt(100) + 1)
                        .toArray()).toArray(int[][]::new);


        maxElem = maxArrayValue(array);

        y = sqrt(abs(sin(sin(c) - ((4 * log(log(d))) / maxElem))));

        System.out.println("Result - " + y);

    }

    public static int maxArrayValue(int[][] array) {
        int maxElem = array[0][0];

        for(int i = 0; i < arrSize; i++) {
            for(int j = 0; j < arrSize; j++) {
                if (array[i][j] > maxElem) {
                    maxElem = array[i][j];
                }
            }
        }
        return maxElem;
    }

}
