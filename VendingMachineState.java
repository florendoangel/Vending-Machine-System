/**
 * State interface defining all possible actions in a vending machine
 */
interface VendingMachineState {
    void selectItem(VendingMachine machine, String item);
    void insertCoin(VendingMachine machine, int amount);
    void dispenseItem(VendingMachine machine);
    void setOutOfOrder(VendingMachine machine);
}