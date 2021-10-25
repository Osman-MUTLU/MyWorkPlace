public class Stack<T> {

    private T[] arr;

    private int total;

    public StackArray(int cap)
    {
        arr = (T[]) new Object[cap];
    }

    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(arr, 0, tmp, 0, total);
        arr = tmp;
    }

    public StackArray<T> push(T a) {
        if (arr.length == total) {
            System.out.println("it is full");
            return null;
        }

        arr[total++] = a;
        return this;
    }

    public T pop() {
        if (total == 0) {
            System.out.println("it is empty");
            T ele = null;
            return ele;
        }

        T ele = arr[--total];
        arr[total] = null;
        return ele;
    }

    public String toString() {
        return java.util.Arrays.toString(arr);
    }
}
