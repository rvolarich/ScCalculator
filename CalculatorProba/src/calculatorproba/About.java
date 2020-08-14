package calculatorproba;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class About {
	
	JLabel tf;

	About(){
		
		init();
	}
	
	private void init() {
		
		JFrame aboutFrame = new JFrame("About");
		aboutFrame.setLayout(null);
		aboutFrame.setSize(new Dimension(300,200));
		aboutFrame.setLocationRelativeTo(null);
		aboutFrame.setVisible(true);
		aboutFrame.setResizable(false);
		
		tf = new JLabel("<html>This calculator is my first Windows and Java application. <br/><br/>I dedicate it to my wife"
				+ " Ivana and daughters Angela and Mila.<br/><br/>Robert Volaric<br/>Croatia, November 2018</html>");
		tf.setVerticalAlignment(SwingConstants.TOP);
		tf.setBounds(22,15,250,150);
		
		aboutFrame.add(tf);
	}
}
