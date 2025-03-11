package Bank;

import Main.Field;
import Main.Menu;
import Main.Main;
import account.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class BankLauncher {
    private static List<Bank> BANKS = new ArrayList<>();
    private static Bank loggedBank = null;

    public static void main(String[] args) {
        bankInit();
        showBanksMenu();
    }

    public static void bankInit() {
        System.out.println("Initializing bank system...");
        BANKS.add(new Bank(1, "Alpha Bank", "alpha123", 50000, 10000, 20000));
        BANKS.add(new Bank(2, "Beta Bank", "beta456", 60000, 15000, 30000));
    }

    public static void showBanksMenu() {
        while (true) {
            Main.showMenuHeader("Bank Menu");
            Main.showMenu(Menu.BankLogin.menuIdx, 1);
            Main.setOption();

            int option = Main.getOption();
            switch (option) {
                case 1:
                    bankLogin();
                    break;
                case 2:
                    createNewBank();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static boolean isLogged() {
        return loggedBank != null;
    }

    public static void bankLogin() {
        Main.showMenuHeader("Bank Login");
        Field<String, String> bankNameField = new Field<>("Bank Name", String.class, "", new Field.StringFieldValidator());
        bankNameField.setFieldValue("Enter Bank Name:");
        String bankName = bankNameField.getFieldValue();

        Field<String, Integer> passcodeField = new Field<>("Passcode", String.class, 6, new Field.StringFieldLengthValidator());
        passcodeField.setFieldValue("Enter Passcode:");
        String passcode = passcodeField.getFieldValue();

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

    public static void setLogSession(Bank b) {
        loggedBank = b;
    }

    public static void logout() {
        System.out.println("Logging out...");
        loggedBank = null;
    }

    public static void bankMenu() {
        while (true) {
            Main.showMenuHeader("Bank Services");
            Main.showMenu(Menu.BankMenu.menuIdx, 1);
            Main.setOption();

            int option = Main.getOption();
            switch (option) {
                case 1:
                    showAccounts();
                    break;
                case 2:
                    newAccounts();
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void createNewBank() {
        Main.showMenuHeader("Create New Bank");

        Field<String, String> bankNameField = new Field<>("Bank Name", String.class, "", new Field.StringFieldValidator());
        bankNameField.setFieldValue("Enter Bank Name:");
        String bankName = bankNameField.getFieldValue();

        Field<String, Integer> passcodeField = new Field<>("Passcode", String.class, 6, new Field.StringFieldLengthValidator());
        passcodeField.setFieldValue("Enter Passcode:");
        String passcode = passcodeField.getFieldValue();

        Field<Double, Double> depositLimitField = new Field<>("Deposit Limit", Double.class, 0.0, new Field.DoubleFieldValidator());
        depositLimitField.setFieldValue("Enter Deposit Limit:");
        double depositLimit = depositLimitField.getFieldValue();

        Field<Double, Double> withdrawLimitField = new Field<>("Withdraw Limit", Double.class, 0.0, new Field.DoubleFieldValidator());
        withdrawLimitField.setFieldValue("Enter Withdraw Limit:");
        double withdrawLimit = withdrawLimitField.getFieldValue();

        Field<Double, Double> creditLimitField = new Field<>("Credit Limit", Double.class, 0.0, new Field.DoubleFieldValidator());
        creditLimitField.setFieldValue("Enter Credit Limit:");
        double creditLimit = creditLimitField.getFieldValue();

        Bank newBank = new Bank(BANKS.size() + 1, bankName, passcode, depositLimit, withdrawLimit, creditLimit);
        BANKS.add(newBank);
        System.out.println("Bank Created Successfully!");
    }

    public static void addBank(Bank b) {
        BANKS.add(b);
    }

    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        return BANKS.stream()
                    .filter(b -> comparator.compare(b, bank) == 0)
                    .findFirst()
                    .orElse(null);
    }

    public static void showAccounts() {
        if (loggedBank == null) {
            System.out.println("No bank is logged in.");
            return;
        }
        loggedBank.displayAccounts();
    }

    public static void newAccounts() {
        if (loggedBank == null) {
            System.out.println("No bank is logged in.");
            return;
        }

        Field<String, String> accNumberField = new Field<>("Account Number", String.class, "", new Field.StringFieldValidator());
        accNumberField.setFieldValue("Enter Account Number:");
        String accountNumber = accNumberField.getFieldValue();

        Field<String, String> accHolderField = new Field<>("Account Holder", String.class, "", new Field.StringFieldValidator());
        accHolderField.setFieldValue("Enter Account Holder Name:");
        String accountHolder = accHolderField.getFieldValue();

        Field<Double, Double> balanceField = new Field<>("Balance", Double.class, 0.0, new Field.DoubleFieldValidator());
        balanceField.setFieldValue("Enter Initial Balance:");
        double balance = balanceField.getFieldValue();

        Field<String, String> accountTypeField = new Field<>("Account Type", String.class, "", new Field.StringFieldValidator());
        accountTypeField.setFieldValue("Enter Account Type (Savings/Credit):");
        String accountType = accountTypeField.getFieldValue();

        Account newAccount = new Account(loggedBank, accountNumber, accountHolder, "N/A", "N/A", "1234"); // Dummy Email & PIN
        loggedBank.addNewAccount(newAccount);
        System.out.println("New Account Created!");
    }

    public static Account findAccount(String accountNum) {
        for (Bank bank : BANKS) {
            Account account = bank.getAccount(accountNum);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

    public static int bankSize() {
        return BANKS.size();
    }

    public static void viewTransactions() {
        if (loggedBank == null) {
            System.out.println("No bank is logged in.");
            return;
        }
        System.out.println("\n" + loggedBank.getTransactionHistory());
    }
}
