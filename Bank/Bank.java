package Bank;

import Accounts.Account;
import Accounts.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


public class Bank {
    private int ID;
    private String name;
    private String passcode;
    private double creditLimit;
    private double depositLimit;
    private double processingFee;
    private List<Account> bankAccounts;
    private List<Transaction> transactions;

    /**
     * Constructs a new Bank with an ID, name, and passcode.
     */
    public Bank(int ID, String name, String passcode) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.creditLimit = 100000.0;
        this.depositLimit = 50000.0;
        this.processingFee = 50.0;
        this.bankAccounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPasscode() {
        return passcode;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getDepositLimit() {
        return depositLimit;
    }

    public void setDepositLimit(double depositLimit) {
        this.depositLimit = depositLimit;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public List<Account> getBankAccounts() {
        return bankAccounts;
    }

    /**
     * Adds a new account to the bank.
     */
    public void addNewAccount(Account account) {
        if (account == null) {
            System.out.println("Invalid account.");
            return;
        }
        bankAccounts.add(account);
        System.out.println("Account added: " + account.getOwnerFullName());
    }


    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            System.out.println("Invalid transaction.");
            return;
        }
        transactions.add(transaction);
        System.out.println("Transaction recorded: " + transaction.getDescription());
    }


    public String getTransactionHistory() {
        if (transactions.isEmpty()) {
            return "No transactions available.";
        }
        StringBuilder history = new StringBuilder("Bank Transaction History:\n");
        for (Transaction t : transactions) {
            history.append(t.getDetails()).append("\n");
        }
        return history.toString();
    }

    /**
     * Finds an account by its account number.
     */
    public Account findAccount(String accountNumber) {
        for (Account acc : bankAccounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    /**
     * Comparator to sort banks by ID.
     */
    public static class BankIdComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            return Integer.compare(b1.ID, b2.ID);
        }
    }
}