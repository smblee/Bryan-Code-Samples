package hw.hw6;

import java.util.List;

import javax.swing.*;


public class FrameOutput implements WeatherOutput {
	private String title;
	private resultGUI gui;
	private ComponentStyler cs;
	
	public FrameOutput(List<WeatherDisplay> wDisplays, String title, ComponentStyler cs) {	
		for (WeatherDisplay wDisplay : wDisplays) {
			wDisplay.setWeatherOutput(this);
		}
		this.title = title;
		this.cs = cs;
		gui = new resultGUI();
	}
	
	public void printInfo(String msg) {
		gui.updateResult(msg);
	}
	
	
	@SuppressWarnings("serial")
	// result GUI screen
	class resultGUI extends JFrame {
		private JTextArea area = cs.createJTextArea(10,50);
		
		resultGUI() {
			area.setLineWrap(true);
			this.setTitle(title);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			JScrollPane p1 = new JScrollPane(area);
			add(p1);
			
			pack();
			setVisible(true);
		}
		
		public void updateResult(String result) {
			area.append(result+"\n");
		}
	}
}
