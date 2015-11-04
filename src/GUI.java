import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jasper on 13-10-2015.
 */
public class GUI extends Canvas implements Observer {

    private Controller controller;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public final String NAME = "Racing Game";
    private JFrame frame;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteCar;
    private BufferedImage background;
    private GameState state;
    private long time;
    private String finaltime;
    public Rectangle startB = new Rectangle(WIDTH/2 -100, 150, 200, 100);
    public Rectangle optionsB = new Rectangle(WIDTH/2 -100, 300, 200, 100);
    public Rectangle quitB = new Rectangle(WIDTH/2 -100, 450, 200, 100);

    public GUI(Controller controller) {
        this.controller = controller;
    }


    public void MenuGUI(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("TOP-DOWN RACINGGAME", WIDTH/4, 100);
        Font fnt1 = new Font("arial", Font.BOLD, 40);
        g.setFont(fnt1);
        g.drawString("START", startB.x + 35, startB.y + 65);
        g.drawString("OPTIONS", optionsB.x + 11, optionsB.y + 65);
        g.drawString("QUIT", quitB.x + 53, quitB.y + 65);
        g2d.draw(startB);
        g2d.draw(optionsB);
        g2d.draw(quitB);
    }

    public void standardGUI() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame(this.NAME);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void renderGame() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        if (state == GameState.GAME) {
            //graphics.setColor(Color.blue);
            //graphics.fillRect(0, 0, 1400, 1400);


            int[][] toDraw = controller.getCarSurroundings();
            for (int i = 0; i < toDraw.length; i++) {
                for (int j = 0; j < toDraw[i].length; j++) {
                    if (toDraw[i][j] == 0) {
                        graphics.setColor(Color.black);
                    } else if (toDraw[i][j] == 1) {
                        graphics.setColor(Color.white);
                    } else {
                        graphics.setColor(Color.orange);
                    }
                    graphics.fillRect(WIDTH/64*j, HEIGHT/64*i, WIDTH/64, HEIGHT/64);
                }
            }
            drawRotated(spriteCar, controller.getCar().getOrientation(), WIDTH/2, HEIGHT/2, graphics);
            graphics.setColor(Color.cyan);
            graphics.fillRect(WIDTH/2, HEIGHT/2, 10, 10);
            graphics.setColor(Color.cyan);
            Font font = new Font("arial", Font.PLAIN, 20);
            graphics.setFont(font);
            graphics.drawString(String.format("%.2f", (double) time/1000), 20, 30);
        } else if (state == GameState.MAINMENU) {
            MenuGUI(graphics);
        } else if (state == GameState.PAUSEMENU) {
            PauseGameGUI(graphics);
        } else if (state == GameState.VICTORY) {
            victoryGUI(graphics);
        } else if (state == GameState.OPTIONS) {
            OptionMenu(graphics);
        }
        graphics.dispose();
        bs.show();
        try {
            Thread.sleep(500);
            //System.out.println("slept");
        } catch (InterruptedException e) {
            //do nothing
        }

    }

    public void OptionMenu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("TOP-DOWN RACINGGAME", WIDTH/4, 100);
        Font fnt1 = new Font("arial", Font.BOLD, 40);
        g.setFont(fnt1);
        g.drawString("Keyboard", startB.x + 11, startB.y + 65);
        g.drawString("Wheel", optionsB.x + 42, optionsB.y + 65);
        g.drawString("Return", quitB.x + 38, quitB.y + 65);
        g2d.draw(startB);
        g2d.draw(optionsB);
        g2d.draw(quitB);
    }

    public void PauseGameGUI(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.GRAY);
        g.fillRect(WIDTH/2 -250, 50, 500, HEIGHT - 100);
        g.setColor(Color.black);
        Font fnt1 = new Font("arial", Font.BOLD, 40);
        g.setFont(fnt1);
        g.drawString("RESUME", startB.x + 15, startB.y + 65);
        g.drawString("RESTART", optionsB.x + 8, optionsB.y + 65);
        g.drawString("QUIT", quitB.x + 53, quitB.y + 65);
        g2d.draw(startB);
        g2d.draw(optionsB);
        g2d.draw(quitB);
    }

    public void victoryGUI(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.yellow);
        Font fnt1 = new Font("arial", Font.BOLD, 80);
        g.setFont(fnt1);
        g.drawString("VICTORY!", WIDTH/2 - 180, startB.y + 65);
        Font fnt2 = new Font("arial", Font.PLAIN, 25);
        g.setFont(fnt2);
        g.drawString("Completed laps in " + finaltime + " seconds", WIDTH/2 - 175, startB.y + 100);
        Font fnt3 = new Font("arial", Font.BOLD, 40);
        g.setFont(fnt3);
        g.setColor(Color.white);
        g.drawString("MENU", quitB.x + 42, quitB.y + 65);
        g2d.draw(quitB);
    }

    public void tick() {

    }

    public void addMouseController(Controller controller) {
        this.addMouseListener(controller);
    }

    public void removeMouseController(Controller controller) {
        this.removeMouseListener(controller);
    }

    public void addKeyController(Controller controller) {
        this.addKeyListener(controller);
    }

    public void removeKeyController(Controller controller) {
        this.removeKeyListener(controller);
    }


    public void setSprite(BufferedImage i) {
        spriteCar = i;
    }
    public void setBackGround(BufferedImage i) { background = i; }
    public void setState(GameState s) { state = s; }


    public void drawRotated(BufferedImage img,double rotationRequired,int x,int y, Graphics g) {
        rotationRequired = Math.toRadians(rotationRequired);
        double locationX = img.getWidth() / 2;
        double locationY = img.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage rotatedImage = op.filter(img, null); // my edit here

        // Drawing the rotated image at the required drawing locations
        g.drawImage(rotatedImage , x-(img.getWidth()/2), y-(img.getHeight()/2), null);
    }

    public GameState getState() {
        return state;
    }

    public void setTime(long l) {
        time = l;
    }

    public void setFinaltime() {
        finaltime = String.format("%.2f", (double) time/1000);
    }

    @Override
    public void update(Observable o, Object arg) {
        long lt = System.nanoTime();
        double ns = 1000000000/60;
        double dt = 0;
        long now = System.nanoTime();
        dt += (now - lt) / ns;
        lt = now;
        if (dt >= 1) {
            tick();
            dt--;
        }
        this.setTime(System.currentTimeMillis() - controller.getRace().getTimeBegin());
        if (state == GameState.GAME) {
            controller.getCar().updateLocation();
            if(controller.getCar().isCrashed()) {
                System.out.println("crashed");
                controller.getCar().updateSpeed(0 - controller.getCar().getSpeed());
            }
            if(controller.getCar().onFinish()) {
                if(controller.getRace().decrementLabsToGo()==0) {
                    state = GameState.VICTORY;
                    setFinaltime();
                    this.removeKeyController(controller);
                    this.addMouseListener(controller);
                }
            }
        }
        renderGame();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
