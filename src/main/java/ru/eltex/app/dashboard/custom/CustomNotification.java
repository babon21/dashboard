package ru.eltex.app.dashboard.entity;

import com.vaadin.flow.component.notification.Notification;

public class CustomNotification {
    private Notification notification;


    public CustomNotification(String message) {
        notification = new Notification();
        notification.setDuration(3000);

        notification.setText(message);
        notification.setPosition(Notification.Position.MIDDLE);
    }

    public void show() {
        notification.open();
    }
}
