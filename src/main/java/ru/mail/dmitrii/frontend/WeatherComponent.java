package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.entity.Weather;
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
        currentWeather = new Label();
        tomorrowWeather = new Label();
        update_info();
        Button update = new Button("Обновить");


        update.addClickListener(e -> update_info());

        VerticalLayout verticalLayout = new VerticalLayout(title, citiesBox,
                currentWeather, tomorrowWeather, update);
        getContent().add(verticalLayout);
    }

    private void update_info() {
        Weather data = WeatherService.getWeatherData("Novosibirsk");
        currentWeather.setText("Температура текущая:" + data.getTemp_C());
        tomorrowWeather.setText("Прогноз на завтра:" +
                "\tМинимум: " + data.getMin_C() + "C" +
                "\tМаксимум: " + data.getMax_C() + "C");
    }
}