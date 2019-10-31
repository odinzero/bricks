package GAME_BRICKS;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;

// save Position and size 'controlBrick','dynamicBrick','staticBricks','bubbles'
// in different levels in order to it was possible save,download and remove games
public class savedStates {

    BRICKS mainBricks;

    // this variables keep all info about killed static bricks and bubbles during passing level
    List<String> killedBrick; // is calculated in file 'staticBricks' 
    List<String> killedBubble;// is calculated in file 'staticBricks' 
    // this variables keep all info about
    List<String> stateDynamicBrick; // x,y -> DynamicBrick
    List<String> stateControlBrick; // x,y -> ControlBrick
    List<String> stateStaticBrick;  // x,y,w,h,type -> StaticBrick
    List<String> stateBubbles;      // x,y,w,h,type -> Bubbles 

    // when gamer push 'Download game'
    // this variables keep all info after reading file 'Games.txt' for displaying
    // saved static bricks and bubbles  
    List<String> readBricks;
    List<String> readBubbles;

    savedStates(BRICKS mainbricks) { // BRICKS mainbricks
        mainBricks = mainbricks;

        readBricks = new ArrayList<String>();
        readBubbles = new ArrayList<String>();

        killedBrick = new ArrayList<String>();
        killedBubble = new ArrayList<String>();

        stateDynamicBrick = new ArrayList<String>();
        stateControlBrick = new ArrayList<String>();
        stateStaticBrick = new ArrayList<String>();
        stateBubbles = new ArrayList<String>();

//        stateBubbles.add(100 + " " + 200 + " " + 300 + " " + 400 + " " + 5);
//        stateBubbles.add(500 + " " + 600 + " " + 700 + " " + 800 + " " + 5);
//        stateBubbles.add(520 + " " + 1000 + " " + 1100 + " " + 1200 + " " + 5);
//        stateBubbles.add(598 + " " + 598 + " " + 66 + " " + 5 + " " + 5);
//        stateBubbles.add(900 + " " + 234 + " " + 8 + " " + 12 + " " + 5);
//        
//        killedBubble.add(500 + " " + 610 + " " + 760 + " " + 700 + " " + 5);
//        killedBubble.add(900 + " " + 1200 + " " + 1700 + " " + 1280 + " " + 5);
    }

    public void emptyAllStates() {
        killedBrick.clear();
        killedBubble.clear();

        stateDynamicBrick.clear();
        stateControlBrick.clear();
        stateStaticBrick.clear();
        stateBubbles.clear();
    }

    public void save_stateControlBrickPosition(int x, int y) {
        String str = x + " " + y;
        stateControlBrick.add(str);
    }

    public void save_stateDynamicBrickPosition(int x, int y) {
        String str = x + " " + y;
        stateDynamicBrick.add(str);
    }

    public String save_stateStaticBrickPosition(int x, int y, int w, int h, int type) {
        String str = x + " " + y + " " + w + " " + h + " " + type;
        stateStaticBrick.add(str);
        return str;
    }

    public String save_stateBubblesPosition(int x, int y, int w, int h, int type) {
        String str = x + " " + y + " " + w + " " + h + " " + type;
        stateBubbles.add(str);
        return str;
    }

    //-------------------------- save background -------------------------------
    public String getLevelBackground() {
        int type = mainBricks.basic.getType();
        return "bg:" + type + ";";
    }

    // ------------------------- control brick ---------------------------------
    // get last current state 'X' coordinate on screen 
    public int getX_controlBrick() {
        String str = getLastItemInArray((ArrayList) stateControlBrick);
        String[] xy = str.split(" ");
        return Integer.parseInt(xy[0]);
    }

    // get last current state 'Y' coordinate on screen 
    public int getY_controlBrick() {
        String str = getLastItemInArray((ArrayList) stateControlBrick);
        String[] xy = str.split(" ");
        return Integer.parseInt(xy[1]);
    }

    // ------------------------- dynamic brick ---------------------------------
    // get last current state 'X' coordinate on screen 
    public int getX_dynamicBrick() {
        String str = getLastItemInArray((ArrayList) stateDynamicBrick);
        String[] xy = str.split(" ");
        return Integer.parseInt(xy[0]);
    }

