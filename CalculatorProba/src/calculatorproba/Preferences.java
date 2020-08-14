package calculatorproba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

import javax.swing.table.AbstractTableModel;



public class Preferences extends JFrame{

	private static final long serialVersionUID = -187018504794021660L;
	String x2;
	String xy;
	String pi;
	String sin;
	String cos;
	String leftPar;
	String rightPar;
	String rec;
	String sqrt;
	String [] combBoxItems = {"x^2", "x^y", "Pi", "Sin", "Cos","Left Parenthesis","Right Parenthesis","1/x","Sqrt" };
	public JComboBox combBox = new JComboBox<>(combBoxItems);
	int keyNum;
	String combBoxString;
	int functionPosition;
	ArrayList<String> arr = new ArrayList<String>(1);
	JTable table;
	boolean setKey = true;
	boolean allowApply = false;
	boolean allowChick;
	boolean allowChangeEvent = false;
	boolean allowBtnSoundChangeListener = false;
	boolean allowRadioBtnChangeListener = false;
	boolean allowApplySounds;
	boolean allowUnbindAll;
	int mouseX;
	int yVal;
	int sliderValue;
	ImageIcon valueLabelIcon = new ImageIcon(getClass().getResource("/images/valuePot48x32.png"));
	ImageIcon muteLabel = new ImageIcon(getClass().getResource("/images/mute70x32.png"));
	JSlider sliderKeys = new JSlider(JSlider.HORIZONTAL);
	JSlider sliderAlert = new JSlider(JSlider.HORIZONTAL);
	JLabel infoLabel = new JLabel("Info Bar...");
	JButton btnApply = new JButton("Apply");
	JFrame prefFrame = new JFrame("Preferences");
	JFrame noteFrame = new JFrame();
	Preferences(){
		
		SetArrayList sal = new SetArrayList(true);
		if(sal.getArrayListKeys().isEmpty()) {
			
			allowUnbindAll = false;
		}
		else {
			
			allowUnbindAll = true;
		}
		setFunctionVariable();
		init();
		
	}
	
	
	
	private void setMenuString() {
		
		combBoxString = (String) combBox.getSelectedItem();
	}
	
	
	public boolean getChickAllowed(){
		
		return allowChick;
	}
	
	private void getFunctionPosition(String str) {
		
		
		switch(str) {
			
		case "x^2": functionPosition = 0;
					break;
		case "x^y": functionPosition = 1;
					break;
		case "Pi": functionPosition = 2;
					break;
		case "Sin": functionPosition = 3;
					break;
		case "Cos": functionPosition = 4;
					break;
		case "Left Parenthesis": functionPosition = 5;
					break;
		case "Right Parenthesis": functionPosition = 6;
					break;
		case "1/x": functionPosition = 7;
					break;
		case "Sqrt": functionPosition = 8;
					break;
					default: return;
		}
	}
	
	
private void setFunctionVariable() {
	
	x2 = xy = pi = sin = cos = leftPar = rightPar = rec = sqrt = null;
	SetArrayList sal = new SetArrayList(true);
	ArrayList<String> arrActions = sal.getArrayListActions();
	ArrayList<Integer> arrKeys = sal.getArrayListKeys();
	char c;
	int a;
	
	for(int i = 0; i < arrActions.size(); i++) {
		
		String str = arrActions.get(i);
		switch(str) {
			
		case "x^2": a = arrKeys.get(i);
					c = (char) a;
					x2 = String.valueOf(c);
					break;
		case "x^y": a = arrKeys.get(i);
					c = (char) a;
					xy = String.valueOf(c);
					break;			
		case "Pi": 	a = arrKeys.get(i);
					c = (char) a;
					pi = String.valueOf(c);
					break;			
		case "Sin": a = arrKeys.get(i);
					c = (char) a;
					sin = String.valueOf(c);
					break;			
		case "Cos": a = arrKeys.get(i);
					c = (char) a;
					cos = String.valueOf(c);
					break;			
		case "Left Parenthesis":a = arrKeys.get(i);
								c = (char) a;
								leftPar = String.valueOf(c);
								break;			
		case "Right Parenthesis": 	a = arrKeys.get(i);
									c = (char) a;
									rightPar = String.valueOf(c);
									break;			
		case "1/x":	 	a = arrKeys.get(i);
						c = (char) a;
						rec = String.valueOf(c);
						break;			
		case "Sqrt": 	a = arrKeys.get(i);
						c = (char) a;
						sqrt = String.valueOf(c);
						break;			
		default: return;			
		}
	}
	}
	
