public class DLL<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;

    public DLL(){
        head = new Node<K, V>(null, null);
        tail = new Node<K, V>(null, null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public void addFirst(Node<K, V> node){
        node.setNext(head.getNext());
        head.getNext().setPrev(node);
        head.setNext(node);
        node.setPrev(head);
    }

    public void remove(Node<K, V> node){
        Node<K, V> prevNode = node.getPrev();
        Node<K, V> nextNode = node.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);

        node.setPrev(null);
        node.setNext(null);
    }

    public Node<K, V> removeLast(){
        Node<K, V> node = tail.getPrev();
        
        if(node != head){
            node.getPrev().setNext(tail);
            tail.setPrev(node.getPrev());
            node.setNext(null);
            node.setPrev(null);
            return node;
        }

        return null;
    }
}
