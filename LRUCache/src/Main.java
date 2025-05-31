public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache.get(2)); // B
        cache.put(4, "D"); // Evicts key 1
        System.out.println(cache.get(1)); // null
        System.out.println(cache.get(3)); // C
    }
}