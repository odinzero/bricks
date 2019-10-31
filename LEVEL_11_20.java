package GAME_BRICKS;

public class LEVEL_11_20 {

    BRICKS mainBRICKS;
    bubbleScore[] bubbles;
    staticBricks[] bricks;
    int screenWidth;
    int qtyBricks;

    LEVEL_11_20(BRICKS main) {
        mainBRICKS = main;
        screenWidth = mainBRICKS.getBasicPanel().getWidth() - 60;
    }

    // clear panel for the next level
    protected void clearPanelForNextLevel() {
        for (int k = 0; k < bricks.length; k++) {
            if (bricks[k] instanceof staticBricks) {
                mainBRICKS.getBasicPanel().remove(bricks[k]);
            }
        }
        for (int k = 0; k < bubbles.length; k++) {
            if (bubbles[k] instanceof bubbleScore) {
                bubbles[k].setLocation(1, -2000);
                mainBRICKS.getBasicPanel().remove(bubbles[k]);
            }
        }
    }

    protected int getQtyBricks() {
        qtyBricks = bricks.length;
        return qtyBricks;
    }

    protected void setQtyBricks(int qty) {
        qtyBricks = qty;
    }

    protected void setLEVEL_11() {
        int x_loc = -62;
        int y_loc = 30;
        bricks = new staticBricks[142];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 26;
            }
            if (k == 15) { x_loc = 37; }  // 2 row
            if (k == 29) { x_loc = 136; } // 3 row
            if (k == 34) {  x_loc = 532; } // 3 row
            if (k == 39 || k == 71) { x_loc = 169; y_loc += 26; } // 4,11 row 
            if (k == 43 || k == 75) { x_loc = 565; }             // 4,11 row
            if (k == 47 || k == 65) { x_loc = 202; y_loc += 26; } // 5,10 row
            if (k == 50 || k == 68) { x_loc = 598; }             // 5,10 row
            if (k == 53 || k == 61) {  x_loc = 235; y_loc += 26; } // 6,9 row
            if (k == 55 || k == 63) { x_loc = 631; }             // 6,9 row
            if (k == 57 || k == 59) { x_loc = 268; y_loc += 26; } // 7,8 row
            if (k == 58 || k == 60) {  x_loc = 664; }             // 7,8 row

            if (k == 79) { x_loc = 136; y_loc += 26; }  // 12 row
            if (k == 84) { x_loc = 532; }               // 12 row

            if (k == 89) { x_loc = 37; y_loc += 26; }    // 13 row

            if (k == 118) {  x_loc = 169; y_loc += 26;}    //v 15 row
            if (k > 118 && k < 121) { x_loc -= 39;}    //v 15 row

            if (k == 121) { x_loc = 367;  }    //v 15 row
            if (k > 121 && k < 124) { x_loc -= 39; }    //v 15 row

            if (k == 124) {  x_loc = 565; }    //v 15 row
            if (k > 124 && k < 127) { x_loc -= 39; }    //v 15 row

            if (k == 127) { x_loc = 763; }    //v 15 row
            if (k > 127 && k < 130) { x_loc -= 39; }    //v 15 row

            if (k == 130) { x_loc = 70; y_loc = 160; }    // 6 row
            if (k == 131) { x_loc = 37; y_loc += 26; }    // 7 row
            if (k == 133) { x_loc = 70; y_loc += 26;  }    // 8 row

            if (k == 134) { x_loc = 466; y_loc = 160; }    // 6 row
            if (k == 135) {  x_loc = 433;  y_loc += 26; }    // 7 row
            if (k == 137) { x_loc = 466; y_loc += 26; }    // 8 row

            if (k == 138) { x_loc = 862; y_loc = 160;}    // 6 row
            if (k == 139) {  x_loc = 829;  y_loc += 26; }    // 7 row
            if (k == 141) { x_loc = 862; }    // 8 row
//            System.out.println("k34: " + x_loc);

