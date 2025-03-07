package Savings;
import account.Account;
import Accounts.Transaction;


public class SavingsAccountLauncher {
    public void initialize()
    {
        System.out.println("Initializing Savings Account....");
    }

    public void deposit(Account account, double amount)
    {
        System.out.println("Deposited" + amount + "to" + account.getOwnerFullName());
        account.addNewTransaction(account.accountNumber, Transaction.TransactionsType.DEPOSIT, "Deposit of " + amount);

    }

    public void withdraw(Account account, double amount)
    {
        System.out.println("Withdrew" + amount + "from" + account.getOwnerFullName());
        account.addNewTransaction(account.accountNumber, Transaction.TransactionTyoe.WITHDRAWAL, "Withdrew of" + amount);
    }

    public void transferFunds(Account sender, Account receiver, double amount)
    {
        System.out.println("Transferred" + amount + "from" + sender.getOwnerFullName() + "to" + receiver.getOwnerFullName());
        sender.addNewTransaction(sender.accountNumber, Transaction.TransactionsType.TRANSFER, " Transfer of " + amount + "to" + receiver.accountNumber);
        receiver.addNewTransaction(receiver.accountNumber, Transaction.TransactionsType.RECEIVE, "Received" + amount + "from" + sender.accountNumber);
    }
}
