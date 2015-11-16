package hw.hw4;

public class ForecastDisplay implements WeatherDisplay {
   private float currentpressure = 29.27f;
   private float oldpressure;
   private WeatherOutput outputStrategy;

	public ForecastDisplay (WeatherData wData) {
		wData.registerObserver(this);
	}
	
   public void update(WeatherData wd) {
      oldpressure = currentpressure;
      currentpressure = wd.getPressure();
      display();
   }

   public void display() {
	   String output = "Forecast: ";
	   if (currentpressure > oldpressure)
		   output = output + ("\nImproving weather on the way!");
	   else if (currentpressure == oldpressure)
		   output = output + ("\nMore of the same");
	   else
		   output = output + ("\nWatch out for cooler, rainy weather");
	   outputStrategy.printInfo(output);
   }
   
   public void setWeatherOutput(WeatherOutput wo) {
	   outputStrategy = wo;
   }
   
}
