package account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class containing Transaction details.
 */
public class Transaction {

    public enum TransactionType {  // Renamed from `Transactions`
        Deposit,
        Withdraw,
        FundTransfer,
        Payment,
        Recompense
    }

    /**
     * Account number that triggered this transaction.
     */
    private String accountNumber;
    /**
     * Type of transaction that was triggered.
     */
    private TransactionType transactionType;
    /**
     * Description of the transaction.
     */
    private String description;
    /**
     * Timestamp of when the transaction occurred.
     */
    private String timestamp;

    public Transaction(String accountNumber, TransactionType transactionType, String description) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.description = description;
        this.timestamp = getCurrentTimestamp(); // Store transaction timestamp
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionType getTransactionType() {  // Updated return type
        return transactionType;
    }

    public String getDescription() {  // ✅ Added method
        return description;
    }

    public String getTimestamp() {  // ✅ Added method
        return timestamp;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        this.accountNumber = accountNumber;
    }

    public void setTransactionType(TransactionType transactionType) {  // Updated parameter type
        this.transactionType = transactionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String getCurrentTimestamp() {  // ✅ Added method to get current date/time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Account Number='" + accountNumber + '\'' +
                ", Transaction Type=" + transactionType +
                ", Description='" + description + '\'' +
                ", Timestamp='" + timestamp + '\'' +
                '}';
    }
}
