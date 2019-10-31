package GAME_BRICKS;

// Format file 'Records.txt'
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//1 g1 400 01:48:03 PM 15/04/2013
//2 g2 35 08:39:47 AM 23/02/2014
//3 g3 30 11:31:34 AM 03/10/2017
//4 g4 25 12:30:58 PM 13/07/2016
//5 g5 20 04:16:04 PM 25/11/2004
//6 g1 15 10:48:03 PM 18/10/2009
//7 g2 10 07:39:47 PM 01/09/2017
//8 g3 5 08:31:34 PM 28/12/2015
public class updateTableRecords extends MouseAdapter {

    boolean newRecord;
    inputGamerName iGamerName;

    BRICKS mainBricks;
    String showNewRecord = "";

    List<String> scoreWithoutRepeat = new ArrayList(); // not used
    boolean isPresent = false; // not used

    updateTableRecords(BRICKS bricks) { // BRICKS bricks
         this.mainBricks = bricks;

        // When record is present after game is finished
        // then need input new record in  file Records.txt
        iGamerName = new inputGamerName(mainBricks, false);
        utils.centerFrame(mainBricks.frame, iGamerName.frame);
        iGamerName.frame.setVisible(true);
    }

    // for button 'Enter' in window 'iGamerName'
    @Override
    public void mousePressed(MouseEvent e) {

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

            // array contains -> {1} {g1} {40} {01:48:03PM} {15/04/2013} 
            String[] recordsLines = null;
            String[] gamerName = new String[bigRecordsLines.length];
            String[] gamerScore = new String[bigRecordsLines.length];
            String[] gamerTime = new String[bigRecordsLines.length];
            String[] gamerDate = new String[bigRecordsLines.length];
            for (int i = 0, cnt1 = -1, cnt2 = -1, cnt3 = -1, cnt4 = -1; i < bigRecordsLines.length; i++) {
                //
                recordsLines = bigRecordsLines[i].split(" ");
                for (int j = 0; j < recordsLines.length; j++) {
                    // contains -> 1, g1, 40, 01:48:03, PM, 15/04/2013 ...
                    String string = recordsLines[j];
                    // name of gamer
                    if (j == 1) {
                        cnt1++;
                        gamerName[cnt1] = string;
                    }
                    // gamer score
                    if (j == 2) {
                        cnt2++;
                        gamerScore[cnt2] = string;
                    }
                    // gamer time
                    if (j == 3) {
                        cnt3++;
                        gamerTime[cnt3] = string;
                    }
                    // gamer date
                    if (j == 4) {
                        cnt4++;
                        gamerDate[cnt4] = string;
                    }
                }
            }
            // from array view -> 4000, 350, 35, 25, 20, 15, 10, 5
            // to array view   -> 4000, 0350, 0035, 0025, 0020, 0015, 0010, 0005
//            String[] format_Score = formatScore(gamerScore);
//
//            for (int i = 0; i < format_Score.length; i++) {
//                String string = format_Score[i];
//            }

//            for (int i = 0; i < gamerScore.length; i++) {
//                String string = gamerScore[i];
//                System.out.println(i + "  " + string);
//            }
            newRecord = isNewRecord(mainBricks.cntScore, gamerScore);

            if (newRecord) {

                gamerName = addElementToEndArray(gamerName,
                        mainBricks.update_tableRecords.iGamerName.tfield.getText());
                gamerScore = addElementToEndArray(gamerScore, String.valueOf(mainBricks.cntScore));
                gamerTime = addElementToEndArray(gamerTime, getCurrentTime());
                gamerDate = addElementToEndArray(gamerDate, getCurrentDate());
                // array contains -> { 85 01:48:03PM 15/4/2013 biff }
                final List<String> arrBeforeSort = new ArrayList();
                for (int i = 0; i < bigRecordsLines.length + 1; i++) {
                    String name = gamerName[i];
                    String score = gamerScore[i];
                    String time = gamerTime[i];
                    String date = gamerDate[i];
                    // contains -> { 85 01:48:03PM 15/4/2013 biff }
                    arrBeforeSort.add(score + " " + time + " " + date + " " + name);
                }

                showNewRecord = gamerName[gamerName.length - 1] + " "
                        + gamerScore[gamerScore.length - 1] + " "
                        + gamerTime[gamerTime.length - 1] + " "
                        + gamerDate[gamerDate.length - 1];

                // System.out.println("showNewRecord: " + showNewRecord);
                Iterator itr0 = arrBeforeSort.iterator();
                while (itr0.hasNext()) {
                    Object element = itr0.next();
                    //  System.out.println(element);
                }

                // int array contains 'score' the same string array gamerScore
                int[] beforeSort_GamerScore = new int[gamerScore.length];
                for (int i = 0; i < beforeSort_GamerScore.length; i++) {
                    String string = gamerScore[i];
                    if (string.equals("-----")) {
                        beforeSort_GamerScore[i] = -1;
                    } else {
                        beforeSort_GamerScore[i] = Integer.valueOf(string);
                    }
                }
                // do sorting 'score' in descending order
                int[] afterSort_GamerScore = sortArrayDescending(beforeSort_GamerScore);
                // string array 'gamerScore' received sorted score values
                for (int i = 0; i < afterSort_GamerScore.length; i++) {
                    gamerScore[i] = String.valueOf(afterSort_GamerScore[i]);
//                System.out.println(gamerScore[i]);
                }

                String[] arr_BeforeSort = arrBeforeSort.toArray(new String[arrBeforeSort.size()]);
                // array contains -> { 1 zero 1050 08:39:47AM 23/2/2014 }
                String[] finalGamesRecords = new String[arr_BeforeSort.length];

                List<String> list = new ArrayList();
                // SORT BY SCORE GAME
                for (int i = 0; i < gamerScore.length; i++) {
                    // contains -> {1085, 350, 200, 85, 30, 25, 20, 15, 10}
                    String score = gamerScore[i];
                    for (int j = 0; j < arr_BeforeSort.length; j++) {
                        // 'recordLine' -> { 85 01:48:03PM 15/4/2013 biff }
                        String recordLine = arr_BeforeSort[j];
                        String name = gamerName[j];
                        // if score present in start 'recordLine' fill 
                        // 'finalGamesRecords' array according to sorted 'gamerScore' array
                        if (recordLine.startsWith(score)) {
                            // replace gamerName from end 'recordLine' to start
                            if (recordLine.endsWith(name)) {
                                int lenName = name.length();
                                int lenRecordLine = recordLine.length();
                                int lenEnd = lenRecordLine - lenName - 1;

                                recordLine = recordLine.substring(0, lenEnd);
                            }
                            finalGamesRecords[i] = name + " " + recordLine;
                            list.add(finalGamesRecords[i]);
                        }

                    }
                }
                // remove the same values in list
                Set<String> recordsSet = new LinkedHashSet<String>(list);
                // array contains -> { zero 1050 08:39:47AM 23/2/2014 }
                String[] recordsData = recordsSet.toArray(new String[recordsSet.size()]);

                // add cnt of lines
                int cntLine = 0;
                for (int i = 0; i < recordsData.length; i++) {
                    cntLine++;
                    // add cnt of lines  1,2,3,4,5,6,7,8,9 + { zero 1050 08:39:47AM 23/2/2014 }
                    recordsData[i] = cntLine + " " + recordsData[i];
                }

                // add empty lines if number record of lines is less 10 !
                if (cntLine < 11) {
                    String[] emptyRecords = new String[10 - cntLine];
                    for (int i = 0; i < emptyRecords.length; i++) {
                        String string = "--" + " "
                                + "-----" + " "
                                + "-----" + " "
                                + "-:-:---" + " " + "-/-/---";
                        emptyRecords[i] = string;
                        recordsData = addElementToEndArray(recordsData, emptyRecords[i]);
                        System.out.println(recordsData[i]);
                    }
                }

//                Iterator itr = s.iterator();
//                while (itr.hasNext()) {
//                    Object element = itr.next();
//                    System.out.println(element);
//                }
                String data = String.join("\n", recordsData);

                utils.writeFileContent(path, data);

                // hide input gamer
                iGamerName.frame.setVisible(false);
                // show table of records with gamer record
                mainBricks.mainMenu.Statistics = new tableOfRecords(mainBricks); // new updateTable(mainBricks)
                mainBricks.mainMenu.Statistics.setWindowVisibility(true);
            } else {
                showNewRecord = "";
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public boolean isNewRecordPresent() {
        return newRecord;
    }

    private static String[] addElementToEndArray(String[] array, String str) {
//    System.out.println(Arrays.toString(array));
        //create new array from old array and allocate one more element    
        String[] array1 = Arrays.copyOf(array, array.length + 1);
        array1[array.length] = str;
//    System.out.println(Arrays.toString(array));
        return array1;
    }

    private boolean theSameScore(String scoreGame) {

        if (scoreWithoutRepeat.contains(scoreGame)) {
            isPresent = true;
            return isPresent;
        } else {
            scoreWithoutRepeat.add(scoreGame);
            isPresent = false;
            return isPresent;
        }
    }

    public static boolean isNewRecord(int score, String[] gameScore) {
        boolean verdict = false;

        int[] intGameScore = new int[gameScore.length];

        for (int i = 0; i < gameScore.length; i++) {
            String scoreOfGame = gameScore[i];
            scoreOfGame = scoreOfGame.replaceAll("\\s", "");
            // if RESET button was pressed in 'tableOfRecords' then score equal "-----"
            if (scoreOfGame.equals("-----")) {
                intGameScore[i] = 0;
              //  System.out.println("if -----:" + intGameScore[i]);
            } else {
                intGameScore[i] = Integer.valueOf(scoreOfGame);
              //  System.out.println("else -----:" + intGameScore[i]);
            }
            // System.out.println( gameScore[i]);
        }

//        System.out.println( intGameScore[0]);
        // int scor = Integer.valueOf(score);
        for (int i = 0; i < intGameScore.length; i++) {

            if (score < intGameScore[i]) {
                verdict = false;
            }

            if (score > intGameScore[i]) {
                verdict = true;
                return verdict;
            }
        }
        return verdict;
    }

    public static int[] sortArrayAscending(int[] nonSortedArray) {
        int[] sortedArray = new int[nonSortedArray.length];
        int temp;
        // added this for loop, think about logic why do we have to add this to make it work
        for (int j = 0; j < nonSortedArray.length - 1; j++) {
            for (int i = 0; i < nonSortedArray.length - 1; i++) {
                if (nonSortedArray[i] > nonSortedArray[i + 1]) {
                    temp = nonSortedArray[i];
                    nonSortedArray[i] = nonSortedArray[i + 1];
                    nonSortedArray[i + 1] = temp;
                    sortedArray = nonSortedArray;
                }
            }
        }
        return sortedArray;
    }

    public static int[] sortArrayDescending(int[] nonSortedArray) {
        int[] sortedArray = new int[nonSortedArray.length];
        int temp;
        // added this for loop, think about logic why do we have to add this to make it work
        for (int j = 0; j < nonSortedArray.length - 1; j++) {
            for (int i = 0; i < nonSortedArray.length - 1; i++) {
                if (nonSortedArray[i] < nonSortedArray[i + 1]) {
                    temp = nonSortedArray[i];
                    nonSortedArray[i] = nonSortedArray[i + 1];
                    nonSortedArray[i + 1] = temp;
                    sortedArray = nonSortedArray;
                }
            }
        }
        return sortedArray;
    }

    private String getCurrentDate() {
        java.util.Calendar showDate = new java.util.GregorianCalendar();
        // add 1 to month because Calendar's months start at 0, not 1
        int month = showDate.get(showDate.MONTH) + 1;
        int dayOfMonth = showDate.get(showDate.DAY_OF_MONTH);
        int year = showDate.get(showDate.YEAR);

        String strMonth = "";
        if (month < 10) {
            strMonth = "0" + String.valueOf(month);
        } else {
            strMonth = String.valueOf(month);
        }

        String strDayOfMonth = "";
        if (dayOfMonth < 10) {
            strDayOfMonth = "0" + String.valueOf(dayOfMonth);
        } else {
            strDayOfMonth = String.valueOf(dayOfMonth);
        }

        String currDate = strDayOfMonth + "/" + strMonth + "/" + year;

        return currDate;
    }

    private String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Formatter ft = new Formatter();
        // Отобразить стандартный 12-часовой формат
        ft.format("%tr", cal);
        String time = "" + ft;

        time = time.replaceAll("\\s", "");

        return time;
    }

    // from array view -> 4000, 350, 35, 25, 20, 15, 10, 5
    // to array view   -> 4000, 0350, 0035, 0025, 0020, 0015, 0010, 0005
    private String[] formatScore(String[] arr) {

        String[] finalScore = new String[arr.length];
        // this array need for define max value in array
        // because for fulfill comparing values need 
        // all values in ArrayList 'arrBeforeSort' should be equal length
        int[] iarr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            String string = arr[i];
            int score = Integer.valueOf(string);
            iarr[i] = score;
        }
        // sort array in order to find max value
        Arrays.sort(iarr);
        // here max value of array
        int max = iarr[iarr.length - 1];
        // convert max value to string to get string length
        String maxStr = String.valueOf(max);
        // get string length of max value
        int lenMax = maxStr.length();

        // System.out.println(max);
        for (int i = 0; i < arr.length; i++) {

            // other not max values
            if (arr[i].length() < lenMax) {
                // get current string length of element
                int len = lenMax - arr[i].length();
                if (len == 1) {
                    finalScore[i] = "0" + arr[i];
                }
                if (len == 2) {
                    finalScore[i] = "00" + arr[i];
                }
                if (len == 3) {
                    finalScore[i] = "000" + arr[i];
                }
                if (len == 4) {
                    finalScore[i] = "0000" + arr[i];
                }
                if (len == 5) {
                    finalScore[i] = "00000" + arr[i];
                }
                if (len == 6) {
                    finalScore[i] = "000000" + arr[i];
                }
                if (len == 7) {
                    finalScore[i] = "0000000" + arr[i];
                }
                if (len == 8) {
                    finalScore[i] = "00000000" + arr[i];
                }
                // max value
            } else {
                finalScore[i] = arr[i];
            }
        }

        return finalScore;
    }

