/**
 * Created by Jasper on 3-11-2015.
 */
/**
 * Created by Jasper on 13-10-2015.
 */
public class Location {
    private Location initialLocation;
    private int trackHeight;
    private int trackWidth;
    private double segmentHeight;
    private double segmentWidth;
    private boolean changedSegmentAfterFinish = true;

    public Location(int trackHeight, int trackWidth, int segmentHeight, int segmentWidth) {
        this.trackHeight = trackHeight;
        this.trackWidth = trackWidth;
        this.segmentHeight = (double)segmentHeight;
        this.segmentWidth = (double)segmentWidth;
    }

    public void updateLocation(int trackHeight, int trackWidth, double segmentHeight, double segmentWidth) {
        this.trackHeight = trackHeight;
        this.trackWidth = trackWidth;
        this.segmentHeight = segmentHeight;
        this.segmentWidth = segmentWidth;
    }
    public void updateLocation(int segmentHeight, int segmentWidth) {
        this.segmentHeight = segmentHeight;
        this.segmentWidth = segmentWidth;
    }
    public void saveAsInitialLocation() {
        initialLocation = new Location(trackHeight, trackWidth, (int)segmentHeight, (int)segmentWidth);
    }
    public void reset() {
        trackHeight = initialLocation.getTrackHeight();
        trackWidth = initialLocation.getTrackWidth();
        segmentHeight = initialLocation.getSegmentHeight();
        segmentWidth = initialLocation.getSegmentWidth();
    }
    public boolean isChangedSegmentAfterFinish() {
        return changedSegmentAfterFinish;
    }
    public void setChangedSegmentAfterFinish(boolean b) {
        changedSegmentAfterFinish = b;
    }


    public int getTrackHeight() {
        return trackHeight;
    }
    public int getTrackWidth() {
        return trackWidth;
    }
    public double getSegmentHeight() {
        return segmentHeight;
    }
    public double getSegmentWidth() {
        return segmentWidth;
    }
}
