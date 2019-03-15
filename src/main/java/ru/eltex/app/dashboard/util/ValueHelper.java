package ru.eltex.app.dashboard.util;

public class ValueHelper {
    public static boolean checkFloat(String s) {
        try {
            Float.parseFloat(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
