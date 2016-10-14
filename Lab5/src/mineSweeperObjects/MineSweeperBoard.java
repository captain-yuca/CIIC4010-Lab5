package mineSweeperObjects;

import java.awt.Color;
import java.util.Random;

public class MineSweeperBoard {

	private MineSweeperObject[][] mineSweeperGameBoard; //Array to store game objects
	private Flag[][] flagBoard; //Array to store Red Flags
	private boolean gameOver; //Determines if game is over
	private int xBoardSize; //Board size in horizontal direction
	private int yBoardSize; //Board size in vertical direction
	private Random randomGenerator; //Random Generator
	private int mineQuantity; //How many mines in the Board
	private int clickedCounter; //How many squares have been clicked
	private static final int MINEBOUNDS = 15; //The maximum amount of Mines that can be displayed in game

	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Constructors
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/
	
	//Constructor for MineSweeperBoard with specified size
	public MineSweeperBoard(int xSize, int ySize){
		this.xBoardSize = xSize;
		this.yBoardSize = ySize;
		this.mineSweeperGameBoard = new MineSweeperObject[xSize][ySize];
		this.flagBoard = new Flag[xSize][ySize];
		this.gameOver = false;
		this.clickedCounter=0;
		this.fillBoard();
		this.populateFlagBoard();
		this.populateGameBoard();
	}
	
	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Getters and Setters
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/
	public int getxBoardSize() {
		return xBoardSize;
	}

	public void setxBoardSize(int xBoardSize) {
		this.xBoardSize = xBoardSize;
	}

	public int getyBoardSize() {
		return yBoardSize;
	}

	public void setyBoardSize(int yBoardSize) {
		this.yBoardSize = yBoardSize;
	}

	//Setter: Sets Flag parameter in the specified position to any state
	public void setFlagUp(int xPosition, int yPosition, boolean state) {
		this.flagBoard[xPosition][yPosition].setFlagUp(state);
	}

	//Getter: Gets Proximity Number parameter in the specified position
	public int getProximityNumber(int xPosition, int yPosition){
		return this.mineSweeperGameBoard[xPosition][yPosition].getProximityNumber();
	}
	
	//Getter: Gets MineSweeperObject from the specified location of Array
	public MineSweeperObject getMineSweeperObjectFromArray(int xPosition, int yPosition) {
		return this.mineSweeperGameBoard[xPosition][yPosition];
	}
	
	public void setObjectWasClickToTrue(int xPosition, int yPosition){
		this.mineSweeperGameBoard[xPosition][yPosition].setHasBeenClicked(true);
	}
	
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	public boolean getGameOver(){
		return this.gameOver;
	}
	
	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Methods
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/
	
	// Method: Add an MineSweeperObject to the board
	public void addMineSweeperObjectToBoard(MineSweeperObject msObj, int xPosition, int yPosition) {
		this.mineSweeperGameBoard[xPosition][yPosition] = msObj;
	}

	//Method: Fills up the whole board with new MineSweeperObjects
	public void fillBoard(){
		for(int m = 0 ; m < xBoardSize; m++){
			for(int n = 0; n < yBoardSize; n++){
				this.mineSweeperGameBoard[m][n] = new MineSweeperObject();
			}
		}

	}

	//Method: Calculates Proximity Number Parameter for the specified MineSweeperObject
	public void calculateProximityNumber(int xPosition, int yPosition){
		// comienzo sin bombas
		int counterOfMines = 0;											
		
		//If we are not in a mine
		if(!(this.mineSweeperGameBoard[xPosition][yPosition].getClass() == Mine.class)){
			for(int i = xPosition - 1 ; i < xPosition + 2; i++){
				for(int j = yPosition - 1; j < yPosition + 2; j++){      //for loops para recocorrer los 8 vecinos

					//Same position of the Object we are calculating the Proximity Number for
					if(i == xPosition && j == yPosition){
						//Do nothing
					}

					//Square Out of Bounds
					else if(i<0 || j<0 || i>=this.xBoardSize || j>=this.yBoardSize){
						//Do nothing
					}

					//If it is a Mine, add to the counterOfMines property
					else if(this.mineSweeperGameBoard[i][j].getObjectColor().equals(Color.BLACK)){

						counterOfMines++;


					}

					//Doesn't fall into any condition from before
					else{
						//Do Nothing
					}
				}
			}
			
			//Assigns the proximity number to the specified MineSweeperObject
			this.mineSweeperGameBoard[xPosition][yPosition].setProximityNumber(counterOfMines);	
		}
		else{
			//Do Nothing
		}

	}

