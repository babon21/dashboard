package ru.eltex.app.dashboard.counter;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.eltex.app.dashboard.custom.CustomNotification;


/**
 * UI компонент, отвещающий за отображение счетчика посещений страницы
 * @author darhzain
 */
public class CountComponent extends Composite<Div> {

    /** Сервис получения счетчика посещений */
    private static CountService countService = new MongoCountService();


    public CountComponent() {
        getContent().setClassName("my-count");

        Label title = new Label("Счетчик посещений");
        title.setClassName("count-title");

        int count = countService.getCount();
        if (count != -1) {
            Label countLabel = new Label(  "" + count);
            VerticalLayout countLayout = new VerticalLayout(countLabel);
            countLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            countLayout.setClassName("count-layout");
            countLayout.setWidth("60%");

            VerticalLayout verticalLayout = new VerticalLayout(title, countLayout);
            verticalLayout.setSizeFull();
            verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            getContent().add(verticalLayout);
        } else {
            CustomNotification notification = new CustomNotification("Ошибка получения счетчика посещений");
            notification.show();
        }

    }
}
