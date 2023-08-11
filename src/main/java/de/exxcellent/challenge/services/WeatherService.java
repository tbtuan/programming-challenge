package de.exxcellent.challenge.services;

import de.exxcellent.challenge.models.Weather;
import de.exxcellent.challenge.readers.Reader;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class WeatherService {
    Reader<Weather> weatherReader;

    public WeatherService(Reader<Weather> weatherReader) {
        this.weatherReader = weatherReader;
    }

    public List<Weather> getWeathers() {
        if (weatherReader == null) {
            return new LinkedList<>();
        }
        return weatherReader.readAll();
    }

    public Weather getSmallestTempSpread(List<Weather> weatherList) {
        if (weatherList == null || weatherList.isEmpty()) {
            return null;
        }
        Comparator<Weather> weatherComparator = Comparator.comparing(Weather::getTemperatureSpread);
        Weather weatherSmallestTempSpread = Collections.min(weatherList, weatherComparator);
        return weatherSmallestTempSpread;
    }

}
