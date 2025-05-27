
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    
    // Map operations:
    // Initialize as new ConcurrentHashMap<>()
    // map.put(key, value)
    // map.remove(value)
    // map.get(key) -> returns value
    // map.getOrDefault(key, defaultValue)
    // map.containsKey(key) -> returns if the ket exists or not.

    private Map<Product, Integer> products;

    public Inventory(){
        products = new ConcurrentHashMap<>();
    }
    
    public void addProduct(Product product, int quantity){
        products.put(product, quantity);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public int getQuantity(Product product){
        return products.getOrDefault(product, 0);
    }

    public boolean isAvailable(Product product){
        return products.containsKey(product) && products.get(product) > 0;
    }
}
