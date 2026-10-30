/*
 * Concept: Classes, Objects and Encapsulation
 */

package com.training.coding_challenge;

class EmployeeDetails{
	private String empName;
	private int empId;
	private double salary;
	
	public EmployeeDetails(String empName,int empId,double salary) {
		this.empName=empName;
		this.empId=empId;
		this.salary=salary;
	}
	public void updateSalary(double newSalary) {
		if(newSalary > 0) {
			salary +=newSalary;
			System.out.println("Update Salary Successfully:" + salary );
		}
	}
	public void displayEmployee() {
		System.out.println("Employee Name: "+ empName);
		System.out.println("Employee Id: "+ empId);
		System.out.println("Employee Salary: "+ salary);
	}
}
public class EmployeeManagement {
	public static void main(String[] args) {
		EmployeeDetails empd=new EmployeeDetails("RamBabu",1110,45000.97);
		
		empd.displayEmployee();
		empd.updateSalary(24539);
	}

}
