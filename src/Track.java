import java.util.ArrayList;

/**
 * Created by Jasper on 13-10-2015.
 */
public class Track {
    private int width;
    private int length;
    private TrackSegmentArray[] track;

    public Track(int width, int length) {
        this.width = width;
        this.length = length;
        track = new TrackSegmentArray[width*length];
    }

    public void addTrackSegment(TrackSegmentArray segment, int index) {
        track[index] = segment;
    }
    public void addTrackSegment(TrackSegmentArray segment, int x, int y) {
        track[x + width*y] = segment;
    }
    public TrackSegmentArray getTrackSegment(int index) {
        return track[index];
    }

    public String toString() {
        String result = "";
        for (TrackSegmentArray seg : track) {
            result += seg.toString();
        }
        return result;
    }
    public int getWidth() {
        return width;
    }
    public int getLength() {
        return length;
    }

    public static void main(String[] args) {
        Track track = new Track(2,2);
        TrackSegmentBuilderArray tsb = new TrackSegmentBuilderArray();
        TrackSegmentArray zero = new TrackSegmentArray(tsb.getRD());
        TrackSegmentArray one = new TrackSegmentArray(tsb.getLD());
        TrackSegmentArray two = new TrackSegmentArray(tsb.getRU());
        TrackSegmentArray three = new TrackSegmentArray(tsb.getLU());

        track.addTrackSegment(zero, 0);
        track.addTrackSegment(one, 1, 0);
        track.addTrackSegment(two, 0, 1);
        track.addTrackSegment(three, 3);

        System.out.println(track.toString());
    }


}
