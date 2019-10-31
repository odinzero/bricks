package GAME_BRICKS;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

class staticBricks extends JComponent {

    boolean leftSide = false;
    boolean topSide = false;
    boolean rightSide = false;
    boolean bottomSide = false;

    boolean touch = false;
    boolean touchRepaint = false;
    bubbleScore bubble;

    int typeBricks; // the choice brick together with 'bubbleScore' type :
    // if 'bubbleScore' type 5 , then typebrick  5

    int brickWidth = 65, brickHeight = 25; // set brick width and height

    BRICKS mainBRICKS;

    staticBricks(BRICKS main, int typeBrick, bubbleScore bubb) {
        mainBRICKS = main;
        typeBricks = typeBrick;
        bubble = bubb;
        setBrickForm(0, 65, 25);
    }

    protected int getBricksTYPE() {
        return typeBricks;
    }

    protected void setBrickForm(int shape, int width, int height) {
        switch (shape) {
            default:
                break;
            case 0:
                rect = new RoundRectangle2D.Double(0, 0, width, height, 10, 10);
                break;
        }
    }

    protected Shape getBrickForm() {
        return rect;
    }

    RoundRectangle2D rect = null;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int choice = this.getBricksTYPE();

        if (!touch) {
            switch (choice) {

                default:
                    break;
                case 5:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, new Color(255, 242, 226), 65, 25, new Color(213, 255, 122), false));
                    g2.fill(rect);
                    g2.setPaint(Color.ORANGE);
                    g2.draw(rect);
                    break;
                case 10:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, new Color(255, 196, 200), 65, 25, new Color(255, 180, 68), false));
                    g2.fill(rect);
                    g2.setPaint(Color.WHITE);
                    g2.draw(rect);
                    break;
                case 25:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, new Color(255, 155, 250), 65, 25, new Color(255, 22, 69), false));
                    g2.fill(rect);
                    g2.setPaint(Color.BLUE);
                    g2.draw(rect);
                    break;
                case 50:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, Color.GREEN, 65, 25, Color.ORANGE, false));
                    g2.fill(rect);
                    g2.setColor(Color.MAGENTA);
                    g2.draw(rect);
                    break;
                case 100:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, new Color(185, 147, 255), 65, 25, new Color(255, 17, 195), false));
                    g2.fill(rect);
                    g2.setColor(Color.ORANGE);
                    g2.draw(rect);
                    break;
                case 150:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, Color.YELLOW, 65, 25, new Color(179, 15, 255), false));
                    g2.fill(rect);
                    g2.setColor(Color.WHITE);
                    g2.draw(rect);
                    break;
                case 300:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, new Color(201, 255, 227), 65, 25, new Color(179, 15, 255), false));
                    g2.fill(rect);
                    g2.setColor(Color.BLACK);
                    g2.draw(rect);
                    break;
                case 500:
                    rect = (RoundRectangle2D) getBrickForm();
                    g2.setPaint(new GradientPaint(0, 0, Color.RED, 65, 25, Color.CYAN, false)); //new Color(187,71,255)
                    g2.fill(rect);
                    g2.setColor(Color.WHITE);
                    g2.draw(rect);
                    break;

            }
        } else {
            if (cntThread == 1) {
                rect = (RoundRectangle2D) getBrickForm();
                g2.setPaint(Color.RED);
                g2.fill(rect);
                g2.setPaint(Color.BLACK);
                g2.draw(rect);
            }
            if (cntThread == 2) {
                rect = (RoundRectangle2D) getBrickForm();
                g2.setPaint(Color.PINK);
                g2.fill(rect);
                g2.setPaint(Color.BLACK);
                g2.draw(rect);
            }
        }
    }

    int cntThread = 0;
    Thread thr0 = new Thread() {
        @Override
        public void run() {
            while (touchRepaint) {
                try {
                    cntThread++;
                    Thread.currentThread().sleep(150);
                    if (cntThread == 2) {
                        setBounds(mainBRICKS.xPos - 1000, mainBRICKS.yPos, 46, 46);
                        repaint();
                        touchRepaint = false;
                    }
                } catch (InterruptedException ex) {
                }
            }
        }
    };

    boolean moving = false;
    Thread thrBubble = new Thread() {
        @Override
        public void run() {
            int xloc = bubble.getLocation().x;
            int yloc = bubble.getLocation().y;
            int cnt = 0;
            bubble.setVisible(true);
            while (moving) {
                try {
                    // *** when next LEVEL have passed and 'bubble' is flying smart stop fly!
                    // becuse repaint shutter panel it is not good
                    if (mainBRICKS.getTransitionLevel() <= 0) {
                        bubble.setBounds(bubble.getLocation().x - 2000, bubble.getLocation().y, 46, 46);
                        moving = false;
                    } else {
                        bubble.setLocation(bubble.getLocation().x, yloc);
                    }

                    yloc -= 10;
                    Thread.currentThread().sleep(200);
                    cnt++;
                    if (cnt == 12) {

                        bubble.setBounds(bubble.getLocation().x - 2000, bubble.getLocation().y, 46, 46);
                        moving = false;
                    }

                    bubble.repaint();
                } catch (InterruptedException ex) {
                }
            }
        }
    };

    int cnt = 0;
    private void computeKilledBricks() {
        cnt++;
        int x = getBounds().x;
        int y = getBounds().y;
        int w = getBounds().width;
        int h = getBounds().height;
        int type = getBricksTYPE();
        String str = x + " " + y + " " + w + " " + h + " " + type;
        mainBRICKS.statesOfPosition.killedBrick.add(str);
        
        int cnt = 0;
         Iterator itr = mainBRICKS.statesOfPosition.killedBrick.iterator();
        while (itr.hasNext()) {
           cnt++;
           Object element = itr.next();
//           System.out.println("killedBrick: " +  element + "  " +   cnt);
        }
    }

    private void computeKilledBubbles() {
        int x = bubble.getBounds().x;
        int y = bubble.getBounds().y;
        int w = bubble.getBounds().width;
        int h = bubble.getBounds().height;
        int type = bubble.getScoreType();
        String str = x + " " + y + " " + w + " " + h + " " + type;
        mainBRICKS.statesOfPosition.killedBubble.add(str);
     //   System.out.println("killedBrick: " + mainBRICKS.statesOfPosition.killedBubble.get(0));

    }

    protected void SCHEMA_MOVE() {
        // ********** mBrick LEFT
        if (mainBRICKS.mBrick.getX() <= (this.getX() + this.getWidth())) {
            leftSide = false;  //System.out.println("left = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            leftSide = true;
        }

        if (mainBRICKS.mBrick.getX() <= (this.getX() + this.getWidth() + 4)) {
            if (mainBRICKS.mBrick.getY() <= ((this.getY() + this.getHeight()))) {
                if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight()) >= ((this.getY()))) {
                    if (leftSide) {
                        mainBRICKS.turn_Xlow = false;
                        mainBRICKS.turn_Xup = true;

                        mainBRICKS.levelTransition--;
                        System.out.println("LEFT : " + mainBRICKS.levelTransition);

                        // for EFFECT of disappearring  !!!
                        touchRepaint = true;
                        Thread tthr = new Thread(thr0);
                        tthr.start();
                        // display bubble score
                        moving = true;
                        Thread bub = new Thread(thrBubble);
                        bub.start();
                        // repaint this component to invisible location
                        touch = true;
                        // start Increase Thread
                       // mainBRICKS.score.startThreadIncrease();
                        mainBRICKS.cntScore += bubble.getScoreType();
                        mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                        
                        computeKilledBricks();
                        computeKilledBubbles();
                    }
                }
            }
        }
