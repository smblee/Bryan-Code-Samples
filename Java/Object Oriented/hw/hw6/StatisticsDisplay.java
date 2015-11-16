package hw.hw6;

public class StatisticsDisplay implements WeatherDisplay {
   private float maxTemp = 0.0f;
   private float minTemp = 200;
   private float tempSum= 0.0f;
   private int numReadings;
   private WeatherOutput outputStrategy;
	   
   public StatisticsDisplay(WeatherData wData) {
      wData.registerObserver(this);
   }
	   
   public void update(WeatherData wd) {
	  float temp = wd.getTemp();
      tempSum += temp;
      numReadings++;
      if (temp > maxTemp)
         maxTemp = temp;
      if (temp < minTemp)
         minTemp = temp;
      display();
   }
	   
   public void display() {
	   outputStrategy.printInfo("Avg/Max/Min temperature: " + (tempSum / numReadings)
               + "/" + maxTemp + "/" + minTemp);
   }
   
   public void setWeatherOutput(WeatherOutput wo) {
	   outputStrategy = wo;
   }
}