    // get last current state 'Y' coordinate on screen 
    public int getY_dynamicBrick() {
        String str = getLastItemInArray((ArrayList) stateDynamicBrick);
        String[] xy = str.split(" ");
        return Integer.parseInt(xy[1]);
    }

    public String getLastItemInArray(ArrayList arrayList) {
        String item = null;
        if (arrayList != null && !arrayList.isEmpty()) {
            item = (String) arrayList.get(arrayList.size() - 1);
            //System.out.println(item);
            return item;
        } else {
            return null;
        }
    }

    public String preparation_turn_Xup_writeFile() {
        // for increasing X coordinate
        boolean bool = mainBricks.turn_Xup;
        String str = "d1:" + bool + ";";
        return str;
    }

    public String preparation_turn_Yup_writeFile() {
        // for increasing Y coordinate
        boolean bool = mainBricks.turn_Yup;
        String str = "d2:" + bool + ";";
        return str;
    }

    public String preparation_turn_Xlow_writeFile() {
        // for decreasing X coordinate
        boolean bool = mainBricks.turn_Xlow;
        String str = "d3:" + bool + ";";
        return str;
    }

    public String preparation_turn_Ylow_writeFile() {
        // for decreasing Y coordinate
        boolean bool = mainBricks.turn_Ylow;
        String str = "d4:" + bool + ";";
        return str;
    }

    // 'arrayList' <-- 'stateControlBrick' or 'stateDynamicBrick' 
    // 'component' <-- 'controlBrick' or 'dynamicBrick'
    public void setSavedPosition(ArrayList arrayList, JComponent component, int w, int h) {
        // this string contains info about 'controlBrick' or 'dynamicBrick' bounds
        // like this  "100  50"
        String strInfo = getLastItemInArray(arrayList);

        String[] info = strInfo.split(" ");
        int x = Integer.parseInt(info[0]);
        int y = Integer.parseInt(info[1]);

        component.setBounds(x, y, w, h);
    }

    // remove touched 'killed' static bricks from old 'not touched' static bricks
    private void removeKilledBricks() {

        // this array contains info about 'staticBrick' bounds and type 
        // like this { 100  50  200  200 5 }
        String[] strOldBricks = stateStaticBrick.toArray(new String[stateStaticBrick.size()]);
        // touched bricks by dynamic brick
        String[] strKilledBricks = killedBrick.toArray(new String[killedBrick.size()]);

        //                       x   y   w   h  type
        // contains like this { 100 200 300 400  5 }
        String[] splitArr = null;
        //                                            x
        // contains first value from 'splitArr' -> { 100 }
        String[] xPos = new String[strKilledBricks.length];
        //                                            y
        // contains first value from 'splitArr' -> { 200 }
        String[] yPos = new String[strKilledBricks.length];
        //                                            w
        // contains second value from 'splitArr' -> { 300 }
        String[] wPos = new String[strKilledBricks.length];
        //                                            h
        // contains second value from 'splitArr' -> { 400 }
        String[] hPos = new String[strKilledBricks.length];
        //                                           type
        // contains second value from 'splitArr' -> { 5 }
        String[] tPos = new String[strKilledBricks.length];

        for (int i = 0; i < strKilledBricks.length; i++) {
            splitArr = strKilledBricks[i].split(" ");
            for (int j = 0; j < splitArr.length; j++) {
                // this is x-coordinate
                if (j == 0) {
                    xPos[i] = splitArr[j];
                }
                // this is y-coordinate
                if (j == 1) {
                    yPos[i] = splitArr[j];
                }
                if (j == 2) {
                    // this is width
                    wPos[i] = splitArr[j];
                }
                if (j == 3) {
                    // this is height
                    hPos[i] = splitArr[j];
                }
                if (j == 4) {
                    // this is type
                    tPos[i] = splitArr[j];
                }
            }
        }
        // compare two array 'strOldBricks' and  'xPos'
        // if   strOldBricks[i] -> { 100 200 300 400 5  } start  
        // with xPos[i] ->  { 100 } and with yPos[i] -> { 200 }
        // then remove element from 'stateStaticBrick'
        for (int i = 0; i < strOldBricks.length; i++) {
            String string0 = strOldBricks[i];
            for (int j = 0; j < xPos.length; j++) {
                String x = xPos[j];
                String y = yPos[j];
                String w = wPos[j];
                String h = hPos[j];
                String t = tPos[j];
                if (string0.startsWith(x + " " + y + " " + w + " " + h + " " + t)) {
                    stateStaticBrick.remove(string0);
                    // System.out.println("remove removeKilledBricks(): " + string0);
                }
            }
        }
        int cnt = 0;
        Iterator itr = stateStaticBrick.iterator();
        while (itr.hasNext()) {
            cnt++;
            Object element = itr.next();
//           System.out.println("after removeKilledBricks(): " +  element + "  " + cnt);
        }
    }

