package GAME_BRICKS;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


//2 g1 5 01:48:03 PM 15/4/2013
//3 g2 4 01:39:47 PM 15/4/2013
//4 g3 3 01:31:34 PM 15/4/2013
//5 g4 2 01:30:04 PM 15/4/2013
//6 g5 2 01:30:04 PM 15/4/2013
//7 g1 5 01:48:03 PM 15/4/2013
//8 g2 4 01:39:47 PM 15/4/2013
//9 g3 3 01:31:34 PM 15/4/2013
public class updateTable {

    static inputGamerName inputgamername;
    String mem = "";
    String nameGamer = "";
   
    boolean added = true;
    Vector<String> v;

     String strt = ""; // time
     String strd = ""; // date
     String mem1 = null; // for saving 'number' in vector
     int num2 = 0;  // second position
    // if line is not right format ,reset line and turn off condition  for the next start class
     boolean addRightFormat = false;
     int pos = -1; // position word in line
     tableOfRecords records;
     BRICKS mainBRICKS;

    updateTable( BRICKS main  ) { // BRICKS main
//         inputgamername = inputuserName;
         mainBRICKS = main;
//         records  = record;
        // get content of textField from CLASS:: inputGamerName
//         gamer = inputgamername.tfield.getText();
        

//        String gameScore = "3000";
        int scoreGame = mainBRICKS.cntScore; //
//        int scoreGame = 4000;
                      // D:\-== BACKUP_netbeans_projects ==-\NetBeansProjects\SWING_1\src\GAME_BRICKS\Records.txt
                      // /-==D==-/
        String path = "/-== BACKUP_netbeans_projects ==-/NetBeansProjects/SWING_1/src/GAME_BRICKS/Records.txt"; //
        File fi = new File(path);
        InputStream in;
        try {
            in = new FileInputStream(fi);

            StreamTokenizer sign = new StreamTokenizer(in);


//         PrintWriter writer = new PrintWriter(new FileWriter(fi));

            java.util.Calendar showDate = new java.util.GregorianCalendar();
            // add 1 to month because Calendar's months start at 0, not 1
            int month = showDate.get(showDate.MONTH) + 1;
            int dayOfMonth = showDate.get(showDate.DAY_OF_MONTH);
            int year = showDate.get(showDate.YEAR);
            String currDate = dayOfMonth + "/" + month + "/" + year;

            Calendar cal = Calendar.getInstance();
            Formatter ft = new Formatter();
            // Отобразить стандартный 12-часовой формат
            ft.format("%tr", cal);
            String t = "" + ft;
           
            
            v = new Vector();
            
            int wr_pos = -1; // count for totally words or numbers in line
            int read_number = 0;
            String read_word = "";
//            String nameGamer = "";
            String currentResult = ""; //  score of Game

            String gamer = "gamer";
            sign.ordinaryChar('/'); // it helps to identify such char in 'currDate'
            // if file not equal zero apply formatting rules for updating  table of score
            if (fi.length() != 0) {
                try {
                    while (sign.nextToken() != StreamTokenizer.TT_EOF) {
                        if (sign.ttype == StreamTokenizer.TT_WORD) {
                            read_word = sign.sval;
                        } else if (sign.ttype == StreamTokenizer.TT_NUMBER) {
                            read_number = (int) sign.nval;
                            mem1 = String.valueOf(read_number);
                        } //                  System.out.println( "EOL !!!" + pos );
                        //            }
                        //               if ( (sign.nextToken() == '\n')  ) { // && wr_pos < 8
                        //                  System.out.println( "EOL !!!" + pos );
                        //            }
  if (sign.lineno() == 1) {
         pos++;
         wr_pos++;
   //              System.out.println( pos );
   // rules saving result of gamer  for only right format of string  in file
     if ((pos == 0 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 1 && sign.ttype != StreamTokenizer.TT_WORD) ||
         (pos == 2 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 3 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 4 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 5 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 6 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 7 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 8 && sign.ttype != StreamTokenizer.TT_WORD) ||
         (pos == 9 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 10 && sign.ttype != 47 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 11 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 12 && sign.ttype != 47 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
         (pos == 13 && sign.ttype != StreamTokenizer.TT_NUMBER))
           {
           if (!addRightFormat) {
                addRightFormat = true;
//                System.out.println("YESm");
    // if format line more then 8 symbols  --> it is wrong format
   // condition for score is implemented when  'if( pos == 8 )'
            if (wr_pos > 13) {
               if (v.contains(currentResult)) {
                   // v.remove(mem1);
                   v.remove(currentResult);
                    }
             currentResult = sign.lineno() + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
             v.add(currentResult);
             wr_pos = -1;
            } else {
//             System.out.println("YESm <");
             currentResult = sign.lineno() + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
             v.add(currentResult);
             wr_pos = -1;
                }
             }
         }
         if (pos == 1) { nameGamer = read_word; }
         if (pos == 2) {
           if (scoreGame >= read_number) {
             mem1 = String.valueOf(read_number);
             num2 = read_number;
             } else {
             num2 = read_number;  }
                    }
         if (pos >= 4 && pos <= 7) {
           if (pos == 4) {
             if (read_number < 10) {
              strt = "0" + strt;
              strt = strt + mem1 + ":";
            } else {
              strt = strt + mem1 + ":";
            }
         } else if (pos == 5) {
             if (read_number < 10) {
              strt = strt + "0";
              strt = strt + mem1 + ":";
            } else {
              strt = strt + mem1 + ":";
            }
         } else if (pos == 7) {
             if (read_number < 10) {
              strt = strt + "0";
              strt = strt + mem1;
         } else {
              strt = strt + mem1;
         //     System.out.println( strt);
                                    }
        //  System.out.println( strt);
         }
       }
          if (pos == 8) { strt = strt + " " + read_word; } // 'PM' or 'AM'
          if (pos >= 9 && pos <= 13) {
            if (pos == 9) {
               strd = mem1 + "/";
           } else if (pos == 11) {
               strd = strd + mem1 + "/";
           } else if (pos == 13) {
               strd = strd + mem1;
              // System.out.println(strd);
           if (scoreGame >= num2) {
             if (!v.contains(currentResult)) {
                if (!addRightFormat) {
                //   System.out.println("l1 num > :  " + num + " " +  "num2 :  " + num2);
               currentResult = sign.lineno() + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
               v.add(currentResult);
               mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
               v.add(mem1);
               strt = "";
                 }
              }
           } else {
               if (!v.contains(currentResult)) {
                  if (!addRightFormat) {
               //  System.out.println("l1 num < :  " + num + " " +  "num2 :  " + num2);
              mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
              v.add(mem1);
              currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
              v.add(currentResult);
              strt = "";
           }
         }
       }
           pos = -1;
             }
           }
        }
 else if (sign.lineno() == 2) {
               pos++;
               wr_pos++;
      // rules saving result of gamer  for only right format of string  in file
       if ((pos == 0 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 1 && sign.ttype != StreamTokenizer.TT_WORD) ||
           (pos == 2 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 3 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 4 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 5 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 6 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 7 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 8 && sign.ttype != StreamTokenizer.TT_WORD) ||
           (pos == 9 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 10 && sign.ttype != 47 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 11 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 12 && sign.ttype != 47 && sign.ttype != StreamTokenizer.TT_NUMBER) ||
           (pos == 13 && sign.ttype != StreamTokenizer.TT_NUMBER)) {
                                if (!addRightFormat) {
                                    addRightFormat = true;
                                    //                           System.out.println( "YESm");
                                    // if format line more then 8 symbols  --> it is wrong format
                                    // condition for score is implemented when  'if( pos == 8 )'
                                    if (wr_pos > 13) {
                                        if (v.contains(currentResult)) {
                                            //                            v.remove(mem1);
                                            v.remove(currentResult);
                                        }
                                        currentResult = sign.lineno() + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                                        v.add(currentResult);
                                        wr_pos = -1;
                                    } else {
                                        //                               System.out.println( "YESm <");
                                        currentResult = sign.lineno() + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                                        v.add(currentResult);
                                        wr_pos = -1;
                                    }
                                }
                            }
           if (pos == 1) {  nameGamer = read_word;    }
                            if (pos == 2) {
                                if (scoreGame >= read_number) {
                                    mem1 = String.valueOf(read_number);
                                    num2 = read_number;
                                } else {
                                    num2 = read_number;
                                }
                            }
                            if (pos >= 4 && pos <= 7) {
                                if (pos == 4) {
                                    if (read_number < 10) {
                                        strt = "0" + strt;
                                        strt = strt + mem1 + ":";
                                    } else {
                                        strt = strt + mem1 + ":";
                                    }
        } else if (pos == 5) {
             if (read_number < 10) {
                strt = strt + "0";
                strt = strt + mem1 + ":";
                 } else {
                strt = strt + mem1 + ":";
                 }
        } else if (pos == 7) {
             if (read_number < 10) {
                 strt = strt + "0";
                 strt = strt + mem1;
                 } else {
                 strt = strt + mem1;
                 }
              }

                            }
        if (pos == 8) {  strt = strt + " " + read_word;  }
        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
                               ///System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {  
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
                                     //  System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
  }
 else if (sign.lineno() == 3) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);

        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
            //  System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
                //  System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
 
                        }
 else if (sign.lineno() == 4) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);
            
        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
                            //   System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
                                    //   System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
                        }
 else if (sign.lineno() == 5) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);

        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
                            //   System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
//                                       System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
                        }

