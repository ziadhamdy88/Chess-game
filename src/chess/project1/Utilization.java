/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1;

/**
 *
 * @author ZIAD
 */
public class Utilization {
    
    public static final boolean[] firstColumn = column(0);
    public static final boolean[] secondColumn = column(1);
    public static final boolean[] seventhColumn = column(6);
    public static final boolean[] eighthColumn = column(7);
    
//    public static final boolean[] firstRow = row(0);
    public static final boolean[] secondRow = row(8);
//    public static final boolean[] thirdRow = row(16);
//    public static final boolean[] fourthRow = row(24);
//    public static final boolean[] fifthRow = row(32);
 //   public static final boolean[] sixthRow = row(40);
    public static final boolean[] seventhRow = row(48);
 //   public static final boolean[] eighthRow = row(56);
    
    
    public static boolean isValidTileCoordinate(final int Coordinate) {
        if(Coordinate >= 0 && Coordinate < 64)
            return true;
        return false;
    }
    
    private static boolean[] row(int rowNumber){
        final boolean[] row = new boolean[64];
        do{
            row[rowNumber] = true;
            rowNumber++;
        }while(rowNumber % 8 != 0);
        return row;
    }

    private static boolean[] column(int columnNumber) {
        
        final boolean[] column = new boolean[64];
        
        do{
            column[columnNumber] = true;
            columnNumber += 8;
        }while(columnNumber < 64);
        return column;
    }
  
}
