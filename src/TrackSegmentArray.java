/**
 * Created by Jasper on 26-10-2015.
 */
public class TrackSegmentArray {
    int[][] trackSegment = new int[64][64];

    public TrackSegmentArray(int[][] trackSegment) {
        this.trackSegment = trackSegment;
    }
    public TrackSegmentArray() {
    }

    public void adjustSegment(int[][] newSegment) {
        trackSegment = newSegment;
    }
    public String toString() {
        String result = "";
        for (int i = 0; i < trackSegment.length; i++) {
            //System.out.println("i < " + trackSegment.length);
            for (int j = 0; j < trackSegment[i].length; j++) {
                //System.out.println("j < " + trackSegment[i].length);
                //System.out.println("j = " + j);
                result += trackSegment[i][j];
            }
            result += "\n";
        }
        return result;
    }
    public int[][] clone() {
        return trackSegment;
    }
    public int getBit(int i, int j) {
        return trackSegment[i][j];
    }



    public static void main(String[] args) {
        TrackSegmentArray segment = new TrackSegmentArray();
        TrackSegmentBuilderArray tsb = new TrackSegmentBuilderArray();
        //segment.adjustSegment(tsb.getRU());
        System.out.println(segment.toString());
    }
}
