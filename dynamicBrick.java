
package GAME_BRICKS;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

class dynamicBrick extends JComponent {

      Area figure;
      Shape outlineDynamicBrick;

      BRICKS mainBRICKS;

     dynamicBrick( BRICKS main) {
       mainBRICKS = main;
     }

      public void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setStroke(new BasicStroke( 0.5f ));

          RoundRectangle2D rect  = new RoundRectangle2D.Double(0,0,38,38, 10, 10);
          Rectangle2D left  = new Rectangle2D.Double(0,10,2,20);
          Rectangle2D top  = new Rectangle2D.Double(10,0,20,2);
          Rectangle2D right  = new Rectangle2D.Double(36,10,2,20);
          Rectangle2D bottom  = new Rectangle2D.Double(10,36,20,2);

          Area rectArea = new Area(rect);
          rectArea.subtract( new Area(left) );
          rectArea.subtract( new Area(top) );
          rectArea.subtract( new Area(right) );
          rectArea.subtract( new Area(bottom) );

          GeneralPath topPath = new GeneralPath();
          topPath.moveTo( 6, 4 );
          topPath.lineTo( 33, 4 );
          topPath.lineTo( rect.getWidth()/2, rect.getHeight()/2 - 2 );
          topPath.closePath();

          GeneralPath leftPath = new GeneralPath();
          leftPath.moveTo( 4, 6 );
          leftPath.lineTo( 4, 33 );
          leftPath.lineTo( rect.getWidth()/2 - 2, rect.getHeight()/2 );
          leftPath.closePath();

          GeneralPath bottomPath = new GeneralPath();
          bottomPath.moveTo( 6, 35 );
          bottomPath.lineTo( 33, 35 );
          bottomPath.lineTo( rect.getWidth()/2 , rect.getHeight()/2 + 2 );
          bottomPath.closePath();

          GeneralPath rightPath = new GeneralPath();
          rightPath.moveTo( 35, 5 );
          rightPath.lineTo( 35, 33 );
          rightPath.lineTo( rect.getWidth()/2 + 2, rect.getHeight()/2  );
          rightPath.closePath();

