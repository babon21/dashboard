package ru.eltex.app.dashboard;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import ru.eltex.app.dashboard.counter.CountComponent;
import ru.eltex.app.dashboard.currency.CurrencyComponent;
import ru.eltex.app.dashboard.weather.WeatherComponent;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main view contains a button a click listener.
 */
@HtmlImport("frontend://styles/shared-styles.html")
@Route("")
public class MainView extends VerticalLayout {

    private Label stateInfo;

    public MainView() {
        setClassName("my-root");
        WeatherComponent weatherComponent = new WeatherComponent(this);
        CurrencyComponent currencyComponent = new CurrencyComponent(this);
        CountComponent countComponent = new CountComponent();

        VerticalLayout verticalLayout = new VerticalLayout(currencyComponent, countComponent);
        VerticalLayout weatherLayout = new VerticalLayout(weatherComponent);
        HorizontalLayout horizontalLayout = new HorizontalLayout(weatherLayout, verticalLayout);
        horizontalLayout.setSizeFull();
        String ip = UI.getCurrent().getSession().getBrowser().getAddress();

        Label ipLabel = new Label("Ваш IP: " + ip);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date now = new Date();
        stateInfo = new Label("Информация по состоянию на " + dateFormat.format(now));

        horizontalLayout.setHeight("90%");

        HorizontalLayout timeIP = new HorizontalLayout(stateInfo, ipLabel);
        timeIP.getStyle().set("margin-top", "25%");
        timeIP.getStyle().set("font-size", "16pt");
        timeIP.setWidth("100%");
        stateInfo.setWidth("80%");

        add(horizontalLayout, timeIP);
        setSizeFull();

    }

    public void updateTime() {
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        stateInfo.setText("Информация по состоянию на " + dateFormat.format(now));
    }
}
