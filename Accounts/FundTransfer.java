package Accounts;

import Bank.Bank;

/**
 * Interface for transferring funds between accounts.
 */
public interface FundTransfer {

    /**
     * Transfers funds between different banks.
     *
     * This transaction applies a processing fee based on the source account's bank.
     *
     * Cannot proceed if:
     * - The source account has insufficient funds.
     * - The account types are incompatible for fund transfer.
     *
     * @param sourceAccount The sender's account.
     * @param recipientBank The recipient's bank.
     * @param recipientAccount The recipient's account.
     * @param amount The amount to be transferred.
     * @return `true` if the transaction is successful, `false` otherwise.
     * @throws IllegalAccountType If the fund transfer occurs between incompatible account types.
     */
    boolean transfer(Account sourceAccount, Bank recipientBank, Account recipientAccount, double amount) throws IllegalAccountType;

    /**
     * Transfers funds within the same bank.
     *
     * Cannot proceed if:
     * - The source account has insufficient funds.
     * - The recipient account does not exist.
     *
     * @param sourceAccount The sender's account.
     * @param recipientAccount The recipient's account.
     * @param amount The amount to be transferred.
     * @return `true` if the transaction is successful, `false` otherwise.
     * @throws IllegalAccountType If the fund transfer occurs between incompatible account types.
     */
    boolean transfer(Account sourceAccount, Account recipientAccount, double amount) throws IllegalAccountType;
}
