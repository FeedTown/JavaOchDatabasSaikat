package com.jdbc.main.manager.teacherTable;


/**
 * Teacher object with setter and getter for each column in the Teacher table  
 * 
 * <p>Created on Okt 23, 2017<p>
 * @author Saikat Talukder
 * @version 1.0
 *
 */
public class Teacher {
	
	/**
	 * Declaration of id, subjectid, firstname and lastname 
	 */
	private int id;
	private int subjectId;
	private String fName;
	private String lName;
	
	public Teacher()
	{
		super();
	}
	
	/**
	 * Constructor and initialize Teacher id, subjectId, fName, and lName
	 * 
	 * @param id id to set
	 * @param subjectId subjectId to set
	 * @param fName fName to set
	 * @param lName lName to set
	 */
	public Teacher(int id, int subjectId, String fName, String lName)
	{
		this.id = id;
		this.subjectId = subjectId;
		this.fName = fName;
		this.lName = lName;
		
	}
	
	/**
	 * @return the id of the teacher that are stored in getId as integer.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id id to set(in Integer).
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the current subjectId as Integer.
	 */
	public int getSubject() {
		return subjectId;
	}
	
	
	/**
	 * 
	 * @param subjectId subjectId to set(in Integer).
	 */
	public void setSubject(int subjectId) {
		this.subjectId = subjectId;
	}
	
	/**
	 * 
	 * @return the current fName as String
	 */
	public String getfName() {
		return fName;
	}
	
	/**
	 * 
	 * @param fName fName to set(as String)
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	/**
	 * 
	 * @return the current lName as String.
	 */
	public String getlName() {
		return lName;
	}
	
	/**
	 * 
	 * @param lName lName to set(as String)
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	
	@Override
	public String toString() {
		return String.format("\nTeacher: [id=%s, lastName=%s, firstName=%s, subjectId=%s]\n",
						id, fName, lName, subjectId);
	}

}