	public ArrayList<String> getArrayList(){
		return arr;
	}
	
	private void applyChanges() {
		infoLabel.setForeground(new Color(70, 70, 70));
		if(allowApply) {
		
		new SetArrayList(false, false);
		ArrayList<String> actions = new ArrayList<String>();
		ArrayList<Integer> keys = new ArrayList<Integer>();;
		SetArrayList sal = new SetArrayList(true);
		actions = sal.getArrayListActions();
		keys = sal.getArrayListKeys();

		 
		CalculatorProba.imm = CalculatorProba.textFieldKeyBind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		CalculatorProba.imm.clear();
						for(int i = 0; i < keys.size(); i++) {
							
							CalculatorProba.imm.put(KeyStroke.getKeyStroke(keys.get(i), 0), actions.get(i));
						}

						

		if(allowApplySounds) {
		int [] intArr = new int [3];
		if(getChickAllowed()) {
			
			intArr[0] = 1;
		}
		else intArr [0] = 0;
		intArr [1] = sliderKeys.getValue();
		intArr [2] = sliderAlert.getValue();
		Serialization serialize = new Serialization("soundVolumeSettings.ser", intArr);
		}
		btnApply.setForeground(new Color(140, 140, 140));
		infoLabel.setText("All changes successfully applied!");
		allowApply = false;
		}
		else {
			infoLabel.setText("No changes has been made!");
		}
		
		
	}
private boolean getAllowApply() {
	
	return allowApply;
}
	
private void initApplyNotification(){
		
		
		noteFrame.setSize(new Dimension(375, 175));
		noteFrame.setLayout(null);
		noteFrame.setLocationRelativeTo(null);
		noteFrame.setVisible(true);
		
		JLabel labelNote = new JLabel("<html>In order for changes to take "
				+ "effect you must click Apply.<br>What would you like to do ?</html>");
		labelNote.setBounds(20,10,330,50);
		
		JButton btnCancel = new JButton("Cancel");
		JButton btnNoApply = new JButton("Don't apply");
		JButton btnApply = new JButton("Apply");
		
		btnCancel.setBounds(20, 80, 100, 30);
		btnCancel.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				noteFrame.dispose();
			}
		});
		btnNoApply.setBounds(130, 80, 100, 30);
		btnNoApply.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				prefFrame.dispose();
				noteFrame.dispose();
				
			}
		});
		btnApply.setBounds(240, 80, 100, 30);
		btnApply.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				applyChanges();
				prefFrame.dispose();
				noteFrame.dispose();
				}
		});
		noteFrame.add(labelNote);
		noteFrame.add(btnCancel);
		noteFrame.add(btnNoApply);
		noteFrame.add(btnApply);
	}
	
	
	private void init() {
		
		prefFrame.setResizable(false);
		prefFrame.setSize(new Dimension(800,700));
		prefFrame.setLayout(null);
		prefFrame.setLocationRelativeTo(null);
		prefFrame.setVisible(true);
		prefFrame.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				
				if(getAllowApply()) {
				prefFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				noteFrame.requestFocus();
				initApplyNotification();
				}
				else prefFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		JPanel panelKeys = new JPanel();
		panelKeys.setBounds(200,45,600,500);
		panelKeys.setLayout(null);
		JPanel panelSound = new JPanel();
		panelSound.setBounds(200,45,600,500);
		panelSound.setLayout(null);
		panelSound.setVisible(false);
		
		JPanel side = new JPanel();
		JPanel sidePanel = new JPanel();
		JPanel horLine = new JPanel();
		JPanel horLineBottom = new JPanel();
		JPanel menuPanel1 = new JPanel();
		JPanel menuPanel2 = new JPanel();
		JPanel scroll = new JPanel();
		JPanel panelInfo = new JPanel();
		JPanel panelPotValueKeys = new JPanel();
		JPanel panelPotValueAlert = new JPanel();
		JLabel labelInfo = new JLabel();
		JLabel menuLabel1 = new JLabel("Keys");
		JLabel menuLabel2 = new JLabel("Sound");
		
		JLabel chooseCat = new JLabel("Choose category:");
		JLabel arrowKey = new JLabel(">>");
		JLabel arrowSound = new JLabel();
		JLabel headerLabel = new JLabel("<html><b>Keys</b> - bind keys to functions</html>");
		JLabel caution = new JLabel();
		JButton btnBind = new JButton("Bind");
		JButton btnUnbind = new JButton("Unbind");
		Color colorBtnFor = btnUnbind.getForeground();
		
		JButton btnUnbindAll = new JButton("Unbind All");
		JButton btnClose = new JButton("Close");
		
		JButton btnClear = new JButton("Clear");
		JTextField attachText = new JTextField();
		Font dialogPlain13 = new Font("Dialog", Font.PLAIN, 13);
		Font dialogPlain12 = new Font("Dialog", Font.PLAIN, 12);
		JRadioButton radioBtnChik = new JRadioButton("Chick");
		JRadioButton radioBtnBlup = new JRadioButton("Blup");
		ButtonGroup bg = new ButtonGroup();
		
		JLabel mute1 = new JLabel("Mute");
		JLabel mute2 = new JLabel("Mute");
		JLabel hundred1 = new JLabel("100");
		JLabel hundred2 = new JLabel("100");
		JLabel chooseSound = new JLabel("Choose sound for keys:");
		JLabel volumeAdjustKeys = new JLabel("Key sound volume adjustment pot:");
		JLabel volumeAdjustAlert = new JLabel("Notification sound volume adjustment pot:");
		JLabel potValueKeysLabelIcon = new JLabel();
		JLabel potValueKeysLabelText = new JLabel();
		JLabel labelUserDefined = new JLabel("Black: user defined");
		JLabel labelSystemDefined = new JLabel("Gray: system defined");
		JLabel labelFunction = new JLabel("Function:");
		JLabel labelKeyField = new JLabel("Key Field (a - z, A - Z)");
		
		
		
		mute1.setBounds(90,300,50,25);
		
		mute2.setBounds(90,445,50,25);
		
		hundred1.setBounds(399,300,50,25);
		
		hundred2.setBounds(399,445,50,25);
		
		labelUserDefined.setBounds(65, 21, 150, 25);
		labelUserDefined.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 11));
		
		labelSystemDefined.setBounds(185, 21, 150, 25);
		labelSystemDefined.setFont(new Font("Dialog", Font.ITALIC, 11));
		labelSystemDefined.setForeground(new Color(140, 140, 140));
		
		labelFunction.setBounds(62, 346, 100, 25);
		labelFunction.setFont(dialogPlain12);
		labelFunction.setForeground(new Color(140, 140, 140));
		
		labelKeyField.setBounds(384, 346, 200, 25);
		labelKeyField.setFont(dialogPlain12);
		labelKeyField.setForeground(new Color(140, 140, 140));
		
		chooseSound.setBounds(50, 50, 200, 25);
		chooseSound.setFont(dialogPlain13);
		
		volumeAdjustKeys.setBounds(50, 200, 300, 25);
		volumeAdjustKeys.setFont(dialogPlain13);
		
		volumeAdjustAlert.setBounds(50, 345, 300, 25);
		volumeAdjustAlert.setFont(dialogPlain13);
		
		attachText.setDocument(new CharLimit(1));
		attachText.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				infoLabel.setForeground(new Color(70, 70, 70));
				char c = e.getKeyChar();
				if(!Character.isLetter(c)) {
					infoLabel.setText("Enter letter (a - z, A -Z) in the Key Field.");
					try {
					attachText.setText("" + attachText.getText().substring(0, -1));
					}catch(Exception ex) {
						
					}
				}
				else {
					
					attachText.setText(null);
					infoLabel.setText("Info Bar...");
					infoLabel.setForeground(new Color(130, 130, 130));
				}
			}
		});
		attachText.setBounds(384, 370, 100, 30);
		attachText.setHorizontalAlignment(JTextField.CENTER);
		attachText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				infoLabel.setText("Info Bar...");
				infoLabel.setForeground(new Color(130, 130, 130));
				}
		});
		
		prefFrame.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        attachText.requestFocusInWindow();
		    }
		});
		table = new JTable(new MyTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(450, 222));
		
		
		
		table.setBounds(0, 150, 500, 270);
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				
				infoLabel.setForeground(new Color(70, 70, 70));
				if(table.getSelectedRow() < 9) {
					
					combBox.setSelectedIndex(table.getSelectedRow());
					String str = (String) table.getValueAt(table.getSelectedRow(), 1);
					attachText.setText(str);
					
				}
				else {
					infoLabel.setText("This field is system defined.");
				}
			}
		});
		
		JScrollPane scrollB = new JScrollPane(table);
		scrollB.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2, false));
		chooseCat.setBounds(30, 22, 100, 25);
		chooseCat.setForeground(Color.WHITE);
		
		headerLabel.setBounds(220, 22, 550, 20);
		headerLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		headerLabel.setForeground(Color.DARK_GRAY);
		arrowKey.setBounds(128, 0, 20, 30 );
		arrowSound.setBounds(128, 0, 20, 30 );
		
		side.setBounds(0, 0, 200, 700);
		side.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		side.setLayout(null);
		side.setBackground(new Color(170, 170, 170));
		
		
		sidePanel.setBackground(Color.WHITE);
		sidePanel.setBounds(25, 50, 150, 500);
		sidePanel.setLayout(null);
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, false));
		
		scroll.setBackground(prefFrame.getBackground());
		scroll.setBounds(48, 40, 500, 260);
		
		
		
		
		Color menuPanelSelected = new Color(225, 225, 225);
		
		//////   KEYS MENU
		menuPanel1.setBounds(1, 15, 148, 30);
		menuPanel1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				panelSound.setVisible(false);
				panelKeys.setVisible(true);
				menuPanel2.setBackground(Color.WHITE);
				arrowSound.setText("");
				menuPanel1.setBackground(menuPanelSelected);
				arrowKey.setText(">>");
				headerLabel.setText("<html><b>Keys</b> - bind keys to functions</html>");
				allowChangeEvent = false;
				allowApplySounds = false;
				
			}
		});
		menuPanel1.setBackground(new Color(225, 225, 225));
		menuPanel1.setLayout(null);
		menuLabel1.setBounds(11, 0, 148, 30);
		
		
		///////////// SOUND MENU
		menuPanel2.setBounds(1, 45, 148, 30);
		menuPanel2.setBackground(Color.WHITE);
		menuPanel2.setLayout(null);
		menuPanel2.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				panelKeys.setVisible(false);
				panelSound.setVisible(true);
				menuPanel2.setBackground(menuPanelSelected);
				arrowSound.setText(">>");
				menuPanel1.setBackground(Color.WHITE);
				arrowKey.setText("");
				headerLabel.setText("<html><b>Sound</b> - set your prefered sounds</html>");
				Serialization serialize = new Serialization("soundVolumeSettings.ser");
				int [] savArray = serialize.getSoundVolumeSettings();
				if (savArray[0] == 1) {
					
					radioBtnChik.setSelected(true);
					allowChick = true;
				}
				else {
					
					radioBtnBlup.setSelected(true);
				}
				
				sliderKeys.setValue(savArray[1]);
				sliderAlert.setValue(savArray[2]);
				allowChangeEvent = false;
				allowApplySounds = true;
				
			}
		});
		menuLabel2.setBounds(10, 0, 148, 30);
		
		horLine.setBounds(200, 50, 600, 2);
		horLine.setBackground(Color.GRAY);
		
		horLineBottom.setBounds(200, 630, 600, 1);
		horLineBottom.setBackground(Color.GRAY);
		
		
		panelInfo.setBounds(61, 302, 471,45);
		panelInfo.setBackground(prefFrame.getBackground());
		panelInfo.setLayout(null);
		
		
		labelInfo.setBounds(0, 0, 471, 45);
		labelInfo.setLayout(null);
		ImageIcon imgInfo = new ImageIcon(getClass(). getResource("/images/infoBarBorderBlue.png"));
		labelInfo.setIcon(imgInfo);
		
		infoLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		infoLabel.setForeground(new Color(130, 130, 130));
		infoLabel.setBounds(20,7, 400, 30);
		infoLabel.setVisible(true);
		
		
		
		sidePanel.add(menuPanel1);
		sidePanel.add(menuPanel2);
		menuPanel1.add(menuLabel1);
		menuPanel2.add(menuLabel2);
		
		combBox.setBounds(62, 370, 150, 30);
		combBox.setBackground(Color.WHITE);
		combBox.addActionListener(new ActionListener() {
			
			public void actionPerformed( ActionEvent e) {
				
				infoLabel.setText("Info Bar...");
				infoLabel.setForeground(new Color(130, 130, 130));
			}
		});
		btnBind.setBounds(248, 370, 100, 30);
		btnBind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				infoLabel.setForeground(new Color(70, 70, 70));
				if(attachText.getText().equals("")) {
					
					infoLabel.setText("Enter letter (a - z, A -Z) in the Key Field.");
				}
				else{
					
				keyNum = attachText.getText().toUpperCase().charAt(0);
				char c = attachText.getText().toUpperCase().charAt(0);
				String str = Character.toString(c);
				setMenuString();
				
				SetArrayList sal = new SetArrayList(combBoxString, keyNum, true, false);
				if(sal.getContainsFunction() && sal.getContainsKey()
						&& sal.getIndexKeyInArray() == sal.getIndexFunctionInArray()) {
					
					infoLabel.setText("<html>Function " + "<b>" + combBoxString + "</b>" +
							" is already binded to " + "<b>" + sal.getCharKey() + "</b>" + ".</html>");
					getFunctionPosition(combBoxString);
					table.setRowSelectionInterval(functionPosition, functionPosition);
				}
				else if(sal.getContainsFunction()) {
					infoLabel.setText("<html>Function "  + "<b>" + combBoxString + "</b>" +
				" is binded to the " + "<strong>" + sal.getCharKey() + "</strong>" + ". "
						+ "Unbind existing connection first.</html>");
					
				}
				else if(sal.getContainsKey()) {
					infoLabel.setText("<html>Key " + "<b>" + attachText.getText().toUpperCase().charAt(0) +
							"</b>" + " is binded to the " + "<b>" + sal.getStringFunction() + "</b>" + ". "
							+ "Unbind existing connection first.</html>");
					
				}
				else {
					getFunctionPosition(combBoxString);
					table.setValueAt(str, functionPosition, 1);
					infoLabel.setText("Bind successfull!");
					btnApply.setForeground(colorBtnFor);
					new SetArrayList(combBoxString, keyNum, 1, false);
					allowApply = true;
					allowUnbindAll = true;
				}
				
			}
			}
		});
		btnUnbind.setBounds(248, 417, 100, 30);
		btnUnbind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				infoLabel.setForeground(new Color(70, 70, 70));
				if(attachText.getText().equals("")) {
					
					infoLabel.setText("Enter letter (a - z, A -Z) in the Key Field.");
				}
				else{
					setMenuString();
				SetArrayList sal = new SetArrayList(combBoxString, keyNum, true, false);
				if(sal.getCharKey() == attachText.getText().toUpperCase().charAt(0)) {
					
					
					getFunctionPosition(combBoxString);
					table.setValueAt(null, functionPosition, 1);
					infoLabel.setText("Unbind successfull!");
					btnApply.setForeground(colorBtnFor);
					new SetArrayList(combBoxString, keyNum, 0, false);
					allowApply = true;
				}
				
				else {
						infoLabel.setText("Function and Key are not binded.");
				}
				
			}
			}
		});
		
		
		
		btnApply.setBounds(640, 592, 90, 30);
		btnApply.setForeground(new Color(140, 140, 140));
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					applyChanges();
				}
		});
		
		btnUnbindAll.setBounds(248, 464, 100, 30);
		btnUnbindAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				infoLabel.setForeground(new Color(70, 70, 70));
				if(!allowUnbindAll) {
					
					infoLabel.setText("There are no keys to unbind!");
				}
				else {
				new SetArrayList(1);
				for(int i = 0; i < 9; i++) {
					
					table.setValueAt(null, i, 1);
				}
				infoLabel.setText("All keys have been successfully unbinded!");
				btnApply.setForeground(colorBtnFor);
				allowApply = true;
				allowUnbindAll = false;
				}
				}
		});
		
		btnClose.setBounds(540, 592, 90, 30);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(allowApply) {
					
					initApplyNotification();
				}
				else prefFrame.dispose();
				
			}
		});
		
		
		btnClear.setBounds(384, 417, 100, 30);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				attachText.setText("");
				
			}
		});
		
		ImageIcon imgCaution = new ImageIcon(getClass(). getResource("/images/caution.png"));
		caution.setBounds(225,627, 500, 50);
		caution.setFont(new Font("Dialog", Font.PLAIN, 11));
		caution.setText("<html> In order for changes to take effect click <b>Apply</b> !</html>");
		caution.setIcon(imgCaution);
		
		ItemListener il = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					
					allowApply = true;
					btnApply.setForeground(colorBtnFor);
				}
			}
		};
		
		radioBtnChik.setFocusPainted(false);
		radioBtnChik.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioBtnChik.setBounds(100, 97, 68, 21);
		
		
		radioBtnChik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				allowChick = true;
				
				}
			
		});;
		
		
		
		////////////// RADIO BUTTON RADIANS
		
		radioBtnBlup.setFocusPainted(false);
		
		radioBtnBlup.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radioBtnBlup.setBounds(100, 137, 68, 21);
		
		radioBtnBlup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
					
				
				allowChick = false;
				
				}
		});
		
		radioBtnChik.addItemListener(il);
		radioBtnBlup.addItemListener(il);
		bg.add(radioBtnChik);
		bg.add(radioBtnBlup);
		
		panelPotValueKeys.setLayout(null);
		
		potValueKeysLabelText.setBounds(18, 5, 20, 20);
		
		
		potValueKeysLabelIcon.setBounds(0, 0, 70, 33);
		potValueKeysLabelIcon.setLayout(null);
		potValueKeysLabelIcon.add(potValueKeysLabelText);
		panelPotValueKeys.add(potValueKeysLabelIcon);
		
		ChangeListener listener = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				if(allowChangeEvent) {
				JSlider source = (JSlider) e.getSource();
				
				String s = String.valueOf(source.getValue());
				
				panelPotValueKeys.setBounds(source.getValue() * 3 + 108, yVal, 70, 33);
				if(source.getValue() == 0) {
					potValueKeysLabelIcon.setIcon(muteLabel);
					potValueKeysLabelText.setText("Mute");
					potValueKeysLabelText.setBounds(21, 3, 50, 25);
				}
				
				else if(source.getValue() == 100) {
					
					potValueKeysLabelIcon.setIcon(valueLabelIcon);
					potValueKeysLabelText.setText(s);
					potValueKeysLabelText.setBounds(14, 5, 25, 20);
					
				}
				
				else {
					
					potValueKeysLabelIcon.setIcon(valueLabelIcon);
					potValueKeysLabelText.setText(s);
					potValueKeysLabelText.setBounds(18, 5, 20, 20);
				}
				
				btnApply.setForeground(colorBtnFor);
				allowApply = true;
				
			}
			}
		};
		
		
		
		sliderKeys.setMajorTickSpacing(10);
		sliderKeys.setMinorTickSpacing(5);
		sliderKeys.setPaintTicks(true);
		sliderKeys.setBounds(100, 265, 315, 40);
		sliderKeys.addChangeListener(listener);
		sliderKeys.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent me) {
				
				allowChangeEvent = true;
				String s = String.valueOf(sliderKeys.getValue());
				if(sliderKeys.getValue() == 0) {
					potValueKeysLabelIcon.setIcon(muteLabel);
					potValueKeysLabelText.setText("Mute");
					potValueKeysLabelText.setBounds(21, 3, 50, 25);
				}
				
				else if(sliderKeys.getValue() == 100) {
					
					potValueKeysLabelIcon.setIcon(valueLabelIcon);
					potValueKeysLabelText.setText(s);
					potValueKeysLabelText.setBounds(14, 5, 25, 20);
					
				}
				
				else {
					
					potValueKeysLabelIcon.setIcon(valueLabelIcon);
					potValueKeysLabelText.setText(s);
					potValueKeysLabelText.setBounds(18, 5, 20, 20);
				}
				panelPotValueKeys.setVisible(true);
				yVal = 230;
				panelPotValueKeys.setBounds(sliderKeys.getValue() * 3 + 108, yVal, 70, 33);
				
				
			}
			
			public void mouseReleased(MouseEvent me) {
				
				String str;
				if(radioBtnChik.isSelected()) str = "chick.wav";
				else str = "blup.wav";
				new Sound(str, sliderKeys.getValue());
				panelPotValueKeys.setVisible(false);
				}
			
			public void mouseClicked(MouseEvent me) {
				
				allowChangeEvent = true;
				yVal = 230;
				panelPotValueKeys.setBounds(sliderKeys.getValue() * 3 + 108, yVal, 70, 33);
				
			}
		});
		
		
		sliderAlert.setMajorTickSpacing(10);
		sliderAlert.setMinorTickSpacing(5);
		sliderAlert.setPaintTicks(true);
		sliderAlert.setBounds(100, 410, 315, 40);
		sliderAlert.addChangeListener(listener);
		sliderAlert.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent me) {
				allowChangeEvent = true;
				String s = String.valueOf(sliderAlert.getValue());
				if(sliderAlert.getValue() == 0) {
					potValueKeysLabelIcon.setIcon(muteLabel);
					potValueKeysLabelText.setText("Mute");
					potValueKeysLabelText.setBounds(21, 3, 50, 25);
				}
				
				else if(sliderAlert.getValue() == 100) {
					
					potValueKeysLabelIcon.setIcon(valueLabelIcon);
					potValueKeysLabelText.setText(s);
					potValueKeysLabelText.setBounds(14, 5, 25, 20);
					
				}
				
				else {
					
					potValueKeysLabelIcon.setIcon(valueLabelIcon);
					potValueKeysLabelText.setText(s);
					potValueKeysLabelText.setBounds(18, 5, 20, 20);
				}
				panelPotValueKeys.setVisible(true);
				yVal = 230 + 145;
				panelPotValueKeys.setBounds(sliderAlert.getValue() * 3 + 108, yVal, 70, 33);
			}
			
			public void mouseReleased(MouseEvent me) {
				
				new Sound("alert.wav", sliderAlert.getValue());
				panelPotValueKeys.setVisible(false);
				
			}
			
			public void mouseClicked(MouseEvent me) {
				
				allowChangeEvent = true;
				yVal = 215 + 130;
				panelPotValueKeys.setBounds(sliderAlert.getValue() * 3 + 108, yVal, 70, 33);
				
			}
		});
		
		
		menuPanel1.add(arrowKey);
		menuPanel2.add(arrowSound);
		side.add(chooseCat);
		side.add(sidePanel);
		
		panelKeys.add(scroll);
		panelKeys.add(combBox);
		panelKeys.add(btnBind);
		panelKeys.add(btnUnbind);
		prefFrame.add(btnApply);
		prefFrame.add(btnClose);
		panelKeys.add(btnClear);
		panelKeys.add(attachText);
		panelKeys.add(btnUnbindAll);
		panelKeys.add(panelInfo);
		panelKeys.add(labelUserDefined);
		panelKeys.add(labelSystemDefined);
		panelKeys.add(labelFunction);
		panelKeys.add(labelKeyField);
		panelInfo.add(labelInfo);
		labelInfo.add(infoLabel);
		scroll.add(scrollB);
		prefFrame.add(side);
		prefFrame.add(headerLabel);
		prefFrame.add(horLine);
		prefFrame.add(horLineBottom);
		prefFrame.add(panelKeys);
		prefFrame.add(caution);
		prefFrame.add(panelSound);
		panelSound.add(radioBtnChik);
		panelSound.add(radioBtnBlup);
		panelSound.add(sliderKeys);
		panelSound.add(sliderAlert);
		panelSound.add(mute1);
		panelSound.add(mute2);
		panelSound.add(hundred1);
		panelSound.add(hundred2);
		panelSound.add(chooseSound);
		panelSound.add(volumeAdjustAlert);
		panelSound.add(volumeAdjustKeys);
		panelSound.add(panelPotValueKeys);
		
		
		
		
	}

