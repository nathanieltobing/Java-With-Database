package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;
import main.Connect;

public class Transaction {

	private String transactionID;
	private String drinkID;
	private String buyerName;
	private int quantity;
	private static Connect con = Connect.getConnection();
	public Transaction(String transactionID, String drinkID, String buyerName, int quantity) {
		super();
		this.transactionID = transactionID;
		this.drinkID = drinkID;
		this.buyerName = buyerName;
		this.quantity = quantity;
	}
	
	public void insert() {
		
		String query = "insert into transaction values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, transactionID);
			ps.setString(2, drinkID);
			ps.setString(3, buyerName);
			ps.setInt(4, quantity);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}					
			
	}
	
	public void delete() {
		String query = "delete from transaction where TransactionID = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, transactionID);			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}			
		
	}
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getDrinkID() {
		return drinkID;
	}
	public void setDrinkID(String drinkID) {
		this.drinkID = drinkID;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}

