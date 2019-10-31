
package GAME_BRICKS;

import GAME_BRICKS.BRICKS.*;
import GAME_BRICKS.staticBricks;

public class LEVEL_0_10 {

    BRICKS mainBRICKS;
    bubbleScore[] bubbles ;
    staticBricks[] bricks;
    int screenWidth;
    int qtyBricks;

    LEVEL_0_10(BRICKS main ) {
       mainBRICKS = main;
       screenWidth = mainBRICKS.getBasicPanel().getWidth() - 60  ;
    }

    // clear panel for the next level
     protected void clearPanelForNextLevel() {
         for( int k = 0; k < bricks.length; k++ ) {
             if(bricks[k] instanceof staticBricks )  {
                bricks[k].setLocation(1, -2000);
                mainBRICKS.getBasicPanel().remove(bricks[k]);  
             }
         }
         for( int k = 0; k < bubbles.length; k++ ) {
             if(bubbles[k] instanceof bubbleScore )  {
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
    
    
     protected void setLEVEL_0 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[50];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
//           if( k == 5) { x_loc = 245;  y_loc += 45;}
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 45; }

           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           //
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
//           System.out.println("BUBBLES: " +
//             mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType()));
          
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           // 
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
//           System.out.println("LEVEL0: " +
//             mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE()));
           
           mainBRICKS.getBasicPanel().add(bricks[k]);
        }
    }



    protected void setLEVEL_00 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 30;
        bricks = new staticBricks[135];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
         qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 30; } // y_loc += 45;

           if(  k == 45 ) { x_loc = 4;   y_loc += 40; }
           if(  k == 90 ) { x_loc = 4;   y_loc += 40;   }


           if( ((k >= 0) && ( k <= 14)) || ((k >= 30) && ( k <= 44))  ||
               ((k >= 45) && ( k <= 59)) || ((k >= 75) && ( k <= 89)) ||
               ((k >= 90) && ( k <= 104)) || ((k >= 120) && ( k <= 134))
                   )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           } else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);      
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }
    

     protected void setLEVEL_1 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[100];
        bubbles = new bubbleScore[bricks.length];
    //      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 30; }

           
           if(  k == 15 || k == 22 || k == 29 || k == 36 || k == 43 ||
                k == 50 || k == 57 || k == 64 || k == 71 || k == 78 )
              { x_loc = 40;   y_loc += 27; }
           
           if(  k >= 16 && k <= 21 ) { x_loc += 70;   } 
           if(  k >= 23 && k <= 28 ) { x_loc += 70;   }
           if(  k >= 30 && k <= 35 ) { x_loc += 70;   }
           if(  k >= 37 && k <= 42 ) { x_loc += 70;   }
           if(  k >= 44 && k <= 49 ) { x_loc += 70;   }
           if(  k >= 51 && k <= 56 ) { x_loc += 70;   }
           if(  k >= 58 && k <= 63 ) { x_loc += 70;   }
           if(  k >= 65 && k <= 70 ) { x_loc += 70;   }
           if(  k >= 72 && k <= 77 ) { x_loc += 70;   }
           if(  k >= 79 && k <= 84 ) { x_loc += 70;   }

           if(  k == 85  ) { x_loc = 4; y_loc += 50; }

           if( ((k >= 0) && ( k <= 14)) || ((k >= 29) && ( k <= 42))   ||
               ( (k >= 57) && ( k <= 70) ) || (k >= 85)   )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           } else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }

     protected void setLEVEL_2 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[114];
        bubbles = new bubbleScore[bricks.length];
//        setQtyBricks(bricks.length);
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 26; }

           if( (k >= 0 && k <= 7) || (k >= 23 && k <= 30 ) ||
               (k >= 85 && k <= 92 ) || (k >= 107 && k <= 114 ) ) {  x_loc += 54;  }

           if(  k == 8 || k == 92 ) { x_loc = 118;  }
           if(  k == 15 || k == 99 ) { x_loc = 118; y_loc += 27; }
           if( ( k >= 9 && k <= 14) || (  k >= 16 && k <= 21 ) ||
             (  k >= 93 && k <= 98 ) || (  k >= 100 && k <= 105 ) )
                 { x_loc += 54;   }

           if( k == 22 || k == 106 )  { x_loc = 58; y_loc += 27; }

           if(  k == 30  ) { x_loc = 35; y_loc += 20; }
           if(  k == 57  ) { x_loc = 35; y_loc += 5; }
           
           if( (k >= 31 && k <= 42) || (k >= 44 && k <= 56) ||
               (k >= 58 && k <= 69) || (k >= 71 && k <= 83 ) ) {  x_loc += 5;  }
           if(  k == 43 || k == 70 ) { x_loc = 4; y_loc += 32; }

           if( k == 84  )  { x_loc = 58; y_loc += 17; }


           if( ((k >= 8) && ( k <= 21)) || ((k >= 30) && ( k <= 42))   ||
               ( (k >= 57) && ( k <= 69) ) || ( (k >= 92) && ( k <= 105) ) )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           } else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }


      protected void setLEVEL_3 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[130];
        bubbles = new bubbleScore[bricks.length];
