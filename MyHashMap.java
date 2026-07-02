public class MyHashMap<K, V> {

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new MyHashMap.Node[16];
        size = 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % table.length;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node newNode = new Node(key, value);

        if (table[index] == null) {
            table[index] = newNode;
            size++;
        } else {
            Node current = table[index];
            while (true) {
                if ((current.key == null && key == null) ||
                        (current.key != null && current.key.equals(key))) {
                    current.value = value;
                    return;
                }
                if (current.next == null) {
                    break;
                }
                current = current.next;
            }
            current.next = newNode;
            size++;
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node current = table[index];

        while (current != null) {
            if ((current.key == null && key == null) ||
                    (current.key != null && current.key.equals(key))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if ((current.key == null && key == null) ||
                    (current.key != null && current.key.equals(key))) {

                V removedValue = current.value;

                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return removedValue;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}