            if (k == 1 || k == 3 || k == 5 || k == 7 || k == 9 || k == 11 || k == 13 || k == 15
                    || k == 17 || k == 19 || k == 21 || k == 23 || k == 25 || k == 27
                    || k == 89 || k == 91 || k == 93 || k == 95 || k == 97 || k == 99
                    || k == 101 || k == 104 || k == 106 || k == 108 || k == 110
                    || k == 112 || k == 114 || k == 116) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 10, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            } else if ((k > 28 && k < 40) || k == 42 || k == 43 || k == 46
                    || k == 47 || k == 49 || k == 50 || k == 52 || (k > 52 && k < 57)
                    || (k > 60 && k < 65) || k == 65 || k == 67 || k == 68 || k == 70
                    || k == 71 || k == 74 || k == 75 || k == 78 || (k > 78 && k < 89)) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 50, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            } else if (k == 40 || k == 41 || k == 44 || k == 45
                    || k == 48 || k == 51 || k == 66 || k == 69 || k == 72 || k == 73
                    || k == 76 || k == 77) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(300);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 300, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            } else if (k == 57 || k == 58 || k == 59 || k == 60) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(100);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 100, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            } else if (k == 118 || k == 120 || k == 121 || k == 123 || k == 124
                    || k == 126 || k == 127 || k == 129 || k == 130 || k == 133
                    || k == 134 || k == 137 || k == 138 || k == 141) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);

            } else {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 25, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }

            // vertical bricks
            if ((k > 117 && k < 121) || (k > 120 && k < 124)
                    || (k > 123 && k < 127) || (k > 126 && k < 130)) {
                bricks[k].setBrickForm(0, 25, 65);
                bricks[k].setBounds(x_loc, y_loc, 26, 66);
            } // horizontal bricks
            else {
                bricks[k].setBrickForm(0, 65, 25);
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // add bubbles to panel
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);
            // add bricks to panel
            mainBRICKS.getBasicPanel().add(bricks[k]);
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
        }
    }

    protected void setLEVEL_12() {
        int x_loc = -62;
        int y_loc = 30;
        bricks = new staticBricks[118];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }
            // 1
            if (k == 0) { x_loc = 37; }   // h
            if (k == 2) { x_loc = 37; y_loc = 56; } // v
            if (k == 3) { x_loc = 63; y_loc = 96; } // h
            // 2
            if (k == 4) { x_loc = 37; y_loc = 146; }   // h
            if (k == 6) { x_loc = 37; y_loc = 172; } // v
            if (k == 7) { x_loc = 63; y_loc = 212;  } // h
            // 3
            if (k == 8) {  x_loc = 37;  y_loc = 262;  }   // h
            if (k == 10) { x_loc = 37; y_loc = 288; } // v
            if (k == 11) {  x_loc = 63;  y_loc = 328;   } // h
            // 4
            if (k == 12) { x_loc = 37; y_loc = 378; }   // h
            if (k == 14) { x_loc = 37; y_loc = 404; } // v
            if (k == 15) { x_loc = 63; y_loc = 444; } // h

            if (k == 16) { x_loc = 37; y_loc = 494;  } // h
            if (k == 17) { x_loc = 63; y_loc = 520; } // h

            if (k == 18) {  x_loc = 155;  y_loc = 51; } // h
            if (k == 19) { x_loc = 155; y_loc = 77;} // h

            if (k == 20) { x_loc = 155; y_loc = 167; } // h
            if (k == 21) { x_loc = 155; y_loc = 193;} // h

            if (k == 22) { x_loc = 155; y_loc = 283; } // h
            if (k == 23) { x_loc = 155; y_loc = 309; } // h

            if (k == 24) { x_loc = 155; y_loc = 399; } // h
            if (k == 25) { x_loc = 155;  y_loc = 425; } // h

            if (k == 26) { x_loc = 155;  y_loc = 494; } // h
            if (k == 27) { x_loc = 181;  y_loc = 520; } // h
            // 1 left
            if (k == 28) { x_loc = 247; y_loc = 30; }   // h
            if (k == 30) { x_loc = 247; y_loc = 56;  } // v
            if (k == 31) { x_loc = 273; y_loc = 96; } // h
            // 2 left
            if (k == 32) { x_loc = 247; y_loc = 146;  }   // h
            if (k == 34) { x_loc = 247; y_loc = 172;  } // v
            if (k == 35) { x_loc = 273; y_loc = 212;  } // h
            // 3 left
            if (k == 36) { x_loc = 247; y_loc = 262; }   // h
            if (k == 38) {  x_loc = 247;  y_loc = 288; } // v
            if (k == 39) {  x_loc = 273;  y_loc = 328; } // h
            // 4 left
            if (k == 40) {  x_loc = 247; y_loc = 378; }   // h
            if (k == 42) {  x_loc = 247;  y_loc = 404;  } // v
            if (k == 43) { x_loc = 273; y_loc = 444; } // h

            if (k == 44) {  x_loc = 273; y_loc = 494; } // h
            if (k == 45) { x_loc = 299; y_loc = 520; } // h
            // 1  right
            if (k == 46) { x_loc = 665; y_loc = 30; }   // h
            if (k == 48) { x_loc = 665; y_loc = 56;  } // v
            if (k == 49) {  x_loc = 691;  y_loc = 96;} // h
            // 2  right
            if (k == 50) { x_loc = 665; y_loc = 146; }   // h
            if (k == 52) { x_loc = 665; y_loc = 172; } // v
            if (k == 53) {  x_loc = 691;  y_loc = 212;  } // h
            // 3 right
            if (k == 54) { x_loc = 665; y_loc = 262;}   // h
            if (k == 56) { x_loc = 665; y_loc = 288; } // v
            if (k == 57) {  x_loc = 691;  y_loc = 328;} // h
            // 4 right
            if (k == 58) { x_loc = 665;  y_loc = 378; }   // h
            if (k == 60) { x_loc = 665; y_loc = 404;} // v
            if (k == 61) { x_loc = 691; y_loc = 444;  } // h
            // right first bottom
            if (k == 62) {  x_loc = 665;  y_loc = 496; }   // h
            if (k == 63) { x_loc = 639; y_loc = 522; }   // h

            if (k == 64) { x_loc = 783; y_loc = 51; } // h
            if (k == 65) { x_loc = 783; y_loc = 77; } // h

            if (k == 66) {x_loc = 783; y_loc = 167; } // h
            if (k == 67) { x_loc = 783; y_loc = 193; } // h

            if (k == 68) {  x_loc = 783; y_loc = 283; } // h
            if (k == 69) {  x_loc = 783; y_loc = 309; } // h

            if (k == 70) { x_loc = 783; y_loc = 399; } // h
            if (k == 71) {  x_loc = 783;  y_loc = 425;} // h

            if (k == 72) { x_loc = 783; y_loc = 494; } // h
            if (k == 73) { x_loc = 757; y_loc = 520;  } // h
            // 1  right
            if (k == 74) {  x_loc = 875; y_loc = 30; }   // h
            if (k == 76) {  x_loc = 875;  y_loc = 56; } // v
            if (k == 77) { x_loc = 901; y_loc = 96;  } // h
            // 2  right
            if (k == 78) { x_loc = 875; y_loc = 146; }   // h
            if (k == 80) { x_loc = 875; y_loc = 172; } // v
            if (k == 81) {  x_loc = 901;  y_loc = 212;  } // h
            // 3 right
            if (k == 82) {  x_loc = 875;  y_loc = 262; }   // h
            if (k == 84) { x_loc = 875; y_loc = 288; } // v
            if (k == 85) {  x_loc = 901; y_loc = 328; } // h
            // 4 right
            if (k == 86) {  x_loc = 875;  y_loc = 378; }   // h
            if (k == 88) {  x_loc = 875;  y_loc = 404; } // v
            if (k == 89) { x_loc = 901; y_loc = 444; } // h
            // right 3 bottom
            if (k == 90) { x_loc = 901; y_loc = 496;  }   // h
            if (k == 91) {  x_loc = 875;  y_loc = 522;}   // h
            // center 1 left
            if (k == 92) { x_loc = 365; y_loc = 56; }   // v
            if (k == 93) {  x_loc = 391;  y_loc = 82; }   // v
            if (k == 94) {  x_loc = 417;  y_loc = 108;}   // v
            if (k == 95) { x_loc = 443; y_loc = 134;}   // v
            if (k == 96) { x_loc = 469; y_loc = 160; }   // v
            // center 1 right
            if (k == 97) {  x_loc = 613;  y_loc = 56;}   // v
            if (k == 98) { x_loc = 587; y_loc = 82; }   // v
            if (k == 99) { x_loc = 561; y_loc = 108; }   // v
            if (k == 100) { x_loc = 535; y_loc = 134; }   // v
            if (k == 101) { x_loc = 509; y_loc = 160;  }   // v
            // center 2 left
            if (k == 102) {  x_loc = 365;  y_loc = 288;   }   // v
            if (k == 103) { x_loc = 391; y_loc = 314; }   // v
            if (k == 104) { x_loc = 417; y_loc = 340; }   // v
            if (k == 105) {  x_loc = 443;  y_loc = 366; }   // v
            if (k == 106) { x_loc = 469; y_loc = 392;  }   // v
            // center 2 right
            if (k == 107) {  x_loc = 613;  y_loc = 288; }  // v
            if (k == 108) { x_loc = 587; y_loc = 314;}  // v
            if (k == 109) { x_loc = 561; y_loc = 340; }  // v
            if (k == 110) {  x_loc = 535;  y_loc = 366; }  // v
            if (k == 111) { x_loc = 509; y_loc = 392; }  // v

            if (k == 112) {  x_loc = 469;  y_loc = 30; }   // h
            if (k == 113) { x_loc = 469; y_loc = 56; }   // h
            if (k == 114) { x_loc = 469; y_loc = 82;   }   // h

            if (k == 115) {  x_loc = 469;  y_loc = 262; }   // h
            if (k == 116) { x_loc = 469; y_loc = 288; }   // h
            if (k == 117) { x_loc = 469; y_loc = 314; }   // h
            // 10 orange
            if ((k > -1 && k < 4) || (k > 7 && k < 12) || k == 16 || k == 17 ||
                (k > 27 && k < 32)|| (k > 35 && k < 40)|| k == 44 || k == 45 ||
                (k > 45 && k < 50)|| (k > 53 && k < 58)|| k == 62 || k == 63 ||
                (k > 73 && k < 78)|| (k > 81 && k < 86)|| k == 90 || k == 91) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 10, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            } //25 red
            else if ((k > 3 && k < 8) || (k > 11 && k < 16) || (k > 31 && k < 36) ||
                     (k > 39 && k < 44) || (k > 49 && k < 54)|| (k > 57 && k < 62) ||
                     (k > 77 && k < 82) || (k > 85 && k < 90)) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 25, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            } // 50 green
            else if ((k > 17 && k < 26) || (k > 63 && k < 72) || k == 95 ||
                      k == 96 || k == 100 || k == 101 || k == 105 || k == 106 ||
                      k == 110 || k == 111 ) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 50, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
             // 100 blue
            else if ( (k > 91 && k < 95) || (k > 96 && k < 100) || 
                      (k > 101 && k < 105)||(k > 106 && k < 110)) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(100);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 100, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 150 
            else if ( k == 112 || k == 113 || k == 114 ) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(150);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 150, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 300 
            else if ( k == 116 ) {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(300);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 300, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 5
            else {
                // add bubbles for each static bricks
                bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
                bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
                // create and layout bricks
                bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }

            // add bubbles to panel
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);
            // add bricks to panel
            mainBRICKS.getBasicPanel().add(bricks[k]);

            // vertical bricks
            if (k == 1 || k == 2 || k == 5 || k == 6 || k == 9 || k == 10
                    || k == 13 || k == 14 || k == 29 || k == 30 || k == 33 || k == 34
                    || k == 37 || k == 38 || k == 41 || k == 42 || k == 47 || k == 48
                    || k == 51 || k == 52 || k == 55 || k == 56 || k == 59 || k == 60
                    || k == 75 || k == 76 || k == 79 || k == 80 || k == 83 || k == 84
                    || k == 87 || k == 88 || (k > 91 && k < 112)) {
                bricks[k].setBrickForm(0, 25, 65);
                bricks[k].setBounds(x_loc, y_loc, 26, 66);
            } // horizontal bricks
            else {
                bricks[k].setBrickForm(0, 65, 25);
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }

            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
        }
    }

    protected void setLEVEL_13() {
        int x_loc = -62;
        int y_loc = 30;
        bricks = new staticBricks[116];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }
            // 1 left upper
            if (k == 0) { x_loc = 63; }   // h
            if (k == 1) { x_loc = 37; y_loc = 56; }   // v
            
            if (k == 2) { x_loc = 89; y_loc = 56; }   // h
            if (k == 3) { x_loc = 63; y_loc = 82; }   // v
            
            if (k == 4) { x_loc = 115; y_loc = 82; } // h
            if (k == 5) { x_loc = 89; y_loc = 108; } // v
            
            if (k == 6) { x_loc = 141; y_loc = 108; } // h
            if (k == 7) { x_loc = 115; y_loc = 134; } // v 
            
            if (k == 8) { x_loc = 167; y_loc = 134; } // h
            if (k == 9) { x_loc = 141; y_loc = 160; } // v 
            
            if (k == 10) { x_loc = 193; y_loc = 160; } // h
            if (k == 11) { x_loc = 167; y_loc = 186; } // v 
            // 1 left low
            if (k == 12) { x_loc = 37;  y_loc = 425; } // v
            if (k == 13) { x_loc = 63; y_loc = 491; } // h
            
            if (k == 14) { x_loc = 63; y_loc = 399; } // v
            if (k == 15) { x_loc = 89; y_loc = 465; } // h
            
            if (k == 16) { x_loc = 89;  y_loc = 373; } // v
            if (k == 17) { x_loc = 115; y_loc = 439; } // h
            
            if (k == 18) { x_loc = 115; y_loc = 347; } // v
            if (k == 19) { x_loc = 141; y_loc = 413; } // h
            
            if (k == 20) { x_loc = 141; y_loc = 321; } // v
            if (k == 21) { x_loc = 167; y_loc = 387; } // h
            
            if (k == 22) { x_loc = 167; y_loc = 295; } // v
            if (k == 23) { x_loc = 193; y_loc = 361; } // h
            // 1 right upper
            if (k == 24) { x_loc = 875; y_loc = 30; } // h
            if (k == 25) { x_loc = 941; y_loc = 56; } // v
            
            if (k == 26) { x_loc = 849; y_loc = 56; }   // h
            if (k == 27) { x_loc = 915; y_loc = 82; }   // v
            
            if (k == 28) { x_loc = 823; y_loc = 82; } // h
            if (k == 29) { x_loc = 889; y_loc = 108; } // v
            
            if (k == 30) { x_loc = 797; y_loc = 108; } // h
            if (k == 31) { x_loc = 863; y_loc = 134; } // v 
            
            if (k == 32) { x_loc = 771; y_loc = 134; } // h
            if (k == 33) { x_loc = 837; y_loc = 160; } // v 
            
            if (k == 34) { x_loc = 745; y_loc = 160; } // h
            if (k == 35) { x_loc = 811; y_loc = 186; } // v 
            // 1 right low
            if (k == 36) { x_loc = 941; y_loc = 425; } // v
            if (k == 37) { x_loc = 875; y_loc = 491; } // h
            
            if (k == 38) { x_loc = 915; y_loc = 399; } // v
            if (k == 39) { x_loc = 849; y_loc = 465; } // h
