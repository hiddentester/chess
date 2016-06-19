/***********************************************************************************************
	File Name:   Chessboard.java
	Name:        hiddentester
	Date:        2016 / 18 / 06
	Description: This program runs code.
 **********************************************************************************************/
	
	import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
    
   public class Chessboard implements MouseListener {
      final int PADDING = 50;
		final int LINEWEIGHT = 5;
      final int BOARDSIZE = 8;
      int [][] board;
      int squareSize;
      int xShift;
      int yShift;
      Drawing draw = new Drawing();
      
      public Chessboard() {
         JFrame frame = new JFrame("Chessboard");
         frame.add(draw);
         draw.addMouseListener(this);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(500, 500);
         frame.setVisible(true);
      } // Chessboard constructor
		
		class Drawing extends JComponent {
			public void paint(Graphics g) {
				// update the size and position of the squares based on the size of the window
           	squareSize = (int) Math.ceil(Math.min(getWidth() - PADDING / 2, getHeight() - PADDING / 2) / (double) BOARDSIZE);
           	xShift = (getWidth() - (BOARDSIZE * squareSize)) / 2;
           	yShift = (getHeight() - (BOARDSIZE * squareSize)) / 2;
					
				// draw grid
           	for (int lines = squareSize; lines < BOARDSIZE * squareSize; lines += squareSize) {
               g.fillRect(lines + xShift - LINEWEIGHT / 2, yShift, LINEWEIGHT, BOARDSIZE * squareSize);
               g.fillRect(xShift, lines + yShift - LINEWEIGHT / 2, BOARDSIZE * squareSize, LINEWEIGHT);
            } // for loop
         } // paint method
      } // Drawing class
   
   	// --> starting implementing MouseListener
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
  		// finishing implementing MouseListener  <---
   
      public static void main(String[] args) {
         new Chessboard();
      } // main method
  	} // Chessboard class