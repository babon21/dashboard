package ru.eltex.app.dashboard.counter;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.custom.CustomNotification;
import ru.eltex.app.dashboard.exception.UserException;


public class CountComponent extends Composite<Div> {

    private static final Logger LOGGER = Logger.getLogger(CountComponent.class);

    private CountService countService = new MongoCountService();

    public CountComponent() {
        getContent().setClassName("my-count");

        Label title = new Label("Счетчик посещений");
        title.setClassName("count-title");

        try {
            int count = countService.getCount();

            Label countLabel = new Label(  "" + count);
            VerticalLayout countLayout = new VerticalLayout(countLabel);
            countLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            countLayout.setClassName("count-layout");
            countLayout.setWidth("60%");

            VerticalLayout verticalLayout = new VerticalLayout(title, countLayout);
            verticalLayout.setSizeFull();
            verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            getContent().add(verticalLayout);
        } catch (UserException e) {
            CustomNotification notification = new CustomNotification(e.getMessage());
            notification.show();
            LOGGER.info(e.getMessage());
        }

    }
}
