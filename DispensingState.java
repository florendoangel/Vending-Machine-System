/**
 * Dispensing State - No operations allowed, auto-transitions to Idle
 */
class DispensingState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String item) {
        System.out.println("✗ Dispensing in progress. Please wait.");
    }
    
    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("✗ Dispensing in progress. Cannot accept coins.");
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        // Perform actual dispensing
        if (machine.hasItem(machine.getSelectedItem()) && 
            machine.getBalance() >= machine.getSelectedItemPrice()) {
            
            // Dispense item
            String item = machine.getSelectedItem();
            int price = machine.getSelectedItemPrice();
            machine.reduceInventory(item);
            machine.deductBalance(price);
            
            System.out.println("✅ " + item + " dispensed successfully!");
            
            // Return change if any
            int change = machine.getBalance();
            if (change > 0) {
                System.out.println("💵 Please collect your change: " + change + " cents");
                machine.refundBalance();
            }
            
            // Transition to Idle state
            machine.clearSelectedItem();
            System.out.println("🔄 Returning to idle state...");
            machine.setState(new IdleState());
        } else {
            System.out.println("❌ Error dispensing item. Please contact support.");
            machine.setState(new OutOfOrderState());
        }
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("✗ Machine is dispensing. Cannot set out of order now.");
    }
}