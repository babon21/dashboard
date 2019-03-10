package ru.eltex.app.dashboard.custom;

import com.vaadin.flow.component.notification.Notification;

/**
 * Пользовательский Notification
 * @author darhzain
 */
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
