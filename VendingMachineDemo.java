/**
 * Demo class to showcase the State Pattern implementation
 */
public class VendingMachineDemo {
    public static void main(String[] args) {
        System.out.println("=" .repeat(60));
        System.out.println("     VENDING MACHINE STATE PATTERN DEMO");
        System.out.println("=" .repeat(60));
        
        VendingMachine machine = new VendingMachine();
        
        // Display initial status
        machine.displayStatus();
        
        // Test Scenario 1: Successful purchase flow
        System.out.println("\n📌 TEST SCENARIO 1: Successful Purchase");
        System.out.println("-".repeat(40));
        machine.selectItem("Soda");
        machine.insertCoin(100);
        machine.insertCoin(50);
        
        // Test Scenario 2: Invalid operations in different states
        System.out.println("\n📌 TEST SCENARIO 2: Invalid Operations");
        System.out.println("-".repeat(40));
        machine.insertCoin(25);              // Idle state - should show error
        machine.selectItem("Chips");         // Idle -> ItemSelected
        machine.selectItem("Candy");          // ItemSelected - should show error
        machine.dispenseItem();               // Should dispense if balance sufficient
        
        // Test Scenario 3: Insufficient balance
        System.out.println("\n📌 TEST SCENARIO 3: Insufficient Balance");
        System.out.println("-".repeat(40));
        machine.selectItem("Chocolate");
        machine.insertCoin(100);              // Insufficient for Chocolate (needs 200)
        
        // Test Scenario 4: Out of order scenario
        System.out.println("\n📌 TEST SCENARIO 4: Out of Order State");
        System.out.println("-".repeat(40));
        machine.setOutOfOrder();
        machine.selectItem("Water");
        machine.insertCoin(50);
        
        // Test Scenario 5: Trying to use out of order machine
        System.out.println("\n📌 TEST SCENARIO 5: Cannot Use Out of Order Machine");
        System.out.println("-".repeat(40));
        machine.selectItem("Soda");
        machine.dispenseItem();
        
        // Display final inventory
        machine.displayStatus();
        
        System.out.println("\n" + "=" .repeat(60));
        System.out.println("     DEMO COMPLETED SUCCESSFULLY");
        System.out.println("=" .repeat(60));
    }
}