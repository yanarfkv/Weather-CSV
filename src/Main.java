import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path to the csv file");
        final String CSV_FILE = scanner.nextLine();

        System.out.println("Enter the path to the file for recording");
        final String FILE_TO_WRITE = scanner.nextLine();

        WeatherManager fileManager = new WeatherManager(new File(Paths.get(CSV_FILE).toUri()));
        List<Weather> weatherList = fileManager.readWeather();
        WeatherFile weatherFile = new WeatherFile(weatherList);

        File file = new File(FILE_TO_WRITE);

        PrintWriter printWriter = new PrintWriter(file);

        printWriter.println("Average temperature = " + weatherFile.countAverageTemperature());
        printWriter.println("Average humidity = " + weatherFile.countAverageHumidity());
        printWriter.println("Average wind speed = " + weatherFile.countAverageWindSpeed());

        SimpleDateFormat format = new SimpleDateFormat("dd.MM, HH:mm");

        Weather maxTemp = weatherFile.maxTemp();
        Date maxTempDate = maxTemp.getDate();
        printWriter.println("The highest temperature - " + maxTemp.getTemperature() + ", was at " + format.format(maxTempDate));

        Weather minHumidity = weatherFile.minHumidity();
        Date minHumidityDate = maxTemp.getDate();
        printWriter.println("The lowest humidity - " + minHumidity.getHumidity() +  ", was at " + format.format(minHumidityDate));

        Weather maxWindSpeedDto = weatherFile.maxWindSpeed();
        Date maxWindSpeedDate = maxTemp.getDate();
        printWriter.println("The strongest wind - " + maxWindSpeedDto.getWindSpeed() + ", was at " + format.format(maxWindSpeedDate));

        printWriter.println("Most frequent wind direction - " + weatherFile.windDirection());

        printWriter.flush();
        printWriter.close();

        System.out.println("Data written to file");
    }

}
