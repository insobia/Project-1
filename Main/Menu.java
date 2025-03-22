package Main;

/**
 * An enumeration of menu options for different menus in the Banking System.
 */
public enum Menu {
    /** Main menu options */
    MAIN_MENU(new String[]{"Account Login", "Bank Login", "Create New Bank", "Exit"}, 1),

    /** Bank login menu */
    BANK_LOGIN(new String[]{"Login", "Create Bank", "Exit"}, 2),

    /** Bank menu after successful login */
    BANK_MENU(new String[]{"Show Accounts", "New Accounts", "View Transactions", "Log Out"}, 3),

    /** Account management menu */
    ACCOUNT_MENU(new String[]{"Show Balance", "Deposit", "Withdraw", "Fund Transfer", "View Transactions", "Logout"}, 4);

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
     * Retrieves the menu index.
     */
    public int getMenuIdx() {
        return menuIdx;
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
        System.out.println("Menu not found!");
        return new String[]{};
    }


    public void printMenu() {
        System.out.println("\n--- " + this.name().replace("_", " ") + " ---");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println("[" + (i + 1) + "] " + menuOptions[i]);
        }
    }

    /**
     * @param name    Name of the custom menu.
     * @param options Menu options for the custom menu.
     * @return A formatted string representing the custom menu.
     */
    public static String createCustomMenu(String name, String[] options) {
        StringBuilder menuBuilder = new StringBuilder("\n--- " + name.toUpperCase() + " ---\n");
        for (int i = 0; i < options.length; i++) {
            menuBuilder.append("[").append(i + 1).append("] ").append(options[i]).append("\n");
        }
        return menuBuilder.toString();
    }
}