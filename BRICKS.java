package GAME_BRICKS;

import GRAPHIC2D_Software.componentBevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.text.AttributedString;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class BRICKS implements KeyListener {

    JFrame frame;
    JPanel menuPanel;
    // buttons  showMenuButton,exitButton;  

    String moveDirection = "";
    boolean turnX = false;    // for moving to right Direction and isolation
    boolean turn_Xup = false; // for increasing X coordinate
    boolean turn_Yup = false; // for increasing Y coordinate
    boolean turn_Xlow = false; // for decreasing X coordinate
    boolean turn_Ylow = false; // for decreasing Y coordinate
    int xPos;
    int yPos = 2;

    boolean start = false;
    AtomicBoolean suspend;
    Thread thrObj;
    Thread shutterThread;
    // it need to pause 'shutterThread' and client should choice 'New' or 'Exit'.
    // if choice have not done 'choiceNewOrExit = true' and 'shutterThread' is paused.
    // After choice is done 'choiceNewOrExit = false' and 'shutterThread' can finish own job.
    boolean choiceNewOrExit = false;

    boolean dynamicBrickError = false;

    // save Position 'controlBrick','dynamicBrick','staticBricks','bubbles'
    // in different levels 
    savedStates statesOfPosition;

    boolean isStartNewGamePressed = false;

    dynamicBrick mBrick;
    controlBrick cBrick;
    deadBricks dead;
//    staticBricks[] brs;
//    bubbleScore[] bubbles;

    customLabel life;
    customLabel score;
    customLabel level;
    customLabel myRecords;
    int cntLife = 2, cntScore = 0, cntLevel = 0;

    int levelTransition; // qty static bricks in each level
    int LEVEL = 0; // count for the next level of game

    shutterPanel shutterPanel;
    fencePanel fencePanel;
    Thread fenceThread;

    presentPanel promo;

    startORfinishGAME startORfinishGAME; // window

    SCENARIO_LEVELS scenario;

    basicPanel basic;

    menu mainMenu;

    updateTableRecords update_tableRecords;

    startORfinishGAME startORfinish_game;

    BRICKS() {
        frame = new JFrame("BRICKS");
//        super("FLYING_AND_DRAWING");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        

//        basic.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));       
        //-------------------------------------------------------------------------------------
//        componentBevelBorder bevel = new componentBevelBorder(0, 2, "BRICKS", 1, 1, new Font("Times New Roman", 1, 12),
//                new Color(0, 100, 0), Color.white,
//                new Color(190, 220, 220),
//                new Color(100, 130, 130),
//                new Color(150, 180, 180));
        // help customize form of JFrame
//        frame =  bevel.getJFrameForm();
        frame.setUndecorated(true);
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(190, 220, 220), 5, true));
//        bevel.setFloatable(false);
//------------------------------------------------------------------------------------------
        frame.setSize(1015, 600);
        frame.setMinimumSize(new Dimension(1015, 600)); // 361, 300
        frame.setPreferredSize(new Dimension(1015, 600));
        // frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 255, 255));

        basic = new basicPanel(this);
        basic.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
//        basic.setBorder(bevel);
        // set Behavior for control buttons in bevelBorder :
        // if first '1' hide JWindow if '0' close JWindow
        // if second : '0' apply for frame , '1' apply for window
//        bevel.getControlButtonsBehavior(basic , 0  , 0 );

        center();
//      save Position 'controlBrick','dynamicBrick','staticBricks','bubbles'
//      in different levels         
        statesOfPosition = new savedStates(this);
// --------------------------- labels -------------------------------------------         
        life = new customLabel("Life : " + cntLife, 0.7f, BRICKS.this);
        score = new customLabel("Score : " + cntScore, 0.7f, BRICKS.this);
        level = new customLabel("Level : " + cntLevel, 0.7f, BRICKS.this);
//        myRecords   = new customLabel("R" , 0.7f , BRICKS.this);
//        myRecords.callTableOfRecords(true);
        life.addMouseListener(callSettingsWindow(life));
        score.addMouseListener(callSettingsWindow(score));
        level.addMouseListener(callSettingsWindow(level));
        
//        score.startThreadDecrease();

        score.setBounds(30, 50, 206, 26);
        life.setBounds(30, 80, 106, 26);
        level.setBounds(30, 110, 106, 26);
