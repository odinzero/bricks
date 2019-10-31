package GAME_BRICKS;

import GRAPHIC2D_Software.componentBevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.awt.geom.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class tableOfRecords {

    boolean turnOn = false;
    JPanel base;
    JWindow window;
    JLabel[] results;
    updateTable table;
    inputGamerName inputgamername;
    BRICKS mainBRICKS;
//    String showNewRecord = "odin 30 11:01:56AM 3/10/2016";

    JLabel[] headers;
    JLabel[] labs;
    JLabel[] emptyLabs;
    String[] heades_names = {"N", "Name", "Score", "Time", "Date"};
    String[][] empty_lines;
    JPanel[] backbone;
    int findLine = -1;
    Thread newRecordThread;
    buttons close, reset;

    tableOfRecords(BRICKS main) { // BRICKS main

        mainBRICKS = main;

        componentBevelBorder bevel = new componentBevelBorder(0, 2, "Records", 1, 1, new Font("Times New Roman", 1, 12),
                new Color(0, 100, 0), Color.white,
                new Color(190, 220, 220),
                new Color(100, 130, 130),
                new Color(150, 180, 180));
        // help customize form of Window
        window = bevel.getWindowForm();
//        window.setLayout(null);
        bevel.setFloatable(true);
        window.setMinimumSize(new Dimension(430, 320)); // 361, 300
        window.setPreferredSize(new Dimension(430, 320));
        window.setAlwaysOnTop(true);
        center();

        // --------------  base panel -------------------------------------------------
        setContentPanel();
//        center();

        base.setMinimumSize(window.getMinimumSize());
        base.setPreferredSize(window.getPreferredSize());
        base.setBorder(bevel);
        // allow drag JWIndow
        bevel.getDnDMouseListener(base);
        // set Behavior for control buttons in bevelBorder :
        bevel.getControlButtonsBehavior(base, 1, 1); // if '1' hide JWindow if '0' close JWindow

        close = new buttons("Close");
        reset = new buttons("Reset");
        close.setButtonAction(0);
        reset.setButtonAction(1);
        close.setBounds(121, 265, 72, 37);
        reset.setBounds(206, 265, 72, 37);
        base.add(close);
        base.add(reset);
//------------------------------------------------------------------------------
        String[] bigRecordsLines = null;

        FileInputStream fis = null;
        String path = "src/GAME_BRICKS/Records.txt";
        File file = new File(path);
        try {

            fis = new FileInputStream(file);
            // contains all content file 'Records.txt'
            String records = utils.readFileContent(fis, "UTF-8");
            fis.close();
            // array contains files lines -> { 1 g1 40 01:48:03PM 15/04/2013 }
            bigRecordsLines = records.split("\n");

            // array contains -> { 1, zero, 1050, 08:39:47AM, 23/2/2014 }
            String[] lines = new String[heades_names.length];
            // array contains -> {0} {1, zero, 1050, 08:39:47AM, 23/2/2014 }
            //                   {1} {2, swan, 350,  07:39:47PM, 1/9/2017 }
            String[][] recordsDataLines = new String[bigRecordsLines.length][heades_names.length];

            for (int i = 0; i < bigRecordsLines.length; i++) {
                // array contains -> { 1, zero, 1050, 08:39:47AM, 23/2/2014 }
                lines = bigRecordsLines[i].split(" ");
                for (int j = 0; j < lines.length; j++) {

                    recordsDataLines[i][j] = lines[j];
                }
            }

            String[] empty_line = {"--", "-----", "-----", "-:-:---", "-/-/---"};
            // array contains ->  {0} {"--", "-----", "-----", "-:-:---", "--", "-/-/---"}
            //                    {1} {"--", "-----", "-----", "-:-:---", "--", "-/-/---"}
            empty_lines = new String[10][empty_line.length];
            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < empty_line.length; j++) {
                    String string = empty_line[j];
                    empty_lines[i][j] = string;
                }
            }

            // Display headers "N"  "Name"  "Score"  "Time"  "Date"
            layoutHeaders(headers, heades_names, heades_names.length, -15, 30);
            // Display records lines like as 
            // 1  zero  1050  08:39:47AM  23/2/2014
            layoutLine(labs, recordsDataLines, recordsDataLines.length, heades_names.length, -15, 60);

            // System.out.println(mainBRICKS.update_tableRecords.showNewRecord);
            if (mainBRICKS.update_tableRecords != null) {
                if (mainBRICKS.update_tableRecords.showNewRecord != "") {
                    for (int i = 0; i < bigRecordsLines.length; i++) {
                        String string = bigRecordsLines[i];
                        // if table of records contains new record
                        if (string.endsWith(mainBRICKS.update_tableRecords.showNewRecord)) {
                            //  System.out.println("showNewRecord i : " + i);
                            findLine = i;
                        }
                    }
                }
            }

            if (findLine != -1) {
                startHighlightNewRecord();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

//        base.setFocusable(true);
        window.setFocusable(true);
        window.setFocusableWindowState(true);
//        window.addKeyListener(this);
        window.add(base);
        window.setVisible(true);

    }

    public void setWindowVisibility(boolean bool) {
        window.setVisible(bool);
    }

    public void center() {
        Dimension screenSize = mainBRICKS.frame.getSize();
//        Dimension frameSize = window.getSize();
        int x = ((mainBRICKS.frame.getLocation().x + screenSize.width) / 2) - 100;
        int y = ((mainBRICKS.frame.getLocation().y + screenSize.height) / 2) - 100;
        window.setLocation(x, y);
    }

    // array 'line_x' ->  {0, 1, 2, 3, 4}
    // this JLabels which contains info in array 'line'
    // --------------------------------------------------------------
    // array 'line'   ->  {0} {1, zero, 1050, 08:39:47AM, 23/2/2014 }
    //                    {1} {2, swan, 350,  07:39:47PM, 1/9/2017 }
    protected void layoutLine(JLabel[] line_x, String[][] line, int len1, int len2, int x, int y) {
        Font font = new Font("Monotype Corsiva", Font.BOLD, 20);
        backbone = new JPanel[len1];
        line_x = new JLabel[len2];
        int xx = x;
        int yy = y;
        for (int i = 0; i < len1; i++) {
            if (i != 0) {
                yy += 25;
            }
            backbone[i] = new JPanel();
            SpringLayout layout = new SpringLayout();
            backbone[i].setLayout(layout);
            backbone[i].setBounds(10, yy, 410, 24);
            backbone[i].setOpaque(false);
            //backbone[i].setBackground(Color.white);

            for (int k = 0; k < len2; k++) {
                xx += 25; // += 25
                line_x[k] = new JLabel(line[i][k]);
                if (k == 0) {    // N
                    // line_x[k].setBounds(xx, yy, 25, 20); // (xx, yy, 25, 20);
                    SpringLayout.Constraints c = layout.getConstraints(line_x[k]);
                    c.setX(Spring.constant(5));
                    c.setWidth(Spring.constant(25));

                    //  c.anchor = GridBagConstraints.EAST;
                    line_x[k].setForeground(Color.BLUE);
                } else if (k == 1) {  // User
                    // line_x[k].setBounds(xx, yy, 90, 20); //  (xx, yy, 90, 20);
                    SpringLayout.Constraints c = layout.getConstraints(line_x[k]);
                    c.setX(Spring.constant(50));
                    c.setWidth(Spring.constant(50));

                    line_x[k].setForeground(new Color(216, 112, 255));
                } else if (k == 2) {  // Score
                    // line_x[k].setBounds(xx + 60, yy, 75, 20); // (xx + 60, yy, 75, 20);
                    SpringLayout.Constraints c = layout.getConstraints(line_x[k]);
                    c.setX(Spring.constant(120));
                    c.setWidth(Spring.constant(60));

                    line_x[k].setForeground(Color.MAGENTA);
                } else if (k == 3) {  // Time
                    // line_x[k].setBounds(xx + 100, yy, 135, 20); // (xx + 100, yy, 135, 20);
                    SpringLayout.Constraints c = layout.getConstraints(line_x[k]);
                    c.setX(Spring.constant(190));
                    c.setWidth(Spring.constant(110));

                    line_x[k].setForeground(new Color(255, 106, 0));
                } else if (k == 4) {  // Date
                    // line_x[k].setBounds(xx + 190, yy, 105, 20);
                    SpringLayout.Constraints c = layout.getConstraints(line_x[k]);
                    c.setX(Spring.constant(310));
                    c.setWidth(Spring.constant(110));

                    line_x[k].setForeground(new Color(255, 106, 0));
                    xx = x;
                }

//                else if (k == 5) {
//                    line_x[k].setBounds(xx + 165, yy, 105, 20);
//                    line_x[k].setForeground(Color.ORANGE);
//                }
                line_x[k].setFont(font);
                backbone[i].add(line_x[k]);
            }
        }
        
        JPanel scrollPanel = new JPanel(null);
        scrollPanel.setBounds(10, 40, 380, 380);
        scrollPanel.setBackground(Color.white);
//        scrollPanel.setPreferredSize(new Dimension(390,400));
//        scrollPanel.setMinimumSize(new Dimension(390,400));

        for (int i = 0; i < backbone.length; i++) {
            JPanel jPanel = backbone[i];
           // base.add(jPanel);
            scrollPanel.add(jPanel);
        }
        
        base.add(new MyScrollBar().makeUI(scrollPanel));
        base.setComponentZOrder(scrollPanel, 3);
    }

    protected void layoutHeaders(JLabel[] line_x, String[] line, int len, int x, int y) {
        Font font = new Font("Monotype Corsiva", Font.BOLD, 26);
        line_x = new JLabel[len];
        for (int k = 0; k < len; k++) {
            x += 25;
            line_x[k] = new JLabel(line[k]);
            if (k == 0) {  // N
                line_x[k].setBounds(x, y, 45, 20);
                line_x[k].setForeground(Color.BLUE);
            } else if (k == 1) {  //  User
                line_x[k].setBounds(x + 15, y, 90, 20);
                line_x[k].setForeground(new Color(216, 112, 255));
            } else if (k == 2) {  // Score
                line_x[k].setBounds(x + 65, y, 75, 20);
                line_x[k].setForeground(Color.MAGENTA);
            } else if (k == 3) { // Time
                line_x[k].setBounds(x + 125, y, 95, 20);
                line_x[k].setForeground(new Color(255, 106, 0));
            } else if (k == 4) { // Date
                line_x[k].setBounds(x + 210, y, 75, 20);
                line_x[k].setForeground(Color.ORANGE);
            }

            line_x[k].setFont(font);
            base.add(line_x[k]);
        }
    }

    protected JPanel setContentPanel() {
        base = new JPanel(null) {
            int w = window.getWidth() - 1;
            int h = window.getHeight() - 1;

            public void paintComponent(Graphics g) {
//             super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                Rectangle2D rect = new Rectangle2D.Double(0, 0, w, h);

                g2.setPaint(new GradientPaint(0, 0, new Color(255, 242, 226), w, h, new Color(213, 255, 122), false));
                g2.fill(rect);
                g2.setColor(Color.BLUE);
                g2.draw(rect);
            }

        };
        return base;
    }

    public void stopHighlightNewRecord() {
//        SwingUtilities.invokeLater(
//                new Runnable() {
//                    @Override
//                    public void run() {
        if (newRecordThread != null) {
            if (newRecordThread.isAlive()) {
                turnOn = false;
                // remove RED color background
                backbone[findLine].setOpaque(false);
                base.repaint();
                base.revalidate();
                System.out.println("tableOfRecords Thread");
                // Notify mainthread
                synchronized (newRecordThread) {
                    newRecordThread.notify();
                }
            }
        }
    }
//                });
//    }

    public void startHighlightNewRecord() {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        turnOn = true;
                        newRecordThread = new Thread(highlight);
                        newRecordThread.start();
                        // Notify mainthread
                        synchronized (newRecordThread) {
                            newRecordThread.notify();
                        }
                    }
                });
    }

    Thread highlight = new Thread() {
        int cnt = -1;

        @Override
        public void run() {
            while (turnOn) {
                try {
                    Thread.currentThread().sleep(600);
                    cnt++;
                    if (cnt == 40) {
                        turnOn = false;
                        backbone[findLine].setOpaque(false);
                    } else {
                        if (cnt % 2 == 0) {
                            backbone[findLine].setOpaque(true);
                            backbone[findLine].setBackground(Color.red);
                        } else {
                            backbone[findLine].setOpaque(false);
                        }
                    }
                    base.repaint();
                    base.revalidate();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    };

    class buttons extends JComponent implements MouseListener {

        boolean focus = false;
        String text;
        int action;

        buttons(String str) {
            text = str;
            this.addMouseListener(this);
        }

        private void setButtonAction(int a) {
            action = a;
        }

        private int getButtonAction() {
            return action;
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, 70, 35, 25, 20);
            int w = (int) rect.getWidth();
            int h = (int) rect.getHeight();
            g2.setStroke(new BasicStroke(0.5f));

            if (!focus) {
                g2.setPaint(new GradientPaint(0, 0, new Color(84, 255, 124), 0, h, new Color(255, 243, 15), false));// 192,255,158
            } else {
                g2.setPaint(new GradientPaint(0, 0, new Color(84, 255, 124), 0, h, Color.RED, false));
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

            float xLoc = (float) (rect.getX() + 10);
            float yLoc = (float) ((rect.getY() + rect.getHeight() - 10));

            Font font1 = new Font("Book Antiqua", Font.PLAIN, 70);
            Font font = new Font("Monotype Corsiva", Font.BOLD, 26);
            TextLayout textLayout = new TextLayout(text, font, g2.getFontRenderContext());
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.setPaint(Color.black);
            textLayout.draw(g2, xLoc, yLoc);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // 'Close' button
            if (getButtonAction() == 0) {

                // stop highlight new Record
                stopHighlightNewRecord();

                if (mainBRICKS.update_tableRecords != null) {
                    mainBRICKS.update_tableRecords.showNewRecord = "";

                    SwingUtilities.invokeLater(
                            new Runnable() {
                                @Override
                                public void run() {
                                    // show 'start' or 'exit' window
                                    mainBRICKS.startORfinish_game.window.setVisible(true);
                                    mainBRICKS.startORfinish_game.window.toFront();
                                }
                            });
                    //
                    mainBRICKS.update_tableRecords = null;
                }

                window.setVisible(false);
            }
            // 'Reset' button
            if (getButtonAction() == 1) {
                FileOutputStream fout = null;
                try {
                    String path = "/-== BACKUP_netbeans_projects ==-/NetBeansProjects/SWING_1/src/GAME_BRICKS/Records.txt";
                    File fo = new File(path);
                    fout = new FileOutputStream(fo, false);

                    base.removeAll();
                    close.setButtonAction(0);
                    reset.setButtonAction(1);
                    close.setBounds(121, 265, 72, 37);
                    reset.setBounds(206, 265, 72, 37);
                    base.add(close);
                    base.add(reset);
                    layoutHeaders(headers, heades_names, heades_names.length, -15, 30);

                    layoutLine(emptyLabs, empty_lines, empty_lines.length, heades_names.length, -15, 60);

                    String[] emptyRecords = new String[10];
                    for (int i = 0; i < 10; i++) {
                        String string = "--" + " "
                                + "-----" + " "
                                + "-----" + " "
                                + "-:-:---" + " " + "-/-/---";
                        emptyRecords[i] = string;
                        // System.out.println(emptyRecords[i]);        
                    }

                    String data = String.join("\n", emptyRecords);

                    utils.writeFileContent(path, data);

                    window.repaint();
                    // stop highlight new Record
                    stopHighlightNewRecord();

                    if (mainBRICKS.update_tableRecords != null) // reset new record to null
                    {
                        mainBRICKS.update_tableRecords.showNewRecord = "";
                    }
                } catch (IOException ex) {
                } finally {
                    try {
                        fout.close();
                    } catch (IOException ex) {
                    }
                }
            }
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
    }

    public static void main(String[] args) throws IOException {
//        new tableOfRecords();
    }
}
