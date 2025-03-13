package account;

import Bank.Bank;
import account.Transaction;
import java.util.ArrayList;

public abstract class Account {
    protected Bank bank;
    protected String accountNumber;
    protected String ownerFirstName, ownerLastName, ownerEmail;
    protected String pin;
    protected ArrayList<Transaction> transactions;

    public Account(Bank bank, String accountNumber, String firstName, String lastName, String email, String pin) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerEmail = email;
        this.ownerFirstName = firstName;
        this.ownerLastName = lastName;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }

    public String getOwnerFullName() {
        return ownerFirstName + " " + ownerLastName;
    }

    public void addNewTransaction(String accountNumber, Transaction transaction, String description) {
        transaction.setDescription(description);
        transactions.add(transaction);
        bank.addTransaction(transaction); // Ensures transaction is also stored in Bank
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " | Owner: " + getOwnerFullName();
    }
}
