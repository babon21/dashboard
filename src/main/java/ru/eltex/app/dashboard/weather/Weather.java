package ru.eltex.app.dashboard.weather;

/**
 * Класс, содержащий информацию о погоде
 * @author darzhain
 */
public class Weather {

    /** Текущая температура */
    private String curC;

    /** Температура ощущается как */
    private String feelsLikeC;

    /** Текущий ветер м/c */
    private String wind;

    /** Текущая влажность (%) */
    private String humidity;

    /** Текущее давление мм рт.ст. */
    private String pressure;

    /** Текущее описание погоды*/
    private String desc;


    /** Минимальная температура на следующий день */
    private String minC;

    /** Максимальная температура на следующий день */
    private String maxC;

    /** Температура на следующий день после обеда */
    private String afternoon;

    /** Описание погоды на следующий день после обеда */
    private String afternoonDesc;

    /** Температура на следующий день вечером */
    private String evening;

    /** Описание погоды на следующий день на вечер */
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
}
