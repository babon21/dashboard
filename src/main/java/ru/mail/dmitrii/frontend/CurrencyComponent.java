package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.service.CurrencyService;

public class CurrencyComponent extends Composite<Div> {

    private Label currencyLabel;
    private CurrencyService currencyService = new CurrencyService();

    public CurrencyComponent( ) {

        getContent().setClassName("my-currency");

        currencyLabel = new Label("Курсы валют: " + CurrencyService.getCurrency());

        Button update = new Button("Обновить");
        update.addClickListener(e -> {
            currencyLabel.setText("Курсы валют: " + CurrencyService.getCurrency());
        });
        VerticalLayout verticalLayout = new VerticalLayout(currencyLabel, update);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
    }
}
