public class Node<K, V> {
    private final K key;
    private V value;
    private Node<K, V> next;
    private Node<K, V> prev;

    public Node(K key, V value){
        this.key = key;
        this.value = value;
    }

    // Getters
    public K getKey() { return key; }
    public V getValue() { return value; }
    public Node<K, V> getPrev() { return prev; }
    public Node<K, V> getNext() { return next; }

    // Setters
    public void setValue(V value) { this.value = value; }
    public void setPrev(Node<K, V> prev) { this.prev = prev; }
    public void setNext(Node<K, V> next) { this.next = next; }
}
