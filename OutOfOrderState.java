/**
 * Out of Order State - No operations allowed
 */
class OutOfOrderState implements VendingMachineState {
    
    @Override
    public void selectItem(VendingMachine machine, String item) {
        System.out.println("✗ Machine is OUT OF ORDER. Cannot select items.");
    }
    
    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        System.out.println("✗ Machine is OUT OF ORDER. Cannot accept coins.");
    }
    
    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("✗ Machine is OUT OF ORDER. Cannot dispense items.");
    }
    
    @Override
    public void setOutOfOrder(VendingMachine machine) {
        System.out.println("✗ Machine is already out of order.");
    }
}