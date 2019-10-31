package GAME_BRICKS;

import GRAPHIC2D_Software.componentBevelBorder;
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class menu {

    JPanel menuPanel;
    buttons optionsMenuItem, statisticsMenuItem, helpMenuItem, gamesMenuItem,
            showMenuButton, // strelkaLeft
            hideMenuButton, // strelkaRight
            exitButton;
    //Menu 'Options' -> 'Start game','pause game','Settings...','exit' 
    buttons newGameMenuItem, pauseGameMenuItem, startGameMenuItem,
            saveGameMenuItem, downloadGameMenuItem, removeGameMenuItem,
            settingsGameMenuItem, exitGameMenuItem; 
    //Menu 'Games' -> submenu 'Save game','Download game','remove game' 
    buttons saveGame, downloadGame, removeGame;
    //Menu 'Help'  -> submenu 'Help','About'
    buttons helpMenuSubItem, aboutMenuSubItem;
    
    // when one click on 'Options' -> show suboptions menu
    // when second click on 'Options' -> hide suboptions menu
    boolean showOptionsSubMenu = false; 
    // when one click on 'Games' -> show submenu games
    // when second click on 'Games' -> hide submenu games
    boolean showGamesSubMenu = false;
    // when one click on 'Help' -> show submenu games
    // when second click on 'Help' -> hide submenu games
    boolean showHelpSubMenu = false;

    // prevent repeat click menu items(options, statistics or help)
    boolean blockMenu = false;

    BRICKS mainBricks;
    //Menu 'Options'  -> 'Start game','pause game','Settings...','exit' 
    //Menu 'Games'    -> submenu 'Save game','Download game','remove game'
    patternWindow helpMenu, Help, About, Options, Games;
    tableOfRecords Statistics;
    submenuSettings submenuSettingsWindow;
    downloadRemoveGame downloadGameWindow, removeGameWindow;
    saveGame savegame;

    menuActions ma;

    menu(BRICKS bricks) {
        mainBricks = bricks;
        
        ma = new menuActions();

        //---------------------- Options ---------------------------------------
        optionsWindow();
        
        // Submenu 'Save Game'
        savegame = new saveGame(mainBricks);
        // Submenu 'Download Game'
        downloadGameWindow = new downloadRemoveGame(mainBricks, false);
        // Submenu 'Remove Game'
        removeGameWindow   = new downloadRemoveGame(mainBricks, true);
        // Submenu 'Settings .......'
        submenuSettingsWindow = new submenuSettings(mainBricks); 
        
        getInitStateLabelsScoreLifeLevel();
        //---------------------- Games -----------------------------------------
        gamesWindow();

        //---------------------- Statistics ------------------------------------
        Statistics = new tableOfRecords(mainBricks); // new updateTable(mainBricks)
        Statistics.setWindowVisibility(false);
        //------------------------ Help ----------------------------------------
        helpSubMenu();
        helpWindow();
        aboutWindow();

        menuPanel = new JPanel(null);
        menuPanel.setSize(new Dimension(945, 30));
        menuPanel.setBackground(new Color(130, 220, 220));
        menuPanel.setVisible(false);

        Rectangle rect1 = new Rectangle(1, 1, 90, 24);
        optionsMenuItem = new buttons("Options", rect1);
        optionsMenuItem.setBounds(1, 1, 90, 27);
        
        Rectangle rect2 = new Rectangle(1, 1, 65, 24);
        gamesMenuItem = new buttons("Games", rect2);
        gamesMenuItem.setBounds(115, 1, 65, 27);

        Rectangle rect3 = new Rectangle(1, 1, 95, 24);
        statisticsMenuItem = new buttons("Statistics", rect3);
        statisticsMenuItem.setBounds(210, 1, 95, 27);

        Rectangle rect4 = new Rectangle(1, 1, 50, 24);
        helpMenuItem = new buttons("Help", rect4);
        helpMenuItem.setBounds(330, 1, 50, 27);

        optionsMenuItem.addMouseListener(ma);
        gamesMenuItem.addMouseListener(ma);
        statisticsMenuItem.addMouseListener(ma);
        helpMenuItem.addMouseListener(ma);
//        optionsMenuItem.addMouseListener(rma);
//        statisticsMenuItem.addMouseListener(rma);
//        helpMenuItem.addMouseListener(rma);

        menuPanel.add(optionsMenuItem);
        menuPanel.add(gamesMenuItem);
        menuPanel.add(statisticsMenuItem);
        menuPanel.add(helpMenuItem);

        mainBricks.basic.add(menuPanel);

        showMenuButton = new buttons(2); // button for displaying menu
        showMenuButton.setBounds(945, 0, 24, 24);
        showMenuButton.addMouseListener(ma); // add mouse listener
        showMenuButton.setVisible(true);

        hideMenuButton = new buttons(3); // button for hide menu
        hideMenuButton.setBounds(945, 0, 24, 24);
        hideMenuButton.addMouseListener(ma); // add mouse listener
        hideMenuButton.setVisible(false);

        exitButton = new buttons(1); // button for common exit
        exitButton.setBounds(975, 3, 24, 24);

        mainBricks.basic.add(showMenuButton);
        mainBricks.basic.add(hideMenuButton);
        mainBricks.basic.add(exitButton);
    }

    private boolean defineSource(String str1, String str2) {
        return str1.contains(str2);
    }
    
    // get 
    private void getInitStateLabelsScoreLifeLevel() {
        
        if (submenuSettingsWindow.checkboxScore.getPressedState() == false &&
            submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
            submenuSettingsWindow.checkboxLevel.getPressedState() == false    ) {
             mainBricks.score.setBounds( 30, 50, 206, 26);
             mainBricks.life.setBounds(  30, 80, 106, 26);
             mainBricks.level.setBounds( 30, 110,106, 26);
             mainBricks.basic.add(mainBricks.score);
             mainBricks.basic.add(mainBricks.life);
             mainBricks.basic.add(mainBricks.level);
        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == true &&
                  submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
                  submenuSettingsWindow.checkboxLevel.getPressedState() == false   ) {
             mainBricks.life.setBounds(  30, 50, 106, 26);
             mainBricks.level.setBounds( 30, 80,106, 26);
             mainBricks.basic.add(mainBricks.life);
             mainBricks.basic.add(mainBricks.level);
        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == false &&
                  submenuSettingsWindow.checkboxLife.getPressedState()  == true &&
                  submenuSettingsWindow.checkboxLevel.getPressedState() == false   ) {
             mainBricks.score.setBounds( 30, 50, 206, 26);
             mainBricks.level.setBounds( 30, 80,106, 26);
             mainBricks.basic.add(mainBricks.score);
             mainBricks.basic.add(mainBricks.level);
        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == false &&
                  submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
                  submenuSettingsWindow.checkboxLevel.getPressedState() == true   ) {
             mainBricks.score.setBounds( 30, 50, 206, 26);
             mainBricks.life.setBounds( 30, 80,106, 26);
             mainBricks.basic.add(mainBricks.score);
             mainBricks.basic.add(mainBricks.life);
        }
         else if(submenuSettingsWindow.checkboxScore.getPressedState() == true &&
                  submenuSettingsWindow.checkboxLife.getPressedState()  == true &&
                  submenuSettingsWindow.checkboxLevel.getPressedState() == false) {
             mainBricks.level.setBounds( 30, 50,106, 26);
             mainBricks.basic.add(mainBricks.level);
        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == true &&
                  submenuSettingsWindow.checkboxLife.getPressedState()  == false &&
                  submenuSettingsWindow.checkboxLevel.getPressedState() == true) {
             mainBricks.life.setBounds( 30, 50,106, 26);
             mainBricks.basic.add(mainBricks.life);
        } else if(submenuSettingsWindow.checkboxScore.getPressedState() == false &&
                  submenuSettingsWindow.checkboxLife.getPressedState()  == true &&
                  submenuSettingsWindow.checkboxLevel.getPressedState() == true) {
             mainBricks.score.setBounds( 30, 50,206, 26);
             mainBricks.basic.add(mainBricks.score);
        }
    }
    
    

    private void optionsWindow() {
        Options = new patternWindow(mainBricks, "", false, 1, false, false, 6, 35, 265, 280);
        Options.base.setBackground(new Color(190, 225, 220));

        // Menu 'Options' ->  Submenu  'Start game'
        Rectangle rect1 = new Rectangle(5, 2, 150, 30);
        newGameMenuItem = new buttons("New game  -  s ", rect1);
        newGameMenuItem.setBounds(5, 2, 150, 30);
        newGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
                 try {
                     mainBricks.startNewGame();
                 } catch (InterruptedException ex) {
                    ex.printStackTrace();
                 }
             Options.setWindowVisibility(false);
           }
        });

        // Menu 'Options' ->  Submenu  'Pause game'
        Rectangle rect2 = new Rectangle(5, 2, 165, 30);
        pauseGameMenuItem = new buttons("Pause game  -  p ", rect2);
        pauseGameMenuItem.setBounds(5, 32, 165, 30);
        pauseGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             mainBricks.pauseGame();
             Options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Start game'
        Rectangle rect3 = new Rectangle(5, 2, 160, 30);
        startGameMenuItem = new buttons("Start game  -  x ", rect3);
        startGameMenuItem.setBounds(5, 62, 160, 30);
        startGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
                 try {
                     mainBricks.startGame();
                 } catch (InterruptedException ex) { ex.printStackTrace(); }
             Options.setWindowVisibility(false);
           }
        });
        
        separator s1 = new separator();
        s1.setBounds(12, 98, 160, 10);
        Options.base.add(s1);
        separator s2 = new separator();
        s2.setBounds(102, 98, 160, 10);
        Options.base.add(s2);
        
        // Menu 'Options' ->  Submenu  'Save game'
        Rectangle rect4 = new Rectangle(5, 2, 200, 30);
        saveGameMenuItem = new buttons("Save game  -  Ctrl+s ", rect4);
        saveGameMenuItem.setBounds(5, 108, 200, 30);
        saveGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             showSaveGameWindow();
             // stop main thread in order to input name user
             mainBricks.suspend.set(true);   
             Options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Download game'
        Rectangle rect5 = new Rectangle(5, 2, 255, 30);
        downloadGameMenuItem = new buttons("Download game  -  Ctrl+d ", rect5);
        downloadGameMenuItem.setBounds(5, 138, 255, 30);
        downloadGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             downloadWindow();
             Options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Remove game'
        Rectangle rect6 = new Rectangle(5, 2, 230, 30);
        removeGameMenuItem = new buttons("Remove game  -  Ctrl+r ", rect6);
        removeGameMenuItem.setBounds(5, 168, 230, 30);
        removeGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             removeWindow();
             Options.setWindowVisibility(false);
           }
        });
        
        // Menu 'Options' ->  Submenu  'Settings'
        Rectangle rect7 = new Rectangle(5, 2, 160, 30);
        settingsGameMenuItem = new buttons("Settings ...........", rect7);
        settingsGameMenuItem.setBounds(5, 198, 160, 30);
        settingsGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             settingsWindow();
             Options.setWindowVisibility(false);
           }
        });
        
        separator s3 = new separator();
        s3.setBounds(12, 234, 160, 10);
        Options.base.add(s3);
        separator s4 = new separator();
        s4.setBounds(102, 234, 160, 10);
        Options.base.add(s4);
        
        // Menu 'Options' ->  Submenu  'Exit'
        Rectangle rect8 = new Rectangle(5, 2, 50, 30);
        exitGameMenuItem = new buttons("Exit ", rect8);
        exitGameMenuItem.setBounds(107, 244, 50, 30);
        exitGameMenuItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             System.exit(0); 
           }
        });

        Options.base.add(newGameMenuItem);
        Options.base.add(pauseGameMenuItem);
        Options.base.add(startGameMenuItem);
        Options.base.add(saveGameMenuItem);
        Options.base.add(downloadGameMenuItem);
        Options.base.add(removeGameMenuItem);
        Options.base.add(settingsGameMenuItem);
        Options.base.add(exitGameMenuItem);
        Options.setWindowVisibility(false);
    }
    
    private void gamesWindow() {
        Games = new patternWindow(mainBricks, "", false, 1, false, false, 115, 35, 175, 105);
        Games.base.setBackground(new Color(190, 225, 220));

        // Menu 'Games' ->  Submenu  'Save game'
        Rectangle rect1 = new Rectangle(5, 2, 130, 30);
        saveGame = new buttons("Save game    ", rect1);
        saveGame.setBounds(5, 2, 150, 30);
        saveGame.addMouseListener(ma); 
        saveGame.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             showSaveGameWindow();
             Games.setWindowVisibility(false);
           }
        });

        // Menu 'Game' ->  Submenu  'Download game'
        Rectangle rect2 = new Rectangle(5, 2, 160, 30);
        downloadGame = new buttons("Download game ", rect2);
        downloadGame.setBounds(5, 32, 160, 30);
        downloadGame.addMouseListener(ma); 
        downloadGame.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             // Submenu 'Download Game'
             downloadGameWindow = new downloadRemoveGame(mainBricks, false);
             // set 'downloadGameWindow' visible true
             downloadWindow();
             Games.setWindowVisibility(false);
           }
        });
        
        // Menu 'Game' ->  Submenu  'Remove game'
        Rectangle rect3 = new Rectangle(5, 2, 160, 30);
        removeGame = new buttons("Remove game     ", rect3);
        removeGame.setBounds(5, 62, 160, 30);
        removeGame.addMouseListener(ma); 
        removeGame.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             // Submenu 'Remove Game'
             removeGameWindow = new downloadRemoveGame(mainBricks, true);
             // set 'removeGameWindow' visible true
             removeWindow();
             Games.setWindowVisibility(false);
           }
        });
        
        Games.window.addMouseListener(ma);        

        Games.base.add(saveGame);
        Games.base.add(downloadGame);
        Games.base.add(removeGame);
        Games.setWindowVisibility(false);
    }
    
    private void helpSubMenu() {
        helpMenu = new patternWindow(mainBricks, "", false, 1, false, false, 325, 35, 120, 70);
        helpMenu.base.setBackground(new Color(190, 225, 220));

        // Menu 'Help' ->  Submenu  'Help'
        Rectangle rect1 = new Rectangle(5, 2, 115, 30);
        helpMenuSubItem = new buttons("Reference  ", rect1);
        helpMenuSubItem.setBounds(5, 2, 150, 30);
        helpMenuSubItem.addMouseListener(ma); 
        helpMenuSubItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             Help.setWindowVisibility(true);
             helpMenu.setWindowVisibility(false);
           }
        });
        
        // Menu 'Help' ->  Submenu  'About ...'
        Rectangle rect2 = new Rectangle(5, 2, 105, 30);
        aboutMenuSubItem = new buttons("About ...  ", rect2);
        aboutMenuSubItem.setBounds(5, 32, 150, 30);
        aboutMenuSubItem.addMouseListener(ma); 
        aboutMenuSubItem.addMouseListener(new MouseAdapter(){         
             @Override
        public void mousePressed(MouseEvent e) {
             About.setWindowVisibility(true);
             helpMenu.setWindowVisibility(false);
           }
        });
        
        helpMenu.base.add(helpMenuSubItem);
        helpMenu.base.add(aboutMenuSubItem);
        helpMenu.setWindowVisibility(false);
    }
    
    private void aboutWindow() {
        About = new patternWindow(mainBricks, "Help", true, 0, true, true, 0, 0, 450, 195);
        About.base.setBackground(new Color(190, 225, 220));
        
        About.addContent("Copyright by Alexey Kravchenko", 30, 15, 40, 440, 35);
        About.addContent(" 2014 to 2018", 30, 125, 80, 205, 35);
        
        About.addButtonsWindow("Close", 191, 130, 72, 37).setButtonAction(0);// +50 +65 
        About.setWindowVisibility(false);
    }
    
    // Menu 'Games' -> submenu 'Save Game'
    private void showSaveGameWindow() {
        savegame.iGamerName.frame.setVisible(true); 
    }
    
    // Menu 'Games' -> submenu 'Download Game'
    public void downloadWindow() {
        downloadGameWindow.downloadWindow.setWindowVisibility(true);
    }
    
    // Menu 'Games' -> submenu 'Remove Game'
    public void removeWindow() {
        removeGameWindow.removeWindow.setWindowVisibility(true);
    }
    
    // Menu 'Options' -> submenu 'Settings'
    private void settingsWindow() {
        submenuSettingsWindow.settings.setWindowVisibility(true); 
    }
  
    private void helpWindow() {
        Help = new patternWindow(mainBricks, "Help", true, 0, true, true, 0, 0, 400, 515);
        // (String str, int fontSize, int x, int y, int width, int height)
        Help.addContent("s  -  ", 30, 45, 40, 85, 35);
        Help.addContent("start new game ", 30, 105, 40, 205, 35);

        Help.addContent("p  -  ", 30, 45, 80, 85, 35);
        Help.addContent("pause  game ", 30, 105, 80, 205, 35);
        
        Help.addContent("x  -  ", 30, 45, 120, 85, 35);
        Help.addContent("start game ", 30, 105, 120, 205, 35);
        
        separator s1 = new separator();
        s1.setBounds(45, 165, 160, 10);
        separator s2 = new separator();
        s2.setBounds(165, 165, 160, 10);
        separator s3 = new separator();
        s3.setBounds(195, 165, 160, 10);
        Help.base.add(s1);
        Help.base.add(s2);
        Help.base.add(s3);
        
        Help.addContent("Ctrl+s  - ", 30, 45, 180, 135, 35);
        Help.addContent("save game ", 30, 175, 180, 205, 35);
        
        Help.addContent("Ctrl+d  - ", 30, 45, 220, 130, 35);
        Help.addContent("download game ", 30, 175, 220, 205, 35);
        
        Help.addContent("Ctrl+r  - ", 30, 45, 260, 135, 35);
        Help.addContent("remove game ", 30, 175, 260, 205, 35);
        
        separator s4 = new separator();
        s4.setBounds(45, 305, 160, 10);
        separator s5 = new separator();
        s5.setBounds(165, 305, 160, 10);
        separator s6 = new separator();
        s6.setBounds(195, 305, 160, 10);
        Help.base.add(s4);
        Help.base.add(s5);
        Help.base.add(s6);
        
        Help.addContent("Ctrl+t  - ", 30, 45, 320, 135, 35);
        Help.addContent("show Settings ", 30, 175, 320, 205, 35);
        
        Help.addContent("Ctrl+c  - ", 30, 45, 360, 135, 35);
        Help.addContent("show Statistics ", 30, 175, 360, 205, 35);
        
        Help.addContent("Ctrl+h  - ", 30, 45, 400, 135, 35);
        Help.addContent("show Help ", 30, 175, 400, 205, 35);       

        // Action 0 <- it means close window
        Help.addButtonsWindow("Close", 171, 450, 72, 37).setButtonAction(0); // +45 +65
        Help.setWindowVisibility(false);
    }

    class separator extends JComponent {

        @Override
        public void paint(Graphics g) {
            Graphics2D graphics2d = (Graphics2D) g;
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));

            Rectangle2D spaceP = new Rectangle2D.Double(1.0D, 1.0D, 22, 22);
            
            Line2D line1 = new Line2D.Double(1.0D, 10.0D, 30.0D, 1.0D);
            Line2D line2 = new Line2D.Double(30.0D, 1.0D, 30.0D, 10.0D);
            Line2D line3 = new Line2D.Double(30.0D, 10.0D,60.0D, 1.0D);
            Line2D line4 = new Line2D.Double(60.0D, 1.0D, 60.0D, 10.0D);
            Line2D line5 = new Line2D.Double(60.0D, 10.0D, 90.0D, 1.0D);
            Line2D line6 = new Line2D.Double(90.0D, 1.0D, 90.0D, 10.0D);
            Line2D line7 = new Line2D.Double(90.0D, 10.0D, 120.0D, 1.0D);
            Line2D line8 = new Line2D.Double(120.0D, 1.0D, 120.0D, 10.0D);
            Line2D line9 = new Line2D.Double(120.0D, 10.0D, 150.0D, 1.0D);
            Line2D line10 = new Line2D.Double(150.0D, 1.0D, 150.0D, 10.0D);
            
            Area area = new Area(line1);
            area.add(new Area(line2));
            area.add(new Area(line3));
            area.add(new Area(line4));
            area.add(new Area(line5));
            area.add(new Area(line6));
            area.add(new Area(line7));
            area.add(new Area(line8));
            area.add(new Area(line9));
            area.add(new Area(line10));
            graphics2d.setColor(Color.orange);
            graphics2d.draw(line1);
            graphics2d.draw(line2);
            graphics2d.draw(line3);
            graphics2d.draw(line4);
            graphics2d.draw(line5);
            graphics2d.draw(line6);
            graphics2d.draw(line7);
            graphics2d.draw(line8);
            graphics2d.draw(line9);
            graphics2d.draw(line10);
        }

    }
    
    

    // 
    class menuActions implements MouseListener {

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//        System.out.println("entrt");
            optionsMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
           // JPanel p = (JPanel) e.getSource();
//              if(Games.window.contains(e.getXOnScreen(), e.getYOnScreen())  ) 
//                Games.setWindowVisibility(true);
//              
//              Games.window.
            
             String str = e.toString();
//             if (defineSource(str, "Save game    ") || defineSource(str, "Download game ") || 
//                 defineSource(str, "Remove game     ")) {
//                 Games.setWindowVisibility(true);
//                 System.out.println(str);
//             }
        }

        @Override
        public void mouseExited(MouseEvent e) {
//        System.out.println("ex");
            optionsMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            
//           if(!Games.window.contains(e.getXOnScreen(), e.getYOnScreen()) )  
//                Games.setWindowVisibility(false);
//          
//            String str = e.toString();
//            if (defineSource(str, "Save game    ") || defineSource(str, "Download game ") || 
//                 defineSource(str, "Remove game     ")) {
//                 Games.setWindowVisibility(false);
//                 System.out.println(str);
//             }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

            String str = e.toString();
                 
            if ((submenuSettingsWindow.settings.window.isVisible() == false) && 
                (savegame.iGamerName.frame.isVisible() == false) && 
                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&   
                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (About.window.isVisible() == false) && (helpMenu.window.isVisible() == false) &&
                (Options.window.isVisible() == false) && (Games.window.isVisible() == false) &&
                (Statistics.window.isVisible() == false) && (Help.window.isVisible() == false)) {
                
            if (defineSource(str, "strelkaLeft") || defineSource(str, "strelkaRight")) {          
                if (showMenuButton.isVisible() && !hideMenuButton.isVisible()) {
                    showMenuButton.setVisible(false);
                    hideMenuButton.setVisible(true);
                    menuPanel.setVisible(true);
                } else if (!showMenuButton.isVisible() && hideMenuButton.isVisible()) {
                    showMenuButton.setVisible(true);
                    hideMenuButton.setVisible(false);
                    menuPanel.setVisible(false);
                }
              }
            }

            if ((submenuSettingsWindow.settings.window.isVisible() == false) &&
                (savegame.iGamerName.frame.isVisible() == false) && 
                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&
                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (About.window.isVisible() == false) && (helpMenu.window.isVisible() == false) &&
                (Games.window.isVisible() == false) &&
                (Statistics.window.isVisible() == false) && (Help.window.isVisible() == false)) {
                // display suboptions 'Start Game', 'Pause Game', 'Download Game', 'Settings'
                // first click - show suboptions
                // second click - hidee suboptions
                if (defineSource(str, "Options")) {
                    if(!showOptionsSubMenu) {
                        showOptionsSubMenu = true;
                        Options.setWindowVisibility(true);
                    }
                    else {
                        showOptionsSubMenu = false;
                        Options.setWindowVisibility(false);
                    }
                }
            } 
            
            if ((submenuSettingsWindow.settings.window.isVisible() == false) &&
                (savegame.iGamerName.frame.isVisible() == false) && 
                (downloadGameWindow.downloadWindow.window.isVisible() == false) && 
                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (About.window.isVisible() == false) && (helpMenu.window.isVisible() == false) &&
                (Options.window.isVisible() == false) && 
                (Statistics.window.isVisible() == false) && (Help.window.isVisible() == false)) {
                // display submenu of 'Games'
                if (defineSource(str, "Games")) {
                    if(!showGamesSubMenu) {
                        showGamesSubMenu = true;
                        Games.setWindowVisibility(true);
                    }
                    else {
                        showGamesSubMenu = false;
                        Games.setWindowVisibility(false);
                    }
                }
            }

            if ((submenuSettingsWindow.settings.window.isVisible() == false) &&
                (savegame.iGamerName.frame.isVisible() == false) && 
                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&
                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (About.window.isVisible() == false) && (helpMenu.window.isVisible() == false) &&
                (Options.window.isVisible() == false) && 
                (Games.window.isVisible() == false) && (Help.window.isVisible() == false)) {
                // display table of records
                if (defineSource(str, "Statistics")) {
                    Statistics.setWindowVisibility(true);
                }
            }

            if ((submenuSettingsWindow.settings.window.isVisible() == false) &&
                (savegame.iGamerName.frame.isVisible() == false) && 
                (downloadGameWindow.downloadWindow.window.isVisible() == false) &&  
                (removeGameWindow.removeWindow.window.isVisible() == false) &&
                (About.window.isVisible() == false) &&   
                (Options.window.isVisible() == false) && (Help.window.isVisible() == false) &&
                (Games.window.isVisible() == false) && (Statistics.window.isVisible() == false)) {
                // display help submenu
                // first click - show suboptions
                // second click - hidee suboptions
                if (defineSource(str, "Help")) { 
                   if(!showHelpSubMenu) {
                        showHelpSubMenu = true;
                        helpMenu.setWindowVisibility(true);
                    }
                    else {
                        showHelpSubMenu = false;
                        helpMenu.setWindowVisibility(false);
                    } 
                }
            }

        }

    }

}
