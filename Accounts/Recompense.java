package Accounts;

/**
 * Interface for recompensing loan amounts to the bank.
 */
public interface Recompense {

    /**
     * Recompenses a specified amount to the bank, reducing the loan balance.
     *
     * Cannot proceed if:
     * - The recompense amount exceeds the current outstanding loan.
     * - The amount is negative or zero.
     *
     * @param amount The amount to be recompensed.
     * @return `true` if the recompense is successful, `false` otherwise.
     */
    boolean recompense(double amount);
}
