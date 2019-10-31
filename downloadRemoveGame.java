package GAME_BRICKS;

import GAME_BRICKS.patternWindow.buttonsWindow;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;

// format data in file Games :
// game:4;user:zeroz;score:55;life:3;level:0;|cb:330;cb:245;db:285;db:285;|sb0:4,100,66,26,5;sb1:70,100,66,26,5;sb2:136,100,66,26,5;sb3:202,100,66,26,5;|b0:4,100,112,82,5;b1:70,100,112,82,5;b2:136,100,112,82,5;b3:202,100,112,82,5;
// game:1;user:Alex;score:200;life:1;level:2
// game:2;user:hero123;score:500;life:2;level:5
public class downloadRemoveGame {

    patternWindow downloadWindow, removeWindow;
    BRICKS mainBricks;

    JLabel[] headers;
    String[] headers_names = {"N", "User", "Score", "Life", "Level"};
    String[][] lines;

    String[] arrLines;
    String[] arrGames;
    String[] pos;
    String[] res;
    JLabel[] labs;
    // contains all content file 'Games.txt'
    String games;

    boolean isGameDownloaded = false;

    boolean removeGame;
    patternWindow.buttonsWindow start;

    downloadRemoveGame(BRICKS mainBricks, boolean removeGame) {
        this.mainBricks = mainBricks;
        this.removeGame = removeGame;

        String[] bigGameLines = null;

        FileOutputStream fout = null;
        FileInputStream fis = null;
        String path = "src/GAME_BRICKS/Games.txt";
        File file = new File(path);
        try {
            fis = new FileInputStream(file);
            // contains all content file 'Games.txt'
            games = utils.readFileContent(fis, "UTF-8");

            if (games != "") {
                // contains files lines -> { game:4;user:zeroz;score:55;life:3;level:0;cb:330;cb:245;db:285;db:285;|sb0:4,100,66,26,5;sb1:70,100,66,26,5;sb2:136,100,66,26,5;sb3:202,100,66,26,5;|b0:4,100,112,82,5;b1:70,100,112,82,5;b2:136,100,112,82,5;b3:202,100,112,82,5;  };
                bigGameLines = games.split("\n");
                String[] lineGameData = null;
                arrLines = new String[bigGameLines.length];

                List<String> listArrLines = new ArrayList<String>();
                for (int m = 0; m < bigGameLines.length; m++) {
                    String str = bigGameLines[m];
                    // data[0] -> game data; data[1] -> static bricks; data[2] -> bubbles data;
                    lineGameData = str.split("\\|");
                    for (int i = 0, cnt = -1; i < lineGameData.length; i++) {
                        String string = lineGameData[i];
                        //     0          1            2                        3                                4             5
                        // game data|background|cbrick + dynamic brick|turnXup;turnYup;turnXlow;turnYlow|<static bricks>|<bubbles data>  
                        if (i % 6 == 0) {
                            listArrLines.add(string);
//                            cnt++;
//                            arrLines[cnt] = string; 
//                            System.out.println("cnt :" + arrLines[cnt] + "  " + cnt);
                        }
                    }
                }
                String[] arrLines0 = listArrLines.toArray(arrLines);
                for (int i = 0; i < arrLines0.length; i++) {
                    arrLines[i] = arrLines0[i];
                    //System.out.println(arrLines[i]);
                }

                // if first line not contains  "game:1;"
                if (!arrLines[0].contains("game:1;")) {

                    // if in file 'Games.txt' text  {game:344;} and {game:4565;} then should be {game:1;} and {game:2;}
                    // game:344;user:Alex;score:200;life:1;level:2
                    //game:4565;user:hero123;score:500;life:2;level:5
                    String[] parts = new String[5];
                    int cnt = 0;
                    for (int i = 0; i < arrLines.length; i++) {

                        // System.out.println(arrLines[i]);
                        cnt++;
                        if (!arrLines[i].substring(6, 7).contains(";")) {
                            parts = arrLines[i].split(";");
                            parts[0] = "game:1";
                            String s = String.join(";", parts);
                            arrLines[i] = s;
                            arrLines[i] = arrLines[i].substring(0, 5) + cnt + arrLines[i].substring(6);
                        } else {
                            arrLines[i] = arrLines[i].substring(0, 5) + cnt + arrLines[i].substring(6);
                        }
                    }
                }

                String data = String.join("\n", arrLines);
                // write content to file
//                fout = new FileOutputStream(file);
//                fout.write(data.getBytes(), 0, data.length());
//                fout.close();
//            }

//            fis = new FileInputStream(file);
//            games = utils.readFileContent(fis, "UTF-8");
                // arrLines = games.split("\n"); // contains files lines -> { game:1;user:Alex;score:200;life:1;level:2  } 
//                System.out.println("games : " + games);
                if (arrLines.length > 0) {

                    arrGames = null; // contains contents like as -> { game:1, user:Alex, score:200, life:1, level:2 }
                    pos = null;      // contains contents like as -> { game, 1, user, Alex, score, 200, life, 1, level, 2 } 
                    res = new String[arrLines.length * 5];  // contains contents like as -> { 1, Alex, 200, 1, 2 }
                    int k = -1;
                    for (int i = 0; i < arrLines.length; i++) {
                        arrGames = arrLines[i].split(";");
                        for (int j = 0; j < arrGames.length; j++) {
                            pos = arrGames[j].split(":");
                            for (int p = 0; p < pos.length; p++) {
                                if (p % 2 != 0) {
                                    k++;
                                    res[k] = pos[p];
                                    // System.out.println(pos[p]);
                                }
                            }
                        }
                    }

                    // file 'Games.txt' is non empty
                    // Format in file should be -> { 1 Alex 200 1 2 }
                    if (res[0] != null && res[1] != null && res[2] != null && res[3] != null && res[4] != null) {
                        // Divide array type { 1, Alex, 200, 1, 2, 2, hero123, 500, 2, 5 }
                        // to array type {0} {1, Alex, 200, 1, 2}
                        //               {1} {2, hero123, 500, 2, 5}
                        //                        2                5
                        lines = new String[arrLines.length][arrGames.length];
                        int size = 0;
                        int m = -1;
                        boolean finished = false;
                        for (int i = 0; i < arrLines.length; i++) {
                            if (finished) {
                                size += 5;
                            }
                            for (int j = size; j < res.length; j++) {
                                if (j < (size + 5)) {
                                    m++;
                                    lines[i][m] = res[j];
                                    finished = false;
                                } else {
                                    finished = true;
                                    m = -1;
                                    continue;
                                }
                                // System.out.println(i + " " + j);
                            }
                        }

                        if (!this.removeGame) {
                            downloadGameWindow();
                        } else {
                            removeGameWindow();
                        }

                        labs = new JLabel[res.length];
                        // layoutLine(JLabel[] line_x, String[][] line, int len1, int len2, int x, int y)
                        layoutLine(labs, lines, arrLines.length, arrGames.length, -15, 80);
                    } // file 'Games.txt' is empty
                    else {
                        if (!this.removeGame) {
                            downloadGameWindow();
                        } else {
                            removeGameWindow();
                        }
                    }
                }
            } else {
                if (!this.removeGame) {
                    downloadGameWindow();
                } else {
                    removeGameWindow();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void downloadGameWindow() {
        downloadWindow = new patternWindow(mainBricks, "Downlod game", true, 0, true, true, 0, 0, 530, 320);

        if (games != "") {
            // Format should be -> { 1 Alex 200 1 2 }
            if (res[0] != null && res[1] != null && res[2] != null && res[3] != null && res[4] != null) {
                // add to window 'N' , 'User', 'Score', 'Life' 'Level'  
                layoutHeaders(headers, headers_names, headers_names.length, -15, 45);
                // add button 'Start' to start download game     
                start = downloadWindow.new buttonsWindow("Start", 80, 35, 12, 23);
                start.setDisabledState(true);
                start.addMouseListener(startDownloadedGame());
                start.setBounds(176, 265, 85, 40);
                downloadWindow.base.add(start);
                // add button 'Close' to close window
                downloadWindow.addButtonsWindow2("Close", 261, 265, 85, 35, 12, 23, 90, 40)
                        .addMouseListener(close());
            } else {
                emptyData("No games", Color.blue, 218, 105, 125, 35);
                // add button 'Close' to close window
                downloadWindow.addButtonsWindow2("Close", 218, 265, 85, 35, 12, 23, 90, 40)
                        .addMouseListener(close());
            }
        } else {
            emptyData("No games", Color.blue, 218, 105, 125, 35);
            // add button 'Close' to close window
            downloadWindow.addButtonsWindow2("Close", 218, 265, 85, 35, 12, 23, 90, 40)
                    .addMouseListener(close());
        }
        downloadWindow.setWindowVisibility(false);
    }

    public void removeGameWindow() {
        removeWindow = new patternWindow(mainBricks, "Remove game", true, 0, true, true, 0, 0, 505, 320);

        if (games != "") {
            // Format should be -> { 1 Alex 200 1 2 }
            if (res[0] != null && res[1] != null && res[2] != null && res[3] != null && res[4] != null) {
                // add to window 'N' , 'User', 'Score', 'Life' 'Level'  
                layoutHeaders(headers, headers_names, headers_names.length, -15, 45);
            } else {
                emptyData("No games", Color.blue, 218, 105, 125, 35);
            }
        } else {
            emptyData("No games", Color.blue, 218, 105, 125, 35);
        }
        // add button 'Close' to close window
        removeWindow.addButtonsWindow3("Close", "-1", 218, 265, 85, 35, 12, 23, 90, 40)
                .addMouseListener(close());

        removeWindow.setWindowVisibility(false);
    }

    private void emptyData(String text, Color color, int x, int y, int w, int h) {
        Font font = new Font("Monotype Corsiva", Font.BOLD, 26);
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, w, h);
        label.setForeground(color);
        if (!removeGame) {
            downloadWindow.base.add(label);
        } else {
            removeWindow.base.add(label);
        }
    }

    protected void layoutHeaders(JLabel[] line_x, String[] line, int len, int x, int y) {
        Font font = new Font("Monotype Corsiva", Font.BOLD, 26);
        line_x = new JLabel[len];
        for (int k = 0; k < len; k++) {
            x += 30;
            line_x[k] = new JLabel(line[k]);
            if (k == 0) {         // N
                line_x[k].setBounds(x, y, 45, 20);
                line_x[k].setForeground(Color.BLUE);
            } else if (k == 1) {  // User
                line_x[k].setBounds(x + 15, y, 90, 20);
                line_x[k].setForeground(new Color(216, 112, 255));
            } else if (k == 2) {  // Score
                line_x[k].setBounds(x + 75, y, 75, 20);
                line_x[k].setForeground(Color.MAGENTA);
            } else if (k == 3) {  // Life
                line_x[k].setBounds(x + 125, y, 95, 20);
                line_x[k].setForeground(new Color(255, 106, 0));
            } else if (k == 4) {  // Level
                line_x[k].setBounds(x + 170, y, 75, 20);
                line_x[k].setForeground(Color.ORANGE);
            } else if (k == 5) {  // Download
                line_x[k].setBounds(x + 225, y, 105, 20);
                line_x[k].setForeground(Color.GRAY);
            }

            line_x[k].setFont(font);
            if (!removeGame) {
                downloadWindow.base.add(line_x[k]);
            } else {
                removeWindow.base.add(line_x[k]);
            }
        }
    }

    protected void layoutLine(JLabel[] line_x, String[][] line, int len1, int len2, int x, int y) {
        Font font = new Font("Monotype Corsiva", Font.BOLD, 26);
        String id = ""; // for diference buttons 'id' in mouseAdapter events 
        line_x = new JLabel[len2];
        int yy = y;
        int xx = x;
        for (int i = 0; i < len1; i++) {
            if (i != 0) {
                yy += 40;
            }
            id = String.valueOf(i);
            for (int k = 0; k < len2; k++) {
                xx += 30;
                line_x[k] = new JLabel(line[i][k]);
                if (k == 0) {         // N
                    line_x[k].setBounds(xx, yy, 25, 20);
                    line_x[k].setForeground(Color.BLUE);
                } else if (k == 1) {  // User
                    line_x[k].setBounds(xx + 15, yy, 90, 20);
                    line_x[k].setForeground(new Color(216, 112, 255));
                } else if (k == 2) {  // Score
                    line_x[k].setBounds(xx + 75, yy, 75, 20);
                    line_x[k].setForeground(Color.MAGENTA);
                } else if (k == 3) {  // Life
                    line_x[k].setBounds(xx + 125, yy, 95, 20);
                    line_x[k].setForeground(new Color(255, 106, 0));
                } else if (k == 4) {  // Level
                    line_x[k].setBounds(xx + 170, yy, 45, 20);
                    line_x[k].setForeground(Color.ORANGE);

                    if (!removeGame) //         int x, int y,  int outW,int outH, int xLoc,int yLoc, int width, int height
                    {
                        downloadWindow.addButtonsWindow3("Download", id, xx + 245, yy - 10, 130, 35, 12, 23, 135, 40)
                                .addMouseListener(downloadGame());
                    } else {
                        removeWindow.addButtonsWindow3("Remove", id, xx + 245, yy - 10, 105, 35, 12, 23, 110, 40)
                                .addMouseListener(removeGame());
                    }

                    xx = x;
                } else if (k == 5) {
                    //line_x[k].setBounds(xx + 225, yy, 105, 20);
//                    line_x[k].setForeground(Color.ORANGE);

                }

                line_x[k].setFont(font);
                if (!removeGame) {
                    downloadWindow.base.add(line_x[k]);
                } else {
                    removeWindow.base.add(line_x[k]);
                }
            }
        }

    }

    private MouseAdapter downloadGame() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttonsWindow bw = (buttonsWindow) e.getSource();

                int index = Integer.decode(bw.toString());
//                System.out.println("index:  " + index);

                mainBricks.cntScore = Integer.decode(lines[index][2]);
                mainBricks.cntLife = Integer.decode(lines[index][3]);
                mainBricks.LEVEL = Integer.decode(lines[index][4]);

                // System.out.println(mainBricks.cntScore + " " + mainBricks.cntLife + " " + mainBricks.LEVEL);
                // reset Score 
                mainBricks.life.setText("Life : " + mainBricks.cntLife);
                // reset Score 
                mainBricks.score.setText("Score : " + mainBricks.cntScore);
                // reset level 
                mainBricks.level.setText("Level : " + mainBricks.cntLevel);

                mainBricks.scenario.level_0_10.clearPanelForNextLevel();
                mainBricks.getBasicPanel().revalidate();
                mainBricks.getBasicPanel().repaint();

                mainBricks.statesOfPosition.killedBrick.clear();
                mainBricks.statesOfPosition.stateStaticBrick.clear();
                mainBricks.statesOfPosition.stateStaticBrick = mainBricks.statesOfPosition.readBricks;

                mainBricks.statesOfPosition.stateBubbles.clear();
                mainBricks.statesOfPosition.killedBubble.clear();
                mainBricks.statesOfPosition.stateBubbles = mainBricks.statesOfPosition.readBubbles;

                // fetch static bricks and bubbles data and save to arrays: 'readBricks','readBubbles'
                mainBricks.statesOfPosition.fetchingLineGameData(games, index);
                // display on screen static bricks and bubbles according to arrays: 'readBricks','readBubbles'
                // mainBricks.scenario.level_0_10.bricks,mainBricks.scenario.level_0_10.bubbles
                mainBricks.statesOfPosition.displaySavedLevel();
                // isGameDownloaded = false;

                // quantity bricks need kill for transition on next level 
                mainBricks.scenario.resetTransitionLevel();

                // hide menu when game is running
                mainBricks.mainMenu.showMenuButton.setVisible(true);
                mainBricks.mainMenu.hideMenuButton.setVisible(false);
                mainBricks.mainMenu.menuPanel.setVisible(false);
                // just one click should open 'Options' submenu
                // see class:: menu
                mainBricks.mainMenu.showOptionsSubMenu = false;

                mainBricks.frame.repaint();
                // set 'Start' button to enable state
                start.setDisabledState(false);
                isGameDownloaded = true;
                start.repaint();
            }
        };
        return ma;
    }

