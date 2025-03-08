package Bank;
import account.Account;
import account.Transaction;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private int ID;
    private String name;
    private String passcode;
    private double DEPOSITLIMIT;
    private double WITHDRAWLIMIT;
    private double CREDITLIMIT;
    private List<Account> accounts; // Store multiple accounts
    private List<Transaction> transactions; // Store all transactions for this bank

    // Constructor
    public Bank(int ID, String name, String passcode, double depositLimit, double withdrawLimit, double creditLimit) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.DEPOSITLIMIT = depositLimit;
        this.WITHDRAWLIMIT = withdrawLimit;
        this.CREDITLIMIT = creditLimit;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addNewAccount(Account account) { // Renamed from `addAccount()`
        accounts.add(account);
        System.out.println("Account added: " + account.getOwnerFullName());
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null; // Account not found
    }

    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        System.out.println("\nBank Accounts:");
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    // Store transaction history for this bank
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getTransactionHistory() {
        if (transactions.isEmpty()) {
            return "No transactions available.";
        }
        StringBuilder history = new StringBuilder("Transaction History:\n");
        for (Transaction t : transactions) {
            history.append(t.toString()).append("\n");
        }
        return history.toString();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPasscode() {
        return passcode;
    }
}
