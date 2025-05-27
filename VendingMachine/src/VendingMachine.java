// We need to define a singleton instance of the vending machine.
// We need to define all the states here, and all as final.
// We need to define the current state as a variable of this class.
// In the states, we pass the singleton instance as we need to associate state with the vending machine and change state from within the state. 
// The states will also have a variable called VendingMachine.
public class VendingMachine {
    private static VendingMachine instance;
    private Inventory inventory;
    private final State idleState;
    private final State readyState;
    private final State dispenseState;
    private final State returnChangeState;
    private State currentState;
    private double balance;
    private Product selectedProduct;

    public VendingMachine(){
        // We initialize all of the states here.
        this.inventory = new Inventory();
        this.idleState = new IdleState(this);
        this.readyState = new ReadyState(this);
        this.dispenseState = new DispenseState(this);
        this.returnChangeState = new ReturnChangeState(this);
        this.balance = 0;
        this.selectedProduct = null;
        this.currentState = this.idleState;
    }

    // The public static method to actually get the instance of the singleton class.
    // We need to have it synchronized for thread safety.
    // The following is how we do it for a typical singleton class.
    // The singleton instance is not final. Remember this!
    public static synchronized VendingMachine getInstance(){
        if(instance == null){
            instance = new VendingMachine();
        }

        return instance;
    }

    public void setState(State state){
        this.currentState = state;
    }

    public double showBalance(){
        return this.balance;
    }

    public Product addProduct(String name, double price, int quantity){
        Product product = new Product(name, price);
        inventory.addProduct(product, quantity);
        return product;
    }

    // We add the method which we are defining in individual states. Just that we are not handling them here.
    // Rather, we will let the individual states handle their execution.
    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void insertNote(Note note) {
        currentState.insertNote(note);
    }

    public void dispenseProduct(){
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    Inventory getInventory() {
        return inventory;
    }

    // We add the state getters as well in the singleton.
    // We will use them as -> vendingMachine.setState(vendingMachine.getDispensingState());
    // getIdleState() returns an actual instance of the state (created in the constructor).
    // These instances are pre-initialized and shared.
    // No new object is created on every transition — more efficient.
    State getIdleState() {
        return idleState;
    }

    State getReadyState() {
        return readyState;
    }

    State getDispenseState() {
        return dispenseState;
    }

    State getReturnChangeState() {
        return returnChangeState;
    }

    Product getSelectedProduct() {
        return selectedProduct;
    }

    void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    void resetSelectedProduct() {
        selectedProduct = null;
    }

    double getBalance() {
        return balance;
    }

    // These are ultimately called by the states as we need to keep track of the updated balance in the vending machine as well.
    void addCoin(Coin coin) {
        balance += coin.getValue();
    }

    void addNote(Note note) {
        balance += note.getValue();
    }

    void resetPayment() {
        balance = 0.0;
    }
}
