package account;

import Bank.Bank;
import Accounts.Account;

public class AccountLauncher {
    private Account loggedAccount;

    /**
     * Checks if an account is currently logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    /**
     * Logs into an account by verifying credentials.
     * @param bank The bank where the account is located.
     * @param accountNumber The account number to log in.
     * @param pin The PIN of the account.
     */
    public void accountLogin(Bank bank, String accountNumber, String pin) {
        if (bank == null) {
            System.out.println("Error: No bank selected.");
            return;
        }

        // Retrieve the account from the bank
        Account foundAccount = bank.findAccount(accountNumber);

        // Validate login credentials
        if (foundAccount != null && foundAccount.validatePin(pin)) {
            loggedAccount = foundAccount;
            System.out.println("Login successful for: " + loggedAccount.getOwnerFullName());
        } else {
            System.out.println("Invalid account credentials. Please try again.");
        }
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        if (loggedAccount == null) {
            System.out.println("⚠ No active session to log out.");
            return;
        }
        System.out.println("✔ Logging out: " + loggedAccount.getOwnerFullName());
        loggedAccount = null;
    }

    /**
     * Retrieves the currently logged-in account.
     * @return The logged-in account or null if no session exists.
     */
    public Account getLoggedAccount() {
        return loggedAccount;
    }
}
