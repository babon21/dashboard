package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CurrencyComponent extends Composite<Div> {

    private Label currencyLabel;

    public CurrencyComponent() {
        getContent().setClassName("my-currency");

        currencyLabel = new Label("Курсы валют");
        VerticalLayout verticalLayout = new VerticalLayout(currencyLabel);

        getContent().add(verticalLayout);
    }
}
