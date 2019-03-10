package ru.eltex.app.dashboard.weather;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static ru.eltex.app.dashboard.util.WeatherHelper.DEGREE;

public class TodayWeather extends Composite<Div> {

    private Label title;

    private Label curC = new Label();
    private Label feelsLikeC = new Label();
    private Label wind = new Label();
    private Label humidity = new Label();
    private Label pressure = new Label();
    private Label desc = new Label();

    private VerticalLayout now;


    public TodayWeather() {
        title = new Label("Сейчас");

        now = new VerticalLayout(title, curC, desc);
        now.setAlignItems(FlexComponent.Alignment.CENTER);
        now.setClassName("now-layout");
        now.setSizeFull();

        VerticalLayout extra = new VerticalLayout(feelsLikeC, pressure, humidity, wind);
        extra.setClassName("extra-layout");

        HorizontalLayout content = new HorizontalLayout(now, extra);
        content.setSpacing(false);
        content.remove();
        content.setSizeFull();

        desc.setClassName("desc");
        title.setClassName("weather-now");
        curC.setClassName("weather-cur");

        feelsLikeC.setClassName("feelLike");
        pressure.setClassName("weather-extra");
        humidity.setClassName("weather-extra");
        wind.setClassName("weather-extra");

        getContent().setClassName("today-weather");
        getContent().add(content);
    }

    public void update(Weather weather) {
        curC.setText(weather.getCurC() + DEGREE + "C");
        desc.setText(weather.getDesc());
        feelsLikeC.setText("Ощущается " + weather.getFeelsLikeC() + DEGREE + "C");
        wind.setText("ветер     " + weather.getWind() + " м/c");
        humidity.setText("влажность       " + weather.getHumidity() + "%");
        pressure.setText("давление        " + weather.getPressure() + " мм");
    }
}
