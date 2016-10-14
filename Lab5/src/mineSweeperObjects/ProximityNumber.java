package mineSweeperObjects;

public class ProximityNumber extends MineSweeperObject {

	private int proxNumber;
	private String proxString;


	public ProximityNumber( int proxNumber)
	{
		this.proxNumber=proxNumber;
	}
	public int getProxNumber(){
		return this.proxNumber;
	}
	public String proxString(){
		String proxString = Integer.toString(proxNumber);
		return proxString;
		
	}

}


