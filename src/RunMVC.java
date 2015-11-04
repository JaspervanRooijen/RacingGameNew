import java.awt.*;

/**
 * Created by Thomas on 17-10-2015.
 */
public class RunMVC {

    private Location location;
    private Race race;
    private Car car;
    private GUI view;
    private Controller controller;
    private Track track;
    //private I2CProtocol protocol;

    public RunMVC() {
        location = new Location(2,3,31,25);
        location.saveAsInitialLocation();
        track = new Track(5,4);
        buildTrack();
        controller = new Controller();
        //I2CProtocol protocol = new I2CProtocol(controller);
        race = new Race(track, controller, 7, 3);//, protocol);
        car = new Car(location, race);
        car.updateOrientation(90);
        view = new GUI(controller);
        controller.addCar(car);
        controller.addRace(race);
        controller.addView(view);
        controller.start();
        race.addObserver(view);
    }

    public static void main(String[] args){
        new RunMVC();
    }

    public void buildTrack() {



        TrackSegmentBuilderArray tsb = new TrackSegmentBuilderArray();

        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 0);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 1);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 2);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 3);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 4);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 5);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 9);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 10);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 14);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 15);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 16);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 17);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 18);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 19);

        track.addTrackSegment(new TrackSegmentArray(tsb.generateRD()), 6);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateSFLR()), 7);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateLD()), 8);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateLR()), 12);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateRU()), 11);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateLU()), 13);

/*

        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 0);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 1);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 2);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 3);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 5);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 6);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 7);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 8);

        track.addTrackSegment(new TrackSegmentArray(tsb.getLU()), 4);
*/

        /*
        TrackSegmentBuilderArray tsb = new TrackSegmentBuilderArray();
        track.addTrackSegment(new TrackSegmentArray(tsb.getLU()), 0);
        */
    }

}