//----------------------------- upper menu ---------------------------------------------  
        mainMenu = new menu(this); // main menu class

        suspend = new AtomicBoolean(false);
        thrObj = new Thread(thr);

        // for trnsition levels
        scenario = new SCENARIO_LEVELS(this);
//------------------ shutterPanel, fencePanel
        shutterPanel = new shutterPanel(BRICKS.this);
        shutterPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        shutterPanel.setOpaque(false);
        frame.add(shutterPanel);
        // int type, int cycles, String direction, int speed, int delay 
//        fencePanel = new fencePanel(BRICKS.this, 1, 1320, "right", 3, 200);
        //fencePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//        fencePanel.setOpaque(false);
//        frame.add(fencePanel);

        mBrick = new dynamicBrick(this);
        mBrick.setBounds(470, 400, 41, 41);
        basic.add(mBrick);

        cBrick = new controlBrick(BRICKS.this);
        cBrick.setBounds(470, 530, 91, 26);
        basic.add(cBrick);

        setInitConditionForNextLevel();

//        dead = new deadBricks(this, -25, 13, 3);
//        dead.setTexturePaint(1);
//        dead.setBounds(490, 350, 66, 31);
//        dead.setBubblesLocation(490, 350);
       // basic.add(dead);
        
        bubbleScore[] b = {new bubbleScore(-5),new bubbleScore(-10),new bubbleScore(-25),
                           new bubbleScore(5),new bubbleScore(10),new bubbleScore(25),new bubbleScore(50),
                           new bubbleScore(100),new bubbleScore(150),new bubbleScore(300),new bubbleScore(500)};
        int x = 55;
