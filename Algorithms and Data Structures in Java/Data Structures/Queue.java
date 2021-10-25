public class Queue<T> {
    private final int DEFAULT_CAPACITY = 100;
    private int rear;
    private T[] queue;

   public QueueArray()
   {
      rear = 0;
      queue = (T[])(new Object[DEFAULT_CAPACITY]);
   }

   public QueueArray (int initialCapacity)
   {
      rear = 0;
      queue = (T[])(new Object[initialCapacity]);
   }

    public void enqueue(T element) {
        if (size() == queue.length)
            System.out.println("it is full");

        queue[rear] = element;
        rear++;
    }

    public T dequeue() {
        if (isEmpty())
            System.out.println("Empty");

        T result = queue[0];

        rear--;

        for (int scan = 0; scan < rear; scan++)
            queue[scan] = queue[scan + 1];

        queue[rear] = null;

        return result;
    }

    public T first() {
        if (rear == 0)
            System.out.println("Empty");
        return queue[0];
    }

    public boolean isEmpty() {
        return rear == 0;
    }

    public int size() {
        return rear;
    }

    public String toString() {
        String result = "";
        for (int k = 0; k < rear; k++) {
            result += queue[k] + "\n";
        }
        return result;
    }
}
