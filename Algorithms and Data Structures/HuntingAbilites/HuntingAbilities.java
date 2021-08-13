package assgn2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class main {
    static ArrayList<Lion> lions;
    static Hashtable<Lion, Integer> levelsOfRelation;
    static String abilitiesFileName="hunting_abilities.txt";
    static String hierarchyFileNameFileName="lions_hierarchy.txt";
    static class Lion{
        String name;
        Lion father;
        Lion rightsibling;
        Lion leftchild;
        int strenght;
        boolean goHunt;
        boolean canHunt;
        public Lion(String name,int strenght) {
            this.father = null;
            this.rightsibling = null;
            this.leftchild = null;
            this.name = name;
            this.strenght = strenght;
            this.goHunt = false;
            this.canHunt = true;
        }
    }
    static void fileOperations(String abilitiesFileName,String hierarchyFileName) throws FileNotFoundException {
        lions = new ArrayList<>();
        File file = new File(abilitiesFileName);
        Scanner sc = new Scanner(file);
        String line;
        line = sc.nextLine();
        while (sc.hasNextLine()){
            line = sc.nextLine();
            String[] data= line.split("\t");
            Lion newLion = new Lion(data[0],Integer.valueOf(data[1]));
            lions.add(newLion);
        }
        sc.close();
        file = new File(hierarchyFileName);
        sc = new Scanner(file);
        line = sc.nextLine();
        while (sc.hasNextLine()){
            line = sc.nextLine();
            String[] data= line.split("\t");
            Lion source = null;
            Lion destination = null;
            for (Lion lion: lions) {
                if(lion.name.equals(data[0])) source = lion;
                if(lion.name.equals(data[1])) destination = lion;
            }
            if(data[2].equals("Left-Child")){
                source.leftchild = destination;
                destination.father = source;
                Lion temp = destination;
                while(temp.rightsibling !=null){
                    temp = temp.rightsibling;
                    temp.father = source;
                }
            }
            else if(data[2].equals("Right-Sibling")){
                source.rightsibling = destination;
                destination.father = source.father;
                Lion temp = destination;
                while(temp.rightsibling !=null){
                    temp = temp.rightsibling;
                    if(source.father!=null) temp.father = source.father;
                }
            }
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        fileOperations(abilitiesFileName,hierarchyFileNameFileName);
        screen();
    }
    static int LeveledRelationTree(){
        levelsOfRelation = new Hashtable<>();
        int max_high = 0;
        for (Lion lion : lions) {
            int counter = 0;
            Lion temp = lion.father;
            while (temp!= null) {
                counter++;
                temp = temp.father;
            }
            if (counter > max_high) max_high = counter;
            levelsOfRelation.put(lion, counter);
        }
        return  max_high;
    }
    static int DP() {
        int max_hunt_strength = 0;
        int max_high = LeveledRelationTree();  // The leveled graph is generated, the Hashtable is saved, and the highest level is returned.
        for (int i = max_high; i >= 0; i--) {  // It cycles through all levels starting from the highest level and decreasing (except 0).
            for (Lion lion : levelsOfRelation.keySet()) { // It returns in the array.
                if(levelsOfRelation.get(lion) == i&& lion.canHunt&& !lion.goHunt){ // If the level of the selected lion is at the desired level,
                    if(lion.father == null){
                        System.out.print(" -> "+lion.name+ " "+ lion.strenght);
                        lion.goHunt = true;
                        max_hunt_strength+= lion.strenght;
                        continue;
                    }
                    Lion father = lion.father; // Father Aslan is reached.
                    Lion child = father.leftchild;  // Father's left child is reached (Lion's leftmost brother).
                    if(father.canHunt) { // If the father lion can hunt,
                        int child_pow = 0; // It represents the total strength of the children who can hunt.
                        while (child != null) {  // It starts from the left child and gathers the powers of the brothers who can go all hunting.
                            if(child.canHunt)child_pow += child.strenght;
                            child = child.rightsibling;
                        }
                        if (father.strenght > child_pow) {  // If the power of the father is greater than the combined strength of the children,
                            if(!father.goHunt){ // If the father has not gone hunting before, the father goes hunting.
                                System.out.print(" -> "+father.name+ " "+ father.strenght);
                                father.goHunt = true;
                                max_hunt_strength+= father.strenght;
                            }
                            if(father.father!=null)father.father.canHunt = false; // If father has a father, his father cannot go hunting.
                        }
                        else { // If the total strength of the children is greater than the strength of the father,
                            child = father.leftchild; // Father's left child is reached (Lion's leftmost brother).
                            father.canHunt = false; // Father lion can't go hunting.
                            while (child != null) {
                                if(child.canHunt&&!child.goHunt){ // If the selected child did not go hunting and can go hunting,
                                    child.goHunt = true; // The child goes hunting.
                                    max_hunt_strength+= child.strenght;
                                    System.out.print(" -> "+child.name+ " "+ child.strenght);
                                }
                                child = child.rightsibling;
                            }
                        }
                    }
                    else { //If the father lion can't hunting, those who can go hunting, from the left child to the last sibling, go hunting.
                        child = father.leftchild;
                        while (child != null) {
                            if(child.canHunt&&!child.goHunt){
                                max_hunt_strength+= child.strenght;
                                child.goHunt = true;
                                System.out.print(" -> "+child.name+ " "+ child.strenght);
                            }
                            child = child.rightsibling;
                        }
                    }
                }
            }
        }
        // The total strength of the lions go hunt is found and given back.
        System.out.println();
        return max_hunt_strength;
    }
    static int Greedy(){
        int max_hunt_strength = 0;
        // In the first stage,first element of the array is temporarily selected as the strongest lion.
        for (int i = 0; i < lions.size(); i++)
        { // The cycle ends if there are no lions left to choose from.
            int strongest_strength = 0;
            Lion strongest = null;
            for(Lion lion:lions){ // It returns the array and the strongest lion is determined.
                if(lion.canHunt&&!lion.goHunt &&lion.strenght >= strongest_strength){
                    strongest = lion;
                    strongest_strength = strongest.strenght;
                }
            }
            if(strongest!=null){
                max_hunt_strength+= strongest.strenght;
                strongest.goHunt = true; // The strongest lion chosen goes hunting.
                System.out.print(" -> " + strongest.name+" "+strongest.strenght);
                if(strongest.father!=null)strongest.father.canHunt = false; // If the strongest lion has a father, his father cannot go hunting.
                if(strongest.leftchild!=null){ // If the strongest lion has children, they cannot go hunting.
                    Lion child = strongest.leftchild;
                    while (child !=null){
                        child.canHunt = false;
                        child = child.rightsibling;
                    }
                }
            }
            else break;
        }
        // The total strength of the lions go hunt is found and given back.
        return max_hunt_strength;
    }
    static void screen(){
        System.out.println("\n****************// Information of Lions \\\\****************\n");
        for(Lion lion: lions){
            System.out.println("**********************************************************\n");
            System.out.println("Lion name = " + lion.name);
            System.out.println("Lion Pow = " + lion.strenght);
            if(lion.leftchild!=null) System.out.println("Lion's left child = " + lion.leftchild.name);
            if(lion.rightsibling !=null)System.out.println("Lion's right brother = " + lion.rightsibling.name);
            if(lion.father!=null)System.out.println("Lion's father = " + lion.father.name);
            System.out.println();
        }

        System.out.println();
        System.out.println("******************// DP Method \\\\******************");
        System.out.println();
        long  baslangicZamani=System.nanoTime();
        System.out.println("DP method's result is "+ DP());
        long  bitisZamani=System.nanoTime();
        System.out.println("Run Time = "+ (bitisZamani-baslangicZamani));
        for(Lion lion:lions){
            lion.canHunt = true;
            lion.goHunt = false;
        }

        System.out.println();
        System.out.println("******************// Greedy Method \\\\******************");
        System.out.println();
        baslangicZamani=System.nanoTime();
        System.out.println("\nGreedy method's result is "+ Greedy());
        bitisZamani=System.nanoTime();
        System.out.println("Run Time = "+ (bitisZamani-baslangicZamani));
    }
}
