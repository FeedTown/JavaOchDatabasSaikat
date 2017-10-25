package com.jdbc.main.manager.subjectTable;

import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;

import com.jdbc.main.manager.ConnectToDB;
import com.jdbc.main.manager.TableManagerInterface;

/**
 * SubjectTable object is a class where it handels CRUD operatons including with search operation for subject information. This class implements a inferface, TableManagerInterface,
 * where it hold all CRUD and search methods.
 * 
 * <p>Created on Okt 23, 2017.<p>
 * @author Saikat
 * @version 1.0
 * 
 */

public class SubjectTable implements TableManagerInterface{
	
	ConnectToDB connAndStmt = ConnectToDB.getInstance();
	private Subject subjectBean = new Subject();
	private Scanner scan = new Scanner(System.in);
	LinkedList<Subject> listSubject;
	
	public SubjectTable() throws SQLException
	{
		super();
	}
	
	
	@Override 
	public void addToTable() throws SQLException {
		
		String sql = "INSERT INTO subject (subjectName) VALUES (?)";
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));
		//statement = connAndStmt.getConn().prepareStatement(sql);
		
		System.out.println("Add to table.");
		System.out.println("Give subjectname: ");
		subjectBean.setName(scan.next());
		connAndStmt.getPreparedStmt().setString(1, subjectBean.getName());
		
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new subject was inserted successfully!");
		}
		
	}
	
	
	@Override
	public void updateTable() throws SQLException {
		System.out.println("Update Table Values.\nDown here is the current subject table");
		showTable();
		
		String sql = "UPDATE subject set subjectName=? where subjectid=?";
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));
		
		System.out.println("Where do you want update teacher info?\nGive subject id: ");
		subjectBean.setId(Integer.parseInt(scan.nextLine()));
		//sub.setId(scan.nextInt());
		scan.nextLine();
		
		System.out.println("Write a new subjectname: ");
		subjectBean.setName(scan.nextLine());
		
		connAndStmt.getPreparedStmt().setString(1, subjectBean.getName());
		connAndStmt.getPreparedStmt().setInt(2, subjectBean.getId());
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new subject was inserted successfully!");
		}
		
	}
	
	
	@Override
	public void removeTable() throws SQLException {
		System.out.println("Delete from table.\nDown here is the current subject table");
		showTable();
		
		String sql = "DELETE FROM subject WHERE subjectId=?";
		//statement = connAndStmt.getConn().prepareStatement(sql);
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));

		System.out.print("What do you want to delete from the table?\nGive the subject id: ");
		subjectBean.setId(scan.nextInt());
		
		connAndStmt.getPreparedStmt().setInt(1, subjectBean.getId());
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Your have deleted a row from the table successfully!");
		}
		
		connAndStmt.getPreparedStmt().close();
		
	}
	
	/*
	@Override 
	public void showTable() throws SQLException {
		System.out.println("+----+------------------+\n"
				+ "| Id |    Subjectname   |\n"
				+ "+----+------------------+");
		String sql = "SELECT * FROM subject";
		connAndStmt.setRs(connAndStmt.getStmt().executeQuery(sql));
		//rs = connAndStmt.getStmt().executeQuery("SELECT * FROM subject");
		
		while(connAndStmt.getRs().next())
		{
			sub.setId(connAndStmt.getRs().getInt("id"));
			sub.setName(connAndStmt.getRs().getString("subjectName"));
			
			System.out.format("| %2d |  %15s |\n",sub.getId(), sub.getName());
			System.out.println("+----+------------------+");
		}
		
		connAndStmt.getRs().close();
	}*/
	
	@Override
	public LinkedList<Subject> showTable() throws SQLException {
		listSubject = new LinkedList<>();
		connAndStmt.setRs(connAndStmt.getStmt().executeQuery("SELECT * FROM subject"));
		
		while (connAndStmt.getRs().next())
		{
			Subject tempSubject = convertRowToSubject(connAndStmt.getRs());
			listSubject.add(tempSubject);
		}
		
		return listSubject;	
	}
	
	public LinkedList<Subject> searchTable(String userInput) throws SQLException
	{
		
		LinkedList<Subject> sList = new LinkedList<>();
		
		userInput = "%" + userInput + "%";
		
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement("select * from subject where subjectName like ?"));
		connAndStmt.getPreparedStmt().setString(1, userInput);
		
		connAndStmt.setRs(connAndStmt.getPreparedStmt().executeQuery());
		while (connAndStmt.getRs().next())
		{
			Subject tempSubject = convertRowToSubject(connAndStmt.getRs());
			sList.add(tempSubject);
		}
		
		return sList;
	}

	
	/**
	 * This method converts a row of subject to a Subject Object then returns the Subject Object. This method takes a ResultSet as parameter, where the ResultSet 
	 * holds information of Subject row.   
	 * 
	 * @param rs rs(ResultSet) to set.
	 * @return tempSubject as Subject Object.
	 * @throws SQLException
	 */
	private Subject convertRowToSubject(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("id");
		String subjectName = rs.getString("subjectName");
	
		Subject tempSubject = new Subject(id, subjectName);
		
		return tempSubject;
	}


}
