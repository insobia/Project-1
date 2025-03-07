package Bank;

import Account.Account;

public class BankLauncher {
    public static void main (String[] args)
    {
        Bank myBank = new Bank();


        //create accounts

        Account acc1 = new Account("123456", "John Doe", 5000.0, "Savings");
        Account acc2 = new Account("789012", "Alice Smith", 3000.0, "Checking");

        //add accounts to bank
        myBank.addAccount(acc1);
        myBank.addAccount(acc2);

        //perform transactions
        acc1.deposit(1000);
        acc1.withdraw(2000);
        acc1.transfer(acc2, 1500);

        //display all acounts
        myBank.displayAccounts();
    }
}