    private MouseAdapter startDownloadedGame() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (isGameDownloaded) {

                    isGameDownloaded = false;

                    downloadWindow.setWindowVisibility(false);
//                System.out.println("mBrick" + mainBricks.statesOfPosition.getX_dynamicBrick() + "   " + 
//                        mainBricks.statesOfPosition.getY_dynamicBrick() );

//                System.out.println("after start: " + mainBricks.turn_Xup);
//                System.out.println("after start: " + mainBricks.turn_Yup);
//                System.out.println("after start: " + mainBricks.turn_Xlow);
//                System.out.println("after start: " + mainBricks.turn_Ylow);
                    // stop old thread if is working or is paused
                    if (mainBricks.thrObj.isAlive()) {
                        mainBricks.start = false;
                        synchronized (mainBricks.thrObj) {
                            mainBricks.thrObj.notify();
                        }
                  //  mainBricks.thr.interrupt();
                        //  mainBricks.thrObj.stop();
                    }
//                // start new thread 
                    mainBricks.thrObj = new Thread(mainBricks.thr);
                    mainBricks.start = true;
                    mainBricks.thrObj.start();
                    // cancel pause
                    if (mainBricks.suspend.get()) {
                        mainBricks.pauseTime.stop();
                        mainBricks.shutterPanel.setLEVEL_CNT(-1);
                        // start mainThread !!!
                        mainBricks.suspend.set(false);
                        synchronized (mainBricks.thrObj) {
                            mainBricks.thrObj.notify();
                        }
                    }
                    mainBricks.startFencePanel();

                    // hide menu when game is running
                    mainBricks.mainMenu.showMenuButton.setVisible(true);
                    mainBricks.mainMenu.hideMenuButton.setVisible(false);
                    mainBricks.mainMenu.menuPanel.setVisible(false);
                // just one click should open 'Options' submenu
                    // see class:: menu
                    mainBricks.mainMenu.showOptionsSubMenu = false;

                    mainBricks.frame.repaint();
                    mainBricks.frame.revalidate();
                    // set 'Start' button to disable state
                    start.setDisabledState(true);
                    start.repaint();
                }

            }
        };
        return ma;
    }

    //game:1;user:Alex;score:200;life:1;level:2
    //game:2;user:hero123;score:500;life:2;level:5
    private MouseAdapter removeGame() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeWindow.window.repaint();
                removeWindow.base.repaint();
                removeWindow.window.revalidate();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                buttonsWindow bw = (buttonsWindow) e.getSource();

                int index = Integer.decode(bw.toString()); // id of button 'Remove'  // 0,1,2 ....
