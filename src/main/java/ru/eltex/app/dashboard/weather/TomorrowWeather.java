package ru.eltex.app.dashboard.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.eltex.app.dashboard.entity.Weather;
import ru.eltex.app.dashboard.util.WeatherHelper;

public class TomorrowWeather extends Composite<Div> {

    private Label afternoonTemp = new Label();
    private Label afternoonDesc = new Label();

    private Label eveningTemp = new Label();
    private Label eveningDesc = new Label();

    private Label min = new Label();
    private Label max = new Label();

    public TomorrowWeather() {
        Label title = new Label("Завтра");
        title.setClassName("tomorrow-title");
        Label afternoonTitle = new Label("День");
        Label eveningTitle = new Label("Вечер");

        afternoonTemp.setClassName("tomorrow-temp");
        eveningTemp.setClassName("tomorrow-temp");
        afternoonDesc.setClassName("tomorrow-desc");
        eveningDesc.setClassName("tomorrow-desc");
        getContent().setClassName("tomorrow-weather");

        VerticalLayout afternoonLayout = new VerticalLayout(afternoonTitle, afternoonTemp,
                afternoonDesc);
        afternoonLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        afternoonLayout.setClassName("tomorrow-layout");
        VerticalLayout eveningLayout = new VerticalLayout(eveningTitle, eveningTemp,
                eveningDesc);

        eveningLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        eveningLayout.setClassName("tomorrow-layout");


        HorizontalLayout horizontalLayout = new HorizontalLayout(afternoonLayout, eveningLayout);

        horizontalLayout.setClassName("tomorrow-layout");
        horizontalLayout.setSizeFull();
        HorizontalLayout minLayout = new HorizontalLayout(min);
        minLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        minLayout.setSizeFull();

        HorizontalLayout maxLaout = new HorizontalLayout(max);
        maxLaout.setSizeFull();
        maxLaout.setAlignItems(FlexComponent.Alignment.CENTER);
        HorizontalLayout minMax = new HorizontalLayout(title, minLayout, maxLaout);
        minMax.setClassName("minMax");

        VerticalLayout verticalLayout = new VerticalLayout(minMax, horizontalLayout);
        verticalLayout.setSizeFull();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        getContent().add(verticalLayout);

    }

    public void update(Weather weather) {
        afternoonTemp.setText(weather.getAfternoon() + WeatherHelper.DEGREE + "C");
        afternoonDesc.setText(weather.getAfternoonDesc());
        eveningTemp.setText(weather.getEvening() + WeatherHelper.DEGREE + "C");
        eveningDesc.setText(weather.getEveningDesc());
        min.setText("Мин " + weather.getMinC() + WeatherHelper.DEGREE + "C");
        max.setText("Макс " + weather.getMaxC() + WeatherHelper.DEGREE + "C");
    }
}
