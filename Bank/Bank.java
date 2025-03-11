package Bank;

import account.Account;
import account.CreditAccount;
import account.SavingsAccount;
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
    private double processingFee; 
    private List<Account> BANKACCOUNTS; 
    private List<Transaction> transactions; 

    public Bank(int ID, String name, String passcode) {
        this.ID = ID;
        this.name = name;
        this.passcode = passcode;
        this.BANKACCOUNTS = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public Bank(int ID, String name, String passcode, double depositLimit, double withdrawLimit, double creditLimit) {
        this(ID, name, passcode);
        this.DEPOSITLIMIT = depositLimit;
        this.WITHDRAWLIMIT = withdrawLimit;
        this.CREDITLIMIT = creditLimit;
    }
    public void addNewAccount(Account account) {
        BANKACCOUNTS.add(account);
        System.out.println("Account added: " + account.getOwnerFullName());
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : BANKACCOUNTS) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null; 
    }
    public <T extends Account> void showAccounts(Class<T> accountType) {
        for (Account acc : BANKACCOUNTS) {
            if (accountType.isInstance(acc)) {
                System.out.println(acc);
            }
        }
    }

    public Account getBankAccount(Bank bank, String accountNum) {
        return bank.findAccount(accountNum);
    }
    public boolean accountExists(Bank bank, String accountNum) {
        return bank.findAccount(accountNum) != null;
    }

    public ArrayList<String> createNewAccount() {
        return new ArrayList<>(); // Placeholder
    }

    public CreditAccount createNewCreditAccount() {
        return new CreditAccount(); // Placeholder (assume default constructor)
    }

    public SavingsAccount createNewSavingsAccount() {
        return new SavingsAccount(); // Placeholder (assume default constructor)
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

    @Override
    public String toString() {
        return "Bank ID: " + ID + ", Name: " + name;
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
}
