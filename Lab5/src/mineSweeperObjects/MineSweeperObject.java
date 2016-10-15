package mineSweeperObjects;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;


public class MineSweeperObject{
	private boolean hasBeenClicked = false;
	private Color objectColor;
	private int proximityNumber;
	public JLabel proximityLabel;

	//Currently the MineSweeperObject works as a mine

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

	public String getLabel(){
		return Integer.toString(this.proximityNumber);
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
	public void initLabel(int xPosition, int yPosition){
		proximityLabel = new JLabel(this.getLabel());
		proximityLabel.setSize(30,30);
		proximityLabel.setLocation(30 + xPosition*30, 25 + yPosition*30);
		proximityLabel.setFont(new Font(proximityLabel.getName(),Font.PLAIN,30));
		proximityLabel.setForeground(Color.white);
	}

}






