
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class test{
    static String DELIMITERS = "[-+=" +

            " " +        //space

            "\r\n " +    //carriage return line fit

            "1234567890" + //numbers

            "’'\"" +       // apostrophe

            "(){}<>\\[\\]" + // brackets

            ":" +        // colon

            "," +        // comma

            "‒–—―" +     // dashes

            "…" +        // ellipsis

            "!" +        // exclamation mark

            "." +        // full stop/period

            "«»" +       // guillemets

            "-‐" +       // hyphen

            "?" +        // question mark

            "‘’“”" +     // quotation marks

            ";" +        // semicolon

            "/" +        // slash/stroke

            "⁄" +        // solidus

            "␠" +        // space?

            "·" +        // interpunct

            "&" +        // ampersand

            "@" +        // at sign

            "*" +        // asterisk

            "\\" +       // backslash

            "•" +        // bullet

            "^" +        // caret

            "¤¢$€£¥₩₪" + // currency

            "†‡" +       // dagger

            "°" +        // degree

            "¡" +        // inverted exclamation point

            "¿" +        // inverted question mark

            "¬" +        // negation

            "#" +        // number sign (hashtag)

            "№" +        // numero sign ()

            "%‰‱" +      // percent and related signs

            "¶" +        // pilcrow

            "′" +        // prime

            "§" +        // section sign

            "~" +        // tilde/swung dash

            "¨" +        // umlaut/diaeresis

            "_" +        // underscore/understrike

            "|¦" +       // vertical/pipe/broken bar

            "⁂" +        // asterism

            "☞" +        // index/fist

            "∴" +        // therefore sign

            "‽" +        // interrobang

            "※" +          // reference mark

            "]";

    public static void main(String[] args) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> stop_words = new ArrayList<>();
        //HashTable<String,HashTable<String,Integer>> word_level= new HashTable(50);
        ArrayList<HashTable<String,Integer>> word_levels = new ArrayList();
        try{
            FileReader fileReader = new FileReader("stop_words_en.txt");
            String line;
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                if(!line.equals(""))stop_words.add(line);
            }
            br.close();
            File dir = new File("bbc");
            File[] list=dir.listFiles();
            for(int i=0;i<list.length;i++){
                File[] inner_list = list[i].listFiles();
                for (int j = 0; j < inner_list.length; j++) {
                    fileReader = new FileReader(inner_list[j]);
                    br = new BufferedReader(fileReader);
                    while ((line = br.readLine()) != null) {
                        String[] tempwords = line.split(DELIMITERS);
                        for (int k = 0; k < tempwords.length; k++) {
                            // Selects a word in the file.
                            // Checks the word is in stop_words or not in.
                            String key = tempwords[k].toLowerCase(Locale.ROOT);
                            if(!stop_words.contains(key)){
                                // Checks the word is in hashtable or not in.
                                // If the word is in hashtable. Updates the value.
                                if(words.contains(key)){
                                    // The value is list of the word's directories and their counts.
                                    // These values' structure is hashtable.
                                    HashTable<String,Integer> temp = word_levels.get(words.indexOf(key));
                                    if(temp.containsKey(inner_list[j].getName())){
                                        // Count increases If the word found in the same directory.
                                        temp.setValue(inner_list[j].getName(),temp.getValue(inner_list[j].getName())+1);
                                    }
                                    else{
                                        // Puts new entry whose key is the directory name in the value's hashtable.
                                        temp.put(inner_list[j].getName(),1);
                                    }
                                }
                                // If word is not in hashtable. Puts new entry in hashtable.
                                else{
                                    words.add(key);
                                    HashTable<String,Integer> temp = new HashTable(50);
                                    // Puts new entry whose key is the directory name in the value's hashtable.
                                    temp.put(inner_list[j].getName(),1);
                                    word_levels.add(temp);
                                }
                            }
                        }
                    }
                    br.close();
                }
            }
            Scanner sc = new Scanner(System.in);
            fileReader = new FileReader("search.txt");
            br = new BufferedReader(fileReader);
            ArrayList<String> searchList = new ArrayList();
            while ((line = br.readLine()) != null) {
                if(!line.equals(""))searchList.add(line);
            }
            br.close();
            while(true){
                String hashingType;
                String collusionType;
                HashTable<String,HashTable<String,Integer>> table;
                int load_factor;
                do {
                    System.out.println("\n---------Hash Table--------");
                    System.out.print("Enter the hashing type : (SSF/PAF)");
                    hashingType= sc.nextLine().toUpperCase(Locale.ROOT);
                }while(!(hashingType.equals("SSF")|| hashingType.equals("PAF")));
                do {
                    System.out.print("Enter the collusion handling type : (LP/DH)");
                    collusionType= sc.nextLine().toUpperCase(Locale.ROOT);
                }while(!(collusionType.equals("LP")||collusionType.equals("DH")));
                do {
                    System.out.print("Enter the load factor : ([50]/80)");
                    load_factor = sc.nextInt();
                }while(!(load_factor==50||load_factor==80));

                long start_time = System.nanoTime();
                table = new HashTable(hashingType,collusionType,load_factor);
                for (int i = 0; i < words.size(); i++) {
                    table.put(words.get(i), word_levels.get(i));
                }
                long end_time = System.nanoTime();
                long total_indexing_time = end_time-start_time;

                long min=99999999;
                long max=0;
                start_time = System.nanoTime();
                for (int i = 0; i < searchList.size(); i++) {
                    long temp_start_time = System.nanoTime();
                    table.search(searchList.get(i));
                    long temp_end_time = System.nanoTime();
                    long temp_spent_time = temp_end_time-temp_start_time;
                    if(temp_spent_time<min) min = temp_spent_time;
                    if(temp_spent_time>max) max = temp_spent_time;
                }
                end_time = System.nanoTime();
                long average_spent_time = (end_time-start_time)/searchList.size();
                System.out.println("Total indexing time = " + total_indexing_time);
                System.out.println("Average spent time = " + average_spent_time);
                System.out.println("Min time = " + min);
                System.out.println("max time = " + max);
                System.out.println("Table size = " + table.getTABLE_SIZE());
                System.out.println("Table element count = " + table.getElement_count());
                System.out.println("Table collision count = " + table.getCollision_count());
                System.out.println("Do you want repeat?y/n ");
                if(!sc.next().equals("y")) {
                    System.exit(0);
                }
            }
        }catch (IOException ioException){
            System.out.println("Some files could not be found!");
            System.out.println("Error massage is "+ioException.getMessage());
        }/*catch(Exception e){
            System.out.println("Something is wrong!");
            System.out.println("Error message is " + e.getMessage());
        }*/
    }
}