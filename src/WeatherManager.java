import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class WeatherManager {
    private File file;

    public WeatherManager(File file) {
        this.file = file;
    }

    public List<Weather> readWeather() throws Exception {
        List<Weather> weathers = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] parts = str.split(",");
            if (isWeather(parts)) {
                Weather weather = deserializeWeather(parts);
                weathers.add(weather);
            }
        }
        return weathers;
    }

    public boolean isWeather(String[] parts) {
        String first = parts[0];
        char c = first.charAt(0);
        return c >= '0' && c <= '9';
    }

    private Weather deserializeWeather (String[] parts) throws Exception {
        Weather data;

        if (parts.length < 5) {
            throw new Exception("Parts length is " + parts.length);
        } else {
            String dateStr = parts[0];
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmm");
            Date trueDate = format.parse(dateStr);
            Double temperature = Double.parseDouble(parts[1]);
            Double humidity = Double.parseDouble(parts[2]);
            Double windSpeed = Double.parseDouble(parts[3]);
            Double windDirection = Double.parseDouble(parts[4]);
           data = new Weather(trueDate,temperature,humidity,windSpeed,windDirection);
        }
        return data;
    }
}
