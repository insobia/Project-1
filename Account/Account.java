package Account;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType; //saving, checking ug unsa pa
    private boolean isActive;


    //Constructor
    public Account(String accountNumber, String accountHolderName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
        this.isActive = true; // default to active

    }

    //Deposit money
    public void deposit(double amount)
    {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        }
        else
        {
            System.out.println("Invalid deposit amount.");
        }
    }

    //withdraw money
    public boolean withdraw(double amount)
    {
        if (!isActive)
        {
            System.out.println("Account is inactive. Withdrawal failed");
            return false;
        }

        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        }
        else
        {
            System.out.println("Insufficient balance or invalid amount");
            return false;
        }
    }

    //Transfer monet to another account

    public boolean transfer (Account targetAccount, double amount)
    {
        if (withdraw(amount))
        {
            targetAccount.deposit(amount);
            System.out.println("Transferred $" + amount + "to" + targetAccount.getAccountHolder());
            return true;
        }
        return false;
    }


    //check balance
    public double checkBalance()
    {
        return balance;
    }


    //getters
    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountHolder()
    {
        return accountHolderName;
    }

    public boolean isActive()
    {
        return isActive;
    }

    //Set account Status
    public void deactivateAccount()
    {
        isActive = false;
        System.out.println("Account has been deactivated");
    }

    public void activateAccount()
    {
        isActive = true;
        System.out.println("Account has been activated");
    }

    @Override
    public String toString()
    {
        return "Account{" +
                "accountNumber =' " +accountNumber + '\''+
                ", accountHolderName = '" + accountHolderName + '\'' +
                ", balance =" + balance +
                ", accountType = '" + accountType + '\'' +
                ", isActive = " + isActive +
                '}';
    }
}

