
import java.util.*;
public class sudoku {
    static int count=1;
    static boolean isSafe(int [][]board,int row,int col,int digit){
       //column check
        for(int j=0;j<=8;j++){
            if(board[row][j]==digit){
               return false;
            }
        }

        //row check
        for(int k=0;k<=8;k++){
            if(board[k][col]==digit){
               return false;
            }
        }

        //3x3 Matrix
        int StartRow=(row/3)*3;
        int StartCol=(col/3)*3;
        for(int i=StartRow;i<StartRow+3;i++){
            for(int j=StartCol;j<StartCol+3;j++){
                if(board[i][j]==digit){
                    return false;
                }
            }
        }
        // digits not found in that column ,rows and its corresponding 3x3 matrix
        return true;
    }

    static int enterDigit(int board[][],int row,int col){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter value for row "+row +" col " +col  );
        int n=sc.nextInt();
        
        if(count==3){
            System.out.println("You have entered wrong value");
            System.out.println("You mistake 3 times ");
            return 0;
        }
        if(isSafe(board, row, col, n) ){
            return n;
        }else{
            count++;
            System.out.println("You have entered wrong value");
            System.out.println("Enter value again");
            return enterDigit(board, row, col);
               
        }
    }


    static boolean  sudokuSolver(int board[][],int row,int col){
        //base case
        if(row==9){
            return true;
        }
        
        int nextRow=row;
        int nextCol=col+1;
        if(col==8){
           nextRow=row+1;
           nextCol=0;
        }

        if(board[row][col]!=0){
            return sudokuSolver(board, nextRow, nextCol);
        }

        int a=enterDigit(board, row, col);
        if(a==0){
            return false;
        }
        board[row][col]=a;
        if(sudokuSolver(board, nextRow, nextCol)){
            return true;
        }
        return false;
    }

    static void print(int [][]board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(board[i][j]+ "  ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        //System Sudoku
        int board[][]=
            { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
        
        
        if(sudokuSolver(board, 0, 0)){
             System.out.println("you won");
             print(board);
        }else{
            System.out.println("You loss the game");
        }
    }   
}
