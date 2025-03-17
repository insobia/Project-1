package account;

import Bank.Bank;
import Savings.SavingsAccount;

public class AccountLauncher {
    private Account loggedAccount;
    private Bank associatedBank;

    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public void accountLogin(Bank bank, String accountNumber, String pin) {
        Account foundAccount = bank.findAccount(accountNumber);

        if (foundAccount != null && foundAccount.validatePin(pin)) {
            loggedAccount = foundAccount;
            associatedBank = bank;
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
