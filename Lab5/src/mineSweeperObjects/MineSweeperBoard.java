package mineSweeperObjects;
import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

public class MineSweeperBoard {

	public MineSweeperObject[][] mineSweeperGameBoard;
	public int xBoardSize;
	public int yBoardSize;
	public Random randomGenerator;
	public int mineQuantity;
	public static final int MINEBOUNDS = 6;


	//Constructor for MineSweeperBoard
	public MineSweeperBoard(int xSize, int ySize){
		this.xBoardSize = xSize;
		this.yBoardSize = ySize;
		this.mineSweeperGameBoard = new MineSweeperObject[xSize][ySize];
		this.fillBoard();
		this.populateBoard();
	}
	//Method: Add an MineSweeperObject to the board
	public void addMineSweeperObjectToBoard(MineSweeperObject msObj, int xPosition, int yPosition){
		this.mineSweeperGameBoard[xPosition][yPosition] = msObj;
	}
	public void fillBoard(){
		for(int m = 0 ; m < xBoardSize; m++){
			for(int n = 0; n < yBoardSize; n++){
				this.mineSweeperGameBoard[m][n] = new MineSweeperObject();
			}
		}

	}
	public void proximityNumber(int xPosition, int yPosition){ //constructor para sacar los numeros de mimnas cercanas
		int counterOfMines = 0;											// comienzo sin bombas
		for(int i = xPosition - 1 ; i < xPosition + 2; i++){
			for(int j = yPosition - 1; j < yPosition + 2; j++){      //for loops para recocorrer los 8 vecinos
				
				if(i == xPosition && j == yPosition){
					//Do nothing
					
				}
				else if(i<1 || j<1 || i>=this.xBoardSize || j>=this.yBoardSize-1){
					//Do nothing
					
				}
				else if(this.mineSweeperGameBoard[i][j].getObjectColor().equals(Color.BLACK)){
					counterOfMines++;
					
				}
				else{
					
				}
			}
		}
		if(!this.mineSweeperGameBoard[xPosition][yPosition].getObjectColor().equals(Color.BLACK)){
			this.mineSweeperGameBoard[xPosition][yPosition] = new ProximityNumber(counterOfMines);
			System.out.println("Counter of Mines in ("+xPosition+", "+yPosition + ") = "  + counterOfMines);
		}
	
	}
	//Method: Check if there is a mine and if there is, print Game Over and return true
	public boolean verifyMine(int xPosition, int yPosition){
		if(this.mineSweeperGameBoard[xPosition][yPosition].getClass().toString().equals("Mine")){
			System.out.println("Game Over");
			return true;
		}
		return false;
	}

	//Method: Return Color of the mine that is on the specified position
	public Color giveCurrentMineColor(int xPosition, int yPosition){
		return this.mineSweeperGameBoard[xPosition][yPosition].getObjectColor(); 
	}
	//Method: Makes a new MineSweeperGameBoard
	public void clearBoard(){
		mineSweeperGameBoard = new MineSweeperObject[this.xBoardSize][this.yBoardSize];
		this.fillBoard();
		this.populateBoard();
	}
	//Method: Add the mines in a random fashion to the board
	public void populateBoard(){
		System.out.println("Commencing to Populate the Board");
		randomGenerator = new Random(); 
		this.mineQuantity = Math.max(randomGenerator.nextInt(MINEBOUNDS+1), 1);
	
		int randomXPos;
		int randomYPos;
		for(int i = 0; i<this.mineQuantity;i++){
			randomXPos = Math.max(randomGenerator.nextInt(this.xBoardSize-1), 1); // -1 so it doesn't go offbounds
			randomYPos = Math.max(randomGenerator.nextInt(this.yBoardSize-1), 1);
			System.out.println("Mine" + i + ": " + randomXPos +", " + randomYPos);
			addMineSweeperObjectToBoard(new Mine(), randomXPos, randomYPos);
			System.out.println(mineSweeperGameBoard[randomXPos][randomYPos]);
		}
		for(int x = 1; x < xBoardSize; x++ ){
			for(int y = 1; y<yBoardSize; y++){
				this.proximityNumber(x, y);

			}
		}
	}

	public void restartBoard(){
		this.clearBoard();
		this.populateBoard();
	}



}
