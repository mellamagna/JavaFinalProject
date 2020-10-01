package com.cognixia.jump.advancedjava.JavaFinalProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class EMSSession {
	
	/****************************************************************************************************
	 * GLOBAL VARIABLES FOR EMPLOYEE MANAGEMENT SYSTEM
	 ****************************************************************************************************/
	
	//global lists of all Employee/Department object instances created at runtime
	//any methods for creating/deleting employees should be called on these variables
	//(they will be the ones used to load and save the full session)
	
	static ArrayList<Employee> globalEmployeeList = new ArrayList<Employee>();
	static ArrayList<Department> globalDepartmentList = new ArrayList<Department>();
	
	/****************************************************************************************************
	 * RUNTIME METHODS FOR EMPLOYEE MANAGEMENT SYSTEM
	 * (USE THESE IN MAIN METHOD)
	 * @throws FileNotFoundException 
	 ****************************************************************************************************/
	
	public static void loadSession(String empFilePath, String depFilePath) throws FileNotFoundException {
		
		//given two paths to files for Employee and Department lists respectively, loads both lists from the current session
		//(these lists are then accessible from the above variables)
		
		readEmployeesFromFile(empFilePath);
		readDepartmentsFromFile(depFilePath);
		
	}
	
	public static void saveSession(String empFilePath, String depFilePath) {
		
		//given two paths to files for Employee and Department lists respectively, saves both lists from the current session
		
		writeEmployeesToFile(globalEmployeeList, empFilePath);
		writeDepartmentsToFile(globalDepartmentList, depFilePath);
		
	}
	
	public static void addEmployee(Employee emp) {
		
		//adds an employee to the global employee list for this session
		//(first checking for any duplicate IDs)
		
		//TODO Possible custom exceptions for duplicate employees/returning errors
		
		Stream<Employee> empStream = globalEmployeeList.stream();
		Optional<Employee> opt = empStream.filter(x -> x.getId() == emp.getId()).findAny();
		if (opt == null) {
			globalEmployeeList.add(emp);
		}
		
	}
	
	public static void addDepartment(Department dep) {
		
		//adds a department to the global department list for this session
		//(first checking for any duplicate names)
		
		//TODO Possible custom exceptions for duplicate departments/returning errors
		
		Stream<Department> depStream = globalDepartmentList.stream();
		Optional<Department> opt = depStream.filter(x -> x.getName() == dep.getName()).findAny();
		if (opt == null) {
			globalDepartmentList.add(dep);
		}
		
	}
	
	public static void deleteEmployee(Employee emp) {
		globalEmployeeList.remove(emp);
	}
	
	public static void deleteDepartment(Department dep) {
		globalDepartmentList.remove(dep);
	}
	
	
	/****************************************************************************************************
	 * INTERNAL FILE I/O METHODS FOR EMPLOYEE MANAGEMENT SYSTEM
	 ****************************************************************************************************/
	
	/********************************************************************************
	 * CORE FILE I/O METHODS
	 ********************************************************************************/
	
	/************************************************************
	 * WRITING METHODS
	 ************************************************************/
	
	public static void writeEmployeeToFile(Employee emp, String filepath) {
		
		//given an Employee object and a path to a file, appends a formatted string to the end of the file containing Employee data
		
		StringBuffer outputString = new StringBuffer(generateEmployeeOutputString(emp));
		
		File file = new File(filepath);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try (FileWriter fr = new FileWriter(file, true);
				BufferedWriter br = new BufferedWriter(fr);
				PrintWriter pr = new PrintWriter(br);
			) {
			
			pr.println(outputString);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	}
	
	public static void writeEmployeesToFile(ArrayList<Employee> empList, String filepath) {
		
		//given an Employee object and a path to a file, overwrites entire file with formatted Employee data from list
		
		StringBuffer outputString = new StringBuffer();
		
		File file = new File(filepath);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try (FileWriter fr = new FileWriter(file);
				BufferedWriter br = new BufferedWriter(fr);
				PrintWriter pr = new PrintWriter(br);
			) {
			
			for (Employee emp : empList) {
				outputString.setLength(0);
				outputString.append(generateEmployeeOutputString(emp));
				pr.println(outputString);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	}
	
	public static void writeDepartmentToFile(Department dep, String filepath) {
		
		//given an Department object and a path to a file, appends a formatted string to the end of the file containing Department data
		
		StringBuffer outputString = new StringBuffer(generateDepartmentOutputString(dep));
		
		File file = new File(filepath);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try (FileWriter fr = new FileWriter(file, true);
				BufferedWriter br = new BufferedWriter(fr);
				PrintWriter pr = new PrintWriter(br);
			) {
			
			pr.println(outputString);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	}
	
	public static void writeDepartmentsToFile(ArrayList<Department> depList, String filepath) {
		
		//given an Employee object and a path to a file, overwrites entire file with formatted Employee data from list
		
		StringBuffer outputString = new StringBuffer();
		
		File file = new File(filepath);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try (FileWriter fr = new FileWriter(file);
				BufferedWriter br = new BufferedWriter(fr);
				PrintWriter pr = new PrintWriter(br);
			) {
			
			for (Department dep : depList) {
				outputString.setLength(0);
				outputString.append(generateDepartmentOutputString(dep));
				pr.println(outputString);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	}
	
	/************************************************************
	 * READING METHODS
	 ************************************************************/
	
	public static ArrayList<Employee> readEmployeesFromFile(String filepath) throws FileNotFoundException {
		
		//given a path to a file, parses entire file into an ArrayList of Employee objects
		
		//ArrayList<Employee> result = new ArrayList<Employee>();
		globalEmployeeList = new ArrayList<Employee>();
		
		File file = new File(filepath);
		
		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
			) {
			
			String line;
			while((line = br.readLine()) != null) {
				Employee emp = buildEmployeeFromInputString(line.trim());
				//result.add(emp);
				globalEmployeeList.add(emp);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return globalEmployeeList;
		
	}
	
	public static ArrayList<Department> readDepartmentsFromFile(String filepath) throws FileNotFoundException {
		
		//given a path to a file, parses entire file into an ArrayList of Department objects
		
		//ArrayList<Department> result = new ArrayList<Department>();
		globalDepartmentList = new ArrayList<Department>();
		
		File file = new File(filepath);
		
		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
			) {
			
			String line;
			while((line = br.readLine()) != null) {
				Department dep = buildDepartmentFromInputString(line.trim());
				//result.add(dep);
				globalDepartmentList.add(dep);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return globalDepartmentList;
		
	}
	
	
	/********************************************************************************
	 * SUPPLEMENTARY METHODS (INTERNAL ONLY)
	 ********************************************************************************/
	
	public static String generateEmployeeOutputString(Employee emp) {
		
		//Used internally by the methods below; no need to worry about using this in any external code.
		//(given an Employee object, returns a formatted string that is written to a central Employee list text file)
		
		StringBuffer outputString = new StringBuffer();
		
		//TODO replace the called getters/setters below with the ones from the final Employee class
		outputString.append(emp.getId() + "|" + emp.getName() + "|" + emp.getDepartmentName() + "|" + emp.getStreet()
			+ "|" + emp.getNumber() + "|" + emp.getSalary());
		
		return outputString.toString();
		
	}
	
	public static String generateDepartmentOutputString(Department dep) {
		
		//Used internally by the methods below; no need to worry about using this in any external code.
		//(given an Department object, returns a formatted string that is written to a central Department list text file)
		
		StringBuffer outputString = new StringBuffer();
		
		//TODO replace the called getters/setters below with the ones from the final Department class
		outputString.append(dep.getName() + "|" + dep.getPhoneNumber() + "|" + dep.getBudget());
		
		return outputString.toString();
		
	}
	
	public static String[] buildFieldsFromInputString(String s) {
		
		//Used internally by the methods below; no need to worry about using this in any external code.
		//(given a formatted string read from a text file, returns an array containing individual fields to be passed to an appropriate constructor wrapper below)
		
		ArrayList<String> fields = new ArrayList<String>();
		StringBuffer currentField = new StringBuffer();
		
		//loop appends letters to the current field until it detects a vertical bar character '|';
		//once this bar is detected the current field is considered complete and is added to the return list
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '|') {
				fields.add(currentField.toString());
				currentField.setLength(0);
			} else {
				currentField.append(s.charAt(i));
			}
		}
		
		//last field is not followed by a vertical bar and must be added to fields manually
		if (!currentField.toString().equals("")) {
			fields.add(currentField.toString());
		}
		
		return (String[]) fields.toArray();
		
	}
	
	public static Employee buildEmployeeFromInputString(String s) throws NumberFormatException {
		
		//Used internally by the methods below; no need to worry about using this in any external code.
		//(given a formatted string read from a text file, returns an Employee object containing this information)
		
		String[] fields = buildFieldsFromInputString(s);
		
		//TODO replace constructor with method from final Employee class
		return new Employee(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], Integer.parseInt(fields[4]), Integer.parseInt(fields[5]));
		
	}
	
	public static Department buildDepartmentFromInputString(String s) throws NumberFormatException {
		
		//Used internally by the methods below; no need to worry about using this in any external code.
		//(given a formatted string read from a text file, returns a Department object containing this information)
		//Employee list will remain null, as this will be constructed at runtime.
		
		String[] fields = buildFieldsFromInputString(s);
		
		//TODO replace constructor with method from final Department class
		return new Department(fields[0], fields[1], Integer.parseInt(fields[2]), null);
		
	}

}
