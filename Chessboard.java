/********************************************************
 * File Name:   Chessboard.java
 * Name:        hiddentester
 * Date:        2016 / 18 / 06
 * Description: This program is a game of chess.
 ********************************************************/
	
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
   
public class Chessboard implements MouseListener, MouseMotionListener {
   final int PADDING = 50;
   final int BOARDSIZE = 8;
   final int WHITE = 0;
   final int BLACK = 1;
   final int TILE = 0;
   final int PAWN = 2;
   final int ROOK = 4;
   final int KNIGHT = 6;
   final int BISHOP = 8;
   final int QUEEN = 10;
   final int KING = 12;
   int [][] board = new int [BOARDSIZE][BOARDSIZE];
   int squareSize;
   int xShift, yShift;
   int mouseRow = -1, mouseCol = -1;
   Drawing draw = new Drawing();
   ImageIcon[] boardPictures = new ImageIcon[15];
   
   public Chessboard() {
      // load images
      for (int i = 0; i < boardPictures.length; i ++) {
         String fileName = "";
         
         switch (i / 2) {
            case 0:
               fileName = "tile";
               break;
            case 1:
               fileName = "pawn";
               break;
            case 2:
               fileName = "rook";
               break;
            case 3:
               fileName = "knight";
               break;
            case 4:
               fileName = "bishop";
               break;
            case 5:
               fileName = "queen";
               break;
            case 6:
               fileName = "king";
               break;
            default:
               fileName = "overlay";
         } // switch structure
         
         if (i < 14) {
            if (i % 2 == 0) {
               fileName += "_white";
            } else {
               fileName += "_black";
            } // if structure
         } else {
            switch (i - 14) {
               default:
                  fileName += "_hover";
            } // switch structure
         } // if structure
         boardPictures[i] = new ImageIcon(fileName + ".png");
      } // for loop
      
      restart();
      
      JFrame frame = new JFrame("Chessboard");
      frame.add(draw);
      draw.addMouseListener(this);
      draw.addMouseMotionListener(this);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setMinimumSize(new Dimension (200, 220));
      frame.setSize(new Dimension (500, 520));
      frame.setVisible(true);
   } // Chessboard constructor
	
   public void restart () {
      for (int row = 0; row < BOARDSIZE; row ++) {
         for (int col = 0; col < BOARDSIZE; col ++) {
            if (row == 0 || row == BOARDSIZE - 1) {
               board[row][col] = (row + 1) % 2;
               
               switch (col) {
                  case 0:
                  case 7:
                     board[row][col] += ROOK;
                     break;
                  case 1:
                  case 6:
                     board[row][col] += KNIGHT;
                     break;
                  case 2:
                  case 5:
                     board[row][col] += BISHOP;
                     break;
                  case 3:
                     board[row][col] += KING;
                     break;
                  default:
                     board[row][col] += QUEEN;
               } // switch structure
            } else if (row == 1 || row == BOARDSIZE - 2) {
               board[row][col] = row % 2 + PAWN;
            }// if structure
         } // for loop
      } // for loop
   } // restart method
   
   class Drawing extends JComponent {
      public void paint(Graphics g) {
		   // update the size and position of the squares based on the size of the window
         squareSize = (int) Math.ceil(Math.min(getWidth() - PADDING / 2, getHeight() - PADDING / 2) / (double) BOARDSIZE);
         xShift = (getWidth() - (BOARDSIZE * squareSize)) / 2;
         yShift = (getHeight() - (BOARDSIZE * squareSize)) / 2;
         
         // draw board
         for (int row = 0; row < BOARDSIZE; row ++) {
            for (int col = 0; col < BOARDSIZE; col ++) {
               g.drawImage(boardPictures[(row + col + 1) % 2].getImage(), col * squareSize + xShift, row * squareSize + yShift, squareSize, squareSize, this);
               
               if (board[row][col] != 0) {
                  g.drawImage(boardPictures[board[row][col]].getImage(), col * squareSize + xShift, row * squareSize + yShift, squareSize, squareSize, this);
               } // if structure
            } // for loop
         } // for loop
         
         // draw mouse position
         if (mouseCol >= 0 && mouseCol < BOARDSIZE && mouseRow >= 0 && mouseRow < BOARDSIZE) {
            g.drawImage(boardPictures[14].getImage(), mouseCol * squareSize + xShift, mouseRow * squareSize + yShift, squareSize, squareSize, this);
         } // if structure
      } // paint method
   } // Drawing class
   
   public void mousePressed(MouseEvent e) {
   }
   
   public void mouseReleased(MouseEvent e) {
   }
   
   public void mouseClicked(MouseEvent e) {
   }
   
   public void mouseEntered(MouseEvent e) {
   }
   
   public void mouseExited(MouseEvent e) {
   }
   
   public void mouseMoved(MouseEvent e) {
      mouseRow = (int) Math.floor((e.getY() - yShift) / (double) squareSize);
      mouseCol = (int) Math.floor((e.getX() - xShift) / (double) squareSize);
      draw.repaint();
   }
   
   public void mouseDragged(MouseEvent e) {
   }
   
   public static void main(String[] args) {
      new Chessboard();
   } // main method
} // Chessboard class