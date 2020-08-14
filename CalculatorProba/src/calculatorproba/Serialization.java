package calculatorproba;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
	
	int [] intArray = new int[3];
	
	public Serialization(String file) {
		
		readFile(file);
	}
	
	public Serialization(String file, int [] intArray) {
		
		writeFile(file, intArray);
	}
	
	public int [] getSoundVolumeSettings(){
		
		return intArray;
	}
	private void readFile(String file) {
		
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		
		try {
			
			fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			
			intArray = (int[]) objectInputStream.readObject();
			
		}
		catch(Exception e) {
			
		}
	}
	
private void writeFile(String file, int [] intArray) {
		
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		
		try {
			
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			objectOutputStream.writeObject(intArray);
		}
		catch(Exception e) {
			
		}
	}

}