    // remove 'killed' bubbles with 'killed' static bricks
    private void removeKilledBubbles() {

        // this array contains info about 'Bubble' bounds and type 
        // like this { 100  50  200  200 5 }
        String[] strOldBubbles = stateBubbles.toArray(new String[stateBubbles.size()]);
        //  bubbles which was fired dynamic brick
        String[] strKilledBubbles = killedBubble.toArray(new String[killedBubble.size()]);

        //                       x   y   w   h  type
        // contains like this { 100 200 300 400  5 }
        String[] splitArr = null;
        //                                            x
        // contains first value from 'splitArr' -> { 100 }
        String[] xPos = new String[strKilledBubbles.length];
        //                                            y
        // contains first value from 'splitArr' -> { 200 }
        String[] yPos = new String[strKilledBubbles.length];
        //                                            w
        // contains second value from 'splitArr' -> { 300 }
        String[] wPos = new String[strKilledBubbles.length];
        //                                            h
        // contains second value from 'splitArr' -> { 400 }
        String[] hPos = new String[strKilledBubbles.length];
        //                                           type
        // contains second value from 'splitArr' -> { 5 }
        String[] tPos = new String[strKilledBubbles.length];

        for (int i = 0; i < strKilledBubbles.length; i++) {
            splitArr = strKilledBubbles[i].split(" ");
            for (int j = 0; j < splitArr.length; j++) {
                if (j == 0) {
                    // this is x-coordinate
                    xPos[i] = splitArr[j];
                }
                if (j == 1) {
                    // this is y-coordinate
                    yPos[i] = splitArr[j];
                }
                if (j == 2) {
                    // this is width
                    wPos[i] = splitArr[j];
                }
                if (j == 3) {
                    // this is height
                    hPos[i] = splitArr[j];
                }
                if (j == 4) {
                    // this is type
                    tPos[i] = splitArr[j];
                }
            }
        }
        // compare two array 'strOldBubbles' and  'xPos'
        // if   strOldBubbles[i] -> { 100 200 300 400 5  } start with 
        //      xPos[i] ->  { 100 } and with yPos[i] ->  { 200 }
        // then remove element from 'stateBubbles'
        for (int i = 0; i < strOldBubbles.length; i++) {
            String string0 = strOldBubbles[i];
            for (int j = 0; j < xPos.length; j++) {
                String x = xPos[j];
                String y = yPos[j];
                String w = wPos[j];
                String h = hPos[j];
                String t = tPos[j];
                if (string0.startsWith(x + " " + y + " " + w + " " + h + " " + t)) {
                    stateBubbles.remove(string0);
                }
            }
        }
    }

    // need prepare rest static bricks to saving(write) in file 'Games.txt'
    public String preparationBricksForFile() {
        // remove touched 'killed' static bricks from old 'not touched' static bricks
        removeKilledBricks();
        // this array contains info about 'staticBrick' bounds and type 
        // like this strBricks[i] = { 100  50  200  200 5 }
        String[] strBricks = stateStaticBrick.toArray(new String[stateStaticBrick.size()]);

        String[] bricksInfo = new String[5];
        String totalBricks = "";

        for (int i = 0; i < strBricks.length; i++) {
            bricksInfo = strBricks[i].split(" ");
            for (int j = 0; j < bricksInfo.length; j++) {
                if (j == 0) {
                    String d = String.join(",", bricksInfo);
                    String str = "sb" + i + ":" + d + ";";
                    totalBricks += str;
                    //  System.out.println("preparationBricksForFile(): " + str + "    " + i);
                }
            }
        }
        // System.out.println("preparationBricksForFile(): " +  "<" + totalBricks + ">");
        return "|" + totalBricks + "|";
    }

