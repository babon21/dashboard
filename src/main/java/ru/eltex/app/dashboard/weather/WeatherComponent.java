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
import ru.eltex.app.dashboard.util.WeatherHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * UI компонент, отвещающий за отображение прогноза погоды
 * @author darhzain
 */
public class WeatherComponent extends Composite<Div> {


    /** Заголовок компонента */
    private Label title;

    private static final Logger logger = Logger.getLogger(WeatherComponent.class);

    /** Сервис получения прогноза погоды */
    private static WeatherService weatherService = new WWOWeatherService();

    private final ComboBox<String> citiesBox = new ComboBox<>();

    /** UI компонент, отображающий информацию о погоде на завтра */
    private TodayWeather todayWeather = new TodayWeather();

    /** UI компонент, отображающий информацию о погоде на сегодня */
    private TomorrowWeather tomorrowWeather = new TomorrowWeather();

    /** Layout, содержащий информацию о погоде */
    private HorizontalLayout weatherTable = new HorizontalLayout();

    /** Основной Layout компонента */
    private VerticalLayout verticalLayout = new VerticalLayout();

    private Button update;

    /** Надпись о недоступности сервиса.
     *  Выводится в случае отсутствия интернета,
     *  или недоступности удаленного сервиса
     * */
    private Label errorLabel = new Label("Сервис недоступен");

    private static final String ERROR_MESSAGE = "Ошибка запроса курса валюты";


    public WeatherComponent() {}

    public WeatherComponent(MainView view) {
        ArrayList<String> places = new ArrayList<>();
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

    /**
     * Обновление информации о погоде,
     * вызывается по кнопке Обновить и в конструкторе.
     * Выводит соответствующую информацию в компонент, в случае
     * недоступности удаленного сервиса, выводит уведомление
     */
    private void update_info() {
        try {
            Weather weather = weatherService.getWeatherData(CitiesHelper.getCity(citiesBox.getValue()));
            updateUI(weather);
        } catch (IllegalArgumentException e) {
            noticeUser("Ошибка, Api сервиса был изменен", "Ошибка, Api сервиса был изменен");
            logger.error("Failed!", e);
        } catch (IOException e) {
            noticeUser(ERROR_MESSAGE, "Сервис недоступен");
            logger.error("Failed!", e);
        } catch (Exception e) {
            noticeUser("Ошибка", "Сервис недоступен");
            logger.error("Failed!", e);
        }
    }

    private void updateUI(Weather weather) {
        logger.info("Обновление сегодняшней погоды");
        todayWeather.update(weather);

        logger.info("Обновление завтрашней погоды");
        tomorrowWeather.update(weather);

        verticalLayout.removeAll();
        weatherTable.add(todayWeather, tomorrowWeather);
        verticalLayout.add(title, citiesBox, weatherTable, update);
    }

    private void noticeUser(String notice, String errorText) {
        verticalLayout.removeAll();
        errorLabel.setText(errorText);
        verticalLayout.add(title, errorLabel, update);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, errorLabel);

        CustomNotification notification = new CustomNotification(notice);
        notification.show();
    }
}