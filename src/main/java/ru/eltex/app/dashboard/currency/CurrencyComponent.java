package ru.eltex.app.dashboard.currency;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.MainView;
import ru.eltex.app.dashboard.custom.CustomNotification;
import ru.eltex.app.dashboard.exception.ApiException;
import ru.eltex.app.dashboard.util.CurrencyHelper;
import ru.eltex.app.dashboard.util.JsonHelper;

import java.io.IOException;

/**
 * UI компонент, отвечающий за отображение текущего курса валют
 * @author darhzain
 */
public class CurrencyComponent extends Composite<Div> {

    public CurrencyComponent() {}

    /** Надпись для значения текущего курса доллара */
    private Label usdLabel = new Label();

    /** Надпись для значения текущего курса евро */
    private Label eurLabel = new Label();

    /** Надпись для значения изменения курса доллара */
    private Label usdDiff = new Label();

    /** Надпись для значения изменения курса евро */
    private Label eurDiff = new Label();

    /** Иконка для отображения изменения курса доллара */
    private Icon usdIconDiff = new Icon();

    /** Иконка для отображения изменения курса евро */
    private Icon eurIconDiff = new Icon();

    /** Основной Layout компонента */
    private VerticalLayout verticalLayout;

    /** Layout для курса валют */
    private HorizontalLayout currencyLayout;

    /** Layout для иконок */
    private VerticalLayout icons;

    /** Заголовок компонента */
    private Label currencyLabel;

    /** Кнопка Обновить */
    private Button updateButton;


    /** Надпись о недоступности сервиса
     *  Выводится в случае отсутствия интернета,
     *  или недоступности удаленного сервиса
     * */
    private Label errorLabel = new Label("Сервис недоступен");

    /** Сервис получения курса валют */
    private static CurrencyService currencyService = new CBRCurrencyService(new CurrencyHelper());

    private static final Logger logger = Logger.getLogger(CurrencyComponent.class);

    private static final String ERROR_MESSAGE = "Ошибка запроса погоды";


    public CurrencyComponent(MainView view) {
        icons = new VerticalLayout(usdIconDiff, eurIconDiff);
        currencyLabel = new Label("Курсы валют");

        setStyle();
        VerticalLayout mainValues = new VerticalLayout(usdLabel, eurLabel);
        mainValues.setWidth("80%");
        mainValues.getStyle().set("padding-left", "8%");

        VerticalLayout diffValues = new VerticalLayout(usdDiff, eurDiff);
        diffValues.getStyle().set("margin-top", "1%");
        diffValues.getStyle().set("margin-left", "0%");
        diffValues.getStyle().set("padding-left", "0%");
        diffValues.setWidth("35%");

        currencyLayout = new HorizontalLayout(mainValues, icons,diffValues);
        currencyLayout.setClassName("currency-layout");
        currencyLayout.setSizeFull();

        updateButton = new Button("Обновить");
        updateButton.setClassName("currency-button");

        updateButton.addClickListener(e -> {
            update();
            view.updateTime();
        });
        verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setSizeFull();

        getContent().add(verticalLayout);

        update();

        logger.debug("Окончание создания CurrencyComponent");
    }

    /**
     * Установка стилей UI элементов
     */
    private void setStyle() {
        errorLabel.getStyle().set("font-size", "18pt");

        logger.debug("Создание CurrencyComponent");
        getContent().setClassName("my-currency");

        currencyLabel.setClassName("currency-title");

        usdLabel.getStyle().set("margin-top", "5%");
        usdDiff.getStyle().set("margin-top", "5%");

        eurLabel.getStyle().set("margin-top", "1%");
        eurDiff.getStyle().set("margin-top", "1%");

        icons.getStyle().set("margin-left", "0%");
        icons.getStyle().set("padding-right", "0%");
        icons.getStyle().set("padding-top", "3%");
        icons.getStyle().set("padding-bottom", "3%");
        icons.setWidth("14%");
        icons.setMargin(false);
    }

    /**
     * Обновление курса валют и отображение соответствующей информации.
     * В случае недоступности удаленного сервиса выводит уведомление
     */
    private void update() {
        try {
            Currency currency = currencyService.getCurrency();

            float usdDiffF = currency.getUsdDiff();
            float eurDiffF = currency.getEurDiff();

            icons.removeAll();
            usdIconDiff = getIcon(usdDiffF);
            eurIconDiff = getIcon(eurDiffF);
            icons.add(usdIconDiff, eurIconDiff);

            usdDiff.setText(convertWithSign(usdDiffF));
            eurDiff.setText(convertWithSign(eurDiffF));

            usdDiff.getStyle().set("color", getColor(usdDiffF));
            eurDiff.getStyle().set("color", getColor(eurDiffF));

            usdIconDiff.setColor(getColor(usdDiffF));
            eurIconDiff.setColor(getColor(eurDiffF));

            usdLabel.setText("USD: " + currency.getUsd() + " RUB");
            eurLabel.setText("EUR: " + currency.getEur() + " RUB");

            verticalLayout.removeAll();
            verticalLayout.add(currencyLabel, currencyLayout, updateButton);
        } catch (ApiException e) {
            CustomNotification notification = new CustomNotification(e.getMessage());
            verticalLayout.remove(currencyLabel, currencyLayout, updateButton);
            errorLabel.setText("Ошибка, API сервиса был изменен");
            verticalLayout.add(currencyLabel, errorLabel, updateButton);

            notification.show();
            logger.error("Failed!", e);
        } catch (IOException e) {
            CustomNotification notification = new CustomNotification(ERROR_MESSAGE);
            verticalLayout.remove(currencyLabel, currencyLayout, updateButton);
            errorLabel.setText("Сервис недоступен");
            verticalLayout.add(currencyLabel, errorLabel, updateButton);

            notification.show();
            logger.error("Failed!", e);
        }
    }

    /**
     * Преобразование в строку со знаком (+ или -)
     * @param f значение курса валюты
     * @return значение со знаком
     */
    private String convertWithSign(float f) {
        return f > 0.0 ? "+ " + f : String.valueOf(f);
    }

    /**
     * Получить иконку курса валют, в зависимости от разницы валюты
     * @param f значение разницы курса валют
     * @return Объект Vaadin иконки
     */
    private Icon getIcon(float f) {
        if (f > 0)
            return new Icon(VaadinIcon.ARROW_UP);
        else if (f < 0)
            return new Icon(VaadinIcon.ARROW_DOWN);
        else
            return new Icon();
    }

    /**
     * Получение цвета элементов, отображающих значение, иконку валюты
     * @param f значение разницы курса валют
     * @return цвет
     */
    private String getColor(float f) {
        if (f > 0)
            return "#30BA8F";
        else if (f < 0)
            return "#D71868";
        else
            return "blue";
    }
}
