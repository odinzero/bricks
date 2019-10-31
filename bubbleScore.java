package GAME_BRICKS;


import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

class bubbleScore extends JComponent {

   protected int type;

       bubbleScore( int Type )
       {
         type = Type;
       }

       protected int getScoreType() {
           return type;
      }

        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setStroke(new BasicStroke( 1.2f ));
          Font f = new Font("Serif", Font.BOLD, 22);
          FontRenderContext frc = g2.getFontRenderContext();
          String s = "";
          GlyphVector gv = null;

          int choice  = bubbleScore.this.getScoreType();

          switch(choice) {

              default:  break;
    case -25:
            GeneralPath out_path_25 = new GeneralPath();
            out_path_25.moveTo(15, 0);  //  15, 0
            out_path_25.lineTo(30, 0);  //  30, 0
            out_path_25.lineTo(45, 30); //  45, 30 
            out_path_25.lineTo(0, 30);  //  0, 30 
            out_path_25.closePath();

            g2.setColor( new Color(255,207,185));
            g2.fill( out_path_25 );
            g2.setStroke(new BasicStroke( 0.5f ));
            g2.setColor( new Color(121,39,0) );
            g2.draw( out_path_25 );

            GeneralPath in_path_25 = new GeneralPath();
            in_path_25.moveTo(16, 2);  //  16, 2
            in_path_25.lineTo(29, 2);  //  29, 2
            in_path_25.lineTo(42, 28); //  42, 28 
            in_path_25.lineTo(3, 28);  //  3, 28 
            in_path_25.closePath();

            g2.setPaint( new GradientPaint( 18, 0, new Color(255,207,185) , 18 , 36, new Color(121,39,0), false) );
            g2.fill( in_path_25 );
            g2.setColor( Color.BLUE );
            g2.draw( in_path_25 );
            
            RoundRectangle2D minus25  = new RoundRectangle2D.Double(9,19,7,2,1,1);

            f = new Font("Serif", Font.BOLD, 20);
            s = "25";
            gv = f.createGlyphVector(frc, s);
            g2.setPaint(new Color(249,255,214));
            g2.drawGlyphVector(gv, 15, 26);
            g2.fill(minus25); 
               break;              
    case -10:
            RoundRectangle2D r_10_out = new RoundRectangle2D.Double(9, 18, 37, 31 , 10 ,10);
            Area r_outline10 = new Area(r_10_out);

            g2.setColor(new Color(32,23,19));
            g2.fill( r_outline10 );
            g2.setStroke(new BasicStroke( 0.5f ));
            g2.setColor( new Color(234,223,219) );
            g2.draw( r_outline10 );

            RoundRectangle2D r_IN_10 = new RoundRectangle2D.Double(10, 19, 34, 28, 10 ,10);
            Area r_inline_10 = new Area(r_IN_10);
            g2.setPaint( new GradientPaint( 11, 20, new Color(234,223,219) , 11 , 47, new Color(32,23,19), false) );//190,255,147
            g2.fill( r_inline_10 );
            g2.setColor( Color.WHITE );
            g2.draw( r_inline_10 );

            RoundRectangle2D minus10  = new RoundRectangle2D.Double(13,32,7,2,1,1);
            
            s = "10";
            gv = f.createGlyphVector(frc, s);
            g2.setPaint(new Color(54,54,11));
            g2.fill(minus10); 
            g2.drawGlyphVector(gv, 20, 40);
               break;              
    case -5:  
             Ellipse2D fieldoutside = new Ellipse2D.Double( 9, 18, 35, 30 );// 9, 18
             Area outline0 = new Area(fieldoutside);

             g2.setColor(new Color(217, 179, 255));// 217, 179, 255      181, 106, 255
             g2.fill( outline0 );
             g2.setStroke(new BasicStroke( 0.4f ));
             g2.setColor( Color.blue  );
             g2.draw( outline0 );

             Ellipse2D fieldoutsideIN = new Ellipse2D.Double( 11, 20, 31, 26 ); // 9, 18, 35, 30
             Area outline_IN = new Area( fieldoutsideIN );
             g2.draw( outline_IN );
             g2.setPaint( new GradientPaint( 0, 0, new Color(171,162,253) , 11 , 46, new Color(208,143,243), false) ); //190,255,147
             g2.fill( outline_IN );  // 104,88,252
             
             RoundRectangle2D minus  = new RoundRectangle2D.Double(16,32,7,2,1,1);

             s = "5";
             gv = f.createGlyphVector(frc, s);
             g2.setPaint(new Color(89,89,89));
             g2.fill( minus );
             g2.drawGlyphVector(gv, 25, 40);
             break;
     case 5:
             Ellipse2D helpfieldoutside = new Ellipse2D.Double( 9, 18, 35, 30 );// 9, 18
             Area outline = new Area(helpfieldoutside);

             g2.setColor(new Color( 145, 237, 255));// 58, 235, 255
             g2.fill( outline );
             g2.setStroke(new BasicStroke( 0.5f ));
             g2.setColor( Color.GRAY );
             g2.draw( outline );

             Ellipse2D helpfieldoutsideIN = new Ellipse2D.Double( 11, 20, 31, 26 ); // 9, 18, 35, 30
             Area outlineIN = new Area( helpfieldoutsideIN );
             g2.draw( outlineIN );
             g2.setPaint( new GradientPaint( 11, 20, new Color(255,242,226) , 11 , 46, new Color(213,255,122), false) ); //190,255,147
             g2.fill( outlineIN );

             s = "5";
             gv = f.createGlyphVector(frc, s);
             g2.setPaint(Color.RED);
             g2.drawGlyphVector(gv, 21, 40);
             break;
    case 10:
            RoundRectangle2D r_OUT = new RoundRectangle2D.Double(9, 18, 35, 31 , 10 ,10);
            Area r_outline = new Area(r_OUT);

            g2.setColor(Color.ORANGE);
            g2.fill( r_outline );
            g2.setStroke(new BasicStroke( 0.5f ));
            g2.setColor( Color.WHITE );
            g2.draw( r_outline );

            RoundRectangle2D r_IN = new RoundRectangle2D.Double(10, 19, 32, 28, 10 ,10);
            Area r_inline = new Area(r_IN);
            g2.setPaint( new GradientPaint( 11, 20, new Color(255,196,200) , 11 , 47, new Color(255,180,68), false) );//190,255,147
            g2.fill( r_inline );
            g2.setColor( Color.WHITE );
            g2.draw( r_inline );

            s = "10";
            gv = f.createGlyphVector(frc, s);
            g2.setPaint(Color.WHITE);
            g2.drawGlyphVector(gv, 15, 40);
               break;

   case 25:
            GeneralPath out_path = new GeneralPath();
            out_path.moveTo(20, 0);
            out_path.lineTo(40, 20);
            out_path.lineTo(20, 40);
            out_path.lineTo(0, 20);
            out_path.closePath();

            g2.setColor(Color.ORANGE);
            g2.fill( out_path );
            g2.setStroke(new BasicStroke( 0.5f ));
            g2.setColor( Color.BLUE );
            g2.draw( out_path );

            GeneralPath in_path = new GeneralPath();
            in_path.moveTo(20, 2);
            in_path.lineTo(38, 20);
            in_path.lineTo(20, 38);
            in_path.lineTo(2, 20);
            in_path.closePath();

            g2.setPaint( new GradientPaint( 18, 0, new Color(255,155,250) , 18 , 36, new Color(255,22,69), false) );
            g2.fill( in_path );
            g2.setColor( Color.BLUE );
            g2.draw( in_path );

            f = new Font("Serif", Font.BOLD, 21);
            s = "25";
            gv = f.createGlyphVector(frc, s);
            g2.setPaint(new Color(249,255,214));
            g2.drawGlyphVector(gv, 9, 27);
               break;

   case 50:
            GeneralPath polygon_out = new GeneralPath(); // Start with an empty shape
            polygon_out.moveTo(0+35/2, 0);
            polygon_out.lineTo(0+35, 0+35/2);
            polygon_out.lineTo(0+ 35*3/4, 0+35);
            polygon_out.lineTo(0+ 35*1/4, 0+35);
            polygon_out.lineTo(0, 0+35/2);
            polygon_out.closePath();

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint( new GradientPaint( 10, 10, Color.GREEN , 10, 36, Color.ORANGE, false) );
           g2.fill( polygon_out );
           g2.setColor( Color.MAGENTA );
           g2.draw( polygon_out );

            GeneralPath polygon_in = new GeneralPath(); // Start with an empty shape
            polygon_in.moveTo(0+35/2, 2);
            polygon_in.lineTo(0+33, 0+35/2);
            polygon_in.lineTo(0+ 34*3/4, 0+33);
            polygon_in.lineTo(0+ 37*1/4, 0+33);
            polygon_in.lineTo(2, 0+35/2);
            polygon_in.closePath();

           g2.setPaint( new GradientPaint( 10, 10, Color.GREEN , 10, 36, Color.ORANGE, false) );
           g2.fill( polygon_in );
           g2.setColor( Color.MAGENTA );
           g2.draw( polygon_in );

           f = new Font("Serif", Font.BOLD, 22);
           s = "50";
           gv = f.createGlyphVector(frc, s);
           g2.setPaint(new Color(73,89,255));
           g2.drawGlyphVector(gv, 7, 27);
              break;

  case 100:
           GeneralPath heart = new GeneralPath(); // Start with an empty shape
           heart.moveTo(0+40*(410-371)/77, 0+33*(110-93)/73);

           heart.curveTo(0+40*(410-371)/77, 0-33*(93-76)/73,
                            0+40+40*(47)/77,    0-33*(93-90)/73,
                            0+40*(411-371)/77, 0+33);

           heart.curveTo(0-40*(371-322)/77, 0-33*(93-90)/73,
                            0+40*(410-371)/77, 0-33*(93-76)/73,
                            0+40*(410-371)/77, 0+33*(110-93)/73);
           heart.closePath();

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint( new GradientPaint( 18, 0, new Color(185,147,255) , 18 , 36, new Color(255,17,195), false) );
           g2.fill( heart );
           g2.setColor( Color.ORANGE );
           g2.draw( heart );

           f = new Font("Serif", Font.BOLD, 18);
           s = "100";
           gv = f.createGlyphVector(frc, s);
           g2.setPaint(new Color(243,255,137));
           g2.drawGlyphVector(gv, 7, 20);
              break;

 case 150:
            GeneralPath triangle_one = new GeneralPath(); // Start with an empty shape
            triangle_one.moveTo(0+35/2, 0);
            triangle_one.lineTo(0+35, 0+35);
            triangle_one.lineTo(0, 0+35);
            triangle_one.closePath();

            GeneralPath triangle_two = new GeneralPath(); // Start with an empty shape
            triangle_two.moveTo(15+35/2, 0);
            triangle_two.lineTo(15+35, 0+35);
            triangle_two.lineTo(15, 0+35);
            triangle_two.closePath();

           Area one = new Area(triangle_one);
           Area two = new Area(triangle_two);
           one.add(two);

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint( new GradientPaint( 0, 0, Color.YELLOW , 35 , 0, new Color(179,15,255), false) );
           g2.fill( one );
           g2.setColor( Color.WHITE );
           g2.draw( one );

            GeneralPath triangle_one_inner = new GeneralPath(); // Start with an empty shape
            triangle_one_inner.moveTo(0+34/2, 3);
            triangle_one_inner.lineTo(0+32, 0+33);
            triangle_one_inner.lineTo(2, 0+33);
            triangle_one_inner.closePath();

            GeneralPath triangle_two_inner = new GeneralPath(); // Start with an empty shape
            triangle_two_inner.moveTo(15+34/2, 3);
            triangle_two_inner.lineTo(15+32, 0+33);
            triangle_two_inner.lineTo(17, 0+33);
            triangle_two_inner.closePath();

           Area one_inner = new Area(triangle_one_inner);
           Area two_inner = new Area(triangle_two_inner);
           one_inner.add(two_inner);

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint( new GradientPaint( 0, 0, Color.YELLOW , 35 , 0, new Color(179,15,255), false) );
           g2.fill( one_inner );
           g2.setColor( Color.WHITE );
           g2.draw( one_inner );

           f = new Font("Serif", Font.BOLD, 20);
           s = "150";
           gv = f.createGlyphVector(frc, s);
           g2.setPaint(Color.pink );  // new Color(243,255,137)
           g2.drawGlyphVector(gv, 9, 31 );
              break;

case 300:
           GeneralPath polygonSixAngles_out = new GeneralPath(); // Start with an empty shape
           polygonSixAngles_out.moveTo(0+35/2, 0);
           polygonSixAngles_out.lineTo(0+35, 0+35*1/4);
           polygonSixAngles_out.lineTo(0+35, 0+35*3/4);
           polygonSixAngles_out.lineTo(0+35/2, 0+35);
           polygonSixAngles_out.lineTo(0, 0+35*3/4);
           polygonSixAngles_out.lineTo(0, 0+35*1/4);
           polygonSixAngles_out.closePath();

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint( Color.CYAN );
           g2.fill( polygonSixAngles_out );
           g2.setColor( Color.BLACK );
           g2.draw( polygonSixAngles_out );

           GeneralPath polygonSixAngles_inner = new GeneralPath(); // Start with an empty shape
           polygonSixAngles_inner.moveTo(0+35/2, 2);
           polygonSixAngles_inner.lineTo(0+33, 0+37*1/4);
           polygonSixAngles_inner.lineTo(0+33, 0+34*3/4);
           polygonSixAngles_inner.lineTo(0+35/2, 0+33);
           polygonSixAngles_inner.lineTo(2, 0+34*3/4);
           polygonSixAngles_inner.lineTo(2, 0+37*1/4);
           polygonSixAngles_inner.closePath();

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint( new GradientPaint( 10,5, new Color(201,255,227) , 35 , 35, new Color(179,15,255), false) );
           g2.fill( polygonSixAngles_inner );
           g2.setColor( Color.BLACK );
           g2.draw( polygonSixAngles_inner );

           f = new Font("Serif", Font.BOLD, 20);
           s = "300";
           gv = f.createGlyphVector(frc, s);
           g2.setPaint(new Color(219,247,255) );  // new Color(243,255,137)
           g2.drawGlyphVector(gv, 3, 24 );
              break;

 case 500:
            GeneralPath star_outer = new GeneralPath(); // Start with an empty shape
           star_outer.moveTo(40+43/2, 40);
           star_outer.lineTo(40+43*1.25/2, 40+43*0.75/2);
           star_outer.lineTo(40+43, 40+43/2);
           star_outer.lineTo(40+43*1.25/2, 40+43*1.25/2);
           star_outer.lineTo(40+43/2, 40+43);
           star_outer.lineTo(40+43*0.75/2, 40+43*1.25/2);
           star_outer.lineTo(40, 40+43/2);
           star_outer.lineTo(40+43*0.75/2, 40+43*0.75/2);
           star_outer.closePath();

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint( Color.GRAY );
           g2.fill( star_outer );
           g2.setColor( Color.GRAY );
           g2.draw( star_outer );

            GeneralPath star_inner = new GeneralPath(); // Start with an empty shape
           star_inner.moveTo(40+40/2, 40);
           star_inner.lineTo(40+40*1.25/2, 40+40*0.75/2);
           star_inner.lineTo(40+40, 40+40/2);
           star_inner.lineTo(40+40*1.25/2, 40+40*1.25/2);
           star_inner.lineTo(40+40/2, 40+40);
           star_inner.lineTo(40+40*0.75/2, 40+40*1.25/2);
           star_inner.lineTo(40, 40+40/2);
           star_inner.lineTo(40+40*0.75/2, 40+40*0.75/2);
           star_inner.closePath();

           g2.setStroke(new BasicStroke( 0.5f ));
           g2.setPaint(  new GradientPaint( 10,5, new Color(104,237,255) , 45 , 45, new Color(20,255,32), false) );
           g2.fill( star_inner );
           g2.setColor( Color.WHITE );
           g2.draw( star_inner );

           AffineTransform at1 = new AffineTransform();
           Shape star_blue1 = at1.createTransformedShape(star_outer);
           at1.translate(15, -15);
           g2.transform(at1);
           g2.setPaint( Color.GRAY  );
           g2.fill( star_blue1 );
           g2.setColor( Color.GRAY );
           g2.draw( star_blue1 );

           AffineTransform at2 = new AffineTransform();
           Shape star_blue2 = at2.createTransformedShape(star_inner);
           g2.setPaint(  new GradientPaint( 0,0, Color.RED , 90 , 45, Color.CYAN , false) ); //new Color(187,71,255)
           g2.fill( star_blue2 );
           g2.setColor( Color.WHITE );
           g2.draw( star_blue2 );

           AffineTransform at3 = new AffineTransform();
           Shape star_red1 = at3.createTransformedShape(star_outer);
           at3.translate(-30, 0);
           g2.transform(at3);
           g2.setPaint( Color.GRAY  );
           g2.fill( star_red1 );
           g2.setColor( Color.GRAY );
           g2.draw( star_red1 );

           AffineTransform at4 = new AffineTransform();
           Shape star_red2 = at4.createTransformedShape(star_inner);
           g2.setPaint(  new GradientPaint( 45,0, Color.WHITE , 90 , 45, Color.RED, false) ); //new Color(187,71,255)
           g2.fill( star_red2 );
           g2.setColor( Color.WHITE );
           g2.draw( star_red2 );

           BufferedImage bi1 = getTextureFor500();
           Rectangle rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
           TexturePaint tp1 = new TexturePaint(bi1, rr);

           f = new Font("Serif", Font.BOLD, 28);
           s = "500";
           gv = f.createGlyphVector(frc, s);
           g2.setPaint( tp1 );  // new Color(243,255,137)
           g2.drawGlyphVector(gv, 55, 38 );
              break;
            }
      }

        private BufferedImage getTextureFor500() {
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

  }