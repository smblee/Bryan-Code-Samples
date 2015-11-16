package hw.hw4;

import java.util.List;

public class ConsoleOutput implements WeatherOutput {
	private String title;
	
	public ConsoleOutput(List<WeatherDisplay> wDisplays, String title) {	
		for (WeatherDisplay wDisplay : wDisplays) {
			wDisplay.setWeatherOutput(this);
		}
		this.title = title;
	}
	
	public void printInfo(String msg) {
		System.out.println(title);
		System.out.println(msg);
	}
}
