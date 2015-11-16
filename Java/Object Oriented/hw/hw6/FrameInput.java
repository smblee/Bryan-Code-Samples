package hw.hw6;

import javax.swing.*;

public class FrameInput implements WeatherInput {
	public WeatherMgr mgr;
	private ComponentStyler cs;
	private JLabel cityLabel;
	private JTextField city;
	private JLabel tempLabel;
	private JTextField temp;
	private JLabel pressureLabel;
	private JTextField pressure;
	private JLabel humidityLabel;
	private JTextField humidity;
	private JButton btn;
	


	public FrameInput(WeatherMgr mgr, ComponentStyler cs) {
		this.cs = cs;
		this.mgr = mgr;
		cityLabel = cs.createJLabel("city: ");
		city = cs.createJTextField("Boston", 10);
		tempLabel = cs.createJLabel("temperature: ");
		temp = cs.createJTextField("70", 10);
		pressureLabel = cs.createJLabel("pressure: ");
		pressure = cs.createJTextField("30.4", 10);
		humidityLabel = cs.createJLabel("humidity: ");
		humidity = cs.createJTextField("60", 10);
		btn = cs.createJButton("ENTER");
	}
	
	

	public void run() {
		JFrame mainFrame = new JFrame("Enter the Weather!");
		JPanel p1 = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p1.add(cityLabel);
		p1.add(city);
		p1.add(tempLabel);
		p1.add(temp);
		p1.add(pressureLabel);
		p1.add(pressure);
		p1.add(humidityLabel);
		p1.add(humidity);

		p1.add(btn);

		btn.addActionListener(evt -> sendMeasurements());

		mainFrame.setVisible(true);
		mainFrame.add(p1);
		mainFrame.setLocation(200, 200);
		mainFrame.pack();

	}

	public void sendMeasurements() {
		String c = city.getText();
		float t = Float.parseFloat(temp.getText());
		float h = Float.parseFloat(humidity.getText());
		float p = Float.parseFloat(pressure.getText());
		mgr.measurementsAdded(c, t, h, p);
	}

}
