package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.entity.Weather;

import static ru.mail.dmitrii.util.WeatherHelper.DEGREE;

public class TodayWeather extends Composite<Div> {

    private Label curC = new Label();
    private Label feelsLikeC = new Label();
    private Label wind = new Label();
    private Label humidity = new Label();
    private Label pressure = new Label();
    private Label desc = new Label();

    public TodayWeather() {
        Label title = new Label("Сейчас");

        VerticalLayout now = new VerticalLayout(title, curC, desc);
        now.setAlignItems(FlexComponent.Alignment.CENTER);
        now.setClassName("now-layout");
        now.setWidth("40%");

        VerticalLayout extra = new VerticalLayout(feelsLikeC, pressure, humidity, wind);
        extra.setAlignItems(FlexComponent.Alignment.STRETCH);
        extra.setClassName("extra-layout");

        HorizontalLayout content = new HorizontalLayout(now, extra);

        //now.setSizeFull();
        //extra.setSizeFull();
        content.setSizeFull();

        desc.setClassName("desc");
        title.setClassName("weather-now");
        curC.setClassName("weather-cur");

        feelsLikeC.setClassName("feelLike");
        pressure.setClassName("weather-extra");
        humidity.setClassName("weather-extra");
        wind.setClassName("weather-extra");

        getContent().setClassName("today-weather");
        //VerticalLayout verticalLayout = new VerticalLayout(title, curC, feelsLikeC,
         //       wind, humidity, pressure);
        //verticalLayout.setSizeFull();
        getContent().add(content);
        //verticalLayout.setSizeFull();
    }

    public void update(Weather weather) {
        curC.setText(weather.getCurC() + DEGREE + "C");
        desc.setText(weather.getDesc());
        feelsLikeC.setText("Ощущается как: " + weather.getFeelsLikeC() + DEGREE + "C");
        wind.setText("ветер: " + weather.getWind() + " м/c");
        humidity.setText("влажность: " + weather.getHumidity() + "%");
        pressure.setText("давление: " + weather.getPressure() + " мм рт.ст.");
    }
}
