class DoublyLinkedList {

    private Node head;
    private Node tail;

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        System.out.print("Display : ");
        dll.display();

        // Insert at head: 20, then 10 -> list: 10 <-> 20
        System.out.println("Insert 20 at the front");
        dll.insertFirst(20);
        System.out.println("Insert 10 at the front");
        dll.insertFirst(10);

        System.out.print("Display : ");
        dll.display();

        // Insert at tail: 30 -> list: 10 <-> 20 <-> 30
        System.out.println("Insert 30 at the back");
        dll.insertLast(30);

        System.out.print("Display : ");
        dll.display();

        // Insert after 20: add 25 -> list: 10 <-> 20 <-> 25 <-> 30
        boolean ok = dll.insertAfter(20, 25);
        System.out.println("Insert 25 after 20 " + ok);

        System.out.print("Display : ");
        dll.display();
    }

    // Insert at the front (head)
    public void insertFirst(int value) {
        Node node = new Node(value);

        if (head == null) { // empty list
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    // Insert at the end (tail)
    public void insertLast(int value) {
        Node node = new Node(value);

        if (tail == null) { // empty list
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    // Insert after the first occurrence of 'afterValue'
    // Returns true if insertion happened, false if afterValue not found.
    public boolean insertAfter(int afterValue, int valueToInsert) {
        Node current = head;

        while (current != null && current.data != afterValue) {
            current = current.next;
        }
        if (current == null) return false; // not found

        // if current is tail, this is just insertLast
        if (current == tail) {
            insertLast(valueToInsert);
            return true;
        }

        Node node = new Node(valueToInsert);

        Node nextNode = current.next;
        current.next = node;
        node.prev = current;

        node.next = nextNode;
        nextNode.prev = node;

        return true;
    }

    public void display() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data);
            if (cur.next != null) System.out.print(" <-> ");
            cur = cur.next;
        }
        System.out.println();
    }

    static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}