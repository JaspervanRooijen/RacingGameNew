
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created by Jasper on 13-10-2015.
 */
public class Controller extends Thread implements KeyListener, MouseListener {
    private GUI view;
    private Car car;
    private Race race;
    private Thread thread;
    private boolean keyboard = true;
    private long pauseTime;

    public void run() {
        view.standardGUI();
        try {
            view.setSprite(ImageIO.read(getClass().getResource("images/car_black.png")));
            view.setSprite(car.loadImageCar("images/rsz_1car_black.png"));
            view.setSprite(car.loadImageCar("images/rsz_2car_black.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        view.setState(GameState.MAINMENU);
        view.addMouseController(this);
        race.startGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(keyboard) {
            switch (c) {
                case KeyEvent.VK_UP:
                    car.updateSpeed(1);
                    break;
                case KeyEvent.VK_DOWN:
                    car.updateSpeed(-1);
                    break;
                case KeyEvent.VK_LEFT:
                    car.updateKeyOrientation(-2);
                    break;
                case KeyEvent.VK_RIGHT:
                    car.updateKeyOrientation(2);
                    break;
                case KeyEvent.VK_P:
                    pauseTime = System.currentTimeMillis();
                    race.setPaused();
                    view.removeKeyController(this);
                    view.addMouseController(this);
                    view.setState(GameState.PAUSEMENU);
                    race.pause();
                    break;
            }
        } else {
            switch (c) {
                case KeyEvent.VK_P:
                    pauseTime = System.currentTimeMillis();
                    race.setPaused();
                    view.removeKeyController(this);
                    view.addMouseController(this);
                    view.setState(GameState.PAUSEMENU);
                    race.pause();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void addCar(Car c){
        this.car = c;
    }

    public void addRace(Race r){
        this.race = r;
    }


    public void addView(GUI g){
        this.view = g;
    }


    public int getCarSurrounding(int i, int j) {
        int trackWidth = race.getTrack().getWidth();
        int currentSegHeight = car.getLocation().getSegmentHeight();
        int currentSegWidth = car.getLocation().getSegmentWidth();
        int currentTrackWidth = car.getLocation().getTrackWidth();
        int currentTrackHeight = car.getLocation().getTrackHeight();

        //System.out.println("Given is:");
        //System.out.println("Trackwidth = " + currentTrackWidth);
        //System.out.println("Trackheight = " + currentTrackHeight);
        //System.out.println("i = " + i);
        //System.out.println("j = " + j);
        //System.out.println("currentsegheight = " + currentSegHeight);
        //System.out.println("currentsegwidth = " + currentSegWidth);

        int askedY = currentSegHeight+32-i;
        int askedX = currentSegWidth-32+j;

        if (askedY > 63) {
            askedY = askedY - 64;
            currentTrackHeight--;
        }
        if (askedY < 0) {
            askedY = 64 + askedY;
            currentTrackHeight++;
        }
        if (askedX > 63) {
            askedX = askedX - 64;
            currentTrackWidth++;
        }
        if (askedX < 0) {
            askedX = 64 + askedX;
            currentTrackWidth--;
        }
        //System.out.println("Asked is:");
        //System.out.println("Trackwidth = " + currentTrackWidth);
        //System.out.println("Trackheight = " + currentTrackHeight);
        //System.out.println("Segmentwidth = " + askedX);
        //System.out.println("Segmentheight = " + askedY);
        int trackIndex = ((currentTrackWidth-1)+(currentTrackHeight-1)*trackWidth);
        //System.out.println(trackIndex);
        return race.getTrack().getTrackSegment(trackIndex).getBit(askedY, askedX);
    }

/*
    public int getCarSurrounding(int x, int y) {

        int trackWidth = race.getTrack().getWidth();
        int currentTrackWidth = car.getLocation().getTrackWidth();
        //System.out.println("CurrentTrackWidth to start with = " + currentTrackWidth);
        int currentTrackHeight = car.getLocation().getTrackHeight();
        //System.out.println("CurrentTrackHeight to start with = " + currentTrackHeight);
        int currentSegmentWidth = car.getLocation().getSegmentWidth();
        int currentSegmentHeight = car.getLocation().getSegmentHeight();

        currentSegmentWidth = currentSegmentWidth-32+x;
        currentSegmentHeight = currentSegmentHeight+32-y;

        if (currentSegmentWidth > 63) {
            currentSegmentWidth %= 64;
            //System.out.println(x);
            //System.out.println(y);
            currentTrackWidth++;
        }
        if (currentSegmentWidth < 0) {
            currentSegmentWidth = 64+currentSegmentWidth;
            currentTrackWidth--;
        }
        if (currentSegmentHeight > 63) {
            //System.out.println("currentSegmentHeight > 63!");
            //System.out.println("OriginalTrackHeight = " + currentTrackHeight);
            //System.out.println("OriginalTrackWidth = " + currentTrackWidth);
            currentSegmentHeight %= 64;
            currentTrackHeight++;
        }
        if (currentSegmentHeight < 0) {
            //System.out.println("currentSegmentHeight < 0!");
            //System.out.println("OriginalTrackHeight = " + currentTrackHeight);
            //System.out.println("OriginalTrackWidth = " + currentTrackWidth);
            currentSegmentHeight = 64+currentSegmentHeight;
            currentTrackHeight--;
        }
        //System.out.println("FinalTrackHeight = "+currentTrackHeight);
        //System.out.println("FinalTrackWidth = "+currentTrackWidth);
        int finalresult = ((currentTrackWidth-1)+(trackWidth * (currentTrackHeight-1)));
        //System.out.println("finalresult = " + finalresult);
        return race.getTrack().getTrackSegment(finalresult).getBit(currentSegmentWidth, currentSegmentHeight);

    }
*/

    public int[][] getCarSurroundings() {
        int[][] result = new int[64][64];
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                //System.out.println("i = "+i + ", j = " +j);
                result[i][j] = getCarSurrounding(i,j);
            }
        }
        return result;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 150 && y <= 250) && view.getState().equals(GameState.MAINMENU)) {
            view.removeMouseController(this);
            view.addKeyController(this);
            reset();
            race.setTimeBegin();
            view.setState(GameState.GAME);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 150 && y <= 250) && view.getState().equals(GameState.OPTIONS)) {
            keyboard = true;
            view.setState(GameState.MAINMENU);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 150 && y <= 250) && view.getState().equals(GameState.PAUSEMENU)) {
            view.removeMouseController(this);
            view.addKeyController(this);
            race.setPauseTime(System.currentTimeMillis() - pauseTime);
            view.setState(GameState.GAME);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 300 && y <= 400) && view.getState().equals(GameState.PAUSEMENU)) {
            view.removeMouseController(this);
            car.getLocation().reset();
            car.updateSpeed(-(car.getSpeed()));
            car.updateOrientation((int) -(car.getOrientation()));
            view.addKeyController(this);
            race.setTimeBegin();
            view.setState(GameState.GAME);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 300 && y <= 400) && view.getState().equals(GameState.MAINMENU)) {
            view.setState(GameState.OPTIONS);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 300 && y <= 400) && view.getState().equals(GameState.OPTIONS)) {
            keyboard = false;
            view.setState(GameState.MAINMENU);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 450 && y <= 550) && view.getState().equals(GameState.MAINMENU)) {
            System.exit(1);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 450 && y <= 550) && view.getState().equals(GameState.PAUSEMENU)) {
            System.exit(1);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 450 && y <= 550) && view.getState().equals(GameState.OPTIONS)) {
            view.setState(GameState.MAINMENU);
        } else if ((x >= view.WIDTH / 2 - 100 && x <= view.WIDTH / 2 + 100) && (y >= 450 && y <= 550) && view.getState().equals(GameState.VICTORY)) {
            //view.removeMouseController(this);
            //view.addKeyController(this);
            // race.setTimePause(pauseTime);
            view.setState(GameState.MAINMENU);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void reset() {
        car.getLocation().reset();
        car.updateSpeed(-(car.getSpeed()));
        //I2CProtocol protocol = new I2CProtocol(controller);
        car.updateOrientation(90);
        race.setTracksToGo(1);
        car.getLocation().setChangedSegmentAfterFinish(true);
    }


    public Car getCar() {
        return car;
    }
    public Race getRace() {
        return race;
    }
}
