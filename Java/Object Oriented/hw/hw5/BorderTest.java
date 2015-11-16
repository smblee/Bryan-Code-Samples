package hw.hw5;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class BorderTest extends JFrame {	
	private JPanel mainPanel = new JPanel();
	private static final int NUMBEROFBORDERS = 4;
	
	
	public BorderTest() {
		mainPanel.setLayout(new GridLayout(NUMBEROFBORDERS, 0, 3, 3));
		getContentPane().add(mainPanel);
		setTitle("BorderTest");
		 Border1();
		 Border2();
		 Border3();
		 Border4();
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setVisible(true);
	}
	
	public void Border1() {
		Border b1 = BorderFactory.createLineBorder(Color.red,5);
		Border b2 = BorderFactory.createLineBorder(Color.green,5);
		Border compound = BorderFactory.createCompoundBorder(b1, b2);
		JPanel borderPanel = new JPanel();
		JTextField textField = new JTextField("your message here",40);
		textField.setBorder(compound);
		borderPanel.add(textField);
		mainPanel.add(borderPanel);
	}
	
	public void Border2() {
		Border b1 = BorderFactory.createLineBorder(Color.red,5);
		Border b2 = BorderFactory.createLineBorder(Color.green,5);
		Border b3 = BorderFactory.createLineBorder(Color.blue,5);
		Border compound = BorderFactory.createCompoundBorder(b1, b2);
		Border tripleCompound = BorderFactory.createCompoundBorder(compound, b3);
		
		JPanel borderPanel = new JPanel();
		JTextField textField = new JTextField("your message here",40);
		textField.setBorder(tripleCompound);
		borderPanel.add(textField);
		mainPanel.add(borderPanel);
	}
	
	public void Border3() {
		Border b2 = BorderFactory.createLineBorder(Color.green,5);
		Border b = BorderFactory.createTitledBorder(b2, "A Title");
		Border b1 = BorderFactory.createLineBorder(Color.red,5);
		JPanel borderPanel = new JPanel();
		JTextField textField = new JTextField("your message here",40);
		borderPanel.setBorder(b);
		textField.setBorder(b1);
		borderPanel.add(textField);
		mainPanel.add(borderPanel);
	}

	public void Border4() {
		Border b1 = BorderFactory.createLineBorder(Color.red,5);
		Border b2 = BorderFactory.createLineBorder(Color.green,5);
		Border b3 = BorderFactory.createCompoundBorder(b1, b2);
		Border doubleBorderTitle = BorderFactory.createTitledBorder(b3, "A Title");
		JPanel borderPanel = new JPanel();
		JTextField textField = new JTextField("your message here",40);
		textField.setBorder(doubleBorderTitle);
		borderPanel.add(textField);
		mainPanel.add(borderPanel);
	}

	
	
	public static void main(String[] args) {
		
		BorderTest bt = new BorderTest();
		bt.pack();
	    
	}
}