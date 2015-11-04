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
        track = new Track(8,6);
        buildTrack();
        controller = new Controller();
        //I2CProtocol protocol = new I2CProtocol(controller);
        race = new Race(track, controller, 7, 2);//, protocol);
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
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 6);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 7);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 8);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 15);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 16);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 18);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 19);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 23);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 24);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 29);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 31);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 32);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 33);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 34);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 35);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 39);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 40);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 41);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 42);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 43);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 44);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 45);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 46);
        track.addTrackSegment(new TrackSegmentArray(tsb.getForbidden()), 47);

        track.addTrackSegment(new TrackSegmentArray(tsb.generateRD()), 9);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateSFLR()),10);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateLR()), 11);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateLD()), 12);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateRD()), 13);
        track.addTrackSegment(new TrackSegmentArray(tsb.generateLD()), 14);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateUD())), 17);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateRU())), 20);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateLU())), 21);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateUD())), 22);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateRU())), 25);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateLR())), 26);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateLR())), 27);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateLD())), 28);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateUD())), 30);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateRU())), 36);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateLR())), 37);
        track.addTrackSegment(new TrackSegmentArray((tsb.generateLU())), 38);


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
