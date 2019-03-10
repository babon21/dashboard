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
import ru.eltex.app.dashboard.exception.UserException;

public class CurrencyComponent extends Composite<Div> {

    public CurrencyComponent() {}

    private Label usdLabel = new Label();
    private Label eurLabel = new Label();
    private Label usdDiff = new Label();
    private Label eurDiff = new Label();

    private Icon usdIconDiff = new Icon();
    private Icon eurIconDiff = new Icon();

    private HorizontalLayout currencyLayout;
    private VerticalLayout verticalLayout;
    private Label currencyLabel;
    private Button updateButton;
    private VerticalLayout icons;

    private CurrencyService currencyService = new CBRCurrencyService();

    private static final Logger LOGGER = Logger.getLogger(CurrencyComponent.class);

    private Label errorLabel = new Label("Сервис недоступен");


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

        LOGGER.debug("Окончание создания CurrencyComponent");
    }

    private void setStyle() {
        errorLabel.getStyle().set("font-size", "18pt");

        LOGGER.debug("Создание CurrencyComponent");
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

            usdLabel.setText("USD/RUB: " + currency.getUsd());
            eurLabel.setText("EUR/RUB: " + currency.getEur());

            verticalLayout.removeAll();
            verticalLayout.add(currencyLabel, currencyLayout, updateButton);
        } catch (UserException e) {
            CustomNotification notification = new CustomNotification(e.getMessage());
            verticalLayout.remove(currencyLabel, currencyLayout, updateButton);
            verticalLayout.add(currencyLabel, errorLabel, updateButton);

            notification.show();
            LOGGER.error(e.getMessage());
        }
    }

    private String convertWithSign(float f) {
        return f > 0.0 ? "+ " + String.valueOf(f) : String.valueOf(f);
    }

    private Icon getIcon(float f) {
        if (f > 0)
            return new Icon(VaadinIcon.ARROW_UP);
        else if (f < 0)
            return new Icon(VaadinIcon.ARROW_DOWN);
        else
            return new Icon();
    }

    private String getColor(float f) {
        if (f > 0)
            return "#30BA8F";
        else if (f < 0)
            return "#D71868";
        else
            return "blue";
    }
}
