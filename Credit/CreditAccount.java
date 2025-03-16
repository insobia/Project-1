package Credit;

import Accounts.Payment;
import Accounts.Recompense;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a credit account in the banking system.
 * Implements Payment and Recompense interfaces.
 */
public class CreditAccount implements Payment, Recompense {
    private double loan;
    private final String bank;
    private final String accountNumber;
    private final String accountHolder;
    private final String branch;
    private final String type;
    private final String currency;
    private final List<String> transactionHistory;

    // Constructor
    public CreditAccount(String bank, String accountNumber, String accountHolder, String branch, String type, String currency) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.branch = branch;
        this.type = type;
        this.currency = currency;
        this.loan = 0.0; // Default loan amount
        this.transactionHistory = new ArrayList<>();
    }

    // Get Loan Statement
    public String getLoanStatement() {
        return "Loan Statement: Bank: " + bank + ", Account: " + accountNumber + ", Loan Amount: " + loan;
    }

    // Check if credit is possible
    public boolean canCredit(double amountAdjustment) {
        return amountAdjustment > 0;
    }

    // Adjust Loan Amount
    public void adjustLoanAmount(double amountAdjustment) {
        if (canCredit(amountAdjustment)) {
            this.loan += amountAdjustment;
            recordTransaction("Loan Adjustment: +" + amountAdjustment);
        } else {
            System.out.println("Invalid loan adjustment.");
        }
    }

    // Implement processPayment from Payment Interface
    @Override
    public void processPayment(double amount, String targetAccountType) {
        if (!targetAccountType.equalsIgnoreCase("savings")) {
            System.out.println("Credit payment can only be made to a savings account.");
            return;
        }

        if (amount > 0 && amount <= loan) {
            loan -= amount;
            recordTransaction("Credit Payment: -" + amount + " to Savings Account");
            System.out.println("Payment of " + amount + " processed. Remaining loan: " + loan);
        } else {
            System.out.println("Invalid payment amount.");
        }
    }

    // Implement calculateReward from Recompense Interface
    @Override
    public double calculateReward() {
        return loan * 0.02; // Example: 2% reward
    }

    // Record Transactions
    private void recordTransaction(String transactionDetail) {
        transactionHistory.add(transactionDetail);
    }

    // Display Transaction History
    public void printTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }

    // Convert Object to String Representation
    @Override
    public String toString() {
        return "CreditAccount{" +
                "loan=" + loan +
                ", bank='" + bank + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", branch='" + branch + '\'' +
                ", type='" + type + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
