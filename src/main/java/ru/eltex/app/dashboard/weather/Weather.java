package ru.eltex.app.dashboard.weather;

/**
 * Класс, содержащий информацию о погоде
 * @author darzhain
 */
public class Weather {

    /** Текущая температура */
    private int curTemp;

    /** Температура ощущается как */
    private int feelsLikeC;

    /** Текущий ветер м/c */
    private float wind;

    /** Текущая влажность (%) */
    private int humidity;

    /** Текущее давление мм рт.ст. */
    private float pressure;

    /** Текущее описание погоды*/
    private String desc;


    /** Минимальная температура на следующий день */
    private int minC;

    /** Максимальная температура на следующий день */
    private int maxC;

    /** Температура на следующий день после обеда */
    private int afternoon;

    /** Описание погоды на следующий день после обеда */
    private String afternoonDesc;

    /** Температура на следующий день вечером */
    private int evening;

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

    public int getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(int afternoon) {
        this.afternoon = afternoon;
    }

    public int getEvening() {
        return evening;
    }

    public void setEvening(int evening) {
        this.evening = evening;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCurTemp() {
        return curTemp;
    }

    public void setCurTemp(int curTemp) {
        this.curTemp = curTemp;
    }

    public int getMinC() {
        return minC;
    }

    public void setMinC(int minC) {
        this.minC = minC;
    }

    public int getMaxC() {
        return maxC;
    }

    public void setMaxC(int maxC) {
        this.maxC = maxC;
    }

    public int getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(int feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Weather { " +
                "curTemp=" + curTemp +
                ", feelsLikeC=" + feelsLikeC +
                ", wind=" + wind +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", desc=" + desc +
                ", minC=" + minC +
                ", maxC=" + maxC +
                ", afternoon=" + afternoon +
                ", afternoonDesc=" + afternoonDesc +
                ", evening=" + evening +
                ", eveningDesc=" + eveningDesc +
                " }";
    }
}
