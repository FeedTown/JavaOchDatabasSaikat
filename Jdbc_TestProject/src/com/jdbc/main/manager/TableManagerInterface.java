package com.jdbc.main.manager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * This is a interface where it holds all the CRUD operation methods and a search operation method.
 * 
 * <p>Created on Okt 23, 2017<p>
 * 
 * @author Saikat Talukder
 * @version 1.0
 *
 */

public interface TableManagerInterface {
	
	/**
	 * This method lets the user to add a new row with info which is specified by users datebase table. 
	 * @throws SQLException that provides information on a database access error or other errors.
	 */
	public void addToTable() throws SQLException;
	
	
	/**
	 * This method lets the user to update info to the table on a existing row from users choice.
	 * @throws SQLException that provides information on a database access error or other errors.
	 */
	public void updateTable() throws SQLException;
	
	/**
	 * This method lets the user to remove row on the table from users choice.
	 * @throws SQLException that provides information on a database access error or other errors.
	 */
	public void removeTable() throws SQLException;
	
	/**
	 * This method shows a table that are specified by the user. Put each row from the table to a LinkedList.
	 * @return: a LinkedList representation of the object.
	 * @throws SQLException that provides information on a database access error or other errors.
	 */
	public LinkedList<?> showTable() throws SQLException;
	
	/**
	 * This method lets the user to search through the table by userInput.
	 * @param userInput userInput is String varible.
	 * @return a LinkedList representation of the object.
	 * @throws SQLException that provides information on a database access error or other errors.
	 */
	public LinkedList<?> searchTable(String userInput) throws SQLException;
	
	
}
