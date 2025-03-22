package Accounts;

/**
 * Exception thrown when a banking operation involves incompatible account types.
 */
public class IllegalAccountType extends Exception {

    /**
     * Constructs an `IllegalAccountType` exception with a specific error message.
     *
     * @param errorMessage The detailed error message.
     */
    public IllegalAccountType(String errorMessage) {
        super(errorMessage);
    }


    public IllegalAccountType() {
        super("Operation not allowed between incompatible account types.");
    }
}
