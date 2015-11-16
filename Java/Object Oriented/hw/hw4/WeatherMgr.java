package hw.hw4;
import java.util.*;

public class WeatherMgr {
	private Collection<WeatherData> wData = new ArrayList<WeatherData>();

	   public void registerObserver(WeatherData obs) {
		   wData.add(obs);
	   }
	   
	   public void removeObserver(WeatherData obs) {
		   wData.remove(obs);
	   }
	   
	   public void measurementsAdded(String city, float t, float h, float p) {
	      for (WeatherData wDatum : wData)
	         wDatum.update(city, t, h, p);
	   }

}
