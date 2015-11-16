package hw.hw6;

import java.awt.Color;
import javax.swing.*;

public class ComponentStyler {
	private Color c1, c2;

	public ComponentStyler(Color c1, Color c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	public void colorCoatJC(JComponent jc) {
		jc.setBorder(BorderFactory.createLineBorder(c1, 4));
		jc.setBackground(c2);
	}

	public JButton createJButton(String name) {
		JButton b = new JButton(name);
		colorCoatJC(b);
		return b;
	}

	public JLabel createJLabel(String label) {
		JLabel l = new JLabel(label);
		l.setForeground(c2);
		return l;
	}

	public JTextField createJTextField(String d, int columns) {
		JTextField tf = new JTextField(d, columns);
		colorCoatJC(tf);
		return tf;
	}

	public JTextArea createJTextArea(int r, int c) {
		JTextArea ta = new JTextArea(r, c);
		colorCoatJC(ta);
		return ta;
	}

}
