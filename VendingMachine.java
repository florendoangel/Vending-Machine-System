import java.util.*;

/**
 * Vending Machine Context class - Main class that delegates actions to state objects
 */
class VendingMachine {
    private VendingMachineState state;
    private Map<String, Item> inventory;
    private String selectedItem;
    private int balance;
    
    public VendingMachine() {
        // Initialize with Idle state
        this.state = new IdleState();
        this.inventory = new HashMap<>();
        this.balance = 0;
        this.selectedItem = null;
        
        // Initialize with sample items
        initializeInventory();
    }
    
    private void initializeInventory() {
        inventory.put("Soda", new Item("Soda", 150, 10));
        inventory.put("Chips", new Item("Chips", 100, 15));
        inventory.put("Candy", new Item("Candy", 75, 20));
        inventory.put("Water", new Item("Water", 125, 8));
        inventory.put("Chocolate", new Item("Chocolate", 200, 5));
    }
    
    // Delegate actions to current state
    public void selectItem(String item) {
        System.out.print("Action: Select '" + item + "' → ");
        state.selectItem(this, item);
    }
    
    public void insertCoin(int amount) {
        System.out.print("Action: Insert " + amount + " cents → ");
        state.insertCoin(this, amount);
    }
    
    public void dispenseItem() {
        System.out.print("Action: Dispense → ");
        state.dispenseItem(this);
    }
    
    public void setOutOfOrder() {
        System.out.print("Action: Set Out of Order → ");
        state.setOutOfOrder(this);
    }
    
    // State management
    public void setState(VendingMachineState state) {
        this.state = state;
    }
    
    public String getCurrentStateName() {
        return state.getClass().getSimpleName();
    }
    
    // Inventory management
    public boolean hasItem(String itemName) {
        Item item = inventory.get(itemName);
        return item != null && item.getQuantity() > 0;
    }
    
    public void reduceInventory(String itemName) {
        Item item = inventory.get(itemName);
        if (item != null) {
            item.setQuantity(item.getQuantity() - 1);
        }
    }
    
    public int getSelectedItemPrice() {
        if (selectedItem != null) {
            Item item = inventory.get(selectedItem);
            return item != null ? item.getPrice() : 0;
        }
        return 0;
    }
    
    // Getters and Setters
    public String getSelectedItem() {
        return selectedItem;
    }
    
    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    public void clearSelectedItem() {
        this.selectedItem = null;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public void addBalance(int amount) {
        this.balance += amount;
    }
    
    public void deductBalance(int amount) {
        this.balance -= amount;
    }
    
    public void refundBalance() {
        this.balance = 0;
    }
    
    public void displayStatus() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     VENDING MACHINE STATUS           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ Current State: " + padRight(getCurrentStateName(), 20) + " ║");
        System.out.println("║ Selected Item: " + padRight(selectedItem != null ? selectedItem : "None", 20) + " ║");
        System.out.println("║ Balance: " + padRight(balance + " cents", 24) + " ║");
        System.out.println("╚══════════════════════════════════════╝");
        
        System.out.println("\n📦 Available Items:");
        System.out.println("┌──────────┬────────┬────────────┐");
        System.out.println("│ Item     │ Price  │ Quantity   │");
        System.out.println("├──────────┼────────┼────────────┤");
        for (Item item : inventory.values()) {
            System.out.printf("│ %-8s │ %-6d │ %-10d │\n", 
                item.getName(), item.getPrice(), item.getQuantity());
        }
        System.out.println("└──────────┴────────┴────────────┘");
    }
    
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}