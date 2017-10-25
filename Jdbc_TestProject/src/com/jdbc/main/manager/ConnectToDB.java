/**
 * 
 */
package com.jdbc.main.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ConnetToDb object is a singelton class where you connect to your specified database. This class has setter and getters for Connection, Statement, ResultSet 
 * and PreparedStatement. 
 * <p>Created on Okt 23, 2017<p>
 * @author Saikat Talukder
 * @version 1.0
 *
 */
public class ConnectToDB {
	
	private static ConnectToDB firstInstance = null;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement preparedStmt;
	private String host = "jdbc:mysql://localhost/jdbcpro", user = "root" , password = "";
	
	/**
	 * Constructor and initialize Connection and Statement.
	 * @throws SQLException that provides information on a database access error or other errors.
	 */
	public ConnectToDB() throws SQLException {
		this.conn = DriverManager.getConnection(host,user,password);
		this.stmt = conn.createStatement();
	}
	
	/**
	 * This method returns the connection so you can get accesses to in other classes
	 * @return the conn as Connection
	 * 
	 */
	public Connection getConn() {
		return conn;
	}
	
	/**
	 * This method returns the Statement so you can get accesses to it in other classes and do database queries.
	 * Read more about Statement in its own javadoc.
	 * @return the stmt as Statement 
	 */
	public Statement getStmt() {
		return stmt;
	}
	
	/**
	 * This method returns the ResultSet so you can get accesses to it in other classes. Read more about ResultSet in its own javadoc.
	 * @return the rs as ResultSet
	 */
	public ResultSet getRs() {
		return rs;
	}
	
	/**
	 * 
	 * @param rs rs to set as ResultSet
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	/**
	 * This method returns the PreparedStatement so you can get accesses to it in other classes. Read more about PreparedStatement in its own javadoc.
	 * @return the preparedStmt as PreparedStatement.
	 */
	public PreparedStatement getPreparedStmt() {
		return preparedStmt;
	}
	
	/**
	 * 
	 * @param preparedStmt preparedStmt to set as PreparedStatement.
	 */
	public void setPreparedStmt(PreparedStatement preparedStmt) {
		this.preparedStmt = preparedStmt;
	}
	
	/**
	 * This method lets you get access to ConnectToDB class and create a object of this class in other classes. ConnectToDB is a singelton class where you can 
	 * create only one Object of this class.
	 * @return the firstInstance as ConnectToDb object
	 */
	public static ConnectToDB getInstance()
	{
		if(firstInstance == null)
		{
			try {
				firstInstance = new ConnectToDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return firstInstance;
	}
	

}