 else if (sign.lineno() == 6) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);

        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
//                               System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
//                                       System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
                        }

 else if (sign.lineno() == 7) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);

        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
//                               System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
//                                       System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
                        }

  else if (sign.lineno() == 8) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);

        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
//                               System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
//                                       System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
                        }

  else if (sign.lineno() == 9) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);

        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
//                               System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
//                                       System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
                        }

 else if (sign.lineno() == 10) {
       analyzeRecordScoreLine( scoreGame, v, sign,
             read_number,
             read_word,
             currentResult, //  score of Game
             scoreGame,
             t,
             currDate);

        if (pos >= 9 && pos <= 13) {
          if (pos == 9) {
             strd = mem1 + "/";
          } else if (pos == 11) {
             strd = strd + mem1 + "/";
          } else if (pos == 13) {
             strd = strd + mem1;
            if (scoreGame >= num2) {
//                               System.out.println(" 2 num  > :  " + scoreGame + " " +  "num2 :  " + num2);
           if (v.contains(currentResult)) {
               if (!addRightFormat) {
                  mem1 = (sign.lineno() + 1) + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                  v.add(mem1);
                  strt = "";
//                  mem1 = "";
                  }
               }
           } else {
               if (v.contains(currentResult)) {
                  if (!addRightFormat) {
//                                       System.out.println(" 2 num  < :  " + scoreGame + " " +  "num2 :  " + num2);
                   v.remove(currentResult);
                   mem1 = sign.lineno() + " " + nameGamer + " " + num2 + " " + strt + " " + strd;
                   v.add(mem1);
                   currentResult = (sign.lineno() + 1) + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
                   v.add(currentResult);
                   strt = "";
//                   mem1 = "";
                      }
                   }
               }
                   pos = -1;
                                }
                            }
                        }

                    }
        } catch (IOException ex) {}
    // if file  equal zero  add first gamer score to file
  } else {
    currentResult = sign.lineno() + " " + gamer + " " + scoreGame + " " + t + " " + currDate;
    v.add(currentResult);
         }

             boolean notLast = true;
             for (int k = 0; k < v.size(); k++) {
                 if(v.contains(currentResult)  )
                 {
//                     System.out.println("currentResult: " + currentResult);
//                     if( (k == (v.size() -1))  ) {
//                     System.out.println("Last ! "  );
//                     notLast = true;
//                     } else {
//                     System.out.println("NON Last ! "  );
//                     notLast = true;
//                     }
                 }
             }
             boolean hide_inputgamername = false;
            Enumeration<String> el = v.elements();
            while(el.hasMoreElements() ) {
                String elem = el.nextElement();
                cntLine++;
              if( (elem.equals(currentResult))  ) {
         // if 'currentResult after parsing present not at last line !!!
         // display CLASS:: 'inputgamername' in order to input winner name 
                  if( cntLine < 11 ) { 
//                      inputgamername = new inputGamerName(mainBRICKS);
//                if(inputgamername.allowInputGamer) {
//                       System.out.println("YES  " + cntLine );  
//                 if( !inputgamername.allowInputGamer ) {
//                    // get content of textField from CLASS:: inputGamerName
//                   String gamern = inputgamername.tfield.getText();
//                   String myResult =  cntLine + " " + gamern + " " + scoreGame + " " + t + " " + currDate ;
//                   mem = mem +  myResult + "\n";
//            //it allows not show instance of CLASS :: 'records'
//                   cntLine = 0;
//                      }
//                    }
                      }
                  
                   else {
                   mem = mem + elem + "\n"; 
                      }
                }
              else { mem = mem + elem + "\n";  }
            }