//            
            if (k == 40) { x_loc = 889;  y_loc = 373; } // v
            if (k == 41) { x_loc = 823; y_loc = 439; } // h
//            
            if (k == 42) { x_loc = 863; y_loc = 347; } // v
            if (k == 43) { x_loc = 797; y_loc = 413; } // h
//            
            if (k == 44) { x_loc = 837; y_loc = 321; } // v
            if (k == 45) { x_loc = 771; y_loc = 387; } // h
//            
            if (k == 46) { x_loc = 811; y_loc = 295; } // v
            if (k == 47) { x_loc = 745; y_loc = 361; } // h
            // 2 left upper
            if (k == 48) { x_loc = 294; y_loc = 30; }   // h
            if (k == 49) { x_loc = 268; y_loc = 56; }   // v
            
            if (k == 50) { x_loc = 320; y_loc = 56; }   // h
            if (k == 51) { x_loc = 294; y_loc = 82; }   // v
            
            if (k == 52) { x_loc = 346; y_loc = 82; } // h
            if (k == 53) { x_loc = 320; y_loc = 108; } // v
//            
            if (k == 54) { x_loc = 372; y_loc = 108; } // h
            if (k == 55) { x_loc = 346; y_loc = 134; } // v 
//            
            if (k == 56) { x_loc = 398; y_loc = 134; } // h
            if (k == 57) { x_loc = 372; y_loc = 160; } // v 
