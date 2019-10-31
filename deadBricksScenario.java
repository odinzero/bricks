package GAME_BRICKS;

public class deadBricksScenario {

    String scenario;
    // number bubbles defined for one brick and dynamic brick need touch deadbrick 
    // number of times equal to number of bubbles. Repaint deadBrick no need after touch any side
    protected static final String SCENARIO_1 = "MANY_BUBBLES_ONE_BRICK__REPAINT_NO";
    // number bubbles defined for one brick and dynamic brick need touch deadbrick 
    // number of times equal to number of bubbles. Repaint deadBrick need after touch any side
    protected static final String SCENARIO_2 = "MANY_BUBBLES_ONE_BRICK__REPAINT_YES";
    // one bubble defined for one side one brick and dynamic brick need touch each deadbrick 
    // side one time. Repaint deadBrick no need after touch. 
    protected static final String SCENARIO_3 = "ONE_BUBBLE_ONE_SIDE__REPAINT_NO";
    // one bubble defined for one side one brick and dynamic brick need touch each deadbrick 
    // side one time. Repaint deadBrick need after touch.
    protected static final String SCENARIO_4 = "ONE_BUBBLE_ONE_SIDE__REPAINT_YES";
    // EQUAL number and TYPE bubbles defined for one side one brick and dynamic brick need touch  deadbrick 
    // each side number of times equal number bubbles for each side of brick. Repaint deadBrick need after touch.
    protected static final String SCENARIO_5 = "MANY_EUQAL_BUBBLES_ONE_SIDE__REPAINT_YES";
    // RANDOM number and TYPE bubbles defined for one side one brick and dynamic brick need touch  deadbrick 
    // each side number of times equal number bubbles for each side of brick. Repaint deadBrick need after touch.
    protected static final String SCENARIO_6 = "MANY_RANDOM_BUBBLES_ONE_SIDE__REPAINT_YES";
    // number bubbles defined for TOP side one dead brick and dynamic brick need touch  deadbrick 
    // TOP side number of times equal number bubbles for TOP side of dead brick.
    // Repaint deadBrick need after touch.
    protected static final String SCENARIO_7 = "MANY_BUBBLES_TOP_SIDE__REPAINT_YES";
    // number bubbles defined for RIGHT side one dead brick and dynamic brick need touch deadbrick 
    // RIGHT side number of times equal number bubbles for RIGHT side of dead brick.
    // Repaint deadBrick need after touch.
    protected static final String SCENARIO_8 = "MANY_BUBBLES_RIGHT_SIDE__REPAINT_YES";
    // number bubbles defined for BOTTOM side one dead brick and dynamic brick need touch deadbrick 
    // BOTTOM side number of times equal number bubbles for BOTTOM side of dead brick.
    // Repaint deadBrick need after touch.
    protected static final String SCENARIO_9 = "MANY_BUBBLES_BOTTOM_SIDE__REPAINT_YES";
    // number bubbles defined for LEFT side one dead brick and dynamic brick need touch deadbrick 
    // LEFT side number of times equal number bubbles for LEFT side of dead brick.
    // Repaint deadBrick need after touch.
    protected static final String SCENARIO_10 = "MANY_BUBBLES_LEFT_SIDE__REPAINT_YES";
    // number bubbles defined for LEFT and RIGHT side one dead brick and dynamic brick need touch deadbrick 
    // LEFT and RIGHT side number of times equal number bubbles for LEFT and RIGHT side of dead brick.
    // Repaint deadBrick need after touch.
    protected static final String SCENARIO_11 = "MANY_BUBBLES_LEFT_RIGHT_SIDE__REPAINT_YES";
    // number bubbles defined for TOP and BOTTOM side one dead brick and dynamic brick need touch deadbrick 
    // TOP and BOTTOM side number of times equal number bubbles for TOP and BOTTOM side of dead brick.
    // Repaint deadBrick need after touch.
    protected static final String SCENARIO_12 = "MANY_BUBBLES_TOP_BOTTOM_SIDE__REPAINT_YES";
    // number bubbles defined for one brick and dynamic brick can  touch deadbrick 
    // without limitation because bubbles return on the same place after touch .Without repaint
    protected static final String SCENARIO_13 = "MANY_BUBBLES_ONE_IMMORTAL_BRICK_REPAINT_NO";
    // number bubbles defined for one brick and dynamic brick can  touch deadbrick 
    // without limitation because bubbles return on the same place after touch .Without repaint
    protected static final String SCENARIO_14 = "MANY_BUBBLES_ONE_IMMORTAL_BRICK_REPAINT_YES";

    // just for type = 500
    // number bubbleScore  for each side of deadbricks
    int cntBubblesInArray;
    // set number bubbles for each deadBrick 
    int indexOfBubble;

    int cntTouchedLeftSide = 0;
    int cntTouchedRightSide = 0;
    int cntTouchedTopSide = 0;
    int cntTouchedBottomSide = 0;
    bubbleScore[] bubblesLeft = null; // bubbles for left side
    bubbleScore[] bubblesRight = null; // bubbles for right side
    bubbleScore[] bubblesTop = null; // bubbles for top side
    bubbleScore[] bubblesBottom = null; // bubbles for bottom side
    bubbleScore[] all_bubbles = {
        new bubbleScore(5),
        new bubbleScore(10),
        new bubbleScore(25),
        new bubbleScore(50),
        new bubbleScore(100),
        new bubbleScore(150),
        new bubbleScore(300),
        new bubbleScore(500)};

    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;

    bubbleScore[] bubbles = null;

    BRICKS mainBRICKS;
    deadBricks dBrick;

    deadBricksScenario(BRICKS mainBRICKS, deadBricks dBricks) {
        this.mainBRICKS = mainBRICKS;
        this.dBrick = dBricks;

//        choiceRandomScenario();
//        scenario = "MANY_BUBBLES_ONE_SIDE__REPAINT_YES";
    }

    // assign 4 bubbles one type for one deadBrick
    protected bubbleScore[] bubblesForDeadBricks(int type, int length) {

        bubbleScore[] bubblesType = new bubbleScore[length];
        for (int i = 0; i < bubblesType.length; i++) {
            bubblesType[i] = new bubbleScore(type);
        }
        return bubblesType;
    }

    // this method  when each deadbrick have many RANDOM NOT REPEAT bubbles for each side of dead brick
    protected void assignRandomBubblesForEachSide(int number) {

        int cntBubblesInArray = number; // 3
        int[] arr = new int[cntBubblesInArray];

        for (int i = 0; i < cntBubblesInArray; i++) {

            int choice = utils.getRandomNumberInRange(1, all_bubbles.length - 1);
            // whether 'arr' contains new random 'choice'
            boolean bool = utils.contains(arr, all_bubbles[choice].getScoreType());

            if (i == 0) {
                arr[i] = all_bubbles[choice].getScoreType();
            }

            if (i > 0 && !bool) {
                arr[i] = all_bubbles[choice].getScoreType();
            } else if (i > 0 && bool) {
                i--;
            }
        }
        // init each array for each side of deadBrick
        // left side of deadBrick will contain number bubbles according to 'bubblesLeft' array
        bubblesLeft = new bubbleScore[cntBubblesInArray];
        // right side of deadBrick will contain number bubbles according to 'bubblesLeft' array
        bubblesRight = new bubbleScore[cntBubblesInArray];
        // top side of deadBrick will contain number bubbles according to 'bubblesLeft' array
        bubblesTop = new bubbleScore[cntBubblesInArray];
        // bottom side of deadBrick will contain number bubbles according to 'bubblesLeft' array
        bubblesBottom = new bubbleScore[cntBubblesInArray];
        //   TOP, RIGHT, BOTTOM, LEFT side of dead brick
        if (scenario == SCENARIO_5) {
            for (int i = 0; i < arr.length; i++) {
                int scoreType = arr[i];
                bubblesLeft[i] = new bubbleScore(scoreType);
                bubblesRight[i] = new bubbleScore(scoreType);
                bubblesTop[i] = new bubbleScore(scoreType);
                bubblesBottom[i] = new bubbleScore(scoreType);
            }
        }
        // TOP side of dead brick
        if (scenario == SCENARIO_7) {
            for (int i = 0; i < arr.length; i++) {
                int scoreType = arr[i];
                bubblesTop[i] = new bubbleScore(scoreType);
            }
        }
        // RIGHT side of dead brick
        if (scenario == SCENARIO_8) {
            for (int i = 0; i < arr.length; i++) {
                int scoreType = arr[i];
                bubblesRight[i] = new bubbleScore(scoreType);
            }
        }
        // BOTTOM side of dead brick
        if (scenario == SCENARIO_9) {
            for (int i = 0; i < arr.length; i++) {
                int scoreType = arr[i];
                bubblesBottom[i] = new bubbleScore(scoreType);
            }
        }
        // LEFT side of dead brick
        if (scenario == SCENARIO_10) {
            for (int i = 0; i < arr.length; i++) {
                int scoreType = arr[i];
                bubblesLeft[i] = new bubbleScore(scoreType);
            }
        }
        //  RIGHT,  LEFT side of dead brick
        if (scenario == SCENARIO_11) {
            for (int i = 0; i < arr.length; i++) {
                int scoreType = arr[i];
                bubblesLeft[i] = new bubbleScore(scoreType);
                bubblesRight[i] = new bubbleScore(scoreType);
            }
        }
        //  TOP,  BOTTOM side of dead brick
        if (scenario == SCENARIO_12) {
            for (int i = 0; i < arr.length; i++) {
                int scoreType = arr[i];
                bubblesTop[i] = new bubbleScore(scoreType);
                bubblesBottom[i] = new bubbleScore(scoreType);
            }
        }
    }

