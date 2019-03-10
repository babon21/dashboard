package ru.eltex.app.dashboard.currency;

/**
 * Класс для описания значений валюты
 *
 * */
public class Currency {

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

}

