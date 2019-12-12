package com.uav.doorpickup.constant;

import android.app.Application;

public class GlobalApplication extends Application {

    public static Integer bookingId;

    public static Integer getBookingId() {
        return bookingId;
    }

    public static void setBookingId(Integer bookingId) {
        GlobalApplication.bookingId = bookingId;
    }
}
