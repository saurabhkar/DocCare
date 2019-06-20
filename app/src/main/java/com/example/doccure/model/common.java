package com.example.doccure.model;

public class common {
    public static final int TIME_SLOT_TOTAL = 20;
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static Barber currentBarber;
    public static final String KEY_DISPLAY_TIME_SLOT="DISPLAY_TIME_SLOT";

    public static String convertTimeSlotToString(int slot) {
        switch (slot) {
            case 1:
                return "10:00-10:05";
            case 2:
                return "10:05-10:10";

            case 3:
                return "10:10-10:15";

            case 4:
                return "10:20-10:25";

            case 5:
                return "10:00-10:05";

            case 6:
                return "10:00-10:05";

            case 7:
                return "10:00-10:05";

            case 8:
                return "10:00-10:05";

            case 9:
                return "10:00-10:05";

            case 10:
                return "10:00-10:05";

            case 11:
                return "10:00-10:05";

            case 12:
                return "10:00-10:05";
            default:
                return "closed";

        }
    }
}