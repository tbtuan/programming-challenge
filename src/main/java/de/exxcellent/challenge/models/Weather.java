package de.exxcellent.challenge.models;

import com.opencsv.bean.CsvBindByName;

public class Weather {

    @CsvBindByName(column = "day", required = true)
    private int day;
    @CsvBindByName(column = "MxT", required = true)
    private int maxTemperature;
    @CsvBindByName(column = "MnT", required = true)
    private int minTemperature;

    public void setDay(int day) {
        this.day = day;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getDay() {
        return day;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getTemperatureSpread() {
        return maxTemperature - minTemperature;
    }
}