    // need prepare rest static bubbles to saving(write)  in file 'Games.txt'
    public String preparationBubblesForFile() {
        // remove 'killed' bubbles with 'killed' static bricks
        removeKilledBubbles();
        // this array contains info about 'Bubbles' bounds and type 
        // like this strBricks[i] = { 100  50  200  200 5 }
        String[] strBubbles = stateBubbles.toArray(new String[stateBubbles.size()]);
        // this array contains elements like as 
        // bubblesInfo[0] = { 100 }, bubblesInfo[1] = { 50 }, bubblesInfo[2] = { 200 } .....
        String[] bubblesInfo = new String[5];
        String totalBubbles = "";

        for (int i = 0; i < strBubbles.length; i++) {
            bubblesInfo = strBubbles[i].split(" ");
            for (int j = 0; j < bubblesInfo.length; j++) {
                if (j == 0) {
                    String d = String.join(",", bubblesInfo);
                    String str = "b" + i + ":" + d + ";";
                    totalBubbles += str;
                }
            }
        }

        return totalBubbles;
    }

    public void displaySavedLevel() { // staticBricks[] savedBricks, bubbleScore[] savedBubbles
        // this array contains info about 'staticBrick' bounds and type 
        // like this { 100  50  200  200 5 }
        String[] strBricks = readBricks.toArray(new String[readBricks.size()]);
        String[] brickInfo = null;
        // this array contains info about 'staticBrick' bounds and type 
        // like this { 100  50  200  200 5 }
        String[] strBubbles = readBubbles.toArray(new String[readBubbles.size()]);
        String[] bubblesInfo = null;
        // LEVEL: 0 - 10
        if (mainBricks.LEVEL < 11) {
//        staticBricks[] savedBricks = new staticBricks[readBricks.size()];
//        bubbleScore[] savedBubbles = new bubbleScore[savedBricks.length];
            mainBricks.scenario.level_0_10.bricks = new staticBricks[readBricks.size()];
            mainBricks.scenario.level_0_10.bubbles = new bubbleScore[readBricks.size()];

            for (int i = 0, cnt = 0; i < strBricks.length; i++) {
                // System.out.println(strBricks[i]);
                cnt++;

                brickInfo = strBricks[i].split(" ");
                int x = Integer.parseInt(brickInfo[0]);
                int y = Integer.parseInt(brickInfo[1]);
                int w = Integer.parseInt(brickInfo[2]);
                int h = Integer.parseInt(brickInfo[3]);
                int type = Integer.parseInt(brickInfo[4]);

                bubblesInfo = strBubbles[i].split(" ");
                int xb = Integer.parseInt(bubblesInfo[0]);
                int yb = Integer.parseInt(bubblesInfo[1]);
                int wb = Integer.parseInt(bubblesInfo[2]);
                int hb = Integer.parseInt(bubblesInfo[3]);
                int typeb = Integer.parseInt(bubblesInfo[4]);

                // add bubbles for each static bricks
                mainBricks.scenario.level_0_10.bubbles[i] = new bubbleScore(typeb);  // 5,10,25,50,100,150,300,500
                mainBricks.scenario.level_0_10.bubbles[i].setBounds(xb, yb, wb, hb); // 112, 86
                mainBricks.scenario.level_0_10.bubbles[i].setVisible(false);
                mainBricks.getBasicPanel().add(mainBricks.scenario.level_0_10.bubbles[i]);
                // create and layout bricks
                mainBricks.scenario.level_0_10.bricks[i] = new staticBricks(mainBricks, type, mainBricks.scenario.level_0_10.bubbles[i]);
                mainBricks.scenario.level_0_10.bricks[i].setBounds(x, y, w, h);
                mainBricks.scenario.level_0_10.bricks[i].setVisible(true);
                mainBricks.getBasicPanel().add(mainBricks.scenario.level_0_10.bricks[i]);

//            System.out.println("display bricks:  " + strBricks[i] + "  " +  cnt);
            }
            mainBricks.timeDelayForAddingSchemaTouch_0_10 = true;
        }
        // LEVEL: 11 - 20
        if ((mainBricks.LEVEL >= 11) && (mainBricks.LEVEL < 21)) {
            mainBricks.scenario.level_11_20.bricks = new staticBricks[readBricks.size()];
            mainBricks.scenario.level_11_20.bubbles = new bubbleScore[readBricks.size()];

            for (int i = 0, cnt = 0; i < strBricks.length; i++) {
                // System.out.println(strBricks[i]);
                cnt++;

                brickInfo = strBricks[i].split(" ");
                int x = Integer.parseInt(brickInfo[0]);
                int y = Integer.parseInt(brickInfo[1]);
                int w = Integer.parseInt(brickInfo[2]);
                int h = Integer.parseInt(brickInfo[3]);
                int type = Integer.parseInt(brickInfo[4]);

                bubblesInfo = strBubbles[i].split(" ");
                int xb = Integer.parseInt(bubblesInfo[0]);
                int yb = Integer.parseInt(bubblesInfo[1]);
                int wb = Integer.parseInt(bubblesInfo[2]);
                int hb = Integer.parseInt(bubblesInfo[3]);
                int typeb = Integer.parseInt(bubblesInfo[4]);

                // add bubbles for each static bricks
                mainBricks.scenario.level_11_20.bubbles[i] = new bubbleScore(typeb);  // 5,10,25,50,100,150,300,500
                mainBricks.scenario.level_11_20.bubbles[i].setBounds(xb, yb, wb, hb); // 112, 86
                mainBricks.scenario.level_11_20.bubbles[i].setVisible(false);
                mainBricks.getBasicPanel().add(mainBricks.scenario.level_11_20.bubbles[i]);
                // create and layout bricks
                mainBricks.scenario.level_11_20.bricks[i] = new staticBricks(mainBricks, type, mainBricks.scenario.level_11_20.bubbles[i]);
                mainBricks.scenario.level_11_20.bricks[i].setBounds(x, y, w, h);
                mainBricks.scenario.level_11_20.bricks[i].setVisible(true);
                mainBricks.getBasicPanel().add(mainBricks.scenario.level_11_20.bricks[i]);

//            System.out.println("display bricks:  " + strBricks[i] + "  " +  cnt);
            }
            mainBricks.timeDelayForAddingSchemaTouch_11_20 = true;
        }
        // LEVEL: 21 - 30
        if ((mainBricks.LEVEL >= 21) && (mainBricks.LEVEL < 31)) {
            mainBricks.scenario.level_21_30.bricks = new staticBricks[readBricks.size()];
            mainBricks.scenario.level_21_30.bubbles = new bubbleScore[readBricks.size()];

            for (int i = 0, cnt = 0; i < strBricks.length; i++) {
                // System.out.println(strBricks[i]);
                cnt++;

                brickInfo = strBricks[i].split(" ");
                int x = Integer.parseInt(brickInfo[0]);
                int y = Integer.parseInt(brickInfo[1]);
                int w = Integer.parseInt(brickInfo[2]);
                int h = Integer.parseInt(brickInfo[3]);
                int type = Integer.parseInt(brickInfo[4]);

                bubblesInfo = strBubbles[i].split(" ");
                int xb = Integer.parseInt(bubblesInfo[0]);
                int yb = Integer.parseInt(bubblesInfo[1]);
                int wb = Integer.parseInt(bubblesInfo[2]);
                int hb = Integer.parseInt(bubblesInfo[3]);
                int typeb = Integer.parseInt(bubblesInfo[4]);

                // add bubbles for each static bricks
                mainBricks.scenario.level_21_30.bubbles[i] = new bubbleScore(typeb);  // 5,10,25,50,100,150,300,500
                mainBricks.scenario.level_21_30.bubbles[i].setBounds(xb, yb, wb, hb); // 112, 86
                mainBricks.scenario.level_21_30.bubbles[i].setVisible(false);
                mainBricks.getBasicPanel().add(mainBricks.scenario.level_21_30.bubbles[i]);
                // create and layout bricks
                mainBricks.scenario.level_21_30.bricks[i] = new staticBricks(mainBricks, type, mainBricks.scenario.level_21_30.bubbles[i]);
                mainBricks.scenario.level_21_30.bricks[i].setBounds(x, y, w, h);
                mainBricks.scenario.level_21_30.bricks[i].setVisible(true);
                mainBricks.getBasicPanel().add(mainBricks.scenario.level_21_30.bricks[i]);

//            System.out.println("display bricks:  " + strBricks[i] + "  " +  cnt);
            }
            mainBricks.timeDelayForAddingSchemaTouch_21_30 = true;  
        }
        

        // display control brick position
        mainBricks.cBrick.setLocation(getX_controlBrick(), getY_controlBrick());
        // display dynamic brick position
        mainBricks.xPos = getX_dynamicBrick();
        mainBricks.yPos = getY_dynamicBrick();
        mainBricks.mBrick.setLocation(mainBricks.xPos, mainBricks.yPos);
    }

