package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Bank.Bank;
import Accounts.Account;
import Accounts.SavingsAccount;
import Accounts.CreditAccount;
import Accounts.IllegalAccountType;

/**
 * The main class for running the Banking System.
 */
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final List<Bank> banks = new ArrayList<>();
    private static final List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            showMenuHeader("Main Menu");
            showMenu();
            int option = getOption();
            input.nextLine();

            switch (option) {
                case 1 -> loginAccount();
                case 2 -> manageBank();
                case 3 -> createBank();
                case 4 -> {
                    System.out.println("Exiting. Thank you for banking!");
                    return;
                }
                default -> System.out.println("Invalid option! Please try again.");
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
        System.out.println("[1] View Banks\n[2] Create New Bank\n[3] Exit");
        int option = getOption();
        input.nextLine();

        switch (option) {
            case 1 -> showBanks();
            case 2 -> createBank();
            case 3 -> System.out.println("Returning to main menu...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void createBank() {
        showMenuHeader("Create New Bank");
        System.out.print("Enter Bank Name: ");
        String bankName = input.nextLine();
        Bank newBank = new Bank(banks.size() + 1, bankName, "1234");
        banks.add(newBank);
        System.out.println("Bank '" + bankName + "' created successfully!");
    }

    private static void manageAccount(Account account) {
        while (true) {
            showMenuHeader("Account Menu");
            System.out.println("[1] View Transaction History\n[2] Deposit\n[3] Withdraw\n[4] Transfer Funds\n[5] Make Payment\n[6] Logout");
            int option = getOption();
            input.nextLine();

            switch (option) {
                case 1 -> System.out.println(account.getTransactionHistory());
                case 2 -> performTransaction(account, "deposit");
                case 3 -> performTransaction(account, "withdraw");
                case 4 -> performFundTransfer(account);
                case 5 -> {
                    try {
                        performPayment(account);
                    } catch (IllegalAccountType e) {
                        System.out.println("Payment failed: " + e.getMessage());
                    }
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void performTransaction(Account account, String transactionType) {
        double amount = getPositiveAmount(transactionType);

        if (transactionType.equals("deposit")) {
            if (account.deposit(amount)) {
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Deposit failed.");
            }
        } else if (transactionType.equals("withdraw")) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Withdrawal failed.");
            }
        }
    }

    private static void performFundTransfer(Account sender) {
        System.out.print("Enter Target Account Number: ");
        String targetAccNum = input.nextLine();
        Account recipient = findAccount(targetAccNum);

        if (recipient == null) {
            System.out.println("Target account not found!");
            return;
        }

        double amount = getPositiveAmount("fund transfer");

        try {
            if (sender.fundTransfer(recipient, amount)) {
                System.out.println("Transfer successful!");
            } else {
                System.out.println("Transfer failed.");
            }
        } catch (IllegalAccountType e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    private static void performPayment(Account payer) throws IllegalAccountType {
        System.out.print("Enter Target Account Number: ");
        String targetAccNum = input.nextLine();
        Account payee = findAccount(targetAccNum);

        if (payee == null) {
            System.out.println("Payee account not found!");
            return;
        }

        double amount = getPositiveAmount("payment");

        try {
            if (payer instanceof CreditAccount && payee instanceof SavingsAccount) {
                boolean success = ((CreditAccount) payer).pay(payee, amount);
                if (success) {
                    System.out.println("Payment successful!");
                } else {
                    System.out.println("Payment failed due to insufficient funds or invalid amount.");
                }
            } else {
                System.out.println("Payments can only be made from Credit to Savings Accounts.");
            }
        } catch (IllegalAccountType e) {
            System.out.println("Payment failed: " + e.getMessage());
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

    private static void showBanks() {
        if (banks.isEmpty()) {
            System.out.println("No banks available.");
            return;
        }
        System.out.println("\nAvailable Banks:");
        for (Bank bank : banks) {
            System.out.println("- " + bank.getName());
        }
    }

    public static void showMenu() {
        System.out.println("[1] Login to Account\n[2] Manage Banks\n[3] Create New Bank\n[4] Exit");
    }

    public static int getOption() {
        System.out.print("Select an option: ");
        while (!input.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            input.next();
        }

        int option = input.nextInt();
        input.nextLine();

        if (option <= 0) {
            System.out.println("Please enter a positive number.");
            return getOption();
        }

        return option;
    }


    private static double getPositiveAmount(String action) {
        double amount;
        while (true) {
            System.out.print("Enter Amount for " + action + ": ");
            if (input.hasNextDouble()) {
                amount = input.nextDouble();
                input.nextLine();
                if (amount > 0) return amount;
                System.out.println("Amount must be greater than zero.");
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                input.next();
            }
        }
    }

    public static void showMenuHeader(String menuTitle) {
        System.out.printf("\n<---- %s ----->\n", menuTitle);
    }
}