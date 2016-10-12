import java.awt.Color;


public class MineSweeperObject {
	private boolean hasBeenClicked = false;
	private Color objectColor;
	private int xPosition;
	private int yPosition;
	
	//Currently the MineSweeperObject works as a mine
	MineSweeperObject (){
		this.objectColor=Color.BLACK;
	}
	
	//Verifies if the Object has been clicked
	public boolean isHasBeenClicked() {
		return hasBeenClicked;
	}

	//Getters and Setters
	
	public void setHasBeenClicked(boolean hasBeenClicked) {
		this.hasBeenClicked = hasBeenClicked;
	}


	public Color getObjectColor() {
		return objectColor;
	}

	
	public void setObjectColor(Color objectColor) {
		this.objectColor = objectColor;
	}
	
//	public int getXPosition( int xPosition){
//		return xPosition;
//	}
//	
//	
//	public void setXPosition(int xPosition){
//		this.xPosition = xPosition;						AUN NO SE SI LOS NECESITO
//	}
//	
//	
//	public int getYPosition(int yPosition){
//		return yPosition;
//	}
//	
//	
//	public void setYPosition(int yPosition){
//		this.yPosition = yPosition;
//	}
//	
	
}