//              System.out.println(mem );

             // from one big string 'mem' do many Strings
             memo = mem.split("\n");
             for (int s = 0; s < memo.length; s++) {
//                  System.out.println("mem1 : " + memo[s] );
             }
              
             line_1  = memo[0].split(" ");
             line_2  = memo[1].split(" ");
             line_3  = memo[2].split(" ");
             line_4  = memo[3].split(" ");
             line_5  = memo[4].split(" ");
             line_6  = memo[5].split(" ");
             
//             if(line_7 == null)
//             line_7  = memo[6].split(" ");
//             if(memo[7] != null) line_8  = null;
//             else                line_8 = memo[7].split(" ");
//             if(memo[8] != null) { line_9 = memo[8].split(" ");
//              System.out.println("memo[8]: " + memo[8]);
//             }
////             else              line_9 = memo[8].split(" ");
//
//             if(memo[9] != null) { line_10  = memo[9].split(" ");
//              System.out.println("memo[9]: " + memo[9]);
//             } else {
//               line_10  = new String[6];
//             }
             
//             else              line_10  = memo[9].split(" ");
             
             for (int s = 0; s < line_1.length; s++) {
//                  System.out.println("memo[0] : " + line_1[s]  );
             }

 // display tableRecords only in variable 'currentResult' present not at last line of file
             // if( cntLine < 11 ) records = new tableOfRecords(mainBRICKS, updateTable.this );