	//Method: Check if there is a mine and if there is, print Game Over and return true
	public boolean verifyMine(int xPosition, int yPosition) {
		if (this.mineSweeperGameBoard[xPosition][yPosition].getObjectColor().equals(Color.BLACK)) {
			System.out.println("Game Over");
			return true;
		}
		return false;
	}

	//Method: Check if there is a red Flag up and if there is, return true
	public boolean verifyFlagUp(int xPosition, int yPosition) {
		if (this.flagBoard[xPosition][yPosition].getFlagUp() == true) {
			return true;
		}
		return false;
	}
	
	

	// Method: Return Color of the mine that is on the specified position
	public Color getCurrentObjectColor(int xPosition, int yPosition) {
		return this.mineSweeperGameBoard[xPosition][yPosition].getObjectColor();
	}

	// Method: Add the mines in a random fashion to the board
	public void populateGameBoard() {
		System.out.println("Commencing to Populate the Board");
		randomGenerator = new Random(); 
		this.mineQuantity = Math.max(randomGenerator.nextInt(MINEBOUNDS+1), 5);
		int randomXPos;
		int randomYPos;
		int i = 0;
		do {
			randomXPos = randomGenerator.nextInt(this.xBoardSize - 1); // so it doesn't go offbounds
			randomYPos = randomGenerator.nextInt(this.yBoardSize - 1);
			//System.out.println("Mine" + i + ": " + (randomXPos) + ", " + (randomYPos));
			if(!(this.mineSweeperGameBoard[randomXPos][randomYPos].getClass() == Mine.class)){
				addMineSweeperObjectToBoard(new Mine(), randomXPos, randomYPos);
				i++;
			}
			//System.out.println(mineSweeperGameBoard[randomXPos][randomYPos]);
		}while(i < this.mineQuantity);
		for(int x = 0; x < xBoardSize; x++ ){
			for(int y = 0; y<yBoardSize; y++){
				this.calculateProximityNumber(x, y);

			}
		}
		System.out.println("Quantity of Mines: " + this.mineQuantity);
		System.out.println("Population Complete");
	}
	
	//Method: Adds Flag objects to Flag Board
		public void populateFlagBoard() {
			for (int i = 0; i < this.xBoardSize; i++) {
				for (int j = 0; j < this.yBoardSize; j++) {
					this.flagBoard[i][j] = new Flag();
				}
			}
		}

	// Method: Makes a new MineSweeperGameBoard
	public void clearGameBoard() {
		mineSweeperGameBoard = new MineSweeperObject[this.xBoardSize][this.yBoardSize];
	}
	
	//Method: Makes a new FlagBoard
	public void clearFlagBoard() {
		flagBoard = new Flag[this.xBoardSize][this.yBoardSize];

	}

	
	//Method: Restarts the whole Board and starts a new game
	public void restartBoard() {
		this.clearGameBoard();
		this.clearFlagBoard();
		this.fillBoard();
		this.populateGameBoard();
		this.populateFlagBoard();
		this.setGameOver(false);
	}
	
	//Method: Check if the square was already clicked in the specified position
	public boolean verifyIfObjectWasClicked(int xPosition, int yPosition){
		return this.mineSweeperGameBoard[xPosition][yPosition].isHasBeenClicked();
	}
	
	//Method: Checks if the user has won
	public boolean verifyIfWin(){
		if(this.clickedCounter == this.yBoardSize*this.xBoardSize - this.mineQuantity){
			return true;
		}
		return false;
	}
	
	//Method: Raises the clicked counter property by 1
	public void raiseClickedCounter(){
		this.clickedCounter++;
	}
	
	//Method: Resets the clicked counter to zero
	public void resetClickedCounter(){
		this.clickedCounter= 0;
	}
}
