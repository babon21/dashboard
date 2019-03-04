package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.MainView;
import ru.mail.dmitrii.entity.Currency;
import ru.mail.dmitrii.service.CurrencyService;

public class CurrencyComponent extends Composite<Div> {

    private Label currencyLabel;
    private CurrencyService currencyService = new CurrencyService();

    public CurrencyComponent() {}

    public CurrencyComponent(MainView view) {

        getContent().setClassName("my-currency");

        Currency currency = CurrencyService.getCurrency();
        currencyLabel = new Label("Курсы валют");
        Label usdLabel = new Label("USD/RUB: " + currency.getUsd());
        Label eurLabel = new Label("EUR/RUB" + currency.getEur());

        Button update = new Button("Обновить");
        update.setClassName("button");
        update.addClickListener(e -> {
            usdLabel.setText("USD/RUB: " + currency.getUsd());
            eurLabel.setText("EUR/RUB" + currency.getEur());
            view.updateTime();
        });
        VerticalLayout verticalLayout = new VerticalLayout(currencyLabel,
                usdLabel, eurLabel, update);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
    }
}
