package Accounts;

/**
 * Interface for withdrawing money from an account.
 */
public interface Withdrawal {

    /**
     * Withdraws a specified amount from an account.
     *
     * @param amount The amount to be withdrawn.
     * @return `true` if the withdrawal is successful, `false` otherwise.
     */
    boolean withdraw(double amount);
}
