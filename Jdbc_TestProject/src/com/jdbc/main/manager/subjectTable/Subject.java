package com.jdbc.main.manager.subjectTable;

/**
 * Subject object with setter and getter for each column in the Subject table 
 * 
 * <p>Created on Okt 23, 2017<p>
 * @author Saikat Talukder
 * @version 1.0
 *
 */

public class Subject {
	
	private int id;
	private String name;
	int count = 0;
	
	/**
	 * Constructor with empty parameters
	 * 
	 */
	public Subject()
	{
		
	}
	
	/**
	 * Constructor and initialize Subject id and name
	 * @param id id to set
	 * @param name name to set
	 */
	public Subject(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return: the id of the subject that are stored in getId as integer 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id id to set(in Integer)
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return subject name as String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name name to set(in String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return String.format("\nSubject: [Id= %1s, Subjectname= %15s]\n", id , name);
	}
}
