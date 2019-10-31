
package GAME_BRICKS;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class shutterPanel extends JPanel {

      boolean turnOn = false;
      boolean changeState = false; // start display change state Game
      int cnt = -1;
      protected AtomicBoolean  suspend ;

      BRICKS mainBRICKS;

      shutterPanel( BRICKS main ) {
        mainBRICKS = main;

        suspend  = new AtomicBoolean(false);

        this.setLayout(null);
        this.setBounds(mainBRICKS.getBasicPanel().getX(), mainBRICKS.getBasicPanel().getY(),
                       mainBRICKS.getBasicPanel().getWidth(), mainBRICKS.getBasicPanel().getHeight());

        v = new Vector();

      }

         float alpha = 0.0f;
         float strAlpha = 0.0f; // for slow Dispaying text
//         Rectangle2D[] rect;
         Vector<Rectangle2D> v ;
       public void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setStroke(new BasicStroke( 0.6f ));

          BufferedImage bi1 = getTextureImage_0_10();
          Rectangle rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
          TexturePaint tp1 = new TexturePaint(bi1, rr);
          g2.setPaint(tp1);

          Rectangle2D rect = null;
          Rectangle2D r = null;
          for( int k = 0; k < v.size(); k++ ) {
            rect = (Rectangle2D) v.elementAt(k);

            double w = rect.getWidth();
            double h = rect.getHeight();
             r = rect;

            if( k % 2 == 0 ){
                alpha = 0.2f;
                g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, alpha ));
            }
            else {
                alpha = 0.35f;
                g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, alpha ));
            }

            g2.setPaint(tp1);
            g2.fill(rect);
            g2.setPaint(Color.BLACK);
            g2.draw(rect);
        }

        Font font1 = new Font("Book Antiqua", Font.PLAIN, 70);
        Font font2 = new Font("Monotype Corsiva", Font.PLAIN, 120);
        int xLoc = (this.getX()+ this.getWidth() ) / 2;
        int yLoc = (this.getY()+ this.getHeight() ) / 2;
        FontRenderContext frc = g2.getFontRenderContext();
        String text = "";
