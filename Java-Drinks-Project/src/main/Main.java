package main;

import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;
import model.Drinks;
import model.MilkTea;
import model.Tea;
import model.Transaction;

public class Main {
	
	private static Scanner scan = new Scanner(System.in);
	private static Connect con = Connect.getConnection();
	
	public Main() {
		boolean run = true;
		
		while(run) {
			menu();
			int choice = scan.nextInt();
			scan.nextLine();
			
			if(choice == 1) {
				buyTea();
			}
			else if(choice == 2) {
				viewTransaction();
				scan.nextLine();
			}
			else if(choice == 3) {
				deleteTransaction();
				scan.nextLine();
			}
			else if(choice == 4) {
				run = false;
				scan.close();
			}
		}
	}
	
	public void menu() {
		System.out.println("Ngeteh Yuk !! ");
		System.out.println("==============");
		System.out.println("1. Buy Tea");
		System.out.println("2. View Transaction");
		System.out.println("3. Delete Transaction");
		System.out.println("4. Exit");
		System.out.print(">>");
	}
	
	public void viewTea() {
		int nomor = 1;
		Drinks tea = new Tea("","","",0,"");
		Drinks milktea = new MilkTea("","","",0,"");
		nomor = tea.showDetails(nomor);
		nomor = milktea.showDetails(nomor);
	}
	
	public void viewTransaction() {
		String query = "(select * from transaction tr JOIN tea t on tr.DrinkID = t.DrinkID) UNION"
				+ "(select * from transaction tr JOIN milktea mt on tr.DrinkID = mt.DrinkID)";
				
		ResultSet result = con.executeQuery(query);
		boolean isEmpty = true;
		int index = 1;
		try {
			while(result.next()) {
				
				int tax = 0,totalPrice = 0;
				if(result.getString("DrinkType").equals("Tea")) {
					tax = 2000;
				}
				else if(result.getString("DrinkType").equals("Milk Tea")) {
					tax = 3500;
				}
				totalPrice = (result.getInt("DrinkPrice")* result.getInt("Quantity")) + tax;
				
				System.out.println(index + " " + result.getString("TransactionID") + " " + result.getString("BuyerName") +
						" " + result.getString("DrinkName") + " " + result.getInt("DrinkPrice") + " " + result.getInt("Quantity") +
						" " + tax + " " + totalPrice);
				
				index ++;
				isEmpty = false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(isEmpty) {
			System.out.println("There is no transaction");
			scan.nextLine();
		}
	}
	
	public void deleteTransaction() {
		if(isEmpty()) {
			System.out.println("There is no transaction...");
			System.out.println("Press any key to continue...");
			
		}
		else {
			viewTransaction();
			int jumlahTransaction = jumlahTransaction();		
			int choice;
			do {
				System.out.println("Input the number of index you want to delete[1 .. " + jumlahTransaction + "] : ");
				choice = scan.nextInt();
				scan.nextLine();
				
			}while(choice < 1 || choice >jumlahTransaction);
			
			String query = "(select * from transaction tr JOIN tea t on tr.DrinkID = t.DrinkID) UNION"
					+ "(select * from transaction tr JOIN milktea mt on tr.DrinkID = mt.DrinkID)";
			ResultSet temp = con.executeQuery(query);
			int index =1;
			String tempID = null;
			try {
				while(temp.next()) {
					if(index == choice) {
						tempID = temp.getString("TransactionID");
						break;
					}
					index++;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Transaction tr = new Transaction(tempID,"","",0);
			tr.delete();
			
			System.out.println("Successfully delete transaction !");
			System.out.println("Press any key to continue..");
		}
		
	}
	
	public int jumlahTransaction() {
		String query = "(select * from transaction tr JOIN tea t on tr.DrinkID = t.DrinkID) UNION"
				+ "(select * from transaction tr JOIN milktea mt on tr.DrinkID = mt.DrinkID)";
		ResultSet temp = con.executeQuery(query);
		int jumlahTransaction = 0;
		try {
			while(temp.next()) {
				jumlahTransaction ++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return jumlahTransaction;
	}
	
	 boolean isEmpty() {
		String query = "(select * from transaction tr JOIN tea t on tr.DrinkID = t.DrinkID) UNION"
				+ "(select * from transaction tr JOIN milktea mt on tr.DrinkID = mt.DrinkID)";
		
		ResultSet result = con.executeQuery(query);
		boolean isEmpty = true;
		try {
			while(result.next()) {
				isEmpty = false;
				break;
			}
		}catch(Exception e) {
			
		}
		
		if(isEmpty) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void buyTea() {
		int choice,jumlahDrink=0,tempPrice = 0;
		String tempID = null,tempType = null;
		Scanner scan = new Scanner(System.in);
		String tempDrinkName = null;
		viewTea();
		String query = "select * from tea UNION select * from milktea";
		ResultSet temp = con.executeQuery(query);
		int jumlahDrinks = 0;
		try {
			while(temp.next()) {
				jumlahDrinks ++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		//System.out.println(jumlahDrinks);
		do {
			System.out.print("Input the number of index you want to buy[1..." +jumlahDrinks + "] : ");
			choice = scan.nextInt();
			scan.nextLine();
			
		}while(choice < 1 || choice >jumlahDrinks);
		
		int index = 1;
		ResultSet result = con.executeQuery(query);
		try {
			while(result.next()) {
				if(index == choice) {
					tempID = result.getString("DrinkID");
					tempType = result.getString("DrinkType");
					tempPrice = result.getInt("DrinkPrice");
					tempDrinkName = result.getString("DrinkName");
					break;
				}
				
				
				index ++;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		//System.out.println(tempID);
		
			
		
		int quantity;
		
		do {
			System.out.print("Input Quantity [>0] : ");
			quantity = scan.nextInt();
			scan.nextLine();
		}while(quantity <1);
		
		String name;
		do {
			System.out.print("Input your name [must be 2 words] :" );
			name = scan.nextLine();
		}while(!name.contains(" "));
		
		int tax = 0,totalPrice;
		
		if(tempType.equalsIgnoreCase("Tea")) {
			tax = 2000;		
		}
		else if(tempType.equalsIgnoreCase("Milk Tea")) {
			tax = 3500;		
		}
		totalPrice = (tempPrice * quantity) + tax;
		
		Random rand = new Random();
		String transactionID = "TR" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
	
		System.out.println("TransactionID : " + transactionID);
		System.out.println("Buyer Name : " + name);
		System.out.println("Drink Name : " + tempDrinkName);
		System.out.println("Drink Quantity : " + quantity);
		System.out.println("Tax : " + tax);
		System.out.println("Total Price : " + totalPrice);
		
		Transaction trans = new Transaction(transactionID,tempID,name,quantity);
		trans.insert();
		
		System.out.println("Successfully Inserted new transaction !");
		System.out.println("Press any key to continue");
		scan.nextLine();
	}
	
	public static void main(String[] args) {
		new Main();

	}

}
