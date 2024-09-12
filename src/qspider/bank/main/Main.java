package qspider.bank.main;

import java.util.Scanner;
import qspider.bank.entity.User;
import qspider.bank.service.UserService;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	static Main main = new Main();
	static UserService userService = new UserService();

	public static void main(String[] args) {

		while (true) {
			System.out.println("Enter your username to access application: ");
			String username = scanner.next();

			System.out.println("Enter your passwordto access application: ");
			String password = scanner.next();

			User user = userService.login(username, password);

			if (user != null && user.getRole().equals("admin")) {
				main.initAdmin();
			} else if (user != null && user.getRole().equals("user")) {
				main.initCustomer(user);
			} else {
				System.out.println("Login unsuccessfull");
			}
		}

	}

	private void initAdmin() {
		boolean flag = true;
		String userId = "";

		while (flag) {
			System.out.println("Hi Admin! ");
			System.out.println("Press 1 to logout. ");
			System.out.println("Press 2 to create customer account. ");
			System.out.println("Press 3 to see all transactions done by customer.");
			System.out.println("Press 4 to see bank balance of customer.");


			int seletedOption = scanner.nextInt();

			switch (seletedOption) {
			
			case 1:
				flag = false;
				System.out.println("Admin sucessfully logged out");
				break;

			case 2:
				main.addNewCustomer();
				break;
				
			case 3:
				System.out.println("Enter customer id: ");
				userId= scanner.next();
				printTransactions(userId);
				break;
				
			case 4:
				System.out.println("Enter user id: ");
				userId= scanner.next();
				Double accountBalance = checkBankBalance(userId);
				System.out.println("The users current account balance is: " + accountBalance);
				break;

			default:
				System.out.println("Wrong choice");
				break;
			}
		}
	}

	private void addNewCustomer() {
		System.out.println("Enter username: ");
		String username = scanner.next();
		System.out.println("Enter password: ");
		String password = scanner.next();
		System.out.println("Enter contact number: ");
		String contact = scanner.next();

		boolean result = userService.addNewCustomer(username, password, contact);

		if (result == true) {
			System.out.println("Customer account is created by admin. ");
		} else {
			System.out.println("Cust account creation failed.");
		}
	}

	private void initCustomer(User user) {

		boolean flag = true;

		while (flag) {
			System.out.println("Hi user");
			System.out.println("User Press 1 to logout. ");
			System.out.println("User Press 2 to check bank balance. ");
			System.out.println("User Press 3 to do fund transfer");
			System.out.println("User Press 4 to see all transactions. ");

			int selectedOption = scanner.nextInt();

			switch (selectedOption) {
			
			case 1:
				flag = false;
				System.out.println("user sucessfully logged out");
				break;
			
			case 2: 
				Double balance = main.checkBankBalance(user.getUsername());
				if (balance != null) {
					System.out.println("your bank balance is " +balance);
				}else {
					System.out.println("Check your username");
				}
				break; 
				
			case 3:
				main.fundTransfer(user);
				break;
				
			case 4:
				main.printTransactions(user.getUsername());
				break;
				
			default:
				System.out.println("user please dont select wrong choice");
				break;
			}

		}

	}
	
	private void printTransactions(String userId) {
		userService.printTransactions(userId);
	}
	
	private void fundTransfer(User userDetails) {
		System.out.println("Enter payee account number: ");
		String payeeAccountId = scanner.next();
		
		User user = getUser(payeeAccountId);
		
		if (user!= null) {
			System.out.println("Valid username recieved so please enter amount to transfer. ");
			Double amount =  scanner.nextDouble();
			
			Double userAccountBalance = checkBankBalance(userDetails.getUsername());
			
			if(userAccountBalance>= amount) {
				boolean result = userService.transferAmount(userDetails.getUsername(), payeeAccountId, amount);
				
				if (result) {
					System.out.println("Amount transferred successfully");
				}else {
					System.out.println("Fund Transfer Failed");
				}
				
			}else {
				System.out.println("Balance insufficient for transfer, current balance is: "+userAccountBalance);
			}
		}else {
			System.out.println("Please enter valid username. ");
		}
	}
	
	private User getUser(String userId) {
		return userService.getUser(userId);
	}
	
	private Double checkBankBalance(String userId) {
		return userService.checkBankBalance(userId);
	}
}
