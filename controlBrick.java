
package GAME_BRICKS;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

class controlBrick extends JComponent  {

       boolean leftSide = false;
       boolean topSide = false;
       boolean rightSide = false;
       boolean bottomSide = false;

       int screenY ;
       int screenX ;
       int screenWidth  ;
       int screenHeight  ;

       BRICKS mainBricks;

     controlBrick(BRICKS brick) {
         mainBricks = brick;

        screenY      = mainBricks.getBasicPanel().getLocation().y;
        screenX      = mainBricks.getBasicPanel().getLocation().x;
        screenWidth  = mainBricks.getBasicPanel().getWidth() - 85 ;
        screenHeight = mainBricks.getBasicPanel().getHeight() - 70 ;

         this.setName("controlBrick");
// Get the KeyStroke for our hot key
        KeyStroke VK_LEFT = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,  0, true);
// Get the input map for our component
// In this case we are interested in key strokes in the focussed window
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

// Map the key stroke to our "action key" (see below)
        inputMap.put(VK_LEFT, "controlBrick");


// Get the action map for our component
        ActionMap actionMap = this.getActionMap();

// Add the required action listener to out action map
        actionMap.put("controlBrick", new leftAction("controlBrick" , null) );

        this.setName("right");
        KeyStroke VK_RIGHT = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,  0, true);
        inputMap.put(VK_RIGHT, "right");
        actionMap.put("right", new rightAction("right" , null) );

//        this.setName("up");
//        KeyStroke VK_UP = KeyStroke.getKeyStroke(KeyEvent.VK_UP,  0, true);
//        inputMap.put(VK_UP, "up");
//        actionMap.put("up", new upAction("up" , null) );
//
//        this.setName("down");
//        KeyStroke VK_DOWN = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,  0, true);
//        inputMap.put(VK_DOWN, "down");
//        actionMap.put("down", new downAction("down" , null) );
     }

      class leftAction extends AbstractAction{

        leftAction(String name, Icon Icon ){
            super(name, Icon);
        }

        public void actionPerformed(ActionEvent ae)
        {
            int xPos  = controlBrick.this.getX()-20;
            int yPos  =  controlBrick.this.getY();

            // limitation of moving 'cBrick' from left side
            if( xPos <= screenX ) {
                xPos = 0 ;
                controlBrick.this.setLocation( xPos, yPos );
            }
            else {
                controlBrick.this.setLocation( xPos, yPos );
            }
            // save position 'controlBrick' in order to it was possible
            // save,download and remove games
            mainBricks.statesOfPosition.save_stateControlBrickPosition(xPos, yPos); 
        }
    }

       class rightAction extends AbstractAction  {

        rightAction(String name, Icon Icon ){
            super(name, Icon);
        }

        public void actionPerformed(ActionEvent ae)
        {
            int xPos  =  controlBrick.this.getX()+20;
            int yPos  =  controlBrick.this.getY();

            // limitation of moving 'cBrick' from right side
            if( xPos >= screenWidth ) {
                xPos = screenWidth ;
                controlBrick.this.setLocation( xPos, yPos );
            }
            else {
                controlBrick.this.setLocation( xPos, yPos );
            }
             // save position 'controlBrick' in order to it was possible
            // save,download and remove games
            mainBricks.statesOfPosition.save_stateControlBrickPosition(xPos, yPos);
        }
    }

       class upAction extends AbstractAction  {

        upAction(String name, Icon Icon ){
            super(name, Icon);
        }
        public void actionPerformed(ActionEvent ae)
        {
            int xPos  =  controlBrick.this.getX();
            int yPos  =  controlBrick.this.getY()-15;

            // limitation of moving 'cBrick' from right side
            if( yPos <= screenY ) {
                yPos = screenY ;
                controlBrick.this.setLocation( xPos, yPos );
            }
            else {
                controlBrick.this.setLocation( xPos, yPos );
            }
            // save position 'controlBrick' in order to it was possible
            // save,download and remove games
            mainBricks.statesOfPosition.save_stateControlBrickPosition(xPos, yPos);
        }
    }

       class downAction extends AbstractAction  {

        downAction(String name, Icon Icon ){
            super(name, Icon);
        }
        public void actionPerformed(ActionEvent ae)
        {
            int xPos  =  controlBrick.this.getX();
            int yPos  =  controlBrick.this.getY()+15;

            // limitation of moving 'cBrick' from right side
            if( yPos >= screenHeight ) {
                yPos = screenHeight ;
                controlBrick.this.setLocation( xPos, yPos );
            }
            else {
                controlBrick.this.setLocation( xPos, yPos );
            }
             // save position 'controlBrick' in order to it was possible
            // save,download and remove games
            mainBricks.statesOfPosition.save_stateControlBrickPosition(xPos, yPos);
        }
    }

      public void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          
          RoundRectangle2D main  =  new RoundRectangle2D.Double(0,0,90,25 ,10 ,10);
          Rectangle2D upper1  =  new Rectangle2D.Double(15,0,20, 2 );
          Rectangle2D upper2  =  new Rectangle2D.Double(55,0,20, 2 );
          Rectangle2D down1  =  new Rectangle2D.Double(15,23,20, 2 );
          Rectangle2D down2  =  new Rectangle2D.Double(55,23,20, 2 );

          Area mainArea = new Area(main);
          mainArea.subtract(new Area(upper1));
          mainArea.subtract(new Area(upper2));
          mainArea.subtract(new Area(down1));
          mainArea.subtract(new Area(down2));

          BufferedImage bi1 = Texture0();
          Rectangle rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
          TexturePaint tp1 = new TexturePaint(bi1, rr);

          g2.setStroke(new BasicStroke( 0.5f ));
          g2.setPaint(tp1);
          g2.fill(mainArea);
