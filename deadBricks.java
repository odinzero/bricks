package GAME_BRICKS;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import static javafx.scene.input.KeyCode.T;
import javax.swing.*;

public class deadBricks extends JComponent {

    boolean leftSide = false;
    boolean topSide = false;
    boolean rightSide = false;
    boolean bottomSide = false;

    boolean touch = false;
    boolean touchRepaint = false;

    int shiftTexture;

    int type;
    BRICKS mainBRICKS;
    deadBricksScenario Scenario;
    
    // FOR SCENARIO -> 6
    // 'numberBubblesTop'   -> number bubbles for TOP side dead brick in array 
    // 'numberBubblesRight' -> number bubbles for RIGHT side dead brick in array 
    // 'numberBubblesBottom' -> number bubbles for BOTTOM side dead brick in array
    // 'numberBubblesLeft'   -> number bubbles for LEFT side dead brick in array
    deadBricks(BRICKS main, int Type, int numberBubblesTop, int numberBubblesRight, 
                                      int numberBubblesBottom, int numberBubblesLeft) {
        mainBRICKS = main;
        type = Type;

        Scenario = new deadBricksScenario(mainBRICKS, this);
        
        Scenario.choiceScenario(6);
        // set initial conditions for parameters for different scenarios
        initConfig6(numberBubblesTop, numberBubblesRight, numberBubblesBottom, numberBubblesLeft);
    }
    
    // For SCENARIO: 1,2,3,4,5,7,8,9,10,11,12,13,14
    // 'numberBubbles' -> number bubbles for each side dead brick in array
    // Type -> -5,-10,-25,5,10,25,50,100,150,300,500
    // choiceScenario -> 1,2,3,4,5,7,8,9,10,11,12,13,14
    // numberBubbles -> 1,2,3 .....
    deadBricks(BRICKS main, int Type, int choiceScenario, int numberBubbles) {
        mainBRICKS = main;
        type = Type;
        
        if(choiceScenario < 1 || choiceScenario > 14) {
            choiceScenario = 1;
        }
        if(choiceScenario  == 6) {
            choiceScenario = 1; 
        }

        Scenario = new deadBricksScenario(mainBRICKS, this);
        if(choiceScenario == 1 || choiceScenario == 2 ||
           choiceScenario == 3 || choiceScenario == 4 || choiceScenario == 5 || 
           choiceScenario == 7 || choiceScenario == 8 || choiceScenario == 9 || 
           choiceScenario == 10 || choiceScenario == 11 || choiceScenario == 12 || 
           choiceScenario == 13 || choiceScenario == 14) {
        Scenario.choiceScenario(choiceScenario);
        // set initial conditions for parameters for different scenarios
        initConfig(numberBubbles);
        }
    }
    
    // just for SCENARIO_6
    protected void initConfig6(int numberBubblesTop, int numberBubblesRight, 
                               int numberBubblesBottom, int numberBubblesLeft ) {
        //
        if (Scenario.scenario == Scenario.SCENARIO_6) {
          
            Scenario.assignRandomBubblesForTopSide(numberBubblesTop);
            Scenario.assignRandomBubblesForRightSide(numberBubblesRight);
            Scenario.assignRandomBubblesForBottomSide(numberBubblesBottom);
            Scenario.assignRandomBubblesForLeftSide(numberBubblesLeft);

            Scenario.indexOfBubble = Scenario.bubblesLeft.length
                    + Scenario.bubblesRight.length + Scenario.bubblesTop.length + Scenario.bubblesBottom.length;
        }
    }

