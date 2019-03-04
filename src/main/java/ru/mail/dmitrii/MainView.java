package ru.mail.dmitrii;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import ru.mail.dmitrii.frontend.CountComponent;
import ru.mail.dmitrii.frontend.CurrencyComponent;
import ru.mail.dmitrii.frontend.WeatherComponent;
import ru.mail.dmitrii.service.CurrencyService;
import ru.mail.dmitrii.service.WeatherService;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main view contains a button and a click listener.
 */
@HtmlImport("frontend://styles/shared-styles.html")
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {
    //private WeatherService weatherService = new WeatherService();
    //private CurrencyService currencyService = new CurrencyService();
    static int count = 0;
    private Label stateInfo;

    public MainView() {
        setClassName("my-root");
        WeatherComponent weatherComponent = new WeatherComponent(this);
        CurrencyComponent currencyComponent = new CurrencyComponent(this);
        CountComponent countComponent = new CountComponent();
        //addListener()
        HorizontalLayout horizontalLayout = new HorizontalLayout(weatherComponent,
                currencyComponent,
                countComponent);
        //UI.getCurrent().getSession().getBrowser()

        String ip = UI.getCurrent().getSession().getBrowser().getAddress();

        Label ipLabel = new Label("Ваш IP: " + ip);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date now = new Date();
        stateInfo = new Label("Информация по состоянию на " + dateFormat.format(now));

        horizontalLayout.setSizeFull();

        HorizontalLayout timeIP = new HorizontalLayout(stateInfo, ipLabel);

        add(horizontalLayout, timeIP);
        setSizeFull();

    }

    public void updateTime() {
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        stateInfo.setText("Информация по состоянию на " + dateFormat.format(now));
    }
}
