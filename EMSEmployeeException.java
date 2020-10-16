package com.cognixia.jump.advancedjava.finalproject;

public class EMSEmployeeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee employee;
	
	public EMSEmployeeException (Employee employee){
		
		super("Employee with ID \"" + employee.getId() + "\" already exists");
		this.employee = employee;
		
	}

}
