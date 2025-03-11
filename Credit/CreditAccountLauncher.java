package Credit;

import account.Account;

public class CreditAccountLauncher {

    // Initialize the credit account
    public void creditAccountInit() {
        System.out.println("Initializing Credit Account...");
    }

    // Process a credit payment for the given account
    public void creditPaymentProcess(Account account, double amount) {
        if (account == null || amount <= 0) {
            System.out.println("Invalid payment request.");
            return;
        }
        System.out.println("Processed credit payment of " + amount + " for " + account.getOwnerFullName());
        account.addNewTransaction(account.getAccountNumber(), Transaction.TransactionType.PAYMENT, "Credit Payment of " + amount);
    }

    // Process recompense (refund or adjustment) for the credit account
    public void creditRecompenseProcess(Account account, double amount) {
        if (account == null || amount <= 0) {
            System.out.println("Invalid recompense request.");
            return;
        }
        System.out.println("Recompensed credit of " + amount + " for " + account.getOwnerFullName());
        account.addNewTransaction(account.getAccountNumber(), Transaction.TransactionType.RECOMPENSE, "Credit Recompense of " + amount);
    }

    // Retrieve the currently logged-in credit account
    public Account getLoggedAccount() {
        // Assuming there is some authentication mechanism to retrieve the logged-in user
        System.out.println("Fetching logged-in credit account...");
        return new Account(); // Replace this with actual logic to get logged-in account
    }
}

