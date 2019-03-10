package ru.eltex.app.dashboard.weather;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.MainView;
import ru.eltex.app.dashboard.custom.CustomNotification;
import ru.eltex.app.dashboard.util.CitiesHelper;
import ru.eltex.app.dashboard.exception.UserException;

import java.util.ArrayList;


public class WeatherComponent extends Composite<Div> {

    private Label title;


    private static final Logger logger = Logger.getLogger(WeatherComponent.class);

    /**
     * Лист наименований городов
     */
    private WeatherService weatherService = new WWOWeatherService();

    private final ArrayList<String> places = new ArrayList<>();

    private final ComboBox<String> citiesBox = new ComboBox<>();

    private TodayWeather todayWeather = new TodayWeather();
    private TomorrowWeather tomorrowWeather = new TomorrowWeather();
    private HorizontalLayout weatherTable = new HorizontalLayout();
    private VerticalLayout verticalLayout = new VerticalLayout();
    private Button update;

    private Label errorLabel = new Label("Сервис недоступен");

    public WeatherComponent() {}

    public WeatherComponent(MainView view) {
        errorLabel.getStyle().set("font-size", "18pt");
        getContent().setClassName("my-weather");
        citiesBox.setItems(places);

        places.add("Москва");
        places.add("Санкт-Петербург");
        places.add("Новосибирск");

        citiesBox.setValue(places.get(0));
        citiesBox.addValueChangeListener(e -> {
            if(citiesBox.isEmpty())
                citiesBox.setValue("Новосибирск");
            update_info();
            view.updateTime();
        });


        title = new Label("Погода");
        title.setClassName("weather-title");

        update = new Button("Обновить");
        update.setClassName("weather-button");

        update.addClickListener(e -> {
            update_info();
            view.updateTime();
        });

        update_info();
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
    }

    private void update_info() {
        try {
            Weather weather = weatherService.getWeatherData(CitiesHelper.getCity(citiesBox.getValue()));

            logger.info("Обновление сегодняшней погоды");
            todayWeather.update(weather);

            logger.info("Обновление завтрашней погоды");
            tomorrowWeather.update(weather);

            verticalLayout.removeAll();
            weatherTable.add(todayWeather, tomorrowWeather);
            verticalLayout.add(title, citiesBox, weatherTable, update);
        } catch (UserException e) {
            logger.info(e.getMessage());
            verticalLayout.removeAll();
            verticalLayout.add(title, errorLabel, update);
            verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, errorLabel);

            CustomNotification notification = new CustomNotification(e.getMessage());
            notification.show();
        }

    }
}