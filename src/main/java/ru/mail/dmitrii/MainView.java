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

    public MainView() {
        WeatherComponent weatherComponent = new WeatherComponent();
        CurrencyComponent currencyComponent = new CurrencyComponent();
        CountComponent countComponent = new CountComponent();
        //addListener()
        HorizontalLayout horizontalLayout = new HorizontalLayout(weatherComponent,
                currencyComponent,
                countComponent);
        //UI.getCurrent().getSession().getBrowser()

        String ip = UI.getCurrent().getSession().getBrowser().getAddress();

        Label ipLabel = new Label("Ваш IP: " + ip);
        Label testCount = new Label("TEST COUNT : " + count++);

        add(horizontalLayout, ipLabel, testCount);

        setSizeFull();

    }
}
