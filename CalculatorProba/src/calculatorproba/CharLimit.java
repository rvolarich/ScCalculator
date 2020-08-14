package calculatorproba;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;

public class CharLimit extends PlainDocument{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit;
	
	public CharLimit(int limitation) {
		
		this.limit = limitation;
	}
	
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
		
		
		if(getLength() + str.length() <= limit) {
			
			super.insertString(offset, str, set);
		}
	}

}
