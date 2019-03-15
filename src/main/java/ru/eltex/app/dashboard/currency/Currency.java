package ru.eltex.app.dashboard.currency;

/**
 * Класс, содержащий информацию о курсе валют
 * @author darzhain
 */
public class Currency {

    /** Текущее значение доллара по курсу Центробанка РФ */
    private String usd;

    /** Текущее значение евро по курсу Центробанка РФ */
    private String eur;

    /** Разница доллара, по сравнению с предыдущим курсом */
    private float usdDiff;

    /** Разница евро, по сравнению с предыдущим курсом */
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

    @Override
    public String toString() {
        return "Currency { " +
                "usd=" + usd +
                ", eur=" + eur +
                ", usdDiff=" + usdDiff +
                ", eurDiff=" + eurDiff +
                " }";
    }
}

