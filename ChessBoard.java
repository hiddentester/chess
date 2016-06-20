/********************************************************
 * File Name:   ChessBoard.java
 * Name:        hiddentester
 * Date:        2016 / 06 / 19
 * Description: This program is a game of chess.
 ********************************************************/
	
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
   
public class ChessBoard extends MouseInputAdapter {
   final int PADDING = 50;
   final int BOARDSIZE = 8;
   final int WHITE = 0;
   final int BLACK = 1;
   int mouseCol = -1;
   int mouseRow = -1;
   int newCol = -1;
   int newRow = -1;
   int squareSize;
   int xShift, yShift;
   boolean valid;
   java.util.List<ChessPiece> pieces;
   ChessPiece p1 = null, p2 = null;
   Drawing draw = new Drawing();
   ImageIcon[] boardPictures = new ImageIcon[17];
   
   public ChessBoard() {
      //load images
      boardPictures[0] = new ImageIcon("tile_white.png");
      boardPictures[1] = new ImageIcon("tile_black.png");
      boardPictures[2] = new ImageIcon("pawn_white.png");
      boardPictures[3] = new ImageIcon("pawn_black.png");
      boardPictures[4] = new ImageIcon("rook_white.png");
      boardPictures[5] = new ImageIcon("rook_black.png");
      boardPictures[6] = new ImageIcon("knight_white.png");
      boardPictures[7] = new ImageIcon("knight_black.png");
      boardPictures[8] = new ImageIcon("bishop_white.png");
      boardPictures[9] = new ImageIcon("bishop_black.png");
      boardPictures[10] = new ImageIcon("queen_white.png");
      boardPictures[11] = new ImageIcon("queen_black.png");
      boardPictures[12] = new ImageIcon("king_white.png");
      boardPictures[13] = new ImageIcon("king_black.png");
      boardPictures[14] = new ImageIcon("overlay_hover.png");
      boardPictures[15] = new ImageIcon("overlay_valid.png");
      boardPictures[16] = new ImageIcon("overlay_invalid.png");
      
      restart();
      
      JFrame frame = new JFrame("ChessBoard");
      frame.add(draw);
      draw.addMouseListener(this);
      draw.addMouseMotionListener(this);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setMinimumSize(new Dimension (200, 220));
      frame.setSize(new Dimension (500, 520));
      frame.setVisible(true);
   } //ChessBoard constructor
	
   //Reset board
   public void restart () {
      pieces = new ArrayList<ChessPiece>();
      
      for (int team = WHITE; team <= BLACK; team ++) {
         int row;
         if (team == WHITE) {
            row = 6;
         } else {
            row = 1;
         } //if structure
         
         for (int col = 0; col < 8; col ++) {
            pieces.add(new ChessPiece("pawn", team, col, row));
         } //for loop
         
         if (team == WHITE) {
            row = 7;
         } else {
            row = 0;
         } //if structure
         
         pieces.add(new ChessPiece("rook", team, 0, row));
         pieces.add(new ChessPiece("rook", team, 7, row));
         pieces.add(new ChessPiece("knight", team, 1, row));
         pieces.add(new ChessPiece("knight", team, 6, row));
         pieces.add(new ChessPiece("bishop", team, 2, row));
         pieces.add(new ChessPiece("bishop", team, 5, row));
         pieces.add(new ChessPiece("queen", team, 3, row));
         pieces.add(new ChessPiece("king", team, 4, row));
      } //for loop
   } //restart method
   
   public boolean isValid () {
      if (newCol < 0 || newCol >= BOARDSIZE || newRow < 0 || newRow >= BOARDSIZE) {
         return false;
      } //if structure
      
      if (p2 != null) {
         if (p1.team == p2.team) {
            return false;
         } //if structre
      } //if structure
      
      switch (p1.type) {
         case "pawn":
            if (p1.team == BLACK) {
               
            } //if structure
         case "rook":
         
         case "knight":
         
         case "bishop":
         
         case "queen":
         
         case "king":
      } //switch structure
      
      return true;
   } //isValid method
   
   public boolean onBoard (int col, int row) {
      return (col >= 0 && col < BOARDSIZE && row >= 0 && row < BOARDSIZE);
   } //onBoard method
   
   class Drawing extends JComponent {
      public void paint(Graphics g) {
         updateScale();
         board(g);
         overlay(g);
         pieces(g);
      } //paint method
      
