package Credit;

import Accounts.Account;  
import account.Transaction;

/**
 * Launcher for Credit Account functionalities.
 * Handles credit payments, recompenses, and retrieving logged-in accounts.
 */
public class CreditAccountLauncher {

    private Account loggedAccount;  // Stores the currently logged-in account

    /**
     * Initializes the credit account system.
     */
    public void creditAccountInit() {
        System.out.println("Initializing Credit Account...");
    }

    /**
     * Processes a credit payment for the given account.
     *
     * @param account The account to process the payment for.
     * @param amount  The amount to be paid.
     */
    public void creditPaymentProcess(Account account, double amount) {
        if (account == null || amount <= 0) {
            System.out.println("Invalid payment request.");
            return;
        }
        System.out.println("Processed credit payment of " + amount + " for " + account.getOwnerFullName());
        account.addNewTransaction(account.getAccountNumber(), Transaction.TransactionType.PAYMENT, "Credit Payment of " + amount);
    }

    /**
     * Processes a recompense (loan repayment or adjustment) for the credit account.
     *
     * @param account The account to recompense.
     * @param amount  The amount to be recompensed.
     */
    public void creditRecompenseProcess(Account account, double amount) {
        if (account == null || amount <= 0) {
            System.out.println("Invalid recompense request.");
            return;
        }
        System.out.println("Recompensed credit of " + amount + " for " + account.getOwnerFullName());
        account.addNewTransaction(account.getAccountNumber(), Transaction.TransactionType.RECOMPENSE, "Credit Recompense of " + amount);
    }

    /**
     * Retrieves the currently logged-in credit account.
     *
     * @return The logged-in account or null if no account is logged in.
     */
    public Account getLoggedAccount() {
        if (loggedAccount == null) {
            System.out.println("No account is currently logged in.");
            return null;
        }
        System.out.println("Fetching logged-in credit account: " + loggedAccount.getOwnerFullName());
        return loggedAccount;
    }

    /**
     * Logs in a credit account (dummy implementation for testing).
     *
     * @param account The account to be logged in.
     */
    public void login(Account account) {
        if (account == null) {
            System.out.println("Invalid account login attempt.");
            return;
        }
        this.loggedAccount = account;
        System.out.println("Successfully logged in: " + account.getOwnerFullName());
    }

    /**
     * Logs out the currently logged-in credit account.
     */
    public void logout() {
        if (loggedAccount == null) {
            System.out.println("No account is logged in.");
            return;
        }
        System.out.println("Logging out: " + loggedAccount.getOwnerFullName());
        loggedAccount = null;
    }
}