// ********** mBrick RIGHT
        if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth()) >= (this.getX())) { //
            rightSide = false; // System.out.println("right = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            rightSide = true;
        }

        if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth() + 4) > (this.getX())) {
            if (mainBRICKS.mBrick.getY() <= ((this.getY() + this.getHeight()))) {
                if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight()) >= ((this.getY()))) {
                    if (rightSide) {
                        mainBRICKS.turn_Xlow = true;
                        mainBRICKS.turn_Xup = false;

                        mainBRICKS.levelTransition--;
                        System.out.println("RIGHT : " + mainBRICKS.levelTransition);

                        // for EFFECT of disappearring  !!!
                        touchRepaint = true;
                        Thread tthr = new Thread(thr0);
                        tthr.start();
                        // display bubble score
                        moving = true;
                        Thread bub = new Thread(thrBubble);
                        bub.start();
                        // repaint this component to invisible location
                        touch = true;
                        // start Increase Thread
                      //  mainBRICKS.score.startThreadIncrease();
                        mainBRICKS.cntScore += bubble.getScoreType();
                        mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                        
                        computeKilledBricks();
                        computeKilledBubbles();
                    }
                }
            }
        }
// **********  TOP SIDE of mBrick
        if ((mainBRICKS.mBrick.getY()) <= (this.getY() + this.getHeight())) { //
            topSide = false; // System.out.println("top = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            topSide = true;
        }

        if ((mainBRICKS.mBrick.getY() - 4) <= (this.getY() + this.getHeight())) {
            if (mainBRICKS.mBrick.getX() <= ((this.getX() + this.getWidth()))) {
                if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth()) >= ((this.getX()))) {
                    if (topSide) {
                        mainBRICKS.turn_Ylow = false;
                        mainBRICKS.turn_Yup = true;

                        mainBRICKS.levelTransition--;
                        System.out.println("TOP : " + mainBRICKS.levelTransition);

                        // for EFFECT of disappearring
                        touchRepaint = true;
                        Thread tthr = new Thread(thr0);
                        tthr.start();
                        // display bubble score
                        moving = true;
                        Thread bub = new Thread(thrBubble);
                        bub.start();
                        // repaint this component to invisible location
                        touch = true;
                        // start Increase Thread
                      //  mainBRICKS.score.startThreadIncrease();
                        mainBRICKS.cntScore += bubble.getScoreType();
                        mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);

                        computeKilledBricks();
                        computeKilledBubbles();
                    }
                }
            }
        }
        // **********  BOTTOM SIDE of mBrick
        if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight()) >= (this.getY())) { //
            bottomSide = false; // System.out.println("top = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            bottomSide = true;
        }

        if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight() + 4) >= (this.getY())) {
            if (mainBRICKS.mBrick.getX() <= ((this.getX() + this.getWidth()))) {
                if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth()) >= ((this.getX()))) {
                    if (bottomSide) {
                        mainBRICKS.turn_Ylow = true;
                        mainBRICKS.turn_Yup = false;

                        mainBRICKS.levelTransition--;
                        System.out.println("BOTTOM : " + mainBRICKS.levelTransition);

                        // for EFFECT of disappearring  !!!
                        touchRepaint = true;
                        Thread tthr = new Thread(thr0);
                        tthr.start();
                        // display bubble score
                        moving = true;
                        Thread bub = new Thread(thrBubble);
                        bub.start();
                        // repaint this component to invisible location
                        touch = true;
                        // start Increase Thread
//                        mainBRICKS.score.startThreadIncrease();
                        mainBRICKS.cntScore += bubble.getScoreType();
                        mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
//                     System.out.println("bottom-YES");
                        computeKilledBricks();
                        computeKilledBubbles();
                    }
                }
            }
        }
    }

}
