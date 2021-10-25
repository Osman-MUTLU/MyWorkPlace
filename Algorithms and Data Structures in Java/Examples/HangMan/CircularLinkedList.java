public class CircularLinkedList {
    CircularNode head;

    public CircularLinkedList() {
        head = null;
    }
    public void add(char dataToAdd){
        if(head == null){
            CircularNode newNode = new CircularNode(dataToAdd);
            head = newNode;
            head.setLink(head);
        }
        else{
            CircularNode temp = head;
            while(!temp.getLink().equals(head)){
                temp = temp.getLink();
            }
            CircularNode newNode = new CircularNode(dataToAdd);
            newNode.setLink(head);
            temp.setLink(newNode);
        }
    }
    public boolean search(char item){
        if(head == null){
            System.out.println("The list is empty!");
        }
        else {
            CircularNode temp = head;
            do {
                if(temp.getData().equals(item))return true;
                temp = temp.getLink();
            } while(temp!=head);
        }
        return false;
    }
    public int size(){
        if(head == null){
            System.out.println("The list is empty!");
            return 0;
        }
        else{
            CircularNode temp = head;
            int counter = 0;
            do {
                counter++;
                temp = temp.getLink();
            }while (temp != head);
            return counter;
        }
    }
    public void display(){
        if(head == null){
            System.out.println("The list is empty!");
        }
        else{
            CircularNode temp = head;
            while(temp.getLink()!=head){
                System.out.print((char)temp.getData()+" ");
                temp = temp.getLink();
            }
            System.out.print((char)temp.getData()+" ");
        }
    }
}
