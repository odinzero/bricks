package GAME_BRICKS;

import GRAPHIC2D_Software.componentBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;

public class saveGame extends MouseAdapter {
    
    inputGamerName iGamerName;
    BRICKS mainBricks;
    MouseAdapter maSaveGame;

    saveGame(BRICKS bricks) {
        mainBricks = bricks;
        iGamerName = new inputGamerName(mainBricks, true);
       // iGamerName.enter.addMouseListener(this);
        utils.centerFrame(mainBricks.frame, iGamerName.frame);
    }

    
            @Override
            public void mousePressed(MouseEvent e) {

                FileOutputStream fout = null;
                FileInputStream fis = null;
                String path = "src/GAME_BRICKS/Games.txt";
                File file = new File(path);
                try {
                    fis = new FileInputStream(file);

                    String games = utils.readFileContent(fis, "UTF-8");

                    String[] arrSaveLines = games.split("\n"); // contains files lines -> { game:1;user:Alex;score:200;life:1;level:2  } 

                    if (games != "") {

                        String[] newArrLines = new String[arrSaveLines.length + 1];
                        int k = -1;
                        for (int i = 0; i < newArrLines.length; i++) {
                            
                             if(i < (newArrLines.length-1)) 
                                newArrLines[i] = arrSaveLines[i];
                             else 
                                newArrLines[i] = saveNewGame(i);
                        }
                        
                        String data = String.join("\n", newArrLines);

                        utils.writeFileContent(path, data);
                        // 
                    } else {
                        String[] newArrLines = new String[1];
                        newArrLines[0] = saveNewGame(0);
                        
                        String data = String.join("\n", newArrLines);

                        utils.writeFileContent(path, data);
                    }
                    
                    iGamerName.frame.setVisible(false);
                    
                    mainBricks.restartMainThread();
                    
                    mainBricks.startFencePanel();
                    
//                    // cancel pause
//                   if (mainBricks.suspend.get() ) {
//                     mainBricks.pauseTime.stop();
//                     mainBricks.shutterPanel.setLEVEL_CNT(-1); 
//                     // start mainThread !!!
//                     mainBricks.suspend.set(false);
//                   // Notify mainThread
//                   synchronized (mainBricks.thrObj) { mainBricks.thrObj.notify(); }
//                } else {
//                      // start mainThread !!!
//                     mainBricks.suspend.set(false);
//                   // Notify mainThread
//                   synchronized (mainBricks.thrObj) { mainBricks.thrObj.notify(); }
//                   }
                
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
               
            }

    private String saveNewGame(int cnt) {
        int c = cnt + 1;
        String game  = "game:"  + c + ";";
        String user  = "user:"  + iGamerName.tfield.getText() + ";";
        String score = "score:" + mainBricks.cntScore + ";";
        String life  = "life:"  + mainBricks.cntLife + ";";
        String level = "level:" + mainBricks.LEVEL + ";";
        String separ1 = "|";
        String bground = mainBricks.statesOfPosition.getLevelBackground() ;
        String separ2 = "|";
        String x_cBrick = "cb:" + mainBricks.statesOfPosition.getX_controlBrick() + ";";
        String y_cBrick = "cb:" + mainBricks.statesOfPosition.getY_controlBrick() + ";";
        String x_dBrick = "db:" + mainBricks.statesOfPosition.getX_dynamicBrick() + ";";
        String y_dBrick = "db:" + mainBricks.statesOfPosition.getY_dynamicBrick() + ";";
        String separ3 = "|";
        String turn_Xup  = mainBricks.statesOfPosition.preparation_turn_Xup_writeFile();
        String turn_Yup  = mainBricks.statesOfPosition.preparation_turn_Yup_writeFile();
        String turn_Xlow = mainBricks.statesOfPosition.preparation_turn_Xlow_writeFile();
        String turn_Ylow = mainBricks.statesOfPosition.preparation_turn_Ylow_writeFile();
        
        String sBricks  = mainBricks.statesOfPosition.preparationBricksForFile();
        String Bubbles  = mainBricks.statesOfPosition.preparationBubblesForFile();
        
        String newgame = game + user + score + life + level +  separ1 + 
                         bground + separ2 +
                         x_cBrick + y_cBrick + x_dBrick + y_dBrick + separ3 +
                         turn_Xup + turn_Yup + turn_Xlow + turn_Ylow +
                         sBricks + Bubbles;
        return newgame;
    }

}
