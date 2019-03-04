package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.entity.Weather;
import ru.mail.dmitrii.service.WeatherService;
import ru.mail.dmitrii.util.CitiesHelper;

import java.util.ArrayList;

public class WeatherComponent extends Composite<Div> {
    private Label title;

    /**
     * Лист наименований городов
     */
    private final ArrayList<String> places = new ArrayList<>();

    private final ComboBox<String> citiesBox = new ComboBox<>("Местоположение", places);

    private Label currentWeather;
    private Label tomorrowWeather;

    private WeatherService weatherService = new WeatherService();


    public WeatherComponent( ) {

        getContent().setClassName("my-weather");

        places.add("Москва");
        places.add("Санкт-Петербург");
        places.add("Новосибирск");

        citiesBox.setValue(places.get(0));

        citiesBox.addValueChangeListener(e -> {
            update_info();
        });

        title = new Label("Погода");
        title.setClassName("kek");
        currentWeather = new Label();
        tomorrowWeather = new Label();
        update_info();
        Button update = new Button("Обновить");


        update.addClickListener(e -> update_info());

        VerticalLayout verticalLayout = new VerticalLayout(title, citiesBox,
                currentWeather, tomorrowWeather, update);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);

    }

    private void update_info() {
        Weather data = WeatherService.getWeatherData(CitiesHelper.getCity(citiesBox.getValue()));
        currentWeather.setText("Температура текущая:" + data.getTemp_C());
        tomorrowWeather.setText("Прогноз на завтра:" +
                "\tМинимум: " + data.getMin_C() + "C" +
                "\tМаксимум: " + data.getMax_C() + "C");
    }
}