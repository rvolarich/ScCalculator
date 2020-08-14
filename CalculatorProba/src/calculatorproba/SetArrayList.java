package calculatorproba;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SetArrayList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8909604352518939080L;
	ArrayList<String> actions = new ArrayList<String>(1);
	ArrayList<Integer> keys = new ArrayList<Integer>(1);
	ArrayList<String> finalActions = new ArrayList<String>(1);
	ArrayList<Integer> finalKeys = new ArrayList<Integer>(1);
	boolean containsFunction = false;
	boolean containsKey = false;
	int indexOfKey;
	int indexOfFunction;
	char charKey;
	String stringFunction;
	
public SetArrayList(int i) {
		
		deserialize(false);
		actions.clear();
		keys.clear();
		try {
			writeObject(actions, keys, true);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}	
	
	public SetArrayList(String str, Integer i, boolean add, boolean preferences) {
		
		deserialize(preferences);
		contains(str, i);
		}
	
	public SetArrayList(boolean preferences) {
		
		deserialize(preferences);
		
		}
	
	
	public SetArrayList(boolean preferences, boolean temp) {

		deserialize(preferences);
		try {
			writeObject(actions, keys, temp );
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public SetArrayList(String str, Integer i, int a, boolean preferences){
		
		
		deserialize(preferences);
		if(a == 1) {
		addArrayList(str, i);
		}
		else if(a == 0) {
		removeArrayList(str, i);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void deserialize(boolean preferences) {
	
		FileInputStream actionsFileInputStream = null;
		ObjectInputStream actionsObjectInputStream = null;
		FileInputStream keysFileInputStream = null;
		ObjectInputStream keysObjectInputStream = null;
		
	try {
		if(preferences)actionsFileInputStream = new FileInputStream("actions.ser");
		else actionsFileInputStream = new FileInputStream("actionsTemp.ser");
	} catch (FileNotFoundException e1) {
		
		e1.printStackTrace();
	}
	try {
		actionsObjectInputStream = new ObjectInputStream(actionsFileInputStream);
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
	
	try {
		if(preferences)keysFileInputStream = new FileInputStream("keys.ser");
		else keysFileInputStream = new FileInputStream("keysTemp.ser");
	} catch (FileNotFoundException e1) {
		
		e1.printStackTrace();
	}
	try {
		keysObjectInputStream = new ObjectInputStream(keysFileInputStream);
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
	try {
		actions = (ArrayList<String>) actionsObjectInputStream.readObject();
	} catch (ClassNotFoundException e1) {
		
		e1.printStackTrace();
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
	
	try {
		keys = (ArrayList<Integer>) keysObjectInputStream.readObject();
	} catch (ClassNotFoundException e1) {
		
		e1.printStackTrace();
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
		
}
	
	
	private void addArrayList(String str, Integer i) {
		
		if(actions.contains(str) || keys.contains(i)) {
			
			return;
		}
		else {
			actions.add(str);
		keys.add(i);
		try {
			writeObject(actions, keys, true);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		}
	}
	
	private void removeArrayList(String str, Integer i){
		
		int index = actions.indexOf(str);
		actions.remove(str);
		keys.remove(index);
		try {
			writeObject(actions, keys, true);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getArrayListActions(){
		return actions;
	}
	
	
	public ArrayList<Integer> getArrayListKeys(){
		
		return keys;
	}
	
	private void writeObject(ArrayList<String> arrStr, ArrayList<Integer> arrInt, boolean temp) 
			throws FileNotFoundException, IOException{
		
		FileOutputStream actionsFileOutputStream = null;
		ObjectOutputStream actionsObjectOutputStream = null;
		FileOutputStream keysFileOutputStream = null;
		ObjectOutputStream keysObjectOutputStream = null;
		

	try {
		
		if(temp) actionsFileOutputStream = new FileOutputStream("actionsTemp.ser");
		else actionsFileOutputStream = new FileOutputStream("actions.ser");
		actionsObjectOutputStream = new ObjectOutputStream(actionsFileOutputStream);
		if(temp) keysFileOutputStream = new FileOutputStream("keysTemp.ser");
		else keysFileOutputStream = new FileOutputStream("keys.ser");
		keysObjectOutputStream = new ObjectOutputStream(keysFileOutputStream);
		
		
		
		actionsObjectOutputStream.writeObject(arrStr);
		keysObjectOutputStream.writeObject(arrInt);
	}
	finally {
		
		if(actionsObjectOutputStream != null) actionsObjectOutputStream.close();
		if(keysObjectOutputStream != null) keysObjectOutputStream.close();
	}
	}
	
private void setCharInArrayKey(String str) {
	
	indexOfFunction = actions.indexOf(str);
	int i = keys.get(indexOfFunction);
	charKey = (char) i;
}

public char getCharKey() {
	
	return charKey;
}

private void setStringInArrayFunction(Integer i) {
	
	indexOfKey = keys.indexOf(i);
	stringFunction = actions.get(indexOfKey);
	}

public String getStringFunction() {
	
	return stringFunction;
}

public int getIndexFunctionInArray() {
	
	return indexOfFunction;
}

public int getIndexKeyInArray() {
	
	return indexOfKey;
}

private void contains(String str, Integer i) {
	
	if(actions.contains(str)) {
		containsFunction = true;
		setCharInArrayKey(str);
	}
	if(keys.contains(i)) {
		containsKey = true;
		setStringInArrayFunction(i);
	}
	
}

public boolean getContainsFunction() {
	
	if(containsFunction) return true;
	else return false;
}

public boolean getContainsKey() {
	
	if(containsKey) return true;
	else return false;
}


public ArrayList<String> getFinalArrayListActions(){
	
	return finalActions;
}


public ArrayList<Integer> getFinalArrayListKeys(){
	
	return finalKeys;
}
}
