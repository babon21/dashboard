package ru.eltex.app.dashboard.entity;

/**
 * Класс для описания значений валюты
 *
 * */
public class Currency {

    /**  Название валюты*/
    private String name;

    /** Текущее значение валюты по курсу Центробанка РФ */
    private String usd;

    private String eur;

    private float usdDiff;

    private float eurDiff;

    public float getUsdDiff() {
        return usdDiff;
    }

    public void setUsdDiff(float usdDiff) {
        this.usdDiff = usdDiff;
    }

    public float getEurDiff() {
        return eurDiff;
    }

    public void setEurDiff(float eurDiff) {
        this.eurDiff = eurDiff;
    }

    /** Значение всех полей, когда сервер информера недоступен*/
    private String sign;



    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getEur() {
        return eur;
    }

    public void setEur(String eur) {
        this.eur = eur;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

