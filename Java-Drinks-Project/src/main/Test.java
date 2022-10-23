package main;

import java.sql.ResultSet;
import java.util.Scanner;

public class Test {
	
	private static Scanner scan = new Scanner(System.in);
	private static Connect con = Connect.getConnection();
	
	public Test() {
		// TODO Auto-generated constructor stub
		String tempID = null;
		String query = "select * from tea UNION select * from milktea";
		ResultSet result = con.executeQuery(query);
		try {
			while(result.next()) {
					tempID = result.getString("DrinkID");
					break;			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		String tempName = null;
		ResultSet test = con.executeQuery(query);
		try {
			while(test.next()) {
					tempName = test.getString("DrinkName");
					break;			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println(tempID + tempName);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test();
	}

}
