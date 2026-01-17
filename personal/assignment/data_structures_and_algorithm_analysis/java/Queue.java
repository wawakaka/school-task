public class Queue {
    private int[] arr;
    private int front, rear;
    private int maxSize;

    public Queue(int size) {
        arr = new int[size];
        front = -1;
        rear = -1;
        maxSize = size;
    }

    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Dequeued: " + q.dequeue());
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full!");
            return;
        }
        if (front == -1) front = 0;
        rear++;
        arr[rear] = value;
        System.out.println("Enqueued: " + value);
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        int dequeuedValue = arr[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front++;
        }
        return dequeuedValue;
    }
}