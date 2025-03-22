package Bank;

import Accounts.Account;
import Accounts.SavingsAccount;
import Accounts.CreditAccount;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/**
 * Handles bank creation, login, and account management.
 */
public class BankLauncher {
    private static List<Bank> BANKS = new ArrayList<>();
    private static Bank loggedBank = null;

    public static void main(String[] args) {
        bankInit();
        showBanksMenu();
    }

    /**
     * Initializes the banking system with some preloaded banks.
     */
    public static void bankInit() {
        System.out.println("Initializing bank system...");
        try {
            BANKS.add(new Bank(1, "Alpha Bank", hashPassword("alpha123")));
            BANKS.add(new Bank(2, "Beta Bank", hashPassword("beta456")));
        } catch (Exception e) {
            System.out.println("Error initializing banks: " + e.getMessage());
        }
    }

    /**
     * Hashes passwords.
     */
    public static String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    /**
     * Displays the bank selection menu.
     */
    public static void showBanksMenu() {
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("[1] Login to Bank");
            System.out.println("[2] Create New Bank");
            System.out.println("[3] Exit");
            System.out.print("Select an option: ");

            int option = getOption();
            switch (option) {
                case 1 -> bankLogin();
                case 2 -> createNewBank();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Handles bank login with hashed passcode validation.
     */
    public static void bankLogin() {
        System.out.print("Enter Bank Name: ");
        String bankName = System.console().readLine();
        System.out.print("Enter Passcode: ");
        String passcode;
        try {
            passcode = hashPassword(System.console().readLine());
        } catch (Exception e) {
            System.out.println("Error hashing password.");
            return;
        }

        for (Bank bank : BANKS) {
            if (bank.getName().equals(bankName) && bank.getPasscode().equals(passcode)) {
                loggedBank = bank;
                System.out.println("Login successful!");
                bankMenu();
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    /**
     * Logs out the current bank.
     */
    public static void logout() {
        System.out.println("Logging out...");
        loggedBank = null;
    }

    /**
     * Displays the bank menu for logged-in banks.
     */
    public static void bankMenu() {
        while (true) {
            System.out.println("\n--- Bank Services ---");
            System.out.println("[1] View Accounts");
            System.out.println("[2] Create New Account");
            System.out.println("[3] View Transactions");
            System.out.println("[4] Logout");
            System.out.print("Select an option: ");

            int option = getOption();
            switch (option) {
                case 1 -> showAccounts();
                case 2 -> newAccounts();
                case 3 -> viewTransactions();
                case 4 -> logout();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Handles the creation of a new bank, prevents duplicates.
     */
    public static void createNewBank() {
        System.out.print("Enter Bank Name: ");
        String bankName = System.console().readLine();

        // Prevent duplicate bank names
        for (Bank b : BANKS) {
            if (b.getName().equalsIgnoreCase(bankName)) {
                System.out.println("Bank name already exists.");
                return;
            }
        }

        System.out.print("Enter Passcode: ");
        try {
            String passcode = hashPassword(System.console().readLine());
            Bank newBank = new Bank(BANKS.size() + 1, bankName, passcode);
            BANKS.add(newBank);
            System.out.println("Bank Created Successfully!");
        } catch (Exception e) {
            System.out.println("Error creating bank: " + e.getMessage());
        }
    }

    /**
     * Displays all accounts within the logged-in bank.
     */
    public static void showAccounts() {
        if (loggedBank == null) {
            System.out.println("No bank is logged in.");
            return;
        }
        System.out.println("\nAccounts in " + loggedBank.getName() + ":");
        for (Account acc : loggedBank.getBankAccounts()) {
            System.out.println(acc);
        }
    }


    public static void viewTransactions() {
        if (loggedBank == null) {
            System.out.println("No bank is logged in.");
            return;
        }
        System.out.println("\nTransaction History for " + loggedBank.getName() + ":");
        System.out.println(loggedBank.getTransactionHistory());
    }

    /**
     * Allows the creation of a new account, prevents duplicates.
     */
    public static void newAccounts() {
        if (loggedBank == null) {
            System.out.println("No bank is logged in.");
            return;
        }

        System.out.print("Enter Account Number: ");
        String accountNumber = System.console().readLine();

        // Prevent duplicate account numbers
        if (findAccount(accountNumber) != null) {
            System.out.println("Account number already exists.");
            return;
        }

        System.out.print("Enter First Name: ");
        String firstName = System.console().readLine();
        System.out.print("Enter Last Name: ");
        String lastName = System.console().readLine();
        System.out.print("Enter Email: ");
        String email = System.console().readLine();
        System.out.print("Enter PIN: ");
        String pin = System.console().readLine();
        System.out.print("Enter Initial Balance: ");
        double balance = Double.parseDouble(System.console().readLine());
        System.out.print("Enter Account Type (Savings/Credit): ");
        String accountType = System.console().readLine();

        Account newAccount;
        if (accountType.equalsIgnoreCase("savings")) {
            newAccount = new SavingsAccount(loggedBank, accountNumber, firstName, lastName, email, pin, balance);
        } else if (accountType.equalsIgnoreCase("credit")) {
            newAccount = new CreditAccount(loggedBank, accountNumber, firstName, lastName, email, pin, balance);
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        loggedBank.addNewAccount(newAccount);
        System.out.println("New Account Created!");
    }

    /**
     * Finds an account by its account number.
     */
    public static Account findAccount(String accountNumber) {
        for (Bank bank : BANKS) {
            for (Account acc : bank.getBankAccounts()) {
                if (acc.getAccountNumber().equals(accountNumber)) {
                    return acc;
                }
            }
        }
        return null;
    }

    /**
     * Gets an integer input from the user.
     */
    public static int getOption() {
        int option;
        while (true) {
            try {
                option = Integer.parseInt(System.console().readLine());
                return option;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}