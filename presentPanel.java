package GAME_BRICKS;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class presentPanel extends JPanel {

    BufferedImage bi1, bi2, bi3, bi4, bi5, bi6;

    AtomicBoolean turnOn1, turnOn2, turnOn3, turnOn4, turnOn5, turnOn6;
    Thread thr1, thr2, thr3, thr4, thr5, thr6;
    //protected AtomicBoolean turnOn1;
    int[] xyLoc;
    int ySpan1, ySpan2, ySpan3, ySpan4, ySpan5, ySpan6;
    int xSpan1, xSpan2, xSpan3, xSpan4, xSpan5, xSpan6;
    int cntMoving1 = 0, cntMoving2 = 0, cntMoving3 = 0, cntMoving4 = 0, cntMoving5 = 0, cntMoving6 = 0;
    
    BRICKS mainBRICKS;

    presentPanel(BRICKS mainBRICKS) {
        this.mainBRICKS = mainBRICKS;

        this.turnOn1 = new AtomicBoolean(false);
        this.turnOn2 = new AtomicBoolean(false);
        this.turnOn3 = new AtomicBoolean(false);
        this.turnOn4 = new AtomicBoolean(false);
        this.turnOn5 = new AtomicBoolean(false);
        this.turnOn6 = new AtomicBoolean(false);

        bi1 = randomImageChoice();
        bi2 = randomImageChoice();
        bi3 = randomImageChoice();
        bi4 = randomImageChoice();
        bi5 = randomImageChoice();
        bi6 = randomImageChoice();

        //System.out.println(bi1.getWidth());
        this.setLayout(null);
        this.setBounds(mainBRICKS.getBasicPanel().getX(), mainBRICKS.getBasicPanel().getY(),
                mainBRICKS.getBasicPanel().getWidth(), mainBRICKS.getBasicPanel().getHeight());

    }

    float Alpha = 1.0f;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3.0f));
        // panel paint
        Rectangle rectPanel = new Rectangle(0, 0, this.getWidth(), this.getHeight());
        g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, Alpha ));
        g2.setPaint( new GradientPaint( 0, 0, new Color(255,246,221), 0+50 , 0, new Color(230,255,5) , true) );
        g2.fill(rectPanel);

        Font font1 = new Font("Book Antiqua", Font.BOLD, 210);
        Font font2 = new Font("Monotype Corsiva", Font.BOLD, 300);

        FontRenderContext frc = g2.getFontRenderContext();

        Rectangle rr = new Rectangle(0, 0, bi1.getWidth(), bi1.getHeight());
        TexturePaint tp1 = new TexturePaint(bi1, rr);
        TexturePaint tp2 = new TexturePaint(bi2, rr);
        TexturePaint tp3 = new TexturePaint(bi3, rr);
        TexturePaint tp4 = new TexturePaint(bi4, rr);
        TexturePaint tp5 = new TexturePaint(bi5, rr);
        TexturePaint tp6 = new TexturePaint(bi6, rr);
        // g2.setPaint(tp1);

        String text = "Bricks ";
        GlyphVector gv = font2.createGlyphVector(frc, text);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
        g2.setPaint(tp1);

        Rectangle r = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        xyLoc = utils.centerString1(g2, r, text, font2);
        // draw first string
        int xLoc1 = xyLoc[0] + xSpan1;
        int yLoc1 = xyLoc[1] + 400 + ySpan1;
        g2.drawGlyphVector(gv, xLoc1, yLoc1);
        // draw second string
        g2.setPaint(tp2);
        int xLoc2 = xyLoc[0] + xSpan2;
        int yLoc2 = xyLoc[1] + 700 + ySpan2;
        g2.drawGlyphVector(gv, xLoc2, yLoc2);
        // draw third string
        g2.setPaint(tp3);
        int xLoc3 = xyLoc[0] + xSpan3;
        int yLoc3 = xyLoc[1] + 1000 + ySpan3;
        g2.drawGlyphVector(gv, xLoc3, yLoc3);
        // draw fourth string
        g2.setPaint(tp4);
        int xLoc4 = xyLoc[0] + xSpan4;
        int yLoc4 = xyLoc[1] + 1300 + ySpan4;
        g2.drawGlyphVector(gv, xLoc4, yLoc4);
        // draw fifth string
        g2.setPaint(tp5);
        int xLoc5 = xyLoc[0] + xSpan5;
        int yLoc5 = xyLoc[1] + 1600 + ySpan5;
        g2.drawGlyphVector(gv, xLoc5, yLoc5);
        // draw sixth string
        g2.setPaint(tp6);
        int xLoc6 = xyLoc[0] + xSpan6;
        int yLoc6 = xyLoc[1] + 1900 + ySpan6;
        g2.drawGlyphVector(gv, xLoc6, yLoc6);
    }

    // like shess board 
    private BufferedImage getTextureImage_chess(int size, Color color1, Color color2) {
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(color1);
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(color2);
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(color2);
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(color1);
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    // horizontal lines
    private BufferedImage getTextureImage_horizontal(int size, Color color1, Color color2) {
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(color1);
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(color1);
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(color2);
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(color2);
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    // vertical lines
    private BufferedImage getTextureImage_vertical(int size, Color color1, Color color2) {
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(color1);
        g2.fillRect(0, 0, size / 2, size / 2);
        g2.setPaint(color2);
        g2.fillRect(size / 2, 0, size, size / 2);
        g2.setPaint(color1);
        g2.fillRect(0, size / 2, size / 2, size);
        g2.setPaint(color2);
        g2.fillRect(size / 2, size / 2, size, size);
        return bi;
    }

    private Color randomColorChoice() {
        Color color = null;

        int randomColor = utils.getRandomNumberInRange(1, 12);

        switch (randomColor) {
            default:
                color = Color.BLACK;
                return color;
            case 1:
                color = Color.BLUE;
                return color;
            case 2:
                color = Color.CYAN;
                return color;
            case 3:
                color = Color.DARK_GRAY;
                return color;
            case 4:
                color = Color.GRAY;
                return color;
            case 5:
                color = Color.GREEN;
                return color;
            case 6:
                color = Color.LIGHT_GRAY;
                return color;
            case 7:
                color = Color.MAGENTA;
                return color;
            case 8:
                color = Color.ORANGE;
                return color;
            case 9:
                color = Color.PINK;
                return color;
            case 10:
                color = Color.RED;
                return color;
            case 11:
                color = Color.BLACK;
                return color;
            case 12:
                color = Color.YELLOW;
                return color;
        }
    }

    private int randomImageSize() {
        int size;

        int randomSize = utils.getRandomNumberInRange(1, 10);

        // System.out.println(randomSize);
        switch (randomSize) {
            default:
                size = 16;
                return size;
            case 1:
                size = 8;
                return size;
            case 2:
                size = 12;
                return size;
            case 3:
                size = 16;
                return size;
            case 4:
                size = 20;
                return size;
            case 5:
                size = 24;
                return size;
            case 6:
                size = 28;
                return size;
            case 7:
                size = 32;
                return size;
            case 8:
                size = 36;
                return size;
            case 9:
                size = 64;
                return size;
            case 10:
                size = 72;
                return size;
        }
    }

    private BufferedImage randomImageChoice() {
        BufferedImage bimage = null;

        int random = utils.getRandomNumberInRange(1, 3);

        switch (random) {
            default:
                bimage = getTextureImage_chess(randomImageSize(), randomColorChoice(), randomColorChoice());
                return bimage;
            case 1:
                bimage = getTextureImage_chess(randomImageSize(), randomColorChoice(), randomColorChoice());
                return bimage;
            case 2:
                bimage = getTextureImage_horizontal(randomImageSize(), randomColorChoice(), randomColorChoice());
                return bimage;
            case 3:
                bimage = getTextureImage_vertical(randomImageSize(), randomColorChoice(), randomColorChoice());
                return bimage;
        }
    }

    Thread moving1 = new Thread() {
        @Override
        public void run() {
            while (turnOn1.get()) {
                try {
                    // ******** in order to 'pause' this thread  !
//                    if (suspend.get()) {
//                        synchronized (mainBRICKS.fenceThread) {
//                            // Pause
//                            try {
//                                mainBRICKS.fenceThread.wait();
//                            } catch (InterruptedException e) {
//                            }
//                        }
                    cntMoving1++;
//                     System.out.println(cntMoving1);
                    if (cntMoving1 == 55) {
                        Thread.currentThread().sleep(15000);
                    } else if(cntMoving1 < 55) {
                       ySpan1 -= 10; 
                    } else if(cntMoving1 > 55 && cntMoving1 < 150 ) {  //95
                       xSpan1 -= 10; 
                    } else if(cntMoving1 == 150) {
                       turnOn1.set(false);
                    }

                    Thread.currentThread().sleep(110);
                    revalidate();
                    repaint();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }

            }
            
        }
    };

    Thread moving2 = new Thread() {
        @Override
        public void run() {
            while (turnOn2.get()) {
                try {
                    cntMoving2++;      
//                     System.out.println(cntMoving2);
                    if (cntMoving2 == 80) {
                        Thread.currentThread().sleep(14000);
                    } else if (cntMoving2 < 80) {
                        ySpan2 -= 10;
                    } else if (cntMoving2 > 80 && cntMoving2 < 175) { // 95
                        xSpan2 += 10;
                    } else if (cntMoving2 == 175) {
                        turnOn2.set(false); 
                    }

                    Thread.currentThread().sleep(110);
                    revalidate();
                    repaint();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }

            }
        }
    };

    Thread moving3 = new Thread() {
        @Override
        public void run() {
            while (turnOn3.get()) {
                try {
                    cntMoving3++;
//                    System.out.println(cntMoving3);
                    if (cntMoving3 == 105) {
                        Thread.currentThread().sleep(13000);
                    } else if(cntMoving3 < 105) {
                        ySpan3 -= 10;
                    } else if(cntMoving3 > 105 && cntMoving3 < 200) { // 95
                        xSpan3 -= 10;
                    } else if(cntMoving3 == 200) {
                        turnOn3.set(false); 
                    }

                    Thread.currentThread().sleep(110);
                    revalidate();
                    repaint();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }

            }
        }
    };

    Thread moving4 = new Thread() {
        @Override
        public void run() {
            while (turnOn4.get()) {
                try {
                    cntMoving4++;
//                    System.out.println(cntMoving3);
                    if (cntMoving4 == 130) { // 126
                        Thread.currentThread().sleep(12000);
                    } else if(cntMoving4 < 130) {
                        ySpan4 -= 10;
                    } else if(cntMoving4 > 130 && cntMoving4 < 225) { // 95
                        xSpan4 += 10;
                    } else if(cntMoving4 == 225) {
                        turnOn4.set(false); 
                    }

                    Thread.currentThread().sleep(110);
                    revalidate();
                    repaint();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }

            }
        }
    };

    Thread moving5 = new Thread() {
        @Override
        public void run() {
            while (turnOn5.get()) {
                try {
                    cntMoving5++;
//                    System.out.println(cntMoving3);
                    if (cntMoving5 == 155) {
                        Thread.currentThread().sleep(9000);
                    } else if (cntMoving5 < 155) {
                        ySpan5 -= 10;
                    } else if (cntMoving5 > 155 && cntMoving5 < 250) { // 95
                        xSpan5 -= 10;
                    } else if (cntMoving5 == 250) {
                        turnOn5.set(false); 
                    }

                    Thread.currentThread().sleep(110);
                    revalidate();
                    repaint();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }

            }
        }
    };

    Thread moving6 = new Thread() {
        @Override
        public void run() {
            while (turnOn6.get()) {
                try {
                    cntMoving6++;
//                    System.out.println(cntMoving6);
                    if (cntMoving6 == 180) {
                        Thread.currentThread().sleep(8000);
                    } else if (cntMoving6 < 180) {
                        ySpan6 -= 10; 
                    } else if (cntMoving6 > 180 && cntMoving6 < 275) { //95
                        xSpan6 += 10;
                        Alpha -= 0.01;
                    } else if (cntMoving6 == 275) {
                        turnOn6.set(false);
                        // set this  Panel invisible
                        setVisible(false); 
                    }

                    Thread.currentThread().sleep(110);
                    revalidate();
                    repaint();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }

            }
        }
    };

    public void startPresentPanel() {
        turnOn1.set(true);
        thr1 = new Thread(moving1);
        thr1.start();

        turnOn2.set(true);
        thr2 = new Thread(moving2);
        thr2.start();
//
        turnOn3.set(true);
        thr3 = new Thread(moving3);
        thr3.start();
//
        turnOn4.set(true);
        thr4 = new Thread(moving4);
        thr4.start();
//
        turnOn5.set(true);
        thr5 = new Thread(moving5);
        thr5.start();

        turnOn6.set(true);
        thr6 = new Thread(moving6);
        thr6.start();
    }

    public void stopPresentPanel() {
        turnOn1.set(false);
        turnOn2.set(false);
        turnOn3.set(false);
        turnOn4.set(false);
        turnOn5.set(false);
        turnOn6.set(false);
    }

    public static void main(String[] args) {
//        final presentPanel present = new presentPanel();
//        present.setBounds(0, 0, 1015, 600);
//        present.startPresentPanel();
        
        //System.out.println(fence.getDirectionMove());

//        SwingUtilities.invokeLater(
//                new Runnable() {
//                    @Override
//                    public void run() {
//
////                        try {
////                            Thread.sleep(5000);
////                            // present.stopPresentPanel();
////                        } catch (InterruptedException ex) {  }
//                        
//                          
//                        
////                        present.stopPresentPanel();
////                        present.turnOn1 = true;
////                        Thread thr1 = new Thread(present.moving1);
////                        thr1.start();
////
////                        present.turnOn2 = true;
////                        Thread thr2 = new Thread(present.moving2);
////                        thr2.start();
////
////                        present.turnOn3 = true;
////                        Thread thr3 = new Thread(present.moving3);
////                        thr3.start();
////                        
////                        present.turnOn4 = true;
////                        Thread thr4 = new Thread(present.moving4);
////                        thr4.start();
////                        
////                        present.turnOn5 = true;
////                        Thread thr5 = new Thread(present.moving5);
////                        thr5.start();
//                    }
//                });
        
//        JFrame f = new JFrame();
//        f.setSize(1015, 600);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(present);
//        f.setVisible(true);
    }
}
