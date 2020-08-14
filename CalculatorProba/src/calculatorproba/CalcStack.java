package calculatorproba;





public class CalcStack {
	
	
	private int capacity;
	public String obj [];
	public char objChar [];
	public  Double [] objDouble;
	public int top = 0;
	public int topChar = 0;
	public  int topDouble = 0;
	public String o, objPoped;
	public  String aString;
	public int aInt;
	public char aChar;
	
	
	
	CalcStack (){
		
	}
   
	
	CalcStack (String s){
		
		
		this.aString = s;
		
		
	}

   CalcStack (char c){
	
	
	this.aChar = c;
	
}
	
	

	
	public CalcStack (int CAP) {
		
		
		
		capacity = CAP;
		obj = new String[capacity];
		objChar = new char[capacity];
		objDouble = new Double[capacity];
	}
	
	
public void push(String o) {
	
	if(top+1 < capacity) {
		
		top++;
		obj[top] = o;
	}
	
	
}

public void pushChar(char o) {
	
	if(topChar+1 < capacity) {
		
		topChar++;
		objChar[topChar] = o;
	}
	
	
}

public void pushDouble(double o) {
	
	if(topDouble+1 < capacity) {
		
		topDouble++;
		objDouble[topDouble] = o;
	}
	
	
}

public void pop() {
	
	
	
	if(top >= 1) {
	
		
		
		
		obj[top]=null;
		top--;
		
	}
}
	
	public void popChar() {
		
		
		
		if(topChar >= 1) {
		
			
			
			
			objChar[topChar]=Character.UNASSIGNED;
			topChar--;
			
		}
	
	
	
}
	
public void popDouble() {
		
		
		
		if(topDouble >= 1) {
		
			
			
			
			objDouble[topDouble]= null;
			topDouble--;
			
		}
	
	
	
}

public void displayStack(int dispCap) {
	
	for(int i = dispCap - 1; i >= 0; i--) {
		
		
	}
}

public void displayStackChar(int dispCapChar) {
	
	for(int i = dispCapChar - 1; i >= 0; i--) {
		
		
	}
}

public void displayStackDouble(int dispCapChar) {
	
	for(int i = dispCapChar - 1; i >= 0; i--) {
		
		
	}
}

public String getTopObject() {
	  
	
	return obj[top];
}

public char getTopObjectChar() {
	  
	
	return objChar[topChar];
}

public Double getTopObjectDouble() {
	  
	
	return objDouble[topDouble];
}



public String getObjPop() {
	
	
	return objPoped;
}


public boolean isEmpty() {
	
	if(top == 0) return true;
	return false;
}

public boolean isEmptyChar() {
	
	if(topChar == 0) return true;
	return false;
}

public boolean isEmptyDouble() {
	
	if(topDouble == 0) return true;
	return false;
}


}