// ****** for LEVEL 0 - 10 **********
         BufferedImage bi2 = getTextureImageForLabel_0_10();
         Rectangle r2 = new Rectangle(0, 0, bi2.getWidth(), bi2.getHeight());
         TexturePaint tp2 = new TexturePaint(bi2, r2);

        if( getLEVEL_CNT() == 0 )
         {
        text = "Game over! ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 240 , yLoc );
         }
        if( getLEVEL_CNT() == 1 )
        {
        text = "Level 1  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
        if( getLEVEL_CNT() == 2 )
        {
        text = "Level 2  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 3 )
        {
        text = "Level 3  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
        if( getLEVEL_CNT() == 4 )
        {
        text = "Level 4  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
        if( getLEVEL_CNT() == 5 )
        {
        text = "Level 5  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
        if( getLEVEL_CNT() == 6 )
        {
        text = "Level 6  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 7 )
        {
        text = "Level 7  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 8 )
        {
        text = "Level 8  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 9 )
        {
        text = "Level 9  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 10 )
        {
        text = "Level 10  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp2);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
// ****** for LEVEL 11 - 20 **********
         BufferedImage bi3 = getTextureImageForLabel_11_20();
         Rectangle r3 = new Rectangle(0, 0, bi3.getWidth(), bi3.getHeight());
         TexturePaint tp3 = new TexturePaint(bi3, r3);
         
          if( getLEVEL_CNT() == 11 )
        {
        text = "Level 11  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 12 )
        {
        text = "Level 12  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 13 )
        {
        text = "Level 13  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 14 )
        {
        text = "Level 14  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 15 )
        {
        text = "Level 15  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 16 )
        {
        text = "Level 16  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 17 )
        {
        text = "Level 17  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 18 )
        {
        text = "Level 18  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 19 )
        {
        text = "Level 19  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 20 )
        {
        text = "Level 20  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp3);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
// ****** for LEVEL 21 - 30 **********
         BufferedImage bi4 = getTextureImageForLabel_21_30();
         Rectangle r4 = new Rectangle(0, 0, bi4.getWidth(), bi4.getHeight());
         TexturePaint tp4 = new TexturePaint(bi4, r4);

          if( getLEVEL_CNT() == 21 )
        {
        text = "Level 21  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 22 )
        {
        text = "Level 22  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 23 )
        {
        text = "Level 23  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 24 )
        {
        text = "Level 24  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 25 )
        {
        text = "Level 25  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
           if( getLEVEL_CNT() == 26 )
        {
        text = "Level 26  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 27 )
        {
        text = "Level 27  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
         if( getLEVEL_CNT() == 28 )
        {
        text = "Level 28  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 29 )
        {
        text = "Level 29  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }
          if( getLEVEL_CNT() == 30 )
        {
        text = "You Win !  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, strAlpha ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
        }

         // ***** for PAUSE *****
          if( getLEVEL_CNT() == 50 )
        {
              if(changePause == 0) {
        text = "Pause  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.7f ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc ); 
              }
              else  if(changePause == 1) {
        text = "Pause. ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.7f ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
              }
              else  if(changePause == 2) {
        text = "Pause.. ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.7f ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
              }
              else  if(changePause == 3) {
        text = "Pause...  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.7f ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
              }
              else  if(changePause == 4) {
        text = "Pause....  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.7f ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
              }
              else  if(changePause == 5) {
        text = "Pause.....  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.7f ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );
              }
        }
          
           if( getLEVEL_CNT() == 100 )
        {
        text = "New game  ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.7f ));
        g2.setPaint(tp4);
        g2.drawGlyphVector(gv, xLoc - 200, yLoc );  
        }
         
      }

       public void setLEVEL_CNT(int count) {
           cnt = count;
       }

       public int getLEVEL_CNT() {
            return cnt;
       }

       private BufferedImage getTextureImage_0_10() {
            int size = 16;
            BufferedImage bi = new BufferedImage(  size , size , BufferedImage.TYPE_INT_ARGB) ;
            Graphics2D g2 = bi.createGraphics();
            g2.setPaint(Color.BLUE);
            g2.fillRect(0 , 0, size/2, size/2);
            g2.setPaint(Color.RED);
            g2.fillRect(size/2, 0, size, size/2);
            g2.setPaint(Color.RED);
            g2.fillRect(0, size/2, size/2, size);
            g2.setPaint(Color.BLUE);
            g2.fillRect(size/2, size/2, size, size);
            return bi;
        }

        private BufferedImage getTextureImageForLabel_0_10() {
            int size = 16;
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

         private BufferedImage getTextureImageForLabel_11_20() {
            int size = 16;
            BufferedImage bi = new BufferedImage(  size , size , BufferedImage.TYPE_INT_ARGB) ;
            Graphics2D g2 = bi.createGraphics();
            g2.setPaint(Color.CYAN);
            g2.fillRect(0 , 0, size/2, size/2);
            g2.setPaint(Color.GREEN);
            g2.fillRect(size/2, 0, size, size/2);
            g2.setPaint(Color.GREEN);
            g2.fillRect(0, size/2, size/2, size);
            g2.setPaint(Color.CYAN);
            g2.fillRect(size/2, size/2, size, size);
            return bi;
        }

          private BufferedImage getTextureImageForLabel_21_30() {
            int size = 16;
            BufferedImage bi = new BufferedImage(  size , size , BufferedImage.TYPE_INT_ARGB) ;
            Graphics2D g2 = bi.createGraphics();
            g2.setPaint(Color.ORANGE);
            g2.fillRect(0 , 0, size/2, size/2);
            g2.setPaint(Color.YELLOW);
            g2.fillRect(size/2, 0, size, size/2);
            g2.setPaint(Color.YELLOW);
            g2.fillRect(0, size/2, size/2, size);
            g2.setPaint(Color.ORANGE);
            g2.fillRect(size/2, size/2, size, size);
            return bi;
        }

        Thread addPiece = new Thread(){
        @Override
        public void run(){
            int xPiece = -35;
            int vectorSize = 0 ;
            int scenario = 0; // scenario run this thread
            boolean startIncreaseStrAplha = true; // increase label opacity
            boolean startDecreaseStrAplha = false; // decrease label opacity
            while(turnOn) {
                try {

    // ******** in order to 'pause' this thread for 'new' or 'exit' !
                     if(suspend.get())
                        {
                            synchronized( mainBRICKS.shutterThread)
                            {
                                // Pause
                                try
                                {
                                    mainBRICKS.shutterThread.wait();
                                }
                                catch (InterruptedException e)
                                {
                                }
                            }
                        }
                     
                scenario++;
                Thread.currentThread().sleep( 40 );

                // sceanarion PART I: if scenario < 30  then add rectangle-shutters
                xPiece  += 35;
                if(  xPiece <= 1000 )
                   {
                vectorSize++;
                v.add( new Rectangle2D.Double(xPiece , 0, 35, mainBRICKS.getBasicPanel().getHeight()) );
                repaint();
                    }
               // sceanario PART II: if scenario > 30  then display label and then remove it
              if( scenario >=30 )
                {

                   Thread.currentThread().sleep( 200 );
                   if ( startIncreaseStrAplha )
                        strAlpha+= 0.08f;
                   if ( startDecreaseStrAplha )
                        strAlpha-= 0.08f;
                   repaint();

                   if( strAlpha > 0.9f ){ //  stop increase opacity and start decrease opacity
                       startIncreaseStrAplha = false;
                       startDecreaseStrAplha = true;
// it need to pause 'shutterThread' and client should choice 'New' or 'Exit'.
// if choice have not done 'choiceNewOrExit = true' and 'shutterThread' is paused.
// After choice is done 'choiceNewOrExit = false' and 'shutterThread' can finish own job.
                        if( mainBRICKS.choiceNewOrExit == true ) {  suspend.set(true);  }
                   }
                   //  if decrease opacity less zero remove label
                   if( (strAlpha <= 0.00f) &&  startDecreaseStrAplha ) {
                        startDecreaseStrAplha = false;
                        cnt = -1;
                   }
                   // condition for remove shutters !!!
                   if( (strAlpha == 0.00f) && !startDecreaseStrAplha ) {
                         vectorSize--;
                         if( vectorSize >= 0 ) {
                             v.remove(vectorSize);
                         } else {
                             vectorSize = 0;
                             turnOn = false; // stop this Thread !
                             repaint();
                         }
                         repaint();
                    }
                   // System.out.println( strAlpha );
                    }


                } catch (InterruptedException ex) { }
            }
        }
    };

        int changePause = 0;
    Thread pause = new Thread() {

        @Override
      public void run() {
        while(true) {
                try {
                    Thread.currentThread().sleep(200);
                    changePause++;

                    if( changePause == 5 ) changePause = 0;

                   // System.out.println("SHUTTER " + changePause );

                    repaint();
                    
                } catch (InterruptedException ex) { }
        }
      }

    } ;

  }
