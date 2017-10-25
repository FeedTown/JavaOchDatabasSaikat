package com.jdbc.main.manager.studentTable;

import java.sql.Date;

/**
 * Student object with setter and getter for each column in the Student table.
 * 
 * <p>Created on Okt 23, 2017<p>
 * @author Saikat Talukder
 * @version 1.0
 *
 */
public class Student {

	private int id;
	private String fName;
	private String lName;
	private Date birthDate;
	private int teacherId;
	
	public Student()
	{
		
	}
	
	
	/**
	 * Constructor and initialize Student id, fName, lName, date and teacherId. 
	 * 
	 * @param id id to set.
	 * @param fName fName to set.
	 * @param lName lName to set.
	 * @param birthDate birthDate to set.
	 * @param teacherId teacherId to set.
	 */
	public Student(int id, String fName, String lName, Date birthDate, int teacherId)
	{
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.birthDate = birthDate;
		this.teacherId = teacherId;
	}
	
	/**
	 * 
	 * @return the current student id as integer.
	 * 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id id on set (as Integer)
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the current student fName as String.
	 */
	public String getfName() {
		return fName;
	}
	
	/**
	 * 
	 * @param fName fName on set as String.
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * 
	 * @return the current student lName as String.
	 */
	public String getlName() {
		return lName;
	}
	
	/**
	 * 
	 * @param lName lName on set as String
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	/**
	 * 
	 * @return the current student birthDate as Date format.
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/**
	 * 
	 * @param birthDate birthDate on set as Date format (from sql libary). 
	 */
	public void setDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * 
	 * @return the current student birthDate as Date format.
	 */
	public int getTeacherId() {
		return teacherId;
	}
	
	/**
	 * 
	 * @param teacherId  teacherId on set as Integer.
	 */
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	
	@Override
	public String toString()
	{
		return String.format("\nStudent: [id=%s, firstName=%s, lastName=%s, birthdate=%s, subjectId=%s]\n", id,fName,lName,birthDate,teacherId);
	}
	
}
