public class DoubleLinkedList {
    private LetterNode head;
    private LetterNode tail;

    public DoubleLinkedList() {
        head = null;
        tail = null;
    }
    public void add(char dataToAdd){
        //Add the word sequentially.
        if(head == null&&tail == null){ //Insert the first node when empty.
            LetterNode newNode = new LetterNode(dataToAdd);
            head = newNode;
            tail = newNode;
        }
        else{
            LetterNode newNode = new LetterNode(dataToAdd);
            LetterNode temp = head;
            boolean flag = true;
            if((char)temp.getElement()>dataToAdd){//Inserts the first.
                temp.setPrev(newNode);
                newNode.setNext(temp);
                head = newNode;
            }
            else{
                while(temp.getNext()!= null){ //Inserts the middle.
                    LetterNode prev = temp;
                    temp = temp.getNext();
                    if((char)temp.getElement() > dataToAdd){
                        flag = false;
                        newNode.setNext(temp);
                        temp.setPrev(newNode);
                        prev.setNext(newNode);
                        newNode.setPrev(prev);
                        break;
                    }
                }
                if(flag){//Inserts the tail.
                    tail.setNext(newNode);
                    newNode.setPrev(tail);
                    tail = newNode;
                }
            }
        }
    }
    public void delete(char dataToDelete){
        if(head == null&& tail == null){
            System.out.println("The list is empty!");
        }
        else{
            LetterNode temp = head;
            LetterNode temp2 = tail;
            if((char)temp.getElement()== dataToDelete){//Delete the head.
                temp = temp.getNext();
                temp.setPrev(null);
                head = temp;
            }
            else if((char)temp2.getElement()== dataToDelete){//Delete the tail.
                temp2 = temp2.getPrev();
                temp2.setNext(null);
                tail = temp2;
            }
            else{//Delete to middle(position 'M' right or left).
                if((int)dataToDelete>(int)'M'){//search from tail.
                    temp2 = temp2.getPrev();
                    while (temp2!= null){
                        if((char)temp2.getElement()== dataToDelete){
                            temp = temp2.getPrev();
                            temp.setNext(temp2.getNext());
                            temp2.getNext().setPrev(temp);
                            break;
                        }
                        temp2 = temp2.getPrev();
                    }
                }
                else {//search from head.
                    temp = temp.getNext();
                    while (temp!= null){
                        if((char)temp.getElement()== dataToDelete){
                            temp2 = temp.getPrev();
                            temp2.setNext(temp.getNext());
                            temp.getNext().setPrev(temp2);
                            break;
                        }
                        temp = temp.getNext();
                    }
                }
            }
        }
    }
    public boolean search(char letter,boolean isDelete){
        //Search suggested letter from dll. It can delete the letter.
        boolean flag = false;
        if(head == null&& tail == null){
            System.out.println("The list is empty!");
        }
        else{
            LetterNode temp;
            if((int)letter>(int)'M'){//search from tail.
                temp = tail;
                while(temp!=null){
                    if((char)temp.getElement() == letter){
                        flag = true;
                        if(isDelete) delete(letter);
                        break;
                    }
                    temp = temp.getPrev();
                }
            }
            else{//search from head.
                temp = head;
                while (temp!=null){
                    if((char)temp.getElement() == letter){
                        flag = true;
                        if(isDelete) delete(letter);
                        break;
                    }
                    temp = temp.getNext();
                }
            }
        }

        return flag;
    }
    public int size(){
        if(head == null&& tail == null){
            System.out.println("The list is empty!");
            return 0;
        }
        else{
            int count = 0;
            LetterNode temp = head;
            while(temp != null){
                count++;
                temp = temp.getNext();
            }
            return count;
        }
    }
    public void display(){
        if(head == null&& tail == null){
            System.out.println("The list is empty!");
        }
        else{
            LetterNode temp = head;
            while(temp != null){
                System.out.print(temp.getElement());
                temp = temp.getNext();
            }
            System.out.println();
        }
    }
}
