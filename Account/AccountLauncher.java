package Account;

public class AccountLauncher {
    public static void main(String[] args) {
        //Create Accounts

        Account acc1 = new Account("123456", "John Doe", 5000.0, "Savings");
        Account acc2 = new Account("789012", "Alice Smith", 3000.0, "Checking");


        //display initial account details

        System.out.println("Initial Account Details: ");
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println();

        //Perform Transaction
        acc1.deposit(1000);
        acc1.withdraw(2000);
        acc1.transfer(acc2, 1500);

        //Display Updated account details
        System.out.println("\nUpdated Account Details: ");
        System.out.println(acc1);
        System.out.println(acc2);

    }

}
