package Bank;
import Account.Account;
import java.util.ArrayList;
import java.util.List;


public class Bank {
    private List<Account> accounts; //Store multiple accounts

    public Bank()
    {
        this.accounts = new ArrayList<>();

    }

    public void addAccount(Account account)
    {
        accounts.add(account);
        System.out.println("Account added: " + account.getAccountHolder());
    }

    //Find account by account number

    public Account findAccount(String accountNumber)
    {
        for (Account acc : accounts)
        {
            if (acc.getAccountNumber().equals(accountNumber))
            {
                return acc;
            }
        }
        return null; //account not found

    }

    //display all accounts
    public void displayAccounts()
    {
        if (accounts.isEmpty())
        {
            System.out.println("No accounts available,");
            return;
        }
        System.out.println("\nBank Account");
        for (Account acc : accounts)
        {
            System.out.println(acc);
        }
    }




}
