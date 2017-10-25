package com.jdbc.main.manager;

import java.sql.*;
import java.util.Scanner;

import com.jdbc.main.manager.studentTable.StudentTable;
import com.jdbc.main.manager.subjectTable.SubjectTable;
import com.jdbc.main.manager.teacherTable.TeacherTable;

/**
 * This class is table manager class where it handles what tables to use in the manager() method. Then it has crudandSearchMenuForTable() method 
 * where it has the menu for the CRUD and search operation.
 * 
 * <p>Created on Okt 23, 2017<p>
 * @author Saikat Talukder
 * @version 1.0
 *
 */
public class JdbcTableManager {
	
	int userChoice;
	int userTableChoice;
	Scanner scan;
	String tempString;
	
	public JdbcTableManager()throws SQLException
	{
		
	}
	
	/**
	 * Constructor and initialize userTableChoice as Integer and scan as Scanner.
	 * 
	 * @param userTableChoice userTableChoice to set as Integer. userTableChoice holds value for the table to use.
	 * @param scan scan to set as Scanner.
	 * @throws SQLException that provides information on a database access error or other errors.
	 */
	public JdbcTableManager(int userTableChoice, Scanner scan) throws SQLException {
		this.userTableChoice = userTableChoice;
		this.scan = scan;
		manager();

		
	}
	
	
	/**
	 * This method is a menu for all CRUD and search operation where user choice what to do. It has while loop with a Boolean variable called running which is true as long as user choice
	 * not to exit the table. If user choice exit table then running becomes false which breaks the while loop. 
	 *  @throws SQLException that provides information on a database access error or other errors.
	 */
	public void crudandSearchMenuForTable() throws SQLException
	{
		boolean running = true;
		while(running)
		{
			System.out.println("\n1. Add to table.\n2. Update table values.\n3. Delete from table."
					+ "\n4. Show table\n5. Search in table(by last name).\n6. Exit from this table.\n");
		
			userChoice = scan.nextInt();
			scan.nextLine();
			if(userChoice > 6)
			{
				System.out.println("Invalid input. Try Again.");
				//continue;
			}
			else if(userChoice == 1)
			{
				//addToTable();
				manager().addToTable();
			}
			else if(userChoice == 2)
			{
				manager().updateTable();
			}
			else if(userChoice == 3)
			{
				manager().removeTable();
			}
			else if(userChoice == 4)
			{
				String result = manager().showTable().toString();
				printOutTableRows(result);
			}
			else if(userChoice == 5)
			{
				String getSearchWord;
				System.out.print(tempString + " Give search word: ");
				getSearchWord = scan.nextLine();
				
				if(getSearchWord == "")
				{
					System.out.println("You must write something to search. Try Again");
					continue;
				}
				
				String result = manager().searchTable(getSearchWord).toString();
				printOutTableRows(result);
					
			}
			else
			{
				running = false;
			}
		}
		
	}
	
	/**
	 * This method is method where it prints out tables rows.
	 * @param result is String that holds table rows
	 */
	private void printOutTableRows(String result) {
		
		System.out.println(result.substring(1, result.length()-1));
	}

	/**
	 * This method is where userTableChoice in use. Here decides what tables going to be used by the userTableChoice variable and return object of that table class.
	 * @return tempTable as StudentTable or SubjectTable or TeacherTable which is decided by userTableChoice.
	 * @throws SQLException
	 */
	private TableManagerInterface manager() throws SQLException
	{
		TableManagerInterface tempTable = null;
		if(userTableChoice == 1)
		{
			tempTable = new StudentTable();
			tempString = "Search by last name.";
		}
		else if(userTableChoice == 2)
		{
			tempTable = new SubjectTable();
			tempString = "Search by subject name.";
		}
		else
		{
			tempString = "Search by last name.";
			tempTable = new TeacherTable();
		}
		
		return tempTable;
	}
	
	

}
