package Accounts;

import Bank.Bank;
import account.Account;

public interface FundTransfer {

    /**
     * Transfer money between different banks.
     * 
     * Each transfer applies a processing fee based on the source account's bank.
     * 
     * Cannot proceed if:
     * - Insufficient balance from the source account.
     * - Account types are incompatible for fund transfer purposes.
     * 
     * @param sourceAccount The sender's account.
     * @param recipientBank The recipient's bank.
     * @param recipientAccount The recipient's account.
     * @param amount The amount to be transferred.
     * @return `true` if the transaction is successful, otherwise `false`.
     * @throws IllegalAccountType If fund transfer occurs between incompatible account types.
     */
    public boolean transfer(Account sourceAccount, Bank recipientBank, Account recipientAccount, double amount) throws IllegalAccountType;

    /**
     * Transfer money within the same bank.
     * 
     * Cannot proceed if:
     * - Insufficient balance from the source account.
     * - Recipient account does not exist.
     * 
     * @param sourceAccount The sender's account.
     * @param recipientAccount The recipient's account.
     * @param amount The amount to be transferred.
     * @return `true` if the transaction is successful, otherwise `false`.
     * @throws IllegalAccountType If fund transfer occurs between incompatible account types.
     */
    public boolean transfer(Account sourceAccount, Account recipientAccount, double amount) throws IllegalAccountType;
}
