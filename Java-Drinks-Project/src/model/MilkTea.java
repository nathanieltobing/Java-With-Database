package model;

import java.sql.ResultSet;
import main.Connect;

public class MilkTea extends Drinks{
	
	private String milkType;
	private static Connect con = Connect.getConnection();

	public MilkTea(String drinkID, String drinkName, String drinkType, int drinkprice,String milkType) {
		super(drinkID, drinkName, drinkType, drinkprice);
		this.milkType = milkType;
	}

	public String getMilkType() {
		return milkType;
	}

	public void setMilkType(String milkType) {
		this.milkType = milkType;
	}

	@Override
	public int showDetails(int nomor) {
		String query = "select * from milktea";
		ResultSet result = con.executeQuery(query);
		
		try {
			while(result.next()) {
				System.out.println(nomor + "." +	"|" + result.getString("DrinkID") +	"|" + result.getString("DrinkName")
				+	"|" + result.getString("DrinkType") +	"|" + result.getInt("DrinkPrice") +	"|" + "-"
				+	"|" + result.getString("MilkType"));			
				nomor ++;
			} 
		}	catch (Exception e) {
			
			}
		
		
		return nomor;
		
	}
	
	
	
	
}

