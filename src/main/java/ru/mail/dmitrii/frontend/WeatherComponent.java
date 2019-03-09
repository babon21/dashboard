package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.log4j.Logger;
import ru.mail.dmitrii.MainView;
import ru.mail.dmitrii.entity.CustomNotification;
import ru.mail.dmitrii.entity.Weather;
import ru.mail.dmitrii.exception.UserException;
import ru.mail.dmitrii.service.WeatherService;
import ru.mail.dmitrii.util.CitiesHelper;

import java.util.ArrayList;


public class WeatherComponent extends Composite<Div> {

    private Label title;


    private static final Logger LOGGER = Logger.getLogger(WeatherComponent.class);

    /**
     * Лист наименований городов
     */
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
            Weather weather = WeatherService.getWeatherData(CitiesHelper.getCity(citiesBox.getValue()));

            LOGGER.info("Начало обновления сегодняшней погоды");
            todayWeather.update(weather);
            LOGGER.info("Конец обновления сегодняшней погоды");

            LOGGER.info("Начало обновления завтрашней погоды");
            tomorrowWeather.update(weather);
            LOGGER.info("Конец обновления завтрашней погоды");

            verticalLayout.removeAll();
            weatherTable.add(todayWeather, tomorrowWeather);
            verticalLayout.add(title, citiesBox, weatherTable, update);
        } catch (UserException e) {
            LOGGER.info(e.getMessage());
            verticalLayout.removeAll();
            verticalLayout.add(title, errorLabel, update);
            verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, errorLabel);

            CustomNotification notification = new CustomNotification(e.getMessage());
            notification.show();
        }

    }
}