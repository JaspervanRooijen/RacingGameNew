import java.util.Arrays;

/**
 * Created by Jasper on 26-10-2015.
 */
public class TrackSegmentBuilderArray {
    private int[] one = generateOneArray();
    private int[] zero = generateZeroArray();
    private int[] verticalStandard = generateVerticalStandard();
    private int[][] LR = generateLR();
    private int[][] UD = generateUD();
    private int[][] LU = generateLU();
    private int[][] RU = generateRU();
    private int[][] LD = generateLD();
    private int[][] RD = generateRD();
    private int[][] forbidden = generateForbidden();
    private int[][] SFLR = generateSFLR();




    public int[][] generateLR() {                                                                   // works, tested visually
        int[][] result = new int[64][64];
        for (int i = 0; i < result.length / 3; i++) {
            result[i] = generateZeroArray();
        }
        for (int i = result.length / 3; i < (result.length / 3 * 2) + 1; i++) {
            result[i] = generateOneArray();
        }
        for (int i = result.length / 3 * 2 + 1; i < result.length; i++) {
            result[i] = generateZeroArray();
        }
        return result;
    }

    public int[][] generateUD() {                                                                  // works, tested visually
        int[][] result = new int[64][64];
        for (int i = 0; i < result.length; i++) {
            result[i] = verticalStandard;
        }
        return result;
    }

    public int[][] generateLD() {                                                                 // seems to work, tested visually
        int[][] result = new int[64][64];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (((i*i) + (j*j) < (44*44)) && ((i*i) + (j*j) > (20*20))) {                   //ToDo: potentially fix hardcoded constants
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }
    public int[][] generateRD() {                                                               // seems to work, tested visually
        int[][] result = generateLD();
        result = mirrorOverY(result);
        return result;
    }
    public int[][] generateLU() {
        int[][] result = generateLD();
        result = mirrorOverX(result);
        return result;
    }
    public int[][] generateRU() {
        int[][] result = generateLD();
        result = mirrorOverY(result);
        result = mirrorOverX(result);
        return result;
    }
    public int[][] generateForbidden() {
        int[][] result = new int[64][64];
        for (int i = 0; i < result.length; i++) {
            result[i] = zero;
        }
        return result;
    }

    public int[][] generateSFLR() {
        int[][] result = generateLR();
        for (int i = 21; i < 43; i++) {
            for (int j = 30; j < 35; j++) {
                result[i][j] = 2;
            }
        }
        return result;
    }







    public int[] generateZeroArray() {
        int[] zero = new int[64];
        for (int i = 0; i < zero.length; i++) {
            zero[i] = 0;
        }
        return zero;
    }
    public int[] generateOneArray() {
        int[] one = new int[64];
        for (int i = 0; i < one.length; i++) {
            one[i] = 1;
        }
        return one;
    }
    public int[] generateVerticalStandard() {
        int[] standard = new int[64];
        for (int i = 0; i < standard.length / 3; i++) {
            standard[i] = 0;
        }
        for (int i = standard.length / 3; i < standard.length / 3 * 2 + 1; i++) {
            standard[i] = 1;
        }
        for (int i = standard.length / 3 * 2 + 1; i < standard.length; i++) {
            standard[i] = 0;
        }
        //System.out.println(Arrays.toString(standard));
        return standard;
    }

    public int[][] mirrorOverY(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length / 2; j++) {
                int parti = array[i][j];
                array[i][j] = array[i][array[i].length - 1 - j];
                array[i][array[i].length - 1 - j] = parti;
            }
        }
        return array;
    }
    public int[][] mirrorOverX(int[][] array) {
        for (int i = 0; i < array.length / 2; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int parti = array[i][j];
                array[i][j] = array[array.length - 1 - i][j];
                array[array.length - 1 - i][j] = parti;
            }
        }
        return array;
    }

    public int[][] combineSegments(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {                                           //ToDo: is this really necessary? Code becomes messy!
            System.err.println("Two segments of incompatible size were tried to combine!");
            System.exit(-1);
            return null;
        }
        else {
            int[][] result = new int[a.length][a[0].length];
            for (int i = 0; i < a.length; i++) {
                for(int j = 0; j < a[i].length; j++) {
                    if (a[i][j] == 1 || b[i][j] == 1) {
                        result[i][j] = 1;
                    } else {
                        result[i][j] = 0;
                    }
                }
            }
            return result;
        }
    }
    public int square(int number) {                                                                         // Can be used to fix hardcoded values in generateLU()
        return number*number;
    }
    public String doubleArraytoString(int[][] doubleArray) {                                                // For testing purposes only i guess...
        String result = "";
        for (int i = 0; i < doubleArray.length; i++) {
            result += Arrays.toString(doubleArray[i]);
            result += "\n";
        }
        return result;
    }

    public int[][] getLR() {
        return LR;
    }
    public int[][] getUD() {
        return UD;
    }
    public int[][] getLU() {
        return LU;
    }
    public int[][] getRU() {
        return RU;
    }
    public int[][] getLD() {
        return LD;
    }
    public int[][] getRD() {
        return RD;
    }
    public int[][] getForbidden() {
        return forbidden;
    }
    public int[][] getSFLR() {
        return SFLR;
    }


    public static void main(String[] args) {
        TrackSegmentBuilderArray tsb = new TrackSegmentBuilderArray();
        /*
        System.out.println("UD: \n" + tsb.doubleArraytoString(tsb.getUD()));
        */
        System.out.println("LR: \n" + tsb.doubleArraytoString(tsb.generateSFLR()));
        /*
        System.out.println("LU: \n" + tsb.doubleArraytoString(tsb.getLU()));
        System.out.println("LD: \n" + tsb.doubleArraytoString(tsb.getLD()));
        System.out.println("RD: \n" + tsb.doubleArraytoString(tsb.getRD()));
        System.out.println("RU: \n" + tsb.doubleArraytoString(tsb.getRU()));
        */
        //System.out.println(tsb.doubleArraytoString(tsb.getForbidden()));
    }
}
