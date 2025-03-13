package account;

import Bank.Bank;
import Savings.SavingsAccount;

public class AccountLauncher {  // ✅ Fix: Removed parentheses ()

    private Account loggedAccount;
    private Bank associatedBank;

    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    // Handle account login
    public void accountLogin(String accountNumber, String pin) {
        // Normally, you'd check from a database or a list of accounts
        if (accountNumber.equals("123456") && pin.equals("1234")) {
            loggedAccount = new SavingsAccount(new Bank(1, "MyBank", "passcode", 50000, 10000, 20000),
                    "123456", "John", "Doe", "john@example.com", "1234");  // ✅ Fix: Adjusted Bank constructor
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public void logout() {
        loggedAccount = null;
    }

    public Account getLoggedAccount() {
        return loggedAccount;
    }
}
