package de.exxcellent.challenge;

import de.exxcellent.challenge.models.Football;
import de.exxcellent.challenge.models.Weather;
import de.exxcellent.challenge.readers.CSVFileReader;
import de.exxcellent.challenge.readers.Reader;
import de.exxcellent.challenge.services.FootballService;
import de.exxcellent.challenge.services.WeatherService;
import de.exxcellent.challenge.verifiers.FootballBeanVerifier;
import de.exxcellent.challenge.verifiers.WeatherBeanVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private WeatherService weatherService;
    private FootballService footballService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService(null);
        footballService = new FootballService(null);
    }

    @Test
    void csvReaderTest() {
        Reader<Weather> weatherReader = new CSVFileReader(Weather.class, null, "mock/weather-mock.csv");
        List<Weather> weathers = weatherReader.readAll();
        Weather weatherExpected = new Weather();
        weatherExpected.setDay(1);
        weatherExpected.setMaxTemperature(88);
        weatherExpected.setMinTemperature(59);
        assertNotNull(weathers);
        assertTrue(weathers.stream().count() == 1);
        Weather weatherActual = weathers.get(0);
        assertEquals(weatherExpected.getDay(), weatherActual.getDay());
        assertEquals(weatherExpected.getMaxTemperature(), weatherActual.getMaxTemperature());
        assertEquals(weatherExpected.getMinTemperature(), weatherActual.getMinTemperature());
    }

    @Test
    void csvReaderWeatherVerifierTest() {
        Reader<Weather> weatherReader = new CSVFileReader(Weather.class, new WeatherBeanVerifier(),
                "mock/weather-malformed-mock.csv");
        List<Weather> weatherList = weatherReader.readAll();
        Weather weatherExpected = new Weather();
        weatherExpected.setDay(1);
        weatherExpected.setMaxTemperature(88);
        weatherExpected.setMinTemperature(59);
        assertNotNull(weatherList);
        assertTrue(weatherList.stream().count() == 1);
        Weather weatherActual = weatherList.get(0);
        assertEquals(weatherExpected.getDay(), weatherActual.getDay());
        assertEquals(weatherExpected.getMaxTemperature(), weatherActual.getMaxTemperature());
        assertEquals(weatherExpected.getMinTemperature(), weatherActual.getMinTemperature());
    }

    @Test
    void csvReaderFootballVerifierTest() {
        Reader<Football> footballReader = new CSVFileReader(Football.class, new FootballBeanVerifier(),
                "mock/football-malformed-mock.csv");
        List<Football> footballList = footballReader.readAll();
        Football footballExpected = new Football();
        footballExpected.setTeam("Arsenal");
        footballExpected.setGoals(79);
        footballExpected.setGoalsAllowed(36);
        assertNotNull(footballList);
        assertTrue(footballList.stream().count() == 1);
        Football footballActual = footballList.get(0);
        assertEquals(footballExpected.getTeam(), footballActual.getTeam());
        assertEquals(footballExpected.getGoals(), footballActual.getGoals());
        assertEquals(footballExpected.getGoalsAllowed(), footballActual.getGoalsAllowed());
    }

    @Test
    void weatherServiceTest() {
        // 1.) Should return null (List is null)
        List<Weather> weatherList = new LinkedList<>();
        Weather weatherActual1 = weatherService.getSmallestTempSpread(null);
        assertEquals(null, weatherActual1);
        // 2.) Should return null (List is empty)
        Weather weatherActual2 = weatherService.getSmallestTempSpread(new LinkedList<>());
        assertEquals(null, weatherActual2);
        // 3.) Should retrieve the weather object with the smallest temperature spread
        Weather weather1 = new Weather();
        weather1.setDay(1);
        weather1.setMaxTemperature(7);
        weather1.setMinTemperature(6);
        Weather weather2 = new Weather();
        weather2.setDay(2);
        weather2.setMaxTemperature(8);
        weather2.setMinTemperature(8);
        weatherList.add(weather1);
        weatherList.add(weather2);
        Weather weatherActual3 = weatherService.getSmallestTempSpread(weatherList);
        Weather weatherExpected3 = weather2;
        assertEquals(weatherExpected3, weatherActual3);
    }

    @Test
    void footballServiceTest() {
        // 1.) Should return null (List is null)
        List<Football> footballList = new LinkedList<>();
        Football footballActual1 = footballService.getSmallestGoalSpread(null);
        assertEquals(null, footballActual1);
        // 2.) Should return null (List is empty)
        Football footballActual2 = footballService.getSmallestGoalSpread(new LinkedList<>());
        assertEquals(null, footballActual2);
        // 3.) Should retrieve the football object with the smallest goal spread
        Football football1 = new Football();
        football1.setTeam("A");
        football1.setGoals(3);
        football1.setGoalsAllowed(1);
        Football football2 = new Football();
        football2.setTeam("B");
        football2.setGoals(7);
        football2.setGoalsAllowed(1);
        footballList.add(football1);
        footballList.add(football2);
        Football footballActual3 = footballService.getSmallestGoalSpread(footballList);
        Football footballExpected3 = football1;
        assertEquals(footballExpected3, footballActual3);
    }

}