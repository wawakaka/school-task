class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            System.out.println("Invalid position: " + position);
            return;
        }

        if (position == 0) {
            insertAtHead(data);
            return;
        }

        if (position == size) {
            insertAtTail(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("=== Inserting at Head ===");
        list.insertAtHead(10);
        list.insertAtHead(20);
        list.insertAtHead(30);
        list.display();

        System.out.println("\n=== Inserting at Tail ===");
        list.insertAtTail(40);
        list.insertAtTail(50);
        list.display();

        System.out.println("\n=== Inserting in Middle ===");
        list.insertAtPosition(25, 2);
        list.display();

        list.insertAtPosition(5, 0);
        list.insertAtPosition(55, 7);
        list.display();

        System.out.println("\nList size: " + list.getSize());
    }
}