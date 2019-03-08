package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.MainView;
import ru.mail.dmitrii.entity.Currency;
import ru.mail.dmitrii.service.CurrencyService;

public class CurrencyComponent extends Composite<Div> {

    public CurrencyComponent() {}

    private Label usdLabel = new Label();
    private Label eurLabel = new Label();
    private Label usdDiff = new Label();
    private Label eurDiff = new Label();

    private Icon usdIconDiff;
    private Icon eurIconDiff;


    public CurrencyComponent(MainView view) {
        update();
        getContent().setClassName("my-currency");

        Label currencyLabel = new Label("Курсы валют");
        currencyLabel.setClassName("currency-title");

        usdLabel.getStyle().set("margin-top", "5%");
        usdDiff.getStyle().set("margin-top", "5%");

        eurLabel.getStyle().set("margin-top", "1%");
        eurDiff.getStyle().set("margin-top", "1%");


        VerticalLayout mainValues = new VerticalLayout(usdLabel, eurLabel);
        mainValues.setWidth("80%");
        mainValues.getStyle().set("padding-left", "8%");

        VerticalLayout icons = new VerticalLayout(usdIconDiff, eurIconDiff);
        icons.getStyle().set("margin-left", "0%");
        icons.getStyle().set("padding-right", "0%");
        icons.getStyle().set("padding-top", "3%");
        icons.getStyle().set("padding-bottom", "3%");

        icons.setWidth("14%");
        icons.setMargin(false);

        VerticalLayout diffValues = new VerticalLayout(usdDiff, eurDiff);
        diffValues.getStyle().set("margin-top", "1%");
        diffValues.getStyle().set("margin-left", "0%");
        diffValues.getStyle().set("padding-left", "0%");
        diffValues.setWidth("35%");

        HorizontalLayout currencyLayout = new HorizontalLayout(mainValues, icons,diffValues);
        currencyLayout.setClassName("currency-layout");
        currencyLayout.setSizeFull();

        Button updateButton = new Button("Обновить");
        updateButton.setClassName("currency-button");

        updateButton.addClickListener(e -> {
            update();
            view.updateTime();
        });
        VerticalLayout verticalLayout = new VerticalLayout(currencyLabel,
                currencyLayout, updateButton);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setSizeFull();

        getContent().add(verticalLayout);
    }

    private void update() {

        Currency currency = CurrencyService.getCurrency();

        float usdDiffF = currency.getUsdDiff();
        float eurDiffF = currency.getEurDiff();

        usdIconDiff = getIcon(usdDiffF);
        eurIconDiff = getIcon(eurDiffF);

        usdDiff.setText(convertWithSign(usdDiffF));
        eurDiff.setText(convertWithSign(eurDiffF));

        usdDiff.getStyle().set("color", getColor(usdDiffF));
        eurDiff.getStyle().set("color", getColor(eurDiffF));

        usdIconDiff.setColor(getColor(usdDiffF));
        eurIconDiff.setColor(getColor(eurDiffF));

        usdLabel.setText("USD/RUB: " + currency.getUsd());
        eurLabel.setText("EUR/RUB: " + currency.getEur());
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