//        for (int i = 0; i < b.length; i++) {
//            x += 50;
//            b[i].setBounds(x, 75, 112, 82);
//            b[i].setVisible(true); 
//           // basic.add(b[i]);
//        }

        scenario.changeLevel();

        frame.add(basic);
        frame.addKeyListener(this);
        frame.setVisible(true);

        frame.requestFocus();
        frame.requestFocusInWindow();
        // frame.setAlwaysOnTop(true); 
        frame.toFront();

        promo = new presentPanel(this);
        frame.add(promo);
        promo.startPresentPanel();

        choiceMoveDirection();

        // window for last choice ->  start new game or exit  
        startORfinish_game = new startORfinishGAME(this);
        startORfinish_game.window.setVisible(false);
    }

    private MouseAdapter callSettingsWindow(final JLabel label) {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainMenu.submenuSettingsWindow.settings.window.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        };
        return ma;
    }

    public int getLife() {
        return cntLife;
    }

    public void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x, y);
    }

    public JPanel getBasicPanel() {
        return basic;
    }

    public int getTransitionLevel() {
        return levelTransition;
    }

    public void setInitConditionForNextLevel() {
        int screenWidth = BRICKS.this.getBasicPanel().getWidth() - 60; //
        int screenHeight = BRICKS.this.getBasicPanel().getHeight() - 80;
        xPos = 470;
        yPos = 420;
        mBrick.setLocation(xPos, yPos);
        // save position 'dynamicBrick' in order to it was possible
        // save,download and remove games
        statesOfPosition.save_stateDynamicBrickPosition(xPos, yPos);
        cBrick.setLocation(screenWidth / 2, screenHeight);
        // save position 'controlBrick' in order to it was possible
        // save,download and remove games
        statesOfPosition.save_stateControlBrickPosition(xPos, yPos);
        //
        if (fencePanel == null) {
            // BRICKS mainBRICKS, int type, int cycles, int speed, int delay    
            fencePanel = new fencePanel(BRICKS.this, 1, 1320, 3, 200);
            fencePanel.setOpaque(false);
            getBasicPanel().add(fencePanel);
            //  System.out.println("fence null");
            // startFencePanel();
        } else {
            stopFencePanel();
            getBasicPanel().remove(fencePanel);

            fencePanel = new fencePanel(BRICKS.this, 1, 1320, 3, 200);
            fencePanel.setOpaque(false);
            getBasicPanel().add(fencePanel);
            //
//            if (shutterThread != null) 
//            if (!shutterThread.isAlive()) 
            if (!isStartNewGamePressed) {
                startFencePanel();
            }

//            System.out.println("fence nonnull");
        }

        System.out.println(moveDirection);

        setMoveDirection(this.moveDirection);
        // reset dynamic brick  start to move in right direction
//        turn_Xup = true;
//        turn_Ylow = true;
//        turn_Xlow = false;
//        turn_Yup = false;
    }

    protected String choiceMoveDirection() {
        int choice = utils.getRandomNumberInRange(1, 10);

        if ((choice == 1) || (choice == 3) || (choice == 5) || (choice == 7) || (choice == 9)) {
            this.moveDirection = "right";
            return this.moveDirection;
        }
        if ((choice == 2) || (choice == 4) || (choice == 6) || (choice == 8) || (choice == 10)) {
            this.moveDirection = "right";
            return this.moveDirection;
        }
        return this.moveDirection;
    }

    private void setMoveDirection(String direction) {

        if (direction == "left") {
            // reset dynamic brick  start to move in left direction
            turn_Xup = false;
            turn_Ylow = true;
            turn_Xlow = true;
            turn_Yup = false;
        }
        if (direction == "right") {
            // reset dynamic brick  start to move in right direction
            turn_Xup = true;
            turn_Ylow = true;
            turn_Xlow = false;
            turn_Yup = false;
        }
    }

    boolean timeDelayForAddingSchemaTouch_0_10 = false;
    boolean timeDelayForAddingSchemaTouch_11_20 = false;
    boolean timeDelayForAddingSchemaTouch_21_30 = false;
    int screenWidth;
    int screenHeight;
    Thread thr = new Thread() {
        @Override
        public synchronized void run() {
            screenWidth = BRICKS.this.getBasicPanel().getWidth() - 60; // -80
            screenHeight = BRICKS.this.getBasicPanel().getHeight() - 80; // -115
            // System.out.println(screenWidth + "  " +  screenHeight );

            xPos = mBrick.getX();
            yPos = mBrick.getY();

            // right
//            turn_Xup = true;
//            turn_Ylow = true;
            //left
//            turn_Ylow = true;
//            turn_Xlow = true;
            setMoveDirection(moveDirection);

            //  boolean haveRecords = false;
            while (start) {
                try {

                    sleep(scenario.getSpeed()); // init 50  getSpeed()
                    
                    System.out.println(turn_Xup + " " + turn_Xlow + " " + turn_Yup + " " + turn_Ylow);

                    // ******** in order to 'pause' this thread
                    if (suspend.get()) {
                        synchronized (thrObj) {
                            // Pause
                            try {
                                thrObj.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }

                    // if Game is finished and client should do choice from  'startOrfinishGAME' window :
                    // 'New' or 'Exit' . After choice is done -> shutterThread should finish own thread
                    // and start new Game
                    if (shutterThread != null) {
                        if (shutterThread.isAlive()) {
                            sleep(5000);
                        }   // Thread.currentThread()
                    }
                    cBrick.SCHEMA_TOUCH();

//                    dead.SCHEMA_TOUCH();
                    //
                    if (LEVEL < 11) {
                        int divider = 0;
                        if (timeDelayForAddingSchemaTouch_0_10) {
                            sleep(100); // Thread.currentThread().
                        }
                        for (int k = 0; k < scenario.level_0_10.getQtyBricks(); k++) {
                            scenario.level_0_10.bricks[k].SCHEMA_MOVE();
                            //  System.out.println("bricks: " + scenario.level_0_10.bricks[k]);
                        }

                        timeDelayForAddingSchemaTouch_0_10 = false;
                    }

                    if ((LEVEL >= 11) && (LEVEL < 21)) {// (LEVEL >= 11)&&(LEVEL < 21)
                        int divider = 0;
                        if (!timeDelayForAddingSchemaTouch_11_20) {
                            sleep(100); // Thread.currentThread().
                        }
                        for (int k = 0; k < scenario.level_11_20.getQtyBricks(); k++) {
                            scenario.level_11_20.bricks[k].SCHEMA_MOVE();
                        }
                        timeDelayForAddingSchemaTouch_11_20 = true;
                    }

                    if ((LEVEL >= 21) && (LEVEL < 31)) {// (LEVEL >= 21)&&(LEVEL < 31)
                        int divider = 0;
                        if (!timeDelayForAddingSchemaTouch_21_30) {
                            sleep(100); // Thread.currentThread().
                        }
                        for (int k = 0; k < scenario.level_21_30.getQtyBricks(); k++) {
                            scenario.level_21_30.bricks[k].SCHEMA_MOVE();
                        }
                        timeDelayForAddingSchemaTouch_21_30 = true;
                    }
//                    System.out.println("LEVEL: " + LEVEL + "  " +  scenario.getSpeed() );

                    // right
                    yPos -= 3;
                    // right and up   (+)
                    if (turn_Xup && turn_Ylow) {
                        xPos += 3; // 6
//                        yPos -= 5;
                        if (yPos <= 0) {  // TOP SIDE: 1+     6+
                            yPos = 0;
                            turn_Ylow = false;
                            turn_Yup = true;
                        }
                        if (xPos >= screenWidth) { // RIGHT SIDE: 3+
                            xPos = screenWidth;
                            turn_Xup = false;
                            turn_Xlow = true;
                        }
                    }

                    // right and low   (+)
                    if (turn_Xup && turn_Yup) {
                        xPos += 3;  // 6
                        yPos += 6; //20
                        if (yPos >= screenHeight) {
                            yPos = screenHeight;
                            turn_Yup = false;
                            turn_Ylow = true;
                            // start Decrease label thread
//                            life.startThreadDecrease();
                            // decrease LIFE
                            cntLife--;
                            life.setText("life : " + cntLife);

                            dynamicBrickError = true;
                            Thread thr1 = new Thread(mBrick.changeColor);
                            thr1.start();

                            // stop main thread  !
                            start = false;
                            sleep(1500);
                            start = true;
                            //thr.start();

                        }
                        if (xPos >= screenWidth) { // RIGHT SIDE: 3+
                            xPos = screenWidth;
                            turn_Xup = false;
                            turn_Xlow = true;
                        }
                    }

                    //  from UP to --> left and bottom
                    if (turn_Xlow && turn_Yup) {
                        xPos -= 3;
                        yPos += 6; // 15
                        if (yPos >= screenHeight) { // BOTTOM SIDE: 5-
                            yPos = screenHeight;
                            turn_Yup = false;
                            turn_Ylow = true;
                            // start Decrease label thread
//                            life.startThreadDecrease();
                            // decrease LIFE
                            cntLife--;
                            life.setText("life : " + cntLife);

                            dynamicBrickError = true;
                            Thread thr1 = new Thread(mBrick.changeColor);
                            thr1.start();
                            // stop main thread  !
                            start = false;
                            sleep(1500);
                            start = true;
                            //thr.start();
                        }
                        if (xPos <= 0) { // LEFT SIDE:  may  be to
                            xPos = 0;
                            turn_Xlow = false;
                            turn_Xup = true;
                        }
                    }

                    // from BOTTOM to --> left and up  (-)
                    if (turn_Xlow && turn_Ylow) {
                        xPos -= 3;
//                        yPos -= 3; //
                        if (yPos <= 0) {  // TOP SIDE: 4-
                            yPos = 0;
                            turn_Ylow = false;
                            turn_Yup = true;
                        }
                        if (xPos <= 0) {  // LEFT SIDE:
                            xPos = 0;
                            turn_Xlow = false;
                            turn_Xup = true;
                        }
                    }

                    // ***************************** if LIFEs = 0 then print --> Game Over *************
                    if (cntLife == 0) {
                        sleep(1800); // Thread.currentThread().
                        // dynamicBrickError = true;

                        stopFencePanel();
                        // Start thread -> 'Game over'
                        shutterPanel.setLEVEL_CNT(0);
                        shutterPanel.turnOn = true;
                        shutterThread = new Thread(shutterPanel.addPiece);
                        shutterThread.start();
                        // for choice start new Game OR Exit from Game
                        // and pause --> 'shutterThread'
                        choiceNewOrExit = true;
                        frame.repaint();

                        xPos = screenWidth / 2;
                        yPos = screenHeight / 2;
                        mBrick.setLocation(xPos, yPos);
                        // save position 'dynamicBrick' in order to it was possible
                        // save,download and remove games
                        statesOfPosition.save_stateDynamicBrickPosition(xPos, yPos);

                        sleep(5000); // Thread.currentThread().
                        scenario.resetTransitionLevel();
                        start = false;
                        thr.interrupt();

                        // if have new Record                        
                        update_tableRecords = new updateTableRecords(BRICKS.this);
//                      if( !table.records.window.isVisible()  )
                        //  startORfinishGAME = new startORfinishGAME(BRICKS.this);
                    }

                    // System.out.println("levelTransition : " + getTransitionLevel());
                    // ********** if LEVEL is finished  succsessfully !!! *************************
                    // if qty staticBrick = 0 it means LEVEL is passed
                    if (getTransitionLevel() == 0) {

                        LEVEL++;

                        if (LEVEL == 1) {
                            scenario.LEVEL_SCENARIO(1);
//                            // clear all states control brick, dynamic brick, static bricks, bubbles, turnX ...
//                            statesOfPosition.emptyAllStates();
//                            // output to screen  label "Level 1" from shutterPanel class
//                            shutterPanel.setLEVEL_CNT(1);
//                            shutterPanel.turnOn = true;
//                            Thread thr = new Thread(shutterPanel.addPiece);
//                            thr.start();
//                            frame.repaint();
//
//                            stopFencePanel();
//
//                            sleep(15000); // Thread.currentThread().
//
//                            scenario.level_0_10.clearPanelForNextLevel();
//                            frame.repaint();
//                            scenario.changeLevel(); // switch to the next level
//                            scenario.resetTransitionLevel();
//                            setInitConditionForNextLevel();
                        } else if (LEVEL == 2) {
                            scenario.LEVEL_SCENARIO(2);
                        } else if (LEVEL == 3) {
                            scenario.LEVEL_SCENARIO(3);
                        } else if (LEVEL == 4) {
                            scenario.LEVEL_SCENARIO(4);
                        } else if (LEVEL == 5) {
                            scenario.LEVEL_SCENARIO(5);
                        } else if (LEVEL == 6) {
                            scenario.LEVEL_SCENARIO(6);
                        } else if (LEVEL == 7) {
                            scenario.LEVEL_SCENARIO(7);
                        } else if (LEVEL == 8) {
                            scenario.LEVEL_SCENARIO(8);
                        } else if (LEVEL == 9) {
                            scenario.LEVEL_SCENARIO(9);
                        } else if (LEVEL == 10) {
                            scenario.LEVEL_SCENARIO(10);
                        } else if (LEVEL == 11) {
                            scenario.LEVEL_SCENARIO(11);
                        } else if (LEVEL == 12) {
                            scenario.LEVEL_SCENARIO(12);
                        } else if (LEVEL == 13) {
                            scenario.LEVEL_SCENARIO(13);
                        } else if (LEVEL == 14) {
                            scenario.LEVEL_SCENARIO(14);
                        } else if (LEVEL == 15) {
                            scenario.LEVEL_SCENARIO(15);
                        } else if (LEVEL == 16) {
                            scenario.LEVEL_SCENARIO(16);
                        } else if (LEVEL == 17) {
                            scenario.LEVEL_SCENARIO(17);
                        } else if (LEVEL == 18) {
                            scenario.LEVEL_SCENARIO(18);
                        } else if (LEVEL == 19) {
                            scenario.LEVEL_SCENARIO(19);
                        } else if (LEVEL == 20) {
                            scenario.LEVEL_SCENARIO(20);
                        } else if (LEVEL == 21) {
                            scenario.LEVEL_SCENARIO(21);
                        } else if (LEVEL == 22) {
                            scenario.LEVEL_SCENARIO(22);
                        } else if (LEVEL == 23) {
                            scenario.LEVEL_SCENARIO(23);
                        } else if (LEVEL == 24) {
                            scenario.LEVEL_SCENARIO(24);
                        } else if (LEVEL == 25) {
                            scenario.LEVEL_SCENARIO(25);
                        } else if (LEVEL == 26) {
                            scenario.LEVEL_SCENARIO(26);
                        } else if (LEVEL == 27) {
                            scenario.LEVEL_SCENARIO(27);
                        } else if (LEVEL == 28) {
                            scenario.LEVEL_SCENARIO(28);
                        } else if (LEVEL == 29) {
                            scenario.LEVEL_SCENARIO(29);
                        } else if (LEVEL == 30) {
                            scenario.LEVEL_SCENARIO(30);
                            start = false;
                        }
                        frame.repaint();
                        System.out.println("levelTransition : " + levelTransition + "LEVEL : " + LEVEL);
                    }

                    mBrick.setLocation(xPos, yPos);
                    // save position 'dynamicBrick' in order to it was possible
                    // save,download and remove games
                    statesOfPosition.save_stateDynamicBrickPosition(xPos, yPos);

                    //  System.out.println("xPOs : " + xPos + "yPos : " + yPos);
                    frame.repaint();
                } catch (InterruptedException ex) {
                }
            }
        }
    };

    protected void touching() {
        timeDelayForAddingSchemaTouch_0_10 = false;

        for (int k = 0; k < scenario.level_0_10.getQtyBricks(); k++) {

            scenario.level_0_10.bricks[k].SCHEMA_MOVE();
        }

    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Presentation panel
        if (!promo.isVisible()) {
            // S without CTRL, ALT, SHIFT
            if (keyCode == KeyEvent.VK_S && ((e.getModifiers() & KeyEvent.CTRL_MASK) == 0)) {
                try {
                    startNewGame();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            // continue existing game
            if (keyCode == KeyEvent.VK_X) {
                try {
                    startGame();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            // when game need pause       
            if (keyCode == KeyEvent.VK_P) {
                pauseGame();
            }
        // save game
            // CTRL + s 
            if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
//            if (thrObj.isAlive()) { 
//            start = false;
//            synchronized (thrObj) { thrObj.notify(); }
//            // thr.interrupt();
//            }
                stopFencePanel();
                if (thrObj.isAlive()) {
                    // stop main thread in order to input name user
                    suspend.set(true);
                    synchronized (thrObj) {
                        thrObj.notify();
                    }

                    mainMenu.savegame.iGamerName.frame.setVisible(true);
                }
            }
        // download game
            // CTRL + d
            if ((e.getKeyCode() == KeyEvent.VK_D) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                mainMenu.downloadWindow();
            }
        // remove game
            // CTRL + r
            if ((e.getKeyCode() == KeyEvent.VK_R) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                mainMenu.removeWindow();
            }
        // Menu Actions
            // show settings
            if ((e.getKeyCode() == KeyEvent.VK_T) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                mainMenu.submenuSettingsWindow.settings.window.setVisible(true);
            }
            // show statistics
            if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                mainMenu.Statistics.setWindowVisibility(true);
            }
            // show help
            if ((e.getKeyCode() == KeyEvent.VK_H) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                mainMenu.Help.setWindowVisibility(true);
            }
        }
        //
        if ((e.getKeyCode() == KeyEvent.VK_ENTER)) {
            // Help window
            if (mainMenu.Help.window.isVisible()) {
                mainMenu.Help.setWindowVisibility(false);
            }
            // About window
            if (mainMenu.About.window.isVisible()) {
                mainMenu.About.setWindowVisibility(false);
            }
            // Statistics window
            if (mainMenu.Statistics.window.isVisible()) {
                mainMenu.Statistics.setWindowVisibility(false);
            }
            // Presentation panel
            if (promo.isVisible()) {
                promo.stopPresentPanel();
                promo.setVisible(false);
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mainMenu.savegame.iGamerName.enter.setFocusable(false);
                }
            });

            // KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            if (mainMenu.savegame.iGamerName.tfield.hasFocus()) {
                System.out.println("outside: " + mainMenu.savegame.iGamerName.frame.isVisible());
            }
            // Savegame window
            if (mainMenu.savegame.iGamerName.frame.isVisible()) {

                Robot bot = null;
                try {
                    bot = new Robot();
                } catch (Exception failed) {
                    System.err.println("Failed instantiating Robot: " + failed);
                }
                int mask = InputEvent.BUTTON1_DOWN_MASK;
                bot.mouseMove(mainMenu.savegame.iGamerName.frame.getX() + 85,
                        mainMenu.savegame.iGamerName.frame.getY() + 125);

//                 bot.mouseMove(this.frame.getX() + 250,
//                              this.frame.getY() + 350);
//               this.frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                Cursor c = new Cursor(Cursor.HAND_CURSOR); 
                bot.mousePress(mask);
                bot.mouseRelease(mask);
                System.out.println("inside: " + mainMenu.savegame.iGamerName.frame.getX() + "  " + mask);
            }
        }
    }

    public void restartMainThread() {

        if (suspend.get()) { // true
            // if 'pause' was pressed
            if (pauseTime != null) {
                pauseTime.stop();
                shutterPanel.setLEVEL_CNT(-1);
            }
            // start mainThread !!!
            suspend.set(false);
            // Notify mainThread
            synchronized (thrObj) {
                thrObj.notify();
            }
        } else { // cancel pause
            // start mainThread !!!
            suspend.set(false);
            // Notify mainThread
            synchronized (thrObj) {
                thrObj.notify();
            }
        }
    }

    public void startNewGame() throws InterruptedException {

        statesOfPosition.emptyAllStates();

        if (thrObj.isAlive()) {
            start = false;
            synchronized (thrObj) {
                thrObj.notify();
            }
        }
        restartMainThread();

        LEVEL = 0;
        scenario.resetTransitionLevel();

        isStartNewGamePressed = true;
        setInitConditionForNextLevel();
        startFencePanel();
        isStartNewGamePressed = false;

        scenario.level_0_10.clearPanelForNextLevel();
        frame.repaint();
        scenario.changeLevel(); // switch to the next level
        // New game
        shutterPanel.setLEVEL_CNT(100);
        shutterPanel.turnOn = true;
        shutterThread = new Thread(shutterPanel.addPiece);
        shutterThread.start();

        thrObj = new Thread(thr);
        start = true;
        thrObj.start();

        timeDelayForAddingSchemaTouch_0_10 = true;

        System.out.println("start  " + this.getTransitionLevel());
        // *** reset all labels values to zero  ***
        // reset LIFEs to max value
        cntLife = 3;
        // reset Score to zero
        life.setText("Life : " + cntLife);
        cntScore = 0;
        score.setText("Score : " + cntScore);
        // reset level to zero
        cntLevel = 0;
        level.setText("Level : " + cntLevel);

        turn_Xup = true;
        turn_Ylow = true;
        turn_Xlow = false;
        turn_Yup = false;

        // it need to pause 'shutterThread' and client should choice 'New' or 'Exit'.
        // if choice have not done 'choiceNewOrExit = true' and 'shutterThread' is paused.
        // After choice is done 'choiceNewOrExit = false' and 'shutterThread' can finish own job.
        if (choiceNewOrExit) {
            choiceNewOrExit = false;
            shutterPanel.suspend.set(false);
            // Resume
            synchronized (shutterThread) {
                shutterThread.notify();
            }
        }

        if (startORfinishGAME != null) {
            startORfinishGAME.window.setVisible(false);
        }
        // hide menu when game is running
        mainMenu.showMenuButton.setVisible(true);
        mainMenu.hideMenuButton.setVisible(false);
        mainMenu.menuPanel.setVisible(false);

        frame.repaint();
    }

    public void startFencePanel() {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        fencePanel.turnOn = true;
                        fenceThread = new Thread(fencePanel.moving);
                        fenceThread.start();
                        // Notify mainthread
                        synchronized (fenceThread) {
                            fenceThread.notify();
                        }
                    }
                });
    }

    public void stopFencePanel() {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        if (fenceThread != null) {
                            if (fenceThread.isAlive()) {
                                fencePanel.turnOn = false;
                                // Notify mainthread
                                synchronized (fenceThread) {
                                    fenceThread.notify();
                                }
                            }
                        }
                    }
                });
    }

    public void startGame() throws InterruptedException {
        if (!thrObj.isAlive()) {

            thrObj = new Thread(thr);
            start = true;
            thrObj.start();

            scenario.resetTransitionLevel();

//            suspend.set(false);
//            // Resume
//            synchronized (thrObj) {
//                thrObj.notify();
//            }
        } else {
//          start = false; 
//          thr.interrupt();
//         // synchronized (thrObj) { thrObj.notify(); }
//          thrObj = new Thread(thr);
//          start = true;
//          thrObj.start(); 

        }
        // cancel pause 
        if (suspend.get()) {
            pauseTime.stop();
            shutterPanel.setLEVEL_CNT(-1);
            suspend.set(false);
            // Notify mainthread
            synchronized (thrObj) {
                thrObj.notify();
            }
            // System.out.println("if X: " + suspend);
            // start main thread 
        } else {
            suspend.set(false);
            // Notify mainthread
            synchronized (thrObj) {
                thrObj.notify();
            }
            // System.out.println("else X: " + suspend);
        }
        //
        startFencePanel();
        // hide menu when game is running
        mainMenu.showMenuButton.setVisible(true);
        mainMenu.hideMenuButton.setVisible(false);
        mainMenu.menuPanel.setVisible(false);
    }

    public void pauseGame() {
        if (!suspend.get()) {
            suspend.set(true);
            shutterPanel.setLEVEL_CNT(50);
            pauseTime = new Thread(shutterPanel.pause);
            pauseTime.start();
        }

        stopFencePanel();
        // hide menu when game is running
        mainMenu.showMenuButton.setVisible(true);
        mainMenu.hideMenuButton.setVisible(false);
        mainMenu.menuPanel.setVisible(false);

    }

    int trigger = 0;
    Thread pauseTime;

    @Override
    public synchronized void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new BRICKS();
    }
}
