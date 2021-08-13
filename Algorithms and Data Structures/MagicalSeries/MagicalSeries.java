package Midterm;

import java.util.Random;

public class MagicalSeries {
    //All cards
    private final Card[] cards;

    //Suffered and closed cards on the desk
    private final CircularQueue desk;

    //Players
    private User user1;
    private User user2;

    //Stacks
    private Stack u1_stack ;
    private Stack u2_stack ;
    private Stack temp ;

    //Card previous for score calculate
    private Card prev = null;

    //Users scores and score limited at 10 points
    private int u1_score =0,u2_score=0;
    private int scoreLimit = 10;
    private boolean isScoreOutOfRange = false;

    //Determines who's turn
    private String playingUsr = "u1";
    private String turnUsr;
    private boolean turnFlag = false;


    public MagicalSeries() {
        cards  = new Card[52]; //52 sequential cards.
        desk = new CircularQueue(52); //Desk cards queue.
        cardGenerator(); //All cards creating
        shuffleCards(); //Shuffles the cards on the table
        screen_desk(); //Shows cards on the desk
        registerUsers(); //Registers users
        u1_stack = new Stack(52); //Creating user1's cards
        u2_stack = new Stack(52); //Creating user2's cards
    }
    //Shows and process all turn in the game
    public void begin() {
        while(!desk.isEmpty()){
            if(!desk.isEmpty()&& prev !=null){
                Card temp = (Card)desk.peek();
                //Score calculating
                if(prev.getRank().equals(temp.getRank())){
                    if(playingUsr.equals("u1")) u1_score+=3;
                    else if(playingUsr.equals("u2")) u2_score+=3;
                }
                else if(prev.getSuit().equals(temp.getSuit())){
                    if(playingUsr.equals("u1")) u1_score+=1;
                    else if(playingUsr.equals("u2")) u2_score+=1;
                }
                //Turns another user if score isn't changing
                else{
                    if(playingUsr.equals("u1")){
                        turnUsr = "u2";
                        turnFlag = true;
                    }
                    else if(playingUsr.equals("u2")) {
                        turnUsr = "u1";
                        turnFlag = true;
                    }
                }
            }
            //Cards Pushing user's stack
            if(playingUsr.equals("u1")){
                prev = (Card) desk.peek();
                u1_stack.Push(desk.dequeue());
            }
            else if(playingUsr.equals("u2")){
                prev = (Card) desk.peek();
                u2_stack.Push(desk.dequeue());
            }
            screen(); // Screen function
            if(user2.getScore() >= scoreLimit || user1.getScore() >= scoreLimit) { //Score control
                isScoreOutOfRange = true;
                turnFlag = true;
            }
            if(turnFlag){ //Turns User
                if(turnUsr.equals("u1")){
                    playingUsr = "u1";
                    u2_stack = new Stack(52);
                    prev = null;
                    user2.setScore(u2_score);
                }
                else if(turnUsr.equals("u2")){
                    playingUsr = "u2";
                    u1_stack = new Stack(52);
                    prev = null;
                    user1.setScore(u1_score);
                }
                turnFlag = false;
            }
            if(isScoreOutOfRange) {
                screen();
                while (!desk.isEmpty()) desk.dequeue(); //All closed cards are removed queue.
            }
        }
    }
    //Screen Functions
    private void screen_desk(){
        System.out.print("Desk : ");
        for (int i = 0; i < desk.size(); i++) {
            Card temp = (Card) desk.peek();
            System.out.print(temp.getSuit()+temp.getRank()+" ");
            desk.enqueue(desk.dequeue());
        }
        System.out.println();
    }
    private void screen(){
        if(playingUsr.equals("u1")&&!u1_stack.isEmpty()&& !isScoreOutOfRange){
            System.out.print("User1's Stack : ");
            stackScreen(u1_stack);
        }
        else if(playingUsr.equals("u2")&&!u2_stack.isEmpty()&& !isScoreOutOfRange){
            System.out.print("User2's Stack : ");
            stackScreen(u2_stack);
        }
        if(desk.isEmpty()|| isScoreOutOfRange){ // End of the game ,winner screen
            System.out.println();
            System.out.println("User1 Score : "+user1.getScore());
            System.out.println("User2 Score : "+user2.getScore());
            System.out.println();
            if(user1.getScore()>user2.getScore()) {
                System.out.println("Winner : User1");
                userScreen(user1);
            }
            else if(user1.getScore() == user2.getScore()) System.out.println("Winner : Tie!");
            else {
                System.out.println("Winner : User2");
                userScreen(user2);
            }
            System.out.println();

        }
    }
    private void userScreen(User user){
        System.out.println("Winner's Name : "+user.getName());
        System.out.println("Winner's Surname : "+user.getSurname());
        Date bd = user.getBirthday();
        System.out.println("Winner's Birthday : "+bd.getDay()+"."+bd.getMonth()+"."+bd.getYear());
        System.out.println("Winner's Gender : "+user.getGender());
        Address add = user.getAddress();
        System.out.println("Winner's Contact Address : "+add.getAddress()+" "+add.getDistrict()+"/"+add.getCity()+" "+add.getCountry());
        Phone ph = user.getNumber();
        System.out.println("Winner's Phone : "+ph.getCountry_code()+" "+ph.getArea_code()+ " "+ph.getNumber());
    }
    private void stackScreen(Stack usr_stack) {
        temp = new Stack(52);
        int exLength=0;
        while(!usr_stack.isEmpty()){
            Card tempcard = (Card) usr_stack.Peek();
            String tempRank = tempcard.getRank();
            if(tempRank.length()>1)exLength++;
            System.out.print(tempcard.getSuit()+tempcard.getRank()+" ");
            temp.Push(usr_stack.Pop());
        }
        while(!temp.isEmpty()) usr_stack.Push(temp.Pop());
        for (int i = 0; i < 30-exLength-(usr_stack.size()*3); i++) {
            System.out.print(" ");
        }
        System.out.print("User1 Score : "+user1.getScore()+ "   User2 Score : "+user2.getScore());
        System.out.println();
    }
    //Registers Users
    private void registerUsers(){
        String u1_name = "Deniz";
        String u1_surname = "Tarak";
        Date u1_birthday = new Date("27","04","1980");
        String u1_gender = "Female";
        Address u1_address = new Address("Türkiye","İzmir","Buca","DEU Kız Yurdu");
        Phone u1_phone = new Phone("+90","232","1234567");
        user1 = new User(u1_name,u1_surname,u1_birthday,u1_gender,u1_address,u1_phone);
        String u2_name = "Osman";
        String u2_surname = "Mutlu";
        Date u2_birthday = new Date("22","05","2000");
        String u2_gender = "Male";
        Address u2_address = new Address("Türkiye","İzmir","Buca","202/58 no:14 daire:12");
        Phone u2_phone = new Phone("+90","531","9075065");
        user2 = new User(u2_name,u2_surname,u2_birthday,u2_gender,u2_address,u2_phone);
    }
    //Generates all cards.
    private void cardGenerator(){
        int counter_d =1,counter_s=1,counter_c=1,counter_h=1;
        for (int i = 0; i <cards.length ; i++) {
            if(counter_d <13){
                if(counter_d == 1) cards[i] = new Card("D","A");
                else if(counter_d<11)cards[i] = new Card("D", String.valueOf(counter_d));
                else{
                    cards[i] = new Card("D","J");
                    i++;
                    cards[i] = new Card("D","Q");
                    i++;
                    cards[i] = new Card("D","K");
                    counter_d++;
                }
                counter_d++;
            }
            else if(counter_h<13){
                if(counter_h == 1) cards[i] = new Card("H","A");
                else if(counter_h<11)cards[i] = new Card("H",String.valueOf(counter_h));
                else{
                    cards[i] = new Card("H","J");
                    i++;
                    cards[i] = new Card("H","Q");
                    i++;
                    cards[i] = new Card("H","K");
                    counter_h++;
                }
                counter_h++;
            }
            else if(counter_s<13){
                if(counter_s == 1) cards[i] = new Card("S","A");
                else if(counter_s<11)cards[i] = new Card("S",String.valueOf(counter_s));
                else{
                    cards[i] = new Card("S","J");
                    i++;
                    cards[i] = new Card("S","Q");
                    i++;
                    cards[i] = new Card("S","K");
                    counter_s++;
                }
                counter_s++;
            }
            else if(counter_c<13){
                if(counter_c == 1) cards[i] = new Card("C","A");
                else if(counter_c<11)cards[i] = new Card("C",String.valueOf(counter_c));
                else{
                    cards[i] = new Card("C","J");
                    i++;
                    cards[i] = new Card("C","Q");
                    i++;
                    cards[i] = new Card("C","K");
                    counter_c++;
                }
                counter_c++;
            }
        }
    }
    //Shuffles all cards
    private  void shuffleCards(){
        Random rnd = new Random();
        while(!desk.isFull()){
            int randomIndex = rnd.nextInt(52);
            if(cards[randomIndex]!=null){
                desk.enqueue(cards[randomIndex]);
                cards[randomIndex] = null;
            }
        }
    }

}
