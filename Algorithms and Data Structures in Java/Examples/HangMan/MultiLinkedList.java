public class MultiLinkedList {
    private LengthNode head;

    public MultiLinkedList() {
        this.head = null;
    }
    public void addCountry(String addToData){
        //Add the country sequentially.
        if(head == null || head.getLength() > addToData.length()){
            //Insert the first lengthNode when list's empty
            // Or insert the new first lengthNode when head's value greater.Then,will be change head.
            LengthNode newNode = new LengthNode(addToData.length());
            CountryNode newNode2 = new CountryNode(addToData);
            newNode.setRight(newNode2);
            if(head != null) newNode.setDown(head);
            head = newNode;
        }
        else{
            LengthNode temp = head;
            boolean hasSameLength = false;
            while(temp != null){
                if(temp.getLength() == addToData.length()){
                    //Insert the country when selecting same length.
                    hasSameLength = true;
                    boolean hasInsert = false;
                    CountryNode temp2 = temp.getRight();
                    //Inserts the head and changes LengthNode's link.
                    for (int i = 0; i < addToData.length(); i++) {
                        if(addToData.charAt(i)>temp2.getName().charAt(i)) break;
                        else if(addToData.charAt(i)<temp2.getName().charAt(i)){
                            CountryNode addNode = new CountryNode(addToData);
                            addNode.setNext(temp2);
                            temp.setRight(addNode);
                            hasInsert = true;
                            break;
                        }
                    }
                    //Inserts the middle.
                    while (temp2.getNext() != null){
                        CountryNode prew = temp2;
                        temp2 = temp2.getNext();
                        for (int i = 0; i < addToData.length(); i++) {
                            if(hasInsert)break;
                            if(addToData.charAt(i)>temp2.getName().charAt(i)) break;
                            else if(addToData.charAt(i)<temp2.getName().charAt(i)){
                                CountryNode addNode = new CountryNode(addToData);
                                addNode.setNext(temp2);
                                prew.setNext(addNode);
                                hasInsert = true;
                                break;
                            }
                        }
                    }
                    //Inserts the last.
                    if(!hasInsert){
                        CountryNode addNode = new CountryNode(addToData);
                        temp2.setNext(addNode);
                    }
                    break;
                }
                temp = temp.getDown();
            }
            if(!hasSameLength){
                //When It's not same LengthNode.Creates new LengthNode.
                temp = head;
                while(temp.getDown() != null){
                    //Insert the middle.
                    LengthNode prew = temp;
                    temp = temp.getDown();
                    if(temp.getLength() > addToData.length()){
                        LengthNode newNode = new LengthNode(addToData.length());
                        CountryNode newNode2 = new CountryNode(addToData);
                        newNode.setRight(newNode2);
                        newNode.setDown(temp);
                        prew.setDown(newNode);
                        hasSameLength = true;
                        break;
                    }
                }
                if(!hasSameLength){
                    //Insert the last.
                    LengthNode newNode = new LengthNode(addToData.length());
                    CountryNode newNode2 = new CountryNode(addToData);
                    newNode.setRight(newNode2);
                    temp.setDown(newNode);
                }
            }
        }
    }
    public String selectedCountry(int n){ //Select the Nth country.
        if(head == null){
            System.out.println("The list is empty!");
            return null;
        }
        else{
            String country = null;
            LengthNode temp = head;
            int counter = 0;
            while(temp != null){
                CountryNode cn = temp.getRight();
                while(cn != null){
                    counter++;
                    if(n == counter){
                        country = cn.getName();
                    }
                    cn = cn.getNext();
                }
                temp = temp.getDown();
            }
            return country;
        }

    }
    public int size(){
        if(head == null){
            System.out.println("The list is empty!");
            return  0;
        }
        else {
            LengthNode temp = head;
            int counter = 0;
            while(temp != null){
                CountryNode cn = temp.getRight();
                while(cn != null){
                    counter++;
                    cn = cn.getNext();
                }
                temp = temp.getDown();
            }
            return counter;
        }
    }
    public void display(){
        if(head == null){
            System.out.println("The list is empty!");
        }
        else {
            LengthNode temp = head;
            while(temp != null){
                CountryNode cn = temp.getRight();
                System.out.println(temp.getLength()+"------");
                while(cn != null){
                    System.out.println(cn.getName());
                    cn = cn.getNext();
                }
                temp = temp.getDown();
            }
        }
    }
}
