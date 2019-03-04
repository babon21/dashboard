package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.MainView;
import ru.mail.dmitrii.entity.Weather;
import ru.mail.dmitrii.service.WeatherService;
import ru.mail.dmitrii.util.CitiesHelper;

import java.util.ArrayList;

public class WeatherComponent extends Composite<Div> {
    private Label title;
    final String DEGREE = "\u00b0";

    /**
     * Лист наименований городов
     */
    private final ArrayList<String> places = new ArrayList<>();

    private final ComboBox<String> citiesBox = new ComboBox<>();

    private Label currentWeather;
    private Label tomorrowWeather = new Label();
    private Label tomorrowMin = new Label();
    private Label tomorrowMax = new Label();

    public WeatherComponent() {}

    public WeatherComponent(MainView view) {

        getContent().setClassName("my-weather");

        citiesBox.setItems(places);
        places.add("Москва");
        places.add("Санкт-Петербург");
        places.add("Новосибирск");

        citiesBox.setValue(places.get(0));

        citiesBox.addValueChangeListener(e -> {
            update_info();
            view.updateTime();
        });

        title = new Label("Погода");
        title.setClassName("kek");
        currentWeather = new Label();
        update_info();
        Button update = new Button("Обновить");
        update.setClassName("");


        update.addClickListener(e -> {
            update_info();
            view.updateTime();
        });

        VerticalLayout verticalLayout = new VerticalLayout(title, citiesBox,
                currentWeather, tomorrowWeather, tomorrowMin, tomorrowMax, update);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);

    }

    private void update_info() {
        Weather data = WeatherService.getWeatherData(CitiesHelper.getCity(citiesBox.getValue()));
        currentWeather.setText("Температура текущая:" + data.getTemp_C() + DEGREE + "C");
        tomorrowWeather.setText("Прогноз на завтра");
        tomorrowMin.setText("Минимум: " + data.getMin_C() + DEGREE + "C");
        tomorrowMax.setText("Максимум: " + data.getMax_C() + DEGREE + "C");
    }
}