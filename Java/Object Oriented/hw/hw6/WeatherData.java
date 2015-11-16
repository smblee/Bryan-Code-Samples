package hw.hw6;

import java.util.ArrayList;
import java.util.Collection;

public class WeatherData {
	   private Collection<WeatherDisplay> wDisplays = new ArrayList<WeatherDisplay>();
	   private String city;
	   private float temperature;
	   private float humidity;
	   private float pressure;
	   
	   public WeatherData(String city, WeatherMgr mgr) {
		   setCity(city);
		   mgr.registerObserver(this);
	   }
	   
	   public void setCity(String city) {
		   this.city = city;
	   }
	   
	   public void registerObserver(WeatherDisplay obs) {
		   wDisplays.add(obs);
	   }
	   
	   public void removeObserver(WeatherDisplay obs) {
		   wDisplays.remove(obs);
	   }
	   
	   public void notifyObservers() {
	      for (WeatherDisplay wDisplay : wDisplays)
	         wDisplay.update(this);
	   }
	   
	   public void measurementsChanged() {
	      notifyObservers();
	   }
	   
	   public void update(String city, float temp, float humidity, float pressure) {
		   if (this.city.equals(city)) {
			   setMeasurements(temp, humidity, pressure);
		   }
	   }
	   
	   public void setMeasurements(float temperature, float humidity, float pressure) {
	      this.temperature = temperature;
	      this.humidity = humidity;
	      this.pressure = pressure;
	      measurementsChanged();
	   }
	   
	   public float getTemp() {
		   return temperature;
	   }
	   
	   public float getHumidity() {
		   return humidity;
	   }
	   
	   public float getPressure() {
		   return pressure;
	   }
}