    public static void main(String[] args)  {
//        updateTableRecords utr = new updateTableRecords();

//        String[] bigRecordsLines = null;
//
//        FileInputStream fis = null;
//        String path = "src/GAME_BRICKS/Records.txt";
//        File file = new File(path);
//        try {
//
//            fis = new FileInputStream(file);
//            // contains all content file 'Records.txt'
//            String records = utils.readFileContent(fis, "UTF-8");
//            fis.close();
//            // array contains files lines -> { 1 g1 40 01:48:03PM 15/04/2013 }
//            bigRecordsLines = records.split("\n");
//
//            // array contains -> {1} {g1} {40} {01:48:03PM} {15/04/2013} 
//            String[] recordsLines = null;
//            String[] gamerName = new String[bigRecordsLines.length];
//            String[] gamerScore = new String[bigRecordsLines.length];
//            String[] gamerTime = new String[bigRecordsLines.length];
//            String[] gamerDate = new String[bigRecordsLines.length];
//            for (int i = 0, cnt1 = -1, cnt2 = -1, cnt3 = -1, cnt4 = -1; i < bigRecordsLines.length; i++) {
//                //
//                recordsLines = bigRecordsLines[i].split(" ");
//                for (int j = 0; j < recordsLines.length; j++) {
//                    // contains -> 1, g1, 40, 01:48:03, PM, 15/04/2013 ...
//                    String string = recordsLines[j];
//
//                    // name of gamer
//                    if (j == 1) {
//                        cnt1++;
//                        gamerName[cnt1] = string;
//                    }
//                    // gamer score
//                    if (j == 2) {
//                        cnt2++;
//                        gamerScore[cnt2] = string;
//                    }
//                    // gamer time
//                    if (j == 3) {
//                        cnt3++;
//                        gamerTime[cnt3] = string;
//                    }
//                    // gamer date
//                    if (j == 4) {
//                        cnt4++;
//                        gamerDate[cnt4] = string;
//                    }
//                }
//            }
//
//            gamerName = addElementToEndArray(gamerName, "Broo");
//            gamerScore = addElementToEndArray(gamerScore, "15");
//            gamerTime = addElementToEndArray(gamerTime, "08:39:47AM");
//            gamerDate = addElementToEndArray(gamerDate, "25/2/2014");
//
//            // array contains -> { -- ----- ----- -:-:--- -/-/--- }
//            List<String> emptyLines = new ArrayList();
//            // array contains -> { 85 01:48:03PM 15/4/2013 biff }
//            final List<String> arrBeforeSort = new ArrayList();
//            for (int i = 0; i < bigRecordsLines.length + 1; i++) {
//                String name = gamerName[i];
//                String score = gamerScore[i];
//                String time = gamerTime[i];
//                String date = gamerDate[i];
//
//                // contains -> { 85 01:48:03PM 15/4/2013 biff }
//                arrBeforeSort.add(score + " " + time + " " + date + " " + name);
//
//            }
//
//            Iterator itr0 = arrBeforeSort.iterator();
//            while (itr0.hasNext()) {
//                Object element = itr0.next();
//                // System.out.println(element);
//            }
//
//            // int array contains 'score' the same string array gamerScore
//            int[] beforeSort_GamerScore = new int[gamerScore.length];
//            for (int i = 0; i < beforeSort_GamerScore.length; i++) {
//                String string = gamerScore[i];
//                if (string.equals("-----")) {
//                    beforeSort_GamerScore[i] = -1;
//                } else {
//                    beforeSort_GamerScore[i] = Integer.valueOf(string);
//                }
//                //  System.out.println(string);
//            }
//            // do sorting 'score' in descending order
//            int[] afterSort_GamerScore = sortArrayDescending(beforeSort_GamerScore);
//            // string array 'gamerScore' received sorted score values
//            for (int i = 0; i < afterSort_GamerScore.length; i++) {
//                gamerScore[i] = String.valueOf(afterSort_GamerScore[i]);
////                    System.out.println(gamerScore[i]);
//            }
//
//            String[] arr_BeforeSort = arrBeforeSort.toArray(new String[arrBeforeSort.size()]);
//            // array contains -> { 1 zero 1050 08:39:47AM 23/2/2014 }
//            String[] finalGamesRecords = new String[arr_BeforeSort.length];
//
//            List<String> list = new ArrayList();
//            // SORT BY SCORE GAME
//            for (int i = 0; i < gamerScore.length; i++) {
//                // contains -> {1085, 350, 200, 85, 30, 25, 20, 15, 10}
//                String score = gamerScore[i];
//                for (int j = 0; j < arr_BeforeSort.length; j++) {
//                    // 'recordLine' -> { 85 01:48:03PM 15/4/2013 biff }
//                    String recordLine = arr_BeforeSort[j];
//                    String name = gamerName[j];
//                    // if score present in start 'recordLine' fill 
//                    // 'finalGamesRecords' array according to sorted 'gamerScore' array
//                    if (recordLine.startsWith(score)) {
//                        // replace gamerName from end 'recordLine' to start
//                        if (recordLine.endsWith(name)) {
//                            int lenName = name.length();
//                            int lenRecordLine = recordLine.length();
//                            int lenEnd = lenRecordLine - lenName - 1;
//
//                            recordLine = recordLine.substring(0, lenEnd);
//                        }
//
//                        finalGamesRecords[i] = name + " " + recordLine;
//                        // System.out.println(":" + finalGamesRecords[i]);
//
//                        list.add(finalGamesRecords[i]);
//                    }
//
//                }
//            }
//
////                Iterator itr = list.iterator();
////                while (itr.hasNext()) {
////                    Object element = itr.next();
////                    System.out.println(element);
////                }
//            // remove the same values in list
//            Set<String> recordsSet = new LinkedHashSet<String>(list);
//            // array contains -> { zero 1050 08:39:47AM 23/2/2014 }
//            String[] recordsData = recordsSet.toArray(new String[recordsSet.size()]);
//
//            // add cnt of lines
//            int cntLine = 0;
//            for (int i = 0; i < recordsData.length; i++) {
//                cntLine++;
//                // add cnt of lines  1,2,3,4,5,6,7,8,9 + { zero 1050 08:39:47AM 23/2/2014 }
//                recordsData[i] = cntLine + " " + recordsData[i];
//            }
//
//            if (cntLine < 11) {
//                String[] emptyRecords = new String[10 - cntLine];
//                for (int i = 0; i < emptyRecords.length; i++) {
//                    String string = "--" + " "
//                            + "-----" + " "
//                            + "-----" + " "
//                            + "-:-:---" + " " + "-/-/---";
//                    emptyRecords[i] = string;
//                    recordsData = addElementToEndArray(recordsData, emptyRecords[i]);
//                    System.out.println(recordsData[i]);
//                }
//            }
//
//            String data = String.join("\n", recordsData);
////
//            utils.writeFileContent(path, data);

//            for (int i = 0; i < gamerScore.length; i++) {
//                String string = gamerScore[i];
//                string = string.replaceAll("\\s", "");
//                if(string.equals("-----")) { 
//                   System.out.println("OK"); 
//                } else 
//                    System.out.println("NOK :" + string );  
//                
//            }
            //  boolean  newRecord = isNewRecord(10, gamerScore);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        //   boolean bool =  updateTableRecords.isNewRecord(10, args);
//        System.out.println(bool);
//
////        String[] arr = {"str1","str2","str3"};
////        arr = updateTableRecords.addElementToEndArray(arr, "str4");
////        for (int i = 0; i < arr.length; i++) {
////            String string = arr[i];
////            //System.out.println(string);
////        }
////        System.out.println(utr.getCurrentDate());
////        System.out.println(utr.getCurrentTime());
//        List<String> games = new ArrayList();
//        games.add(" 15/4/2013"); // biff 85 01:48:03PM
//        games.add(" 25/11/2004");// stex 20 04:16:04PM
//        games.add(" 23/2/2014"); // zero 1050 08:39:47AM
//
//        Collections.sort(games); // Collections.reverseOrde()
//
//        Iterator itrr = games.iterator();
//        while (itrr.hasNext()) {
////            cnt++;
//            Object element = itrr.next();
//            //  System.out.println(element);
//        }
//
//        //List[] a = {"08:39:47AM", "08:31:34PM", "10:48:03PM" };
////        Arrays.sort(a); 
//        //   updateTableRecords.sortArrayAscending(a);
////        for (int i = 0; i < a.length; i++) {
////            int j = a[i];
////            //  System.out.println(j);
////        }
//        List<String> test = new ArrayList();
//
//        test.add("040 00:48:03 AM 15/04/2011");
//        test.add("005 23:48:03 PM 15/04/2013");
//        test.add("040 01:48:03 AM 15/04/2013");
//        test.add("500 01:48:03 AM 15/04/2013");
//
//        Collections.sort(test);
//
//        Iterator itr = test.iterator();
//        while (itr.hasNext()) {
////            cnt++;
//            Object element = itr.next();
////            System.out.println(element);
//        }
    }
}
