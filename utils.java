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
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JWindow;

public class utils {
    
    /**
     * This method searches a <code>v</code> of generic type in
     * <code>array</code> of generic type.
     *
     * @param array - The <code>T[]</code> array of generic type( String[], Integer[], Double[] and etc. ).
     * @param v -     <code>T</code> value of generic type( String, Integer, Double and etc. ).
     */ 
     public static <T> boolean containsT(final T[] array, final T v) {
        for (final T e : array) {
            if (e == v || v != null && v.equals(e)) {
                return true;
            }
        }
        return false;
    }

     /**
     * This method searches a <code>v</code> value in 
     * <code>array</code> 
     *
     * @param array - The <code>int[]</code> array of int type).
     * @param v     - The <code>int</code> value .
     */
    public static boolean contains(int[] array, final int v) {
        for (int i : array) {
            if (i == v) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method centers a <code>String</code> in a bounding
     * <code>Rectangle</code>.
     *
     * @param g - The <code>Graphics</code> instance.
     * @param r - The bounding <code>Rectangle</code>.
     * @param s - The <code>String</code> to center in the bounding rectangle.
     * @param font - The display font of the <code>String</code>
     *
     * @see java.awt.Graphics
     * @see java.awt.Rectangle
     * @see java.lang.String
     */
    public static void centerString(Graphics g, Rectangle r, String s,
            Font font) {
        FontRenderContext frc
                = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }
    
     /**
     * This method centers a <code>String</code> in a bounding
     * <code>Rectangle</code>.
     *
     * @param g - The <code>Graphics</code> instance.
     * @param r - The bounding <code>Rectangle</code>.
     * @param s - The <code>String</code> to center in the bounding rectangle.
     * @param font - The display font of the <code>String</code>
     *
     * @see java.awt.Graphics
     * @see java.awt.Rectangle
     * @see java.lang.String
     */
    public static int[] centerString1(Graphics g, Rectangle r, String s,
            Font font) {
        FontRenderContext frc
                = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;
        
        return new int[] {r.x + a,  r.y + b};
    }
    
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    

    public static void centerFrame(JFrame outside, JFrame inside) {
        // get the size of the screen, on systems with multiple displays,
        // the primary display is used
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        Dimension outsideDim = outside.getSize();
        int x = (dim.width - inside.getSize().width) / 2;
        int y = (dim.height - inside.getSize().height) / 2;

        inside.setLocation(x, y);
    }

    public static void centerWindow(JFrame outside, JWindow inside) {
        // get the size of the screen, on systems with multiple displays,
        // the primary display is used
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        Dimension outsideDim = outside.getSize();
        int x = (dim.width - inside.getSize().width) / 2;
        int y = (dim.height - inside.getSize().height) / 2;

        inside.setLocation(x, y);
    }

    public static String readFileContent(FileInputStream fis, String encoding) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        if (sb.length() == 0) {
            String str = "";
            return str;
        } else {
            return sb.toString();
        }
    }

    public static void writeFileContent(String path, String content) throws FileNotFoundException, IOException {        
        // get the content in bytes
//	byte[] contentInBytes = content.getBytes();
        File file = new File(path);
        // write content to file
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(content.getBytes(), 0, content.length());
        fout.close();
    }

    public static class buttonsWindow extends JComponent implements MouseListener {

        boolean focus = false;
        String text;
        int action = -1;
        int outW, outH;
        boolean handLocation = false;
        float xLoc, yLoc;
        String id;
        boolean disabled = false;

        buttonsWindow(String str, int outX, int outY, int xLoc, int yLoc) {
            text = str;
            handLocation = true;
            this.outW = outX;
            this.outH = outY;
            this.xLoc = xLoc;
            this.yLoc = yLoc;
            this.addMouseListener(this);
        }

        public String getNameButton() {
            return this.text;
        }

        public void setID(String id) {
            this.id = id;
        }

        public void setDisabledState(boolean disabled) {
            this.disabled = disabled;
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, outW, outH, 25, 20);
            int w = (int) rect.getWidth();
            int h = (int) rect.getHeight();
            g2.setStroke(new BasicStroke(0.5f));

            if (!focus && !disabled) {
                g2.setPaint(new GradientPaint(0, 0, new Color(84, 255, 124), 0, h, new Color(255, 243, 15), false));// 192,255,158
            } else if (focus && !disabled) {
                g2.setPaint(new GradientPaint(0, 0, new Color(84, 255, 124), 0, h, Color.RED, false));
            } else if (disabled) {
                g2.setPaint(new Color(192, 192, 192));
            }
            g2.fill(rect);

            AffineTransform at = new AffineTransform();
            Shape outline_rect = at.createTransformedShape(rect);
            Area outline = new Area(outline_rect);

            int deep = 2;
            for (int k = 0; k < deep; k += 1) {
                at.translate(0, k);
                g2.setStroke(new BasicStroke(1.0f));
                g2.transform(at);
                g2.setColor(new Color(150, 195, 255));
                g2.draw(outline_rect);
            }

            if (!handLocation) {
                xLoc = (float) (rect.getX() + 10); // + 10
                yLoc = (float) ((rect.getY() + rect.getHeight() - 10));
            }

            Font font1 = new Font("Book Antiqua", Font.PLAIN, 70);
            Font font = new Font("Monotype Corsiva", Font.BOLD, 26);
            TextLayout textLayout = new TextLayout(text, font, g2.getFontRenderContext());
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.setPaint(Color.black);
            textLayout.draw(g2, xLoc, yLoc);

            // button in is disabled state
            if (disabled) {
                Line2D line1 = new Line2D.Double(0.0D, 0.0D, outW, outH);
                Line2D line2 = new Line2D.Double(0.0D, 0.0D + outH, outW, 0.0D);

                g2.setStroke(new BasicStroke(3.0f));
                g2.setColor(Color.red);
                g2.draw(line1);
                g2.draw(line2);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            focus = true;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            focus = false;
            repaint();
        }

        @Override
        public String toString() {
            return this.id;
        }

    }
}
