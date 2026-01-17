class DeletionDoublyLinkedList {

    static class DoublyLinkedList {
        Node head, tail;

        public static void main(String[] args) {
            DoublyLinkedList dll = new DoublyLinkedList();
            dll.addLast(10);
            dll.addLast(20);
            dll.addLast(30);
            dll.addLast(40);

            dll.printForward();        // 10 <-> 20 <-> 30 <-> 40

            dll.deleteFirst();
            dll.printForward();        // 20 <-> 30 <-> 40

            dll.deleteLast();
            dll.printForward();        // 20 <-> 30

            dll.deleteValue(30);
            dll.printForward();        // 20

            dll.deleteAt(0);
            dll.printForward();        // (empty line)
        }

        // Append at end (helper for demo)
        void addLast(int value) {
            System.out.println("add last "+ value);
            Node n = new Node(value);
            if (head == null) {
                head = tail = n;
                return;
            }
            tail.next = n;
            n.prev = tail;
            tail = n;
        }

        // 1) Delete first node
        void deleteFirst() {
            System.out.println("delete first");
            if (head == null) return;          // empty
            if (head == tail) {                // single node
                head = tail = null;
                return;
            }
            head = head.next;
            head.prev = null;
        }

        // 2) Delete last node
        void deleteLast() {
            System.out.println("delete last");
            if (tail == null) return;          // empty
            if (head == tail) {                // single node
                head = tail = null;
                return;
            }
            tail = tail.prev;
            tail.next = null;
        }

        // 3) Delete first occurrence of a value
        boolean deleteValue(int value) {
            System.out.println("delete value "+ value);
            Node cur = head;
            while (cur != null && cur.data != value) cur = cur.next;
            if (cur == null) return false;     // not found

            if (cur == head) {
                deleteFirst();
            } else if (cur == tail) {
                deleteLast();
            } else {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
            }
            return true;
        }

        // 4) Delete by position (0-based index)
        boolean deleteAt(int index) {
            System.out.println("delete at index "+ index);
            if (index < 0) return false;
            Node cur = head;
            for (int i = 0; i < index && cur != null; i++) cur = cur.next;
            if (cur == null) return false;

            if (cur == head) {
                deleteFirst();
            } else if (cur == tail) {
                deleteLast();
            } else {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
            }
            return true;
        }

        void printForward() {
            for (Node cur = head; cur != null; cur = cur.next) {
                System.out.print(cur.data + (cur.next != null ? " <-> " : ""));
            }
            System.out.println();
        }

        static class Node {
            int data;
            Node prev, next;

            Node(int data) {
                this.data = data;
            }
        }
    }
}