class MyTableModel extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		

		private String [] columnNames = {"<html><b>Function</b></html>" , "<html><b>Key</b></html>"};
		
		private Object [][] data = {
				{"x^2", x2},
				{"x^y", xy},
				{"Pi", pi},
				{"Sin", sin},
				{"Cos", cos},
				{"Left Parenthesis", leftPar},
				{"Right Parenthesis", rightPar},
				{"1/x", rec},
				{"Sqrt", sqrt},
				{"<html><font color=gray>0 - 9</font></html>", "<html><font color=gray>Digit 0-9, NUMPAD 0-9</font></html>"},
				{"<html><font color=gray>Divide</font></html>", "<html><font color=gray>Shift + Digit 7, NUMPAD Slash</font></html>"},
				{"<html><font color=gray>Multiply</font></html>", "<html><font color=gray>NUMPAD *</font></html>"},
				{"<html><font color=gray>Subtract</font></html>", "<html><font color=gray>NUMPAD -</font></html>"},
				{"<html><font color=gray>Add</font></html>", "<html><font color=gray>NUMPAD +</font></html>"},
				{"<html><font color=gray>MC</font></html>", "<html><font color=gray>F1</font></html>"},
				{"<html><font color=gray>MR</font></html>", "<html><font color=gray>F2</font></html>"},
				{"<html><font color=gray>MS</font></html>", "<html><font color=gray>F3</font></html>"},
				{"<html><font color=gray>M+</font></html>", "<html><font color=gray>F4</font></html>"},
				{"<html><font color=gray>M-</font></html>", "<html><font color=gray>F5</font></html>"},
				{"<html><font color=gray>Backspace</font></html>", "<html><font color=gray>Backspace</font></html>"},
				{"<html><font color=gray>CE</font></html>", "<html><font color=gray>Delete</font></html>"},
				{"<html><font color=gray>C</font></html>", "<html><font color=gray>Esc</font></html>"},
				{"<html><font color=gray>Left Parenthesis</font></html>", "<html><font color=gray>SHIFT + 8</font></html>"},
				{"<html><font color=gray>Right Parenthesis</font></html>", "<html><font color=gray>SHIFT + 9</font></html>"},
				{"<html><font color=gray>+/-</font></html>", "<html><font color=gray>Minus</font></html>"},
				{"<html><font color=gray>Dot</font></html>", "<html><font color=gray>Dot, NUMPAD Coma</font></html>"},
				{"<html><font color=gray>Equals</font></html>", "<html><font color=gray>Enter</font></html>"}
				
				
		};
		
		
		
		@Override
		public int getColumnCount() {
			
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			
			return data.length;
		}
		
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
			
		}
		
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
		
		public String getColumnName(int col) {
		      return columnNames[col];
		    }

		public boolean isCellEditable(int row, int col) {
		     
		        return false;
		      } 
		
		
		
		
	}
}


