package ru.mail.dmitrii.entity;

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



    /** Значение всех полей, когда сервер информера недоступен*/
    private String sign;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

