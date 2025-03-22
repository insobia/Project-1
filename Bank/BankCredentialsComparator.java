package Bank;

import java.util.Comparator;

/**
 * Comparator for sorting banks based on passcodes.
 */
public class BankCredentialsComparator implements Comparator<Bank> {

    @Override
    public int compare(Bank b1, Bank b2) {
        return b1.getPasscode().compareTo(b2.getPasscode());
    }
}
