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
        this.ownerFirstName = firstName; // Fixed typo (was "firsName")
        this.ownerLastName = lastName;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }

    public String getOwnerFullName() {
        return ownerFirstName + " " + ownerLastName;
    }

    public void addNewTransaction(String accountNumber, Transaction.TransactionType type, String description) {
        Transaction newTransaction = new Transaction(accountNumber, type, description);
        transactions.add(newTransaction);
        bank.addTransaction(newTransaction); // Ensures transaction is also stored in `Bank`
    }

    public String getTransactionInfo() {
        StringBuilder info = new StringBuilder("Transaction History:\n");
        for (Transaction t : transactions) {
            info.append(t.toString()).append("\n"); //Fixed missing semicolon
        }
        return info.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " | Owner: " + getOwnerFullName();
    }
}
