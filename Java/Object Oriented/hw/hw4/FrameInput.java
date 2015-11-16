package hw.hw4;

import javax.swing.*;

public class FrameInput implements WeatherInput {
	public WeatherMgr mgr;
	
   private JLabel cityLabel = new JLabel("city: ");
   private JTextField city = new JTextField("Boston", 10);
   private JLabel tempLabel = new JLabel("temperature: ");
   private JTextField temp = new JTextField("70", 10);
   private JLabel pressureLabel = new JLabel("pressure: ");
   private JTextField pressure = new JTextField("30.4", 10);
   private JLabel humidityLabel = new JLabel("humidity: ");
   private JTextField humidity = new JTextField("60", 10);
   private JButton btn = new JButton("ENTER");


   public FrameInput(WeatherMgr mgr) {
		this.mgr = mgr;
   }
	
   public void run() {
	   JFrame mainFrame = new JFrame("Enter the Weather!");	
	   JPanel p1 = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p1.add(cityLabel); p1.add(city);
		p1.add(tempLabel); p1.add(temp);
		p1.add(pressureLabel); p1.add(pressure); 
		p1.add(humidityLabel); p1.add(humidity); 
		
		p1.add(btn);

		btn.addActionListener( evt -> sendMeasurements() );

		mainFrame.setVisible(true);
		mainFrame.add(p1);
		mainFrame.setLocation(200,200);
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
