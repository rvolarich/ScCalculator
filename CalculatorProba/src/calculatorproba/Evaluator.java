package calculatorproba;



public class Evaluator {
	
	Double operand;
	Double result;
	

	public void convertDouble (String s) {
		
		operand = Double.valueOf(s);
	}
	
	public Double getDouble() {
		
		return operand;
	}
	
	public Double getResult(Double op1, Double op2, String s) {
		
		switch(s) {
		
		case "+": result = op1 + op2;
			break;
		case "-": result = op1 - op2;
			break;
		case "*": result = op1 * op2;
			break;
		case "/": result = op1 / op2;
			break;
		case "^": result = Math.pow(op1, op2);
			
		}
		
		return result;
	}
	

}
