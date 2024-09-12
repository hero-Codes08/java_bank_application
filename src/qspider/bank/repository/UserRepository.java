package qspider.bank.repository;

import java.time.LocalDate;
import java.util.*;
import qspider.bank.entity.Transaction;
import qspider.bank.entity.User;

public class UserRepository {

	private static Set<User> users = new HashSet<User>();
	
	private static List<Transaction> transactions = new ArrayList<Transaction>();
	
	static {
		User user1 = new User("admin", "admin", "111", "admin", 0.0);
		User user2 = new User("user2", "user2", "222", "user", 1000.0);
		User user3 = new User("user3", "user3", "333", "user", 2000.0);
		User user4 = new User("user4", "user4", "444", "user", 2000.0);

		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
	}

	public boolean transferAmount(String userId, String payeeUserId, Double amount) {
		boolean isDebit = debit(userId, amount, payeeUserId);
		boolean isCredit = credit(payeeUserId,amount,userId);
		
		return isDebit && isCredit;
	}

	private boolean debit(String userId, Double amount, String PayeeUserId) {
		User user = getUser(userId);
		Double accountBalance = user.getAccountBalance();

		users.remove(user);

		Double finalBalance = accountBalance - amount;
		user.setAccountBalance(finalBalance);

		Transaction transaction = new Transaction(LocalDate.now(), PayeeUserId, amount, "Debit", accountBalance,
				finalBalance, userId);
		
		System.out.println(transaction);
		transactions.add(transaction);
		return users.add(user);
	}

	private boolean credit(String payeeUserId, Double amount, String userId ) {
		User user = getUser(payeeUserId);
		Double accountBalance = user.getAccountBalance();

		users.remove(user);

		Double finalBalance = accountBalance + amount;
		user.setAccountBalance(finalBalance);
		
		Transaction transaction = new Transaction(LocalDate.now(), userId, amount, "Credit", accountBalance,
				finalBalance, payeeUserId);
		
		System.out.println(transaction);
		transactions.add(transaction);
		return users.add(user);
	}

	public void printTransactions(String userId) {
	    List<Transaction> filteredTransactions = new ArrayList<>();
	    Iterator<Transaction> iterator = transactions.iterator();
	    
	    while (iterator.hasNext()) {
	        Transaction transaction = iterator.next();
	        if (transaction.getTransactionPerformedBy().equals(userId)) {
	            filteredTransactions.add(transaction);
	        }
	    }
	    
	    System.out.println("Date \t UserId \t Amount \t Type \t InitialBalance \t FinalBalance");
	    System.out.println("-------------------------------------------------------------------------");
	    
	    for (Transaction t : filteredTransactions) {
	        System.out.printf("%s\t%s\t%.2f\t\t%s\t\t%.2f\t\t%.2f%n",
	            t.getTransactionDate(),
	            t.getTranscationUserId(),
	            t.getTransactionAmount(),
	            t.getTransactionType(),
	            t.getInitialBalance(),
	            t.getFinalbalance());
	    }
	    
	    System.out.println("-------------------------------------------------------------------------");
	}
	
	public User getUser(String userId) {
		for (User user : users) {
			if (user.getUsername().equals(userId)) {
				return user;
			}
		}
		return null; 
	}

	public Double checkBankBalance(String userId) {
		for (User user : users) {
			if (user.getUsername().equals(userId)) {
				return user.getAccountBalance();
			}
		}
		return null; 
	}

	public void printUsers() {
		System.out.println(users);
	}

	public User login(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user; 
			}
		}
		return null; 
	}

	public boolean addNewCustomer(String username, String password, String contact) {
		User user = new User(username, password, contact, "user", 500.0);
		return users.add(user);
	}
}