//        setQtyBricks(bricks.length);
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 68;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 28; }
           

           if( k == 0 ) { x_loc = 180; }
           if( k == 9 ) { x_loc = 150; y_loc += 28;}
           if( k == 19 ) { x_loc = 120; y_loc += 28;}
           if( k == 30 ) { x_loc = 90; y_loc += 28;}
           if( k == 42 ) { x_loc = 60; y_loc += 28;}
           if( k == 55 ) { x_loc = 60; y_loc += 38;}
           if( k == 68 ) { x_loc = 90; y_loc += 28;}
           if( k == 80 ) { x_loc = 120; y_loc += 28;}
           if( k == 91 ) { x_loc = 150; y_loc += 28;}
           if( k == 101 ) { x_loc = 180; y_loc += 28;}

           if( k == 110 && k <= 113 ) { x_loc = 20; y_loc += 48;}
           if( k == 114 && k <= 117 ) { x_loc = 710; }
           if( k == 118 && k <= 120 ) { x_loc = 50; }
           if( k == 121 && k <= 123 ) { x_loc = 740; }
           if( k == 124 && k <= 125 ) { x_loc = 80;y_loc += 28; }
           if( k == 126 && k <= 127 ) { x_loc = 770; }
           if( k == 128 ) { x_loc = 110; y_loc += 28;}
           if( k == 129 ) { x_loc = 800; }

        if(   k == 0 || k == 2 || k == 4 ||  k == 6 || k == 8 ||
              k == 9 || k == 11 || k == 13 || k == 14 || k == 16 || k == 18 ||
              k == 19 || k == 21 || k == 23 || k == 25 || k == 27 || k == 29 ||
              k == 30 || k == 32 || k == 34 || k == 37 || k == 39 || k == 41 ||
              k == 42 || k == 44 || k == 46 || k == 50 || k == 52 || k == 54 ||

              k == 55 || k == 57 || k == 59 || k == 63 || k == 65 || k == 67 ||
              k == 68 || k == 70 || k == 72 || k == 75 || k == 77 || k == 79 ||
              k == 80 || k == 82 || k == 84 || k == 86 || k == 88 || k == 90 ||
              k == 91 || k == 93 || k == 95 || k == 96 || k == 98 || k == 100 ||
              k == 101 || k == 103 || k == 105 ||  k == 107 || k == 109 ||

              k == 110 || k == 113 || k == 114 ||  k == 117 ||
              k == 118 || k == 120 || k == 121 ||  k == 123 ||
              k == 124 || k == 125 || k == 126 ||  k == 127 ||
              k == 128 || k == 129
              )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           } 
        else if( k == 48 || k == 61 )
           {
            bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
        else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }

       protected void setLEVEL_4 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[130];
        bubbles = new bubbleScore[bricks.length];
//        setQtyBricks(bricks.length);
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 27; }

           if( k == 15  ) { x_loc = 34; }   // first blok
           if( (k >= 16 && k <= 21) || (k >= 23 && k <= 28)  ) { x_loc += 66; }
           if( k == 22 ) { x_loc = 34; y_loc += 27; }
           if( k == 29 ) { x_loc = 4; y_loc += 27; }
           
           if( k == 44 && k <= 49  ) { x_loc = 4; y_loc += 27; } // line: 1
           if( k == 50  ) { x_loc = 466; }
           if( k == 51 && k <= 56  ) { x_loc = 598; }

           if( k == 57  ) { x_loc = 4;  }             // line: 2
           if( k == 58 && k <= 60  ) { x_loc = 400; }
           if( k == 61  ) { x_loc = 928; }
           
           if( k == 62 && k <= 67 ) { x_loc = 4; }  // line: 3
           if( k == 68  ) { x_loc = 466; }
           if( k == 69 && k <= 74 ) { x_loc = 598; }

           if( k == 75  ) { x_loc = 4;  }             // line: 4
           if( k == 76 && k <= 78  ) { x_loc = 400; }
           if( k == 79  ) { x_loc = 928; }

           if( k == 80 && k <= 85  ) { x_loc = 4;  } // line: 5
           if( k == 86  ) { x_loc = 466; }
           if( k == 87 && k <= 92  ) { x_loc = 598; }

           if( k == 93 && k <= 107  ) { x_loc = 4; y_loc += 27;  }   // second blok
           if( k == 108  ) {  x_loc = 34; }
           if( (k >= 109 && k <= 114)   ) { x_loc += 66; }
           if( k == 115  ) {  x_loc = 4; y_loc += 27; }
           
            if( ( k >= 15 &&  k <= 28) ||
                 k == 44 || k == 46 || k ==  47 || k == 50 || k == 53 || k == 54 || k == 56 ||
                 k == 57 || k == 58 || k == 60  || k == 61 ||
                 k == 62 || k == 64 || k == 65 || k == 71 || k == 72 || k == 74 ||
                 k == 75 || k == 76 || k == 78 || k == 79 ||
                 k == 80 || k == 82 || k == 83 || k == 86 || k == 89 || k == 90 || k == 92 ||
                 (k >= 108 && k <= 114)   )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           } 
           else if( k == 59 || k == 68 || k == 77 )
           {
           bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
           else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
      
        }
    }


        protected void setLEVEL_5 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[140];
        bubbles = new bubbleScore[bricks.length];
