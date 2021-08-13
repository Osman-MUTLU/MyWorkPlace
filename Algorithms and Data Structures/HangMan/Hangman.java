import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private MultiLinkedList mll;
    private DoubleLinkedList dll;
    private SingleLinkedList sll;
    private CircularLinkedList cll1;
    private CircularLinkedList cll2;
    private String name;
    private int score;
    private int lives;
    private String inputFile = "input.txt";
    private String highScoreListFile = "HighScoreTable.txt";

    public Hangman() {
        mll = new MultiLinkedList();
        dll = new DoubleLinkedList();
        sll = new SingleLinkedList();
        cll1 = new CircularLinkedList();
        cll2 = new CircularLinkedList();
        //Add all leter from ASCII; [65,90]
        for (int i = 65; i < 91; i++) {
            dll.add((char)i);
        }
        name = null;
        score = 0;
        lives = 6;
    }

    private void ReadCountries() throws IOException {
        //Reading input.txt from disk and countries save to MLL directly.
        FileReader fr=new FileReader(inputFile);
        BufferedReader br=new BufferedReader(fr);
        String line= br.readLine();
        while(line != null){
            mll.addCountry(line);
            line= br.readLine();
        }
        br.close();
        fr.close();
    }
    private void ReadHighScoreList() throws IOException {
        //Reading HighScoreList.txt from disk and it saves to SLL directly.
        FileReader fr=new FileReader(highScoreListFile);
        BufferedReader br=new BufferedReader(fr);
        String line= br.readLine();
        while(line != null){
            String[] data = line.split(";");
            String name = data[0];
            int score = Integer.parseInt(data[1]);
            sll.add(name,score);
            line= br.readLine();
        }
        br.close();
        fr.close();
    }
    private void SaveHighScoreList() throws IOException {
        //Save high score list to HighScoreList.txt
        FileWriter fw = new FileWriter(highScoreListFile);
        BufferedWriter bw = new BufferedWriter(fw);
        ScoreNode temp = sll.head;
        for (int i = 0; i < 10; i++) {
            bw.write(temp.getName()+";"+temp.getScore());
            bw.newLine();
            temp = temp.getNext();
        }
        bw.close();
        fw.close();
    }
    private void selectCountry(){
        Random rnd = new Random();
        int n = rnd.nextInt(mll.size())+1;
        System.out.println("Randomly generated number: "+n);
        //toUpperCase() works without changing letters.So , I can use replace() func. for change 'İ' to 'I'.
        String country = mll.selectedCountry(n).toUpperCase().replace('İ','I');
        //English Word Control for selected country from DLL.
        System.out.println(country);
        boolean flag = true;
        while(true){
            for (int i = 0; i < country.length(); i++) {
                flag = dll.search(country.charAt(i),false);
                //If it can't match to any dll characters. Flag will be false.
                if(!flag)break;
            }
            if(flag)break;
            //It will be change when 'flag == false' situation.So , that's mean it's wrong characters.
            n = rnd.nextInt(mll.size())+1;
            country = mll.selectedCountry(n).toUpperCase().replace('İ','I');
        }
        //Add all chars to cll1 from selected country.
        for (int i = 0; i < country.length(); i++) {
            cll1.add(country.charAt(i));
            cll2.add('-');
        }
    }
    public void Game() throws IOException {
        //Main game function.
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name :  ");
        String name = sc.next();
        System.out.println();
        ReadCountries(); //Read countries from input.txt
        ReadHighScoreList(); //Read high score list from HighScoreList.txt
        selectCountry(); //Select randomly country from mll.
        while(lives !=0){
            screen();
            char guess= sc.next().toUpperCase().replace('İ','I').charAt(0);
            if(dll.search(guess,true)){
                if(cll1.search(guess)){ //Correct letter control from cll1.
                    if(cll2.search(guess)) System.out.println("You entered the same letter before.");//Same letter control from cll2.
                    else {
                        CircularNode temp = cll1.head;
                        CircularNode temp2 = cll2.head;
                        int counter = 0; //Counting '-' character from cll2 for ending.
                        do{ //Insert correct letters positions to cll2 from cll1.
                            if(temp.getData().equals(guess))temp2.setData(guess);
                            if(!temp2.getData().equals('-'))counter++;
                            temp = temp.getLink();
                            temp2 = temp2.getLink();
                        }while (temp!=cll1.head);
                        //Adds 5 point to score for each vowel letter.
                        //Adds 10 point to score for each consonant letter.
                        if(guess == 'A' ||guess == 'E' ||guess == 'I' ||guess == 'O' ||guess == 'U') score+=5;
                        else score+=10;
                        //IF cll2 hasn't '-' character.Game is over.
                        if(counter == cll1.size()){
                            screen();
                            break;
                        }
                    }
                }
                else lives--;
            }
            else System.out.println("Please enter usable characters!");
        }
        //If he/she hasn't lives.Will be lost.
        if(lives!=0) System.out.println("You win!!");
        else System.out.println("You lost!!");
        sll.add(name,score);
        System.out.println();
        System.out.println("High Score List");
        System.out.println();
        sll.display();
        SaveHighScoreList();//Save sll for first 10 data to HighScoreList.txt
    }
    private void screen(){
        //Screen specific variables.
        System.out.println();
        System.out.print("Word :   ");
        cll2.display();
        System.out.print("           Lives :   ");
        System.out.print(lives);
        System.out.print("           Scores :   ");
        System.out.print(score);
        System.out.print("           ");
        dll.display();
        System.out.print("Guess: ");
    }
}
