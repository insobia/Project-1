class SQLitePersistence implements DataPersistence {
    public SQLitePersistence() {
        setupDatabase(); 
    }

    private Connection connect() {
        String url = "jdbc:sqlite:bank.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e.getMessage());
        }
        return conn;
    }

    private void setupDatabase() {  
        String createAccounts = "CREATE TABLE IF NOT EXISTS accounts ("
                                + "account_number TEXT PRIMARY KEY,"
                                + "balance REAL NOT NULL);";
        String createTransactions = "CREATE TABLE IF NOT EXISTS transactions ("
                                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + "account_number TEXT,"
                                + "amount REAL,"
                                + "type TEXT,"
                                + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createAccounts);
            stmt.execute(createTransactions);
        } catch (SQLException e) {
            System.out.println("Database Setup Error: " + e.getMessage());
        }
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
            System.out.println("Transaction Error: " + e.getMessage());
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
            System.out.println("Update Balance Error: " + e.getMessage());
        }
    }

    @Override
    public synchronized double getBalance(String accountNumber) { 
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            System.out.println("Get Balance Error: " + e.getMessage());
        }
        return 0.0;
    }
}