//        setQtyBricks(bricks.length);
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 27; }

           if( k == 15  ) { x_loc = 37; } //  line : 2
           if( k == 29  ) { x_loc = 70; } //  line : 3
           if( k == 42  ) { x_loc = 103; y_loc += 27; } //  line : 4
           if( k == 54  ) { x_loc = 204; y_loc += 27; } //  line : 5
           
           if( k == 63 ) { x_loc = 4; y_loc += 27; } //  line : 6
           if( k == 64 ) { x_loc = 301;  }
           if( k == 70 ) { x_loc = 928;  }

           if( k == 73 ) { x_loc = 400; } //  line : 7
           if( k == 76 ) { x_loc = 400; }
           if( k == 77 ) { x_loc = 862; }

           if( k == 82 ) { x_loc = 466; } //  line : 8
           if( k == 83 ) { x_loc = 796; }

           if( k == 90 ) { x_loc = 400; } // line : 9
           if( k == 93 ) { x_loc = 730; }

           if( k == 100 ) { x_loc = 367; } // line : 10
           if( k == 104 ) { x_loc = 796; }

           if( k == 109 ) { x_loc = 334; } // line : 11
           if( k == 114 ) { x_loc = 862; }

           if( k == 117 ) { x_loc = 300; } // line : 12
           if( k == 123 ) { x_loc = 928; }

           if( k == 124 ) { x_loc = 267; } // line : 13
           if( k == 125 ) { x_loc = 663; }

           if( k == 126 ) { x_loc = 234; y_loc += 27; } // line : 14
           if( k == 127 ) { x_loc = 696; }

           if( k == 128 ) { x_loc = 201; y_loc += 27; } // line : 15
           if( k == 129 ) { x_loc = 729; }

           if( k == 130 ) { x_loc = 4; y_loc += 27; } // line : 16
           if( k == 131 ) { x_loc = 168; }
           if( k == 132 ) { x_loc = 763; }
           if( k == 133 ) { x_loc = 928; }

           if( k == 134 ) { x_loc = 52;  } // line : 17
           if( k == 136 ) { x_loc = 815; }

           if( k == 138 ) { x_loc = 85; y_loc += 27; } // line : 18
           if( k == 139 ) { x_loc = 850; }

            if( k == 1  || k == 5  || k == 9  || k == 13  ||
                k == 16 || k == 19 || k == 24 || k == 27  ||
                k == 30 || k == 32 || k == 38 || k == 40  ||
                k == 43 || k == 44 || k == 51 || k == 52  ||
                k == 54 || k == 62  ||

                k == 71 || k == 74 || k == 76 || k == 78 ||
                k == 79 || k == 81 || k == 82 || k == 83 || k == 85 ||
                k == 86 || k == 88 || k == 91 || k == 94 || k == 96 ||
                k == 97 || k == 99 ||  k == 101 || k == 102 || k == 104 || k == 106 ||
                k == 107  || ( k >= 110 && k <= 112 ) || k == 115 )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           }
           else if( k == 7 ||
                    k == 21 || k == 22 ||
                    k == 34 || k == 36 ||
                    k == 46 || k == 49 ||
                    k == 56 || k == 58 || k == 60 ||
                    k == 64 || k == 66 || k == 67 || k == 69 ||
                    k == 72 || k == 73 || k == 75 || k == 77 ||
                    k == 80 || k == 84 ||
                    k == 87 || k == 89 || k == 90 || k == 92 || k == 93 || k == 95 ||
                    k == 98 || k == 100|| k == 103 || k == 105 ||
                    k == 108|| k == 109|| k == 113 || k == 114 ||
                    k == 117|| k == 122||
                    k == 124|| k == 125||
                    k == 126|| k == 127||
                    k == 128|| k == 129
                    )
           {
           bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
           else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }


         protected void setLEVEL_6 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[116];
        bubbles = new bubbleScore[bricks.length];
