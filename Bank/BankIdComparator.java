package Bank;

import java.util.Comparator;

/**
 * Comparator for sorting banks based on their unique ID.
 */
public class BankIdComparator implements Comparator<Bank> {

    @Override
    public int compare(Bank b1, Bank b2) {
        return Integer.compare(b1.getID(), b2.getID());
    }
}