//          g2.setStroke(new BasicStroke( 2.0f ));
          g2.setPaint(Color.BLACK);
          g2.draw(mainArea);

          BufferedImage bi2 = Texture1();
          Rectangle rect = new Rectangle(0, 0, bi2.getWidth(), bi2.getHeight());
          TexturePaint tp2 = new TexturePaint(bi2, rect);

          RoundRectangle2D inside1  =  new RoundRectangle2D.Double(16,3,18,19 ,2 ,2);
          g2.setStroke(new BasicStroke( 1.0f ));
          g2.setPaint(tp2);
          g2.fill(inside1);
          g2.setPaint(Color.BLACK);
          g2.draw(inside1);

          RoundRectangle2D inside2  =  new RoundRectangle2D.Double(56,3,18,19 ,2 ,2);
          g2.setPaint(tp2);
          g2.fill(inside2);
          g2.setPaint(Color.BLACK);
          g2.draw(inside2);
      }

       private BufferedImage Texture0() {
            int size = 4;
            BufferedImage bi = new BufferedImage(  size , size , BufferedImage.TYPE_INT_ARGB) ;
            Graphics2D g2 = bi.createGraphics();
            g2.setPaint(Color.BLACK);
            g2.fillRect(0 , 0, size/2, size/2);
            g2.setPaint(Color.RED);
            g2.fillRect(size/2, 0, size, size/2);
            g2.setPaint(Color.RED);
            g2.fillRect(0, size/2, size/2, size);
            g2.setPaint(Color.BLACK);
            g2.fillRect(size/2, size/2, size, size);
            return bi;
        }

        private BufferedImage Texture1() {
            int size = 4;
            BufferedImage bi = new BufferedImage(  size , size , BufferedImage.TYPE_INT_ARGB) ;
            Graphics2D g2 = bi.createGraphics();
            g2.setPaint(Color.BLACK);
            g2.fillRect(0 , 0, size/2, size/2);
            g2.setPaint(Color.BLACK);
            g2.fillRect(size/2, 0, size, size/2);
            g2.setPaint(Color.LIGHT_GRAY);
            g2.fillRect(0, size/2, size/2, size);
            g2.setPaint(Color.LIGHT_GRAY);
            g2.fillRect(size/2, size/2, size, size);
            return bi;
        }

      

      protected void SCHEMA_TOUCH() {

          // **********  BOTTOM SIDE of mBrick
                     if ((mainBricks.mBrick.getY()+ mainBricks.mBrick.getHeight()  ) >= (this.getY() )) { //
                              bottomSide = false; // System.out.println("top = false");
                    } else  {
                         bottomSide = true;
                            }

         if(( mainBricks.mBrick.getY()+ mainBricks.mBrick.getHeight() + 10 ) >= ( this.getY() ) )
            if( mainBricks.mBrick.getX() <= (( this.getX() + this.getWidth()   )))
                if( (mainBricks.mBrick.getX()+ mainBricks.mBrick.getWidth()) >= ((   this.getX() )))
                    if( bottomSide )
                    {
                      mainBricks.turn_Ylow = true;
                      mainBricks.turn_Yup = false;
          }
      }
  }