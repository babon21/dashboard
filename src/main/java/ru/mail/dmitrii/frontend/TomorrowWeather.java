package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TomorrowWeather extends Composite<Div> {

    private Label temp = new Label();
    public TomorrowWeather() {
        getContent().setClassName("tomorrow-weather");

        VerticalLayout verticalLayout = new VerticalLayout(temp);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
        verticalLayout.setSizeFull();
    }

    public void setTemperature(String temperature) {
        temp.setText(temperature);
    }
}