    protected int[] assignRandomNotRepeatValues(int number, bubbleScore[] all_bubbles_) {

        int cntBubblesInArray = number; // 3
        int[] arr = new int[cntBubblesInArray];

        for (int i = 0; i < cntBubblesInArray; i++) {

            int choice = utils.getRandomNumberInRange(1, all_bubbles_.length - 1);
            // whether 'arr' contains new random 'choice'
            boolean bool = utils.contains(arr, all_bubbles_[choice].getScoreType());

            if (i == 0) {
                arr[i] = all_bubbles_[choice].getScoreType();
            }

            if (i > 0 && !bool) {
                arr[i] = all_bubbles_[choice].getScoreType();
            } else if (i > 0 && bool) {
                i--;
            }
        }
        return arr;
    }

    // FOR SCENARIO 6 - "MANY_RANDOM_BUBBLES_ONE_SIDE__REPAINT_YES"
    // Top side of dead brick
    protected void assignRandomBubblesForTopSide(int numberBubblesTop) {

        int[] arr = assignRandomNotRepeatValues(numberBubblesTop, all_bubbles);

        // top side of deadBrick will contain number bubbles according to 'bubblesTop' array
        bubblesTop = new bubbleScore[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int scoreType = arr[i];
            bubblesTop[i] = new bubbleScore(scoreType);
        }
    }

    // FOR SCENARIO 6 - "MANY_RANDOM_BUBBLES_ONE_SIDE__REPAINT_YES"
    // Right side of dead brick
    protected void assignRandomBubblesForRightSide(int numberBubblesRight) {

        int[] arr = assignRandomNotRepeatValues(numberBubblesRight, all_bubbles);

        // right side of deadBrick will contain number bubbles according to 'bubblesRight' array
        bubblesRight = new bubbleScore[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int scoreType = arr[i];
            bubblesRight[i] = new bubbleScore(scoreType);
        }
    }

    // FOR SCENARIO 6 - "MANY_RANDOM_BUBBLES_ONE_SIDE__REPAINT_YES"
    // Bottom side of dead brick
    protected void assignRandomBubblesForBottomSide(int numberBubblesBottom) {

        int[] arr = assignRandomNotRepeatValues(numberBubblesBottom, all_bubbles);

        // bottom side of deadBrick will contain number bubbles according to 'bubblesBottom' array
        bubblesBottom = new bubbleScore[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int scoreType = arr[i];
            bubblesBottom[i] = new bubbleScore(scoreType);
        }
    }

    // FOR SCENARIO 6 - "MANY_RANDOM_BUBBLES_ONE_SIDE__REPAINT_YES"
    // Left side of dead brick
    protected void assignRandomBubblesForLeftSide(int numberBubblesLeft) {

        int[] arr = assignRandomNotRepeatValues(numberBubblesLeft, all_bubbles);

        // left side of deadBrick will contain number bubbles according to 'bubblesLeft' array
        bubblesLeft = new bubbleScore[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int scoreType = arr[i];
            bubblesLeft[i] = new bubbleScore(scoreType);
        }
    }

    protected void choiceScenario(int choice) {

        switch (choice) {
            default:
                break;
            case 1:
                scenario = SCENARIO_1;
                break;
            case 2:
                scenario = SCENARIO_2;
                break;
            case 3:
                scenario = SCENARIO_3;
                break;
            case 4:
                scenario = SCENARIO_4;
                break;
            case 5:
                scenario = SCENARIO_5;
                break;
            case 6:
                scenario = SCENARIO_6;
                break;
            case 7:
                scenario = SCENARIO_7;
                break;
            case 8:
                scenario = SCENARIO_8;
                break;
            case 9:
                scenario = SCENARIO_9;
                break;
            case 10:
                scenario = SCENARIO_10;
                break;
            case 11:
                scenario = SCENARIO_11;
                break;
            case 12:
                scenario = SCENARIO_12;
                break;
            case 13:
                scenario = SCENARIO_13;
                break;
            case 14:
                scenario = SCENARIO_14;
                break;
        }
    }

    private String choiceRandomScenario() {

        int choice = utils.getRandomNumberInRange(1, 5);

        switch (choice) {
            default:
                break;
            case 1:
                scenario = SCENARIO_1;
                break;
            case 2:
                scenario = SCENARIO_2;
                break;
            case 3:
                scenario = SCENARIO_3;
                break;
            case 4:
                scenario = SCENARIO_4;
                break;
            case 5:
                scenario = SCENARIO_5;
                break;
            case 6:
                scenario = SCENARIO_6;
                break;
            case 7:
                scenario = SCENARIO_7;
                break;
            case 8:
                scenario = SCENARIO_8;
                break;
            case 9:
                scenario = SCENARIO_9;
                break;
            case 10:
                scenario = SCENARIO_10;
                break;
            case 11:
                scenario = SCENARIO_11;
                break;
            case 12:
                scenario = SCENARIO_12;
                break;
            case 13:
                scenario = SCENARIO_13;
                break; 
            case 14:
                scenario = SCENARIO_14;
                break;
        }
        return scenario;
    }
    
    protected void startThread_brickToDown() {
        if (indexOfBubble == 0) {
            // for EFFECT changing of color  !!!
            dBrick.touchRepaint = true;
            Thread tthr = new Thread(thr0);
            tthr.start();
            dBrick.shiftTexture = 1;
            dBrick.repaint();
        }
    }

    private void startBubblesBricksThreads() {
      
        // protection from bugs!!!
//        if (indexOfBubble < 0) {
//            indexOfBubble = 0;
//        }

        if (scenario == SCENARIO_1 || scenario == SCENARIO_2) {
            
            startThread_brickToDown();
            new Thr_1_2(bubbles, indexOfBubble);
        }

        if (scenario == SCENARIO_3 || scenario == SCENARIO_4) {

            startThread_brickToDown();
            new Thr_3_4(bubbles, indexOfBubble);
//            Thread bub = new Thread(multiBubbleThr);
//            bub.start();
        }

        if (scenario == SCENARIO_5 || scenario == SCENARIO_6) {

            startThread_brickToDown();
            new Thr_5_6(bubblesLeft, bubblesRight, bubblesTop, bubblesBottom,
                    cntTouchedLeftSide, cntTouchedRightSide, cntTouchedTopSide, cntTouchedBottomSide,
                    bool1, bool2, bool3, bool4);
//            Thread bub = new Thread(multiBubbleThr5);
//            bub.start();
        }

        if (scenario == SCENARIO_7 || scenario == SCENARIO_8
                || scenario == SCENARIO_9 || scenario == SCENARIO_10) {

            startThread_brickToDown();
            new Thr_7_8_9_10(bubblesLeft, bubblesRight, bubblesTop, bubblesBottom,
                    cntTouchedLeftSide, cntTouchedRightSide, cntTouchedTopSide, cntTouchedBottomSide,
                    bool1, bool2, bool3, bool4);
//            Thread bub = new Thread(oneSideThr);
//            bub.start();
        }

        if (scenario == SCENARIO_11 || scenario == SCENARIO_12) {

            startThread_brickToDown();
            new Thr_11_12(bubblesLeft, bubblesRight, bubblesTop, bubblesBottom,
                    cntTouchedLeftSide, cntTouchedRightSide, cntTouchedTopSide, cntTouchedBottomSide,
                    bool1, bool2, bool3, bool4);
//            Thread bub = new Thread(twoSideThr);
//            bub.start();
        }
        
        if (scenario == SCENARIO_13 || scenario == SCENARIO_14) {

            new Thr_13_14(bubbles, indexOfBubble);
        }
    }