//                System.out.println(index);

//                int line = Integer.decode(lines[index][0]); // game:{1}
                FileInputStream fis = null;
                String path = "src/GAME_BRICKS/Games.txt";
                File file = new File(path);

                FileOutputStream fout = null;
                PrintWriter writer = null;

                String[] bigGameLines = null;
                String[] lineGameData = null;
                String[] newArr = null;

                try {
                    fis = new FileInputStream(file);
                    String games = utils.readFileContent(fis, "UTF-8");

                    //  String[] newArrLines = games.split("\n"); // contains files lines -> { game:1;user:Alex;score:200;life:1;level:2  }
                    bigGameLines = games.split("\n");
                    String[] newArrLines = new String[bigGameLines.length];

                    List<String> dataGames = new ArrayList<String>();
                    List<String> dataBGround = new ArrayList<String>();
                    List<String> dataCDBrick = new ArrayList<String>(); // control brick and Dynamic brick
                    List<String> dataTurnXY = new ArrayList<String>();
                    List<String> dataBricks = new ArrayList<String>();
                    List<String> dataBubbles = new ArrayList<String>();
                    for (int m = 0; m < bigGameLines.length; m++) {
                        String str = bigGameLines[m];
                        // data[0] -> game data; data[1] -> static bricks; data[2] -> bubbles data;
                        lineGameData = str.split("\\|");
                        for (int i = 0, cnt = -1; i < lineGameData.length; i++) {
                            String string = lineGameData[i];
                            //     0            1                              2                        3               4
                            // game data|cbrick + dynamic brick|turnXup;turnYup;turnXlow;turnYlow|static bricks|bubbles data  
                            if (i % 6 == 0) {
                                dataGames.add(string);
                                // cbrick + dynamic brick    
                            } else if (i % 6 == 1) {
                                dataBGround.add(string);
                                // cbrick + dynamic brick    
                            } else if (i % 6 == 2) {
                                dataCDBrick.add(string);
                                // turnXup;turnYup;turnXlow;turnYlow     
                            } else if (i % 6 == 3) {
                                dataTurnXY.add(string);
                                // static bricks     
                            } else if (i % 6 == 4) {
                                dataBricks.add(string);
                                // bubbles data     
                            } else if (i % 6 == 5) {
                                dataBubbles.add(string);
                            }
                        }
                    }
                    // 
                    String[] arr_games_data = dataGames.toArray(new String[dataGames.size()]);
                    for (int i = 0; i < arr_games_data.length; i++) {
                        newArrLines[i] = arr_games_data[i];
                        // System.out.println(arrLines[i]);
                    }

                    newArrLines = removeArrayElement(index, newArrLines);

                    String[] arr1 = dataBGround.toArray(new String[dataBGround.size()]);
                    String[] arr2 = dataCDBrick.toArray(new String[dataCDBrick.size()]);
                    String[] arr3 = dataTurnXY.toArray(new String[dataTurnXY.size()]);
                    String[] arr4 = dataBricks.toArray(new String[dataBricks.size()]);
                    String[] arr5 = dataBubbles.toArray(new String[dataBubbles.size()]);

                    arr1 = removeArrayElement(index, arr1);
                    arr2 = removeArrayElement(index, arr2);
                    arr3 = removeArrayElement(index, arr3);
                    arr4 = removeArrayElement(index, arr4);
                    arr5 = removeArrayElement(index, arr5);
                    // if in file 'Games.txt'  {game:344;} and {game:4565;} should be {game:1;} and {game:2;}
                    // game:344;user:Alex;score:200;life:1;level:2
                    //game:4565;user:hero123;score:500;life:2;level:5
                    String[] parts = new String[5];
                    // 'bigLines' -> for saving games to file after modifying
                    String[] bigLines = new String[newArrLines.length];
                    int cnt = 0;
                    for (int i = 0; i < newArrLines.length; i++) {

                        String s1 = arr1[i]; // background 'basic' JPAnel in file 'BRICKS'
                        String s2 = arr2[i]; // control brick and dynamic brick -> { cb:737;cb:235;db:714;db:21; }
                        String s3 = arr3[i]; // turn -> { d1:false;d2:true;d3:true;d4:false; }
                        String s4 = arr4[i]; // static bricks -> { sb0:4,100,66,26,5;sb1:70,100,66,26,5; }
                        String s5 = arr5[i]; // bubbles ->  { b0:4,100,112,82,5;b1:70,100,112,82,5;b2:136,100,112,82,5; }

                        cnt++;
                        if (!newArrLines[i].substring(6, 7).contains(";")) {
                            parts = newArrLines[i].split(";");
                            parts[0] = "game:1";
                            String s = String.join(";", parts);

                            newArrLines[i] = s;
                            newArrLines[i] = newArrLines[i].substring(0, 5) + cnt + newArrLines[i].substring(6);

                            bigLines[i] = s;
                            bigLines[i] = newArrLines[i].substring(0, 5) + cnt + newArrLines[i].substring(6) + "|"
                                    + s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5;
                        } else {
                            newArrLines[i] = newArrLines[i].substring(0, 5) + cnt + newArrLines[i].substring(6);

                            bigLines[i] = newArrLines[i].substring(0, 5) + cnt + newArrLines[i].substring(6) + "|"
                                    + s1 + "|" + s2 + "|" + s3 + "|" + s4 + "|" + s5;
                        }
                    }

                    String data = String.join("\n", bigLines);

                    // clear content of file
                    writer = new PrintWriter(path);
                    writer.print("");
                    writer.close();
                    // write content to file
                    fout = new FileOutputStream(file);
                    fout.write(data.getBytes(), 0, data.length());
                    fout.close();

//                    for (int i = 0; i < newArrLines.length; i++) {
//                        String string = newArrLines[i];
//                        System.out.println("string: " + string);
//                    }
                    arrGames = null; // contains contents like as -> { game:1 user:Alex score:200 life:1 level:2 }
                    pos = null; // contains contents like as -> { game 1 user Alex score 200 life 1 level 2 } 
                    res = new String[newArrLines.length * 5];  // contains contents like as -> { 1 Alex 200 1 2 }
                    int kk = -1;
                    for (int i = 0; i < newArrLines.length; i++) {
                        arrGames = newArrLines[i].split(";");
                        for (int j = 0; j < arrGames.length; j++) {
                            pos = arrGames[j].split(":");
                            for (int p = 0; p < pos.length; p++) {
                                // System.out.println("POS::" + pos[p] + "  " + res.length);
                                if (p % 2 != 0) {
                                    kk++;
                                    res[kk] = pos[p];
                                    // System.out.println("RES::" + res[kk]);
                                }
                            }
                        }
                    }

                    if (newArrLines.length > 0 || newArrLines.length == 1) {
                        // Divide array type { 1, Alex, 200, 1, 2, 2, hero123, 500, 2, 5 }
                        // to array type {0} {1, Alex, 200, 1, 2}
                        //               {1} {2, hero123, 500, 2, 5}
                        //                        1                5
                        lines = new String[newArrLines.length][arrGames.length];
                        int size = 0;
                        int m = -1;
                        boolean finished = false;
                        for (int i = 0; i < newArrLines.length; i++) {
                            if (finished) {
                                size += 5;
                            }
                            for (int j = size; j < res.length; j++) {
                                if (j < (size + 5)) {
                                    m++;
                                    lines[i][m] = res[j];
                                    finished = false;
                                } else {
                                    finished = true;
                                    m = -1;
                                    continue;
                                }
                                // System.out.println(i + " " + j);
                            }
                        }
                        // remove all JLabels and Button 'Remove' for current Game except "Close" button
                        removeComponents();
                        // add Headers and rest Games lines   
                        layoutHeaders(headers, headers_names, headers_names.length, -15, 45);
                        layoutLine(labs, lines, newArrLines.length, arrGames.length, -15, 80);

                    } else // when empty file and no games
                    {
                        // remove all JLabels and Button 'Remove' for current Game except "Close" button
                        removeComponents();
                        emptyData("No games", Color.blue, 218, 105, 125, 35);
                    }

                    removeWindow.window.repaint();
                    removeWindow.base.repaint();

                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                }

            }
        };
        return ma;
    }

    public String[] removeArrayElement(int index, String[] newArrLines) {
        String[] newArr = null;

        if (newArrLines.length > 0) {
            newArr = new String[newArrLines.length - 1];
        } else if (newArrLines.length == 1) {
            newArr = new String[newArrLines.length];
        }

        int k = -1;
        for (int i = 0; i < newArrLines.length; i++) {
            // add to games array all games except selected 
            if (i != index) {
                k++;
                newArr[k] = newArrLines[i];
                // System.out.println(newArr[k]);
            }
        }
        newArrLines = new String[newArr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArrLines[i] = newArr[i];
        }
        return newArrLines;
    }

    public void removeComponents() {

        Component[] components = removeWindow.base.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                removeWindow.base.remove(component);
            }
            if (component instanceof buttonsWindow) {
                // remove all 'Remove' buttons except 'Close' button with id=-1
                if (component.toString() != "-1") {
                    removeWindow.base.remove(component);
                }
            }
        }

    }

    private MouseAdapter close() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // just one click should open 'Options' submenu
                // see class:: menu
                mainBricks.mainMenu.showOptionsSubMenu = false;
                // just one click should open 'Games' submenu
                // see class:: menu
                mainBricks.mainMenu.showGamesSubMenu = false;

                if (!removeGame) {
                    downloadWindow.setWindowVisibility(false);
                } else {
                    removeWindow.setWindowVisibility(false);
                }
            }
        };
        return ma;
    }

}