          if( !mainBRICKS.dynamicBrickError )
          {
          paintDynamicBrick( g2 , rectArea , topPath ,  bottomPath ,  leftPath , rightPath  );
          }
          else {
               paintDynamicBrick( g2 , rectArea , topPath ,  bottomPath ,  leftPath , rightPath  );
          }
      }

      private void paintDynamicBrick( Graphics2D g2 , Area rectArea ,
                                      GeneralPath topPath , GeneralPath bottomPath ,
                                      GeneralPath leftPath ,GeneralPath rightPath  )
        {
          g2.setPaint( new Color(147,199,255));// new Color(230,191,255)
          g2.fill(rectArea);
          g2.setPaint(Color.BLUE);
          g2.draw(rectArea);

          BufferedImage bi = HOR_Texture();
          Rectangle rr = new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
          TexturePaint tp1 = new TexturePaint(bi, rr);
           g2.setPaint(tp1);

          g2.fill(topPath);
          g2.fill(bottomPath);

          BufferedImage bi1 = VER_Texture();
          Rectangle r = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
          TexturePaint tp2 = new TexturePaint(bi1, r);
          g2.setPaint(tp2);

          g2.fill(leftPath);
          g2.fill(rightPath);
      }

       private BufferedImage HOR_Texture() {
            int size = 4;
            BufferedImage bi = new BufferedImage(  size , size , BufferedImage.TYPE_INT_ARGB) ;
            Graphics2D g2 = bi.createGraphics();
            if( shift == 1)        g2.setPaint(Color.BLUE);
            else if( shift == 2)   g2.setPaint(Color.BLUE);
            else if( shift == 3)   g2.setPaint(Color.RED);
            else if( shift == 4)   g2.setPaint(Color.RED);
            else if( shift == 5)   g2.setPaint(Color.BLACK);
            g2.fillRect(0 , 0, size/2, size/2);

            if( shift == 1)        g2.setPaint(Color.BLUE);
            else if( shift == 2)   g2.setPaint(Color.BLUE);
            else if( shift == 3)   g2.setPaint(Color.RED);
            else if( shift == 4)   g2.setPaint(Color.RED);
            else if( shift == 5)   g2.setPaint(Color.BLACK);
            g2.fillRect(size/2, 0, size, size/2);

            if( shift == 1)        g2.setPaint(Color.CYAN);
            else if( shift == 2)   g2.setPaint(Color.RED);
            else if( shift == 3)   g2.setPaint(Color.BLUE);
            else if( shift == 4)   g2.setPaint(Color.GREEN);
            else if( shift == 5)   g2.setPaint(Color.WHITE);
            g2.fillRect(0, size/2, size/2, size);

            if( shift == 1)         g2.setPaint(Color.CYAN);
            else if( shift == 2)   g2.setPaint(Color.RED);
            else if( shift == 3)   g2.setPaint(Color.BLUE);
            else if( shift == 4)   g2.setPaint(Color.GREEN);
            else if( shift == 5)   g2.setPaint(Color.WHITE);
            g2.fillRect(size/2, size/2, size, size);
            return bi;
        }

        private BufferedImage VER_Texture() {
            int size = 4;
            BufferedImage bi = new BufferedImage(  size , size , BufferedImage.TYPE_INT_ARGB) ;
            Graphics2D g2 = bi.createGraphics();
            if( shift == 1)        g2.setPaint(Color.BLUE);
            else if( shift == 2)   g2.setPaint(Color.BLUE);
            else if( shift == 3)   g2.setPaint(Color.RED);
            else if( shift == 4)   g2.setPaint(Color.RED);
            else if( shift == 5)   g2.setPaint(Color.BLACK);
            g2.fillRect(0 , 0, size/2, size/2);

            if( shift == 1)        g2.setPaint(Color.CYAN);
            else if( shift == 2)   g2.setPaint(Color.RED);
            else if( shift == 3)   g2.setPaint(Color.BLUE);
            else if( shift == 4)   g2.setPaint(Color.GREEN);
            else if( shift == 5)   g2.setPaint(Color.WHITE);
            g2.fillRect(size/2, 0, size, size/2);

            if( shift == 1)        g2.setPaint(Color.BLUE);
            else if( shift == 2)   g2.setPaint(Color.BLUE);
            else if( shift == 3)   g2.setPaint(Color.RED);
            else if( shift == 4)   g2.setPaint(Color.RED);
            else if( shift == 5)   g2.setPaint(Color.BLACK);
            g2.fillRect(0, size/2, size/2, size);

            if( shift == 1)        g2.setPaint(Color.CYAN);
            else if( shift == 2)   g2.setPaint(Color.RED);
            else if( shift == 3)   g2.setPaint(Color.BLUE);
            else if( shift == 4)   g2.setPaint(Color.GREEN);
            else if( shift == 5)   g2.setPaint(Color.WHITE);
            g2.fillRect(size/2, size/2, size, size);
            return bi;
        }

        // This thread is used when dynamic brick fall down on floor and LIFE  decreased on  1
      int shift =  1;
      Thread changeColor = new Thread(){
            @Override
          public void run() {
              while(mainBRICKS.dynamicBrickError)
              {
                try {
                      shift++;
                      Thread.currentThread().sleep(500);
                      if( shift == 5) {
                          // reset cnt for next Error
                          shift = 1;

//                          mainBRICKS.xPos = 470;
//                          mainBRICKS.yPos = 400;
                           // set dynamic brick in initial position
                          //mainBRICKS.mBrick.setBounds( mainBRICKS.xPos, mainBRICKS.yPos, 46, 48); // mBrick


                          dynamicBrick.this.repaint();

//                           start = true;
//                           Thread thrr = new Thread(thr);
//                           thrr.start();

                           mainBRICKS.dynamicBrickError = false;
                            repaint();
                      }
                      dynamicBrick.this.repaint();
                          repaint();
                } catch (InterruptedException ex) {   }
              }
          }
      };
  }
