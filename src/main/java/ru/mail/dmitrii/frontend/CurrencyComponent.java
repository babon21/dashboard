package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.MainView;
import ru.mail.dmitrii.entity.Currency;
import ru.mail.dmitrii.service.CurrencyService;

public class CurrencyComponent extends Composite<Div> {

    private Label currencyLabel;

    public CurrencyComponent() {}

    public CurrencyComponent(MainView view) {

        getContent().setClassName("my-currency");

        Currency currency = CurrencyService.getCurrency();
        currencyLabel = new Label("Курсы валют");
        currencyLabel.setClassName("currency-title");
        Label usdLabel = new Label("USD/RUB: " + currency.getUsd());
        Label eurLabel = new Label("EUR/RUB :" + currency.getEur());
        usdLabel.getStyle().set("margin-top", "5%");
        usdLabel.getStyle().set("margin-left", "4%");
        eurLabel.getStyle().set("margin-top", "1%");
        eurLabel.getStyle().set("margin-left", "4%");
        //usdLabel.setClassName("currency-label");
        //eurLabel.setClassName("currency-label");

        VerticalLayout currencyLayout = new VerticalLayout(usdLabel, eurLabel);
        currencyLayout.setClassName("currency-layout");
        currencyLayout.setSizeFull();

        Button update = new Button("Обновить");
        update.setClassName("currency-button");

        update.addClickListener(e -> {
            usdLabel.setText("USD/RUB: " + currency.getUsd());
            eurLabel.setText("EUR/RUB: " + currency.getEur());
            view.updateTime();
        });
        VerticalLayout verticalLayout = new VerticalLayout(currencyLabel,
                currencyLayout, update);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
    }
}
