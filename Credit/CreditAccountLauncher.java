package Credit;
import account.Account;




public class CreditAccountLauncher {

    public void initialize() {
        System.out.println("Initializing Credit Account...");
    }

    public void processPayment(Account account, double amount) {
        System.out.println("Processed credit payment of " + amount + " for " + account.getOwnerFullName());
        account.addNewTransaction(account.accountNumber, Transaction.TransactionType.PAYMENT, "Credit Payment of " + amount);
    }

    public void processRecompense(Account account, double amount) {
        System.out.println("Recompensed credit of " + amount + " for " + account.getOwnerFullName());
        account.addNewTransaction(account.accountNumber, Transaction.TransactionType.RECOMPENSE, "Credit Recompense of " + amount);
    }
}

