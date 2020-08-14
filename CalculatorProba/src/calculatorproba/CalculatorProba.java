package calculatorproba;

import java.awt.EventQueue;
import java.lang.Math;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.lang.model.element.Parameterizable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.JRadioButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.SystemColor;

public class CalculatorProba{
	
	private JTextField tf;
	private JFrame frame;
	private JTextField num2;
	private JTextField num1;
	public static JLabel textFieldKeyBind;
	public static InputMap imm;
	private String numString;
	boolean startZero = true;
	boolean numReset = false;
	public static String dResult;
	public static Long longResult;
	char f;
	int indexCEdelete;
	int openParIndex = 0;
	int leftPar, rightPar, parDiff;
	int dotIndex;
	public static int keyValue;
	Double op2;
	Double op1;
	Double result;
	Double finalResult;
	Double memoryDouble;
	String memoryString = null;
	public static String stringKeyBind;
	String customStringBind;
	private JTextField txtM;
	private JTextField arrowDisplay;
	// controls erasure of minus
	boolean minusTrue = true;
	String delMinus;
	// blocks plus minus key before pressing any number
	boolean blockPM = false;
	
	boolean allowDot = true;
	boolean allowOperator = true;
	boolean allowZero = false;
	boolean allowNumber = true;
	// sets num2 to 0 once
	boolean setMainDispZero = true;
	boolean allowEq = false;
	// controls PlusMinus
	boolean allowPM = false;
	boolean allowMC = true;
	boolean allowMR = true;
	boolean allowMS = true;
	boolean allowMPlus = true;
	boolean allowMMinus = true;
	boolean allowPowTwo = false;
	boolean allowPi = true;
	boolean allowSin = false;
	boolean allowCos = false;
	boolean allowParOpen = true;
	boolean allowParClose = true;
	boolean allowSqrt = false;
	boolean allowRec = false;
	boolean allowResultOne = false;
	boolean allowResultTwo = false;
	boolean allowResultThree = false;
	// controls whether value from num2 is taken to num1
	boolean closeParDisplayControl = false;
	// controls if value value from num2 is taken to num1 depending on char E position
	boolean closeParDisplayBlock = true;
	boolean allowBackspace = false;
	boolean allowCE = true;
	boolean parEraseDisplay = false;
	boolean parTakeDisplay = true;
	boolean operatorTakeDisplayE = false;
	boolean openParResetNum2Display = true;
	boolean isMouseClicked = false;
	boolean keyBind = false;
	boolean operatorExchange = false;
	static boolean deSerialize = true;
	private Color orig;
	private Color menuHoverColor;
	private Color menuPressedColor;
	JRadioButton radioBtnDeg = new JRadioButton("Degrees");
	JRadioButton radioBtnRad = new JRadioButton("Radians");
	InputMap im;
	public static ActionMap am;
	ButtonGroup bg = new ButtonGroup();
	
