/**
 * Created by Jasper on 3-11-2015.
 */
/*
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;

import java.io.IOException;

public class I2CProtocol extends Thread {
    private Controller controller;

    private static int piBus = 0;
    private static int FPGAAddress = 160;
    private int byte1;
    private int byte2;
    private int byte3;
    private int byte4;
    private I2CDevice device;

    public I2CProtocol(Controller controller) {
        this.controller = controller;
        try {
            //initialize things
            System.out.println("Starting");
            I2CBus i2cbus = I2CFactory.getInstance(I2CBus.BUS_1);
            System.out.println("I2CBus created");
            device = i2cbus.getDevice(0x0A);
            System.out.println("I2CDevice created");
            writeByte(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            this.readByte(device);
            this.writeByte(device);
        }
    }

    public void readByte(I2CDevice d) {
        try {
            System.out.println("ready to read");
            byte1 = d.read(0x0A);
            System.out.println("Read something 1: " + byte1);
            byte2 = d.read(0x0B);
            System.out.println("Read something 2: " + byte2);
            byte3 = d.read(0x0C);
            System.out.println("Read something 3: " + byte3);
            byte4 = d.read(0x0D);
            System.out.println("Read something 4: " + byte4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeByte(I2CDevice d) {
        try {
            d.write(12, (byte) controller.getCar().getSpeed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getByte1() {
        return byte1;
    }

    public int getByte2() {
        return byte2;
    }

    public void updateCarOrientation(int b1, int b2) {
        if (b2 >= 128) {
            b1 = -b1;
            b2 -= 128;
        }
        controller.getCar().updateOrientation(b1);
    }

    public void updateCarGas(int b2) {
        if (b2 >= 64) {
            controller.getCar().updateSpeed(1);
            b2 -= 64;
        }
        if (b2 > 32) {
            controller.getCar().updateSpeed(-2);
            b2 -= 32;
        }
    }

    public int getByte3() {
        return byte3;
    }

    public int getByte4() {
        return byte4;
    }
}
*/