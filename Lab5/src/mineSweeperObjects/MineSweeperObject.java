package mineSweeperObjects;
import java.awt.Color;

import javax.swing.JLabel;


public class MineSweeperObject{
	private boolean hasBeenClicked = false;
	private Color objectColor;
	private int proximintyNumber;
	private JLabel text;
	private String labelString;
	//Currently the MineSweeperObject works as a mine
	public MineSweeperObject(){
		this.objectColor=Color.white;
		this.proximintyNumber = 0;
		this.setLabel();
		
		
	}

	public int getProximintyNumber() {
		return proximintyNumber;
	}

	public void setProximintyNumber(int proximintyNumber) {
		this.proximintyNumber = proximintyNumber;
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

	public  String addStringToBoard(){
		return Integer.toString(this.proximintyNumber);
		
	}
	
	public void setLabel(){
		this.labelString = addStringToBoard();
	}
	public String getLabel(){
		return Integer.toString(this.proximintyNumber);
	}
		
//Para q se vea el string en la pantalla debes crear un label a cada mineSweeperObject en blanco y luego q se cliquee entonces se muestra el string
}
//tengo q hacer un for loop que vaya dentro de cada selda y le coloque el label en base a su strignToProxNumber, lo va a colocar blanco
//para q asi cuando se pulse la tecla entonces se resalta el color blanco con el fondo gris






