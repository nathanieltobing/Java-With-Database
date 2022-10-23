package model;

public abstract class Drinks {

	private String drinkID;
	private String drinkName;
	private String drinkType;
	private int drinkprice;
	
	public Drinks(String drinkID, String drinkName, String drinkType, int drinkprice) {
		super();
		this.drinkID = drinkID;
		this.drinkName = drinkName;
		this.drinkType = drinkType;
		this.drinkprice = drinkprice;
	}
	
	public abstract int showDetails(int nomor) ;
		
	
	
	public String getDrinkID() {
		return drinkID;
	}
	public void setDrinkID(String drinkID) {
		this.drinkID = drinkID;
	}
	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	public String getDrinkType() {
		return drinkType;
	}
	public void setDrinkType(String drinkType) {
		this.drinkType = drinkType;
	}
	public int getDrinkprice() {
		return drinkprice;
	}
	public void setDrinkprice(int drinkprice) {
		this.drinkprice = drinkprice;
	}
	
	

}
