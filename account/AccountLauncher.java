package account;

import Bank.Bank;
import Savings.SavingsAccount;

public class AccountLauncher {
    private Account loggedAccount;
    private Bank associatedBank;

    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    // Handle account login
    public void accountLogin(String accountNumber, String pin) {
        // Normally, you'd check from a database or a list of accounts
        if ("123456".equals(accountNumber) && "1234".equals(pin)) {
            associatedBank = new Bank(1, "MyBank", "bankpass");
            loggedAccount = new SavingsAccount(associatedBank, "123456", "John", "Doe", "john@example.com", "1234");
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public void logout() {
        loggedAccount = null;
        System.out.println("Logged out successfully.");
    }

    public Account getLoggedAccount() {
        return loggedAccount;
    }
}
