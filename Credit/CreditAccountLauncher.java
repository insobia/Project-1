package Credit;

import Accounts.Account;
import Accounts.Transaction;
import Accounts.Transaction.TransactionType;
import Accounts.CreditAccount;

/**
 * Launcher for Credit Account functionalities.
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
     */
    public void creditPaymentProcess(Account account, double amount) {
        if (account == null || amount <= 0) {
            System.out.println("Invalid payment request.");
            return;
        }


        if (!(account instanceof CreditAccount)) {
            System.out.println("Only credit accounts can process credit payments.");
            return;
        }

        System.out.println("Processed credit payment of " + amount + " for " + account.getOwnerFullName());


        account.recordTransaction(new Transaction(account.getAccountNumber(), TransactionType.PAYMENT,
                "Credit Payment of " + amount));
    }

    /**
     * Processes a recompense (loan repayment).
     */
    public void creditRecompenseProcess(Account account, double amount) {
        if (account == null || amount <= 0) {
            System.out.println("Invalid recompense request.");
            return;
        }


        if (!(account instanceof CreditAccount)) {
            System.out.println("Only credit accounts support recompense.");
            return;
        }

        System.out.println("Recompensed credit of " + amount + " for " + account.getOwnerFullName());


        account.recordTransaction(new Transaction(account.getAccountNumber(), TransactionType.RECOMPENSE,
                "Credit Recompense of " + amount));
    }

    /**
     * Retrieves the currently logged-in credit account.
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
     * Logs in a credit account.
     */
    public void login(Account account) {
        if (account == null) {
            System.out.println("Invalid account login attempt.");
            return;
        }


        if (!(account instanceof CreditAccount)) {
            System.out.println("Only credit accounts can log in.");
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