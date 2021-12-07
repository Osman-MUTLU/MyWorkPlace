import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

public class HashTable <K, V>
{
    private int TABLE_SIZE;
    private String hashingType;
    private String collisionHandlingType;
    private int collision_count;
    private int element_count;
    private int load_factor;
    private HashEntry<K, V>[] table;

    public HashTable(int capacity,String algorithmType,String collisionHandlingType,int load_factor){
        this.hashingType = algorithmType;
        this.collisionHandlingType = collisionHandlingType;
        this.load_factor = load_factor;
        TABLE_SIZE = capacity;
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) table[i] = null;
        collision_count =0;
        element_count = 0;
    }
    public HashTable(String algorithmType,String collisionHandlingType,int load_factor){
        this.collisionHandlingType = collisionHandlingType;
        this.hashingType = algorithmType;
        this.load_factor = load_factor;
        TABLE_SIZE =211;
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) table[i] = null;
        collision_count =0;
        element_count = 0;
    }
    public HashTable(int load_factor){
        this.collisionHandlingType = "LP";
        this.hashingType = "SSF";
        this.load_factor=load_factor;
        TABLE_SIZE =211; // It's a prime number
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) table[i] = null;
        collision_count =0;
        element_count = 0;
    }
    private void resize_table(){
        // Finds the smallest prime number greater than twice the table size.
        int newSize = TABLE_SIZE*2;
        while(true){
            boolean flag = true;
            for (int i = 2; i < newSize ; i++) {
                if(newSize%i == 0){
                    newSize++;
                    flag = false;
                    break;
                }
            }
            if(flag) break;
        }
        // Sets the new size.
        TABLE_SIZE = newSize;
        // Creates the temporary table.
        HashEntry<K, V>[] newtable = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < newtable.length; i++) {
            newtable[i] =null;
        }
        for (int i = 0; i < table.length; i++) {
            newtable[i] = table[i];
        }
        table = newtable;
    }
    public boolean isFull(){
        for (int i = 0; i < TABLE_SIZE; i++) {
            if(table[i]==null) return false;
        }
        return true;
    }
    public void put(K key,V value){
        int hashIndex;
        element_count++;
        // Resizing is done according to the load factor.
        if(element_count>(TABLE_SIZE*load_factor/100)){
            resize_table();
        }
        // Generates the hashIndex according to the hashing type.
        if(hashingType.equals("SSF")){
            hashIndex = hashCodeSSF(key,TABLE_SIZE);
        }
        else{
            hashIndex = hashCodePAF(key,TABLE_SIZE);
        }

        HashEntry<K,V> newentry= new HashEntry(key,value);
        // If this index is empty, new element is added.
        if(table[hashIndex] == null){
            table[hashIndex] = newentry;
        }
        // If it's not empty, it has been collision.
        else{
            collision_count++;
            int old_hasIndex=hashIndex;
            System.out.println("!!Collision!!");
            // Solves the collision according to collision handling type.
            if(collisionHandlingType.equals("LP")){
                // First handling type is linear probing("LP").
                System.out.println("Handling with LP.....");
                // If the hash index is last index of the table, go to the index of 0.
                if(hashIndex == table.length-1){
                    hashIndex = 0;
                }
                while(table[hashIndex+1]!=null){
                    // Loops until it finds the null in table.
                    hashIndex+=1;
                    // If it finds the same key, it puts the new value and finishes the operation.
                    if(table[hashIndex]!=null&&table[hashIndex].getKey().equals(key)){
                        table[hashIndex].setValue(value);
                        System.out.println("Key is the "+key);
                        System.out.println("Old Hash Index = "+old_hasIndex+" , "+"New Hash Index = "+ hashIndex);
                        System.out.println("!!! Collision handled!!!");
                        System.out.println("------------------------");
                        return;
                    }
                    // If the hash index is last index of the table, go to the index of 0.
                    if(hashIndex == table.length-1){
                        hashIndex = 0;
                    }
                }
                // table[hashIndex] is null since it has exited the loop at this stage.
                // Puts the new entry in table.
                table[hashIndex+1] = newentry;
                System.out.println("Key is the "+key);
                System.out.println("Old Hash Index = "+old_hasIndex+" , "+"New Hash Index = "+ hashIndex);
            }
            else{
                System.out.println("Handling with DH.....");
                int k= ((String)key).length();
                int q = 97;
                int d_k = q - (k%q);
                int j = 0;
                int double_hashcode = (hashIndex+(j*d_k))%TABLE_SIZE;
                if(table[double_hashcode] !=null&&table[double_hashcode].getKey().equals(key)){
                    table[double_hashcode].setValue(value);
                    System.out.println("Key is the "+key);
                    System.out.println("Old Hash Index = "+old_hasIndex+" , "+"New Hash Index = "+ double_hashcode);
                }
                else{
                    while(table[double_hashcode] !=null){
                        j++;
                        double_hashcode = (hashIndex+(j*d_k))%TABLE_SIZE;
                        System.out.println("The "+j+"st lookup index: "+double_hashcode);
                        if(table[double_hashcode] !=null&&table[double_hashcode].getKey().equals(key)){
                            table[double_hashcode].setValue(value);
                            System.out.println("Key is the "+key);
                            System.out.println("Old Hash Index = "+old_hasIndex+" , "+"New Hash Index = "+ double_hashcode);
                            System.out.println("!!! Collision handled!!!");
                            System.out.println("------------------------");
                            return;
                        }
                    }
                    table[double_hashcode] = newentry;
                    System.out.println("Key is the "+key);
                    System.out.println("Old Hash Index = "+old_hasIndex+" , "+"New Hash Index = "+ double_hashcode);
                }
            }
            System.out.println("!!! Collision handled!!!");
            System.out.println("------------------------");
        }
    }

    public boolean containsKey(K key){
        for (int i = 0; i < table.length; i++) {
            if(table[i]!=null&&table[i].getKey().equals(key)) return true;
        }
        return false;
    }

    public int hashCodeSSF(K key,int table_size){
        String tempKey = String.valueOf(key);
        int sum=0;
        for (int i = 0; i < tempKey.length(); i++) {
            sum += (Integer.valueOf(tempKey.charAt(i))-96);
        }
        return Math.abs(sum%table_size);
    }
    public  int hashCodePAF (K key,int table_size)
    {
        int sum=0;
        int z = 31;
        String tempKey = String.valueOf(key);
        for (int i = 0; i < tempKey.length(); i++) {
            int ch = (Integer.valueOf(tempKey.charAt(i))-96);
            int z_n = (int)Math.pow(z,(tempKey.length()-(i+1)));
            sum += ch*z_n;
        }
        return Math.abs(sum%table_size);
    }
    public int search(K key){
        int hashIndex;
        int tempSize = TABLE_SIZE;
        /*  This method searches the key according to hash function.
        *   The index of the key may not be found because we do not know the table size at the time the key was inserted.
        *   We have to repeatedly generate the codes starting from TABLE_SIZE to the lowest possible table size of 2.
        *   But we have to reduce this operation by reversing the increment form in resize.
        *   We must continue the loop by finding the largest PRIME number smaller than TABLE_SIZE.
        * */
        for (int i = tempSize; i > 1; i--) {
            /*  If there is no element in the hash index calculated according to the key,
            *   or if a null value is encountered when it is navigated according to the collision handling type,
            *   the resize_flag turns true and the reversed resize operation is performed.
            *   The value "i" specifies the table size.
            * */
            boolean resize_flag = false;
            if(hashingType.equals("SSF")){
                hashIndex = hashCodeSSF(key,i);
            }
            else{
                hashIndex = hashCodePAF(key,i);
            }
            // System.out.println("Key = " + key + " HashIndex = " + hashIndex);
            // System.out.println("Hashing Type = " + hashingType);
            if(table[hashIndex] != null&&table[hashIndex].getKey().equals(key)){
                HashTable<String,Integer> temp= (HashTable<String, Integer>) table[hashIndex].getValue();
                //temp.screen();
                return hashIndex;
            }
            else if(collisionHandlingType.equals("LP")){
                // System.out.println("Collision Handling Type = " + collisionHandlingType);
                if(hashIndex == table.length-1) hashIndex=0;
                while (table[hashIndex+1]!=null){
                    hashIndex+=1;
                    if (table[hashIndex].getKey().equals(key)){
                        HashTable<String,Integer> temp= (HashTable<String, Integer>) table[hashIndex].getValue();
                        //temp.screen();
                        return hashIndex;
                    }
                    if(hashIndex == table.length-1) hashIndex=0;
                }
                resize_flag = true;
            }
            else{
               // System.out.println("Collision Handling Type = " + collisionHandlingType);
                int k= ((String)key).length();
                int q = 97;
                int d_k = q - (k%q);
                int j = 0;
                int double_hashcode = (hashIndex+(j*d_k))%i;
                if(table[double_hashcode] !=null&&table[double_hashcode].getKey().equals(key)){
                    //HashTable<String,Integer> temp= (HashTable<String, Integer>) table[double_hashcode].getValue();
                    //temp.screen();
                    return double_hashcode;
                }
                while(table[double_hashcode] !=null){
                    j++;
                    double_hashcode =(hashIndex+(j*d_k))%i;
                    if(table[double_hashcode] !=null&&table[double_hashcode].getKey().equals(key)){
                        //HashTable<String,Integer> temp= (HashTable<String, Integer>) table[double_hashcode].getValue();
                        //temp.screen();
                        return double_hashcode;
                    }
                    if(j>table.length) break;
                }
                resize_flag = true;
            }
            if(resize_flag){
                // Reversed resize operation.
                for (int j = i-1; j > 1; j--) {
                    if(j%2==0){
                        int tempSize2 = j/2;
                        boolean prime_flag = true;
                        for (int k = 2; k < tempSize2 ; k++) {
                            if(tempSize2%k == 0){
                                prime_flag = false;
                            }
                        }
                        // If it finds the prime, it sets the table_size(i) .
                        if(prime_flag){
                            i=tempSize2+1;
                            break;
                        }
                    }
                }
            }
        }
        //System.out.println("Key is not found!");
        return -1;
    }
    public void screen(){
        for (int i = 0; i < table.length; i++) {
            if(table[i]!=null){
                System.out.println(table[i].getKey()+" "+ table[i].getValue());
            }
        }
    }

    public int getCollision_count() {
        return collision_count;
    }

    public int getElement_count() {
        return element_count;
    }
    public int getTABLE_SIZE(){
        return TABLE_SIZE;
    }
    public void setValue(K key,V newValue){
        for (int i = 0; i < table.length; i++) {
            if(table[i]!=null&&table[i].getKey().equals(key)) table[i].setValue(newValue);
        }
    }
    public V getValue(K key){
        for (int i = 0; i < table.length; i++) {
            if(table[i]!=null&&table[i].getKey().equals(key)) return table[i].getValue();
        }
        return null;
    }
    public ArrayList<K> keys() {
        ArrayList<K> keys= new ArrayList();
        for (int i = 0; i < table.length; i++) {
            if(table[i]!=null) keys.add(table[i].getKey());
        }
        return keys;
    }
}
