package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.service.CountService;

public class CountComponent extends Composite<Div> {

    public CountComponent() {
        getContent().setClassName("my-count");

        Label title = new Label("Счетчик посещений");
        Label countLabel = new Label(  "" + CountService.getCount());
        VerticalLayout verticalLayout = new VerticalLayout(title, countLabel);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
    }
}
