
package GAME_BRICKS;

public class LEVEL_21_30 {

     BRICKS mainBRICKS;
    bubbleScore[] bubbles ;
    staticBricks[] bricks;
    int screenWidth;
    int qtyBricks;

    LEVEL_21_30(BRICKS main ) {
       mainBRICKS = main;
       screenWidth = mainBRICKS.getBasicPanel().getWidth() - 60  ;
    }

    // clear panel for the next level
    protected void clearPanelForNextLevel() {
         for( int k = 0; k < bricks.length; k++ ) {
             if(bricks[k] instanceof staticBricks )  {
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

    protected void setLEVEL_21 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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

     protected void setLEVEL_22 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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


      protected void setLEVEL_23 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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

       protected void setLEVEL_24 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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


    protected void setLEVEL_25 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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


    protected void setLEVEL_26 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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


    protected void setLEVEL_27 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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


    protected void setLEVEL_28 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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


         protected void setLEVEL_29 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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

          protected void setLEVEL_30 (  )
    {
        int x_loc = -62;
        int y_loc = 100;
        bricks = new staticBricks[7];
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
