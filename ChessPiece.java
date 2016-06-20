/************************************************************************
 * File Name:   ChessPiece.java
 * Name:        hiddentester
 * Date:        2016 / 06 / 19
 * Description: This program describes a chesspiece.
 ************************************************************************/

public class ChessPiece {
   String type;
   int team;
   int xPos;
   int yPos;
      
   public ChessPiece(String rank, int colour, int col, int row) {
      type = rank.toLowerCase();
      team = colour;
      xPos = col;
      yPos = row;
   } // ChessPiece constructor
   
   public void setPos (int col, int row) {
      xPos = col;
      yPos = row;
   } // setPos method
} // ChessPiece class