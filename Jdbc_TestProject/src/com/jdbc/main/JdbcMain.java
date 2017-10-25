package com.jdbc.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.main.manager.JdbcTableManager;

/**
 * 
 * JdbcMain is the main class for this program with the main method.
 * <p>Created on Okt 23, 2017<p>
 * @author Saikat Talukder
 * @version 1.0
 *
 */

public class JdbcMain {
	
	static JdbcTableManager tManager;
	
	public static void main(String[] args) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		boolean dBRunning = true;
		System.out.println("Loggin into Mysql database.");
		System.out.println("Connecting to jdbcprojekt database.");
		System.out.println("...\n...\n...\n");
		System.out.println("Your are now connected to jdbcprojekt database.\n");
		while(dBRunning)
		{
			dBRunning = chooseTable(scan);
		}
		
		scan.close();
		System.out.println("You are now disconnected from jdbcprojekt database");

	}
	
	/**
	 * This method is a boolean method where it returns true or false depending on users choice. chooseTable() is where user can choice a table to work with. This method has boolean variable called running which is true as long as 
	 * user choice not to close the program where running becomes false and exits the program.
	 * 
	 * @param scan as Scanner to used in this class
	 * @return <p>returns true if userChoice is 1, 2, 3 or userChoice > 4<p> 
	 * <p>return false if userChoice is 4<p>						   
	 * 
	 * @throws SQLException
	 */
	private static boolean chooseTable(Scanner scan) throws SQLException {
		
		boolean running = true;
		
		System.out.println("Choose a table to work with:\n1. "
				+ "Students\n2. Subject\n3. Teachers\n4. Close the program\n");
		
		int userChoice = scan.nextInt();
		scan.nextLine();
		if(userChoice == 4)
		{
			running = false;
		}
		else if(userChoice > 4)
		{ 
			System.out.println("Invalid table choice. Try Again.");
		}
		else
		{
			tManager = new JdbcTableManager(userChoice, scan);
			tManager.crudandSearchMenuForTable();
		}
			
		
		return running;
		
	}

}
