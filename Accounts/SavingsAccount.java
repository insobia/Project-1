package Accounts;

import Bank.Bank;

/**
 * Represents a savings account in the banking system.
 */
public class SavingsAccount extends Account {

    public SavingsAccount(Bank bank, String accountNumber, String firstName, String lastName, String email, String pin, double balance) {
        super(bank, accountNumber, firstName, lastName, email, pin, balance);
    }


    @Override
    public boolean fundTransfer(Account recipient, double amount) throws IllegalAccountType {
        if (!(recipient instanceof SavingsAccount || recipient instanceof CreditAccount)) {
            throw new IllegalAccountType("Transfers can only be made to Savings or Credit accounts.");
        }

        if (recipient == null || amount <= 0 || amount > this.balance) {
            System.out.println("Transfer failed: Invalid recipient or insufficient balance.");
            return false;
        }

        this.balance -= amount;
        recipient.deposit(amount);
        recordTransaction(new Transaction(this.accountNumber, Transaction.TransactionType.TRANSFER,
                "Transferred: " + amount + " to " + recipient.getOwnerFullName()));
        System.out.println("Transfer successful to " + recipient.getOwnerFullName());
        return true;
    }


    public boolean transfer(SavingsAccount recipient, double amount) throws IllegalAccountType {
        return fundTransfer(recipient, amount);
    }

    public boolean transfer(CreditAccount recipient, double amount) throws IllegalAccountType {
        return fundTransfer(recipient, amount);
    }

    public boolean transfer(Bank recipientBank, SavingsAccount recipient, double amount) throws IllegalAccountType {
        return fundTransfer(recipient, amount);
    }

    public boolean transfer(Bank recipientBank, CreditAccount recipient, double amount) throws IllegalAccountType {
        return fundTransfer(recipient, amount);
    }

    /**
     * Deposits money into the savings account.
     */
    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        this.balance += amount;
        recordTransaction(new Transaction(accountNumber, Transaction.TransactionType.DEPOSIT, "Deposited: " + amount));
        return true;
    }


    public boolean cashDeposit(double amount) {
        return deposit(amount);
    }

    /**
     * Withdraws money from the savings account.
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        this.balance -= amount;
        recordTransaction(new Transaction(accountNumber, Transaction.TransactionType.WITHDRAWAL, "Withdrawn: " + amount));
        return true;
    }

    /**
     * Retrieves the bank associated with this account.
     */
    public Bank getBank() {
        return this.bank;
    }


    public double getAccountBalance() {
        return this.balance;
    }
}