package Bank;

import java.util.Comparator;

/**
 * Comparator for sorting banks alphabetically by name.
 */
public class BankComparator implements Comparator<Bank> {

    @Override
    public int compare(Bank b1, Bank b2) {
        return b1.getName().compareTo(b2.getName());
    }
}
