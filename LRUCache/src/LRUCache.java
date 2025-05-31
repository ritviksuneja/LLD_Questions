import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private int capacity;
    private final Map<K, Node<K, V>> cache;
    private DLL<K, V> dll;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DLL<>();
    }

    public V get(K key){
        Node<K, V> node = cache.getOrDefault(key, null);
        if(node == null){
            return null;
        }

        dll.remove(node);
        dll.addFirst(node);
        return node.getValue();
    }

    public void put(K key, V value){
        if(cache.containsKey(key)){
            Node<K, V> node = cache.get(key);
            node.setValue(value);
            dll.remove(node);
            dll.addFirst(node);
        }
        else{
            if (cache.size() >= capacity) {
                Node<K, V> lru = dll.removeLast();
                if (lru != null) {
                    cache.remove(lru.getKey());
                }
            }
            Node<K, V> newNode = new Node<>(key, value);
            dll.addFirst(newNode);
            cache.put(key, newNode);
        }
    }
}
