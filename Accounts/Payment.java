package Accounts;

/**
 * Interface for processing payments between accounts.
 */
public interface Payment {

    /**
     * Pays a certain amount of money into a given account.
     *
     * This is different from Fund Transfer, as payments do not have any processing fees.
     *
     * Cannot proceed if:
     * - The source account has insufficient funds.
     * - The recipient account is incompatible for payment.
     *
     * @param account The target account to receive the payment.
     * @param amount The amount to be paid.
     * @return `true` if the payment is successful, `false` otherwise.
     * @throws IllegalAccountType If payment is attempted between incompatible account types.
     */
    boolean pay(Account account, double amount) throws IllegalAccountType;
}
