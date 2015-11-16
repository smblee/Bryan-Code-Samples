package hw.hw4;

public class CurrentConditionsDisplay implements WeatherDisplay {
   private float temp;
   private float humidity;
   private WeatherOutput outputStrategy;

	public CurrentConditionsDisplay (WeatherData wData) {
		wData.registerObserver(this);
	}	   

	public void update(WeatherData wd) {
      this.temp = wd.getTemp();
      this.humidity = wd.getHumidity();
      display();
   }
	   
   public void display() {
	   outputStrategy.printInfo("Current conditions: " + temp
               + "F degrees and " + humidity + "% humidity");
   }
	
   public void setWeatherOutput(WeatherOutput wo) {
	   outputStrategy = wo;
   }

}
