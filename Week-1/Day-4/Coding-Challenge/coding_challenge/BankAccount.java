/*
 * Concept: Encapsulation and data protection
 */
package com.training.coding_challenge;

class BankAccountDetails{
	private String accountHolder;
	private int accountNumber;
	private double balance;
	
	public BankAccountDetails(String accountHolder,int accountNumber,double balance) {
		this.accountHolder=accountHolder;
		this.accountNumber=accountNumber;
		this.balance=balance;
		
	}
	
	public void deposit(double amount) {
		if(amount > 0) {
			balance +=amount;
			
			  System.out.println("Amount deposited: " + amount);
		}else {
			System.out.println("Invalid Deposit Amount");
		}
	}
	
	public void withdraw(double amount) {
		if(amount > 0 && amount <=balance) {
			System.out.println("Amount withdraw: "+ amount);
		}else {
			System.out.println("Insufficent Balance or Invalid Amount");
		}
	}
	public void checkBalance() {
		System.out.println("Account Holder Name: "+ accountHolder);
		System.out.println("Account Number: "+ accountNumber);
		System.out.println("Available Balance: "+ balance);
	}
}

public class BankAccount {
	public static void main(String[] args) {
		BankAccountDetails acc=new BankAccountDetails("Vijay",112343268,24763.89);
		acc.deposit(674.98);
		acc.withdraw(500);
		acc.checkBalance();
	}

}
