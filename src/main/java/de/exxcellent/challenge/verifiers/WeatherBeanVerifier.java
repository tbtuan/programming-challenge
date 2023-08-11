package de.exxcellent.challenge.verifiers;

import com.opencsv.bean.BeanVerifier;
import de.exxcellent.challenge.models.Weather;

public class WeatherBeanVerifier implements BeanVerifier<Weather> {

    @Override
    public boolean verifyBean(Weather weather) {
        // Days within boundaries (a month has at most 31 days)
        boolean isDay = weather.getDay() > 0 && weather.getDay() < 32;
        // Max temperature should be higher than min temperature
        boolean isMinMaxTemperatureCorrect = weather.getMaxTemperature() >= weather.getMinTemperature();
        return isDay && isMinMaxTemperatureCorrect;
    }
}
