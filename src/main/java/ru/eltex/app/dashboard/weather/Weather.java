package ru.eltex.app.dashboard.weather;

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
    private String minC;

    private String maxC;

    private String afternoon;

    private String afternoonDesc;

    private String evening;

    private String eveningDesc;

    public String getAfternoonDesc() {
        return afternoonDesc;
    }

    public void setAfternoonDesc(String afternoonDesc) {
        this.afternoonDesc = afternoonDesc;
    }

    public String getEveningDesc() {
        return eveningDesc;
    }

    public void setEveningDesc(String eveningDesc) {
        this.eveningDesc = eveningDesc;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getEvening() {
        return evening;
    }

    public void setEvening(String evening) {
        this.evening = evening;
    }

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

    public String getMinC() {
        return minC;
    }

    public void setMinC(String minC) {
        this.minC = minC;
    }

    public String getMaxC() {
        return maxC;
    }

    public void setMaxC(String maxC) {
        this.maxC = maxC;
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
                "\nMin temp: " + maxC + "\nMin temp: " + minC + "\n";
    }
}
