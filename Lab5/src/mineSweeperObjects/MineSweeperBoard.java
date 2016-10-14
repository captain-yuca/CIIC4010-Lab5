package mineSweeperObjects;

import java.awt.Color;
import java.util.Random;

public class MineSweeperBoard {

	public MineSweeperObject[][] mineSweeperGameBoard;
	public Flag[][] flagBoard;
	public int xBoardSize;
	public int yBoardSize;
	public Random randomGenerator;
	public int mineQuantity;
	public int getProxNumber;
	public static final int MINEBOUNDS = 6;


	//Constructor for MineSweeperBoard
	public MineSweeperBoard(int xSize, int ySize){
		this.xBoardSize = xSize;
		this.yBoardSize = ySize;
		this.mineSweeperGameBoard = new MineSweeperObject[xSize][ySize];
		this.flagBoard = new Flag[xSize][ySize];
		this.fillBoard();
		this.populateFlagBoard();
		this.populateGameBoard();
	}

	// Method: Add an MineSweeperObject to the board
	public void addMineSweeperObjectToBoard(MineSweeperObject msObj, int xPosition, int yPosition) {
		this.mineSweeperGameBoard[xPosition][yPosition] = msObj;
	}
	public void fillBoard(){
		for(int m = 0 ; m < xBoardSize; m++){
			for(int n = 0; n < yBoardSize; n++){
				this.mineSweeperGameBoard[m][n] = new MineSweeperObject();
			}
		}

	}//Constructor to get the number of neighbors in all the squares
	public void proximityNumber(int xPosition, int yPosition){ 
		int counterOfMines = 0;											
		for(int i = xPosition - 1 ; i < xPosition + 2; i++){
			for(int j = yPosition - 1; j < yPosition + 2; j++){     

				if(i == xPosition && j == yPosition){
					//Do nothing


				}
				else if(i<0 || j<0 || i>=this.xBoardSize || j>=this.yBoardSize){
					//Do nothing

				}
				else if(this.mineSweeperGameBoard[i][j].getObjectColor().equals(Color.BLACK)){
					counterOfMines++;


				}
				else{
					//Do Nothing
				}
			}
		}
		if(!this.mineSweeperGameBoard[xPosition][yPosition].getObjectColor().equals(Color.BLACK)){
			this.mineSweeperGameBoard[xPosition][yPosition].setProximintyNumber(counterOfMines);
			System.out.println("Counter of Mines in ("+xPosition+", "+yPosition + ") = "  + counterOfMines);
		}

	}

	// Method: Check if there is a mine and if there is, print Game Over and
	// return true
	public boolean verifyMine(int xPosition, int yPosition) {
		if (this.mineSweeperGameBoard[xPosition][yPosition].getObjectColor().equals(Color.BLACK)) {
			System.out.println("Game Over");
			return true;
		}
		return false;
	}

	public boolean verifyFlagUp(int xPosition, int yPosition) {
		if (this.flagBoard[xPosition][yPosition].getFlagUp() == true) {
			return true;
		}
		return false;
	}


	public void setFlagUp(int xPosition, int yPosition, boolean state) {
		this.flagBoard[xPosition][yPosition].setFlagUp(state);
	}

	public MineSweeperObject getMineSweeperObjectFromArray(int xPosition, int yPosition) {
		return this.mineSweeperGameBoard[xPosition][yPosition];
	}
	// Method: Return Color of the mine that is on the specified position
	public Color getCurrentObjectColor(int xPosition, int yPosition) {
		return this.mineSweeperGameBoard[xPosition][yPosition].getObjectColor();
	}

	// Method: Add the mines in a random fashion to the board
	public void populateGameBoard() {
		System.out.println("Commencing to Populate the Board");
		randomGenerator = new Random(); 
		//this.mineQuantity = Math.max(randomGenerator.nextInt(MINEBOUNDS+1), 1);
		this.mineQuantity = 1;
		int randomXPos;
		int randomYPos;
		for (int i = 0; i < this.mineQuantity; i++) {
			randomXPos = randomGenerator.nextInt(this.xBoardSize - 1); // -1
			// so
			// it
			// doesn't
			// go
			// offbounds
			randomYPos = randomGenerator.nextInt(this.yBoardSize - 1);
			System.out.println("Mine" + i + ": " + (randomXPos) + ", " + (randomYPos));
			addMineSweeperObjectToBoard(new Mine(), randomXPos, randomYPos);
			System.out.println(mineSweeperGameBoard[randomXPos][randomYPos]);
			//System.out.println(" the number of neighborhs of position : (3,3) is :" + this.mineSweeperGameBoard[3][3].getProxNumber() );
		}
		for(int x = 0; x < xBoardSize; x++ ){
			for(int y = 0; y<yBoardSize; y++){
				this.proximityNumber(x, y);

			}
		}
	}

	// Method: Makes a new MineSweeperGameBoard
	public void clearGameBoard() {
		mineSweeperGameBoard = new MineSweeperObject[this.xBoardSize][this.yBoardSize];
	}

	public void clearFlagBoard() {
		flagBoard = new Flag[this.xBoardSize][this.yBoardSize];

	}

	public void populateFlagBoard() {
		for (int i = 0; i < this.xBoardSize; i++) {
			for (int j = 0; j < this.yBoardSize; j++) {
				this.flagBoard[i][j] = new Flag();
			}
		}
	}
	//This class has to change the counterOfMines integer number to a string number so then I could use it to print it on the Board
	public void addStringToBoard(){
		for (int i = 0; i < this.xBoardSize; i++) {
			for (int j = 0; j < this.yBoardSize; j++) {
				String proxNumString =  this.mineSweeperGameBoard[i][j].addStringToBoard();
				//System.out.println("The number of mines pero coordinates is: ( " " ,+ i " " +j" "  );
			}
		}
	}
	public void StringAddingToSquares(){
		for (int i = 0; i < this.xBoardSize; i++) {
			for (int j = 0; j < this.yBoardSize; j++) {

			}
		}
	}	
	public void restartBoard() {
		this.clearGameBoard();
		this.clearFlagBoard();
		this.fillBoard();
		this.populateGameBoard();
		this.populateFlagBoard();
	}

}
