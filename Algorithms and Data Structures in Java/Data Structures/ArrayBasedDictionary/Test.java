package ArrayBasedDictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Test {
	public static void display(DictionaryInterface<Integer, Integer> dataBase) {
		Iterator<Integer> keyIterator = dataBase.getKeyIterator();
		Iterator<Integer> valueIterator = dataBase.getValueIterator();
		while (keyIterator.hasNext()) {
			System.out.println("Key: " + keyIterator.next() + " Value: " + valueIterator.next());
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		DictionaryInterface<Integer, Integer> StudentGradesDictionary = new Dictionary<Integer, Integer>();
		DictionaryInterface<Integer, Integer> GradesInformation = new Dictionary<Integer, Integer>();
		BufferedReader csvReader = new BufferedReader(new FileReader("input.csv"));
		String row;
		while ((row = csvReader.readLine()) != null) {
			String[] data =row.split(",");
			// do something with the data
			int key = Integer.valueOf(data[0]);
			int value = Integer.valueOf(data[1]);
			StudentGradesDictionary.add(key,value);
		}
		Iterator<Integer> keys=StudentGradesDictionary.getKeyIterator();
		Iterator<Integer> values=StudentGradesDictionary.getValueIterator();
		
		while(keys.hasNext()){
			int key = keys.next();
			int value = values.next();
			if(GradesInformation.contains(key)){
				Iterator<Integer> tempkeys=GradesInformation.getKeyIterator();
				Iterator<Integer> tempvalues=GradesInformation.getValueIterator();
				while(tempkeys.hasNext()){
					int tempkey = tempkeys.next();
					int tempvalue = tempvalues.next();
					GradesInformation.remove(tempkey);
					GradesInformation.add(tempkey,tempvalue+1);	
				}
			}
			else{
				GradesInformation.add(value,1);
			}
		}
		display(StudentGradesDictionary);
		display(GradesInformation);
	}

}
