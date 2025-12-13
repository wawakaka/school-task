public class Main {
    public static void main(String[] args) {
        RestaurantQueue queue = new RestaurantQueue();

        queue.push("Joko");
        queue.push("Wito");
        queue.push("Shamira");

        queue.pop();
        queue.push("Anastasia");
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop(); // queue kosong
    }

    static class Node {
        String customerName;
        Node next;

        Node(String customerName) {
            this.customerName = customerName;
            this.next = null;
        }
    }

    static class RestaurantQueue {
        private Node head;
        private Node tail;

        public RestaurantQueue() {
            head = null;
            tail = null;
        }

        // Push (Enqueue)
        public void push(String customerName) {
            Node newNode = new Node(customerName);

            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }

            System.out.println("Customer added: " + customerName);
            displayQueue();
        }

        // Pop (Dequeue)
        public void pop() {
            if (head == null) {
                System.out.println("Queue is empty. No customer to serve.");
                return;
            }

            System.out.println("Customer served: " + head.customerName);
            head = head.next;

            if (head == null) {
                tail = null;
            }

            displayQueue();
        }

        // Display Queue
        public void displayQueue() {
            if (head == null) {
                System.out.println("Queue is empty.\n");
                return;
            }

            System.out.print("Current Queue: ");
            Node current = head;
            while (current != null) {
                System.out.print(current.customerName + " -> ");
                current = current.next;
            }
            System.out.println("null\n");
        }
    }
}
