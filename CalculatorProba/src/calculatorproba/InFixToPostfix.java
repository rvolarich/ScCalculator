package calculatorproba;

import java.util.ArrayList;



public class InFixToPostfix {
	//input string
	String inString;
	// output string
	String [] outStringArray;
	int qSize;
	char firstChar;
	char secondChar;
	ArrayList<String> outStringArrayList = new ArrayList<String>(1);
	
	InFixToPostfix (int i){
		
		qSize = i;
	}

	public void setString(String inStr) {
		
		
		
	}
	
	public String getString() {
		
		return inString;
	}
	
public void setIsNumber(String s) {
		
		
	    firstChar = s.charAt(0);
	    
	    
	   
	}
	
	public boolean getIsNumber() {
		
		if(firstChar >= '0' && firstChar <= '9') return true;
	    return false;
		
	}
	
	
	
	public boolean isHigherPrecedenceChar(char op1, char op2) {
		switch(op1) {
		
			case '+':
			case '-': return (op2 == '+' || op2 == '-' || op2 == '/' || op2 == '*' || op2 == '^');
			case '*':
			case '/': return (op2 == '/' || op2 == '*' || op2 == '^');
			case '^': return (op2 == '^');
			default: return false;
		}
	}
	
	public boolean isOpeningParenthesisChar(char s) {
		
		switch(s) {
		case '(':return true;
		default: return false;
		}
	}
}



