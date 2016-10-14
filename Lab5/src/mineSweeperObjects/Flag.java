package mineSweeperObjects;

import java.awt.Color;

public class Flag extends MineSweeperObject{
	public boolean flagUp;
	Flag(){
		this.setObjectColor(Color.RED);
		this.flagUp = false;
	}

	public void setFlagUp(boolean flagUp){
		this.flagUp = flagUp;
	}
	public boolean getFlagUp(){
		return this.flagUp;
	}
}
