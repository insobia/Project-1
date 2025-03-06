package Accounts;

/**
 * Class containing Transaction enums.
 */
public class Transaction {

    public enum Transactions {
        Deposit,
        Withdraw,
        FundTransfer,
        Payment,
        Recompense
    }

    /**
     * Account number that triggered this transaction.
     */
    public String accountNumber;
    /**
     * Type of transcation that was triggered.
     */
    public Transactions transactionType;
    /**
     * Description of the transaction.
     */
    public String description;

    public Transaction(String accountNumber, Transactions transactionType, String description) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.description = description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Transactions getTransactionType() {
        return transactionType;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        this.accountNumber = accountNumber;
    }

    public void setTransactionType(Transactions transactionType) {
        this.transactionType = transactionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Transaction{" +
                "Account Number='" + accountNumber + '\'' +
                ", Transaction Type=" + transactionType +
                ", Description='" + description + '\'' +
                '}';
    }
}