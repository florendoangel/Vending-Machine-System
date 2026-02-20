/**
 * ItemSelected State - Allows coin insertion and item dispensing
 */
class ItemSelectedState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String item) {
        System.out.println("✗ Item already selected. Please insert coins or cancel.");
    }
    
    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        machine.addBalance(amount);
        System.out.println("💰 Inserted: " + amount + " cents. Total balance: " + machine.getBalance());
        
        // Check if sufficient balance
        if (machine.getBalance() >= machine.getSelectedItemPrice()) {
            System.out.println("✓ Sufficient balance. Dispensing item...");
            machine.setState(new DispensingState());
            machine.dispenseItem();
        }
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        if (machine.getBalance() >= machine.getSelectedItemPrice()) {
            System.out.println("🔄 Dispensing item...");
            machine.setState(new DispensingState());
            machine.dispenseItem();
        } else {
            int remaining = machine.getSelectedItemPrice() - machine.getBalance();
            System.out.println("✗ Insufficient balance. Please insert " + remaining + " more cents.");
        }
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("⚠ Vending machine is now out of order. Refunding balance: " + machine.getBalance());
        machine.refundBalance();
        machine.setState(new OutOfOrderState());
    }
}