    private void forScenario_1() {

        if (indexOfBubble  > 0 && indexOfBubble <= bubbles.length ) {
            indexOfBubble--;
            System.out.println("forScenario_1(): " + indexOfBubble);
            // start Decrease Thread
            mainBRICKS.score.startThreadDecrease();
            // decrease score
            mainBRICKS.cntScore += bubbles[indexOfBubble].getScoreType();
            mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);

            startBubblesBricksThreads();
        }
    }

    private void forScenario_2() {

        if (indexOfBubble  > 0 && indexOfBubble <= bubbles.length ) {
            indexOfBubble--;
            // start Decrease Thread
            mainBRICKS.score.startThreadDecrease();
            // decrease score
            mainBRICKS.cntScore += bubbles[indexOfBubble].getScoreType();
            mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
            // change view of deadBrick (repaint)
            dBrick.shiftTexture++;
            dBrick.repaint();

            startBubblesBricksThreads();
        } else {
            indexOfBubble = 1000;
        }
    }

    private void forScenario_3_and_4() {

        if (dBrick.leftSide) {
            // counter touch for left side
            cntTouchedLeftSide++;
            if (cntTouchedLeftSide == 1) {
                indexOfBubble--;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score
                mainBRICKS.cntScore += bubbles[0].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
            }
        }
        if (dBrick.rightSide) {
            // counter touch for right side
            cntTouchedRightSide++;
            if (cntTouchedRightSide == 1) {
                indexOfBubble--;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score
                mainBRICKS.cntScore += bubbles[1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
            }
        }
        if (dBrick.topSide) {
            // counter touch for top side
            cntTouchedTopSide++;
            if (cntTouchedTopSide == 1) {
                indexOfBubble--;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score
                mainBRICKS.cntScore += bubbles[3].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
            }
        }
        if (dBrick.bottomSide) {
            // counter touch for bottom side
            cntTouchedBottomSide++;
            if (cntTouchedBottomSide == 1) {
                indexOfBubble--;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score
                mainBRICKS.cntScore += bubbles[2].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
            }
        }

        System.out.println("method for_34");

        startBubblesBricksThreads();
    }

    private void forScenario_5_6() {

        if (dBrick.leftSide) {
            // counter touch for left side
            cntTouchedLeftSide++;
            if (cntTouchedLeftSide <= bubblesLeft.length) {
                indexOfBubble--;
                bool1 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score.  'cntTouchedLeftSide' start from 1
                mainBRICKS.cntScore += bubblesLeft[cntTouchedLeftSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if (dBrick.rightSide) {
            // counter touch for right side
            cntTouchedRightSide++;
            if (cntTouchedRightSide <= bubblesRight.length) {
                indexOfBubble--;
                bool2 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score  'cntTouchedRightSide' start from 1
                mainBRICKS.cntScore += bubblesRight[cntTouchedRightSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if (dBrick.topSide) {
            // counter touch for top side
            cntTouchedTopSide++;
            System.out.println("cntTouchedTopSide: " + cntTouchedTopSide);
            if (cntTouchedTopSide <= bubblesTop.length) {
                indexOfBubble--;
                bool3 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score  'cntTouchedTopSide' start from 1
                mainBRICKS.cntScore += bubblesTop[cntTouchedTopSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if (dBrick.bottomSide) {
            // counter touch for bottom side
            cntTouchedBottomSide++;
            if (cntTouchedBottomSide <= bubblesBottom.length) {
                indexOfBubble--;
                bool4 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score   'cntTouchedBottomSide' start from 1
                mainBRICKS.cntScore += bubblesBottom[cntTouchedBottomSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }

        if ((bool1 && !bool2 && !bool3 && !bool4) || (!bool1 && bool2 && !bool3 && !bool4)
                || (!bool1 && !bool2 && bool3 && !bool4) || (!bool1 && !bool2 && !bool3 && bool4)) {
            startBubblesBricksThreads();
        }
    }

    // "MANY_BUBBLES_TOP_SIDE__REPAINT_YES"
    private void forScenario_7() {
        if (dBrick.topSide) {
            // counter touch for top side
            cntTouchedTopSide++;
            System.out.println("cntTouchedTopSide: " + cntTouchedTopSide);
            if (cntTouchedTopSide <= bubblesTop.length) {
                indexOfBubble--;
                bool3 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score  'cntTouchedTopSide' start from 1
                mainBRICKS.cntScore += bubblesTop[cntTouchedTopSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }

        if ((!bool1 && !bool2 && bool3 && !bool4)) {
            startBubblesBricksThreads();
        }
    }

    // "MANY_BUBBLES_RIGHT_SIDE__REPAINT_YES"
    private void forScenario_8() {
        if (dBrick.rightSide) {
            // counter touch for right side
            cntTouchedRightSide++;
            if (cntTouchedRightSide <= bubblesRight.length) {
                indexOfBubble--;
                bool2 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();   
                // increase score  'cntTouchedRightSide' start from 1
                mainBRICKS.cntScore += bubblesRight[cntTouchedRightSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if ((!bool1 && bool2 && !bool3 && !bool4)) {
            startBubblesBricksThreads();
        }
    }

    // "MANY_BUBBLES_BOTTOM_SIDE__REPAINT_YES"
    private void forScenario_9() {
        if (dBrick.bottomSide) {
            // counter touch for bottom side
            cntTouchedBottomSide++;
            if (cntTouchedBottomSide <= bubblesBottom.length) {
                indexOfBubble--;
                bool4 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score   'cntTouchedBottomSide' start from 1
                mainBRICKS.cntScore += bubblesBottom[cntTouchedBottomSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if ((!bool1 && !bool2 && !bool3 && bool4)) {
            startBubblesBricksThreads();
        }
    }

    // "MANY_BUBBLES_LEFT_SIDE__REPAINT_YES"
    private void forScenario_10() {
        if (dBrick.leftSide) {
            // counter touch for left side
            cntTouchedLeftSide++;
            if (cntTouchedLeftSide <= bubblesLeft.length) {
                indexOfBubble--;
                bool1 = true;
                // start Decrease Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score.  'cntTouchedLeftSide' start from 1
                mainBRICKS.cntScore += bubblesLeft[cntTouchedLeftSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if ((bool1 && !bool2 && !bool3 && !bool4)) {
            startBubblesBricksThreads();
        }
    }

    // "MANY_BUBBLES_LEFT_RIGHT_SIDE__REPAINT_YES"
    private void forScenario_11() {
        if (dBrick.leftSide) {
            // counter touch for left side
            cntTouchedLeftSide++;
            if (cntTouchedLeftSide <= bubblesLeft.length) {
                indexOfBubble--;
                bool1 = true;
                // start Decrease Label Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score.  'cntTouchedLeftSide' start from 1
                mainBRICKS.cntScore += bubblesLeft[cntTouchedLeftSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if (dBrick.rightSide) {
            // counter touch for right side
            cntTouchedRightSide++;
            if (cntTouchedRightSide <= bubblesRight.length) {
                indexOfBubble--;
                bool2 = true;
                // start Decrease Label Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score  'cntTouchedRightSide' start from 1
                mainBRICKS.cntScore += bubblesRight[cntTouchedRightSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
            }
        }
        if ((bool1 && !bool2 && !bool3 && !bool4) || (!bool1 && bool2 && !bool3 && !bool4)) {
            startBubblesBricksThreads();
        }
    }

    //  "MANY_BUBBLES_TOP_BOTTOM_SIDE__REPAINT_YES"
    private void forScenario_12() {
        if (dBrick.topSide) {
            // counter touch for top side
            cntTouchedTopSide++;
            System.out.println("cntTouchedTopSide: " + cntTouchedTopSide);
            if (cntTouchedTopSide <= bubblesTop.length) {
                indexOfBubble--;
                bool3 = true;
                // start Decrease Label Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score  'cntTouchedTopSide' start from 1
                mainBRICKS.cntScore += bubblesTop[cntTouchedTopSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();

                //  System.out.println("method forScenario_12()t: " + bool3);
            }
        }
        if (dBrick.bottomSide) {

            // counter touch for bottom side
            cntTouchedBottomSide++;
            if (cntTouchedBottomSide <= bubblesBottom.length) {
                indexOfBubble--;
                bool4 = true;
                // start Decrease Label Thread
                mainBRICKS.score.startThreadDecrease();
                // decrease score   'cntTouchedBottomSide' start from 1
                mainBRICKS.cntScore += bubblesBottom[cntTouchedBottomSide - 1].getScoreType();
                mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
                // change view of deadBrick (repaint)
                dBrick.shiftTexture++;
                dBrick.repaint();
                // System.out.println("method forScenario_12()b: " + bool4);
            }
        }
        // System.out.println("before out startBubblesBricksThreads :" + bool3 + " " +  bool4 );
        if ((!bool1 && !bool2 && bool3 && !bool4) || (!bool1 && !bool2 && !bool3 && bool4)) {
            //  System.out.println("before startBubblesBricksThreads :" + bool3);
            startBubblesBricksThreads();
        }
    }
    
    protected void forScenario_13() {
        
            indexOfBubble--;    
            if(indexOfBubble < 0) 
               indexOfBubble = bubbles.length - 1;
                       
            System.out.println("forScenario_13(): " + indexOfBubble);
            // start Decrease Label Thread
            mainBRICKS.score.startThreadDecrease();
            // decrease score
            mainBRICKS.cntScore += bubbles[indexOfBubble].getScoreType();
            mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);

            startBubblesBricksThreads();
    }
    
     protected void forScenario_14() {
        
            indexOfBubble--;    
            if(indexOfBubble < 0) 
               indexOfBubble = bubbles.length - 1;
                       
            System.out.println("forScenario_14(): " + indexOfBubble);
            // start Decrease Label Thread
            mainBRICKS.score.startThreadDecrease();
            // decrease score
            mainBRICKS.cntScore += bubbles[indexOfBubble].getScoreType();
            mainBRICKS.score.setText("Score : " + mainBRICKS.cntScore);
            // change view of deadBrick (repaint)
            dBrick.shiftTexture++;
            if(dBrick.shiftTexture > 6)
                dBrick.shiftTexture = 1;
            dBrick.repaint();

            startBubblesBricksThreads();
    }

    protected void scenarii(String scenario) {
        // "MANY_BUBBLES_ONE_BRICK__REPAINT_NO"
        if (scenario == SCENARIO_1) {

            forScenario_1();

            System.out.println("1 indexOfBubble:" + indexOfBubble);
        }
        // "MANY_BUBBLES_ONE_BRICK__REPAINT_YES"
        if (scenario == SCENARIO_2) {

            forScenario_2();

            System.out.println("2 indexOfBubble:" + indexOfBubble);
        }
        // "ONE_BUBBLE_ONE_SIDE__REPAINT_NO"
        if (scenario == SCENARIO_3) {

            forScenario_3_and_4();
            System.out.println("3 indexOfBubble:" + indexOfBubble);
        }
        // "ONE_BUBBLE_ONE_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_4) {

            forScenario_3_and_4();
            // change view of deadBrick (repaint)
            dBrick.shiftTexture++;
            dBrick.repaint();

            System.out.println("4 indexOfBubble:" + indexOfBubble);
        }
        // "MANY_BUBBLES_ONE_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_5) {

            forScenario_5_6();

            System.out.println("5 indexOfBubble:" + indexOfBubble);

            System.out.println("bubblesLeft:" + cntTouchedLeftSide);
            System.out.println("bubblesRight:" + cntTouchedRightSide);
            System.out.println("bubblesTop:" + cntTouchedTopSide);
            System.out.println("bubblesBottom:" + cntTouchedBottomSide);
        }
        // "MANY_RANDOM_BUBBLES_ONE_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_6) {

            forScenario_5_6();

            System.out.println("6 indexOfBubble:" + indexOfBubble);

            System.out.println("bubblesLeft:" + cntTouchedLeftSide + " " + bubblesLeft.length);
            System.out.println("bubblesRight:" + cntTouchedRightSide + " " + bubblesRight.length);
            System.out.println("bubblesTop:" + cntTouchedTopSide + " " + bubblesTop.length);
            System.out.println("bubblesBottom:" + cntTouchedBottomSide + " " + bubblesBottom.length);
        }
        // "MANY_BUBBLES_TOP_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_7) {

            forScenario_7();

            System.out.println("7 indexOfBubble:" + indexOfBubble);
            System.out.println("7 bubblesTop:" + cntTouchedTopSide);
        }
        // "MANY_BUBBLES_RIGHT_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_8) {

            forScenario_8();

            System.out.println("8 indexOfBubble:" + indexOfBubble);
            System.out.println("8 bubblesRight:" + cntTouchedRightSide);
        }
        // "MANY_BUBBLES_BOTTOM_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_9) {

            forScenario_9();

            System.out.println("9 indexOfBubble:" + indexOfBubble);
            System.out.println("9 bubblesBottom:" + cntTouchedBottomSide);
        }
        // "MANY_BUBBLES_LEFT_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_10) {

            forScenario_10();

            System.out.println("10 indexOfBubble:" + indexOfBubble);
            System.out.println("10 bubblesLeft:" + cntTouchedLeftSide);
        }
        // "MANY_BUBBLES_LEFT_RIGHT_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_11) {

            forScenario_11();

            System.out.println("11 indexOfBubble:" + indexOfBubble);
            System.out.println("11 cntTouchedLeftSide:" + cntTouchedLeftSide);
            System.out.println("11 cntTouchedRightSide:" + cntTouchedRightSide);
        }
        // "MANY_BUBBLES_LEFT_RIGHT_SIDE__REPAINT_YES"
        if (scenario == SCENARIO_12) {

            // System.out.println("(scenario == SCENARIO_12): " + bool3 + " " + bool4);
            forScenario_12();

            System.out.println("12 indexOfBubble:" + indexOfBubble);
            System.out.println("12 cntTouchedTopSide:" + cntTouchedTopSide);
            System.out.println("12 cntTouchedBottomSide:" + cntTouchedBottomSide);
        }
        // "MANY_BUBBLES_ONE_IMMORTAL_BRICK_REPAINT_NO"
        if (scenario == SCENARIO_13) {
            
            forScenario_13();

            System.out.println("13 indexOfBubble:" + indexOfBubble);
        }
        // "MANY_BUBBLES_ONE_IMMORTAL_BRICK_REPAINT_YES"
        if (scenario == SCENARIO_14) {
            
            forScenario_14();

            System.out.println("14 indexOfBubble:" + indexOfBubble);
        }
    }

    int cntThread = 0;
    Thread thr0 = new Thread() {
        @Override
        public void run() {
            int y_loc = dBrick.getLocation().y;
            while (dBrick.touchRepaint) {
                try {
                    cntThread++;
                    y_loc += 10;
                    Thread.currentThread().sleep(300);
                    // dead brick start move down
                    dBrick.setBounds(dBrick.getLocation().x, y_loc, 66, 26);
                    dBrick.repaint();
                    if (y_loc >= (mainBRICKS.basic.getY() + mainBRICKS.basic.getHeight())) {
                        dBrick.setBounds(dBrick.getLocation().x, y_loc + 100, 66, 26);
                        dBrick.touchRepaint = false;
                        System.out.println("STOP !");
                    }
                } catch (InterruptedException ex) {
                }
            }
        }
    };

    // For SCENARIO: 1,2
    class Thr_1_2 implements Runnable {

        bubbleScore[] bubbles_;
        int indexOfBubble_;

        Thr_1_2(bubbleScore[] bubbles, int indexOfBubble) {
            this.bubbles_ = bubbles;
            this.indexOfBubble_ = indexOfBubble;

            Thread bub = new Thread(this);
            bub.start();
        }

        @Override
        public void run() {
            boolean moving = true;
            int xloc = 0;
            int yloc = 0;

            xloc = bubbles_[indexOfBubble_].getLocation().x;
            yloc = bubbles_[indexOfBubble_].getLocation().y;
            bubbles[indexOfBubble_].setVisible(true);

            int cntMove = 0;
            while (moving) {
                try {
                    // *** when next LEVEL have passed and 'bubble' is flying smart stop fly!
                    // becuse repaint shutter panel it is not good
                    if (mainBRICKS.getTransitionLevel() <= 0) {
                        bubbles_[indexOfBubble_].setBounds(xloc - 2000, yloc, 46, 46);
                        moving = false;
                        // continue moving bubbles
                    } else {
                        bubbles_[indexOfBubble_].setLocation(xloc, yloc);
                    }

                    yloc -= 10;
                    Thread.currentThread().sleep(200);
                    cntMove++;
                    if (cntMove == 12) {
                        bubbles_[indexOfBubble_].setBounds(xloc - 2000, yloc, 46, 46);
                        moving = false;
                    }
                    bubbles_[indexOfBubble_].repaint();

                } catch (InterruptedException ex) {
                }
            }
        }
    }

    // For SCENARIO: 3,4
    class Thr_3_4 implements Runnable {

        bubbleScore[] bubbles_;
        int indexOfBubble_;

        Thr_3_4(bubbleScore[] bubbles, int indexOfBubble) {
            this.bubbles_ = bubbles;
            this.indexOfBubble_ = indexOfBubble;

            Thread bub = new Thread(this);
            bub.start();
        }

        @Override
        public void run() {
            boolean moving = true;
            int xloc = 0;
            int yloc = 0;

            if (indexOfBubble_ == 0) {
                xloc = bubbles_[0].getLocation().x;
                yloc = bubbles_[0].getLocation().y;
                bubbles_[0].setVisible(true);
            } else if (indexOfBubble_ == 1) {
                xloc = bubbles_[1].getLocation().x;
                yloc = bubbles_[1].getLocation().y;
                bubbles_[1].setVisible(true);
            } else if (indexOfBubble_ == 2) {
                xloc = bubbles_[2].getLocation().x;
                yloc = bubbles_[2].getLocation().y;
                bubbles_[2].setVisible(true);
            } else if (indexOfBubble_ == 3) {
                xloc = bubbles_[3].getLocation().x;
                yloc = bubbles_[3].getLocation().y;
                bubbles_[3].setVisible(true);
            }

            int cntMove = 0;
            while (moving) {
                try {
                    // *** when next LEVEL have passed and 'bubble' is flying smart stop fly!
                    // becuse repaint shutter panel it is not good
                    if (mainBRICKS.getTransitionLevel() <= 0) {
                        bubbles_[0].setBounds(xloc - 2000, yloc, 46, 46);
                        bubbles_[1].setBounds(xloc - 2000, yloc, 46, 46);
                        bubbles_[2].setBounds(xloc - 2000, yloc, 46, 46);
                        bubbles_[3].setBounds(xloc - 2000, yloc, 46, 46);
                        moving = false;
                        // continue moving bubbles
                    } else {
                        if (indexOfBubble_ == 0) {
                            bubbles_[0].setLocation(xloc, yloc);
                        }
                        if (indexOfBubble_ == 1) {
                            bubbles_[1].setLocation(xloc, yloc);
                        }
                        if (indexOfBubble_ == 2) {
                            bubbles_[2].setLocation(xloc, yloc);
                        }
                        if (indexOfBubble_ == 3) {
                            bubbles_[3].setLocation(xloc, yloc);
                        }
                    }

                    // hide bubble[0]
                    if (indexOfBubble_ == 0) {
                        yloc -= 10;
                        Thread.currentThread().sleep(200);
                        cntMove++;
                        if (cntMove == 12) {
                            bubbles_[0].setBounds(xloc - 2000, yloc, 46, 46);
                            moving = false;
                        }
                        bubbles_[0].repaint();
                    }
//                    
                    if (indexOfBubble_ == 1) {
                        yloc -= 10;
                        Thread.currentThread().sleep(200);
                        cntMove++;
                        if (cntMove == 12) {
                            bubbles_[1].setBounds(xloc - 2000, yloc, 46, 46);
                            moving = false;
                        }
                        bubbles_[1].repaint();
                    }

                    if (indexOfBubble_ == 2) {
                        yloc -= 10;
                        Thread.currentThread().sleep(200);
                        cntMove++;
                        if (cntMove == 12) {
                            bubbles_[2].setBounds(xloc - 2000, yloc, 46, 46);
                            moving = false;
                        }
                        bubbles_[2].repaint();
                    }

                    if (indexOfBubble_ == 3) {
                        yloc -= 10;
                        Thread.currentThread().sleep(200);
                        cntMove++;
                        if (cntMove == 12) {
                            bubbles_[3].setBounds(xloc - 2000, yloc, 46, 46);
                            moving = false;
                        }
                        bubbles_[3].repaint();
                    }

                    System.out.println("INSIDE THREAD 3_4");

                } catch (InterruptedException ex) {
                }
            }
        }
    }

    // just for SCENARIO_5 - "MANY_EUQAL_BUBBLES_ONE_SIDE__REPAINT_YES"
    //          SCENARIO_6 - "MANY_RANDOM_BUBBLES_ONE_SIDE__REPAINT_YES"
    class Thr_5_6 implements Runnable {

        bubbleScore[] bubblesLeft_ = null; // bubbles for left side
        bubbleScore[] bubblesRight_ = null; // bubbles for right side
        bubbleScore[] bubblesTop_ = null; // bubbles for top side
        bubbleScore[] bubblesBottom_ = null; // bubbles for bottom side
        int cntTouchedLeftSide_ = 0;
        int cntTouchedRightSide_ = 0;
        int cntTouchedTopSide_ = 0;
        int cntTouchedBottomSide_ = 0;
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;

        Thr_5_6(bubbleScore[] bubblesLeft, bubbleScore[] bubblesRight,
                bubbleScore[] bubblesTop, bubbleScore[] bubblesBottom,
                int cntTouchedLeftSide, int cntTouchedRightSide,
                int cntTouchedTopSide, int cntTouchedBottomSide,
                boolean bool1, boolean bool2, boolean bool3, boolean bool4) {
            this.bubblesLeft_ = bubblesLeft;
            this.bubblesRight_ = bubblesRight;
            this.bubblesTop_ = bubblesTop;
            this.bubblesBottom_ = bubblesBottom;
            this.cntTouchedLeftSide_ = cntTouchedLeftSide;
            this.cntTouchedRightSide_ = cntTouchedRightSide;
            this.cntTouchedTopSide_ = cntTouchedTopSide;
            this.cntTouchedBottomSide_ = cntTouchedBottomSide;
            this.b1 = bool1;
            this.b2 = bool2;
            this.b3 = bool3;
            this.b4 = bool4;
//          Thread bub = new Thread(twoSideThr);
//          bub.start();

            Thread bub = new Thread(this);
            bub.start();
        }

        @Override
        public void run() {
            boolean moving = true;
            int xloc = 0;
            int yloc = 0;
            // location bubbles in left side dead brick
            if (dBrick.leftSide) {
                if (cntTouchedLeftSide_ > 0) {
                    if (cntTouchedLeftSide_ <= bubblesLeft_.length) {
                        xloc = bubblesLeft_[cntTouchedLeftSide_ - 1].getLocation().x;
                        yloc = bubblesLeft_[cntTouchedLeftSide_ - 1].getLocation().y;
                        bubblesLeft_[cntTouchedLeftSide_ - 1].setVisible(true);
                    }
                }
            }
            // location bubbles in right side dead brick
            if (dBrick.rightSide) {
                if (cntTouchedRightSide_ > 0) {
                    if (cntTouchedRightSide_ <= bubblesRight_.length) {
                        xloc = bubblesRight_[cntTouchedRightSide_ - 1].getLocation().x;
                        yloc = bubblesRight_[cntTouchedRightSide_ - 1].getLocation().y;
                        bubblesRight_[cntTouchedRightSide_ - 1].setVisible(true);
                    }
                }
            }
            // location bubbles in top side dead brick
            if (dBrick.topSide) {
                if (cntTouchedTopSide_ > 0) {
                    if (cntTouchedTopSide_ <= bubblesTop_.length) {
                        xloc = bubblesTop_[cntTouchedTopSide_ - 1].getLocation().x;
                        yloc = bubblesTop_[cntTouchedTopSide_ - 1].getLocation().y;
                        bubblesTop_[cntTouchedTopSide_ - 1].setVisible(true);
                    }
                }
            }
            // location bubbles in bottom side dead brick
            if (dBrick.bottomSide) {
                if (cntTouchedBottomSide_ > 0) {
                    if (cntTouchedBottomSide_ <= bubblesBottom_.length) {
                        xloc = bubblesBottom_[cntTouchedBottomSide_ - 1].getLocation().x;
                        yloc = bubblesBottom_[cntTouchedBottomSide_ - 1].getLocation().y;
                        bubblesBottom_[cntTouchedBottomSide_ - 1].setVisible(true);
                    }
                }
            }

            int cntMove = 0;
            while (moving) {
                try {
                    // *** when next LEVEL have passed and 'bubble' is flying smart stop fly!
                    // becuse repaint shutter panel it is not good
                    if (mainBRICKS.getTransitionLevel() <= 0) {
                        // locate bubbles of LEFT side dead brick in invisible state and location
                        for (int i = 0; i < bubblesLeft_.length; i++) {
                            bubblesLeft_[i].setBounds(xloc - 2000, yloc, 46, 46);
                            bubblesLeft_[i].setVisible(false);
                        }
                        // locate bubbles of RIGHT side dead brick in invisible state and location
                        for (int i = 0; i < bubblesRight_.length; i++) {
                            bubblesRight_[i].setBounds(xloc - 2000, yloc, 46, 46);
                            bubblesRight_[i].setVisible(false);
                        }
                        // locate bubbles of TOP side dead brick in invisible state and location
                        for (int i = 0; i < bubblesTop_.length; i++) {
                            bubblesTop_[i].setBounds(xloc - 2000, yloc, 46, 46);
                            bubblesTop_[i].setVisible(false);
                        }
                        // locate bubbles of BOTTOM side dead brick in invisible state and location
                        for (int i = 0; i < bubblesBottom_.length; i++) {
                            bubblesBottom_[i].setBounds(xloc - 2000, yloc, 46, 46);
                            bubblesBottom_[i].setVisible(false);
                        }
                        moving = false;
                    }

                    // hide bubbles of LEFT side dead brick
                    if (b1 && !b2 && !b3 && !b4) {
                        if (cntTouchedLeftSide_ > 0) {
                            if (cntTouchedLeftSide_ <= bubblesLeft_.length) {
                                yloc -= 10;
                                Thread.currentThread().sleep(200);
                                bubblesLeft_[cntTouchedLeftSide_ - 1].setLocation(xloc, yloc);
                                cntMove++;
                                if (cntMove == 12) {
                                    bubblesLeft_[cntTouchedLeftSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                    moving = false;
                                    b1 = false;
                                    bool1 = b1;
                                }
                                bubblesLeft_[cntTouchedLeftSide_ - 1].repaint();
                                // System.out.println("cntTouchedLeftSide:" + cntTouchedLeftSide);
                            }
                        }
                    }
                    // hide bubbles of RIGHT side dead brick
                    if (!b1 && b2 && !b3 && !b4) {
                        if (cntTouchedRightSide_ > 0) {
                            if (cntTouchedRightSide_ <= bubblesRight_.length) {
                                yloc -= 10;
                                Thread.currentThread().sleep(200);
                                bubblesRight_[cntTouchedRightSide_ - 1].setLocation(xloc, yloc);
                                cntMove++;
                                if (cntMove == 12) {
                                    bubblesRight_[cntTouchedRightSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                    moving = false;
                                    b2 = false;
                                    bool2 = b2;
                                }
                                bubblesRight_[cntTouchedRightSide_ - 1].repaint();
//                        System.out.println("cntTouchedRightSide:" + cntTouchedRightSide);
                            }
                        }
                    }
                    // hide bubbles of TOP side dead brick
                    if (!b1 && !b2 && b3 && !b4) {
                        if (cntTouchedTopSide_ > 0) {
                            if (cntTouchedTopSide_ <= bubblesTop_.length) {
                                yloc -= 10;
                                Thread.currentThread().sleep(200);
                                bubblesTop_[cntTouchedTopSide_ - 1].setLocation(xloc, yloc);
                                cntMove++;
                                if (cntMove == 12) {
                                    bubblesTop_[cntTouchedTopSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                    moving = false;
                                    b3 = false;
                                    bool3 = b3;
                                }
                                bubblesTop_[cntTouchedTopSide_ - 1].repaint();
                                //  System.out.println("cntTouchedTopSide:" + cntTouchedTopSide);
                            }
                        }
                    }
                    // hide bubbles of BOTTOM side dead brick
                    if (!b1 && !b2 && !b3 && b4) {
                        if (cntTouchedBottomSide_ > 0) {
                            if (cntTouchedBottomSide_ <= bubblesBottom_.length) {
                                yloc -= 10;
                                Thread.currentThread().sleep(200);
                                bubblesBottom_[cntTouchedBottomSide_ - 1].setLocation(xloc, yloc);
                                cntMove++;
                                if (cntMove == 12) {
                                    bubblesBottom_[cntTouchedBottomSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                    moving = false;
                                    b4 = false;
                                    bool4 = b4;
                                }
                                bubblesBottom_[cntTouchedBottomSide_ - 1].repaint();
                                // System.out.println("cntTouchedBottomSide:" + cntTouchedBottomSide);
                            }
                        }
                    }
                    // System.out.println("yloc: " + yloc);
                } catch (InterruptedException ex) {
                }

            }
        }
    }

    // For SCENARIO: 7  - "MANY_BUBBLES_TOP_SIDE__REPAINT_YES"
    //               8  - "MANY_BUBBLES_RIGHT_SIDE__REPAINT_YES"
    //               9  - "MANY_BUBBLES_BOTTOM_SIDE__REPAINT_YES"
    //               10 - "MANY_BUBBLES_LEFT_SIDE__REPAINT_YES"
    class Thr_7_8_9_10 implements Runnable {

        bubbleScore[] bubblesLeft_ = null; // bubbles for left side
        bubbleScore[] bubblesRight_ = null; // bubbles for right side
        bubbleScore[] bubblesTop_ = null; // bubbles for top side
        bubbleScore[] bubblesBottom_ = null; // bubbles for bottom side
        int cntTouchedLeftSide_ = 0;
        int cntTouchedRightSide_ = 0;
        int cntTouchedTopSide_ = 0;
        int cntTouchedBottomSide_ = 0;
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;

        Thr_7_8_9_10(bubbleScore[] bubblesLeft, bubbleScore[] bubblesRight,
                bubbleScore[] bubblesTop, bubbleScore[] bubblesBottom,
                int cntTouchedLeftSide, int cntTouchedRightSide,
                int cntTouchedTopSide, int cntTouchedBottomSide,
                boolean bool1, boolean bool2, boolean bool3, boolean bool4) {
            this.bubblesLeft_ = bubblesLeft;
            this.bubblesRight_ = bubblesRight;
            this.bubblesTop_ = bubblesTop;
            this.bubblesBottom_ = bubblesBottom;
            this.cntTouchedLeftSide_ = cntTouchedLeftSide;
            this.cntTouchedRightSide_ = cntTouchedRightSide;
            this.cntTouchedTopSide_ = cntTouchedTopSide;
            this.cntTouchedBottomSide_ = cntTouchedBottomSide;
            this.b1 = bool1;
            this.b2 = bool2;
            this.b3 = bool3;
            this.b4 = bool4;
//          Thread bub = new Thread(twoSideThr);
//          bub.start();

            Thread bub = new Thread(this);
            bub.start();
        }

        // Thread oneSideThr = new Thread() {
        @Override
        public void run() {
            boolean moving = true;
            int xloc = 0;
            int yloc = 0;
            if (scenario == SCENARIO_10) {
                // location bubbles in LEFT side dead brick
                if (dBrick.leftSide) {
                    if (cntTouchedLeftSide_ > 0) {
                        if (cntTouchedLeftSide_ <= bubblesLeft_.length) {
                            xloc = bubblesLeft_[cntTouchedLeftSide_ - 1].getLocation().x;
                            yloc = bubblesLeft_[cntTouchedLeftSide_ - 1].getLocation().y;
                            bubblesLeft_[cntTouchedLeftSide_ - 1].setVisible(true);
                        }
                    }
                }
            }
            if (scenario == SCENARIO_8) {
                // location bubbles in RIGHT side dead brick
                if (dBrick.rightSide) {
                    if (cntTouchedRightSide_ > 0) {
                        if (cntTouchedRightSide_ <= bubblesRight_.length) {
                            xloc = bubblesRight_[cntTouchedRightSide_ - 1].getLocation().x;
                            yloc = bubblesRight_[cntTouchedRightSide_ - 1].getLocation().y;
                            bubblesRight_[cntTouchedRightSide_ - 1].setVisible(true);
                        }
                    }
                }
            }
            if (scenario == SCENARIO_7) {
                // location bubbles in TOP side dead brick
                if (dBrick.topSide) {
                    if (cntTouchedTopSide_ > 0) {
                        if (cntTouchedTopSide_ <= bubblesTop_.length) {
                            xloc = bubblesTop_[cntTouchedTopSide_ - 1].getLocation().x;
                            yloc = bubblesTop_[cntTouchedTopSide_ - 1].getLocation().y;
                            bubblesTop_[cntTouchedTopSide_ - 1].setVisible(true);
                        }
                    }
                }
            }
            if (scenario == SCENARIO_9) {
                // location bubbles in BOTTOM side dead brick
                if (dBrick.bottomSide) {
                    if (cntTouchedBottomSide_ > 0) {
                        if (cntTouchedBottomSide_ <= bubblesBottom_.length) {
                            xloc = bubblesBottom_[cntTouchedBottomSide_ - 1].getLocation().x;
                            yloc = bubblesBottom_[cntTouchedBottomSide_ - 1].getLocation().y;
                            bubblesBottom_[cntTouchedBottomSide_ - 1].setVisible(true);
                        }
                    }
                }
            }
            int cntMove = 0;
            while (moving) {
                try {
                    // *** when next LEVEL have passed and 'bubble' is flying smart stop fly!
                    // becuse repaint shutter panel it is not good
                    if (mainBRICKS.getTransitionLevel() <= 0) {

                        if (scenario == SCENARIO_10) {
                            // locate bubbles of LEFT side dead brick in invisible state and location
                            for (int i = 0; i < bubblesLeft_.length; i++) {
                                bubblesLeft_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                bubblesLeft_[i].setVisible(false);
                            }
                        }
                        if (scenario == SCENARIO_8) {
                            // locate bubbles of RIGHT side dead brick in invisible state and location
                            for (int i = 0; i < bubblesRight_.length; i++) {
                                bubblesRight_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                bubblesRight_[i].setVisible(false);
                            }
                        }
                        if (scenario == SCENARIO_7) {
                            // locate bubbles of TOP side dead brick in invisible state and location
                            for (int i = 0; i < bubblesTop_.length; i++) {
                                bubblesTop_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                bubblesTop_[i].setVisible(false);
                            }
                        }
                        if (scenario == SCENARIO_9) {
                            // locate bubbles of BOTTOM side dead brick in invisible state and location
                            for (int i = 0; i < bubblesBottom_.length; i++) {
                                bubblesBottom_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                bubblesBottom_[i].setVisible(false);
                            }
                        }
                        moving = false;
                    }

                    if (scenario == SCENARIO_10) {
                        // hide bubbles of LEFT side dead brick
                        if (b1 && !b2 && !b3 && !b4) {
                            if (cntTouchedLeftSide_ > 0) {
                                if (cntTouchedLeftSide_ <= bubblesLeft_.length) {
                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    bubblesLeft_[cntTouchedLeftSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        bubblesLeft_[cntTouchedLeftSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        moving = false;
                                        b1 = false;
                                        bool1 = b1;
                                    }

                                    bubblesLeft_[cntTouchedLeftSide_ - 1].repaint();

                                    // System.out.println("cntTouchedLeftSide:" + cntTouchedLeftSide);
                                }
                            }
                        }
                    }
                    if (scenario == SCENARIO_8) {
                        // hide bubbles of RIGHT side dead brick
                        if (!b1 && b2 && !b3 && !b4) {
                            if (cntTouchedRightSide_ > 0) {
                                if (cntTouchedRightSide_ <= bubblesRight_.length) {

                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    bubblesRight_[cntTouchedRightSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        bubblesRight_[cntTouchedRightSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        moving = false;
                                        b2 = false;
                                        bool2 = b2;
                                    }

                                    bubblesRight_[cntTouchedRightSide_ - 1].repaint();

                                    System.out.println("THR cntTouchedRightSide:" + cntTouchedRightSide_);
                                }
                            }
                        }
                    }
                    if (scenario == SCENARIO_7) {
                        // hide bubbles of TOP side dead brick
                        if (!b1 && !b2 && b3 && !b4) {
                            if (cntTouchedTopSide_ > 0) {
                                if (cntTouchedTopSide_ <= bubblesTop_.length) {
                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    bubblesTop_[cntTouchedTopSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        bubblesTop_[cntTouchedTopSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        moving = false;
                                        b3 = false;
                                        bool3 = b3;
                                    }
                                    bubblesTop_[cntTouchedTopSide_ - 1].repaint();

                                    //  System.out.println("cntTouchedTopSide:" + cntTouchedTopSide);
                                }
                            }
                        }
                    }
                    if (scenario == SCENARIO_9) {
                        // hide bubbles of BOTTOM side dead brick
                        if (!b1 && !b2 && !b3 && b4) {
                            if (cntTouchedBottomSide_ > 0) {
                                if (cntTouchedBottomSide_ <= bubblesBottom_.length) {

                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    bubblesBottom_[cntTouchedBottomSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        bubblesBottom_[cntTouchedBottomSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        moving = false;
                                        b4 = false;
                                        bool4 = b4;
                                    }
                                    bubblesBottom_[cntTouchedBottomSide_ - 1].repaint();

                                    System.out.println("cntTouchedBottomSide:" + cntTouchedBottomSide_);
                                }
                            }
                        }
                    }

                    // System.out.println("yloc: " + yloc);
                } catch (InterruptedException ex) {
                }

            }
        }
        // };
    }

    // for SCENARIO: 11 - "MANY_BUBBLES_LEFT_RIGHT_SIDE__REPAINT_YES"
    //               12 - "MANY_BUBBLES_TOP_BOTTOM_SIDE__REPAINT_YES" 
    class Thr_11_12 implements Runnable {

        bubbleScore[] bubblesLeft_ = null; // bubbles for left side
        bubbleScore[] bubblesRight_ = null; // bubbles for right side
        bubbleScore[] bubblesTop_ = null; // bubbles for top side
        bubbleScore[] bubblesBottom_ = null; // bubbles for bottom side
        int cntTouchedLeftSide_ = 0;
        int cntTouchedRightSide_ = 0;
        int cntTouchedTopSide_ = 0;
        int cntTouchedBottomSide_ = 0;
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;

        Thr_11_12(bubbleScore[] bubblesLeft, bubbleScore[] bubblesRight,
                bubbleScore[] bubblesTop, bubbleScore[] bubblesBottom,
                int cntTouchedLeftSide, int cntTouchedRightSide,
                int cntTouchedTopSide, int cntTouchedBottomSide,
                boolean bool1, boolean bool2, boolean bool3, boolean bool4) {
            this.bubblesLeft_ = bubblesLeft;
            this.bubblesRight_ = bubblesRight;
            this.bubblesTop_ = bubblesTop;
            this.bubblesBottom_ = bubblesBottom;
            this.cntTouchedLeftSide_ = cntTouchedLeftSide;
            this.cntTouchedRightSide_ = cntTouchedRightSide;
            this.cntTouchedTopSide_ = cntTouchedTopSide;
            this.cntTouchedBottomSide_ = cntTouchedBottomSide;
            this.b1 = bool1;
            this.b2 = bool2;
            this.b3 = bool3;
            this.b4 = bool4;
//          Thread bub = new Thread(twoSideThr);
//          bub.start();

            Thread bub = new Thread(this);
            bub.start();
        }

//    Thread twoSideThr = new Thread() {
        @Override
        public void run() {
            boolean movTest = true;
            int xloc = 0;
            int yloc = 0;
            if (scenario == SCENARIO_11) {
                // location bubbles in left side dead brick
                if (dBrick.leftSide) {
                    if (this.cntTouchedLeftSide_ > 0) {
                        if (this.cntTouchedLeftSide_ <= this.bubblesLeft_.length) {
                            xloc = this.bubblesLeft_[this.cntTouchedLeftSide_ - 1].getLocation().x;
                            yloc = this.bubblesLeft_[this.cntTouchedLeftSide_ - 1].getLocation().y;
                            this.bubblesLeft_[cntTouchedLeftSide_ - 1].setVisible(true);
                        }
                    }
                }
                // location bubbles in right side dead brick
                if (dBrick.rightSide) {
                    if (this.cntTouchedRightSide_ > 0) {
                        if (this.cntTouchedRightSide_ <= this.bubblesRight_.length) {
                            xloc = this.bubblesRight_[this.cntTouchedRightSide_ - 1].getLocation().x;
                            yloc = this.bubblesRight_[this.cntTouchedRightSide_ - 1].getLocation().y;
                            this.bubblesRight_[this.cntTouchedRightSide_ - 1].setVisible(true);
                        }
                    }
                }
            }
            if (scenario == SCENARIO_12) {
                // location bubbles in top side dead brick
                if (dBrick.topSide) {
                    if (this.cntTouchedTopSide_ > 0) {
                        if (this.cntTouchedTopSide_ <= this.bubblesTop_.length) {
                            xloc = this.bubblesTop_[this.cntTouchedTopSide_ - 1].getLocation().x;
                            yloc = this.bubblesTop_[this.cntTouchedTopSide_ - 1].getLocation().y;
                            this.bubblesTop_[this.cntTouchedTopSide_ - 1].setVisible(true);
                            //  System.out.println("Thread: " + this.bubblesTop[this.cntTouchedTopSide - 1].getScoreType());
                        }
                    }
                }
                // location bubbles in bottom side dead brick
                if (dBrick.bottomSide) {
                    if (this.cntTouchedBottomSide_ > 0) {
                        if (this.cntTouchedBottomSide_ <= this.bubblesBottom_.length) {
                            xloc = this.bubblesBottom_[this.cntTouchedBottomSide_ - 1].getLocation().x;
                            yloc = this.bubblesBottom_[this.cntTouchedBottomSide_ - 1].getLocation().y;
                            this.bubblesBottom_[this.cntTouchedBottomSide_ - 1].setVisible(true);
                        }
                    }
                }
            }

            int cntMove = 0;
            while (movTest) {
                try {
                    // *** when next LEVEL have passed and 'bubble' is flying smart stop fly!
                    // becuse repaint shutter panel it is not good
                    if (mainBRICKS.getTransitionLevel() <= 0) {
                        if (scenario == SCENARIO_11) {
                            // locate bubbles of LEFT side dead brick in invisible state and location
                            for (int i = 0; i < this.bubblesLeft_.length; i++) {
                                this.bubblesLeft_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                this.bubblesLeft_[i].setVisible(false);
                            }
                            // locate bubbles of RIGHT side dead brick in invisible state and location
                            for (int i = 0; i < this.bubblesRight_.length; i++) {
                                this.bubblesRight_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                this.bubblesRight_[i].setVisible(false);
                            }
                        }
                        if (scenario == SCENARIO_12) {
                            // locate bubbles of TOP side dead brick in invisible state and location
                            for (int i = 0; i < this.bubblesTop_.length; i++) {
                                this.bubblesTop_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                this.bubblesTop_[i].setVisible(false);
                            }
                            // locate bubbles of BOTTOM side dead brick in invisible state and location
                            for (int i = 0; i < bubblesBottom_.length; i++) {
                                this.bubblesBottom_[i].setBounds(xloc - 2000, yloc, 46, 46);
                                this.bubblesBottom_[i].setVisible(false);
                            }
                        }
                        movTest = false;
                    }

                    if (scenario == SCENARIO_11) {
                        // hide bubbles of LEFT side dead brick
                        if (this.b1 && !this.b2 && !this.b3 && !this.b4) {
                            if (this.cntTouchedLeftSide_ > 0) {
                                if (this.cntTouchedLeftSide_ <= this.bubblesLeft_.length) {
                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    this.bubblesLeft_[this.cntTouchedLeftSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        this.bubblesLeft_[this.cntTouchedLeftSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        movTest = false;
                                        this.b1 = false;
                                        bool1 = this.b1;
                                    }

                                    this.bubblesLeft_[this.cntTouchedLeftSide_ - 1].repaint();

                                    // System.out.println("cntTouchedLeftSide:" + cntTouchedLeftSide);
                                }
                            }
                        }
                        // hide bubbles of RIGHT side dead brick
                        if (!this.b1 && this.b2 && !this.b3 && !this.b4) {
                            if (this.cntTouchedRightSide_ > 0) {
                                if (this.cntTouchedRightSide_ <= this.bubblesRight_.length) {

                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    this.bubblesRight_[this.cntTouchedRightSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        this.bubblesRight_[this.cntTouchedRightSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        movTest = false;
                                        this.b2 = false;
                                        bool2 = this.b2;
                                    }

                                    this.bubblesRight_[this.cntTouchedRightSide_ - 1].repaint();

//                        System.out.println("cntTouchedRightSide:" + cntTouchedRightSide);
                                }
                            }
                        }
                    }
                    if (scenario == SCENARIO_12) {
                        // hide bubbles of TOP side dead brick
                        if (!this.b1 && !this.b2 && this.b3 && !this.b4) {
                            if (this.cntTouchedTopSide_ > 0) {
                                if (this.cntTouchedTopSide_ <= this.bubblesTop_.length) {
                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    this.bubblesTop_[this.cntTouchedTopSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        this.bubblesTop_[this.cntTouchedTopSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        movTest = false;
                                        this.b3 = false;
                                        bool3 = this.b3;
                                    }
                                    this.bubblesTop_[this.cntTouchedTopSide_ - 1].repaint();

//                                     System.out.println("INSIDE Top Thread:");
                                }
                            }
                        }
                        // hide bubbles of BOTTOM side dead brick
                        if (!this.b1 && !this.b2 && !this.b3 && this.b4) {
                            if (this.cntTouchedBottomSide_ > 0) {
                                if (this.cntTouchedBottomSide_ <= this.bubblesBottom_.length) {

                                    yloc -= 10;
                                    Thread.currentThread().sleep(200);
                                    this.bubblesBottom_[this.cntTouchedBottomSide_ - 1].setLocation(xloc, yloc);
                                    cntMove++;
                                    if (cntMove == 12) {
                                        this.bubblesBottom_[this.cntTouchedBottomSide_ - 1].setBounds(xloc - 2000, yloc, 46, 46);
                                        movTest = false;
                                        this.b4 = false;
                                        bool4 = this.b4;
                                        // System.out.println("dead bubbles:" + bool4 + " " + this.b4);
                                        for (int i = 0; i < bubblesBottom.length; i++) {
                                            bubbleScore bs = bubblesBottom[i];
                                            System.out.println("bs: " + bs.getLocation().x + " "
                                                    + bs.getLocation().y + " " + i);
                                        }
                                    }
                                    this.bubblesBottom_[this.cntTouchedBottomSide_ - 1].repaint();

                                    // System.out.println("cntTouchedBottomSide:" + cntTouchedBottomSide);
//                                    System.out.println("cntMove:" + cntMove
//                                            + " bubbles:" + this.bubblesBottom[this.cntTouchedBottomSide - 1].getScoreType());
                                }
                            }
                        }
                    }

                    // System.out.println("yloc: " + yloc);
                } catch (InterruptedException ex) {
                }

            }
        }
//    };
    }
     
    // For SCENARIO: 13,14
    class Thr_13_14 implements Runnable {

        bubbleScore[] bubbles_;
        int indexOfBubble_;
        int first_xloc;
        int first_yloc;
        int counter = 0;

        Thr_13_14(bubbleScore[] bubbles, int indexOfBubble) {
            this.bubbles_ = bubbles;
            this.indexOfBubble_ = indexOfBubble;

            Thread bub = new Thread(this);
            bub.start();
        }

        @Override
        public void run() {
            boolean moving = true;
            int xloc = 0;
            int yloc = 0;

            xloc = bubbles_[indexOfBubble_].getLocation().x;
            yloc = bubbles_[indexOfBubble_].getLocation().y;
            bubbles[indexOfBubble_].setVisible(true);
            
            counter++;
            // remember first coordiates of bubble
            if(counter == 1) {
              first_xloc = xloc;
              first_yloc = yloc;      
            }

            int cntMove = 0;
            while (moving) {
                try {
                    // *** when next LEVEL have passed and 'bubble' is flying smart stop fly!
                    // becuse repaint shutter panel it is not good
                    if (mainBRICKS.getTransitionLevel() <= 0) {
                        for (int i = 0; i < bubbles_.length; i++) {
                           bubbles_[i].setBounds(xloc - 2000, yloc, 46, 46);   
                            }
                        moving = false;
                        // continue moving bubbles
                    } else {
                        bubbles_[indexOfBubble_].setLocation(xloc, yloc);
                    }

                    if((mainBRICKS.turn_Xup && mainBRICKS.turn_Ylow)||
                       (mainBRICKS.turn_Xup && mainBRICKS.turn_Yup) ) {
                      if (cntMove < 3) {
                           xloc += 5;  
                           yloc -= 5;
                      }
                      else if(cntMove > 2 && cntMove < 4) {
                           xloc += 3;  
                           yloc -= 3;
                      } 
                      else if(cntMove > 3 && cntMove < 6) {
                           xloc += 2;  
                           yloc += 2;
                      }
                      else if(cntMove > 5 && cntMove < 7) {
                           xloc += 1;  
                           yloc += 5;
                      }
                      else if(cntMove > 6 && cntMove < 14)
                      {
                           xloc += 0;  
                           yloc += 10;
                      }
                    }
                    
                    if((mainBRICKS.turn_Xlow && mainBRICKS.turn_Yup)||
                       (mainBRICKS.turn_Xlow && mainBRICKS.turn_Ylow) ) {
                      if (cntMove < 3) {
                           xloc -= 5;  
                           yloc -= 5;
                         //  System.out.println("2:" + "1");
                      }
                      else if(cntMove > 2 && cntMove < 4) {
                           xloc -= 3;  
                           yloc -= 3;
                         //  System.out.println("2:" + "2");
                      } 
                      else if(cntMove > 3 && cntMove < 6) {
                           xloc -= 2;  
                           yloc += 2;
                      }
                      else if(cntMove > 5 && cntMove < 7) {
                           xloc -= 1;  
                           yloc += 5;
                      }
                      else if(cntMove > 6 && cntMove < 14)
                      { 
                           xloc -= 0;
                           yloc += 10;
                         //  System.out.println("2:" + "3");
                      }
                    }
                    
                    Thread.currentThread().sleep(200);
                    cntMove++;
                    if (cntMove == 14) {
                        yloc = bubbles_[indexOfBubble_].getLocation().y - 7 * 10;
                        bubbles_[indexOfBubble_].setBounds(xloc, yloc, 46, 46);
                        bubbles_[indexOfBubble_].setVisible(false); 
                        moving = false;
                        counter = 0;
                    }
                    bubbles_[indexOfBubble_].repaint();

                } catch (InterruptedException ex) {
                }
            }
        }
    }

}