      public void updateScale() {
         squareSize = (int) Math.ceil(Math.min(getWidth() - PADDING / 2, getHeight() - PADDING / 2) / (double) BOARDSIZE);
         xShift = (getWidth() - (BOARDSIZE * squareSize)) / 2;
         yShift = (getHeight() - (BOARDSIZE * squareSize)) / 2;
      } //scaling method
      
      //Draw board
      public void board(Graphics g) {
         updateScale();
         for (int row = 0; row < BOARDSIZE; row ++) {
            for (int col = 0; col < BOARDSIZE; col ++) {
               g.drawImage(boardPictures[(row + col + 1) % 2].getImage(), col * squareSize + xShift, row * squareSize + yShift, squareSize, squareSize, this);
            } //for loop
         } //for loop
      } //board method
      
      //Draw pieces
      public void pieces(Graphics g) {
         int imageIndex;
         for (ChessPiece p : pieces) {
            switch (p.type) {
               case "pawn":
                  imageIndex = 2;
                  break;
               case "rook":
                  imageIndex = 4;
                  break;
               case "knight":
                  imageIndex = 6;
                  break;
               case "bishop":
                  imageIndex = 8;
                  break;
               case "queen":
                  imageIndex = 10;
                  break;
               default:
                  imageIndex = 12;
            } //switch structure
            
            if (p.team == BLACK) {
               imageIndex ++;
            } //if structure
            
            g.drawImage(boardPictures[imageIndex].getImage(), p.xPos * squareSize + xShift, p.yPos * squareSize + yShift, squareSize, squareSize, this);
         } //for loop
      } //pieces method
      
      public void overlay(Graphics g) {
         //highlight mouse selection
         if (mouseCol != -1 && mouseRow != -1) {
            if (mouseCol >= 0 && mouseCol < BOARDSIZE && mouseRow >= 0 && mouseRow < BOARDSIZE) {
               g.drawImage(boardPictures[14].getImage(), mouseCol * squareSize + xShift, mouseRow * squareSize + yShift, squareSize, squareSize, this);
            } //if structure
         } //if structure
         
         if (onBoard(newCol, newRow)) {
            if (valid) {
               g.drawImage(boardPictures[15].getImage(), newCol * squareSize + xShift, newRow * squareSize + yShift, squareSize, squareSize, this);
            } else {
               g.drawImage(boardPictures[16].getImage(), newCol * squareSize + xShift, newRow * squareSize + yShift, squareSize, squareSize, this);
            } //if structure
         } //if structure
      } //overlay method
   } //Drawing class
   
   public void mouseReleased(MouseEvent e) {
      if ((newCol != mouseCol || newRow != mouseRow) && onBoard(newCol, newRow)) {
         if (valid) {
            p1.setPos(newCol, newRow);
            pieces.remove(p2);
         } //if structure
      } //if structure
      
      mouseRow = newRow;
      mouseCol = newCol;
      newRow = -1;
      newCol = -1;
      draw.repaint();
   } //mouseReleased method
   
   public void mouseMoved(MouseEvent e) {
      mouseRow = (int) Math.floor((e.getY() - yShift) / (double) squareSize);
      mouseCol = (int) Math.floor((e.getX() - xShift) / (double) squareSize);
      draw.repaint();
   } //mouseMoved method
   
   public void mouseDragged(MouseEvent e) {
      newRow = (int) Math.floor((e.getY() - yShift) / (double) squareSize);
      newCol = (int) Math.floor((e.getX() - xShift) / (double) squareSize);
      p1 = null;
      p2 = null;
      
      if ((newRow == mouseRow && newCol == mouseCol) || !onBoard(newCol, newRow)) {
         newCol = -1;
         newRow = -1;
      } else {
         for (ChessPiece p : pieces) {
            if (mouseCol == p.xPos && mouseRow == p.yPos) {
               p1 = p;
            } else if (newCol == p.xPos && newRow == p.yPos) {
               p2 = p;
            } //if structure
            
            if (p1 != null && p2 != null) {
               break;
            } //if structure
         } //for loop
         
         if (p1 != null && p2 != null) {
            valid = isValid();
         } else if (p1 != null && p2 == null) {
            valid = true;
         } else {
            valid = false;
         } //if structure
      } //if structure
      draw.repaint();
   } //mouseDragged method
   
   public static void main (String[] args) {
      new ChessBoard();
   } //main method
} //ChessBoard class