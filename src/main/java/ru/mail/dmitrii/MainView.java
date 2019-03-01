package ru.mail.dmitrii;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import ru.mail.dmitrii.frontend.CountComponent;
import ru.mail.dmitrii.frontend.CurrencyComponent;
import ru.mail.dmitrii.frontend.WeatherComponent;

/**
 * The main view contains a button and a click listener.
 */
@HtmlImport("frontend://styles/shared-styles.html")
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
        CountComponent countComponent = new CountComponent();
        CurrencyComponent currencyComponent = new CurrencyComponent();
        WeatherComponent weatherComponent = new WeatherComponent();

        HorizontalLayout horizontalLayout = new HorizontalLayout(weatherComponent,
                currencyComponent,
                countComponent);

        Label ipLabel = new Label("Ваш IP: ");

        add(horizontalLayout, ipLabel);

        setSizeFull();

    }
}