    // for reading data from File in order to change variables(control brick, dynamic brick, static bricks,
    // bubbles, turn_Xup, turn_Yup, turn_Xlow, turn_Ylow) according to file 
    // see file: 'downloadRemoveGame.txt'
    public void fetchingLineGameData(String games, int gameSelector) {

        readBricks.clear();
        readBubbles.clear();

        String[] bigGameLines = games.split("\n");

        String[] data = null;
//        String[] gameInfo = new String[1];

        String[] cbrick_dbrick = new String[bigGameLines.length]; // -> {cb:330, cb:245, db:285, db:285}
        String[] xy_cbrick = new String[2];
        String[] xy_dbrick = new String[2];

        String[] turn_XY = new String[bigGameLines.length]; // -> { d1:false;d2:false;d3:true;d4:true; }
        boolean turn_Xup;
        boolean turn_Yup;
        boolean turn_Xlow;
        boolean turn_Ylow;

        String[] eachBrick = null; // -> {sb0:4,100,66,26,5},{sb1:70,100,66,26,5},{sb2:136,100,66,26,5},{sb3:202,100,66,26,5}
        String[] dataBrick = null; // -> { sb0:4 100 66 26 5 sb1:70 100 66 26 5 sb2:136 100 66 26 5 sb3:202 100 66 26 5 }
        // list type -> {4} {100} {66} {26} {5} {70} {100} {66} {26} {5} {136} {100} {66} {26} {5} {202} {100} {66} {26} {5} 
        List<String> readDataBricks = new ArrayList<String>();

        String[] eachBubble = null; // -> {sb0:4,100,66,26,5},{sb1:70,100,66,26,5},{sb2:136,100,66,26,5},{sb3:202,100,66,26,5}
        String[] dataBubble = null; // -> { sb0:4 100 66 26 5 sb1:70 100 66 26 5 sb2:136 100 66 26 5 sb3:202 100 66 26 5 }
        List<String> readDataBubbles = new ArrayList<String>();

        for (int m = gameSelector; m < gameSelector + 1; m++) {

            String str = bigGameLines[m];

            data = str.split("\\|");

            for (int i = 0, cnt0 = -1; i < data.length; i++) {

                String string = data[i];

                // fetch game data and format data game for saving  
                if (i % 6 == 0) {
                    //cnt0++;
                    //gameInfo[cnt0] = string;
                    // System.out.println("0: " + gameInfo[cnt0]);
                    // fetch background of 'basic' panel for each level    
                } else if (i % 6 == 1) {
                    string = string.substring(0, string.length() - 1);
                    // string -> bg:2;
                    String[] bg = string.split(":");
                    // bg[0] = bg; bg[1] = 2 
                    int bground = Integer.parseInt(bg[1]);
                    mainBricks.basic.setType(bground);
                } // fetch control bricks data and dynamic bricks data format data for saving in right format    
                else if (i % 6 == 2) {
                    // string -> cb:330;cb:245;db:285;db:285;
                    cbrick_dbrick = string.split(";");
                    for (int j = 0, c1 = -1, c2 = -1; j < cbrick_dbrick.length; j++) {
                        // string1 -> {cb:330, cb:245, db:285, db:305}
                        String string1 = cbrick_dbrick[j];
                        if (string1.startsWith("cb:")) {
                            c1++;
                            // {330, 245}
                            xy_cbrick[c1] = string1.substring(3);
                        }
                        if (string1.startsWith("db:")) {
                            c2++;
                            // {285, 305}
                            xy_dbrick[c2] = string1.substring(3);
                        }
                    }
                    //                                           X = 330                             Y = 245
                    save_stateControlBrickPosition(Integer.parseInt(xy_cbrick[0]), Integer.parseInt(xy_cbrick[1]));
                    save_stateDynamicBrickPosition(Integer.parseInt(xy_dbrick[0]), Integer.parseInt(xy_dbrick[1]));

//                    System.out.println(" save getX_dynamicBrick() : " + Integer.parseInt(xy_dbrick[0]) +
//                                       " save_getY_dynamicBrick : "   + Integer.parseInt(xy_dbrick[1]) );
                    // fetch turnX_up, turn_Yup, turn_Xlow, turn_Ylow      
                } else if (i % 6 == 3) {
                    // string -> { d1:false;d2:false;d3:true;d4:true; }
                    turn_XY = string.split(";");
                    for (int j = 0; j < turn_XY.length; j++) {
                        String string1 = turn_XY[j];
//                            System.out.println(string1);
                        if (string1.startsWith("d1:")) {
                            turn_Xup = Boolean.parseBoolean(string1.substring(3));
                            mainBricks.turn_Xup = turn_Xup;
                            System.out.println(mainBricks.turn_Xup);
                        }
                        if (string1.startsWith("d2:")) {
                            turn_Yup = Boolean.parseBoolean(string1.substring(3));
                            mainBricks.turn_Yup = turn_Yup;
                            System.out.println(mainBricks.turn_Yup);
                        }
                        if (string1.startsWith("d3:")) {
                            turn_Xlow = Boolean.parseBoolean(string1.substring(3));
                            mainBricks.turn_Xlow = turn_Xlow;
                            System.out.println(mainBricks.turn_Xlow);
                        }
                        if (string1.startsWith("d4:")) {
                            turn_Ylow = Boolean.parseBoolean(string1.substring(3));
                            mainBricks.turn_Ylow = turn_Ylow;
                            System.out.println(mainBricks.turn_Ylow);
                        }
                    }
                    // fetch bricks data and format data bricks for saving
                } else if (i % 6 == 4) {
                    //System.out.println(string);
                    // string ->  sb0:4,100,66,26,5;sb1:70,100,66,26,5;sb2:136,100,66,26,5;sb3:202,100,66,26,5;
                    eachBrick = string.split(";");
                    for (int j = 0; j < eachBrick.length; j++) {
                        // string1 -> {sb0:4,100,66,26,5},{sb1:70,100,66,26,5},{sb2:136,100,66,26,5},{sb3:202,100,66,26,5}
                        String string1 = eachBrick[j];
                        dataBrick = string1.split(",");
                        for (int k = 0; k < dataBrick.length; k++) {
                            // string2 -> { sb0:4 100 66 26 5 sb1:70 100 66 26 5 sb2:136 100 66 26 5 sb3:202 100 66 26 5 }
                            String string2 = dataBrick[k];
                            //System.out.println(string2);
                            if (k % 5 == 0) { // sb0:4, sb1:70, sb2:136, sb3:202
                                // x[0] = sb0, x[1] = 4;
                                String[] x = string2.split(":");
                                for (int z = 0; z < x.length; z++) {
                                    String string3 = x[z];
                                    // x[1] = 4
                                    if (z == (x.length - 1)) {
                                        readDataBricks.add(string3);
                                    }
                                }
                            } else {
                                readDataBricks.add(string2);
                            }
                        }
                    }

                    // fetch bubbles data and format data bubbles for saving    
                } else if (i % 6 == 5) {
//                 System.out.println(string);
                    // string ->  sb0:4,100,66,26,5;sb1:70,100,66,26,5;sb2:136,100,66,26,5;sb3:202,100,66,26,5;
                    eachBubble = string.split(";");
                    for (int j = 0; j < eachBubble.length; j++) {
                        // string1 -> {sb0:4,100,66,26,5},{sb1:70,100,66,26,5},{sb2:136,100,66,26,5},{sb3:202,100,66,26,5}
                        String string1 = eachBubble[j];
                        dataBubble = string1.split(",");
                        for (int k = 0; k < dataBubble.length; k++) {
                            // string2 -> { sb0:4 100 66 26 5 sb1:70 100 66 26 5 sb2:136 100 66 26 5 sb3:202 100 66 26 5 }
                            String string2 = dataBubble[k];
                            if (k % 5 == 0) { // sb0:4, sb1:70, sb2:136, sb3:202
                                // x[0] = sb0, x[1] = 4;
                                String[] x = string2.split(":");
                                for (int z = 0; z < x.length; z++) {
                                    String string3 = x[z];
                                    // x[1] = 4
                                    if (z == (x.length - 1)) {
                                        readDataBubbles.add(string3);
                                    }
                                }
                            } else {
                                readDataBubbles.add(string2);
                            }
                        }
                    }

                }
            }

        }

        String xywh_brick = "";
        // list type -> {4} {100} {66} {26} {5} {70} {100} {66} {26} {5} {136} {100} {66} {26} {5} {202} {100} {66} {26} {5} 
        String[] newArr = readDataBricks.toArray(new String[readDataBricks.size() / 5]);
        for (int i = 0; i < newArr.length; i++) {
            String string = newArr[i];
            if (i % 5 == 4) {
                xywh_brick += string;
                readBricks.add(xywh_brick); // {4 100 66 26 5}
                xywh_brick = "";
            } else {
                xywh_brick += string + " ";
            }
        }

        String xywh_bubbles = "";
        // list type -> {4} {100} {66} {26} {5} {70} {100} {66} {26} {5} {136} {100} {66} {26} {5} {202} {100} {66} {26} {5} 
        String[] newArr1 = readDataBubbles.toArray(new String[readDataBubbles.size() / 5]);
        for (int i = 0; i < newArr1.length; i++) {
            String string = newArr1[i];
            if (i % 5 == 4) {
                xywh_bubbles += string;
                readBubbles.add(xywh_bubbles); // {4 100 112 26 5}
                xywh_bubbles = "";
            } else {
                xywh_bubbles += string + " ";
            }
        }

    }