//            aList = Collections.list(el);
//            System.out.println(aList.get(0) );
//            for(int k = 0 ;k < aList.size(); k++ ){  System.out.println(aList.get(k) );  }

//            strs = enumNameToStringArray(new String[] );

//            records = new tableOfRecords( updateTable.this );
//            strs = (String[]) v.toArray(new String[v.size()]);
//            for (int k = 0; k < strs.length; k++) {
////                mem = mem + strs[k] + "\n";
////                System.out.println(strs[k]);
//            }


            File fo = new File(path);
            FileOutputStream fout = new FileOutputStream(fo, false);
            byte[] newbuff = mem.getBytes();
            ByteArrayInputStream newarr = new ByteArrayInputStream(newbuff);
            BufferedInputStream newbufstream = new BufferedInputStream(newarr);
            try {
                fout.write(newbuff, 0, newbuff.length);
                fout.close();
            } catch (IOException ex) {  }



        } catch (FileNotFoundException ex) {
        }
    }
    String[] strs;
    ArrayList<String> aList;
    String[] memo;
    int cntLine = 0; // display line number in 'currentResult' and  in class 'tableOfRecords'
    String[] line_1 , line_2 ,line_3 , line_4 ,  line_5 ,
             line_6 , line_7 ,line_8 , line_9 ,  line_10;
    

    public static <T extends Enum<T>> String[] enumNameToStringArray(T[] values) {
    int i = 0;
    String[] result = new String[values.length];
    for (T value: values) {
      result[i++] = value.name();
    }
    return result;
} 

    private void analyzeRecordScoreLine(
            int num,
            Vector v,
            StreamTokenizer sign,
            int read_number,
            String read_word,
//            String mem1 , // for saving 'number' in vector
//            String nameGamer,
            String currentResult, //  score of Game
            //                 boolean addRightFormat ,
            int scoreGame,
            String t,
            String currDate) {

        pos++;
//        wr_pos++;
//                System.out.println("pos : " + pos );

        if (pos == 1) {
            nameGamer = read_word;
//             System.out.println("read_word : " + read_word );
        }
        if (pos == 2) {
            if (num >= read_number) {
                mem1 = String.valueOf(read_number);
                num2 = read_number;
            } else {
                num2 = read_number;
            }
        }
        if (pos >= 4 && pos <= 7) {
            if (pos == 4) {
                if (read_number < 10) {
                    strt = "0" + strt;
                    strt = strt + mem1 + ":";
                } else {
                    strt = strt + mem1 + ":";
                }
            } else if (pos == 5) {
                if (read_number < 10) {
                    strt = strt + "0";
                    strt = strt + mem1 + ":";
                } else {
                    strt = strt + mem1 + ":";
                }
            } else if (pos == 7) {
                if (read_number < 10) {
                    strt = strt + "0";
                    strt = strt + mem1;
                } else {
                    strt = strt + mem1;
                }
            }
        }
        if (pos == 8) {  strt = strt + " " + read_word; }

       
    }
