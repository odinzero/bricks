/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GAME_BRICKS;

import GRAPHIC2D_Software.componentBevelBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JWindow;

public class submenuSettings {

    buttons checkboxLife, checkboxScore, checkboxLevel;

    patternWindow settings;
    BRICKS mainBricks;
    
    submenuSettings(BRICKS mainBricks) {
        this.mainBricks = mainBricks;
        settingsWindow();
    }

    public void settingsWindow() {
        settings = new patternWindow(mainBricks, "Settings", true, 0, true, true, 0, 0, 295, 320);

        checkboxScore = new buttons(5, "score");
        // get State checkboxScore from file . If first state in file:
        // 0 -> '-' pressedState = true
        // 1 -> '+" pressedState = false
        checkboxScore.setPressedState(readStateFromFile(0));
        checkboxScore.setBounds(25, 25, 45, 55);
        settings.base.add(checkboxScore);
        // (String str, int fontSize, int x, int y, int width, int height)
        settings.addContent("show 'Score'", 30, 85, 40, 205, 35);

        checkboxLife = new buttons(5, "life");
        // get State checkboxLife from file . If first state in file:
        // 0 -> '-' pressedState = true
        // 1 -> '+" pressedState = false
        checkboxLife.setPressedState(readStateFromFile(1));
        checkboxLife.setBounds(25, 65, 45, 55);
        settings.base.add(checkboxLife);
        settings.addContent("show 'Life' ", 30, 85, 80, 205, 35);

        checkboxLevel = new buttons(5, "level");
        // get State checkboxLevel from file . If first state in file:
        // 0 -> '-' pressedState = true
        // 1 -> '+" pressedState = false
        checkboxLevel.setPressedState(readStateFromFile(2));
        checkboxLevel.setBounds(25, 105, 45, 55);
        settings.base.add(checkboxLevel);
        settings.addContent("show 'Level' ", 30, 85, 120, 205, 35);

//         int x, int y,  int outW,int outH, int xLoc,int yLoc, int width, int height
        settings.addButtonsWindow2("Reset to defaults", 40, 190, 200, 35, 12, 23, 205, 40)
                .addMouseListener(restoreSettings());

        settings.addButtonsWindow2("Apply", 50, 265, 85, 35, 12, 23, 90, 40)
                .addMouseListener(writeStateCheckboxesToFile());

        // Action 0 <- it means close window
        settings.addButtonsWindow2("Cancel", 150, 265, 85, 35, 7, 23, 90, 40)
                .addMouseListener(cancel()); 

        settings.setWindowVisibility(false);
    }
    