//      pass length of bricks to field: 'qtyBricks'
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 27; }

           if( k == 0 ) x_loc = 70;  // line : 1
           if( k == 5 ) x_loc = 598;

           if( k == 10 ) { x_loc = 37; y_loc += 27; } // line : 2
           if( k == 16 ) x_loc = 565;

           if( k == 22 ) { x_loc = 4;  } // line : 3
           if( k == 23 ) { x_loc = 928;  }

           if( k == 25 ) { x_loc = 466;  } // line : 4
           if( k == 26 ) { x_loc = 928;  }

           if( k == 27 ) { x_loc = 37;  } // line : 4
           if( k == 32 ) { x_loc = 433;  }
           if( k == 34 ) { x_loc = 631;  }

           if( k == 39 )  {x_loc = 70;  } // line : 5
           if( k == 44 ) { x_loc = 466;  }
           if( k == 45 ) x_loc = 598;

           if( k == 50 )  {x_loc = 70;  } // line : 6
           if( k == 55 )  {x_loc = 466;  }
           if( k == 56 )  {x_loc = 598;  }

           if( k == 57 )  {x_loc = 301; y_loc += 27; } // line : 7
           if( k == 58 )  {x_loc = 466;  }
           if( k == 59 )  {x_loc = 631;  }

           if( k == 60 )  {x_loc = 301; y_loc += 27; } // line : 8
           if( k == 61 )  {x_loc = 466;  }
           if( k == 62 )  {x_loc = 631;  }

           if( k == 63 )  {x_loc = 70; y_loc += 27; } // line : 9
           if( k == 67 )  {x_loc = 400;  }
           if( k == 70 )  {x_loc = 664;  }

           if( k == 74 )  {x_loc = 37; y_loc += 27; } // line : 10
           if( k == 78 )  {x_loc = 433;  }
           if( k == 80 )  {x_loc = 697;  }

           if( k == 84 )  {x_loc = 4;  } // line : 11
           if( k == 85 )  {x_loc = 433;  }
           if( k == 87 )  {x_loc = 928;  }

           if( k == 88 )  {x_loc = 4;  } // line : 12
           if( k == 89 )  {x_loc = 400;  }
           if( k == 92 )  {x_loc = 928;  }

           if( k == 93 )  {x_loc = 37;  } // line : 13
           if( k == 97 )  {x_loc = 400;  }
           if( k == 100 )  {x_loc = 697;  }

           if( k == 104 )  {x_loc = 70;  } // line : 14
           if( k == 107 )  {x_loc = 730;  }

           if( k == 110 )  {x_loc = 103; y_loc += 27; } // line : 15
           if( k == 112 )  {x_loc = 763;  }

           if( k == 114 )  {x_loc = 136; y_loc += 27; } // line : 16
           if( k == 115 )  {x_loc = 799;  }

         if( k <= 10 || k == 22 || ( k >= 21 && k <= 24 ) || k == 26 || k == 27 ||
             ( k >= 38 && k <= 42 ) || (k >= 46 && k <= 57) || k == 59 || k == 60 ||
              k == 62 || ( k >= 63 && k <= 66 ) || ( k >= 70 && k <= 74 ) ||
              k == 83 || k == 84 || k == 87 || k == 88 || k == 92 || k == 93 ||
              k == 103 || k ==104 || k == 109 || k == 110 ||  k == 113 ||
              k == 114 || k == 115

                   )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           }
           else if( (k >= 11 && k <= 20) || k == 25 ||
                    (k >= 28 && k <= 31) || (k >= 34 && k <= 37) ||
                    (k >= 43 && k <= 45)|| k == 58 || k == 61 ||
                    ( k >= 75 && k <= 82 ) || k == 85 || k == 86 ||
                    ( k >= 94 && k <= 96 ) || ( k >= 100 && k <= 102 )
                    )
           {
           bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
           else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }

          protected void setLEVEL_7 (  ) // OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[111];
        bubbles = new bubbleScore[bricks.length];

        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 27; }

           if( k == 0 ) x_loc = 4;  // line : 1
           if( k == 16 ) x_loc = 928; // line: 2
           
           if( k == 18 ) x_loc = 136; // line: 3
           if( k == 29 ) x_loc = 928;

           if( k == 31 ) x_loc = 136; // line: 4
           if( k == 32 ) x_loc = 796;
           if( k == 33 ) x_loc = 928;

           if( k == 35 ) x_loc = 136; // line: 5
           if( k == 36 ) x_loc = 268;
           if( k == 43 ) x_loc = 796;
           if( k == 44 ) x_loc = 928;

           if( k == 46 ) x_loc = 136; // line: 6
           if( k == 47 ) x_loc = 268;
           if( k == 48 ) x_loc = 664;
           if( k == 49 ) x_loc = 796;
           if( k == 50 ) x_loc = 928;

           if( k == 52 ) x_loc = 136; // line: 7
           if( k == 53 ) x_loc = 268;
           if( k == 54 ) x_loc = 400;
           if( k == 57 ) x_loc = 664;
           if( k == 58 ) x_loc = 796;
           if( k == 59 ) x_loc = 928;

           if( k == 61 ) x_loc = 136; // line: 8
           if( k == 62 ) x_loc = 268;
           if( k == 63 ) x_loc = 664;
           if( k == 64 ) x_loc = 796;
           if( k == 65 ) x_loc = 928;

           if( k == 67 ) x_loc = 136; // line: 9
           if( k == 68 ) x_loc = 268;
           if( k == 75 ) x_loc = 796;
           if( k == 76 ) x_loc = 928;

           if( k == 78 ) x_loc = 136; // line: 10
           if( k == 79 ) x_loc = 796;
           if( k == 80 ) x_loc = 928;

           if( k == 82 ) x_loc = 136; // line: 11
           if( k == 93 ) x_loc = 928;

           if( k == 95 ) x_loc = 928; // line: 12

        if( (k >= 18 && k <= 28 ) || k == 31 || k == 32 || k == 35 || k == 43 ||
             k == 46 || k == 49 || k == 52 || k == 58 || k == 61 || k == 64 ||
             k == 67 || k == 75 || k == 78 || k == 79 || (k >= 82 && k <= 92 )
                   )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           }
           else if(  (k >= 36 && k <= 42 ) || k == 47 || k == 48 || k == 53 || k == 57
                     || k == 62 || k == 63 || (k >= 68 && k <= 74 )
                    )
           {
           bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
           else if( (k >= 54 && k <= 56 ) || k == 0 || k == 14 || k == 96 ||
                   k == 110   )
           {
           bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 50 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
           else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }

           protected void setLEVEL_8 (  ) //OK
    {
        int x_loc = -62;
        int y_loc = 15;
        bricks = new staticBricks[117];
        bubbles = new bubbleScore[bricks.length];
//        
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 27; }

           if( k == 0 ) { x_loc = 136; y_loc = 15; } // line : 1
           if( k == 1 )   x_loc = 400;
           if( k == 2 )   x_loc = 532;
           if( k == 3 )   x_loc = 796;
           
           if( k == 4 ) {  x_loc = 70; y_loc += 27; }// line : 2
           if( k == 7 )    x_loc = 334;
           if( k == 12 )   x_loc = 730;

           if( k == 15 ) { x_loc = 136; y_loc += 27; } // line : 3
           if( k == 16 )   x_loc = 400;
           if( k == 17 )   x_loc = 532;
           if( k == 18 )   x_loc = 796;

           if( k == 19 ) { x_loc = 70; y_loc += 27; } // line : 4
           if( k == 22 )   x_loc = 334;
           if( k == 27 )   x_loc = 730;

           if( k == 30 ) { x_loc = 136; y_loc += 27; } // line : 5
           if( k == 31 )   x_loc = 400;
           if( k == 32 )   x_loc = 532;
           if( k == 33 )   x_loc = 796;

           if( k == 34 ) { x_loc = 70; y_loc += 27; }// line : 6
           if( k == 37 )   x_loc = 334;
           if( k == 42 )   x_loc = 730;

           if( k == 45 ) { x_loc = 136; y_loc += 27; } // line : 7
           if( k == 46 )   x_loc = 400;
           if( k == 47 )   x_loc = 532;
           if( k == 48 )   x_loc = 796;

           if( k == 49 ) {  x_loc = 70; y_loc += 27; }// line : 8
           if( k == 52 )    x_loc = 334;
           if( k == 57 )    x_loc = 730;

           if( k == 60 ) { x_loc = 136; y_loc += 27; } // line : 9
           if( k == 61 )   x_loc = 400;
           if( k == 62 )   x_loc = 532;
           if( k == 63 )   x_loc = 796;

           if( k == 64 ) {  x_loc = 70; y_loc += 27; }// line : 10
           if( k == 67 )    x_loc = 334;
           if( k == 72 )    x_loc = 730;

           if( k == 75 ) { x_loc = 136; y_loc += 27; } // line : 11
           if( k == 76 )   x_loc = 400;
           if( k == 77 )   x_loc = 532;
           if( k == 78 )   x_loc = 796;

           if( k == 79 ) {  x_loc = 70; y_loc += 27; }// line : 12
           if( k == 82 )    x_loc = 334;
           if( k == 87 )    x_loc = 730;

           if( k == 90 ) { x_loc = 136; y_loc += 27; } // line : 13
           if( k == 91 )   x_loc = 334;
           if( k == 96 )   x_loc = 796;

           if( k == 97 ) {  x_loc = 70; y_loc += 27; }// line : 14
           if( k == 100 )   x_loc = 730;

           if( k == 103 ) { x_loc = 136; y_loc += 27; } // line : 15
           if( k == 104 )   x_loc = 796;

           if( k == 105 ) {  x_loc = 70; y_loc += 27; }// line : 16
           if( k == 108 )   x_loc = 730;

           if( k == 111 ) {  x_loc = 70; y_loc += 27; }// line : 17
           if( k == 114 )   x_loc = 730;

        if( (k >= 105 && k <= 116 ) || (k >= 82 && k <= 86 ) ||
            (k >= 91 && k <= 95 )   )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
           }
           else if(  k == 5 || k == 8 || k == 10 || k == 13 ||
                     k == 20|| k == 23|| k == 25 || k == 28 ||
                     k == 35|| k == 38|| k == 40 || k == 43 ||
                     k == 50|| k == 53|| k == 55 || k == 58 ||
                     k == 65|| k == 68|| k == 70 || k == 73 ||
                     k == 80|| k == 88||
                     k == 98|| k == 101
                    )
           {
           bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
           else if(  k == 4 || k == 6 || k == 7 || k == 9 || k == 11 || k == 12 || k == 14 ||
                     k == 19|| k == 21|| k == 22|| k == 24|| k == 26 || k == 27 || k == 29 ||
                     k == 34|| k == 36|| k == 37|| k == 39|| k == 41 || k == 42 || k == 44 ||
                     k == 49|| k == 51|| k == 52|| k == 54|| k == 56 || k == 57 || k == 59 ||
                     k == 64|| k == 66|| k == 67|| k == 69|| k == 71 || k == 72 || k == 74 ||
                     k == 79|| k == 81|| k == 87|| k == 89||
                     k == 97|| k == 99|| k == 100|| k == 102
                   )
           {
           bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 50 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
            }
           else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(x_loc, y_loc, 112, 82, bubbles[k].getScoreType());
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(x_loc, y_loc, 66, 26, bricks[k].getBricksTYPE());
             }
        }
    }

         protected void setLEVEL_9 (  ) // OK
    {
        int xx_loc = -62;
        int yy_loc = 10;
        int bubblesScoreType = 0;
        int bricksType = 0;
             
        int x_loc = -62;
        int y_loc = 10;
        bricks = new staticBricks[111];
        bubbles = new bubbleScore[bricks.length];
//        
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 27; }

           if( k == 1 )   x_loc = 202; // line 1
           if( k >= 2 )   x_loc += 15;
           if( k == 8 )   x_loc = 968; //v

           if( k == 9 )    x_loc += 15; // line 2  //v
           if( k == 10 ) { x_loc  = 65; y_loc += 27; } //v
           if( k == 11 ) { x_loc  = 169; y_loc -= 22; } // line 2

           if( k == 20 )    x_loc = 905; // line 3  v
           if( k == 19 )    x_loc = 937; //v

           if( k == 21 )   { x_loc = 4;  y_loc = 81; } //v
           if( k == 22 )   { x_loc = 35;  y_loc += 27; } //v
           if( k == 23 )   { x_loc = 66;  y_loc += 27; } //v
           
           if( k == 24 )   { x_loc = 136;  y_loc -= 60; } // line 3 

           if( k == 33 )   {  x_loc = 905; y_loc += 66; } // v
           if( k == 34 )   { x_loc = 937; y_loc -= 55; }//v
           if( k == 35 )   { x_loc = 968; y_loc -= 60; }//v

           if( k == 36 )   { x_loc = 4;  y_loc += 45; } //v
           if( k == 37 )   { x_loc = 35;  y_loc += 27; } //v
           if( k == 38 )   { x_loc = 66;  y_loc += 27; } //v

           if( k == 39 )   { x_loc = 169;  y_loc -= 70; } // line 4  v
           if( k == 40 )   { x_loc = 210;  }
           if( k == 47 )   { x_loc = 780;  }  // v

           if( k == 48 )   { x_loc = 4;  y_loc += 90; } //v
           if( k == 49 )   { x_loc = 35;  y_loc += 27; } //v
           if( k == 50 )   { x_loc = 66;  y_loc += 27; } //v
           if( k == 51 || k == 54 || k == 57  )   { x_loc = 4;  y_loc += 20; } //v
           if( k == 52 || k == 55 || k == 58 )   { x_loc = 35;  y_loc += 27; } //v
           if( k == 53 || k == 56  )   { x_loc = 66;  y_loc += 27; } //v

           if( k == 59  )   {  x_loc = 905; y_loc -= 260; } // v
           if( k == 60 )   { x_loc  = 937; y_loc -= 55; }//v
           if( k == 61 )   { x_loc  = 968; y_loc -= 60; }//v

           if( k == 62 || k == 65 || k == 68   )   {  x_loc = 905; y_loc += 108; } // v
           if( k == 63 || k == 66 || k == 69  )   { x_loc  = 937; y_loc -= 55; }//v
           if( k == 64 || k == 67 || k == 70 )   { x_loc  = 968; y_loc -= 60; }//v
         
           if( k == 71 )   { x_loc  = 937; y_loc += 80; }//v
           if( k == 72 )   { x_loc  = 968; y_loc -= 60; }//v

           if( k == 73 )   { x_loc = 169;  y_loc -= 265; } // line 5

           if( k == 81 )   { x_loc  = 136; y_loc += 60; } // line 6
           if( k == 90 )   { x_loc  = 169; y_loc += 32; } // line 7
           if( k == 98 )   { x_loc  = 202; y_loc += 32; } // line 8

           if( k == 105 )   { x_loc = 169;  y_loc += 37; }
           if( k >= 106 )   { x_loc -= 40;  }

           if( k == 108 )   { x_loc = 699;   }
           
           
           if( (k >= 0 && k <= 8)  || k == 21 || k == 35 || k == 36  || k == 48 ||
                k == 51 || k == 54|| k == 57 || k == 61 || k == 64  || k == 67 ||
                k == 70 || k == 72|| (k >= 98 && k <= 104)
                  )
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
            }
           else if(  k == 10 || k == 20 || k == 23 || (k >= 24 && k <= 33) ||
                     k == 38 || k == 50 || k == 53 || k == 56 || k == 59   ||
                     k == 62 || k == 65 || k == 68 || (k >= 81 && k <= 89)
                     )     // red
           {
           bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
            }
           else if(  k == 9 || (k >= 11 && k <= 18) || k == 19 || k == 22 ||
                     k == 34|| k == 37 || k == 40 || k == 42   ||
                     k == 44|| k == 46 || k == 49 || k == 52   || k == 55 ||
                     k == 58|| k == 60 || k == 63 || k == 66   || k == 69 ||
                     k == 71|| k == 73 || k == 75 || k == 77   || k == 79 ||
                     (k >= 90 && k <= 97) 
                     )    // green
           {
           bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 50 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
            }
           else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
             }

           // vertical bricks
            if( k == 0 || k == 8 || k == 9 || k == 10 || k == 19 || k == 20 ||
                ( k >= 21 && k <= 23 ) || ( k >= 33 && k <= 35 ) ||
                ( k >= 36 && k <= 38 || k == 39 || k ==47 )     ||
                ( k >= 47 && k <= 72 ) || ( k >= 105 && k <= 111 )   )
            {
               bricks[k].setBrickForm(0, 25, 65);
               bricks[k].setBounds( x_loc, y_loc, 26, 66);
            } // horizontal bricks
           else  {
               bricks[k].setBrickForm(0, 65, 25);
               bricks[k].setBounds( x_loc, y_loc, 66, 26);
           }
            
           xx_loc = x_loc;
           yy_loc = y_loc;
            // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(xx_loc, yy_loc, 112, 82,    bubblesScoreType );
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(xx_loc, yy_loc, 66, 26, bricksType);
        }
    }

            protected void setLEVEL_10 (  ) // 10
    {
        int xx_loc = -62;
        int yy_loc = 10;
        int bubblesScoreType = 0;
        int bricksType = 0;
                
        int x_loc = -62;
        int y_loc = 10;
        bricks = new staticBricks[99];
        bubbles = new bubbleScore[bricks.length];
//        
        qtyBricks = bricks.length;
         for( int k = 0; k < bricks.length; k++ ) {
           x_loc += 66;
           if( x_loc >= screenWidth ) { x_loc = 4;  y_loc += 27; }

           if( k == 2 ) { x_loc = 334; } // line 1
           if( k == 4 ) { x_loc = 664; }

           if( k == 6 ) { x_loc = 4; y_loc += 27; } // line 2  v
           if( k >= 7 && k <= 10) { x_loc -= 39;  }
           if( k == 11 ) { x_loc = 334; }
           if( k >= 12 && k <= 15) { x_loc -= 39; }
           if( k == 16 ) { x_loc = 664; }
           if( k >= 17 && k <= 20) { x_loc -= 39; }

           if( k == 21 ) { x_loc = 4; y_loc += 67; }  // line 3
           if( k == 23 ) { x_loc = 334; }
           if( k == 25 ) { x_loc = 664; }

           if( k == 27 ) { x_loc = 169; y_loc += 27; }  // line 4
           if( k == 29 ) { x_loc = 499; }
           if( k == 31 ) { x_loc = 829; }

           if( k == 33 ) { x_loc = 169;  } // line 5  v
           if( k >= 34 && k <= 37) { x_loc -= 39;  }
           if( k == 38 ) { x_loc = 499;  }
           if( k >= 39 && k <= 42) { x_loc -= 39;  }
           if( k == 43 ) { x_loc = 829;  }
           if( k >= 44 && k <= 46) { x_loc -= 39;  }
           if( k == 47 ) { x_loc = 937; y_loc -= 27; }

           if( k == 48 ) { x_loc = 169; y_loc += 40; }  // line 6
           if( k == 50 ) { x_loc = 499; }
           if( k == 52 ) { x_loc = 829; }
           
           if( k == 54 ) { x_loc = 4;  }  // line 7
           if( k == 56 ) { x_loc = 334; }
           if( k == 58 ) { x_loc = 664; }

           if( k == 60 ) { x_loc = 4; y_loc += 27; } // line 8  v
           if( k >= 61 && k <= 64) { x_loc -= 39;  }
           if( k == 65 ) { x_loc = 334; }
           if( k >= 66 && k <= 69) { x_loc -= 39; }
           if( k == 70 ) { x_loc = 664; }
           if( k >= 71 && k <= 74) { x_loc -= 39; }

           if( k == 75 ) { x_loc = 4;  y_loc += 67;}  // line 9
           if( k == 77 ) { x_loc = 334; }
           if( k == 79 ) { x_loc = 664; }

           if( k == 81 ) { x_loc = 169; y_loc += 27; }  // line 10
           if( k == 83 ) { x_loc = 829; }

           if( k == 85 ) { x_loc = 169;  } // line 11  v
           if( k >= 86 && k <= 89) { x_loc -= 39;  }
           if( k == 90 ) { x_loc = 829;  }
           if( k >= 91 && k <= 93) { x_loc -= 39;  }
           if( k == 94 ) { x_loc = 937; y_loc -= 27; }

           if( k == 95 ) { x_loc = 169; y_loc += 40; }  // line 4
           if( k == 97 ) { x_loc = 829; }
         

           if( k == 0 || k == 1 || ( k >= 4 && k <= 6) ||
               k == 10|| k == 16 || ( k >= 20 && k <= 22) ||
               k == 25|| k == 26 || ( k >= 34 && k <= 36 )||
               ( k >= 44 && k <= 46 ) || k == 56|| k == 57||
               k == 77|| k == 78 || k == 85|| k == 87|| k == 89
               )
               
           {
           bubbles[k] = new bubbleScore(10);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 10 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
           }
           else if(   ( k >= 7 && k <= 9)|| ( k >= 17 && k <= 19) || 
                        k == 27|| k == 28 || ( k >= 31 && k <= 33 ) ||
                        k == 37|| k == 43 || ( k >= 47 && k <= 49) ||
                        k == 52|| k == 53 || ( k >= 60 && k <= 64) ||
                        ( k >= 70 && k <= 74) || k == 81|| k == 82 ||
                        k == 90|| k == 92 || k == 94 || k == 95    ||
                        k == 96
                        )      // red
           {
           bubbles[k] = new bubbleScore(25);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 25 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
            }
           else if(  ( k >= 12 && k <= 14 ) ||
                     k == 29|| k == 30 || k == 38 || k == 42 ||
                     k == 50|| k == 51 || ( k >= 65 && k <= 69) ||
                     k == 83|| k == 84 || k == 97 || k == 98
                     )    // green
           {
           bubbles[k] = new bubbleScore(50);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 50 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
            }
           else
           {
           // add bubbles for each static bricks
           bubbles[k] = new bubbleScore(5);  // 5,10,25,50,100,150,300,500
           bubbles[k].setBounds( x_loc, y_loc, 112, 82 ); // 112, 86
           bubbles[k].setVisible(false);
           mainBRICKS.getBasicPanel().add(bubbles[k]);

           // create and layout bricks
           bricks[k] = new staticBricks(mainBRICKS, 5 , bubbles[k]);  // 5,10,25,50,100,150,300,500
//           bricks[k].setBounds( x_loc, y_loc, 66, 26);
           mainBRICKS.getBasicPanel().add(bricks[k]);
           
           bubblesScoreType = bubbles[k].getScoreType();
           bricksType       = bricks[k].getBricksTYPE();
             }

           // vertical bricks
            if( ( k >= 6 && k <= 20) || ( k >= 33 && k <= 47) ||
                ( k >= 60 && k <=74) || ( k >= 85 && k <= 94)  )  // (k >= 0 && k <= 5 )
            {
               bricks[k].setBrickForm(0, 25, 65);
               bricks[k].setBounds( x_loc, y_loc, 26, 66);
            } // horizontal bricks
           else  {
               bricks[k].setBrickForm(0, 65, 25);
               bricks[k].setBounds( x_loc, y_loc, 66, 26);
           }
           
           xx_loc = x_loc;
           yy_loc = y_loc;
            // save states bricks for 'save','download','remove' game
           mainBRICKS.statesOfPosition.save_stateBubblesPosition(xx_loc, yy_loc, 112, 82,    bubblesScoreType );
           mainBRICKS.statesOfPosition.save_stateStaticBrickPosition(xx_loc, yy_loc, 66, 26, bricksType); 
        }
    }
}
