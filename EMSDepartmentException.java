package com.cognixia.jump.advancedjava.finalproject;

public class EMSDepartmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Department department;
	
	public EMSDepartmentException (Department department){
		
		super("Department with name \"" +  department.getName() + "\" already exists");
		this.department = department;
		
	}

}
