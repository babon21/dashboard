package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.service.CountService;

public class CountComponent extends Composite<Div> {

    private Label countLabel;
    public CountComponent() {
        getContent().setClassName("my-count");

        countLabel = new Label("Счетчик посещений: " + CountService.getCount());
        VerticalLayout verticalLayout = new VerticalLayout(countLabel);
        verticalLayout.setSizeFull();
        getContent().add(verticalLayout);
    }
}
