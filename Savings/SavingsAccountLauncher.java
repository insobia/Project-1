package Accounts;

import Accounts.SavingsAccount;


public class SavingsAccountLauncher {
    private SavingsAccount loggedAccount;


    public void savingsAccountInit() {
        System.out.println("Initializing Savings Account...");
    }


    public void deposit(double amount) {
        if (loggedAccount == null) {
            System.out.println("No account is currently logged in.");
            return;
        }
        if (amount > 0) {
            loggedAccount.deposit(Math.round(amount * 100.0) / 100.0);
            System.out.println("Deposited $" + String.format("%.2f", amount) + " successfully.");
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive number.");
        }
    }


    public void withdraw(double amount) {
        if (loggedAccount == null) {
            System.out.println("No account is currently logged in.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive number.");
            return;
        }

        if (loggedAccount.withdraw(Math.round(amount * 100.0) / 100.0)) {
            System.out.println("Withdrawal of $" + String.format("%.2f", amount) + " successful.");
        } else {
            System.out.println("Withdrawal failed due to insufficient balance or limits.");
        }
    }


    public double getBalance() {
        if (loggedAccount == null) {
            System.out.println("No account is currently logged in.");
            return 0.0;
        }
        double balance = loggedAccount.getBalance();
        System.out.println("Current balance: $" + String.format("%.2f", balance));
        return balance;
    }


    public void login(SavingsAccount account) {
        if (account == null) {
            System.out.println("Invalid account login attempt.");
            return;
        }
        this.loggedAccount = account;
        System.out.println("Successfully logged in: " + account.getOwnerFullName());
    }

    public void logout() {
        if (loggedAccount == null) {
            System.out.println("No account is logged in.");
            return;
        }

        System.out.print("Are you sure you want to log out? (Y/N): ");
        String confirm = System.console().readLine();
        if (confirm.equalsIgnoreCase("Y")) {
            System.out.println("Logging out: " + loggedAccount.getOwnerFullName());
            loggedAccount = null;
        } else {
            System.out.println("Logout cancelled.");
        }
    }
}