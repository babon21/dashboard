package ru.mail.dmitrii.entity;

public class Weather {

    /* Текущая погода
    *  - текущая температура
    *  - ощущается как
    *  - ветер м/c
    *  - влажность (%)
    *  - давление мм рт.ст.
    * */
    private String curC;

    private String feelsLikeC;

    private String wind;

    private String humidity;

    private String pressure;

    private String desc;


    /* Погода на завтра
    *
    * */
    private String min_C;

    private String max_C;


    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurC() {
        return curC;
    }

    public void setCurC(String curC) {
        this.curC = curC;
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
        return "Current temp: " + curC + "\nFeels like C: " + feelsLikeC +
                "\nCurrent wind: " + wind + "\nHumidity: " + humidity +
                "\nPressure: " + pressure +
                "\nMin temp: " + max_C + "\nMin temp: " + min_C + "\n";
    }
}
