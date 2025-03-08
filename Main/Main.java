package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Banking.Bank;
import Banking.Account;
import Banking.SavingsAccount;
import Banking.CreditAccount;
import Transactions.*;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static List<Bank> banks = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();
    
    public static Field<Integer, Integer> option = new Field<>("Option", Integer.class, -1, new Field.IntegerFieldValidator());

    public static void main(String[] args) {
        while (true) {
            showMenuHeader("Main Menu");
            showMenu(1);
            setOption();

            switch (getOption()) {
                case 1 -> loginAccount();
                case 2 -> manageBank();
                case 3 -> createBank();
                case 4 -> {
                    System.out.println("Exiting. Thank you for banking!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void loginAccount() {
        showMenuHeader("Account Login Menu");
        System.out.print("Enter Account Number: ");
        String accNumber = input.nextLine();

        Account account = findAccount(accNumber);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.println("Login successful!");
        manageAccount(account);
    }

    private static void manageBank() {
        showMenuHeader("Bank Services");
        showMenu(3, 1);
        setOption();

        switch (getOption()) {
            case 1 -> System.out.println("Checking Balance...");
            case 2 -> System.out.println("Processing a transaction...");
            default -> System.out.println("Invalid choice for Bank Services");
        }
    }

    private static void createBank() {
        showMenuHeader("Create New Bank");
        System.out.print("Enter your Bank Name: ");
        String bankName = input.nextLine();
        Bank bank = new Bank(bankName);
        banks.add(bank);
        System.out.println("Bank '" + bankName + "' has been created successfully!");
    }

    private static void manageAccount(Account account) {
        while (true) {
            showMenuHeader("Account Menu");
            showMenu(4);
            setOption();

            switch (getOption()) {
                case 1 -> System.out.println(account.getAccountBalanceStatement());
                case 2 -> performTransaction(account, new Deposit());
                case 3 -> performTransaction(account, new Withdrawal());
                case 4 -> performTransaction(account, new FundTransfer());
                case 5 -> performTransaction(account, new Payment());
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void performTransaction(Account account, Transaction transaction) {
        System.out.print("Enter Amount: ");
        double amount = input.nextDouble();
        input.nextLine(); // Consume newline

        if (transaction instanceof Deposit deposit) {
            deposit.execute(account, amount);
        } else if (transaction instanceof Withdrawal withdrawal) {
            withdrawal.execute(account, amount);
        } else if (transaction instanceof FundTransfer transfer) {
            System.out.print("Enter Target Account Number: ");
            String targetAccNum = input.nextLine();
            Account targetAccount = findAccount(targetAccNum);

            if (targetAccount != null) {
                transfer.execute(account, targetAccount, amount);
            } else {
                System.out.println("Target account not found!");
            }
        } else if (transaction instanceof Payment payment) {
            payment.execute(account, amount);
        }
    }

    private static Account findAccount(String accNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNumber)) {
                return acc;
            }
        }
        return null;
    }

    public static void showMenu(int menuIdx) {
        showMenu(menuIdx, 1);
    }

    public static void showMenu(int menuIdx, int inlineTexts) {
        String[] menu = Menu.getMenuOptions(menuIdx);
        if (menu == null) {
            System.out.println("Invalid menu index given!");
            return;
        }

        String fmt = "[%d] %-20s";
        int count = 0;
        for (String s : menu) {
            count++;
            System.out.printf(fmt, count, s);
            if (count % inlineTexts == 0) {
                System.out.println();
            }
        }
    }

    public static void setOption() {
        option.setFieldValue("Select an option: ");
    }

    public static int getOption() {
        return option.getFieldValue();
    }

    public static void showMenuHeader(String menuTitle) {
        System.out.printf("<---- %s ----->\n", menuTitle);
    }
}
