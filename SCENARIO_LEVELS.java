
package GAME_BRICKS;

public class SCENARIO_LEVELS {

    LEVEL_0_10 level_0_10;
    LEVEL_11_20 level_11_20;
    LEVEL_21_30 level_21_30;

    int speed = 70;

//    int  levelTransition = 20; // qty static bricks in each level
//    int  LEVEL  = 0;          // count level for the next level of game

    BRICKS mainBricks ;

    SCENARIO_LEVELS( BRICKS main ) {
       mainBricks = main;
    }
     protected int changeLevel() {
      if( mainBricks.LEVEL == 0 )
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_0();
         mainBricks.basic.setType(1);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
        }
      else if (mainBricks.LEVEL == 1)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_1();
         mainBricks.basic.setType(1);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(65);
        }
      else if (mainBricks.LEVEL == 2)
        {
        level_0_10 = new LEVEL_0_10(mainBricks);
        level_0_10.setLEVEL_2();
        mainBricks.basic.setType(2);
        mainBricks.level.setText("Level : " + mainBricks.LEVEL);
        setSpeed(65);
        }
      else if (mainBricks.LEVEL == 3)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_3();
         mainBricks.basic.setType(3);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(65);
        }
       else if (mainBricks.LEVEL == 4)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_4();
         mainBricks.basic.setType(4);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(65);
        }
        else if (mainBricks.LEVEL == 5)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_5();
         mainBricks.basic.setType(5);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(65);
        }
        else if (mainBricks.LEVEL == 6)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_6();
         mainBricks.basic.setType(6);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(60);
        }
       else if (mainBricks.LEVEL == 7)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_7();
         mainBricks.basic.setType(7);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(60);
        }
       else if (mainBricks.LEVEL == 8)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_8();
         mainBricks.basic.setType(8);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(60);
        }
       else if (mainBricks.LEVEL == 9)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_9();
         mainBricks.basic.setType(9);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(60);
        }
       else if (mainBricks.LEVEL == 10)
        {
         level_0_10 = new LEVEL_0_10(mainBricks);
         level_0_10.setLEVEL_10();
         mainBricks.basic.setType(10);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(60);
        }
       else if (mainBricks.LEVEL == 11)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_11();
         mainBricks.basic.setType(11);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(50);
        }
       else if (mainBricks.LEVEL == 12)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_12();
         mainBricks.basic.setType(12);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(50);
        }
        else if (mainBricks.LEVEL == 13)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_13();
         mainBricks.basic.setType(13);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(50);
        }
        else if (mainBricks.LEVEL == 14)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_14();
         mainBricks.basic.setType(14);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(50);
        }
        else if (mainBricks.LEVEL == 15)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_15();
         mainBricks.basic.setType(15);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(50);
        }
        else if (mainBricks.LEVEL == 16)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_16();
         mainBricks.basic.setType(16);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(40);
        }
        else if (mainBricks.LEVEL == 17)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_17();
         mainBricks.basic.setType(17);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(40);
        }
         else if (mainBricks.LEVEL == 18)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_18();
         mainBricks.basic.setType(18);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(40);
        }
        else if (mainBricks.LEVEL == 19)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_19();
         mainBricks.basic.setType(19);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(40);
        }
         else if (mainBricks.LEVEL == 20)
        {
         level_11_20 = new LEVEL_11_20(mainBricks);
         level_11_20.setLEVEL_20();
         mainBricks.basic.setType(20);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(40);
        }
         else if (mainBricks.LEVEL == 21)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_21();
         mainBricks.basic.setType(21);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(30);
        }
          else if (mainBricks.LEVEL == 22)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_22();
         mainBricks.basic.setType(22);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(30);
        }
          else if (mainBricks.LEVEL == 23)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_23();
         mainBricks.basic.setType(23);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(30);
        }
          else if (mainBricks.LEVEL == 24)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_24();
         mainBricks.basic.setType(24);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(30);
        }
         else if (mainBricks.LEVEL == 25)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_25();
         mainBricks.basic.setType(25);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(30);
        }
          else if (mainBricks.LEVEL == 26)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_26();
         mainBricks.basic.setType(26);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(20);
        }
          else if (mainBricks.LEVEL == 27)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_27();
         mainBricks.basic.setType(27);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(20);
        }
          else if (mainBricks.LEVEL == 28)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_28();
         mainBricks.basic.setType(28);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(20);
        }
          else if (mainBricks.LEVEL == 29)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_29();
         mainBricks.basic.setType(29);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(10);
        }
          else if (mainBricks.LEVEL == 30)
        {
         level_21_30 = new LEVEL_21_30(mainBricks);
         level_21_30.setLEVEL_30();
         mainBricks.basic.setType(30);
         mainBricks.level.setText("Level : " + mainBricks.LEVEL);
         setSpeed(10);
        }
         return mainBricks.LEVEL;
     }

      public void resetTransitionLevel() {
        if( mainBricks.LEVEL < 11)
        mainBricks.levelTransition = level_0_10.getQtyBricks();
        else if ( (mainBricks.LEVEL >= 11)&&(mainBricks.LEVEL < 21) )
        mainBricks.levelTransition = level_11_20.getQtyBricks();
        else if ( (mainBricks.LEVEL >= 21)&&(mainBricks.LEVEL < 31))
        mainBricks.levelTransition = level_21_30.getQtyBricks();
    }
      
      

       protected void setSpeed(int sp) {
        speed = sp;
    }

       protected int getSpeed() {
        return speed;
    }

      public void LEVEL_SCENARIO(int level_cnt ) throws InterruptedException {
          // clear all states control brick, dynamic brick, static bricks, bubbles, turnX ...
                        mainBricks.statesOfPosition.emptyAllStates();
         // output to screen  label "Level 2" from shutterPanel class
                        mainBricks.shutterPanel.setLEVEL_CNT(level_cnt); // shutterPanel in CLASS:: BRICKS
                        mainBricks.shutterPanel.turnOn = true;
                        Thread thr = new Thread(mainBricks.shutterPanel.addPiece);
                        thr.start();
                        mainBricks.frame.repaint();
                        
                        mainBricks.stopFencePanel();

                        Thread.currentThread().sleep(15000);
                        mainBricks.choiceMoveDirection();
                        mainBricks.setInitConditionForNextLevel() ;
                  // clear panel for tne next level ::
                  // protection from bug: may be mistake of calculation -> levelTransition and
                  // some staticBricks can be on screen but level already passed !
                  // It need clear screen for the next level!
                         if(  level_cnt < 12 )    // < 3
                        level_0_10.clearPanelForNextLevel();  // >= 3
                         if(  (level_cnt >= 12)&&(level_cnt < 22) ) // (level_cnt >= 11)&&(level_cnt < 21)
                        level_11_20.clearPanelForNextLevel();
                         if(  (level_cnt >= 22)&&(level_cnt < 31) )
                        level_21_30.clearPanelForNextLevel();
                        mainBricks.frame.repaint();

                        changeLevel(); // switch to the next level
                        resetTransitionLevel();
    }
}
