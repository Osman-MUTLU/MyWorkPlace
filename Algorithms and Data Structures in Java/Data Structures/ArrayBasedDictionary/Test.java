import java.util.Iterator;

public class Test {
	public static void print(String result) {
		if(result == null)
			System.out.println("The value is not found");
		else
			System.out.println("Found value is: " + result);
	}
	
	public static void display(DictionaryInterface<Integer, String> dataBase) {
		Iterator<Integer> keyIterator = dataBase.getKeyIterator();
		Iterator<String> valueIterator = dataBase.getValueIterator();
		while (keyIterator.hasNext()) {
			System.out.println("Key: " + keyIterator.next() + " Value: " + valueIterator.next());
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DictionaryInterface<Integer, String> dataBase = new Dictionary<Integer, String>();
		
		dataBase.add(67, "Goksu");
		dataBase.add(75, "Ali");
		dataBase.add(88, "Melih");
		dataBase.add(28, "Nilay");		
		dataBase.add(50, "Ýlker");
		display(dataBase);
		int initialSize = dataBase.getSize();
		System.out.println(initialSize + " instances were added");

		System.out.println("-----------------------------------------");
		String result = null;
		result = dataBase.remove(90);
		print(result);		
		result = dataBase.remove(67);
		print(result);
		
		System.out.println("-----------------------------------------");
		display(dataBase);
		System.out.println(initialSize - dataBase.getSize() + " instance(s) were deleted");
		
		System.out.println("-----------------------------------------");

		System.out.println("Is the key >67< in the dataBase?\n" + dataBase.contains(67));
		System.out.println("Is the key >28< in the dataBase?\n" + dataBase.contains(28));

		
		System.out.println("-----------------------------------------");
		result = dataBase.getValue(88);
		print(result);
		result = dataBase.getValue(80);
		print(result);		
		
		System.out.println("-----------------------------------------");
		display(dataBase);
		dataBase.clear();
		System.out.println("-----------------------------------------");
		
		System.out.println("Final content of the dataBase is: ");
		display(dataBase);
		System.out.println(dataBase.getSize() + " instances were remained");
	}

}
