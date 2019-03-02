package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.service.WeatherService;

public class WeatherComponent extends Composite<Div> {
    private Label title;
    private ComboBox<String> citiesBox;
    private Label currentWeather;
    private Label tomorrowWeather;

    private WeatherService weatherService = new WeatherService();


    public WeatherComponent( ) {

        getContent().setClassName("my-weather");

        title = new Label("Погода");
        title.setClassName("kek");
        citiesBox = new ComboBox<>();
        currentWeather = new Label("Температура текущая: " );
        tomorrowWeather = new Label("Прогноз на завтра: ");
        Button update = new Button("Обновить");


        update.addClickListener(e -> {
            String data = weatherService.getWeatherData();
            currentWeather.setText("Температура текущая: " + data);
            tomorrowWeather.setText("Прогноз на завтра: " + data);
        });

        VerticalLayout verticalLayout = new VerticalLayout(title, citiesBox,
                currentWeather, tomorrowWeather, update);
        getContent().add(verticalLayout);
        //getContent().add(title, citiesBox, currentWeather, tomorrowWeather, update);
    }
}