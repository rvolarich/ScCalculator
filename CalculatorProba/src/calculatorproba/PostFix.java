package calculatorproba;


import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class PostFix {
	// holds return from queue
	Object [] finalObjectArray;
	// holds converted object-string array
	String [] finalStringArray;
	String [] tempStringArray;
	// holds converted object-string array
		ArrayList<String> tempStringArrayList = new ArrayList<String>(1);
	// temporary string container for operands
	String stringBufferOperand;
	// temporary string container for operators
	String stringBufferOperator;
	// input string
	String newString;
	// holds newString split
	StringBuilder sb = new StringBuilder();
	char [] charArray;
	
	//controls logic if char is an operator
	public static boolean IsOperator = false;
	public static boolean IsOperand = false;
	// controls i iteration in string forming loop
	public static boolean itterateI = true;
	// queue for separated strings
	public Queue<String> q = new LinkedList<>();
	
	// holds string white space removed
	String noSpaceString;
	// buffers the values before storing in queue
	char [] charArrBuffer;
	int arraySize;
	
	
public static boolean isOperator (char c) {
		
		if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == '^' || c == ')') return true;
		return false;
		}
	
public static boolean isOperand (char c) {
		
		if(c >= '0' && c <= '9' || c == '.') return true;
		return false;	
}

public int setArraySize() {
	int i = 0;
	while(tempStringArray[i] != null) {
		i++;
	}
	arraySize = i;
	return arraySize;
}

public int getArraySize() {
	return arraySize;
}

public boolean finalStringArrayLastElement(int i) {
	
	if(i == setArraySize() - 1) return true;
	return false;
}

	

	public void setString(String str) {
		
		
		
	noSpaceString = str.replaceAll("\\s","");
	charArray = new char [noSpaceString.length()];
	
	
	charArray = noSpaceString.toCharArray();
	tempStringArray = new String [1000];
	itterateI = true;
	int k = 0;
	for(int i = 0; i < charArray.length; i++) {
	
	
	
		if(isOperand(charArray[i]) || charArray[i] == '-' && charArray[i - 1] == '(') {
			
			
			
			while(isOperand(charArray[i]) || charArray[i] == '-' && charArray[i - 1] == '(') {
			
			sb.append(charArray[i]);
			
			
			if(i == charArray.length - 1) {
				itterateI = false;
				break;
			}
			
			i++;
		}
			
			if(itterateI)i--;
			tempStringArray [k] = sb.toString();
			sb.setLength(0);
			
			
		}
		
		else  {
				
				
				sb.append(charArray[i]);
				tempStringArray [k] = sb.toString();
				
				
				sb.setLength(0);
		}
		
		if(k == charArray.length - 1) {
			break;
		}
		else
		k++;
		
	}

	
	
	


}
}
		





