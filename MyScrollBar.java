
package GAME_BRICKS;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;

public class MyScrollBar {
    
    

    public JComponent makeUI(JComponent cmp) {
//        JTextArea cmp = new JTextArea();
////    String str = "1234567890abcdefghijklmnopqrstuvwxyz";
//        String str = "1234567890abcdefghij";
//        for (int i = 0; i < 20; i++) {
//            cmp.append(str + str + "\n");
//        }

//        cmp.setForeground(Color.WHITE);
//        cmp.setBackground(Color.BLACK);
//        cmp.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(
                cmp, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(100,100)); 
        scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
        scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
        scrollPane.getVerticalScrollBar().setOpaque(false);
//        scrollPane.setAutoscrolls(true); 

        scrollPane.setLayout(new ScrollPaneLayout() {
            @Override
            public void layoutContainer(Container parent) {
                JScrollPane scrollPane = (JScrollPane) parent;

                Rectangle availR = scrollPane.getBounds();
                availR.x = availR.y = 0;

                Insets insets = parent.getInsets();
                availR.x = insets.left;
                availR.y = insets.top;
                availR.width -= insets.left + insets.right;
                availR.height -= insets.top + insets.bottom;

                Rectangle vsbR = new Rectangle();
                vsbR.width = 12;
                vsbR.height = availR.height;
                vsbR.x = availR.x + availR.width - vsbR.width;
                vsbR.y = availR.y;

                if (viewport != null) {
                    viewport.setBounds(availR);
                    System.out.println("viewport: " + viewport.getBounds());
                }
                if (vsb != null) {
                    vsb.setVisible(true);
                    vsb.setBounds(vsbR);
                    System.out.println("viewport: " + vsb.getBounds());
                }
            }
        });
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            private final Dimension d = new Dimension();

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return d;
                    }
                };
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return d;
                    }
                };
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                Color color = null;
                JScrollBar sb = (JScrollBar) c;
                if (!sb.isEnabled() || r.width > r.height) {
                    return;
                } else if (isDragging) {
                    color = new Color(136, 245, 251, 200);
                } else if (isThumbRollover()) {
                    color = new Color(153,217,234,200);
                } else {
                    color = new Color(152,154,235,200);
                }
                g2.setPaint(color);
                g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
                g2.setPaint(new Color(255,255,255,200));
                g2.drawRoundRect(r.x, r.y, r.width, r.height, 10, 10);
                g2.dispose();
            }

            @Override
            protected void setThumbBounds(int x, int y, int width, int height) {
                super.setThumbBounds(x, y, width, height);
                scrollbar.repaint();
            }
            
        });
        return scrollPane;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JWindow f = new JWindow();
//        f.setLayout(null); 
        //f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
//                JTextArea cmp = new JTextArea();
//////    String str = "1234567890abcdefghijklmnopqrstuvwxyz";
//        String str = "1234567890abcdefghij";
//        for (int i = 0; i < 20; i++) {
//            cmp.append(str + str + "\n");
//        }
        JPanel cmp = new JPanel(null);
        cmp.setBounds(0, 0, 300, 300);
        cmp.setBackground(Color.black);
//        cmp.setPreferredSize(new Dimension(300,300));
//        cmp.setMinimumSize(new Dimension(300,300));
        
        JPanel cmp1 = new JPanel(null);
        cmp1.setBackground(Color.red); 
        cmp1.setBounds(100, 50, 100, 100);
//        cmp1.setPreferredSize(new Dimension(100,100));
//        cmp1.setMinimumSize(new Dimension(100,100));
        
       
        
//        JScrollBar bar = new JScrollBar();
//        
//        bar.setVisibleAmount(50); 
//        bar.add(cmp1);
       
       // scroll.makeUI(cmp1).setPreferredSize(new Dimension(50,50)); 
        
//        JScrollPane sp = new JScrollPane(cmp1); 
//        sp.setBackground(Color.blue);  
//        sp.setBounds(0, 0, 50, 50);
//        sp.setPreferredSize(new Dimension(50,50));
//        sp.setMinimumSize(new Dimension(50,50));
       // sp.add(cmp1);
        
         MyScrollBar scroll = new MyScrollBar();
//        cmp.add(scroll.makeUI(cmp1));
        
        f.add(scroll.makeUI(cmp1));
        f.setSize(320, 240);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

