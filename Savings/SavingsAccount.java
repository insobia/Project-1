public class SavingsAccount extends BankAccount {
    private double interestRate; // Annual interest rate in percentage
    private int withdrawalLimit; // Number of withdrawals allowed per month
    private int withdrawalsThisMonth;

    // Constructor
    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate, int withdrawalLimit) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
        this.withdrawalLimit = withdrawalLimit;
        this.withdrawalsThisMonth = 0;
    }

    // Apply monthly interest
    public void applyInterest() {
        double interest = (getBalance() * (interestRate / 100)) / 12; // Monthly interest
        deposit(interest);
        System.out.println("Interest applied: " + interest);
    }

    // Override withdrawal method to enforce limits
    @Override
    public boolean withdraw(double amount) {
        if (withdrawalsThisMonth >= withdrawalLimit) {
            System.out.println("Withdrawal limit reached for this month.");
            return false;
        }
        if (super.withdraw(amount)) {
            withdrawalsThisMonth++;
            return true;
        }
        return false;
    }

    // Reset withdrawals (to be called at the beginning of each month)
    public void resetWithdrawals() {
        withdrawalsThisMonth = 0;
    }

    // Getters and Setters
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(int withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }
}

