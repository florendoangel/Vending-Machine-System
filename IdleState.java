/**
 * Idle State - Only allows item selection
 */
class IdleState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String item) {
        if (machine.hasItem(item)) {
            System.out.println("✓ Item '" + item + "' selected. Please insert coins.");
            machine.setSelectedItem(item);
            machine.setState(new ItemSelectedState());
        } else {
            System.out.println("✗ Item '" + item + "' is not available.");
        }
    }
    
    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("✗ Please select an item first.");
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("✗ Please select an item first.");
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("⚠ Vending machine is now out of order.");
        machine.setState(new OutOfOrderState());
    }
}