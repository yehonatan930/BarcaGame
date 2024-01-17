/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barcamodel;

/**
 *
 * @author User
 */
public class BarcaBoard {
    static final int BOARDLEN = 12;
    private GameSquare boardMat[][];
    
    //constructors:
  
    public BarcaBoard() {
        boardMat = new GameSquare[BOARDLEN][BOARDLEN];
        for (int i = 0; i < BOARDLEN; i++)
            for (int j = 0; j < BOARDLEN; j++)
                boardMat[i][j]= new GameSquare(this, i, j);
    }
   
    
    //methods:
    //getters
   
    public GamePiece getPieceInPos(int x, int y){
        return this.boardMat[x][y].getPiece();
    }
    public boolean isEmpty (int x, int y){return this.boardMat[x][y].isEmpty();}
    
    //setters
    //etc.
    private void initBoard(boolean white){
      /*  
        int flag, yPos; //flag 
        flag = (white)? 1:0;
        //setting the pieces:
        //elephants:
        yPos = 1 + flag * 9;
        this.boardMat[5][yPos].placePiece(new ElephantPiece(white,boardMat[5][yPos]));
        this.boardMat[6][yPos] = new ElephantPiece(white);
        //lions:
        yPos = 2 + flag * 7;
        this.boardMat[3][yPos] = new LionPiece(white);
        this.boardMat[6][yPos] = new LionPiece(white);
        //mouses:
        this.boardMat[4][yPos] = new MousePiece(white);
        this.boardMat[5][yPos] = new MousePiece(white);
*/
    }
    
    
    public GameSquare getSquare (int x, int y){
        return boardMat[x + 1][y + 1];
    }
    
    public void printcheck(){
        for (int i = 0; i < BOARDLEN; i++)
            for (int j = 0; j < BOARDLEN; j++){
                if (!boardMat[i][j].isEmpty())
                    System.out.println(i + " " + j);
            }
    }
    
    @Override
    public String toString() {
        return "";
    }
}
