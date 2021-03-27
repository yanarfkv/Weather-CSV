import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WeatherFile {
    private List<Weather> weathers;

    public WeatherFile(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Double countAverageTemperature() {
        return weathers.stream().mapToDouble((weather) -> weather.getTemperature()).average().getAsDouble();
    }

    public Double countAverageHumidity() {
        return weathers.stream().mapToDouble((weather) -> weather.getHumidity()).average().getAsDouble();

    }

    public Double countAverageWindSpeed() {
        return weathers.stream().mapToDouble((weather) -> weather.getWindSpeed()).average().getAsDouble();
    }

    public Weather maxTemp() {
        Double maxTemp = weathers.stream().mapToDouble((weather) -> weather.getTemperature()).max().getAsDouble();
        return weathers.stream().filter((weather) -> weather.getTemperature().equals(maxTemp)).findFirst().get();
    }

    public Weather minHumidity() {
        Double minHumidity = weathers.stream().mapToDouble((weather) -> weather.getHumidity()).min().getAsDouble();
        return weathers.stream().filter((weather) -> weather.getHumidity().equals(minHumidity)).findFirst().get();
    }

    public Weather maxWindSpeed() {
        Double maxWindSpeed = weathers.stream().mapToDouble((weather) -> weather.getWindSpeed()).max().getAsDouble();
        return weathers.stream().filter((weather) -> weather.getWindSpeed().equals(maxWindSpeed)).findFirst().get();
    }

    public String windDirection() {
        List<String> windDirections = weathers.stream().map(weather -> {
            double wind = weather.getWindDirection();
            if (wind >=315 && wind <45) return "North";
            else if (wind >=45 && wind < 135) return "East";
            else if (wind >=135 && wind < 225) return "South";
            else return "West";
        }).collect(Collectors.toList());
        TreeMap<String, Long> numOfDirections = new TreeMap<>();
        numOfDirections.put("North", windDirections.stream().filter(windDirection -> windDirection.equals("North")).count());
        numOfDirections.put("East", windDirections.stream().filter(windDirection -> windDirection.equals("North")).count());
        numOfDirections.put("South", windDirections.stream().filter(windDirection -> windDirection.equals("North")).count());
        numOfDirections.put("West", windDirections.stream().filter(windDirection -> windDirection.equals("North")).count());
        Collection<Long> values = numOfDirections.values();
        Long value = values.stream().mapToLong(value1 -> value1).max().getAsLong();
        Set<String> sets = numOfDirections.keySet();
        return sets.stream().filter(s -> numOfDirections.get(s).equals(value)).findFirst().get();
    }
}