	public static Action action;
	
	
	public static void main(String[] args) {
		
		
;		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					CalculatorProba window = new CalculatorProba();
					
					window.frame.setVisible(true);
					if(deSerialize) {
						new SetArrayList(true, false);
						deSerialize = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CalculatorProba() {
		
		
		initialize();
	}
	
	public CalculatorProba(int i) {
		
		
		playSound(i);
	}
	
	public CalculatorProba(String s) {
		
		
		
	}
	

	public int getKeyBind() {
		
		return keyValue;
	}
	
	private static void calc(String str) {
		
		PostFix pf = new PostFix();
		
		pf.setString(str);
		
		InFixToPostfix itp = new InFixToPostfix(pf.setArraySize());
		CalculatorProba cp = new CalculatorProba("string");
		CalcStack cs = new CalcStack(str.length());
		Evaluator ev = new Evaluator();
		itp.outStringArray = new String [pf.setArraySize()];		
		int a;
		for(int i = 0; i < pf.setArraySize(); i++) {
			// check whether string is an operand or operator
			itp.setIsNumber(pf.tempStringArray[i]);
			
			
		if(itp.getIsNumber() || pf.tempStringArray[i].charAt(0) == '-' && cp.getCharCount(pf.tempStringArray[i], 0) > 1) {
				
				itp.outStringArrayList.add(pf.tempStringArray[i]);
				
				if(pf.finalStringArrayLastElement(i)) {
					while(!cs.isEmpty()) {
						if(cs.getTopObjectChar() != '(') {
						itp.outStringArrayList.add(cs.getTopObject());
						}
						cs.pop();
						cs.popChar();
					}
			}
		}
		else {
			
			
				
			if(itp.isOpeningParenthesisChar(pf.tempStringArray[i].charAt(0))) {
				cs.push(pf.tempStringArray[i]);
				cs.pushChar(pf.tempStringArray[i].charAt(0));
			}
			
			else if(pf.tempStringArray[i].charAt(0) == ')') {
				while(!itp.isOpeningParenthesisChar(cs.getTopObjectChar())) {
					itp.outStringArrayList.add(cs.getTopObject());
					cs.pop();
					cs.popChar();
			}
				cs.pop();
				cs.popChar();
			}	
			else {		
					while(!cs.isEmpty() && itp.isHigherPrecedenceChar(pf.tempStringArray[i].charAt(0), cs.getTopObjectChar()) 
							&& !itp.isOpeningParenthesisChar(cs.getTopObjectChar())) {
						itp.outStringArrayList.add(cs.getTopObject());
						cs.pop();
						cs.popChar();
						}
						
							
					cs.push(pf.tempStringArray[i]);
					cs.pushChar(pf.tempStringArray[i].charAt(0));
					
				}
				
			if(pf.finalStringArrayLastElement(i)) {
				while(!cs.isEmpty()) {
					if(cs.getTopObjectChar() != '(') {
						itp.outStringArrayList.add(cs.getTopObject());
						}
					cs.pop();
					cs.popChar();
			}
			}
				
			
		}
			
		
		}
		int arraySize = itp.outStringArrayList.size();
		String[] finalArray = itp.outStringArrayList.toArray(new String[0]);
		
		
		
		for(int i = 0; i < finalArray.length; i++) {

			itp.setIsNumber(finalArray[i]);
			if(itp.getIsNumber() || finalArray[i].charAt(0) == '-' && cp.getCharCount(finalArray[i], 0) > 1) {
				ev.convertDouble(finalArray[i]);
				cs.pushDouble(ev.getDouble());
				

			}
			
			else {
				
				cp.op2 = cs.getTopObjectDouble();			
				cs.popDouble();
				cp.op1 = cs.getTopObjectDouble();			
				cs.popDouble();
				cp.result = ev.getResult(cp.op1, cp.op2, finalArray[i]);
				cs.pushDouble(cp.result);
				
			}
		}
	
	
	longResult = cs.getTopObjectDouble().longValue();
	if(cs.getTopObjectDouble() % 1 == 0) {
		if(cp.findE(cs.getTopObjectDouble().toString()))
			dResult = cs.getTopObjectDouble().toString();
		else dResult = cs.getTopObjectDouble().toString().substring(0, cs.getTopObjectDouble().toString().length() - 2);
	}
	else dResult = cs.getTopObjectDouble().toString();
	cs.popDouble();

	
	
	
	
	}
	
	
	
	
	public String deleteMinus(String s) {
		
		StringBuilder sb = new StringBuilder();
		for(char c: s.toCharArray()) {
			
			if(c >= '0' && c <= '9' || c == '.') {
				
				sb.append(c);
			}
					
		}
		
		delMinus = sb.toString();
		return delMinus;
		
	}
	
	
	public void displayText(String str) {
		
		String s;
		Double d = Double.parseDouble(str);
		
		if(infinityNan(str)) {
			num1.setText("Press C" );
			num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
			num2.setText("Infinity" );
			arrowDisplay.setText(null);
			allowBtn();
		}
		else if(findE(str)) {
			if(getCharCount(str, 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
			if(getCharCount(str, 0) > 23) num2.setText(str.substring(0, 23));
			num2.setText(str);
		}
		else if(d % 1 == 0 ) {
			s = str.substring(0, str.length() - 2);
		if(getCharCount(s, 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		if(getCharCount(s, 0) > 23) num2.setText(s.substring(0, 23));
		else num2.setText(s);
		}
		else {
			if(getCharCount(str, 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
			if(getCharCount(str, 0) > 23) num2.setText(str.substring(0, 23));
			else num2.setText(str);
		}
	}
	
	
	
	
	// counts chars displayed on num1 and num2 text fields
	public int getCharCount(String s, int a) {
		
		for(char c: s.toCharArray()) {
			a++;
		}
		
		return a;
	}
	
	public boolean findLastChar(String s, char ch) {
		
		if(s == "") return false;
		else {
			char c = s.charAt(s.toCharArray().length - 1);
			if(c == ch) return true;
		}
		return false;
		
	}
	
	public boolean maxLong(Long l) {
		
		if(l >= Long.MAX_VALUE) return true;
		return false;
	}
	
	public boolean findE (String str) {
		
		for(char c: str.toCharArray()) {
			
			if(c == 'E') return true;
		}
		return false;
	}
	
public boolean findMinus (String str) {
		
		if(str.charAt(0) == '-') return true;
		return false;
	}

public void displayArrow() {
	
	if(getCharCount(num1.getText(), 0) > 29) {
		
		arrowDisplay.setText("<<");
	}
	else arrowDisplay.setText(null);
}

private void countPar(String str) {
	
	leftPar = rightPar = 0;
	
	for(char c: str.toCharArray()) {
		
		if(c == '(') {
			
			leftPar++;
		}
		
		else if(c == ')') {
			
			rightPar++;
		}
		
		parDiff = leftPar - rightPar;
	}
}

private boolean foundDot(String str) {
	
	for(char c: str.toCharArray()) {
		
		if(c == '.') return true;
		
	}
	
	return false;
}

private int findDotIndex(String str) {
	
	for(int i = 0; i < str.length(); i++) {
		
		if(str.charAt(i) == '.') {
			
			dotIndex = i;
			break;
		}
	}
	
	return dotIndex;
	
}


public int configCEdelete(String s) {
	int a = 0;
	for(int i = s.length() - 1; i >= 0; i--) {
		if(s.charAt(i) == ')') {
			a++;
		}
		else if(s.charAt(i) == '(') {
			a--;
			indexCEdelete = i;
			if(a == 0) break;
		}
	}
	
	return indexCEdelete;
}

public int findOpenParIndex(String s) {
	
	for(int i = s.length() - 1; i >= 0; i--) {
		
	if(s.charAt(i) == ')') {
		openParIndex = 0;
		break;	
	}
	else if(s.charAt(i) == '(') {
			openParIndex = i;
			break;
		}
	}
	
	return openParIndex;
}

public boolean infinityNan(String s) {
	
	if(s == null) return false;
	else if(s.contains("Infinity") || s.contains("NaN")) {
		return true;
	}
	
	return false;
	
}

public void allowBtn() {
	
	allowCE = false;
	allowBackspace = false;
	allowEq = false;
	allowNumber = false;
	allowOperator = false;
	allowPM = false;
	allowDot = false;
	allowMC = false;
	allowMR = false;
	allowMS = false;
	allowMPlus = false;
	allowMMinus = false;
	allowPowTwo = false;
	allowPi = false;
	allowSin = false;
	allowCos = false;
	allowParOpen = false;
	allowParClose = false;
	allowSqrt = false;
	allowRec = false;
	operatorExchange = false;
	
	
}

public void calculation() {
	
	configCEdelete(num1.getText());
	calc(num1.getText().substring(indexCEdelete, num1.getText().length() - 1));
	if(getCharCount(dResult, 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
	if(getCharCount(dResult, 0) > 23) num2.setText(dResult.substring(0, 23));
	else num2.setText(dResult);
	if(infinityNan(num2.getText())) {
		num1.setText("Press C" );
		arrowDisplay.setText(null);
	}
}


public boolean checkParOpPar(String str) {
	
	String s = str.replaceAll("\\s","");
	if(s.substring(s.length() - 2, s.length()).contains("/(") || s.substring(s.length() - 2, s.length()).contains("*(") || 
			s.substring(s.length() - 2, s.length()).contains("+(") || s.substring(s.length() - 2, s.length()).contains("-(") ||
			s.substring(s.length() - 2, s.length()).contains("^(") ) return true;
	
	return false;
}

public void equalControl() {
	
	if(infinityNan(num2.getText())) {
		
		playSound(1);
		allowBtn();
	}
	else {
		if(!allowEq) playSound(1);
	if(allowEq) {
		
		invalidEntry();
		
		if(closeParDisplayBlock) {
		if(closeParDisplayControl) num2.setText(null);
		
		else if(findMinus(num2.getText())) {
			
			num1.setText(num1.getText() + " (" + num2.getText());
		}
		else {
			
			num1.setText(num1.getText() + num2.getText());
		}
	
		calc(num1.getText());
		num1.setText(null);
	
	
		if(getCharCount(dResult, 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		if(getCharCount(dResult, 0) > 23) num2.setText(dResult.substring(0, 23));
		else num2.setText(dResult);
		if(infinityNan(num2.getText())) {
			num1.setText("Press C" );
			arrowDisplay.setText(null);
			
		}
		closeParDisplayBlock = true;
	}

	allowEq = false;
	startZero = true;
	allowZero = false;
	allowNumber = true;
	allowPM = false;
	allowOperator = true;
	allowResultTwo = false;
	allowResultThree = false;
	closeParDisplayControl = false;
	allowBackspace = false;
	parEraseDisplay = false;
	playSound(0);
	
}
	arrowDisplay.setText(null);
	if(infinityNan(num2.getText())) allowBtn();
}
}

public void operatorControl(String single, String both) {
	
	
	if(infinityNan(num2.getText())) {
		
		playSound(1);
		allowBtn();
	}
	else {
		
	  if(allowOperator) {
		if(findE(num2.getText())) {
				int a = num2.getText().indexOf('E');
				
				if(num2.getText().charAt(num2.getText().length() - 1) == 'E'){
					num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
					num2.setText("Invalid entry !");
					num1.setText(null);
					allowCE = false;
					allowBackspace = false;
					
				}
				
				else if(num2.getText().toCharArray()[num2.getText().indexOf('E') + 1] == '-' && (parTakeDisplay)) {
					num1.setText(num1.getText() + "(" + num2.getText().substring(0, a) + 
							" * 10 ^ (" + num2.getText().substring(a + 1, num2.getText().toCharArray().length ) + "))" + both);
					operatorTakeDisplayE = false;
					parTakeDisplay = false;
				}
				
				else if(!operatorTakeDisplayE){
					num1.setText(num1.getText() + " " + single);
					
				}
				
				else {
					
					num1.setText(num1.getText() + "(" + num2.getText().substring(0, a) + " * 10 ^ " + 
							num2.getText().substring(a + 1, num2.getText().toCharArray().length ) + ")" + both);
				}
				
			}
		else if(findMinus(num2.getText())) 
		{ 
			if(closeParDisplayControl) num1.setText(num1.getText() + " " + single);
			else if(findLastChar(num2.getText(), '.')) {
				num1.setText(num1.getText() + "(" + num2.getText().substring(0, num2.getText().length() - 1) +  ")" + both);
				num2.setText(num2.getText().substring(0, num2.getText().length() - 1));
				
			}
			else {
				num1.setText(num1.getText() + "(" + num2.getText() +  ")" + both);
				
			}
		}
		else if(foundDot(num2.getText()) && Double.parseDouble(num2.getText()) % 1 == 0.0) {
			num1.setText(num1.getText() + num2.getText().substring(0, findDotIndex(num2.getText())) + " " + single);
			num2.setText(num2.getText().substring(0, findDotIndex(num2.getText())));
		}
		else if(closeParDisplayControl) num1.setText(num1.getText() + " " + single);
		else num1.setText(num1.getText() + num2.getText() + " " + single);
		if(allowResultOne) allowResultTwo = true;
		if(closeParDisplayBlock) {
		if(allowResultThree) {
			
			closeParDisplayBlock = true;
			findOpenParIndex(num1.getText());
			calc(num1.getText().substring(openParIndex, num1.getText().length() - 2));
			
		
		
			if(getCharCount(dResult, 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
			if(getCharCount(dResult, 0) > 23) num2.setText(dResult.substring(0, 23));
			else num2.setText(dResult);
			if(infinityNan(num2.getText())) {
				num1.setText("Press C" );
				arrowDisplay.setText(null);
				
			}
		}
		}	
		startZero = true;
		allowDot = true;
		allowOperator = false;
		allowZero = false;
		allowNumber = true;
		allowCE = true;
		allowEq = true;
		allowBackspace = false;
		closeParDisplayBlock = true;
		closeParDisplayControl = false;
		parTakeDisplay = true;
		operatorTakeDisplayE = true;
		parEraseDisplay = false;
		openParResetNum2Display = true;
		operatorExchange = true;
		playSound(0);
		}
		
		else if (operatorExchange){
			
			if(findLastChar(num1.getText(), '(')) num1.setText(num1.getText() + num2.getText() + " " + single);
			else num1.setText(num1.getText().substring(0, num1.getText().length() - 3 ) + both);
			playSound(0);
		}
	  
		else {
			
			playSound(1);
		}
		
		displayArrow();
		
}
}

public void powerControl() {
	
	if(infinityNan(num2.getText())) {
		playSound(1);
		allowBtn();
		}
	else if(allowOperator) {
		if(findE(num2.getText())) {
			int a = num2.getText().indexOf('E');
			
			if(num2.getText().charAt(num2.getText().length() - 1) == 'E'){
				num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
				num2.setText("Invalid entry !");
			}
			
			else if(num2.getText().toCharArray()[num2.getText().indexOf('E') + 1] == '-') {
				num1.setText(num1.getText() + "(" + num2.getText().substring(0, a) + 
						" * 10 ^ (" + num2.getText().substring(a + 1, num2.getText().toCharArray().length ) + "))" + " ^ ");
			}
			
			
			
			else {
				num1.setText(num1.getText() + "(" + num2.getText().substring(0, a) + " * 10 ^ " + 
			num2.getText().substring(a + 1, num2.getText().toCharArray().length ) + ")" + " ^ ");
				
			}
			
		}
	
		else if(findMinus(num2.getText())) 
		{ 
			if(closeParDisplayControl) num1.setText(num1.getText() + " " + "^ ");
			else num1.setText(num1.getText() + " (" + num2.getText() + ")" + " " + "^ ");
		}
		else if(foundDot(num2.getText()) && Double.parseDouble(num2.getText()) % 1 == 0.0) {
			num1.setText(num1.getText() + num2.getText().substring(0, findDotIndex(num2.getText())) + " " + "^ ");
			num2.setText(num2.getText().substring(0, findDotIndex(num2.getText())));
		}
		else if(closeParDisplayControl) num1.setText(num1.getText() + " " + "^ ");
		else num1.setText(num1.getText() + num2.getText() + " " + "^ ");
		
	startZero = true;
	allowDot = true;
	allowOperator = false;
	allowZero = false;
	allowNumber = true;
	allowEq = true;
	allowBackspace = false;
	closeParDisplayBlock = true;
	closeParDisplayControl = false;
	parTakeDisplay = true;
	operatorTakeDisplayE = true;
	parEraseDisplay = false;
	operatorExchange = true;
	playSound(0);
	}
	
	else if(operatorExchange){
		if(findLastChar(num1.getText(), '(')) num1.setText(num1.getText() + num2.getText() + " " + "^ ");
		else num1.setText(num1.getText().substring(0, num1.getText().length() - 3 ) + " ^ ");
		playSound(0);
	}
	
	else {
		playSound(1);
	}
	displayArrow();
}

public void numberControl(int i) {
	
	if(!allowNumber) playSound(1);
	if(allowNumber) {
		
		if(startZero) {
			
			num2.setText(null);
			startZero = false;
		}
		if(getCharCount(num2.getText(), 1) > 23) allowNumber = false;
		num2.setText(num2.getText() + i);
		if(getCharCount(num2.getText(), 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		else num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
		if(allowResultTwo) allowResultThree = true;
		allowResultOne = true;
		blockPM = true;
		allowOperator = true;
		allowZero = true;
		allowPM = true;
		closeParDisplayBlock = true;
		allowBackspace = true;
		allowRec = true;
		allowSin = true;
		allowCos = true;
		openParResetNum2Display = false;
		allowPowTwo = true;
		allowSqrt = true;
		minusTrue = true;
		
		
		playSound(0);
		}
	}

public void dotControl(String s) {
	
	if(!allowDot) playSound(1);
	if(allowDot) {
		
		if(startZero) {
			
			num2.setText("0");
			startZero = false;
		}
		num2.setText(num2.getText() + s);
		blockPM = true;
		allowDot = false;
		allowZero = true;
		allowBackspace = true;
		playSound(0);
}
	
}

public void ceControl() {
	
	if(!allowCE) playSound(1);
	if(allowCE) {
		if(parEraseDisplay) {
		if(findLastChar(num1.getText(), ')')){
			configCEdelete(num1.getText());
			num1.setText(num1.getText().substring(0, indexCEdelete));
			num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
			num2.setText("0");
			displayArrow();
		}
		}
		
		else {
			
			num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
			num2.setText("0");
		}
	startZero = true;
	allowNumber = true;
	parEraseDisplay = false;
	parTakeDisplay = true;
	operatorTakeDisplayE = true;
	allowOperator = false;
	closeParDisplayControl = false;
	allowSin = false;
	allowCos = false;
	playSound(0);
}
	
}

public void cControl(){
	
	num2.setText("0");
	num1.setText(null);
	num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
	arrowDisplay.setText(null);
	startZero = true;
	blockPM = false;
	allowDot = true;
	allowZero = false;
	allowOperator = true;
	allowNumber = true;
	allowPM = false;
	allowMC = true;
	allowMR = true;
	allowMS = true;
	allowMPlus = true;
	allowMMinus = true;
	allowPi = true;
	allowSin = false;
	allowCos = false;
	allowParOpen = true;
	allowParClose = true;
	allowResultOne = false;
	allowResultTwo = false;
	allowResultThree = false;
	closeParDisplayControl = false;
	closeParDisplayBlock = true;
	parEraseDisplay = false;
	allowBackspace = false;
	allowEq = false;
	allowPowTwo = false;
	allowRec = false;
	allowSqrt = false;
	playSound(0);
}

public void backSpaceControl() {
	
	if(!allowBackspace) playSound(1);
	if(allowBackspace) {
		String c;
		if(num2.getText().length() == 1) {
			num2.setText("0");
			startZero = true;
			allowPM = false;
			allowBackspace = false;
		}
		else {
			c = num2.getText().substring(num2.getText().length() - 1);
			if(c.charAt(0) == '.') {
				allowDot = true;
			}
			
		num2.setText(num2.getText().substring(0, num2.getText().length() - 1));
		c = num2.getText();
		
		}
		playSound(0);
		}
	
}

public void zeroControl() {
	
	if(!allowNumber) playSound(1);
	if(allowNumber) {
		if(allowZero){
			if(startZero) {
				
				num2.setText(null);
				startZero = false;
				
			}
		if(getCharCount(num2.getText(), 1) > 23) allowNumber = false;
		num2.setText(num2.getText() + 0);
		if(getCharCount(num2.getText(), 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		else num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
		allowOperator = true;
		if(allowResultTwo) allowResultThree = true;
		allowResultOne = true;
		
	}
		else {
			num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
			num2.setText("0");
			allowZero = false;
		}
		
		playSound(0);
	}
}

public void openParenthesisControl(){
	
	if(!allowParOpen) playSound(1);
	if(allowParOpen) {
		if(parEraseDisplay) {
		
		
		if(findLastChar(num1.getText(), ')')){
			configCEdelete(num1.getText());
			num1.setText(num1.getText().substring(0, indexCEdelete) + "(");
			startZero = true;
			parEraseDisplay = false;
			displayArrow();
		}
		
		}
		else {
			if(openParResetNum2Display) {
				
				num1.setText(num1.getText() + "(");
				num2.setText("0");
			}
			else num1.setText(num1.getText() + "(");
		}
	
	allowNumber = true;
	closeParDisplayControl = false;
	closeParDisplayBlock = false;
	parTakeDisplay = true;
	operatorTakeDisplayE = true;
	allowResultOne = true;
	allowResultTwo = false;
	allowResultThree = false;
	displayArrow();
	playSound(0);
}
	
}

public void closeParenthesisControl(){
	
	if(infinityNan(num2.getText())) {
		
		playSound(1);
		allowBtn();
	}
	
	else {
		if(!allowParClose) playSound(1);
	if(allowParClose) {
		countPar(num1.getText());
	if(parDiff > 0) {
		if(findE(num2.getText())){
			int a = num2.getText().indexOf('E');
			
			
			if(num2.getText().charAt(num2.getText().length() - 1) == 'E'){
				num1.setText(null);
				num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
				num2.setText("Invalid entry !");
				closeParDisplayBlock = false;
			}
			
			else if(num2.getText().toCharArray()[num2.getText().indexOf('E') + 1] == '-' && (parTakeDisplay)) {
				num1.setText(num1.getText() + "(" + num2.getText().substring(0, a) + 
						" * 10 ^ (" + num2.getText().substring(a + 1, num2.getText().toCharArray().length ) + ")))");
				calculation();
				parTakeDisplay = false;
			}
			
			else if(parTakeDisplay){
				num1.setText(num1.getText() + "(" + num2.getText().substring(0, a) + " * 10 ^ " + 
			num2.getText().substring(a + 1, num2.getText().toCharArray().length ) + "))");
				calculation();
				parTakeDisplay = false;
			}
			
			else {
				num1.setText(num1.getText() + ")");
				
			}
			
		}
		else if(findMinus(num2.getText()) && (parTakeDisplay)) 
		{ 
			num1.setText(num1.getText() + "(" + num2.getText() + ")");
			parTakeDisplay = false;
		}
		else if(foundDot(num2.getText()) && Double.parseDouble(num2.getText()) % 1 == 0.0) {
			num1.setText(num1.getText() + num2.getText().substring(0, findDotIndex(num2.getText())) + ")");
			num2.setText(num2.getText().substring(0, findDotIndex(num2.getText())));
			parTakeDisplay = false;
		}
		else if (parTakeDisplay) {
			num1.setText(num1.getText() + num2.getText() + ")");
			parTakeDisplay = false;
		}
		else {
			num1.setText(num1.getText() + ")");
			
		}
	if(closeParDisplayBlock) {	
		num2.setText(null);
	
	calculation();
	
	}
	closeParDisplayBlock = true;
	closeParDisplayControl = true;
	allowNumber = false;
	allowBackspace = false;
	parEraseDisplay = true;
	allowEq = true;
	allowOperator = true;
	operatorExchange = false;
	operatorTakeDisplayE = false;
	
	}
	displayArrow();
	playSound(0);
}
	
}
}

public void plusMinusControl() {
	
	if(!allowPM) playSound(1);
	if(allowPM) {
		if(blockPM) {
		if(minusTrue){
			
			num2.setText( "-" + num2.getText() );
			if(getCharCount(num2.getText(), 0) > 18) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
			minusTrue = false;
			allowOperator = true;
			
			
		}
		
			else {
			
			deleteMinus(num2.getText());
			num2.setText(delMinus);
			minusTrue = true;
			allowOperator = true;
			
			
		}
	}
		playSound(0);
	}
}

public void mClearControl() {
	
	if(!allowMC) playSound(1);
	if(allowMC) {
		
		memoryString = null;
		memoryDouble = 0.0;
		txtM.setText(null);
		playSound(0);
		}
}

public void mRecallControl() {
	
	if(!allowMR) playSound(1);
	if(allowMR) {
		if(memoryDouble % 1 == 0) {
			if(findE(memoryDouble.toString()))
				memoryString = memoryDouble.toString();
			else memoryString = memoryDouble.toString().substring(0, memoryDouble.toString().length() - 2);
		}
		else memoryString = memoryDouble.toString();
		
		if(getCharCount(memoryString, 0) > 17) num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		if(getCharCount(memoryString, 0) > 23) {
			num2.setText(memoryString.substring(0, 23));
			memoryString = null;
			
		}
		else {
			if(memoryDouble == 0.0) {
				num2.setText("0");
				memoryString = null;
				
			}
			else num2.setText(memoryString);
		}
		startZero = true;
		allowResultOne = true;
		allowBackspace = false;
		playSound(0);
	}
}

public void mSaveControl() {
	
	if(!allowMS) playSound(1);
	if(allowMS) {
		memoryString = num2.getText();
		memoryDouble = Double.parseDouble(memoryString);
		if(memoryDouble != 0.0) txtM.setText("M");
		else txtM.setText(null);
		memoryString = null;
		startZero = true;
		playSound(0);
	}
}

public void mPlusControl() {
	
	if(!allowMPlus) playSound(1);
	if(allowMPlus) {
		if(memoryString == null) {
		memoryString = num2.getText();
		memoryDouble = Double.parseDouble(memoryString);
		if(memoryDouble != 0.0) txtM.setText("M");
		
		}
		else{
			memoryDouble = memoryDouble + Double.parseDouble(num2.getText());
			memoryString = memoryDouble.toString();
			if(memoryDouble == 0.0) {
				txtM.setText(null);
				memoryString = null;
			}
		}
		
		playSound(0);
	}
}

public void mMinusControl() {
	
	if(!allowMMinus) playSound(1);
	if(allowMMinus) {
		if(memoryString == null) {
			memoryString = num2.getText();
			memoryDouble = Double.parseDouble(memoryString) * -1;
			if(memoryDouble != 0.0) txtM.setText("M");
			
			}
			else{
				memoryDouble = memoryDouble - Double.parseDouble(num2.getText());
				memoryString = memoryDouble.toString();
				if(memoryDouble == 0.0) {
					txtM.setText(null);
					memoryString = null;
				}
			}
		
		playSound(0);
	}
}

private void controlRec() {
	
	if(!allowRec) playSound(1);
	if(allowRec) {
		
		Double d = 1 / Double.parseDouble(num2.getText());
		displayText(d.toString());
		playSound(0);
}
}

private void controlSqrt() {
	
	if(!allowSqrt) playSound(1);
	if(allowSqrt) {
		
		Double d = Double.parseDouble(num2.getText());
		Double powTwo = Math.sqrt(d);
		displayText(powTwo.toString());
		playSound(0);
		}
}

private void controlPowTwo() {
	
	if(!allowPowTwo) playSound(1);
	if(allowPowTwo) {
		
		Double d = Double.parseDouble(num2.getText());
		Double powTwo = Math.pow(d, 2);
		displayText(powTwo.toString());
		playSound(0);
}
}

private void controlPi() {
	
	if(!allowPi) playSound(1);
	if(allowPi) {
		
		Double d = Math.PI;
		displayText(d.toString());
		allowResultOne = true;
		allowOperator = true;
		allowPowTwo = true;
		allowSin = true;
		allowCos = true;
		allowRec = true;
		allowSqrt = true;
		playSound(0);
}
}

private void controlSin() {
	
	if(!allowSin) playSound(1);
	if(allowSin) {
		if(radioBtnDeg.isSelected()) {
			
			Double d = Math.toRadians(Double.parseDouble(num2.getText()));
			Double sinRad = Math.sin(d);
			displayText(sinRad.toString());
			
		}
		
		else if(radioBtnRad.isSelected()) {
			
			Double d = Math.sin(Double.parseDouble(num2.getText()));
			displayText(d.toString());
			
		}
		playSound(0);
}
}

private void controlCos() {
	
	if(!allowCos) playSound(1);
	if(allowCos) {
		if(radioBtnDeg.isSelected()) {
			
			Double d = Math.toRadians(Double.parseDouble(num2.getText()));
			Double sinRad = Math.cos(d);
			displayText(sinRad.toString());
			
		}
		
		else if(radioBtnRad.isSelected()) {
			
			Double d = Math.cos(Double.parseDouble(num2.getText()));
			displayText(d.toString());
			
		}
		playSound(0);
}
}

public void invalidEntry() {
	
	if(findE(num2.getText())) {
		
		
		if(num2.getText().charAt(num2.getText().length() - 1) == 'E'){
			num2.setFont(new Font("Calibri Light", Font.PLAIN, 30));
			num2.setText("Invalid entry !");
			num1.setText(null);
			allowCE = false;
			allowBackspace = false;
			closeParDisplayBlock = false;
		}
	}
}

public void keyBind (JComponent comp, int keyCode, int mod, String id, ActionListener actionListener) {
	
	InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	im.put(KeyStroke.getKeyStroke(keyCode, mod, false), id);
	ActionMap am = comp.getActionMap();
	am.put(id, new AbstractAction() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2575523625412552405L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			actionListener.actionPerformed(e);
			
		}
	});
}




public void keyBindxy () {
	
	ArrayList<String> actions = new ArrayList<String>();
	ArrayList<Integer> keys = new ArrayList<Integer>();;
	SetArrayList sal = new SetArrayList(true);
	actions = sal.getArrayListActions();
	keys = sal.getArrayListKeys();

	 imm = textFieldKeyBind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	imm.clear();
	for(int i = 0; i < keys.size(); i++) {
		
		imm.put(KeyStroke.getKeyStroke(keys.get(i), 0), actions.get(i));
	}
		

	am = textFieldKeyBind.getActionMap();
	
	am.put("x^2" , new AbstractAction() {
		
		private static final long serialVersionUID = 6454617287792973686L;
		@Override
		public void actionPerformed(ActionEvent e) {
			controlPowTwo();
			}
	});
	
	am.put("x^y" , new AbstractAction() {
		
		private static final long serialVersionUID = 6427889137835575159L;
		@Override
		public void actionPerformed(ActionEvent e) {
			powerControl();
			}
	});
	
	am.put("Pi" , new AbstractAction() {
		
		private static final long serialVersionUID = 1807010885579463576L;
		@Override
		public void actionPerformed(ActionEvent e) {
			controlPi();
			}
	});
	
	am.put("Sin" , new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controlSin();
			}
		});

	am.put("Cos" , new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controlCos();
			}
		});
	
	am.put("Left Parenthesis" , new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			openParenthesisControl();
			}
		});
	
	am.put("Right Parenthesis" , new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			closeParenthesisControl();
			}
		});
	
	am.put("1/x" , new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controlRec();
			}
		});
	
	am.put("Sqrt" , new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			controlSqrt();
			}
		});
	
	
	
	
}

public void keyBindings() {
	
//////////////////NUMBERS ////////////////////////////////////
	
		keyBind(num2, KeyEvent.VK_1, 0, "1", (evt) -> {
			numberControl(1);});
		keyBind(num2, KeyEvent.VK_NUMPAD1, 0,  "NUM1", (evt) -> {
		numberControl(1);});
		keyBind(num2, KeyEvent.VK_2, 0, "2", (evt) -> {
		numberControl(2);});
		keyBind(num2, KeyEvent.VK_NUMPAD2, 0, "NUM2", (evt) -> {
		numberControl(2);});
		keyBind(num2, KeyEvent.VK_3, 0, "3", (evt) -> {
		numberControl(3);});
		keyBind(num2, KeyEvent.VK_NUMPAD3, 0, "NUM3", (evt) -> {
		numberControl(3);});
		keyBind(num2, KeyEvent.VK_4, 0, "4", (evt) -> {
		numberControl(4);});
		keyBind(num2, KeyEvent.VK_NUMPAD4, 0, "NUM4", (evt) -> {
		numberControl(4);});
		keyBind(num2, KeyEvent.VK_5, 0, "5", (evt) -> {
		numberControl(5);});
		keyBind(num2, KeyEvent.VK_NUMPAD5, 0, "NUM5", (evt) -> {
		numberControl(5);});
		keyBind(num2, KeyEvent.VK_6, 0, "6", (evt) -> {
		numberControl(6);});
		keyBind(num2, KeyEvent.VK_NUMPAD6, 0, "NUM6", (evt) -> {
		numberControl(6);});
		keyBind(num2, KeyEvent.VK_7, 0, "7", (evt) -> {
		numberControl(7);});
		keyBind(num2, KeyEvent.VK_NUMPAD7, 0, "NUM7", (evt) -> {
		numberControl(7);});
		keyBind(num2, KeyEvent.VK_8, 0, "8", (evt) -> {
		numberControl(8);});
		keyBind(num2, KeyEvent.VK_NUMPAD8, 0, "NUM8", (evt) -> {
		numberControl(8);});
		keyBind(num2, KeyEvent.VK_9, 0, "9", (evt) -> {
		numberControl(9);});
		keyBind(num2, KeyEvent.VK_NUMPAD9, 0, "NUM9", (evt) -> {
		numberControl(9);});
		keyBind(num2, KeyEvent.VK_0, 0, "0", (evt) -> {
		zeroControl();});
		keyBind(num2, KeyEvent.VK_NUMPAD0, 0, "NUM0", (evt) -> {
		zeroControl();});
		
		/////////////////   DOT    ////////////////////////////////////	
		
		keyBind(num2, KeyEvent.VK_PERIOD, 0, "PERIOD", (evt) -> {
		dotControl(".");});
		keyBind(num2, KeyEvent.VK_DECIMAL, 0, "NUMPADPERIOD", (evt) -> {
		dotControl(".");});
		
		////////////////////OPERATORS  ///////////////////////////
		
		keyBind(num2, KeyEvent.VK_7, InputEvent.SHIFT_DOWN_MASK, "DIV", (evt) -> {
		operatorControl("/ ", " / ");});
		keyBind(num2, KeyEvent.VK_DIVIDE, 0, "NUMPADDIVIDE", (evt) -> {
		operatorControl("/ ", " / ");});
		keyBind(num2, KeyEvent.VK_MULTIPLY, 0, "NUMPADMULTI", (evt) -> {
		operatorControl("* ", " * ");});
		keyBind(num2, KeyEvent.VK_MINUS, 0, "SUB", (evt) -> {
		plusMinusControl();});
		keyBind(num2, KeyEvent.VK_SUBTRACT, 0, "NUMPADSUB", (evt) -> {
		operatorControl("- ", " - ");});
		keyBind(num2, KeyEvent.VK_ADD, 0, "NUMPADADD", (evt) -> {
		operatorControl("+ ", " + ");});
		
		///////////////////// EQUAL ////////////   CE    ////////////  BACKSPACE
		
		keyBind(num2, KeyEvent.VK_ENTER, 0, "ENTER", (evt) -> {
		equalControl();});
		keyBind(num2, KeyEvent.VK_BACK_SPACE, 0, "BACKSPACE", (evt) -> {
		backSpaceControl();});
		keyBind(num2, KeyEvent.VK_DELETE, 0, "CE", (evt) -> {
		ceControl();});
		keyBind(num2, KeyEvent.VK_ESCAPE, 0, "C", (evt) -> {
		cControl();});
		
		/////////////////  PARENTHESIS  ///////////////////////////////
		
		keyBind(num2, KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK, "OPENPAR", (evt) -> {
		openParenthesisControl();});
		keyBind(num2, KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK, "CLOSEPAR", (evt) -> {
		closeParenthesisControl();});
		
		
		/////////////////  MEMORY  ///////////////////////////////
		
		
		keyBind(num2, KeyEvent.VK_F5, 0, "mMINUS", (evt) -> {
		mMinusControl();});
		keyBind(num2, KeyEvent.VK_F4, 0, "mPLUS", (evt) -> {
		mPlusControl();});
		keyBind(num2, KeyEvent.VK_F3, 0, "mSAVE", (evt) -> {
		mSaveControl();});
		keyBind(num2, KeyEvent.VK_F2, 0, "mRECALL", (evt) -> {
		mRecallControl();});
		keyBind(num2, KeyEvent.VK_F1, 0, "mCLEAR", (evt) -> {
		mClearControl();});

	
}
	

	public String catchString(String str) {
		
		numString = str;
		return numString;
	}
	
	public void setKeyBind(int keyValue) {
		
		keyBind(num2, keyValue, 0, "1", (evt) -> {
			numberControl(1);});
	}
	
	private void playSound(int toggle) {
		
		int [] intArr = new int[3];
		Serialization serialize = new Serialization("soundVolumeSettings.ser");
		intArr = serialize.getSoundVolumeSettings();
		if(toggle == 0) {
		if(intArr[0] == 0) {
			
			new Sound("blup.wav", intArr[1]);
		}
		else if(intArr[0] == 1) {
			
			new Sound("chick.wav", intArr[1]);
		}
		}
		else if(toggle == 1){
			new Sound("alert.wav", intArr[2]);
		}
		
		
	}

	
	

	
	public void initialize() {
		
		
		
		
		frame = new JFrame();
		
		frame.setResizable(false);
		frame.setSize(new Dimension(420, 632));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textFieldKeyBind = new JLabel();
		frame.add(textFieldKeyBind);
		
		num1 = new JTextField("");
		num2 = new JTextField("0");
		
		keyBindxy();
		keyBindings();
		
		menuHoverColor = new Color(195,195,198);
		menuPressedColor = new Color(147,160,186);
		Border bLine = BorderFactory.createLineBorder(new Color(134, 172, 216), 1);
		Border bRaised = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		JLabel menuBar = new JLabel();
		menuBar.setBounds(92, 0, 322, 21);
		menuBar.setVisible(true);
		menuBar.setBackground(Color.BLACK);
		menuBar.setLayout(null);
		frame.getContentPane().add(menuBar);
		
		
		JLabel options = new JLabel("Options");
		Color menuOriginal = options.getBackground();
		Color menuSelect = new Color(210, 217, 231);
		JLabel preferences = new JLabel("");
		JLabel help = new JLabel("Help");
		JLabel about = new JLabel("");
		JLabel prefText = new JLabel("Preferences");
		JLabel aboutText = new JLabel("About");
		
		
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				help.setBackground(menuOriginal);
				options.setBackground(menuOriginal);
				about.setVisible(false);
				preferences.setVisible(false);
				isMouseClicked = false;
			}
		});
		
		num1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				help.setBackground(menuOriginal);
				options.setBackground(menuOriginal);
				about.setVisible(false);
				preferences.setVisible(false);
				isMouseClicked = false;
			}
		});
		
		num2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				help.setBackground(menuOriginal);
				options.setBackground(menuOriginal);
				about.setVisible(false);
				preferences.setVisible(false);
				isMouseClicked = false;
			}
		});
		options.setOpaque(true);
		
		options.setHorizontalAlignment(SwingConstants.CENTER);
		options.setBounds(0,0,55,20);
		frame.getContentPane().add(options);
		options.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if(isMouseClicked) {
					
					options.setBackground(menuPressedColor);
					preferences.setVisible(true);
					help.setBackground(menuOriginal);
					about.setVisible(false);
				}
				else {
					options.setBackground(menuHoverColor);
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				options.setBackground(menuOriginal);
			}
		
			@Override
			public void mouseClicked(MouseEvent e) {
				options.setBackground(menuPressedColor);
				preferences.setVisible(true);
				
				
				isMouseClicked = true;
			}
		});
		
			
			
		preferences.setOpaque(true);	
		preferences.setBounds(0,20,90,30);	
		preferences.setHorizontalAlignment(SwingConstants.CENTER);
		preferences.setBackground(menuOriginal);
		preferences.setVisible(false);
	    frame.getContentPane().add(preferences);
	    preferences.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
					
					options.setBackground(menuPressedColor);
				
				
					
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				options.setBackground(menuOriginal);
			}
		
			
		
			
	    });
		
		prefText.setBounds(0, 4, 90, 22);
		prefText.setOpaque(true);
	
		prefText.setHorizontalAlignment(SwingConstants.CENTER);
		preferences.add(prefText);
		prefText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				prefText.setBackground(menuSelect);
				options.setBackground(menuPressedColor);
				
					
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				prefText.setBackground(null);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Preferences pf = new Preferences();
				new SetArrayList(true, true);
				options.setBackground(menuOriginal);
				preferences.setVisible(false);
				isMouseClicked = false;
			}
		
			
		
			
	    });
		
		
		
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.setBounds(55,0,37,20);
		help.setOpaque(true);	
		frame.getContentPane().add(help);
		help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if(isMouseClicked) {
					
					help.setBackground(menuPressedColor);
					about.setVisible(true);
					options.setBackground(menuOriginal);
					preferences.setVisible(false);
					
				}
				else {
					help.setBackground(menuHoverColor);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				help.setBackground(menuOriginal);
			}
		
			@Override
			public void mouseClicked(MouseEvent e) {
				help.setBackground(menuPressedColor);
				about.setVisible(true);
				isMouseClicked = true;
			}
		
		
		
		
		
		
		});
		
		about.setOpaque(true);	
		about.setBounds(50,20,60,30);	
		about.setHorizontalAlignment(SwingConstants.CENTER);
		about.setBackground(menuOriginal);
		about.setVisible(false);
	    frame.getContentPane().add(about);
	    about.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				help.setBackground(menuPressedColor);
					isMouseClicked = true;
				}
			});
		
	    aboutText.setBounds(0, 4, 60, 22);
	    aboutText.setOpaque(true);
	    
	    aboutText.setHorizontalAlignment(SwingConstants.CENTER);
	    
		about.add(aboutText);
		aboutText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
					
				aboutText.setBackground(menuSelect);
				
				
					
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				aboutText.setBackground(null);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				About aboutWindow = new About();
				help.setBackground(menuOriginal);
				about.setVisible(false);
				isMouseClicked = false;
			}
		
			
		
			
	    });
		
		num2.setFocusTraversalKeysEnabled(false);
		frame.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        num2.requestFocusInWindow();
		    }
		});
		
		
		
		
		
		
		
		
		

		
		
		
		
		num2.setFont(new Font("Calibri Light", Font.PLAIN, 42));
		num2.setBorder(null);
		num2.setHorizontalAlignment(SwingConstants.TRAILING);
		num2.setBounds(28, 83, 381, 60);
		frame.getContentPane().add(num2);
		num2.setColumns(10);
		num2.setEditable(false);
		Color bgColor = UIManager.getColor("TextField.background");
        num2.setBackground(bgColor);
       
		
       
		
		num1.setFont(new Font("Consolas", Font.PLAIN, 23));
		num1.setBorder(null);
		num1.setHorizontalAlignment(SwingConstants.TRAILING);
		num1.setBounds(30, 35, 377, 33);
		frame.getContentPane().add(num1);
		num1.setColumns(10);
		num1.setEditable(false);
		num1.setBackground(bgColor);
		num1.setForeground(new Color(83, 90, 94));
		
		
		
		
		txtM = new JTextField();
		txtM.setHorizontalAlignment(SwingConstants.CENTER);
		txtM.setBorder(null);
		txtM.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtM.setBounds(7, 104, 25, 25);
		frame.getContentPane().add(txtM);
		txtM.setColumns(10);
		txtM.setEditable(false);
		txtM.setBackground(bgColor);
		
		arrowDisplay = new JTextField();
		
		arrowDisplay.setBorder(null);
		arrowDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		arrowDisplay.setFont(new Font("Calibri", Font.PLAIN, 20));
		arrowDisplay.setBounds(5, 41, 21, 20);
		frame.getContentPane().add(arrowDisplay);
		arrowDisplay.setColumns(10);
		arrowDisplay.setEditable(false);
		arrowDisplay.setBackground(bgColor);
		
		JButton btnEq = new JButton("=");
		btnEq.setFocusPainted(false);
		btnEq.setFocusTraversalKeysEnabled(false);
		btnEq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				equalControl();
			}
		});
		btnEq.setFont(new Font("Calibri", Font.PLAIN, 35));
		btnEq.setBounds(332, 468, 68, 125);
		 
		frame.getContentPane().add(btnEq);
		
		
		JButton btnOne = new JButton("1");
		btnOne.setFocusPainted(false);
		btnOne.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				numberControl(1);
				
			}
		});
		btnOne.setBounds(12, 468, 68, 60);
		frame.getContentPane().add(btnOne);
		
		JButton btnTwo = new JButton("2");
		btnTwo.setFocusPainted(false);
		btnTwo.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
              
				numberControl(2);
			}
		});
		btnTwo.setBounds(85, 468, 68, 60);
		frame.getContentPane().add(btnTwo);
		
		JButton btnThree = new JButton("3");
		btnThree.setFocusPainted(false);
		btnThree.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnThree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				numberControl(3);
			}
		});
		btnThree.setBounds(158, 468, 68, 60);
		frame.getContentPane().add(btnThree);
		
		JButton btnFour = new JButton("4");
		btnFour.setFocusPainted(false);
		btnFour.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnFour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				numberControl(4);
				
			}
		});
		btnFour.setBounds(12, 403, 68, 60);
		frame.getContentPane().add(btnFour);
		
		JButton btnFive = new JButton("5");
		btnFive.setFocusPainted(false);
		btnFive.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnFive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				numberControl(5);
			}
		});
		btnFive.setBounds(85, 403, 68, 60);
		frame.getContentPane().add(btnFive);
		
		JButton btnSix = new JButton("6");
		btnSix.setFocusPainted(false);
		btnSix.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnSix.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				numberControl(6);
			}
		});
		btnSix.setBounds(158, 403, 68, 60);
		frame.getContentPane().add(btnSix);
		
		JButton btnSeven = new JButton("7");
		btnSeven.setFocusPainted(false);
		btnSeven.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnSeven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				numberControl(7);
			}
		});
		btnSeven.setBounds(12, 338, 68, 60);
		frame.getContentPane().add(btnSeven);
		
		JButton btnEight = new JButton("8");
		btnEight.setFocusPainted(false);
		btnEight.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnEight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				numberControl(8);
			}
		});
		btnEight.setBounds(85, 338, 68, 60);
		frame.getContentPane().add(btnEight);
		
		JButton btnNine = new JButton("9");
		btnNine.setFocusPainted(false);
		btnNine.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnNine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				numberControl(9);
			}
		});
		btnNine.setBounds(158, 338, 68, 60);
		frame.getContentPane().add(btnNine);
		
		JButton btnZero = new JButton("0");
		btnZero.setFocusPainted(false);
		btnZero.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnZero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			
			zeroControl();
			}
		});
		btnZero.setBounds(85, 533, 68, 60);
		frame.getContentPane().add(btnZero);
		
		JButton btnDiv = new JButton("/");
		btnDiv.setFocusPainted(false);
		btnDiv.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				operatorControl("/ ", " / ");
			}
		});
		btnDiv.setBounds(259, 338, 68, 60);
		frame.getContentPane().add(btnDiv);
		
		JButton btnMulti = new JButton("*");
		btnMulti.setFocusPainted(false);
		btnMulti.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				operatorControl("* ", " * ");
				
			}
		});
		btnMulti.setBounds(259, 403, 68, 60);
		frame.getContentPane().add(btnMulti);
		
		
		
		JButton btnSub = new JButton("-");
		btnSub.setFocusPainted(false);
		btnSub.setFont(new Font("Calibri", Font.PLAIN, 35));
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				operatorControl("- "," - ");
			}
		});
		
		
		btnSub.setBounds(259, 468, 68, 60);
		frame.getContentPane().add(btnSub);
		
		JButton btnAdd = new JButton("+");
		btnAdd.setFocusPainted(false);
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				operatorControl("+ "," + ");
				
			}
		});
		
		btnAdd.setBounds(259, 533, 68, 60);
		frame.getContentPane().add(btnAdd);
		
		JButton btnPow = new JButton("<html><i><font face=Cambria Math><font size=5>x </font><font size=4><sup>y</sup></font></font></i></html>");
		btnPow.setFocusPainted(false);
		btnPow.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnPow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				powerControl();
			}
		});
		btnPow.setBounds(92, 209, 68, 33);
		frame.getContentPane().add(btnPow);
		
		
		
		JButton btnC = new JButton("C");
		btnC.setFocusPainted(false);
		btnC.setFont(new Font("Calibri", Font.PLAIN, 19));
		btnC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cControl();
			}
		});
		btnC.setBounds(172, 272, 68, 52);
		frame.getContentPane().add(btnC);
		
		JButton btnMc = new JButton("MC");
		btnMc.setFocusPainted(false);
		btnMc.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnMc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mClearControl();
			}
		});
		btnMc.setBounds(12, 163, 68, 33);
		frame.getContentPane().add(btnMc);
		
		JButton btnMr = new JButton("MR");
		btnMr.setFocusPainted(false);
		btnMr.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnMr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				mRecallControl();
			}
		});
		btnMr.setBounds(92, 163, 68, 33);
		frame.getContentPane().add(btnMr);
		
		JButton btnMs = new JButton("MS");
		btnMs.setFocusPainted(false);
		btnMs.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnMs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				mSaveControl();
			}
		});
		btnMs.setBounds(172, 163, 68, 33);
		frame.getContentPane().add(btnMs);
		
		JButton btnMPlus = new JButton("M+");
		btnMPlus.setFocusPainted(false);
		btnMPlus.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnMPlus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				mPlusControl();
			}
		});
		btnMPlus.setBounds(252, 163, 68, 33);
		frame.getContentPane().add(btnMPlus);
		
		JButton btnMMinus = new JButton("M-");
		btnMMinus.setFocusPainted(false);
		btnMMinus.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnMMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				mMinusControl();
			}
		});
		btnMMinus.setBounds(332, 163, 68, 33);
		frame.getContentPane().add(btnMMinus);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setEditable(false);
		editorPane_1.setBounds(0, 20, 414, 123);
		frame.getContentPane().add(editorPane_1);
		editorPane_1.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseEntered(MouseEvent e) {
				help.setBackground(menuOriginal);
				options.setBackground(menuOriginal);
				about.setVisible(false);
				preferences.setVisible(false);
				isMouseClicked = false;
			}
		
		
		
		
		
		
		});
		
		JButton btnPlusMinus = new JButton("+/-");
		btnPlusMinus.setFocusPainted(false);
		btnPlusMinus.setFont(new Font("Calibri", Font.PLAIN, 21));
		btnPlusMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				plusMinusControl();
			}
		});
		btnPlusMinus.setBounds(12, 533, 68, 60);
		frame.getContentPane().add(btnPlusMinus);
		
		JButton btnBackspace = new JButton("");
		btnBackspace.setFocusPainted(false);
		frame.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        btnBackspace.requestFocusInWindow();
		    }
		});
		btnBackspace.setFont(new Font("Calibri", Font.PLAIN, 8));
		ImageIcon cHover = new ImageIcon(getClass(). getResource("/images/backspace2.png"));
		btnBackspace.setIcon(cHover);
		btnBackspace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				backSpaceControl();
			}
		});
		btnBackspace.setBounds(12, 272, 68, 52);
		frame.getContentPane().add(btnBackspace);
		
		JButton btnCe = new JButton("CE");
		btnCe.setFocusPainted(false);
		frame.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        btnCe.requestFocusInWindow();
		    }
		});
		btnCe.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ceControl();
			}
			
		});
		btnCe.setBounds(92, 272, 68, 52);
		frame.getContentPane().add(btnCe);
		
		JButton btnParOpen = new JButton("(");
		btnParOpen.setFocusPainted(false);
		btnParOpen.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnParOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				openParenthesisControl();
			}
		});
		btnParOpen.setBounds(252, 272, 68, 52);
		frame.getContentPane().add(btnParOpen);
		
		JButton btnParClose = new JButton(")");
		btnParClose.setFocusPainted(false);
		btnParClose.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnParClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeParenthesisControl();
			}
		});
		btnParClose.setBounds(332, 272, 68, 52);
		frame.getContentPane().add(btnParClose);
		
		JButton btnRec = new JButton("<html>1/<i><font face=Cambria Math>x</font></i></html>");
		btnRec.setFocusPainted(false);
		btnRec.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnRec.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlRec();
			}
		});
		btnRec.setBounds(332, 338, 68, 60);
		frame.getContentPane().add(btnRec);
		
		JButton btnSqrt = new JButton("\u221A");
		btnSqrt.setFocusPainted(false);
		btnSqrt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSqrt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlSqrt();
			}
		});
		btnSqrt.setBounds(332, 403, 68, 60);
		frame.getContentPane().add(btnSqrt);
		
		JButton btnDot = new JButton(".");
		btnDot.setFocusPainted(false);
		btnDot.setFont(new Font("Calibri", Font.PLAIN, 30));
		btnDot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dotControl(".");
			}
		});
		btnDot.setBounds(158, 533, 68, 60);
		frame.getContentPane().add(btnDot);
		
		JButton btnPowTwo = new JButton("<html><i><font face=Cambria Math><font size=5>x </font><font size=4><sup>2</sup></font></font></i></html>");
		btnPowTwo.setFocusPainted(false);
		btnPowTwo.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnPowTwo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlPowTwo();
			}
		});
		btnPowTwo.setBounds(12, 209, 68, 33);
		frame.getContentPane().add(btnPowTwo);
		
		
		
		JButton btnPi = new JButton("\u03C0");
		btnPi.setFocusPainted(false);
		btnPi.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnPi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlPi();
			}
		});
		btnPi.setBounds(172, 209, 68, 33);
		frame.getContentPane().add(btnPi);
		
		JButton btnSin = new JButton("sin");
		btnSin.setFocusPainted(false);
		btnSin.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnSin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlSin();
			}
		});
		btnSin.setBounds(252, 209, 68, 33);
		frame.getContentPane().add(btnSin);
		
		JButton btnCos = new JButton("cos");
		btnCos.setFocusPainted(false);
		btnCos.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnCos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlCos();
			}
		});
		btnCos.setBounds(332, 209, 68, 33);
		frame.getContentPane().add(btnCos);
		
		
		////////// RADIO BUTTON DEGREES
		radioBtnDeg.setFocusPainted(false);
		radioBtnDeg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		radioBtnDeg.setBounds(250, 246, 68, 21);
		radioBtnDeg.setSelected(true);
		
		
		
		
		////////////// RADIO BUTTON RADIANS
		
		radioBtnRad.setFocusPainted(false);
		radioBtnRad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		radioBtnRad.setBounds(330, 246, 68, 21);
		
		bg.add(radioBtnDeg);
		bg.add(radioBtnRad);
		
		frame.getContentPane().add(radioBtnDeg);
		frame.getContentPane().add(radioBtnRad);
		
		JLabel lblNewLabel = new JLabel("VOLARE AUTOMATION");
		lblNewLabel.setFont(new Font("Calibri", Font.ITALIC, 12));
		lblNewLabel.setBounds(19, 251, 138, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblF = new JLabel("F5");
		lblF.setForeground(SystemColor.windowBorder);
		lblF.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblF.setHorizontalAlignment(SwingConstants.CENTER);
		lblF.setBounds(343, 146, 46, 14);
		frame.getContentPane().add(lblF);
		
		JLabel label = new JLabel("F4");
		label.setForeground(SystemColor.windowBorder);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label.setBounds(263, 146, 46, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("F3");
		label_1.setForeground(SystemColor.windowBorder);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label_1.setBounds(183, 146, 46, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("F2");
		label_2.setForeground(SystemColor.windowBorder);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label_2.setBounds(103, 146, 46, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("F1");
		label_3.setForeground(SystemColor.windowBorder);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label_3.setBounds(23, 146, 46, 14);
		frame.getContentPane().add(label_3);
		
	}
}
