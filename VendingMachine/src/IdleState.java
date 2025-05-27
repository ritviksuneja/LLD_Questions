public class IdleState implements State {
    
    // Not marking it as static here. Important!
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first.");
    }

    @Override
    public void selectProduct(Product product) {
        
        if (vendingMachine.getInventory().isAvailable(product)){
            System.out.println("Product selected: " + product.getName());
            vendingMachine.setSelectedProduct(product);
            vendingMachine.setState(vendingMachine.getReadyState());
        } 
        else{
            System.out.println("Product not available: " + product.getName());
        }

        this.vendingMachine.setState(this.vendingMachine.getReadyState());
    }

    @Override
    public void returnChange() {
        System.out.println("Please select a product first.");
    }
}
