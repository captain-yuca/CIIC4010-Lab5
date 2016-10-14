package mineSweeperObjects;
import java.awt.Color;


public class MineSweeperObject {
	private boolean hasBeenClicked = false;
	private Color objectColor;
	private int proximityNumber;

	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Constructors
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/

	MineSweeperObject (){
		this.objectColor=Color.WHITE;
		this.proximityNumber=0;
	}
	MineSweeperObject(int proximityNumber){
		this.objectColor=Color.WHITE;
		this.proximityNumber=proximityNumber;
	}

	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Getters and Setters
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/

	public void setHasBeenClicked(boolean hasBeenClicked) {
		this.hasBeenClicked = hasBeenClicked;
	}


	public Color getObjectColor() {
		return objectColor;
	}


	public void setObjectColor(Color objectColor) {
		this.objectColor = objectColor;
	}

	public int getProximityNumber(){
		return this.proximityNumber;
	}
	public void setProximityNumber(int proximityNumber){
		this.proximityNumber=proximityNumber;
	}

	/*+----------------------------------------------------------------------
	 ||
	 ||
	 ||	 Methods
	 ||
	 ||
	 ++-----------------------------------------------------------------------*/

	//Verifies if the Object has been clicked
	public boolean isHasBeenClicked() {
		return hasBeenClicked;
	}





}
