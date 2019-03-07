package ru.mail.dmitrii.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.mail.dmitrii.service.CountService;

public class CountComponent extends Composite<Div> {

    public CountComponent() {
        getContent().setClassName("my-count");



        Label title = new Label("Счетчик посещений");
        title.setClassName("count-title");
        Label countLabel = new Label(  "" + CountService.getCount());
        VerticalLayout countLayout = new VerticalLayout(countLabel);
        countLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        countLayout.setClassName("count-layout");
        countLayout.setWidth("60%");

        VerticalLayout verticalLayout = new VerticalLayout(title, countLayout);
        verticalLayout.setSizeFull();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        getContent().add(verticalLayout);
    }
}