    protected void initConfig(int numberBubbles) {
        
        if (Scenario.scenario == Scenario.SCENARIO_1 || Scenario.scenario == Scenario.SCENARIO_2) {
                //Scenario.cntBubblesInArray = 3;
            //Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.bubbles = Scenario.bubblesForDeadBricks(type, numberBubbles);
            Scenario.indexOfBubble = Scenario.bubbles.length;
        }
        
        if (Scenario.scenario == Scenario.SCENARIO_3 || Scenario.scenario == Scenario.SCENARIO_4) {
                //Scenario.cntBubblesInArray = 3;
            //Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.bubbles = Scenario.bubblesForDeadBricks(type, 4);
            Scenario.indexOfBubble = Scenario.bubbles.length;
        }
        //
        if (Scenario.scenario == Scenario.SCENARIO_5 || Scenario.scenario == Scenario.SCENARIO_5) {
            Scenario.cntBubblesInArray = numberBubbles;
            Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.indexOfBubble = Scenario.bubblesLeft.length
                    + Scenario.bubblesRight.length + Scenario.bubblesTop.length + Scenario.bubblesBottom.length;
        }
        //
        if (Scenario.scenario == Scenario.SCENARIO_7) {
            Scenario.cntBubblesInArray = numberBubbles;
            Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.indexOfBubble = Scenario.bubblesTop.length;
        }
        if (Scenario.scenario == Scenario.SCENARIO_8) {
            Scenario.cntBubblesInArray = numberBubbles;
            Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.indexOfBubble = Scenario.bubblesRight.length;
        }
        if (Scenario.scenario == Scenario.SCENARIO_9) {
            Scenario.cntBubblesInArray = numberBubbles;
            Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.indexOfBubble = Scenario.bubblesBottom.length;
        }
        if (Scenario.scenario == Scenario.SCENARIO_10) {
            Scenario.cntBubblesInArray = numberBubbles;
            Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.indexOfBubble = Scenario.bubblesLeft.length;
        }
        if (Scenario.scenario == Scenario.SCENARIO_11) {
            Scenario.cntBubblesInArray = numberBubbles;
            Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.indexOfBubble = Scenario.bubblesLeft.length + Scenario.bubblesRight.length;
        }
        if (Scenario.scenario == Scenario.SCENARIO_12) {
            Scenario.cntBubblesInArray = numberBubbles;
            Scenario.assignRandomBubblesForEachSide(Scenario.cntBubblesInArray);

            Scenario.indexOfBubble = Scenario.bubblesTop.length + Scenario.bubblesBottom.length;
        }
        if (Scenario.scenario == Scenario.SCENARIO_13 || Scenario.scenario == Scenario.SCENARIO_14 ) {
           
            Scenario.bubbles = Scenario.bubblesForDeadBricks(type, numberBubbles);
            Scenario.indexOfBubble = Scenario.bubbles.length;
        }
    }

    protected int getDeadBricksTYPE() {
        return type;
    }

    protected Shape getDeadBrickForm() {
        return sh;
    }

