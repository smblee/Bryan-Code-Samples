package hw.hw4;

import java.util.Scanner;

public class ConsoleInput implements WeatherInput {
	public WeatherMgr mgr;

	public ConsoleInput(WeatherMgr mgr) {
		this.mgr = mgr;
	}

	public void run() {
		boolean end = false;
		Scanner sc = new Scanner(System.in);
		while ( ! end ) {
			System.out.println("What's the city?");
			String city = sc.next();
			System.out.println("Temperature?");
			float t = sc.nextFloat();
			System.out.println("Humidity?");
			float h = sc.nextFloat();
			System.out.println("Pressure?");
			float p = sc.nextFloat();
			System.out.println();
			mgr.measurementsAdded(city, t, h, p);
			System.out.println("===================================");
		}
	}
}
