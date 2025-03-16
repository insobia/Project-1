package Main;

/**
 * An enumeration of menu options for different menus in the Banking System.
 */
public enum Menu {
    /** Main menu options */
    MainMenu(new String[]{"Accounts Login", "Bank Login", "Create New Bank", "Exit"}, 1),
    
    /** Account login menu */
    AccountLogin(new String[]{"Accounts Login", "Go Back"}, 2),
    
    /** Bank login menu */
    BankLogin(new String[]{"Login", "Go Back"}, 3),
    
    /** Bank menu after successful login */
    BankMenu(new String[]{"Show Accounts", "New Accounts", "Log Out"}, 31),
    
    /** Menu for showing a particular set of accounts */
    ShowAccounts(new String[]{"Credit Accounts", "Savings Accounts", "All Accounts", "Go Back"}, 32),

    /** Account type selection when creating new accounts */
    AccountTypeSelection(new String[]{"Credit Account", "Savings Account"}, 33),
    
    /** Credit account menu options */
    CreditAccountMenu(new String[]{"Show Credits", "Pay", "Recompense", "Show Transactions", "Logout"}, 41),
    
    /** Savings account menu options */
    SavingsAccountMenu(new String[]{"Show Balance", "Deposit", "Withdraw", "Fund Transfer", "Show Transactions", "Logout"}, 51);

    /** Stores menu options */
    private final String[] menuOptions;
    
    /** Unique identifier for each menu */
    private final int menuIdx;

    /**
     * Constructor for the menu enum.
     *
     * @param menuOptions List of options for the menu.
     * @param menuIdx The index associated with this menu.
     */
    Menu(String[] menuOptions, int menuIdx) {
        this.menuOptions = menuOptions;
        this.menuIdx = menuIdx;
    }

    /**
     * Retrieves the menu options based on the menu index.
     *
     * @param menuIdx The menu index to search for.
     * @return The menu options if found, otherwise an empty array.
     */
    public static String[] getMenuOptions(int menuIdx) {
        for (Menu menu : Menu.values()) {
            if (menu.menuIdx == menuIdx) {
                return menu.menuOptions;
            }
        }
        return new String[] {}; // ✅ Returns an empty array instead of null
    }
}
