package Accounts;

import Bank.Bank;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic bank account.
 */
public abstract class Account {
    protected Bank bank;
    protected String accountNumber;
    protected String ownerFirstName, ownerLastName;
    protected String email;
    protected String pin;
    protected double balance;
    protected List<Transaction> transactions;

    /**
     * Constructs an Account.
     */
    public Account(Bank bank, String accountNumber, String firstName, String lastName, String email, String pin, double balance) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerFirstName = firstName;
        this.ownerLastName = lastName;
        this.email = email;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public Bank getBank() {
        return this.bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwnerFullName() {
        return ownerFirstName + " " + ownerLastName;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerEmail() {
        return email;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        this.balance += amount;
        recordTransaction(new Transaction(accountNumber, Transaction.TransactionType.DEPOSIT, "Deposited: " + amount));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        this.balance -= amount;
        recordTransaction(new Transaction(accountNumber, Transaction.TransactionType.WITHDRAWAL, "Withdrawn: " + amount));
        return true;
    }

    public void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("✔ Transaction recorded: " + transaction.getDetails());
    }

    public String getTransactionHistory() {
        if (transactions.isEmpty()) {
            return "No transactions.";
        }
        StringBuilder history = new StringBuilder("Transaction history for " + getOwnerFullName() + ":\n");
        for (Transaction transaction : transactions) {
            history.append(transaction.getDetails()).append("\n");
        }
        return history.toString();
    }

    public String getTransactionsInfo() {
        StringBuilder info = new StringBuilder();
        for (Transaction transaction : transactions) {
            info.append(transaction.getDetails()).append("\n");
        }
        return info.toString().trim();
    }

    /**
     * Fix: Allows `fundTransfer()` to throw `IllegalAccountType`
     */
    public abstract boolean fundTransfer(Account recipient, double amount) throws IllegalAccountType;
}