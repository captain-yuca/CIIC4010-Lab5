package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mineSweeperObjects.MineSweeperBoard;

public class MyPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 29;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 9;   //Last row has only one cell
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	public MineSweeperBoard mineSweeperBoard = new MineSweeperBoard(TOTAL_COLUMNS, TOTAL_ROWS); //Constructing a new Game Board
	public int[] xCounter = new int[TOTAL_COLUMNS*TOTAL_ROWS];
	public int[] yCounter = new int[TOTAL_COLUMNS*TOTAL_ROWS];;
	public int numberCounter=0;
	public JLabel[] innerLabels = new JLabel[TOTAL_COLUMNS*TOTAL_ROWS];

	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Constructors
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/


	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;

			}
		}
		this.setLayout(null);
	}
	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Methods
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x1, y1, width + 1, height + 1);
		//put a string on the panel


		//Draw the grid minus the bottom row (which has only one cell)
		//By default, the grid will be 10x10 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
		}

		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {

				Color c = colorArray[x][y];
				g.setColor(c);
				g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);


			}
		}
	}
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return x;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return y;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS-1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}

	//Method: Restarts the whole game
	public void restartGame(){
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				this.colorArray[x][y] = Color.WHITE;
			}
		}
//		for (int i = 0;i<=numberCounter; i++ ){
//			this.remove(this.innerLabels[i]);
//		}
		this.removeAll();
		this.mineSweeperBoard.restartBoard();
		numberCounter = 0;
		this.repaint();

	}

	public void paintNumbers(int xPosition, int yPosition){
		if(this.mineSweeperBoard.mineSweeperGameBoard[xPosition][yPosition].getProximityNumber()>0){
			this.mineSweeperBoard.mineSweeperGameBoard[xPosition][yPosition].initLabel(xPosition, yPosition);
			this.add(this.mineSweeperBoard.mineSweeperGameBoard[xPosition][yPosition].proximityLabel);
			
		}


	}

	//Method: Reveals neighbor squares from the specified position
	public void revealSquares(int xPosition, int yPosition){
		if(xPosition<0||yPosition<0||xPosition>this.mineSweeperBoard.getxBoardSize()||yPosition>this.mineSweeperBoard.getyBoardSize()){
			//Do nothing
		}
		else{
			this.mineSweeperBoard.setObjectWasClickToTrue(xPosition, yPosition);
			this.mineSweeperBoard.raiseClickedCounter();
			this.paintNumbers(xPosition, yPosition);
			this.colorArray[xPosition][yPosition] = Color.GRAY;
			int[] upSquare ={xPosition, yPosition-1};
			int[] downSquare={xPosition, yPosition+1};
			int[] rightSquare={xPosition+1, yPosition};
			int[] leftSquare={xPosition-1, yPosition};

			if(!(upSquare[1]<0) && this.mineSweeperBoard.getProximityNumber(upSquare[0], upSquare[1])!=-1 && !(this.mineSweeperBoard.verifyIfObjectWasClicked(upSquare[0], upSquare[1]))){
				if(this.mineSweeperBoard.getProximityNumber(upSquare[0], upSquare[1])==0){
					revealSquares(upSquare[0], upSquare[1]);
				}
				else{
					this.mineSweeperBoard.setObjectWasClickToTrue(upSquare[0], upSquare[1]);
					this.mineSweeperBoard.raiseClickedCounter();
					this.colorArray[upSquare[0]][upSquare[1]] = Color.GRAY;
					this.paintNumbers(upSquare[0],upSquare[1]);
				}
			}
			if(!(downSquare[1]>=mineSweeperBoard.getyBoardSize()) && this.mineSweeperBoard.getProximityNumber(downSquare[0], downSquare[1])!=-1 && !(this.mineSweeperBoard.verifyIfObjectWasClicked(downSquare[0], downSquare[1]))){
				if(this.mineSweeperBoard.getProximityNumber(downSquare[0], downSquare[1])==0){
					revealSquares(downSquare[0], downSquare[1]);
				}
				else{
					this.mineSweeperBoard.setObjectWasClickToTrue(downSquare[0], downSquare[1]);
					this.mineSweeperBoard.raiseClickedCounter();
					this.colorArray[downSquare[0]][downSquare[1]] = Color.GRAY;
					this.paintNumbers(downSquare[0],downSquare[1]);

				}
			}
			if(!(rightSquare[0]>=mineSweeperBoard.getxBoardSize()) && this.mineSweeperBoard.getProximityNumber(rightSquare[0], rightSquare[1])!=-1 && !(this.mineSweeperBoard.verifyIfObjectWasClicked(rightSquare[0], rightSquare[1]))){
				if(this.mineSweeperBoard.getProximityNumber(rightSquare[0], rightSquare[1])==0){
					revealSquares(rightSquare[0], rightSquare[1]);
				}
				else{
					this.mineSweeperBoard.setObjectWasClickToTrue(rightSquare[0], rightSquare[1]);
					this.mineSweeperBoard.raiseClickedCounter();
					this.colorArray[rightSquare[0]][rightSquare[1]] = Color.GRAY;
					this.paintNumbers(rightSquare[0],rightSquare[1]);

				}
			}
			if(!(leftSquare[0]<0) && this.mineSweeperBoard.getProximityNumber(leftSquare[0], leftSquare[1])!=-1 && !(this.mineSweeperBoard.verifyIfObjectWasClicked(leftSquare[0], leftSquare[1]))){
				if(this.mineSweeperBoard.getProximityNumber(leftSquare[0], leftSquare[1])==0){
					revealSquares(leftSquare[0], leftSquare[1]);
				}
				else{
					this.mineSweeperBoard.setObjectWasClickToTrue(leftSquare[0], leftSquare[1]);
					this.mineSweeperBoard.raiseClickedCounter();
					this.colorArray[leftSquare[0]][leftSquare[1]] = Color.GRAY;
					this.paintNumbers(leftSquare[0],leftSquare[1]);

				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}