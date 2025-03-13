package account;

import Bank.Bank;
import Savings.SavingsAccount;

public class AccountLauncher {
    private Account loggedAccount;
    private Bank associatedBank;

    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public void accountLogin(String accountNumber, String pin) {
        if (accountNumber.equals("123456") && pin.equals("1234")) {
            loggedAccount = new SavingsAccount(new Bank("MyBank"), "123456", "John", "Doe", "john@example.com", "1234", 0.0);
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public void selectBank(Bank bank) {
        this.associatedBank = bank;
    }

    public void setSession(Account account) {
        this.loggedAccount = account;
    }

    public void destroyLogSession() {
        loggedAccount = null;
    }

    public Account checkCredentials() {
        return loggedAccount;
    }

    public Account getLoggedAccount() {
        return loggedAccount;
    }
}
