package com.cognixia.jump.advancedjava.finalproject;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/************************************************************
 * Employee Management System
 * by Kwame Acheampong, Ken Cheng, Rodney Chery, Mel Lamagna
 ************************************************************/

public class EMSRunner {

	public static void main(String[] args) {
		
		EMSSession session = new EMSSession();
		
		String empFile = "resources/employees.txt";
		String depFile = "resources/departments.txt";
		
		try {
			session.loadSession(empFile, depFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String menu = new String(
				"------------------------------------------------------------\n"
				+ "1. add new employee\n"
				+ "2. update employee information\n"
				+ "3. remove employee\n"
				+ "4. list employee information\n"
				+ "5. add a department\n"
				+ "6. update a department\n"
				+ "7. remove a department\n"
				+ "8. list the departments\n"
				+ "9. list employees in department\n"
				+ "10. save and exit\n"
				);		
		System.out.println(menu);
		System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
		
		Scanner scan = new Scanner(System.in);
		
		int input = 0;
		try {
			input = scan.nextInt();
		} catch (InputMismatchException e2) {
			System.out.println("Invalid input.");
			scan.next();
		}
		
		
		int Id;
		String employeeName;
		String departmentName;
		String street;
		long number;
		int salary;
		String deptName;
		String phoneNumber;
		int budget;
		Department department;
		Department newDep;
		Employee employee;
		Employee newEmp;
		
		int p = 0;
		
		while (true) {

			switch (input) {
			
			case 0:
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;
	
			case 1:
				
				try {
					
					System.out.println("\nInput employee ID you want to add:");
					Id = scan.nextInt();
	
					System.out.println("\nInput employee name:");
					scan.nextLine();
					employeeName = scan.nextLine();
					
					System.out.println("\nInput department name:");
					departmentName = scan.nextLine();
	
					System.out.println("\nInput street:");
					street = scan.nextLine();
					
					System.out.println("\nInput phone number:");
					number = scan.nextLong();
					
					System.out.println("\nInput salary:");
					salary = scan.nextInt();
					
					
					employee = new Employee(Id, employeeName, departmentName, street, number, salary);			
					try {
						session.addEmployee(employee);
					} catch (EMSEmployeeException e1) {
						System.out.println(e1.getMessage());
					}
					session.saveSession(empFile, depFile);
					
					System.out.println("\nEmployee is added!");
				} catch (InputMismatchException e2) {
					System.out.println("Invalid input.");
					scan.next();
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;
				
			case 2:
				
				try {
					System.out.println("\nInput employee ID you want to update:");
					Id = scan.nextInt();
	
					System.out.println("\nInput employee name:");
					scan.nextLine();
					employeeName = scan.nextLine();
					
					System.out.println("\nInput department name:");
					departmentName = scan.nextLine();
	
					System.out.println("\nInput street:");
					street = scan.nextLine();
					
					System.out.println("\nInput phone number:");
					number = scan.nextLong();
					
					System.out.println("\nInput salary:");
					salary = scan.nextInt();
					
					// delete the object with old data and add a new object with updated data
					p = 0;
					for (Employee emp : session.globalEmployeeList) {
						  if (emp.getId() == Id) {
							  session.deleteEmployee(emp);
								newEmp = new Employee(Id, employeeName, departmentName, street, number, salary);			
								try {
									session.addEmployee(newEmp);
								} catch (EMSEmployeeException e1) {
									System.out.println(e1.getMessage());
								}
							  session.saveSession(empFile, depFile);
							  System.out.println("\nEmployee is updated!");
							  p = 1;
							  break;
						  }
							
					}
					if (p == 0) {
						System.out.println("\nEmployee not found!");
					}
				} catch (InputMismatchException e1) {
					System.out.println("Invalid input.");
					scan.next();
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;
			
			case 3:
				
				try {
					System.out.println("\nInput employee ID you want to delete:");
					Id = scan.nextInt();
					p = 0;
					for (Employee emp : session.globalEmployeeList) {
						  if (emp.getId() == Id) {
							session.deleteEmployee(emp);
							session.saveSession(empFile, depFile);
							System.out.println("\nEmployee is deleted!");
							p = 1;
							break;
						  }
					}
					if (p == 0) {
						System.out.println("\nEmployee not found!");
					}
				} catch (InputMismatchException e1) {
					System.out.println("Invalid input.");
					scan.next();
				}
	
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;
			
			case 4:
				
				//System.out.println(session.globalEmployeeList);
				if (!session.globalEmployeeList.isEmpty()) {
					for (Employee employee3 : session.globalEmployeeList) {
						System.out.println(employee3);
					}
				} else {
					System.out.println("No employees found.");
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;			
				
			case 5:
				
				try {
					System.out.println("\nInput department name you want to add:");
					scan.nextLine();
					deptName = scan.nextLine();
					
					System.out.println("\nInput phone number:");
					phoneNumber = scan.next();
					
					System.out.println("\nInput budget:");
					budget = scan.nextInt();
					
					department = new Department(deptName, phoneNumber, budget);
					
					try {
						session.addDepartment(department);
					} catch (EMSDepartmentException e) {
						System.out.println(e.getMessage());
					}
					session.saveSession(empFile, depFile);
					System.out.println("\nDepartment is added!");
				} catch (InputMismatchException e1) {
					System.out.println("Invalid input.");
					scan.next();
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;
			
			case 6:
				
				try {
					System.out.println("\nInput department name you want to update:");
					scan.nextLine();
					deptName = scan.nextLine();
					
					System.out.println("\nInput phone number:");
					phoneNumber = scan.next();
					
					System.out.println("\nInput budget:");
					budget = scan.nextInt();
					
					// delete the object with old data and add a new object with updated data
					p = 0;
					for (Department dep : session.globalDepartmentList) {
						  if (dep.getName().equals(deptName)) {
							session.deleteDepartment(dep);
							newDep = new Department(deptName, phoneNumber, budget);
							try {
								session.addDepartment(newDep);
							} catch (EMSDepartmentException e) {
								System.out.println(e.getMessage());
							}
							session.saveSession(empFile, depFile);
							System.out.println("\nDepartment is updated!");
							p = 1;
							break;
						  }
					}
					if (p == 0) {
						System.out.println("\nDepartment not found!");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input.");
					scan.next();
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;
			
			case 7:
				
				try {
					System.out.println("\nInput department you want to delete:");
					scan.nextLine();
					deptName = scan.nextLine();
					p = 0;
					for (Department dep : session.globalDepartmentList) {
						  if (dep.getName().equals(deptName)) {
							session.deleteDepartment(dep);
							session.saveSession(empFile, depFile);
							System.out.println("Department is deleted!");
							p = 1;
							break;
						  }
					}
					if (p == 0) {
						System.out.println("\nDepartment not found!");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input.");
					scan.next();
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;
			
			case 8:
				
				//System.out.println(session.globalDepartmentList);
				if (!session.globalDepartmentList.isEmpty()) {
					for (Department dept : session.globalDepartmentList) {
						System.out.println(dept);
					}
				} else {
					System.out.println("No departments found.");
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;			
				
			case 9:
				
				try {
					
					System.out.println("\nInput department name you want to search for in employee records:");
					scan.nextLine();
					deptName = scan.nextLine();
	
					List<Employee> deptEmpl = session.getEmployeesInDepartment(deptName);
					for (Employee employee2 : deptEmpl) {
						System.out.println(employee2);
					}
				
				} catch (InputMismatchException e) {
					System.out.println("Invalid input.");
					scan.next();
				}
				
				System.out.println("\n" + menu);
				System.out.println("\nPlease enter an integer between 1 to 10 to continue:");
				try {
					input = scan.nextInt();
				} catch (InputMismatchException e3) {
					System.out.println("Invalid input.");
					scan.next();
					input = 0;
					continue;
				}
				break;	
				
			case 10:
				
	
				System.out.println("Exiting...");
				session.saveSession(empFile, depFile);
				scan.close();
				return;		
				
			
			}
		}
	}
}
