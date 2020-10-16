package com.cognixia.jump.advancedjava.finalproject;

public class Employee {

	//hi

	int id;
	public String name;
	public String departmentName;
	public String street;
	public long number;
	public int salary;

	public Employee(int id, String name, String departmentName, String street, long number, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.departmentName = departmentName;
		this.street = street;
		this.number = number;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
	   this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
 
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}


	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", departmentName=" + departmentName + ", street=" + street
				+ ", number=" + number + ", salary=" + salary + "]";
	}
	
	

}