    protected void setBubblesLocation(int x, int y) {
        if (Scenario.scenario == Scenario.SCENARIO_1 || Scenario.scenario == Scenario.SCENARIO_2
         || Scenario.scenario == Scenario.SCENARIO_3 || Scenario.scenario == Scenario.SCENARIO_4 
         || Scenario.scenario == Scenario.SCENARIO_13 || Scenario.scenario == Scenario.SCENARIO_14  ) {
            for (int k = 0; k < Scenario.bubbles.length; k++) {
                Scenario.bubbles[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubbles[k]);
                Scenario.bubbles[k].setVisible(false);
            }
        }
        if (Scenario.scenario == Scenario.SCENARIO_5 || Scenario.scenario == Scenario.SCENARIO_6) {
            // bubbles of LEFT side of dead brick
            for (int k = 0; k < Scenario.bubblesLeft.length; k++) {
                Scenario.bubblesLeft[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesLeft[k]);
                Scenario.bubblesLeft[k].setVisible(false);
            }
            // bubbles of RIGHT side of dead brick
            for (int k = 0; k < Scenario.bubblesRight.length; k++) {
                Scenario.bubblesRight[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesRight[k]);
                Scenario.bubblesRight[k].setVisible(false);
            }
            // bubbles of TOP side of dead brick
            for (int k = 0; k < Scenario.bubblesTop.length; k++) {
                Scenario.bubblesTop[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesTop[k]);
                Scenario.bubblesTop[k].setVisible(false);
            }
            // bubbles of BOTTOM side of dead brick
            for (int k = 0; k < Scenario.bubblesBottom.length; k++) {
                Scenario.bubblesBottom[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesBottom[k]);
                Scenario.bubblesBottom[k].setVisible(false);
            }
        }
        // TOP side of dead Brick
        if (Scenario.scenario == Scenario.SCENARIO_7) {
            // bubbles of TOP side of dead brick
            for (int k = 0; k < Scenario.bubblesTop.length; k++) {
                Scenario.bubblesTop[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesTop[k]);
                Scenario.bubblesTop[k].setVisible(false);
            }
        }
        // RIGHT side of dead Brick
        if (Scenario.scenario == Scenario.SCENARIO_8) {
            // bubbles of RIGHT side of dead brick
            for (int k = 0; k < Scenario.bubblesRight.length; k++) {
                Scenario.bubblesRight[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesRight[k]);
                Scenario.bubblesRight[k].setVisible(false);
            }
        }
        // BOTTOM side of dead Brick
        if (Scenario.scenario == Scenario.SCENARIO_9) {
            // bubbles of BOTTOM side of dead brick
            for (int k = 0; k < Scenario.bubblesBottom.length; k++) {
                Scenario.bubblesBottom[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesBottom[k]);
                Scenario.bubblesBottom[k].setVisible(false);
            }
        }
        // LEFT side of dead Brick
        if (Scenario.scenario == Scenario.SCENARIO_10) {
            // bubbles of LEFT side of dead brick
            for (int k = 0; k < Scenario.bubblesLeft.length; k++) {
                Scenario.bubblesLeft[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesLeft[k]);
                Scenario.bubblesLeft[k].setVisible(false);
            }
        }
        // LEFT and RIGHT side of dead Brick
        if (Scenario.scenario == Scenario.SCENARIO_11) {
            // bubbles of LEFT side of dead brick
            for (int k = 0; k < Scenario.bubblesLeft.length; k++) {
                Scenario.bubblesLeft[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesLeft[k]);
                Scenario.bubblesLeft[k].setVisible(false);
            }
            // bubbles of RIGHT side of dead brick
            for (int k = 0; k < Scenario.bubblesRight.length; k++) {
                Scenario.bubblesRight[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesRight[k]);
                Scenario.bubblesRight[k].setVisible(false);
            }
        }
        // TOP and BOTTOM side of dead Brick
        if (Scenario.scenario == Scenario.SCENARIO_12) {
            // bubbles of TOP side of dead brick
            for (int k = 0; k < Scenario.bubblesTop.length; k++) {
                Scenario.bubblesTop[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesTop[k]);
                Scenario.bubblesTop[k].setVisible(false);
            }
            // bubbles of BOTTOM side of dead brick
            for (int k = 0; k < Scenario.bubblesBottom.length; k++) {
                Scenario.bubblesBottom[k].setBounds(x, y, 112, 82);
                mainBRICKS.basic.add(Scenario.bubblesBottom[k]);
                Scenario.bubblesBottom[k].setVisible(false);
            }
        }
    }

    protected Shape setTYPE_5(Graphics2D g2) {
        RoundRectangle2D round = new RoundRectangle2D.Double(0, 0, 65, 30, 10, 10);
        GeneralPath[] path = new GeneralPath[6];
        int i = -1;
        for (int x = 10; x <= 60; x += 10) {
            i++;
            path[i] = new GeneralPath();
            path[i].moveTo(x, 0);
            path[i].lineTo(x, 30);
            path[i].lineTo(x - 5, 30);
            path[i].lineTo(x - 5, 0);
            path[i].closePath();
        }
        Area ar = new Area(round);
        for (int k = 0; k < path.length; k++) {
            ar.subtract(new Area(path[k]));
        }
        sh = ar;
        return sh;
    }

    protected Shape setTYPE_10(Graphics2D g2) {
        RoundRectangle2D round = new RoundRectangle2D.Double(0, 0, 65, 30, 10, 10);
        GeneralPath[] path = new GeneralPath[7];
        int i = -1;
        for (int y = 5; y <= 65; y += 10) {
            i++;
            // System.out.println(i);
            path[i] = new GeneralPath();
            path[i].moveTo(0, y);
            path[i].lineTo(65, y);
            path[i].lineTo(65, y + 5);
            path[i].lineTo(0, y + 5);
            path[i].closePath();
        }
        Area ar = new Area(round);
        for (int k = 0; k < path.length; k++) {
            ar.subtract(new Area(path[k]));
        }
        sh = ar;
        return sh;
    }

    protected Shape setTYPE_25(Graphics2D g2) {
        RoundRectangle2D round = new RoundRectangle2D.Double(0, 0, 65, 30, 10, 10);
        GeneralPath[] path = new GeneralPath[7];
        int i = -1;
        for (int y = 5; y <= 65; y += 10) {
            i++;
            System.out.println(i);
            path[i] = new GeneralPath();
            path[i].moveTo(0, y);
            path[i].lineTo(65, y);
            path[i].lineTo(65, y + 5);
            path[i].lineTo(0, y + 5);
            path[i].closePath();
        }
        Area ar = new Area(round);
        for (int k = 0; k < path.length; k++) {
            ar.subtract(new Area(path[k]));
        }
        sh = ar;
        return sh;
    }