    private MouseAdapter cancel() {
         MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // just one click should open 'Options' submenu
                // see class:: menu
                mainBricks.mainMenu.showOptionsSubMenu = false;
                // just one click should open 'Games' submenu
                // see class:: menu
                mainBricks.mainMenu.showGamesSubMenu = false;
                
                settings.setWindowVisibility(false); 
            } 
         };
         return ma;
    }

    private MouseAdapter restoreSettings() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // just one click should open 'Options' submenu
                // see class:: menu
                mainBricks.mainMenu.showOptionsSubMenu = false;
                
                checkboxLife.setPressedState(false);
                checkboxScore.setPressedState(false);
                checkboxLevel.setPressedState(false);
                checkboxLife.repaint();
                checkboxScore.repaint();
                checkboxLevel.repaint();
                // set Labels 'Score','Life','Level' to init state
                getCurrentStateOfCheckboxes_ScoreLifeLevel();

                FileOutputStream fout = null;
                String path = "D:\\-== BACKUP_netbeans_projects ==-\\NetBeansProjects\\SWING_1\\src\\GAME_BRICKS\\settingsCheckboxesStates.txt";
                File fo = new File(path);
                try {
                    fout = new FileOutputStream(fo);
                    String content = "111";
                    // get the content in bytes
                    byte[] contentInBytes = content.getBytes();

                    fout.write(contentInBytes);
                    fout.flush();
                    fout.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        };
        return ma;
    }

    private MouseAdapter writeStateCheckboxesToFile() {
        MouseAdapter ma;
        ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // just one click should open 'Options' submenu
                // see class:: menu
                mainBricks.mainMenu.showOptionsSubMenu = false;

                String s = collectCheckboxesStates();

                try {
                    System.out.println(s);
                    writeFileContent(s);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // after gamer set different states of checkboxes
                // need display labels: score,life,level according to
                // states of checkboxes
                getCurrentStateOfCheckboxes_ScoreLifeLevel();
                
                mainBricks.basic.repaint();
                mainBricks.basic.revalidate();
            }

        };
        return ma;
    }
    
    private void getCurrentStateOfCheckboxes_ScoreLifeLevel() {
        if (checkboxScore.getPressedState() == false &&
            checkboxLife.getPressedState()  == false &&
            checkboxLevel.getPressedState() == false    ) {
            resetAllLabels();
             mainBricks.score.setBounds( 30, 50, 206, 26);
             mainBricks.life.setBounds(  30, 80, 106, 26);
             mainBricks.level.setBounds( 30, 110,106, 26);
             mainBricks.basic.add(mainBricks.score);
             mainBricks.basic.add(mainBricks.life);
             mainBricks.basic.add(mainBricks.level);
             resetLevel();
        } 
        else if(checkboxScore.getPressedState() == true &&
                  checkboxLife.getPressedState()  == false &&
                  checkboxLevel.getPressedState() == false   ) {
             resetAllLabels();
             mainBricks.life.setBounds(  30, 50, 106, 26);
             mainBricks.level.setBounds( 30, 80,106, 26);
             mainBricks.basic.add(mainBricks.life);
             mainBricks.basic.add(mainBricks.level);
             resetLevel();
        } else if(checkboxScore.getPressedState() == false &&
                  checkboxLife.getPressedState()  == true &&
                  checkboxLevel.getPressedState() == false   ) {
            resetAllLabels();
             mainBricks.score.setBounds( 30, 50, 206, 26);
             mainBricks.level.setBounds( 30, 80,106, 26);
             mainBricks.basic.add(mainBricks.score);
             mainBricks.basic.add(mainBricks.level);
             resetLevel();
        } else if(checkboxScore.getPressedState() == false &&
                  checkboxLife.getPressedState()  == false &&
                  checkboxLevel.getPressedState() == true   ) {
            resetAllLabels();
             mainBricks.score.setBounds( 30, 50, 206, 26);
             mainBricks.life.setBounds( 30, 80,106, 26);
             mainBricks.basic.add(mainBricks.score);
             mainBricks.basic.add(mainBricks.life);
             resetLevel();
        }
         else if(checkboxScore.getPressedState() == true &&
                 checkboxLife.getPressedState()  == true &&
                 checkboxLevel.getPressedState() == false) {
             resetAllLabels();
             mainBricks.level.setBounds( 30, 50,106, 26);
             mainBricks.basic.add(mainBricks.level);
        } else if(checkboxScore.getPressedState() == true &&
                  checkboxLife.getPressedState()  == false &&
                  checkboxLevel.getPressedState() == true) {
            resetAllLabels();
             mainBricks.life.setBounds( 30, 50,106, 26);
             mainBricks.basic.add(mainBricks.life);
             resetLevel();
        } else if(checkboxScore.getPressedState() == false &&
                  checkboxLife.getPressedState()  == true &&
                  checkboxLevel.getPressedState() == true) {
            resetAllLabels();
             mainBricks.score.setBounds( 30, 50,206, 26);
             mainBricks.basic.add(mainBricks.score);
             resetLevel();
        } else if(checkboxScore.getPressedState() == true &&
                  checkboxLife.getPressedState()  == true &&
                  checkboxLevel.getPressedState() == true) {
            resetAllLabels();
        }
        
    }
    
    private void resetAllLabels() {
         mainBricks.basic.remove(mainBricks.score);
         mainBricks.basic.remove(mainBricks.life);
         mainBricks.basic.remove(mainBricks.level);
    }
    
    // this method need for JLebels 'Score','Life','Level' to set always 
    // in most upper view 
    private void resetLevel() {
        if(mainBricks.LEVEL < 11)
          mainBricks.scenario.level_0_10.clearPanelForNextLevel();
        if(mainBricks.LEVEL > 10 && mainBricks.LEVEL < 21)
          mainBricks.scenario.level_11_20.clearPanelForNextLevel();
        if(mainBricks.LEVEL > 20 && mainBricks.LEVEL < 31)
           mainBricks.scenario.level_21_30.clearPanelForNextLevel();  
        
        mainBricks.frame.repaint();
        mainBricks.scenario.changeLevel(); // switch to the next level
        mainBricks.scenario.resetTransitionLevel();
    }


    public static void writeFileContent(String str) throws IOException {

        String path = "D:\\-== BACKUP_netbeans_projects ==-\\NetBeansProjects\\SWING_1\\src\\GAME_BRICKS\\settingsCheckboxesStates.txt";
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        fw.write(str);
        fw.close();
//        BufferedWriter bw = new BufferedWriter(fw);        
//        bw.write(str);
    }

    public static String readFileContent(FileInputStream fis, String encoding) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();
    }

    public boolean readStateFromFile(int index) {
        boolean pressedState = false;

        FileInputStream fis = null;
        String path = "D:\\-== BACKUP_netbeans_projects ==-\\NetBeansProjects\\SWING_1\\src\\GAME_BRICKS\\settingsCheckboxesStates.txt";
        File file = new File(path);
        try {
            fis = new FileInputStream(file);

            String state = readFileContent(fis, "UTF-8");

            if (state == null) {
                pressedState = false;
                return pressedState;
            } else {
                char c = state.charAt(index);

                if (c == '1') {
                    pressedState = false;
                    return pressedState;
                }
                if (c == '0') {
                    pressedState = true;
                    return pressedState;
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return pressedState;
    }

    private String collectCheckboxesStates() {

        String totalState = "";
        String s = "";
        
        if (checkboxScore.getPressedState() == false) {
            s += totalState.concat("1");
        }
        if (checkboxScore.getPressedState() == true) {
            s += totalState.concat("0");
        }

        if (checkboxLife.getPressedState() == false) {
            s += totalState.concat("1");
        }
        if (checkboxLife.getPressedState() == true) {
            s += totalState.concat("0");
        }

        if (checkboxLevel.getPressedState() == false) {
            s += totalState.concat("1");
        }
        if (checkboxLevel.getPressedState() == true) {
            s += totalState.concat("0");
        }

        return s;
    }
    
    

    public static void main(String[] args) {
//        componentBevelBorder aaa = new componentBevelBorder(1, // BevelType
//                2, // deep
//                "Figure", // name  "Change Properties "
//                1, // positionOnSideOfNameBorder
//                1, // positionOfNameBorder
//                new Font("Times New Roman", 1, 12),
//                new Color(0, 100, 0), //  fontColor
//                Color.white, // Color.white  // outsideShadow
//                new Color(190, 220, 220), // new Color(190, 220, 220)    // colorBorder
//                new Color(100, 130, 130), //   new Color(100, 130, 130)  // outsideShadow
//                new Color(150, 180, 180)); // new Color(150, 180, 180)    // insideBorder
//        aaa.setFloatable(false);
//        aaa.setResizableLimit(false);
//
//        JWindow w = aaa.getWindowForm();
////        JWindow w = new JWindow();
//        w.setAlwaysOnTop(true);
//        w.setPreferredSize(new Dimension(200, 250));
//        w.setMinimumSize(new Dimension(200, 250));

//        JComponent con = (JComponent) w.getContentPane();
//        con.setBorder(aaa);
        
//        c cc = new c();
//        cc.settingsWindow();

//        w.setVisible(true);
    }

}
