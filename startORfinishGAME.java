
package GAME_BRICKS;

import GRAPHIC2D_Software.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class startORfinishGAME implements KeyListener {

    JPanel  base;
    JWindow window;

    BRICKS mainBricks;

    startORfinishGAME(BRICKS main ) { // BRICKS main
        mainBricks = main;

        componentBevelBorder bevel = new componentBevelBorder(0, 2, "", 1, 1, new Font("Times New Roman", 1, 12),
                new Color(0, 100, 0), Color.white,
                new Color(190, 220, 220),
                new Color(100, 130, 130),
                new Color(150, 180, 180));
        // help customize form of Window
        window = bevel.getWindowForm();
//        window.setLayout(null);
        bevel.setFloatable(true);
        window.setMinimumSize(new Dimension(200, 120)); // 361, 300
        window.setPreferredSize(new Dimension(200, 120));
        window.setAlwaysOnTop(true);
        center();

 // --------------  base panel -------------------------------------------------
        setContentPanel();
        
        base.setMinimumSize(window.getMinimumSize());
        base.setPreferredSize(window.getPreferredSize());
        base.setBorder(bevel);
        // allow drag JWIndow
        bevel.getDnDMouseListener(base);
        // set Behavior for control buttons in bevelBorder :
        bevel.getControlButtonsBehavior(base , 1 , 1 ); // if first '1' hide JWindow if '0' close JWindow

        buttons  exit = new buttons("Exit");
        buttons start = new buttons("New");

        newGameAction startNewGame = new newGameAction("Exit", null);
        exit.setName("Exit");
        exit.getActionMap( ).put("Exit", startNewGame);
        exit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke("Z"), "Exit");
//        exit.requestFocusInWindow();
//        exit.setFocusable(true);
        exit.setButtonAction(0);
        start.setButtonAction(1);
        start.setBounds(20, 50 , 72, 37);
        exit.setBounds(105, 50, 72, 37);
        base.add(exit);
        base.add(start);
//------------------------------------------------------------------------------

//        base.setFocusable(true);
        window.setFocusable(true);
        window.setFocusableWindowState(true);
//        window.addKeyListener(this);
        window.add(base);
        window.setVisible(true);
    }

     public void center() {
        Dimension screenSize = mainBricks.frame.getSize();
        Dimension frameSize = window.getSize();
        int x = (mainBricks.frame.getLocation().x + screenSize.width ) /2;
        int y = (mainBricks.frame.getLocation().y + screenSize.height ) /2;
        window.setLocation(x, y);
    }

    protected JPanel setContentPanel() {
         base = new JPanel(null)
         {
            int w = window.getWidth()-1;
            int h = window.getHeight()-1;
         public void paintComponent(Graphics g) {
//             super.paintComponent(g);
             Graphics2D g2 = (Graphics2D) g;
             Rectangle2D rect = new Rectangle2D.Double( 0, 0, w, h   );

             g2.setPaint( new GradientPaint( 0, 0, new Color(255,242,226) , w , h, new Color(213,255,122), false) );
             g2.fill(rect);
             g2.setColor(Color.BLUE);
             g2.draw(rect);
             }

         };
        return base;
    }

    class newGameAction extends AbstractAction{

        newGameAction(String name, Icon Icon ){
            super(name, Icon);
        }

        public void actionPerformed(ActionEvent ae)
        {
             System.out.println("PRESSED");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
              int keyCode = e.getKeyCode();
           if( keyCode == KeyEvent.VK_A ){
               System.out.println("PRESSED");
           }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class buttons extends JComponent  implements MouseListener  {
          boolean focus = false;
          String text;
          int action ;
        buttons(String str) {
          text = str;
          this.addMouseListener( this );
//          setNewGameAction();
        }

        private void setButtonAction(int a){
            action = a;
        }

        private int getButtonAction() {
            return action;
        }

//        private void setNewGameAction() {
//            this.setName(text);
//            KeyStroke VK_RIGHT = KeyStroke.getKeyStroke(KeyEvent.VK_T,  0, true);
//            InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//            ActionMap actionMap = this.getActionMap();
//            inputMap.put(VK_RIGHT, text);
//            actionMap.put(text, new newGameAction(text , null) );
//        }

         public void paintComponent(Graphics g) {
             Graphics2D g2 = (Graphics2D) g;
             g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
             RoundRectangle2D rect = new RoundRectangle2D.Double( 0, 0, 70, 35 , 25 ,20  );
             int w = (int) rect.getWidth();
             int h = (int) rect.getHeight();
             g2.setStroke(new BasicStroke(0.5f));

             if( !focus )
             {
              g2.setPaint( new GradientPaint( 0, 0, new Color(84,255,124) , 0 , h, new Color(255,243,15), false) );// 192,255,158
             } else {
              g2.setPaint( new GradientPaint( 0, 0, new Color(84,255,124) , 0 , h, Color.RED, false) );
             }
              g2.fill(rect);

             AffineTransform at = new AffineTransform();
             Shape outline_rect = at.createTransformedShape(rect);
             Area  outline = new Area(outline_rect);
            
             int deep  = 2;
              for (int k = 0; k < deep; k+= 1) {
             at.translate(0, k);
             g2.setStroke(new BasicStroke(1.0f));
             g2.transform(at);
             g2.setColor(new Color(150,195,255));
             g2.draw(outline_rect);
             }
             

          float xLoc = (float) (  rect.getX() + 10   );
          float yLoc = (float) ( ( rect.getY() + rect.getHeight() - 10 ) );

          Font font1 = new Font("Book Antiqua", Font.PLAIN, 70);
          Font font = new Font("Monotype Corsiva", Font.BOLD, 26);
          TextLayout textLayout = new TextLayout(text, font, g2.getFontRenderContext() );
          g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ));
          g2.setPaint(Color.black);
          textLayout.draw(g2, xLoc, yLoc);
        }

           @Override
                public void mouseClicked(MouseEvent e) {  }
                @Override
                public void mousePressed(MouseEvent e) {
                    if( getButtonAction() == 0 ){
                    System.exit(0);
                    }
                    if(getButtonAction() == 1)
                    {
                       mainBricks.statesOfPosition.emptyAllStates();
                       
                       if (mainBricks.thrObj.isAlive()) {
                           mainBricks.start = false;
                       synchronized (mainBricks.thrObj) {
                           mainBricks.thrObj.notify();
                       }
                     }
                     mainBricks.restartMainThread();
                     mainBricks.LEVEL = 0;
                     mainBricks.scenario.resetTransitionLevel();
                     
                     mainBricks.isStartNewGamePressed = true;
                     mainBricks.setInitConditionForNextLevel();
                     mainBricks.startFencePanel();
                     mainBricks.isStartNewGamePressed = false;
                     
                     mainBricks.scenario.level_0_10.clearPanelForNextLevel();
                     mainBricks.frame.repaint();
                     mainBricks.scenario.changeLevel(); // switch to the next level
                     
                     // New game
//                     mainBricks.shutterPanel.setLEVEL_CNT(100);
//                     mainBricks.shutterPanel.turnOn = true;
//                     mainBricks.shutterThread = new Thread(mainBricks.shutterPanel.addPiece);
//                     mainBricks.shutterThread.start();
                     
                     mainBricks.thrObj = new Thread(mainBricks.thr);
                     mainBricks.start = true;
                     mainBricks.thrObj.start();

                     mainBricks.timeDelayForAddingSchemaTouch_0_10 = true;

                     //System.out.println("start  " + this.getTransitionLevel());
                       
//                       mainBricks.start = true;
//                       Thread thrr = new Thread(mainBricks.thr);
//                       thrr.start();
//                       mainBricks.scenario.resetTransitionLevel();

//                       System.out.println("start  " + LEVEL  );
           // *** reset all labels values to zero  ***
                      // reset LIFEs to max value
                       mainBricks.cntLife  = 3;
                      // reset Score to zero
                       mainBricks.life.setText("Life : " + mainBricks.cntLife);
                       mainBricks.cntScore = 0;
                       mainBricks.score.setText("Score : " + mainBricks.cntScore);
                      // reset level to zero
                       mainBricks.cntLevel = 0;
                       mainBricks.level.setText("Level : " + mainBricks.cntLevel);

                       mainBricks.turn_Xup = true;
                       mainBricks.turn_Ylow = true;
                       mainBricks.turn_Xlow  = false;
                       mainBricks.turn_Yup = false;

 // it need to pause 'shutterThread' and client should choice 'New' or 'Exit'.
 // if choice have not done 'choiceNewOrExit = true' and 'shutterThread' is paused.
 // After choice is done 'choiceNewOrExit = false' and 'shutterThread' can finish own job.
                       if( mainBricks.choiceNewOrExit )
                       {
                          mainBricks.choiceNewOrExit = false;
                          mainBricks.shutterPanel.suspend.set(false);
                          // Resume
                          synchronized(mainBricks.shutterThread) {
                          mainBricks.shutterThread.notify();
                            }
                       }

                       window.setVisible(false);
                       
                    // hide menu when game is running
                    mainBricks.mainMenu.showMenuButton.setVisible(true);
                    mainBricks.mainMenu.hideMenuButton.setVisible(false);
                    mainBricks.mainMenu.menuPanel.setVisible(false);
                       
                       mainBricks.frame.repaint();
                  //  System.out.println("Action");
//-------------------------------------------------------------------------------                       

                    }
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {  }
                @Override
                public void mouseEntered(MouseEvent e) {
                       System.out.println("Enterd");
                       focus = true;
                       repaint();

                }
                @Override
                public void mouseExited(MouseEvent e) {
                       System.out.println("Exit");
                       focus = false;
                      repaint();
                }
    }
       

    public static void main(String[] args) {
//         new startORfinishGAME();
    }
}
