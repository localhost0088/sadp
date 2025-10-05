import java.util.Observable;
import java.util.Observer;

// Step 1: Subject class - WeatherStation
class WeatherStation extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public void measurementsChanged() {
        setChanged(); // Marks the Observable as changed
        notifyObservers(); // Notify all registered observers
    }

    // Getter methods
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

// Step 2: Observer class - Display that observes WeatherStation
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherStation) {
            WeatherStation weatherData = (WeatherStation) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("Current Conditions:");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Pressure: " + pressure + " hPa\n");
    }
}

// Step 3: Client class (main program)
public class WeatherStationDemo {
    public static void main(String[] args) {
        // Create WeatherStation (Observable)
        WeatherStation weatherStation = new WeatherStation();

        // Create Display (Observer)
        CurrentConditionsDisplay display = new CurrentConditionsDisplay();

        // Register the display observer
        weatherStation.addObserver(display);

        // Simulate weather data changes
        System.out.println("Updating weather measurements...\n");
        weatherStation.setMeasurements(30.4f, 70f, 1012f);

        weatherStation.setMeasurements(28.2f, 65f, 1009f);

        weatherStation.setMeasurements(33.5f, 72f, 1008f);
    }
}
