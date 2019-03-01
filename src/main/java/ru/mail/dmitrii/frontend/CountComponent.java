package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CountComponent extends Composite<Div> {

    private Label countLabel;
    public CountComponent() {
        getContent().setClassName("my-count");

        countLabel = new Label("Счетчик посещений");
        VerticalLayout verticalLayout = new VerticalLayout(countLabel);

        getContent().add(verticalLayout);
    }
}
