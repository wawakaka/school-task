class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    private Node head;
    private Node tail;

    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Insert at the end
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head; // Circular link
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Maintain circular link
        }
    }

    // Display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(back to head)");
    }

    // Delete a node by value
    public void delete(int key) {
        if (head == null) return;

        Node current = head;
        Node prev = tail; // because it's circular

        // If head is the only node
        if (head == tail && head.data == key) {
            head = tail = null;
            return;
        }

        // If head needs to be deleted
        if (head.data == key) {
            head = head.next;
            tail.next = head;
            return;
        }

        // Search for the node to delete
        while (current.data != key) {
            if (current.next == head) {
                System.out.println("Key not found.");
                return;
            }
            prev = current;
            current = current.next;
        }

        // Delete the node
        prev.next = current.next;
        if (current == tail) {
            tail = prev;
        }
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.insert(10);
        cll.insert(20);
        cll.insert(30);
        cll.display(); // Output: 10 -> 20 -> 30 -> (back to head)

        cll.delete(20);
        cll.display(); // Output: 10 -> 30 -> (back to head)
    }
}