    Shape sh = null;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int choice = this.getDeadBricksTYPE();

        BufferedImage bi1 = getTexturePaint();
        Rectangle rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
        TexturePaint tp1 = new TexturePaint(bi1, rr);

        switch (choice) {

            default:
                break;
            case -5:
                sh = new RoundRectangle2D.Double(0, 0, 65, 20, 8, 8);
                g2.setPaint(tp1);
                g2.fill(sh);
                g2.setPaint(Color.ORANGE);
                g2.setStroke(new BasicStroke( .5f ));
                g2.draw(sh);
                break;
            case -10:
                setTexturePaint(7);
                BufferedImage bi2 = getTexturePaint();
                sh = new RoundRectangle2D.Double(0, 0, 65, 16, 6, 6);
                TexturePaint tp2 = new TexturePaint(bi2, rr);
                g2.setPaint(tp2);
                g2.fill(sh);
                g2.setPaint(Color.GRAY);
                g2.setStroke(new BasicStroke( .9f ));
                g2.draw(sh);
                break; 
            case -25:
                setTexturePaint(8);
                BufferedImage bi3 = getTexturePaint();
                sh = new RoundRectangle2D.Double(0, 0, 65, 16, 6, 6);
                TexturePaint tp3 = new TexturePaint(bi3, rr);
                g2.setPaint(tp3);
                g2.fill(sh);
                g2.setPaint(Color.GRAY);
                g2.setStroke(new BasicStroke( .9f ));
                g2.draw(sh);
                break;    
            case 5:
                sh = setTYPE_5(g2);
//          getTexturePaint();
//          g2.setPaint( new GradientPaint( 0, 0, Color.BLUE , 65 , 25, Color.BLUE, false) );
                g2.setPaint(tp1);
                g2.fill(sh);
                g2.setPaint(Color.gray);
                g2.draw(sh);
                break;
            case 10:
                sh = setTYPE_10(g2);
                g2.setPaint(tp1);
                g2.fill(sh);
                g2.setPaint(Color.BLACK);
                g2.draw(sh);
                break;
            case 25:
                sh = setTYPE_25(g2);
                g2.setPaint(new GradientPaint(0, 0, new Color(255, 155, 250), 65, 25, new Color(255, 22, 69), false));
                g2.fill(sh);
                g2.setPaint(Color.BLUE);
                g2.draw(sh);
                break;
            case 50:
//          sh  =  (RoundRectangle2D) getBrickForm();
                g2.setPaint(new GradientPaint(0, 0, Color.GREEN, 65, 25, Color.ORANGE, false));
                g2.fill(sh);
                g2.setColor(Color.MAGENTA);
                g2.draw(sh);
                break;
            case 100:
//          sh  =  (RoundRectangle2D) getBrickForm();
                g2.setPaint(new GradientPaint(0, 0, new Color(185, 147, 255), 65, 25, new Color(255, 17, 195), false));
                g2.fill(sh);
                g2.setColor(Color.ORANGE);
                g2.draw(sh);
                break;
            case 150:
//          sh  =  (RoundRectangle2D) getBrickForm();
                g2.setPaint(new GradientPaint(0, 0, Color.YELLOW, 65, 25, new Color(179, 15, 255), false));
                g2.fill(sh);
                g2.setColor(Color.WHITE);
                g2.draw(sh);
                break;
            case 300:
//          sh  =  (RoundRectangle2D) getBrickForm();
                g2.setPaint(new GradientPaint(0, 0, new Color(201, 255, 227), 65, 25, new Color(179, 15, 255), false));
                g2.fill(sh);
                g2.setColor(Color.BLACK);
                g2.draw(sh);
                break;
            case 500:
//          sh  =  (RoundRectangle2D) getBrickForm();
                g2.setPaint(new GradientPaint(0, 0, Color.RED, 65, 25, Color.CYAN, false)); //new Color(187,71,255)
                g2.fill(sh);
                g2.setColor(Color.WHITE);
                g2.draw(sh);
                break;

        }
//          }
//          else
//          {
//              if( cntThread == 1 )
//              {
//                   sh  =  getDeadBrickForm();
//                   g2.setPaint(Color.RED);
//                   g2.fill(sh);
//                   g2.setPaint(Color.BLACK);
//                   g2.draw(sh);
//              }
//               if( cntThread == 2 )
//              {
//                   sh  =  getDeadBrickForm();
//                   g2.setPaint(Color.PINK);
//                   g2.fill(sh);
//                   g2.setPaint(Color.BLACK);
//                   g2.draw(sh);
//              }
//          }
    }

    protected void setTexturePaint(int ch) {
        shiftTexture = ch;
    }

    private BufferedImage getTexturePaint() {
        int size = 8;
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        if (shiftTexture == 1) {
            g2.setPaint(Color.ORANGE);
        } else if (shiftTexture == 2) {
            g2.setPaint(new Color(193, 7, 255));
        } else if (shiftTexture == 3) {
            g2.setPaint(Color.RED);
        } else if (shiftTexture == 4) {
            g2.setPaint(Color.YELLOW);
        } else if (shiftTexture == 5) {
            g2.setPaint(Color.GREEN);
        } else if (shiftTexture == 6) {
            size = 10;
            g2.setPaint(Color.GREEN);
        } else if (shiftTexture == 7) {  // -10 
            g2.setPaint(Color.GRAY);
        } else if (shiftTexture == 8) {  // -25
           // size = 4;
            g2.setPaint(new Color(124,121,3));
        }
        // for 10
        g2.fillRect(0, 0, size / 2, size / 2);

        if (shiftTexture == 1) {
            g2.setPaint(Color.YELLOW);
        } else if (shiftTexture == 2) {
            g2.setPaint(new Color(193, 7, 255));
        } else if (shiftTexture == 3) {
            g2.setPaint(Color.RED);
        } else if (shiftTexture == 4) {
            g2.setPaint(Color.MAGENTA);
        } else if (shiftTexture == 5) {
            g2.setPaint(Color.BLUE);
        } else if (shiftTexture == 6) {
            size = 10;
            g2.setPaint(Color.LIGHT_GRAY);
        }  else if (shiftTexture == 7) {  // -10
            g2.setPaint(Color.DARK_GRAY);
        }  else if (shiftTexture == 8) {  // -25
           // size = 4;
            g2.setPaint(new Color(124,121,3));
        }
        // for 10
        g2.fillRect(size / 2, 0, size, size / 2);

        if (shiftTexture == 1) {
            g2.setPaint(Color.YELLOW);
        } else if (shiftTexture == 2) {
            g2.setPaint(Color.GREEN);
        } else if (shiftTexture == 3) {
            g2.setPaint(Color.CYAN);
        } else if (shiftTexture == 4) {
            g2.setPaint(Color.YELLOW);
        } else if (shiftTexture == 5) {
            g2.setPaint(Color.RED);
        } else if (shiftTexture == 6) {
            size = 10;
            g2.setPaint(Color.GREEN);
        }  else if (shiftTexture == 7) {  // -10
            g2.setPaint(Color.DARK_GRAY);
        }  else if (shiftTexture == 8) {  // -25
           // size = 4;
            g2.setPaint(new Color(88,46,39));
        }
        // for 10
        g2.fillRect(0, size / 2, size / 2, size);

        if (shiftTexture == 1) {
            g2.setPaint(Color.ORANGE);
        } else if (shiftTexture == 2) {
            g2.setPaint(Color.GREEN);
        } else if (shiftTexture == 3) {
            g2.setPaint(Color.CYAN);
        } else if (shiftTexture == 4) {
            g2.setPaint(Color.MAGENTA);
        } else if (shiftTexture == 5) {
            g2.setPaint(Color.YELLOW);
        } else if (shiftTexture == 6) {
            size = 10;
            g2.setPaint(Color.LIGHT_GRAY);
        } else if (shiftTexture == 7) {  // -10
            g2.setPaint(Color.GRAY);
        }  else if (shiftTexture == 8) { // -25
           // size = 4;
            g2.setPaint(new Color(88,46,39));
        }
        // for 10
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

//    private void hideBubblesAfterTouch(int indexBubble) throws InterruptedException {
//        // if (indexBubble == 3) {
////            yloc -= 10;
//        Thread.currentThread().sleep(200);
////        cntMove++;
//        // System.out.println("cntMove: " + cntMove + "  yloc : " + yloc);
//        if (cntMove == 12) {
//            bubbles[indexBubble]
//                    .setBounds(bubbles[indexBubble].getLocation().x - 2000, bubbles[indexBubble].getLocation().y, 46, 46);
//            moving = false;
//        }
//        bubbles[indexBubble].repaint();
//        // }
//    }
    protected void SCHEMA_TOUCH() {
        // ********** mBrick RIGHT
        if (mainBRICKS.mBrick.getX() <= (this.getX() + this.getWidth())) {
            rightSide = false;  //System.out.println("left = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            rightSide = true;
        }

        if (mainBRICKS.mBrick.getX() <= (this.getX() + this.getWidth() + 4)) {
            if (mainBRICKS.mBrick.getY() <= ((this.getY() + this.getHeight()))) {
                if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight()) >= ((this.getY()))) {
                    if (rightSide) {
                        mainBRICKS.turn_Xlow = false;
                        mainBRICKS.turn_Xup = true;

                        Scenario.scenarii(Scenario.scenario);
//                      System.out.println("LEFT : " + mainBRICKS.levelTransition);
                    }
                }
            }
        }