// work not properly !!!

//    private void resetFormatOfLine(StreamTokenizer sign,
//            //                                        boolean addRightFormat ,
//            Vector<String> v,
//            String currentResult,
//            String gameScore,
//            String t,
//            String currDate) {
//        // rules saving result of gamer  for only right format of string  in file
//        if ((pos == 0 && sign.ttype != StreamTokenizer.TT_NUMBER)
//                || (pos == 1 && sign.ttype != StreamTokenizer.TT_WORD)
//                || (pos == 2 && sign.ttype != StreamTokenizer.TT_NUMBER)
//                || (pos == 3 && sign.ttype != StreamTokenizer.TT_NUMBER)
//                || (pos == 4 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) || // 58 = 0(zero)
//                (pos == 5 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) || // 58 = 0(zero)
//                (pos == 6 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) || // 58 = 0(zero)
//                (pos == 7 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER) || // 58 = 0(zero)
//                (pos == 8 && sign.ttype != StreamTokenizer.TT_WORD)
//                || (pos == 9 && sign.ttype != 58 && sign.ttype != StreamTokenizer.TT_NUMBER)
//                || (pos == 10 && sign.ttype != 47 && sign.ttype != StreamTokenizer.TT_NUMBER) || // 47 = /('/')
//                (pos == 11 && sign.ttype != StreamTokenizer.TT_NUMBER)
//                || (pos == 12 && sign.ttype != 47 && sign.ttype != StreamTokenizer.TT_NUMBER) || // 47 = /('/')
//                (pos == 13 && sign.ttype != StreamTokenizer.TT_NUMBER)) {
//            if (!addRightFormat) {
//                addRightFormat = true;
//                // if format line more then 8 symbols  --> it is wrong format
//                // condition for score is implemented when  'if( pos == 8 )'
//                if (wr_pos > 13) {
//                    if (v.contains(currentResult)) {
////                            v.remove(mem1);
//                        System.out.println("YESmetod > 13" + " " + addRightFormat);
//                        v.remove(currentResult);
//                    }
//                    currentResult = sign.lineno() + " " + "gamer" + " " + gameScore + " " + t + " " + currDate;
//                    v.add(currentResult);
//                    wr_pos = -1;
//                } else {
//                    System.out.println("YESmethod < 13");
//                    currentResult = sign.lineno() + " " + "gamer" + " " + gameScore + " " + t + " " + currDate;
//                    v.add(currentResult);
//                    wr_pos = -1;
//                }
//            }
////                 System.out.println("pos : " + pos );
//        }
//    }

    public static void main(String[] args) {
//        new updateTable();
    }
}