//            
            if (k == 58) { x_loc = 418; y_loc = 160; } // h
            if (k == 59) { x_loc = 398; y_loc = 186; } // v 
            // 2 left low
            if (k == 60) { x_loc = 268;  y_loc = 425; } // v  x + 231
            if (k == 61) { x_loc = 294; y_loc = 491; } // h
            
            if (k == 62) { x_loc = 294; y_loc = 399; } // v
            if (k == 63) { x_loc = 320; y_loc = 465; } // h
            
            if (k == 64) { x_loc = 320;  y_loc = 373; } // v
            if (k == 65) { x_loc = 346; y_loc = 439; } // h
            
            if (k == 66) { x_loc = 346; y_loc = 347; } // v
            if (k == 67) { x_loc = 372; y_loc = 413; } // h
            
            if (k == 68) { x_loc = 372; y_loc = 321; } // v
            if (k == 69) { x_loc = 398; y_loc = 387; } // h
            
            if (k == 70) { x_loc = 398; y_loc = 295; } // v
            if (k == 71) { x_loc = 424; y_loc = 361; } // h
            // 2 right upper
            if (k == 72) { x_loc = 644; y_loc = 30; } // h    x - 231
            if (k == 73) { x_loc = 710; y_loc = 56; } // v
            
            if (k == 74) { x_loc = 618; y_loc = 56; }   // h
            if (k == 75) { x_loc = 684; y_loc = 82; }   // v
            
            if (k == 76) { x_loc = 592; y_loc = 82; } // h
            if (k == 77) { x_loc = 658; y_loc = 108; } // v
            
            if (k == 78) { x_loc = 566; y_loc = 108; } // h
            if (k == 79) { x_loc = 632; y_loc = 134; } // v 
            
            if (k == 80) { x_loc = 540; y_loc = 134; } // h
            if (k == 81) { x_loc = 606; y_loc = 160; } // v 
            
            if (k == 82) { x_loc = 514; y_loc = 160; } // h
            if (k == 83) { x_loc = 580; y_loc = 186; } // v 
            // 2 right low
            if (k == 84) { x_loc = 710; y_loc = 425; } // v  x - 231
            if (k == 85) { x_loc = 644; y_loc = 491; } // h
            
            if (k == 86) { x_loc = 684; y_loc = 399; } // v
            if (k == 87) { x_loc = 618; y_loc = 465; } // h
            
            if (k == 88) { x_loc = 658;  y_loc = 373; } // v
            if (k == 89) { x_loc = 592; y_loc = 439; } // h
            
            if (k == 90) { x_loc = 632; y_loc = 347; } // v
            if (k == 91) { x_loc = 566; y_loc = 413; } // h
            
            if (k == 92) { x_loc = 606; y_loc = 321; } // v
            if (k == 93) { x_loc = 540; y_loc = 387; } // h
            
            if (k == 94) { x_loc = 580; y_loc = 295; } // v
            if (k == 95) { x_loc = 514; y_loc = 361; } // h
            // left additional
            if (k == 96) { x_loc = 50; y_loc = 226; } // h
            if (k == 97) { x_loc = 50; y_loc = 295; } // h
            
            if (k == 98) { x_loc = 259; y_loc = 186; } // v
            if (k == 99) { x_loc = 259; y_loc = 295; } // v
            if (k == 100){ x_loc = 193; y_loc = 246; } // h
            if (k == 101){ x_loc = 193; y_loc = 272; } // h
            // center upper additional
            if (k == 102) { x_loc = 469; y_loc = 30; } // h
            if (k == 103) { x_loc = 469; y_loc = 82; } // h
            // center center additional
            if (k == 104) { x_loc = 486; y_loc = 186; } // v
            if (k == 105) { x_loc = 486; y_loc = 295; } // v
            if (k == 106){ x_loc = 422; y_loc = 246; } // h
            if (k == 107){ x_loc = 422; y_loc = 272; } // h
            if (k == 108){ x_loc = 512; y_loc = 246; } // h
            if (k == 109){ x_loc = 512; y_loc = 272; } // h
            // right additional
            if (k == 110) { x_loc = 719; y_loc = 186; } // v
            if (k == 111) { x_loc = 719; y_loc = 295; } // v
            if (k == 112) { x_loc = 745; y_loc = 246; } // h
            if (k == 113) { x_loc = 745; y_loc = 272; } // h
            // right additional
            if (k == 114) { x_loc = 895; y_loc = 226; } // h
            if (k == 115) { x_loc = 895; y_loc = 295; } // h
            
            // 25 red
            if((k > -1 && k < 10)||(k > 11 && k < 22)||(k > 23 && k < 34) ||
               (k > 35 && k < 46)) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86

               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 25, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 50 green
            else if( k == 10 || k == 11 || k == 22 || k == 23 || k == 34 || k == 35 ||
                     k == 46 || k == 47 || k == 58 || k == 59 || k == 70 || k == 71 ||
                     k == 82 || k == 83 || k == 94 || k == 95 || k == 98 || k == 99 ||
                     k == 104 || k == 105|| k == 110 || k == 111) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86

               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 50, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 100 blue
            else if((k > 47 && k < 58)||(k > 71 && k < 82)) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(100);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86

               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 100, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 150 blue
            else if(k == 100 || k == 101 || k == 106 || k == 107 || k == 108 || k == 109 ||
                    k == 112 || k == 113) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(150);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86

               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 150, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 10 orange
            else if((k > 59 && k < 70)||(k > 83 && k < 94)) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86

               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 10, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            // 5
            else {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86

               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            
            // add bubbles to panel
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);
            // add bricks to panel
            mainBRICKS.getBasicPanel().add(bricks[k]);
            
            // vertical bricks
            if (k == 1 || k == 3 || k == 5 || k == 7 || k == 9 || k == 11 || k == 12 ||
                k == 14|| k == 16|| k == 18|| k == 20|| k == 22|| k == 25|| k == 27 ||
                k == 29|| k == 31|| k == 33|| k == 35|| k == 36|| k == 38|| k == 40 ||
                k == 42|| k == 44|| k == 46|| k == 49|| k == 51|| k == 53|| k == 55 || 
                k == 57|| k == 59|| k == 60|| k == 62|| k == 64|| k == 66|| k == 68 ||
                k == 70|| k == 73|| k == 75|| k == 77|| k == 79|| k == 81|| k == 83 || 
                k == 84|| k == 86|| k == 88|| k == 90|| k == 92|| k == 94|| k == 98 ||
                k == 99|| k == 104|| k == 105|| k == 110 || k == 111) {
                bricks[k].setBrickForm(0, 25, 65);
                bricks[k].setBounds(x_loc, y_loc, 26, 66);
            } // horizontal bricks
            else {
                bricks[k].setBrickForm(0, 65, 25);
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
        }
    }

    protected void setLEVEL_14() {
        int x_loc = -62;
        int y_loc = 30;
        bricks = new staticBricks[134];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }
            
            if (k == 0) { x_loc = 195; }  // v
            // 1 left increase
            if (k == 1) { x_loc = 143; y_loc = 66;}  // v y = +36
            if (k == 2) { x_loc = 169; y_loc = 66;}  // v
            if (k == 3) { x_loc = 221; y_loc = 66;}  // v
            if (k == 4) { x_loc = 247; y_loc = 66;}  // v
            // 2 left increase
            if (k == 5) { x_loc = 91; y_loc = 102;}  // v
            if (k == 6) { x_loc = 117; y_loc = 102;}  // v
            if (k == 7) { x_loc = 273; y_loc = 102;}  // v
            if (k == 8) { x_loc = 299; y_loc = 102;}  // v
            // 3 left increase
            if (k == 9) {  x_loc = 39; y_loc = 138;}  // v
            if (k == 10) { x_loc = 65; y_loc = 138;}  // v  y = +36
            if (k == 11) { x_loc = 325; y_loc = 138;}  // v
            if (k == 12) { x_loc = 351; y_loc = 138;}  // v
            // 4 left increase
            if (k == 13) { x_loc = 13; y_loc = 174;}  // v
            if (k == 14) { x_loc = 377; y_loc = 174;}  // v
            // 1 left decrease
            if (k == 15) { x_loc = 39; y_loc = 210;}  // v  y = +36
            if (k == 16) { x_loc = 65; y_loc = 210;}  // v
            if (k == 17) { x_loc = 325; y_loc = 210;}  // v
            if (k == 18) { x_loc = 351; y_loc = 210;}  // v
            // 2 left decrease
            if (k == 19) { x_loc = 91; y_loc = 248;}  // v  y = +36
            if (k == 20) { x_loc = 117; y_loc = 248;}  // v
            if (k == 21) { x_loc = 273; y_loc = 248;}  // v
            if (k == 22) { x_loc = 299; y_loc = 248;}  // v
            // 3 left decrease
            if (k == 23) { x_loc = 143; y_loc = 284;}  // v y = +36
            if (k == 24) { x_loc = 169; y_loc = 284;}  // v
            if (k == 25) { x_loc = 221; y_loc = 284;}  // v
            if (k == 26) { x_loc = 247; y_loc = 284;}  // v
            
            if (k == 27) { x_loc = 195; y_loc = 320;}  // v
            // 4 left increase
            if (k == 28) { x_loc = 143; y_loc = 356;}  // v y = +36
            if (k == 29) { x_loc = 169; y_loc = 356;}  // v
            if (k == 30) { x_loc = 221; y_loc = 356;}  // v
            if (k == 31) { x_loc = 247; y_loc = 356;}  // v
            // 5 left increase
            if (k == 32) { x_loc = 117; y_loc = 392;}  // v y = +36
            if (k == 33) { x_loc = 273; y_loc = 392;}  // v
            // 6 left increase
            if (k == 34) { x_loc = 91; y_loc = 428;}  // v y = +36
            if (k == 35) { x_loc = 143; y_loc = 428;}  // v
            if (k == 36) { x_loc = 247; y_loc = 428;}  // v
            if (k == 37) { x_loc = 299; y_loc = 428;}  // v
            // 7 left increase
            if (k == 38) { x_loc = 65; y_loc = 443;}  // v y = +15
            if (k == 39) { x_loc = 169; y_loc = 443;}  // v
            if (k == 40) { x_loc = 221; y_loc = 443;}  // v
            if (k == 41) { x_loc = 325; y_loc = 443;}  // v
            // 8 left increase
            if (k == 42) { x_loc = 39; y_loc = 458;}  // v y = +15
            if (k == 43) { x_loc = 195; y_loc = 458;}  // v
            if (k == 44) { x_loc = 351; y_loc = 458;}  // v
            // 9 left increase
            if (k == 45) { x_loc = 13; y_loc = 473;}  // v y = +15
            if (k == 46) { x_loc = 377; y_loc = 473;}  // v
            // inside left tree
            if (k == 47) { x_loc = 195; y_loc = 174;} // v
            if (k == 48) { x_loc = 129; y_loc = 194;} // h
            if (k == 49) { x_loc = 221; y_loc = 194;} // h
            
            // right tree
            if (k == 50) { x_loc = 780; y_loc = 30;} // v
            // 1 right increase
            if (k == 51) { x_loc = 728; y_loc = 66;}  // v y = +36
            if (k == 52) { x_loc = 754; y_loc = 66;}  // v
            if (k == 53) { x_loc = 806; y_loc = 66;}  // v
            if (k == 54) { x_loc = 832; y_loc = 66;}  // v
            // 2 right increase
            if (k == 55) { x_loc = 676; y_loc = 102;}  // v
            if (k == 56) { x_loc = 702; y_loc = 102;}  // v
            if (k == 57) { x_loc = 858; y_loc = 102;}  // v
            if (k == 58) { x_loc = 884; y_loc = 102;}  // v
            // 3 right increase
            if (k == 59) { x_loc = 624; y_loc = 138;}  // v
            if (k == 60) { x_loc = 650; y_loc = 138;}  // v  y = +36
            if (k == 61) { x_loc = 910; y_loc = 138;}  // v
            if (k == 62) { x_loc = 936; y_loc = 138;}  // v
            // 4 right increase
            if (k == 63) { x_loc = 598; y_loc = 174;}  // v
            if (k == 64) { x_loc = 962; y_loc = 174;}  // v 
             // 1 left decrease
            if (k == 65) { x_loc = 624; y_loc = 210;}  // v  y = +36
            if (k == 66) { x_loc = 650; y_loc = 210;}  // v
            if (k == 67) { x_loc = 910; y_loc = 210;}  // v
            if (k == 68) { x_loc = 936; y_loc = 210;}  // v
            // 2 left decrease
            if (k == 69) { x_loc = 676; y_loc = 248;}  // v  y = +36
            if (k == 70) { x_loc = 702; y_loc = 248;}  // v
            if (k == 71) { x_loc = 858; y_loc = 248;}  // v
            if (k == 72) { x_loc = 884; y_loc = 248;}  // v
            // 3 left decrease
            if (k == 73) { x_loc = 728; y_loc = 284;}  // v y = +36
            if (k == 74) { x_loc = 754; y_loc = 284;}  // v
            if (k == 75) { x_loc = 806; y_loc = 284;}  // v
            if (k == 76) { x_loc = 832; y_loc = 284;}  // v
            
            if (k == 77) { x_loc = 780; y_loc = 320;}  // v  !!!
            // 4 left increase
            if (k == 78) { x_loc = 728; y_loc = 356;}  // v y = +36
            if (k == 79) { x_loc = 754; y_loc = 356;}  // v
            if (k == 80) { x_loc = 806; y_loc = 356;}  // v
            if (k == 81) { x_loc = 832; y_loc = 356;}  // v
            // 5 left increase
            if (k == 82) { x_loc = 702; y_loc = 392;}  // v y = +36
            if (k == 83) { x_loc = 858; y_loc = 392;}  // v
            // 6 left increase
            if (k == 84) { x_loc = 676; y_loc = 428;}  // v y = +36
            if (k == 85) { x_loc = 728; y_loc = 428;}  // v
            if (k == 86) { x_loc = 832; y_loc = 428;}  // v
            if (k == 87) { x_loc = 884; y_loc = 428;}  // v
            // 7 left increase
            if (k == 88) { x_loc = 650; y_loc = 443;}  // v y = +15
            if (k == 89) { x_loc = 754; y_loc = 443;}  // v
            if (k == 90) { x_loc = 806; y_loc = 443;}  // v
            if (k == 91) { x_loc = 910; y_loc = 443;}  // v
            // 8 left increase
            if (k == 92) { x_loc = 624; y_loc = 458;}  // v y = +15
            if (k == 93) { x_loc = 780; y_loc = 458;}  // v
            if (k == 94) { x_loc = 936; y_loc = 458;}  // v
            // 9 left increase
            if (k == 95) { x_loc = 598; y_loc = 473;}  // v y = +15
            if (k == 96) { x_loc = 962; y_loc = 473;}  // v
            // inside left tree
            if (k == 97) { x_loc = 780; y_loc = 174;} // v
            if (k == 98) { x_loc = 714; y_loc = 194;} // h
            if (k == 99) { x_loc = 806; y_loc = 194;} // h
            // center
            if (k == 100) { x_loc = 311; y_loc = 30; }  // h
            if (k == 101) { x_loc = 624; y_loc = 30; }  // h
            if (k == 102) { x_loc = 337; y_loc = 56; }  // h
            if (k == 103) { x_loc = 598; y_loc = 56; }  // h
            if (k == 104) { x_loc = 363; y_loc = 82; }  // h
            if (k == 105) { x_loc = 572; y_loc = 82; }  // h
            if (k == 106) { x_loc = 389; y_loc = 108; }  // h
            if (k == 107) { x_loc = 546; y_loc = 108; }  // h
            if (k == 108) { x_loc = 415; y_loc = 134; }  // h
            if (k == 109) { x_loc = 520; y_loc = 134; }  // h
            
            if (k == 110) { x_loc = 425; y_loc = 160; }  // h
            if (k == 111) { x_loc = 510; y_loc = 160; }  // h
            if (k == 112) { x_loc = 425; y_loc = 186; }  // h
            if (k == 113) { x_loc = 510; y_loc = 186; }  // h
            if (k == 114) { x_loc = 425; y_loc = 212; }  // h
            if (k == 115) { x_loc = 510; y_loc = 212; }  // h
            if (k == 116) { x_loc = 425; y_loc = 238; }  // h
            if (k == 117) { x_loc = 510; y_loc = 238; }  // h
            
            if (k == 118) { x_loc = 415; y_loc = 264; }  // h
            if (k == 119) { x_loc = 520; y_loc = 264; }  // h
            if (k == 120) { x_loc = 389; y_loc = 290; }  // h
            if (k == 121) { x_loc = 546; y_loc = 290; }  // h
            if (k == 122) { x_loc = 363; y_loc = 316; }  // h
            if (k == 123) { x_loc = 572; y_loc = 316; }  // h
            if (k == 124) { x_loc = 337; y_loc = 342; }  // h
            if (k == 125) { x_loc = 598; y_loc = 342; }  // h
            if (k == 126) { x_loc = 311; y_loc = 368; }  // h
            if (k == 127) { x_loc = 624; y_loc = 368; }  // h
            
            if (k == 128) { x_loc = 337; y_loc = 394; }  // h
            if (k == 129) { x_loc = 598; y_loc = 394; }  // h
            if (k == 130) { x_loc = 363; y_loc = 420; }  // h
            if (k == 131) { x_loc = 572; y_loc = 420; }  // h
            
            if (k == 132) { x_loc = 468; y_loc = 56; }  // h
            if (k == 133) { x_loc = 468; y_loc = 82; }  // h
            
            // 25 red
            if(k == 0 || k == 13 || k == 14 || k == 27 || k == 50 || k == 63 ||
               k == 64|| k == 77) { 
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 25, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);  
            }
            // 50 green
            else if((k > 0 && k < 13)||(k > 14 && k < 23)||(k > 50 && k < 63)||
                    (k > 63 && k < 73)) {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 50, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);  
            }
            // 10 orange
            else if((k > 22 && k < 27)||(k > 27 && k < 32)||(k > 33 && k < 38) ||
                    (k > 41 && k < 45)||(k > 72 && k < 77)||(k > 76 && k < 82) ||
                    (k > 83 && k < 88)||(k > 91 && k < 95)) {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 10, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);  
            }
            // 100 blue
            else if(k == 32 || k == 33 || (k > 37 && k < 42) || k == 82 || k == 83 ||
                   (k > 87 && k < 92) || (k > 109 && k < 118)) {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(100);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 100, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);  
            }
            // 150 blue
            else if(k == 47 || k == 48 || k == 49 || k == 97 || k == 98 || k == 99 ||
                   (k > 99 && k < 110)||(k > 117 && k < 126)) {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(150);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 150, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);  
            }
            // 300 
            else if(k == 132 || k == 133) {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(300);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 300, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);  
            }
            else {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            
            // vertical bricks
            if ((k > -1 && k < 48)||(k > 49 && k < 98) ) {
                bricks[k].setBrickForm(0, 25, 65);
                bricks[k].setBounds(x_loc, y_loc, 26, 66);
            } // horizontal bricks
            else {
                bricks[k].setBrickForm(0, 65, 25);
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);
            mainBRICKS.getBasicPanel().add(bricks[k]);
            
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
        }
    }

    protected void setLEVEL_15() {
        int x_loc = -62;
        int y_loc = 30;
        bricks = new staticBricks[111];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }
            // 1 left
            if(k == 4) {x_loc = 70;y_loc = 56;} // 2 row
            if(k == 6) {x_loc = 70;y_loc = 82;} // 3 row
            if(k == 8) {x_loc = 70;y_loc = 108;}// 4 row
            if(k == 10) {x_loc = 4;y_loc = 134;}// 5 row
            // 2 left
            if(k == 14) {x_loc = 70;y_loc = 186;}// 1 row y += 52
            if(k == 18) {x_loc = 136;y_loc = 212;} // 2 row
            if(k == 20) {x_loc = 136;y_loc = 238;}// 3 row
            if(k == 22) {x_loc = 136;y_loc = 264;}// 4 row
            if(k == 24) {x_loc = 70;y_loc = 290;} // 3 row
            // 3 left
            if(k == 28) {x_loc = 4;y_loc = 342;}// 1 row y += 52
            if(k == 32) {x_loc = 70;y_loc = 368;} // 2 row
            if(k == 34) {x_loc = 70;y_loc = 394;}// 3 row
            if(k == 36) {x_loc = 70;y_loc = 420;}// 4 row
            if(k == 38) {x_loc = 4;y_loc = 446;} // 5 row
            // 1 right
            if(k == 42) {x_loc = 738;y_loc = 30;} // 1 row
            if(k == 46) {x_loc = 804;y_loc = 56;} // 2 row
            if(k == 48) {x_loc = 804;y_loc = 82;}// 3 row
            if(k == 50) {x_loc = 804;y_loc = 108;}// 4 row
            if(k == 52) {x_loc = 738;y_loc = 134;}// 5 row
            // 2 right
            if(k == 56) {x_loc = 672;y_loc = 186;}// 1 row y += 52
            if(k == 60) {x_loc = 738;y_loc = 212;} // 2 row
            if(k == 62) {x_loc = 738;y_loc = 238;}// 3 row
            if(k == 64) {x_loc = 738;y_loc = 264;}// 4 row
            if(k == 66) {x_loc = 672;y_loc = 290;} // 5 row
            // 3 right
            if(k == 70) {x_loc = 738;y_loc = 342;}// 1 row y += 52
            if(k == 74) {x_loc = 804;y_loc = 368;} // 2 row
            if(k == 76) {x_loc = 804;y_loc = 394;}// 3 row
            if(k == 78) {x_loc = 804;y_loc = 420;}// 4 row
            if(k == 80) {x_loc = 738;y_loc = 446;} // 5 row
            // center
            if(k == 84) {x_loc = 396;y_loc = 10;} //v 1 row
            if(k == 85) {x_loc = 580;y_loc = 10;} //v 1 row
            
            if(k == 86) {x_loc = 370;y_loc = 43;} //v 2 row
            if(k == 87) {x_loc = 606;y_loc = 43;} //v 2 row
            
            if(k == 88) {x_loc = 370;y_loc = 134;} // 3 row
            if(k == 92) {x_loc = 370;y_loc = 160;} // 4 row
            
            if(k == 96) {x_loc = 370;y_loc = 186;} // 5 row
            if(k == 100) {x_loc = 396;y_loc = 252;} // 6 row
            if(k == 104) {x_loc = 370;y_loc = 318;} // 6 row
            
            if(k == 108) {x_loc = 463;y_loc = 45;} // 
            if(k == 109) {x_loc = 489;y_loc = 45;} //
            if(k == 110) {x_loc = 515;y_loc = 45;} // 
            
            // 25 red
            if((k > -1 && k < 4)||(k > 9 && k < 14)|| (k > 27 && k < 32) ||
               (k > 37 && k < 46)||(k > 51 && k < 56)||(k > 69 && k < 74) ||
               (k > 79 && k < 84)||(k > 99 && k < 104)) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 25, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26); 
            }
            // 10 orange
            else if((k > 13 && k < 18)||(k > 23 && k < 28)||(k > 55 && k < 60)||
                    (k > 65 && k < 70) || (k > 91 && k < 96)) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 10, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26); 
            }
            // 100 fiolet
            else if(k == 18 || k == 20 || k == 22 || k == 61 || k == 63 || k == 65 ||
                    (k > 87 && k < 92) ) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(100);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 100, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26); 
            }
            // 150 blue
            else if(k == 84 || k == 85 || k == 86 || k == 87 ) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(150);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 150, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26); 
            }
            // 150 blue
            else if(k == 84 || k == 85 || k == 86 || k == 87 ) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(150);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 150, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26); 
            }
            // 300 blue
            else if( k == 108 || k == 109 || k == 110 ) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(300);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 300, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26); 
            }
            // 50 green
            else if( (k > 3 && k < 10) || k == 19 || k == 21 || k == 23 ||
                      (k > 31 && k < 38) || (k > 45 && k < 52) || k == 60 || k == 62 ||
                       k == 64 || (k > 73 && k < 80) ||  (k > 95 && k < 100)) {
                // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 50, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26); 
            }
            // 5 yellow
            else {
               // add bubbles for each static bricks
               bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
               bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
               // create and layout bricks
               bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
               bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            
             // vertical bricks
            if ( k == 84 || k == 85 || k == 86 || k == 87 || (k > 95 && k < 111) ) {
                bricks[k].setBrickForm(0, 25, 65);
                bricks[k].setBounds(x_loc, y_loc, 26, 66);
            } // horizontal bricks
            else {
                bricks[k].setBrickForm(0, 65, 25);
                bricks[k].setBounds(x_loc, y_loc, 66, 26);
            }
            
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);
            mainBRICKS.getBasicPanel().add(bricks[k]);
            
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());

        }
    }

    protected void setLEVEL_16() {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[6];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }

            // add bubbles for each static bricks
            bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
            bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);

            // create and layout bricks
            bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
            bricks[k].setBounds(x_loc, y_loc, 66, 26);
            mainBRICKS.getBasicPanel().add(bricks[k]);
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());

        }
    }

    protected void setLEVEL_17() {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[6];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }

            // add bubbles for each static bricks
            bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
            bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);

            // create and layout bricks
            bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
            bricks[k].setBounds(x_loc, y_loc, 66, 26);
            mainBRICKS.getBasicPanel().add(bricks[k]);
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());

        }
    }

    protected void setLEVEL_18() {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[6];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }

            // add bubbles for each static bricks
            bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
            bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);

            // create and layout bricks
            bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
            bricks[k].setBounds(x_loc, y_loc, 66, 26);
            mainBRICKS.getBasicPanel().add(bricks[k]);
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());

        }
    }

    protected void setLEVEL_19() {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[6];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }

            // add bubbles for each static bricks
            bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
            bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);

            // create and layout bricks
            bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
            bricks[k].setBounds(x_loc, y_loc, 66, 26);
            mainBRICKS.getBasicPanel().add(bricks[k]);
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());

        }
    }

    protected void setLEVEL_20() {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[6];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
        for (int k = 0; k < bricks.length; k++) {
            x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
            if (x_loc >= screenWidth) {
                x_loc = 4;
                y_loc += 45;
            }

            // add bubbles for each static bricks
            bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
            bubbles[k].setBounds(x_loc, y_loc, 112, 82); // 112, 86
            bubbles[k].setVisible(false);
            mainBRICKS.getBasicPanel().add(bubbles[k]);

            // create and layout bricks
            bricks[k] = new staticBricks(mainBRICKS, 5, bubbles[k]);  // 5,10,25,50,100,150,300,500
            bricks[k].setBounds(x_loc, y_loc, 66, 26);
            mainBRICKS.getBasicPanel().add(bricks[k]);
            // save states bricks for 'save','download','remove' game
            mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
            mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());

        }
    }

}