    public static void main(String[] args) {

        // savedStates ss = new savedStates();
        // ss.preparationBricksForFile();
        // System.out.println(ss.preparationBubblesForFile()); 
//        Iterator itr = ss.stateBubbles.iterator();
//        while (itr.hasNext()) {
//           Object element = itr.next();
//          // System.out.println(element + " ");
//        }
        //-------------------------------------------------
//        List<String> l = new ArrayList<String>();
//        l.add(100 + " " + 200 + " " + 300 + " " + 400 + " " + 5);
//        l.add(500 + " " + 600 + " " + 700 + " " + 800 + " " + 5);
//        l.add(520 + " " + 1000 + " " + 1100 + " " + 1200 + " " + 5);
//        l.add(598 + " " + 598 + " " + 66 + " " + 5 + " " + 5);
//        l.add(900 + " " + 234 + " " + 8 + " " + 12 + " " + 5);
//
//        List<String> ll = new ArrayList<String>();
//        //ll.add(100 + " " + 210 + " " + 310 + " " + 600 + " " + 5);
//        ll.add(500 + " " + 610 + " " + 760 + " " + 700 + " " + 5);
//        ll.add(900 + " " + 1200 + " " + 1700 + " " + 1280 + " " + 5);
//
//        String[] a = l.toArray(new String[l.size()]);
//        String[] aa = ll.toArray(new String[ll.size()]);
//
//        String[] splitted0 = null;
//        String[] el = new String[aa.length];
//
//        for (int i = 0; i < aa.length; i++) {
//            splitted0 = aa[i].split(" ");
//            for (int j = 0; j < splitted0.length; j++) {
//                if (j == 0) {
//                    el[i] = splitted0[j];
////                   System.out.println(  el[i] );
//                }
//            }
//        }
//        
//        int k = -1;
//        for (int i = 0; i < a.length; i++) {
//            String string0 = a[i];
//            for (int j = 0; j < el.length; j++) {
//                String string1 = el[j];
//                if (string0.startsWith(string1)) {
//                    k++;
//                    l.remove(string0);
//                }
//            }
//        }
//        Iterator itr = l.iterator();
//        while (itr.hasNext()) {
//           Object element = itr.next();
//           // System.out.println(element + " ");
//        }
    }
}
