package GAME_BRICKS;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class fencePanel extends JPanel {

    BRICKS mainBRICKS;

    boolean turnOn = false;
    protected AtomicBoolean suspend;
    int cntMoving; // -90
    String direction = "";
    int randomFence;
    Color randomColor;
    String randomDirection = "";
    int speed;
    int delay;

    int type;
    Area area = null;

    double xx = 0.0D;
    double yy;
    double ww = 30.0D;
    double hh;

    int cycles = 10;

    fencePanel(BRICKS mainBRICKS, int type, int cycles, int speed, int delay) {
        this.mainBRICKS = mainBRICKS;

        this.type = type;
        this.xx = 0.0D;
        this.yy = 10.0D;
        this.ww = 30.0D;
        this.hh = 1.0D;
        this.cycles = cycles;
        this.direction = direction;
        this.speed = speed;
        this.delay = delay;

        randomDirection();
        randomFence = getRandomNumberInRange(1, 3);
        randomColor = randomColorChoice();

        suspend = new AtomicBoolean(false);

        this.setLayout(null);
        this.setBounds(mainBRICKS.getBasicPanel().getX(), mainBRICKS.getBasicPanel().getY() + 560,
                mainBRICKS.getBasicPanel().getWidth() + 150, 40);
    }

    fencePanel(int type, double x, double y, double w, double h, int cycles) {
        this.type = type;
        this.xx = x;
        this.yy = y;
        this.ww = w;
        this.hh = h;
        this.cycles = cycles;
        suspend = new AtomicBoolean(false);

        this.setLayout(null);
        this.setBounds(mainBRICKS.getBasicPanel().getX(), mainBRICKS.getBasicPanel().getY(),
                mainBRICKS.getBasicPanel().getWidth(), mainBRICKS.getBasicPanel().getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1.0f));

        g2.setColor(Color.orange);

        switch (this.type) {
            default:
                break;
            case 0:

                double x0 = 0.0D;
                double x = 0.0D;
                double y;
                double w = 0; // 30.0D 
                double h;
                int cnt = -1;
                int cnt1 = 0;
                for (int i = 0; i < cycles; i++) {

                    if (i % 2 == 0) {
                        y = 10.0D; // 10.0D; 
                        h = 1.0D;  //  1.0D;
                    } else {
                        y = 1.0D;  // 1.0D;
                        h = 10.0D; // 10.0D; 
                    }

                    cnt++;
                    cnt1++;

                    if (cnt == 2) {
                        w += 30.0D;
                        cnt = 0;
                    }

                    if (cnt1 == 2) {
                        x += 30.0D;
                        cnt1 = 0;
                    }

                    if (i == 0) {
                        x0 = 0.0D;
                        Line2D shape_lineDown = new Line2D.Double(x0, y + 25, w, 10.0D);
                        area = new Area(shape_lineDown);
                        g2.draw(shape_lineDown);
                    } else if (i != (cycles - 1)) {
                        Line2D line = new Line2D.Double(x, y, w, h);
                        area.add(new Area(line));
                        g2.draw(line);
                    } else {
                        Line2D shape_lineStraight = new Line2D.Double(x0, y + 25, x, y + 25);
                        Line2D shape_lineUp = new Line2D.Double(x, y + 25, x, 0);

                        area.add(new Area(shape_lineStraight));
                        area.add(new Area(shape_lineUp));
                        g2.draw(shape_lineStraight);
                        g2.draw(shape_lineUp);
                    }
//            Line2D line1 = new Line2D.Double(1.0D, 10.0D, 30.0D, 1.0D);
//            Line2D line2 = new Line2D.Double(30.0D, 1.0D, 30.0D, 10.0D);
//            Line2D line3 = new Line2D.Double(30.0D, 10.0D, 60.0D, 1.0D);
//            Line2D line4 = new Line2D.Double(60.0D, 1.0D, 60.0D, 10.0D);
//            Line2D line5 = new Line2D.Double(60.0D, 10.0D, 90.0D, 1.0D);
//            Line2D line6 = new Line2D.Double(90.0D, 1.0D, 90.0D, 10.0D);
//            Line2D line7 = new Line2D.Double(90.0D, 10.0D, 120.0D, 1.0D);
//            Line2D line8 = new Line2D.Double(120.0D, 1.0D, 120.0D, 10.0D);
//            Line2D line9 = new Line2D.Double(120.0D, 10.0D, 150.0D, 1.0D);
//            Line2D line10 = new Line2D.Double(150.0D, 1.0D, 150.0D, 10.0D);

//              System.out.println(x + "  " + y + "  " + w + "  " + cnt1);
                }
                break;
            case 1:
                if (getDirectionMove() == "right") {

                    area = new Area(randomFenceChoice(cntMoving, 0));

                    for (int xPos = cntMoving; xPos < this.cycles; xPos += 30) {

                        if (cntMoving < -60) { // -60
                            area.add(new Area(randomFenceChoice(xPos, 0)));
                        } else {
                            if (xPos < (this.cycles - 30)) { // -30
                                Area a = new Area(randomFenceChoice(xPos, 0));
                                area.subtract(a);
                                cntMoving = -90;
                            }
                        }

                        //  System.out.println(xPos);
                    }

                    g2.setColor(randomColor);
                    g2.fill(area);
                    g2.setPaint(Color.BLACK);
                    g2.draw(area);
                }
                if (getDirectionMove() == "left") {

                    // System.out.println("inside");
                    area = new Area(randomFenceChoice(cntMoving, 0));

                    for (int xPos = cntMoving; xPos > -90; xPos -= 30) {

                        if (cntMoving > (this.cycles - 30)) {
                            area.add(new Area(randomFenceChoice(xPos, 0)));
                        } else {
//                            if (xPos < (this.cycles - 30)) {
                            Area a = new Area(randomFenceChoice(xPos, 0));
                            area.subtract(a);
                            cntMoving = this.cycles;
//                            }
                        }

                        //  System.out.println(cntMoving);
                    }

                    g2.setColor(randomColor);
                    g2.fill(area);
                    g2.setPaint(Color.BLACK);
                    g2.draw(area);
                }
                break;
        }

    }

    //                        0         0
    public Shape fenceBredRight(double x, double y) {
        GeneralPath bred = new GeneralPath(); // Start with an empty shape
        bred.moveTo(x, y + 35);
        bred.lineTo(x, y + 10);
        bred.lineTo(x + 30, y + 1);
        bred.lineTo(x + 30, y + 35);
        bred.lineTo(x, y + 35);
        bred.closePath();
        return bred;
    }

    //                            0         0
    public Shape fenceBredLeft(double x, double y) {
        GeneralPath bred = new GeneralPath(); // Start with an empty shape
        bred.moveTo(x, y + 1);
        bred.lineTo(x, y + 35);
        bred.lineTo(x + 30, y + 35);
        bred.lineTo(x + 30, y + 10);
        bred.lineTo(x, y + 1);
        bred.closePath();
        return bred;
    }

    //                            0         0
    public Shape fenceBredCenter(double x, double y) {
        GeneralPath bred = new GeneralPath(); // Start with an empty shape
        bred.moveTo(x + 15, y + 1);
        bred.lineTo(x, y + 10);
        bred.lineTo(x, y + 35);
        bred.lineTo(x + 30, y + 35);
        bred.lineTo(x + 30, y + 10);
        bred.lineTo(x + 15, y + 1);
        bred.closePath();
        return bred;
    }

    private String randomDirection() {
        int choiceDirection = getRandomNumberInRange(1, 2);

        if (choiceDirection == 1) {
            this.direction = "right";
        }
        if (choiceDirection == 2) {
            this.direction = "left";
        }

        if (this.direction == "right") {
            this.cntMoving = -90;
        }
        if (this.direction == "left") {
            this.cntMoving = this.cycles;
        }

        return this.direction;
    }

    private Color randomColorChoice() {
        Color color = null;

        int red = getRandomNumberInRange(5, 234);
        int green = getRandomNumberInRange(5, 211);
        int blue = getRandomNumberInRange(5, 254);

        color = new Color(red, green, blue);

        return color;
    }

    private Shape randomFenceChoice(double x, double y) {
        Shape shape = null;

        if (randomFence == 1) {
            shape = fenceBredRight(x, y);
        }
        if (randomFence == 2) {
            shape = fenceBredLeft(x, y);
        }
        if (randomFence == 3) {
            shape = fenceBredCenter(x, y);
        }

        return shape;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void setDirectionMove(String direction) {
        this.direction = direction;
    }

    public String getDirectionMove() {
        if ((this.direction == "right") || (this.direction == "left")) {
            return this.direction;
        } else {
            return null;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    Thread moving = new Thread() {
        @Override
        public void run() {
            while (turnOn) {
                try {

                    // ******** in order to 'pause' this thread  !
                    if (suspend.get()) {
                        synchronized (mainBRICKS.fenceThread) {
                            // Pause
                            try {
                                mainBRICKS.fenceThread.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }

                    // do delay when start new game 
                    // s - key is pressed
                    if (mainBRICKS.shutterThread != null) {
                        if (mainBRICKS.shutterThread.isAlive()) {
                            sleep(5000);
                        }
                    }

//                    if (!mainBRICKS.shutterThread.isAlive()) {

                        if (getDirectionMove() == "right") {
                            cntMoving += speed;
                        } else {
                            cntMoving -= speed;
                        }

                        Thread.currentThread().sleep(delay);
                        repaint();
                        revalidate();
//                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) {
        double x0 = 0.0D;
        double x = 0.0D;
        double y;
        double w = 30.0D;
        double h;
        double j = 0.25D;
        int cnt = -1;
        int cnt1 = 0;
        for (int i = 0; i < 10; i++) {

            if (i % 2 == 0) {
                y = 10.0D;
                h = 1.0D;
            } else {
                y = 1.0D;
                h = 10.0D;
            }

            cnt++;
            cnt1++;

            if (cnt == 2) {
                w += 30.0D;
                cnt = 0;
            }

            if (cnt1 == 2) {
                x += 30.0D;
                cnt1 = 0;
            }

            if (i == 0) {
                x0 = 1.0D;
                Line2D line = new Line2D.Double(x0, y, w, h);
            } else {
                Line2D line = new Line2D.Double(x, y, w, h);
            }

//            Line2D line1 = new Line2D.Double(1.0D, 10.0D, 30.0D, 1.0D);
//            Line2D line2 = new Line2D.Double(30.0D, 1.0D, 30.0D, 10.0D);
//            Line2D line3 = new Line2D.Double(30.0D, 10.0D, 60.0D, 1.0D);
//            Line2D line4 = new Line2D.Double(60.0D, 1.0D, 60.0D, 10.0D);
//            Line2D line5 = new Line2D.Double(60.0D, 10.0D, 90.0D, 1.0D);
//            Line2D line6 = new Line2D.Double(90.0D, 1.0D, 90.0D, 10.0D);
//            Line2D line7 = new Line2D.Double(90.0D, 10.0D, 120.0D, 1.0D);
//            Line2D line8 = new Line2D.Double(120.0D, 1.0D, 120.0D, 10.0D);
//            Line2D line9 = new Line2D.Double(120.0D, 10.0D, 150.0D, 1.0D);
//            Line2D line10 = new Line2D.Double(150.0D, 1.0D, 150.0D, 10.0D);
//            System.out.println(x + "  " + y + "  " + w + "  " + cnt1);
        }

        //                                int type, int cycles, String direction, int speed, int delay                                 
//        final fencePanel fence = new fencePanel(1, 1320, "right", 3, 200);
//        System.out.println(fence.getDirectionMove());
//
//        SwingUtilities.invokeLater(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        fence.turnOn = true;
//                        Thread thr = new Thread(fence.moving);
//                        thr.start();
//                    }
//                });
//
//        JFrame f = new JFrame();
//        f.setSize(1015, 600);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(fence);
//        f.setVisible(true);
    }
} //    }
//}
