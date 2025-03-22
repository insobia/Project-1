package Accounts;

/**
 * Represents a transaction in the banking system.
 */
public class Transaction {
    private final String accountNumber;
    private final TransactionType type;
    private final String description;

    /**
     * Enum representing transaction types.
     */
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, PAYMENT, RECOMPENSE, LOAN, TRANSFER
    }

    /**
     * Constructs a Transaction.
     */
    public Transaction(String accountNumber, TransactionType type, String description) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.description = description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns transaction details as a formatted string.
     */
    public String getDetails() {
        return String.format("Account: %s | Type: %s | Details: %s", accountNumber, type, description);
    }
}

