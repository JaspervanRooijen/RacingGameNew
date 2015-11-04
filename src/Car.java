import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

/**
 * Created by Jasper on 13-10-2015.
 */
public class Car extends Observable {
    private BufferedImage originalImage;
    private Location location;
    private int speed;
    private double orientation;
    private Race race;

    public Car(Location location, Race race) {
        this.location = location;
        this.race = race;
        speed = 0;
        orientation = 0;
    }
    public void updateSpeed(int speedChange) {
        //System.out.println(speed);
        speed += speedChange;
        if (speed < -3) {
            speed = -3;
        }
        if (speed > 10) {
            speed = 10;
        }
        System.out.println("Speed = "+speed);
    }

    public void updateOrientation(int orientationChange) {
        orientation = orientationChange;
    }
    public void updateKeyOrientation(int orientationChange) {
        orientation += orientationChange;
        if (orientation < 0) {
            orientation += 360;
        }
        orientation %= 360;
    }
    public void updateLocation() {
        int trackHeight = location.getTrackHeight();
        int trackWidth = location.getTrackWidth();
        int segmentHeight = location.getSegmentHeight();
        int segmentWidth = location.getSegmentWidth();
        //System.out.println("Original segmentHeight: " + segmentHeight);
        //System.out.println("Original segmentWidth: " + segmentWidth);


        segmentHeight = (int)(Math.cos(Math.toRadians(orientation)) * speed) + segmentHeight;

        segmentWidth = (int)(Math.sin(Math.toRadians(orientation))*speed) + segmentWidth;
        //System.out.println("original " + segmentHeight + " + " + segmentWidth);

        if (segmentHeight > 63) {
            trackHeight--;
            segmentHeight %= 64;
            location.setChangedSegmentAfterFinish(true);
        }
        if (segmentHeight < 0) {
            trackHeight++;
            segmentHeight += 64;
            location.setChangedSegmentAfterFinish(true);

        }
        if (segmentWidth > 63) {
            trackWidth++;
            segmentWidth %= 64;
            location.setChangedSegmentAfterFinish(true);

        }
        if (segmentWidth < 0 ) {
            trackWidth--;
            segmentWidth += 64;
            location.setChangedSegmentAfterFinish(true);

        }

        // System.out.println("result " + segmentHeight + " + " + segmentWidth);




        //System.out.println("SegmentHeight after calculation: " + segmentHeight);
        //System.out.println("SegmentWidth after calculation: " + segmentWidth);

        //System.out.println(segmentHeight + " + " + segmentWidth);
        location.updateLocation(trackHeight, trackWidth, segmentHeight, segmentWidth);
    }

    public double getOrientation() {
        if(orientation < 0) {
            orientation += 360;
        }
        return orientation;
    }

    public Location getLocation() {
        return location;
    }
    public int getSpeed() {
        return speed;
    }

    public BufferedImage loadImageCar(String s) throws IOException {
        originalImage = ImageIO.read(getClass().getResource(s));
        return originalImage;
    }
    public boolean isCrashed() {
        int currentTrackHeight = location.getTrackHeight();
        int currentTrackWidth = location.getTrackWidth();
        int currentSegHeight = location.getSegmentHeight();
        int currentSegWidth = location.getSegmentWidth();
        //System.out.println("TrackHeight = "+currentTrackHeight);
        //System.out.println("TrackWidth = "+currentTrackWidth);
        //System.out.println("SegHeight = "+currentSegHeight);
        //System.out.println("SegWidth = "+currentSegWidth);


        int trackIndex = ((currentTrackWidth-1)+(((currentTrackHeight-1)*(race.getTrack().getWidth()))));
        //System.out.println("Trackindex = "+trackIndex);
        boolean result = ((race.getTrack().getTrackSegment(trackIndex).getBit(currentSegHeight, currentSegWidth)) == 0);
        //System.out.println(result);
        return result;
    }
    public boolean onFinish() {
        int currentTrackHeight = location.getTrackHeight();
        int currentTrackWidth = location.getTrackWidth();
        int currentSegHeight = location.getSegmentHeight();
        int currentSegWidth = location.getSegmentWidth();

        int trackIndex = ((currentTrackWidth-1)+(((currentTrackHeight-1)*(race.getTrack().getWidth()))));
        //System.out.println("Trackindex = "+trackIndex);
        boolean result = ((race.getTrack().getTrackSegment(trackIndex).getBit(currentSegHeight, currentSegWidth)) == 2);
        boolean segChange = location.isChangedSegmentAfterFinish();
        //System.out.println(result);
        //if (result) {
        //System.out.println("on finish xD !!!");
        //}
        //if (segChange) {
        //    System.out.println("segment is new");
        // }
        if (result&&segChange) {
            System.out.println("So it counts");
            location.setChangedSegmentAfterFinish(false);
        }
        return (result&&segChange);
    }

/*
    public static void main(String[] args) {
        Location location = new Location(4,0,60,60);
        Race race = new Race();
        Car car = new Car(location, race);

        car.updateOrientation(45);
        car.updateSpeed(10);

        car.updateLocation();


        System.out.println("TrackHeight = " + location.getTrackHeight());
        System.out.println("TrackWidth = " + location.getTrackWidth());
        System.out.println("SegmentHeight = " + location.getSegmentHeight());
        System.out.println("SegmentWidth = " + location.getSegmentWidth());

    }
*/
}
