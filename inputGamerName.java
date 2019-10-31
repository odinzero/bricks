package GAME_BRICKS;

import GRAPHIC2D_Software.componentBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Caret;

public class inputGamerName {

    JFrame frame;
    JPanel basePanel;
    JTextField tfield;
    utils.buttonsWindow enter;
    // if true  -> addMouseListener for save game in file 'saveGame'
    // if false -> addMouseListener for update records in file 'updateTableRecords'
    boolean saveGameORupdateRecords ;
    updateTable updateTable;
    BRICKS mainBRICKS;

    inputGamerName(BRICKS bricks, boolean saveGameORupdateRecords) { // updateTable upTable
        mainBRICKS = bricks;
        this.saveGameORupdateRecords = saveGameORupdateRecords;
//       updateTable = upTable;

        componentBevelBorder bevel = new componentBevelBorder(0, 2, "Gamer Name", 1, 1, new Font("Times New Roman", 1, 12),
                new Color(0, 100, 0), Color.white,
                new Color(190, 220, 220),
                new Color(100, 130, 130),
                new Color(150, 180, 180));
        // help customize form of JFrame
        frame = bevel.getJFrameForm();
        frame.setUndecorated(true);
//        frame.setLayout(null);
        bevel.setFloatable(false);
        frame.setMinimumSize(new Dimension(276, 170)); // 361, 300
        frame.setPreferredSize(new Dimension(276, 170));
        frame.setAlwaysOnTop(true);
        frame.toFront();
        //----------------------------------------------------------------------------------
        setContentPanel();
        utils.centerFrame(mainBRICKS.frame, frame);

        basePanel.setMinimumSize(frame.getMinimumSize());
        basePanel.setPreferredSize(frame.getPreferredSize());
        basePanel.setBorder(bevel);
        // allow drag JWIndow
        //bevel.getDnDMouseListener(basePanel);
        // set Behavior for control buttons in bevelBorder :
        // if first : '1' hide JWindow if '0' close JWindow
        // if second : '0' apply for frame , '1' apply for frame
        bevel.getControlButtonsBehavior(basePanel, 1, 0);

        Font font1 = new Font("Book Antiqua", Font.PLAIN, 20);
        Font font2 = new Font("Monotype Corsiva", Font.PLAIN, 26);

        JLabel inputGamerName = new JLabel("Input gamer name ");
        inputGamerName.setFont(font2);
        inputGamerName.setForeground(new Color(198, 137, 255));
        inputGamerName.setBounds(48, 25, 180, 35);
        basePanel.add(inputGamerName);

        tfield = new JTextField(30);
        tfield.setBackground(Color.YELLOW);
        tfield.setForeground(new Color(184, 109, 255));
        tfield.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        tfield.setFont(font1);
        tfield.setBounds(63, 65, 150, 30);

        tfield.getDocument().addDocumentListener(textControl());
        tfield.addCaretListener(limitName());
        basePanel.add(tfield);

        // button "Enter"
        enter = new utils.buttonsWindow("Enter", 80, 35, 12, 23);
        enter.setDisabledState(true);
        enter.setBounds(48, 115, 85, 40);
        basePanel.add(enter);

        // button "Cancel"
        utils.buttonsWindow cancel = new utils.buttonsWindow("Cancel", 85, 35, 7, 23);
        cancel.setDisabledState(false);
        cancel.addMouseListener(close());
        cancel.setBounds(143, 115, 90, 40);
        basePanel.add(cancel);

        frame.add(basePanel);
        frame.setVisible(false);
    }

    private CaretListener limitName() {

        CaretListener cl = new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                limitation(e.getDot());
            }

            protected void limitation(final int dot) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (dot > 9) {
                            tfield.setCaretPosition(8);
                            String t = tfield.getText().substring(0, 8);
                            tfield.setText("");
                            tfield.setText(t);
                        }
                    }
                });
            }
        };
        return cl;
    }

    private DocumentListener textControl() {

        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            boolean addListener = false;

            public void changed() {
                if (tfield.getText().equals("")) {
                    enter.setDisabledState(true);

                    addListener = false;
                    
                    if(saveGameORupdateRecords) {
                    // remove listener for saveGame
                    enter.removeMouseListener(mainBRICKS.mainMenu.savegame);
                    } else {
                    // remove listener for updateTableRecords
                    enter.removeMouseListener(mainBRICKS.update_tableRecords);    
                    }
                    
                    enter.repaint();
                    System.out.println("zero");
                } else {
                    if (!addListener) {
                        if(saveGameORupdateRecords) {
                        // add listener for saveGame
                        enter.addMouseListener(mainBRICKS.mainMenu.savegame);
                        } else {
                        // add listener for updateTableRecords
                        enter.addMouseListener(mainBRICKS.update_tableRecords);    
                        }
                        addListener = true;
                    }
                    enter.setDisabledState(false);
                    enter.repaint();
                    System.out.println("nonzero");
                }
            }
        };
        return dl;
    }

    private MouseAdapter close() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // just one click should open 'Options' submenu
                // see class:: menu
//                mainBricks.mainMenu.showOptionsSubMenu = false;
                // just one click should open 'Games' submenu
                // see class:: menu
//                mainBricks.mainMenu.showGamesSubMenu = false;

                // when input game name window used for  -> saving game
                if(saveGameORupdateRecords) {
                    
                  frame.setVisible(false);
                
                  mainBRICKS.restartMainThread();

                  mainBRICKS.startFencePanel();
                // when input game name window used for ->  inputting record of game 
                // and need do last choice  start new game or exit  
                } else {
                   // hide input gamer name when gamer do not want input new record
                   mainBRICKS.update_tableRecords.iGamerName.frame.setVisible(false); 
                   // show 'start' or 'exit' window
                   mainBRICKS.startORfinish_game.window.setVisible(true); 
                }
                
            }
        };
        return ma;
    }

    protected JPanel setContentPanel() {
        basePanel = new JPanel(null) {
            int w = frame.getWidth() - 1;
            int h = frame.getHeight() - 1;

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
        return basePanel;
    }

    public static void main(String[] args) {
//       new inputGamerName();
    }

}
