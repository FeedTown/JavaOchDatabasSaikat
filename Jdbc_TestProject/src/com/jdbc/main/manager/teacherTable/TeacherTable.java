package com.jdbc.main.manager.teacherTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

import com.jdbc.main.manager.ConnectToDB;
import com.jdbc.main.manager.TableManagerInterface;

/**
 * TeacherTable object is a class where it handels CRUD operatons including with search operation for teachers information. This class implements a inferface, TableManagerInterface,
 * where it hold all CRUD and search methods.
 * 
 * <p>Created on Okt 23, 2017.<p>
 * @author Saikat
 * @version 1.0
 *
 */
public class TeacherTable implements TableManagerInterface {
	
	ConnectToDB connAndStmt = ConnectToDB.getInstance();
	private Teacher teacherBean = new Teacher();
	private Scanner scan = new Scanner(System.in);
	LinkedList<Teacher> listTeacher;
	
	public TeacherTable() throws SQLException {
		
	}
	 
	@Override
	public void addToTable() throws SQLException {
		String sql = "INSERT INTO teachers (fname, lname, subject) VALUES (?, ?, ?)";
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));
		
		System.out.println("Add to table.");
		System.out.println("Give teachers firstname: ");
		teacherBean.setfName(scan.next());
		System.out.println("Give teachers lastname: ");
		teacherBean.setlName(scan.next());
		System.out.println("Teachers subjectId: ");
		
		teacherBean.setSubject(scan.nextInt());
		
		connAndStmt.getPreparedStmt().setString(1, teacherBean.getfName());
		connAndStmt.getPreparedStmt().setString(2, teacherBean.getlName());
		connAndStmt.getPreparedStmt().setInt(3, teacherBean.getSubject());
		
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new teacher was inserted successfully!");
		}
		
	}
	
	@Override
	public void updateTable() throws SQLException {
		System.out.println("Update Table Values.\nDown here is the current teacher table");
		showTable();
		String sql = "UPDATE teachers set fname=?, lname=?, subject=? where teacherid=?";
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql)); 
		
		System.out.print("Where do you want update teacher info?\nGive Teacher Id: ");
		teacherBean.setId(Integer.parseInt(scan.nextLine()));
		System.out.println("Write a new Name for teacher: ");
		System.out.println("First name: ");
		teacherBean.setfName(scan.next());
		System.out.println("LastName name: ");
		teacherBean.setlName(scan.next());
		System.out.println("SubjectId: ");
		teacherBean.setSubject(scan.nextInt());
		
		connAndStmt.getPreparedStmt().setString(1, teacherBean.getfName());
		connAndStmt.getPreparedStmt().setString(2, teacherBean.getlName());
		connAndStmt.getPreparedStmt().setInt(3, teacherBean.getSubject());
		connAndStmt.getPreparedStmt().setInt(4, teacherBean.getId());
			
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Teachers info was updatet successfully!");
		}
		
		connAndStmt.getPreparedStmt().close();
		
	}
	
	@Override
	public void removeTable() throws SQLException {
		System.out.println("Delete from table.\nDown here is the current Teacher table");
		showTable();
		
		String sql = "DELETE FROM teachers WHERE id=?";
		
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement(sql));
		//statement = connAndStmt.getConn().prepareStatement(sql);
		
		System.out.print("What do you want to delete from the table?\nGive Teachers id: ");
		teacherBean.setId(scan.nextInt());
		connAndStmt.getPreparedStmt().setInt(1, teacherBean.getId());
		
		int rowsInserted = connAndStmt.getPreparedStmt().executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Your have deleted a row from the table successfully!");
		}
		
	}
	
	/*@Override
	public void showTable() throws SQLException {
		System.out.println("+----+-------------+-------------+---------+");
		System.out.println("| Id |   FirstName |   LastName  | Subject |");
		System.out.println("+----+-------------+-------------+---------+");
		
		connAndStmt.setRs(connAndStmt.getStmt().executeQuery("SELECT * FROM teachers"));
		
		//rs = connAndStmt.getStmt().executeQuery("SELECT * FROM teachers");
		
		while(connAndStmt.getRs().next())
		{
			teacherBean.setId(connAndStmt.getRs().getInt("teacherId"));
			teacherBean.setfName(connAndStmt.getRs().getString("fname"));
			teacherBean.setlName(connAndStmt.getRs().getString("lname"));
			teacherBean.setSubject(connAndStmt.getRs().getInt("subject"));
			
			System.out.format("| %2d |%12s |%12s |%5d    |\n", teacherBean.getId(), teacherBean.getfName(),teacherBean.getlName(), teacherBean.getSubject());
			System.out.println("+----+-------------+-------------+---------+");
		}
		
		connAndStmt.getRs().close();
		
	}*/
	
	
	@Override
	public LinkedList<Teacher> showTable() throws SQLException {
		// TODO Auto-generated method stub
		listTeacher = new LinkedList<>();
		connAndStmt.setRs(connAndStmt.getStmt().executeQuery("SELECT * FROM teachers"));
		
		while (connAndStmt.getRs().next())
		{
			Teacher tempteacher = convertRowToTeacher(connAndStmt.getRs());
			listTeacher.add(tempteacher);
		}
		
		return listTeacher;
	}
	
	@Override
	public LinkedList<Teacher> searchTable(String userInput) throws SQLException {
		listTeacher = new LinkedList<>();
		
		userInput = "%" + userInput + "%";
		
		connAndStmt.setPreparedStmt(connAndStmt.getConn().prepareStatement("select * from teachers where lname like ?"));
		connAndStmt.getPreparedStmt().setString(1, userInput);
		
		connAndStmt.setRs(connAndStmt.getPreparedStmt().executeQuery());
		while (connAndStmt.getRs().next())
		{
			Teacher tempteacher = convertRowToTeacher(connAndStmt.getRs());
			listTeacher.add(tempteacher);
		}
		
		return listTeacher;
	}
	
	/**
	 * This method converts a row of teacher to a Teacher Object then returns the Teacher Object. This method takes a ResultSet as parameter, where the ResultSet 
	 * holds information of Teachers row. 
	 * 
	 * @param rs rs(ResultSet) to set.
	 * @return tempTeacher as Teacher Object.
	 * @throws SQLException
	 */
	private Teacher convertRowToTeacher(ResultSet resultSet) throws SQLException {
		
		int id = resultSet.getInt("teacherId");
		String firstName = resultSet.getString("fname");
		String lastName = resultSet.getString("lname");
		int subjectId = resultSet.getInt("subject");
		
		
		Teacher tempTeacher = new Teacher(id, subjectId, lastName, firstName);
		
		return tempTeacher;
	}
	
}
