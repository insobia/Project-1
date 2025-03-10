import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Interface for Persistence (DIP Applied)
interface DataPersistence {
    void saveTransaction(String accountNumber, double amount, String type);
    void updateBalance(String accountNumber, double newBalance);
    double getBalance(String accountNumber);
}

// SQLite Implementation
class SQLitePersistence implements DataPersistence {
    private Connection connect() {
        String url = "jdbc:sqlite:bank.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public void saveTransaction(String accountNumber, double amount, String type) {
        String sql = "INSERT INTO transactions(account_number, amount, type) VALUES(?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public double getBalance(String accountNumber) {
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }
}

// Bank Account Class
abstract class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected DataPersistence persistence;
    private Lock lock = new ReentrantLock();

    public BankAccount(String accountNumber, double balance, DataPersistence persistence) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.persistence = persistence;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            persistence.updateBalance(accountNumber, balance);
            persistence.saveTransaction(accountNumber, amount, "Deposit");
        } finally {
            lock.unlock();
        }
    }

    public abstract void withdraw(double amount);
}

// Savings Account
class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountNumber, double balance, DataPersistence persistence) {
        super(accountNumber, balance, persistence);
    }

    @Override
    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                persistence.updateBalance(accountNumber, balance);
                persistence.saveTransaction(accountNumber, amount, "Withdrawal");
            } else {
                System.out.println("Insufficient funds!");
            }
        } finally {
            lock.unlock();
        }
    }
}

// Multithreading Simulation
class BankTransactionSimulator implements Runnable {
    private BankAccount account;
    private boolean deposit;
    private double amount;

    public BankTransactionSimulator(BankAccount account, boolean deposit, double amount) {
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (deposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

// SavingsAccountLauncher
public class SavingsAccountLauncher {
    public static void main(String[] args) {
        DataPersistence persistence = new SQLitePersistence();
        SavingsAccount account = new SavingsAccount("12345", 1000, persistence);
        
        Thread t1 = new Thread(new BankTransactionSimulator(account, true, 500));
        Thread t2 = new Thread(new BankTransactionSimulator(account, false, 200));
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final Balance: " + persistence.getBalance("12345"));
    }
}
