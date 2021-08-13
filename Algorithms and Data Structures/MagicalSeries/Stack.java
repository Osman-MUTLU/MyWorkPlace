package Midterm;

public class Stack {
    private Object[] stacks;
    private int top;
    Stack(int capacity) {
        this.stacks = new Object[capacity];
        this.top = -1;
    }
    boolean isEmpty(){
        return top == -1;
    }
    int size(){
        return top+1;
    }
    boolean isFull(){
        return top+1 == stacks.length;
    }
    void Push(Object data){
        if (!isFull()){
            top++;
            stacks[top] = data;

        }
        else System.out.println("Stack overflow!!");
    }
    Object Pop(){
        Object retData = null;
        if(!isEmpty()) {
            retData = stacks[top];
            top--;
        }
        else System.out.println("Stack is empty");
        return retData;
    }
    Object Peek(){
        Object retData = null;
        if(!isEmpty()) {
            retData = stacks[top];
        }
        else System.out.println("Stack is empty");
        return retData;
    }


}
