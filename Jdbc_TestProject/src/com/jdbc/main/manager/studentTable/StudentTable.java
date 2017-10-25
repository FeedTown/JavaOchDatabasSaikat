package com.jdbc.main.manager.studentTable;

import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;

import com.jdbc.main.manager.ConnectToDB;
import com.jdbc.main.manager.TableManagerInterface;

/**
 * StudentTable object is a class where it handels CRUD operatons including with search operation for students information. This class implements a inferface, TableManagerInterface,
 * where it hold all CRUD and search methods.
 * 
 * <p>Created on Okt 23, 2017.<p>
 * @author Saikat
 * @version 1.0
 * 
 */
public class StudentTable implements TableManagerInterface{
	
	ConnectToDB connAndStmt = ConnectToDB.getInstance();
	private Student studentBean = new Student();
	Scanner scan = new Scanner(System.in);
	boolean useranswer = true;
	LinkedList<Student> listStudent;
	
	public StudentTable() throws SQLException {
		
	}
	
	
	@Override
	public void addToTable() throws SQLException {
		
		String temp = "Are you really want to add info to the table?";
		useranswer = userCall(temp);
		
		if(!useranswer)
			return;
		
		String sql = "INSERT INTO students(fname, lname, birthdate,teacher) VALUES (?, ?, ?, ?)";
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));
		
		
		//User inputs
		System.out.println("Add to table.");
		System.out.print("Give Students first name: ");
		studentBean.setfName(scan.nextLine());
		System.out.print("Give Students last name: ");
		studentBean.setlName(scan.nextLine());
		System.out.print("Students birthdate(yyyy-mm-dd): ");
		studentBean.setDate(Date.valueOf(scan.nextLine()));
		System.out.print("Students teacherId: ");
		studentBean.setTeacherId(scan.nextInt());
		
		connAndStmt.getPreparedStmt().setString(1, studentBean.getfName());
		connAndStmt.getPreparedStmt().setString(2, studentBean.getlName());
		connAndStmt.getPreparedStmt().setDate(3, studentBean.getBirthDate());
		connAndStmt.getPreparedStmt().setInt(4, studentBean.getTeacherId());
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new student was inserted successfully!\n");
		}
		
		connAndStmt.getConn().close();
		
	}
	
	@Override
	public void updateTable() throws SQLException {
		
		String temp = "Update table?";
		
		useranswer = userCall(temp);
		
		if(!useranswer)
			return;
		
		System.out.println("Update Table Values.\nDown here is the current Student table");
		showTable();
		
		
		String sql = "Update students set fname=?, lname=?, birthdate=?,teacher=? where id=?";
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));
		
		//askForUpdate();
		
		//User input
		System.out.print("Where do you want to update students info?\nGive Students id: ");
		studentBean.setId(Integer.parseInt(scan.nextLine()));
		//scan.nextLine();
		System.out.println("Give Students first name: ");
		studentBean.setfName(scan.nextLine());
		System.out.println("Give Students last name: ");
		studentBean.setlName(scan.nextLine());
		System.out.println("Students birthdate(yyyy-mm-dd): ");
		studentBean.setDate(Date.valueOf(scan.nextLine()));
		System.out.print("Students teacherId: ");
		studentBean.setTeacherId(scan.nextInt());
		
		connAndStmt.getPreparedStmt().setString(1, studentBean.getfName());
		connAndStmt.getPreparedStmt().setString(2, studentBean.getlName());
		connAndStmt.getPreparedStmt().setDate(3, studentBean.getBirthDate());
		connAndStmt.getPreparedStmt().setInt(4, studentBean.getTeacherId());
		connAndStmt.getPreparedStmt().setInt(5, studentBean.getId());
		
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new student was inserted successfully!\n");
		}
		
		
	}

	private boolean userCall(String temp) {
		System.out.println(temp + "\n1. Yes.\n2. No");
		int answer = scan.nextInt();
		scan.nextLine();
		
		if(answer == 1)
			return true;
		else
			return false;
		
	}

	@Override
	public void removeTable() throws SQLException {
		
		String temp = "Are you really want to delete info from the table?";
		useranswer = userCall(temp);
		
		if(!useranswer)
			return;
		
		System.out.println("Delete from table.\nDown here is the current Student table");
		showTable();
		
		
		
		String sql = "DELETE FROM students WHERE id=?";
		
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));
		
		//statement = connAndStmt.getConn().prepareStatement(sql);
		
		System.out.print("What do you want to delete from the table?\nGive Students id: ");
		studentBean.setId(scan.nextInt());
		connAndStmt.getPreparedStmt().setInt(1, studentBean.getId());
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Your have deleted a row from the table successfully\n!");
		}
		
	}
	
	@Override
	/*public void showTable() throws SQLException 
	{
		System.out.println("+----+-------------+-------------+-------------+----------+");
		System.out.println("| Id |   FirstName |   LastName  |  Birthdate  |  Teacher |");
		System.out.println("+----+-------------+-------------+-------------+----------+");
		
		//rs = stmt.executeQuery("SELECT * FROM students");
		//rs = connAndStmt.getStmt().executeQuery("SELECT * FROM students");
		
		connAndStmt.setRs(connAndStmt.getStmt().executeQuery("SELECT * FROM students"));
		
		while(connAndStmt.getRs().next())
		{
			/*sTest = new StudentTest(rs.getInt("id"),rs.getString("fname"),rs.getString("lname"),
					rs.getString("birthdate"),rs.getInt("teacher"));
			
			studentBean.setId(connAndStmt.getRs().getInt("id"));
			studentBean.setfName(connAndStmt.getRs().getString("fname"));
			studentBean.setlName(connAndStmt.getRs().getString("lname"));
			studentBean.setDate(connAndStmt.getRs().getDate("birthdate"));
			studentBean.setTeacherId(connAndStmt.getRs().getInt("teacher"));
			
			
			System.out.format("| %2d |%12s |%12s |%12s |%5d     |\n",studentBean.getId(),studentBean.getfName(),studentBean.getlName(),studentBean.getDate(),studentBean.getTeacherId());
			System.out.println("+----+-------------+-------------+-------------+----------+");
		}
		
		connAndStmt.getRs().close();
		
	}*/
	
	public LinkedList<Student> showTable() throws SQLException
	{
		listStudent = new LinkedList<>();
		connAndStmt.setRs(connAndStmt.getStmt().executeQuery("SELECT * FROM students"));
		
		while (connAndStmt.getRs().next())
		{
			Student tempStudent = convertRowToStudent(connAndStmt.getRs());
			listStudent.add(tempStudent);
		}
		
		return listStudent;	
	}
	
	public LinkedList<Student> searchTable(String userInput) throws SQLException
	{
		listStudent = new LinkedList<>();
		
		userInput = "%" + userInput + "%";
		
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement("select * from students where lname like ?"));
		connAndStmt.getPreparedStmt().setString(1, userInput);
		
		connAndStmt.setRs(connAndStmt.getPreparedStmt().executeQuery());
		while (connAndStmt.getRs().next())
		{
			Student tempStudent = convertRowToStudent(connAndStmt.getRs());
			listStudent.add(tempStudent);
		}
		
		return listStudent;
	}
	
	/**
	 * This method converts a row of subject to a Subject Object then returns the Subject Object. This method takes a ResultSet as parameter, where the ResultSet 
	 * holds information of Subject row.   
	 * 
	 * @param rs rs(ResultSet) to set.
	 * @return tempStudent as Student Object.
	 * @throws SQLException
	 */
	
	private Student convertRowToStudent(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("id");
		String firstName = rs.getString("fname");
		String lastName = rs.getString("lname");
		Date birthdate = rs.getDate("birthdate");
		int teacherId = rs.getInt("teacher");
		
		Student tempStudent = new Student(id, firstName, lastName, birthdate, teacherId);
		
		return tempStudent;
	}
	
	
}
