public class SingleLinkedList {
    public ScoreNode head;

    public SingleLinkedList() {
        this.head = null;
    }
    public void add(String name,int score){
        //Insert the name and score to list. Will be sorting scores.
        if(head == null){
            ScoreNode newNode = new ScoreNode(name,score);
            head = newNode;
        }
        else{
            ScoreNode newNode = new ScoreNode(name,score);
            ScoreNode temp = head;

            if(temp.getScore() < score){ //Insert head.
                newNode.setNext(temp);
                head = newNode;
            }
            else{
                boolean flag =true;
                while(temp.getNext() != null){ //Insert middle.
                    ScoreNode prev = temp;
                    temp = temp.getNext();
                    if(temp.getScore() < score){
                        prev.setNext(newNode);
                        newNode.setNext(temp);
                        flag = false;
                        break;
                    }
                }
                if(flag){//Insert tail.
                    temp.setNext(newNode);
                }
            }
        }
    }
    public void display(){
        if(head == null){
            System.out.println("The list is empty!");
        }
        else{
            int counter = 1;
            ScoreNode temp = head;
            while (temp!= null) {
                System.out.println(counter + "-) "+temp.getName()+" "+temp.getScore());
                counter++;
                temp = temp.getNext();
            }
        }
    }
}
