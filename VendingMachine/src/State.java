public interface State {
    public void insertNote(Note note);
    public void insertCoin(Coin coin);
    public void dispenseProduct();
    public void selectProduct(Product product);
    public void returnChange();
}