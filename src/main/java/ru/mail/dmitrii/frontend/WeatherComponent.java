package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.MainView;
import ru.mail.dmitrii.entity.Weather;
import ru.mail.dmitrii.service.WeatherService;
import ru.mail.dmitrii.util.CitiesHelper;

import java.util.ArrayList;

import static ru.mail.dmitrii.util.WeatherHelper.DEGREE;

public class WeatherComponent extends Composite<Div> {
    private Label title;


    /**
     * Лист наименований городов
     */
    private final ArrayList<String> places = new ArrayList<>();

    private final ComboBox<String> citiesBox = new ComboBox<>();


    private Label tomorrowMin = new Label();
    private Label tomorrowMax = new Label();

    private TodayWeather todayWeather = new TodayWeather();
    private TomorrowWeather tomorrowWeather = new TomorrowWeather();;

    public WeatherComponent() {}

    public WeatherComponent(MainView view) {
        HorizontalLayout weatherTable = new HorizontalLayout(todayWeather, tomorrowWeather);
        
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
        update_info();
        Button update = new Button("Обновить");
        update.setClassName("weather-button");

        update.addClickListener(e -> {
            update_info();
            view.updateTime();
        });

        VerticalLayout verticalLayout = new VerticalLayout(title, citiesBox, weatherTable,
                update);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
    }

    private void update_info() {
        Weather weather = WeatherService.getWeatherData(CitiesHelper.getCity(citiesBox.getValue()));
        todayWeather.update(weather);
        tomorrowWeather.update(weather);
    }
}