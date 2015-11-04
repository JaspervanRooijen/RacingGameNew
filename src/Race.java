import java.util.Observable;

/**
 * Created by Jasper on 13-10-2015.
 */
public class Race extends Observable implements Runnable {
    private boolean running = false;
    private long timeBegin;
    private Thread thread;
    private boolean isPaused;
    private Track track;
    private Controller controller;
    private int tracksToGo;
    //private I2CProtocol protocol;

    public Race(Track track, Controller controller, int finishSegment, int amountOfTracks) {// I2CProtocol protocol) {
        thread = new Thread(this);
        thread.start();
        this.track = track;
        this.controller = controller;
        tracksToGo = amountOfTracks;
        //this.protocol = protocol;
        //protocol.start();
    }

    public synchronized void startGame() {

        if(!running) {
            isPaused = false;
            running = true;
            this.run();
            //System.out.println("Still going");
        }
    }

    public void pause() {
        while(true) {
            if (!isPaused) {
                try {
                    thread.currentThread().sleep(300);
                    //System.out.println("sleeping");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }

    public void setPaused() {
        this.isPaused = !isPaused;
    }

    public synchronized void stop() {
        if(running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(1);
        }
    }
    public int decrementLabsToGo() {
        tracksToGo--;
        return tracksToGo;
    }

    public void setPauseTime(long l) {
        timeBegin += l;
    }

    @Override
    public void run() {
        while (running) {
            // protocol.updateCarOrientation(protocol.getByte1(), protocol.getByte2());
            //protocol.updateCarGas(protocol.getByte2());
            setChanged();
            notifyObservers();
        }
        stop();
    }

    public Track getTrack() {
        return track;
    }
    public long getTimeBegin() {
        return timeBegin;
    }
    public void setTimeBegin() {
        timeBegin = System.currentTimeMillis();
    }
    public void setTracksToGo(int i) {
        tracksToGo = i;
    }

}