// ********** mBrick LEFT
        if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth()) >= (this.getX())) { //
            leftSide = false; // System.out.println("right = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            leftSide = true;
        }

        if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth() + 4) > (this.getX())) {
            if (mainBRICKS.mBrick.getY() <= ((this.getY() + this.getHeight()))) {
                if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight()) >= ((this.getY()))) {
                    if (leftSide) {
                        mainBRICKS.turn_Xlow = true;
                        mainBRICKS.turn_Xup = false;

//                       System.out.println("RIGHT : " + mainBRICKS.levelTransition);
                        Scenario.scenarii(Scenario.scenario);
                    }
                }
            }
        }
// **********  BOTTOM SIDE of mBrick
        if ((mainBRICKS.mBrick.getY()) <= (this.getY() + this.getHeight())) { //
            bottomSide = false; // System.out.println("top = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            bottomSide = true;
        }

        if ((mainBRICKS.mBrick.getY() - 4) <= (this.getY() + this.getHeight())) {
            if (mainBRICKS.mBrick.getX() <= ((this.getX() + this.getWidth()))) {
                if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth()) >= ((this.getX()))) {
                    if (bottomSide) {
                        mainBRICKS.turn_Ylow = false;
                        mainBRICKS.turn_Yup = true;

//                       System.out.println("TOP : " + mainBRICKS.levelTransition);
                        Scenario.scenarii(Scenario.scenario);
                    }
                }
            }
        }
        // **********  TOP SIDE of mBrick
        if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight()) >= (this.getY())) { //
            topSide = false; // System.out.println("top = false");
            // turn off  EFFECT of disappearring
//                              touchRepaint = false;
        } else {
            topSide = true;
        }

        if ((mainBRICKS.mBrick.getY() + mainBRICKS.mBrick.getHeight() + 4) >= (this.getY())) {
            if (mainBRICKS.mBrick.getX() <= ((this.getX() + this.getWidth()))) {
                if ((mainBRICKS.mBrick.getX() + mainBRICKS.mBrick.getWidth()) >= ((this.getX()))) {
                    if (topSide) {
                        mainBRICKS.turn_Ylow = true;
                        mainBRICKS.turn_Yup = false;

                        Scenario.scenarii(Scenario.scenario);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4};
//        boolean b = utils.contains(a, 4);
//        System.out.println(b);

        int[] all_bubbles = {5, 10, 25, 50, 100, 150, 300, 500};
        int[] arr = new int[3];

//        int cntBubblesInArray = 3;
//        for (int i = 0; i < cntBubblesInArray; i++) {
//
//            int choice = utils.getRandomNumberInRange(1, all_bubbles.length-1); // all_bubbles.length-1
//
//            boolean bool = utils.contains(arr, all_bubbles[choice]);
//
//            if (i == 0) {
//                arr[i] = all_bubbles[choice];
//            }
//
//            if (i > 0 && !bool) {
//                arr[i] = all_bubbles[choice];
//            } else if (i > 0 && bool) {
//                i--;
//            }
//        }
//
//        for (int i = 0; i < arr.length; i++) {
//            int j = arr[i];
//            System.out.println(j);
//        }
    }

}
