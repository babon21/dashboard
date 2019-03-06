package ru.mail.dmitrii.entity;

public class Weather {
    // Текущая погода
    private String cur_C;

    private String feelsLikeC;

    // метр в секунду
    private String wind;

    // в процентах
    private String humidity;

    // в мм рт.ст.
    private String pressure;


    // Погода на завтра
    private String min_C;

    private String max_C;


    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }



    public String getCur_C() {
        return cur_C;
    }

    public void setCur_C(String cur_C) {
        this.cur_C = cur_C;
    }

    public String getMin_C() {
        return min_C;
    }

    public void setMin_C(String min_C) {
        this.min_C = min_C;
    }

    public String getMax_C() {
        return max_C;
    }

    public void setMax_C(String max_C) {
        this.max_C = max_C;
    }

    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(String feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Current temp: " + cur_C + "\nFeels like C: " + feelsLikeC +
                "\nCurrent wind: " + wind + "\nHumidity: " + humidity +
                "\nPressure: " + pressure +
                "\nMin temp: " + max_C + "\nMin temp: " + min_C + "\n";
    }
}
