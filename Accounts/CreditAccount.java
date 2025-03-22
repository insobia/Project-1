package Accounts;

import Bank.Bank;

/**
 * Represents a credit account in the banking system.
 */
public class CreditAccount extends Account {
    private double loan;


    public CreditAccount() {
        super(new Bank(0, "Default Bank", "0000"), "000000", "Default", "User", "default@email.com", "1234", 0.0);
        this.loan = 0.0;
    }


    public CreditAccount(Bank bank, String accountNumber, String firstName, String lastName, String email, String pin) {
        super(bank,
                accountNumber != null ? accountNumber : "000000",
                firstName != null ? firstName : "Default",
                lastName != null ? lastName : "User",
                email != null ? email : "default@email.com",
                pin != null ? pin : "1234",
                0.0);
        this.loan = 0.0;
    }

    public CreditAccount(Bank bank) {
        super(bank, "000000", "Default", "User", "default@email.com", "1234", 0.0);
        this.loan = 0.0;
    }

    public CreditAccount(Bank bank, String accountNumber, String firstName, String lastName, String email, String pin, double balance) {
        super(bank, accountNumber, firstName, lastName, email, pin, balance);
        this.loan = 0.0;
    }


    public boolean pay(Account recipient, double amount) throws IllegalAccountType {
        if (!(recipient instanceof SavingsAccount)) {
            throw new IllegalAccountType("Credit payments can only be made to Savings Accounts.");
        }

        if (amount <= 0) {
            System.out.println("Payment failed: Amount must be greater than zero.");
            return false;
        }

        if (this.balance < amount) {
            System.out.println("Payment failed: Insufficient balance.");
            return false;
        }

        this.balance -= amount;
        recipient.deposit(amount);
        recordTransaction(new Transaction(this.accountNumber, Transaction.TransactionType.PAYMENT,
                "Paid: " + amount + " to " + recipient.getOwnerFullName()));
        System.out.println("Payment of " + amount + " to " + recipient.getOwnerFullName() + " successful.");
        return true;
    }

    /**
     * Repays loan without going negative.
     */
    public boolean recompense(double amount) {
        if (amount <= 0) {
            System.out.println("Recompense failed: Amount must be greater than zero.");
            return false;
        }

        if (amount > this.loan) {
            System.out.println("Recompense failed: Amount exceeds loan balance.");
            return false;
        }

        this.loan -= amount;
        recordTransaction(new Transaction(this.accountNumber, Transaction.TransactionType.RECOMPENSE,
                "Recompensed loan of " + amount));
        System.out.println("Recompensed loan of: " + amount);
        return true;
    }

    /**
     * Returns current loan amount.
     */
    public double getLoan() {
        return this.loan;
    }


    @Override
    public boolean fundTransfer(Account recipient, double amount) throws IllegalAccountType {
        if (!(recipient instanceof SavingsAccount || recipient instanceof CreditAccount)) {
            throw new IllegalAccountType("Transfers can only be made to Savings or Credit accounts.");
        }

        if (amount <= 0 || amount > this.balance) {
            System.out.println("Transfer failed: Insufficient balance.");
            return false;
        }

        this.balance -= amount;
        recipient.deposit(amount);
        recordTransaction(new Transaction(this.accountNumber, Transaction.TransactionType.TRANSFER,
                "Transferred: " + amount + " to " + recipient.getOwnerFullName()));
        System.out.println("Transfer successful to " + recipient.getOwnerFullName());
        return true;
    }


    @Override
    public String toString() {
        return super.toString() + String.format("\nLoan Balance: %.2f", loan);
    }
}