package model;

import java.sql.ResultSet;
import main.Connect;

public class Tea extends Drinks{
	
	private String sugarType;
	private static Connect con = Connect.getConnection();

	public Tea(String drinkID, String drinkName, String drinkType, int drinkprice,String sugarType) {
		super(drinkID, drinkName, drinkType, drinkprice);
		this.sugarType = sugarType;
	}

	public String getSugarType() {
		return sugarType;
	}

	public void setSugarType(String sugarType) {
		this.sugarType = sugarType;
	}

	@Override
	public int showDetails(int nomor) {
		String query = "select * from tea";
		ResultSet result = con.executeQuery(query);
		
		try {
			while(result.next()) {
				System.out.println(nomor + "." +	"|" + result.getString("DrinkID") +	"|" + result.getString("DrinkName")
				+	"|" + result.getString("DrinkType") +	"|" + result.getInt("DrinkPrice") +	"|" + result.getString("SugarType")
				+	"|" + "-");			
				nomor ++;
			} 
		}	catch (Exception e) {
			
			}
		
		
		return nomor;
		
	}
	
	
	

	

}

