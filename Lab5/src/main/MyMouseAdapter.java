package main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		myPanel.mouseDownGridX = myPanel.getGridX(x, y);
		myPanel.mouseDownGridY = myPanel.getGridY(x, y);
		myPanel.repaint();
		switch (e.getButton()) {

		case 1:		//Left mouse button
			break;

		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {

		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame)c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);
		switch (e.getButton()) {
		case 1:		//Left mouse button



			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
							//On the grid other than on the left column and on the top row:

							//Verify if there is a mine, and if there is, pain the selected square black
						if(!myPanel.mineSweeperBoard.getGameOver() && !myPanel.mineSweeperBoard.verifyIfWin()){

							if(myPanel.mineSweeperBoard.verifyMine(myPanel.mouseDownGridX, myPanel.mouseDownGridY)){
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = myPanel.mineSweeperBoard.getCurrentObjectColor(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
								myPanel.mineSweeperBoard.setGameOver(true);
								myPanel.repaint();
							}
							else if(myPanel.mineSweeperBoard.getProximityNumber(myPanel.mouseDownGridX, myPanel.mouseDownGridY)>0){
								myPanel.mineSweeperBoard.setObjectWasClickToTrue(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
								myPanel.mineSweeperBoard.raiseClickedCounter();
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.GRAY;
								myPanel.paintNumbers(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
							}
							else if(!myPanel.mineSweeperBoard.verifyIfObjectWasClicked(myPanel.mouseDownGridX, myPanel.mouseDownGridY)){
								//myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.GRAY;
								//myPanel.mineSweeperBoard.setObjectWasClickToTrue(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
								myPanel.revealSquares(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
							}
							
							if(myPanel.mineSweeperBoard.verifyIfWin()){
								System.out.println("You win!");
							}
						}
						else{
							//Do Nothing
						}
						
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button

			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
				System.out.println("1");
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed

						//Do nothing
					} else {
							//On the grid other than on the left column and on the top row:
						if(!myPanel.mineSweeperBoard.verifyIfObjectWasClicked(myPanel.mouseDownGridX, myPanel.mouseDownGridY)){
							if(!myPanel.mineSweeperBoard.verifyFlagUp(myPanel.mouseDownGridX, myPanel.mouseDownGridY) ){
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
								myPanel.mineSweeperBoard.setFlagUp(myPanel.mouseDownGridX,myPanel.mouseDownGridY, true);
							}
							else{
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
								myPanel.mineSweeperBoard.setFlagUp(myPanel.mouseDownGridX,myPanel.mouseDownGridY, false);
							}

							myPanel.repaint();
						}
						
					}
				}
			}
			myPanel.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}