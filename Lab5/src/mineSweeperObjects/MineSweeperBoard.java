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
	public static final int MINEBOUNDS = 6;

	// Constructor for MineSweeperBoard
	public MineSweeperBoard(int xSize, int ySize) {
		this.xBoardSize = xSize;
		this.yBoardSize = ySize;
		this.mineSweeperGameBoard = new MineSweeperObject[xSize][ySize];
		this.flagBoard = new Flag[xSize][ySize];
		this.populateFlagBoard();
		this.populateGameBoard();
	}

	// Method: Add an MineSweeperObject to the board
	public void addMineSweeperObjectToBoard(MineSweeperObject msObj, int xPosition, int yPosition) {
		this.mineSweeperGameBoard[xPosition][yPosition] = msObj;
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
		this.mineQuantity = Math.max(randomGenerator.nextInt(MINEBOUNDS + 1), 1);
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
			System.out.println("Mine" + i + ": " + (randomXPos+1) + ", " + (randomYPos+1));
			addMineSweeperObjectToBoard(new Mine(), randomXPos, randomYPos);
			System.out.println(mineSweeperGameBoard[randomXPos][randomYPos]);
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
	
	public void restartBoard() {
		this.clearGameBoard();
		this.clearFlagBoard();
		this.populateGameBoard();
		this.populateFlagBoard();
	}

	

}
