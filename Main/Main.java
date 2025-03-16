package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Bank.Bank;
import account.Account;
import Accounts.Deposit;
import Accounts.Withdrawal;
import Accounts.FundTransfer;
import Accounts.Payment;
import Transactions.Transaction;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final List<Bank> banks = new ArrayList<>();
    private static final List<Account> accounts = new ArrayList<>();

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
                case 1 -> System.out.println(account.getTransactionInfo());
                case 2 -> performTransaction(account, new Deposit() {
                    @Override
                    public boolean cashDeposit(double amount) {
                        return account.deposit(amount);
                    }
                });
                case 3 -> performTransaction(account, new Withdrawal() {
                    @Override
                    public boolean withdrawal(double amount) {
                        return account.withdraw(amount);
                    }
                });
                case 4 -> performFundTransfer(account);
                case 5 -> performPayment(account);
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void performTransaction(Account account, Object transaction) {
        System.out.print("Enter Amount: ");
        double amount = input.nextDouble();
        input.nextLine(); // Consume newline

        if (transaction instanceof Deposit deposit) {
            if (deposit.cashDeposit(amount)) {
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Deposit failed.");
            }
        } else if (transaction instanceof Withdrawal withdrawal) {
            if (withdrawal.withdrawal(amount)) {
                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance or withdrawal limit reached.");
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

        System.out.print("Enter Transfer Amount: ");
        double amount = input.nextDouble();
        input.nextLine(); // Consume newline

        try {
            FundTransfer transfer = (sender1, recipient1, amount1) -> {
                if (sender1.getBalance() >= amount1) {
                    sender1.withdraw(amount1);
                    recipient1.deposit(amount1);
                    System.out.println("Fund transfer successful!");
                    return true;
                } else {
                    System.out.println("Insufficient balance.");
                    return false;
                }
            };
            transfer.transfer(sender, recipient, amount);
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    private static void performPayment(Account payer) {
        System.out.print("Enter Target Account Number: ");
        String targetAccNum = input.nextLine();
        Account payee = findAccount(targetAccNum);

        if (payee == null) {
            System.out.println("Payee account not found!");
            return;
        }

        System.out.print("Enter Payment Amount: ");
        double amount = input.nextDouble();
        input.nextLine(); // Consume newline

        try {
            Payment payment = (payee1, amount1) -> {
                if (payer.getBalance() >= amount1) {
                    payer.withdraw(amount1);
                    payee1.deposit(amount1);
                    System.out.println("Payment successful!");
                    return true;
                } else {
                    System.out.println("Insufficient balance.");
                    return false;
                }
            };
            payment.pay(payee, amount);
        } catch (Exception e) {
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
