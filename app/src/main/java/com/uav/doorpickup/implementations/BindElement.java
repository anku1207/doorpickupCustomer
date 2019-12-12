package com.uav.doorpickup.implementations;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.uav.doorpickup.interfaces.IBindElement;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Calendar;

import static java.util.Objects.requireNonNull;

public class BindElement {

    public BindElement(){

    }

    public static void bind(Context context){

        try{

            Class<?> objectClass = requireNonNull(context).getClass();
            for (Field field: objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(IBindElement.class)) {
                    Object x =  context.getClass().getField ( field.getName());
                    x = ((Activity) context).findViewById(field.getAnnotation(IBindElement.class).value());
                    field.set(context,x);
                }
            }

        }catch (Exception e){
            Log.e("error",e.toString());
        }
    }


    public static DatePickerDialog bindCalendar(Context context, final EditText editText, Calendar cal){

        int mYear, mMonth, mDay, mHour, mMinute;

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        DecimalFormat df = new DecimalFormat("00");
                        int m=monthOfYear+1;
                        String month = df.format(m);

                        String day=df.format(dayOfMonth);
                        editText.setText(day + "/" + month + "/" +year);
                        editText.setError(null);

                        editText.setSelection(editText.getText().toString().length());
                    }
                }, mYear, mMonth, mDay);
                return datePickerDialog;
    }


}
