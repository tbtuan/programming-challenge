package de.exxcellent.challenge.controllers;

import de.exxcellent.challenge.models.Weather;
import de.exxcellent.challenge.services.WeatherService;

import java.util.List;

public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public int retrieveSmallestTempSpread() {
        if (weatherService == null) {
            return -1;
        }
        List<Weather> weatherList = this.weatherService.getWeathers();
        Weather weatherSmallestTempSpread = this.weatherService.getSmallestTempSpread(weatherList);
        if (weatherSmallestTempSpread == null) {
            return -1;
        }
        return weatherSmallestTempSpread.getDay();
    }

}
