package com.cognixia.jump.advancedjava.finalproject;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EMSSessionTest {

	@Test
	void testReadEmployeesFromFile() throws FileNotFoundException {
		
		String employeeFilePath = "resources/employee.txt";
		
		EMSSession session = new EMSSession();
		
		ArrayList<Employee> expected = session.readEmployeesFromFile(employeeFilePath);
		
		ArrayList<Employee> actual = session.globalEmployeeList;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testEmployeeFileNotFound() throws FileNotFoundException{
		
		String employeeFilePath = "resources/employee2.txt";
		
		EMSSession session = new EMSSession();
		
		FileNotFoundException thrown = new FileNotFoundException();
		
		Assertions.assertThrows(FileNotFoundException.class, 
				() -> {session.readEmployeesFromFile(employeeFilePath);});
		
		assertTrue(thrown.getMessage(), true);
	}
	
	@Test
	void testReadDepartmentsFromFile() throws FileNotFoundException{
		
		String departmentsFilePath = "resources/departments.txt";
		
		EMSSession session = new EMSSession();
		
		ArrayList<Department> expected = session.readDepartmentsFromFile(departmentsFilePath);
		
		ArrayList<Department> actual = session.globalDepartmentList;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testDepartmentsFileNotFound() {
		
		String departmentsFilePath = "resources/departments2.txt";
		
		EMSSession session = new EMSSession();
		
		FileNotFoundException thrown = new FileNotFoundException();
		
		Assertions.assertThrows(FileNotFoundException.class, 
				() -> {session.readDepartmentsFromFile(departmentsFilePath);});

		assertTrue(thrown.getMessage(), true);
		
	}
	
	
	
	
	
	
	
	
}