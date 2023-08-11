package de.exxcellent.challenge;

import de.exxcellent.challenge.controllers.FootballController;
import de.exxcellent.challenge.controllers.WeatherController;
import de.exxcellent.challenge.models.Football;
import de.exxcellent.challenge.models.Weather;
import de.exxcellent.challenge.readers.CSVFileReader;
import de.exxcellent.challenge.readers.Reader;
import de.exxcellent.challenge.services.FootballService;
import de.exxcellent.challenge.services.WeatherService;
import de.exxcellent.challenge.verifiers.FootballBeanVerifier;
import de.exxcellent.challenge.verifiers.WeatherBeanVerifier;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    private final static String WEATHER_CSV = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private final static String FOOTBALL_CSV = "src/main/resources/de/exxcellent/challenge/football.csv";

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        // Init service and controller
        if (args.length > 1 && args.length < 3) {
            switch(args[0]) {
                case "--football":
                    calculateFootball(args[1]);
                    break;
                case "--weather":
                    calculateWeather(args[1]);
                    break;
            }
        } else {
            calculateWeather(WEATHER_CSV);
            calculateFootball(FOOTBALL_CSV);
        }


    }

    private static void calculateWeather(String filename) {
        Reader<Weather> weatherReader = new CSVFileReader<Weather>(
                Weather.class, new WeatherBeanVerifier(), filename);
        WeatherService weatherService = new WeatherService(weatherReader);
        WeatherController weatherController = new WeatherController(weatherService);

        String dayWithSmallestTempSpread = String.valueOf(weatherController.retrieveSmallestTempSpread());
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
    }

    private static void calculateFootball(String filename) {
        Reader<Football> footballReader = new CSVFileReader<Football>(
                Football.class, new FootballBeanVerifier(), filename);
        FootballService footballService = new FootballService(footballReader);
        FootballController footballController = new FootballController(footballService);

        String teamWithSmallestGoalSpread = footballController.retrieveTeamWithSmallestGoalSpread();
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
