package account;

import Bank.Bank;
import account.Transaction;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected Bank bank;
    protected String accountNumber;
    protected String ownerFirstName;
    protected String ownerLastName;
    protected String ownerEmail;
    protected String pin;
    protected List<Transaction> transactions;

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

    public void addNewTransaction(Transaction.TransactionType type, String description) {
        Transaction newTransaction = new Transaction(accountNumber, type, description);
        transactions.add(newTransaction);
        if (bank != null) {
            bank.addTransaction(newTransaction);
        }
    }

    public String getTransactionInfo() {
        if (transactions.isEmpty()) {
            return "No transactions available.";
        }
        StringBuilder info = new StringBuilder("Transaction History:\n");
        for (Transaction t : transactions) {
            info.append(t.toString()).append("\n");
        }
        return info.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " | Owner: " + getOwnerFullName();
    }
}
