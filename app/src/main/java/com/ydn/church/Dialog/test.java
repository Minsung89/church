package com.ydn.church.Dialog;

import android.app.TimePickerDialog;
import android.content.Context;

public class test extends TimePickerDialog {
    public test(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }


}
