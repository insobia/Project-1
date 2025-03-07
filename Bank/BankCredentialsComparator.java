import java.util.Comparator;

public class BankCredentialsComparator implements Comparator<Bank> {
    @Override
    public int compare(Bank b1, Bank b2) {
        return b1.getCredentials().compareTo(b2.getCredentials());
    }
}
