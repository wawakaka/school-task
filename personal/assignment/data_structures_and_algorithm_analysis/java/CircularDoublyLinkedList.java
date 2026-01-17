class CircularDoublyLinkedList {

    private Node head; // points to any node; we’ll keep it as the first node

    // ---- Example usage ----
    public static void main(String[] args) {
        CircularDoublyLinkedList cdll = new CircularDoublyLinkedList();

        cdll.insertEnd(10);
        cdll.insertEnd(20);
        cdll.insertFront(5);
        cdll.insertEnd(30);

        cdll.display();   // 5 10 20 30

        cdll.deleteValue(20);
        cdll.display();   // 5 10 30
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Insert at end
    public void insertEnd(int data) {
        Node newNode = new Node(data);
        System.out.println("insert new node " + data);

        // Empty list: point to itself
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
            return;
        }

        Node tail = head.prev; // in CDLL, head.prev is the tail

        // Link: tail <-> newNode <-> head
        newNode.next = head;
        newNode.prev = tail;
        tail.next = newNode;
        head.prev = newNode;
    }

    // Insert at front
    public void insertFront(int data) {
        insertEnd(data);
        System.out.println(data + " became head");
        head = head.prev; // newly inserted node becomes head
    }

    // Display once around the circle, starting from head (front)
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node cur = head;
        System.out.print("List: ");
        do {
            System.out.print(cur.data + " ");
            cur = cur.next;
        } while (cur != head);
        System.out.println();
    }

    // Delete first node that matches value
    public boolean deleteValue(int value) {
        if (head == null) return false;

        Node cur = head;
        do {
            if (cur.data == value) {

                // Case: only one node
                if (cur.next == cur) {
                    head = null;
                    return true;
                }

                // Remove cur by linking neighbors
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;

                // If deleting head, move head
                if (cur == head) {
                    head = cur.next;
                }
                return true;
            }
            cur = cur.next;
        } while (cur != head);

        return false; // not found
    }

    // Node for Circular Doubly Linked List
    static class Node {
        int data;
        Node next, prev;

        Node(int data) {
            this.data = data;
        }
    }
}