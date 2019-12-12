package com.uav.doorpickup.bookingRequest.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.android.databinding.library.baseAdapters.BR;
import com.uav.doorpickup.vo.BaseVO;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookingModel extends BaseVO implements Serializable {

    private String pickupPincode;
    private String destinationPincode;
    private Double weight;
    private Long pickupDate;
    private String anonymousDate;
    private String eleWeight;
    private String eleShowWeight;
    private Integer bookingId;







    public BookingModel() {

    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Bindable
    public String getPickupPincode() {
        return pickupPincode;
    }

    public void setPickupPincode(String pickupPincode) {
        this.pickupPincode = pickupPincode;
        notifyPropertyChanged(BR.pickupPincode);

    }

    @Bindable
    public String getDestinationPincode() {
        return destinationPincode;
    }

    public void setDestinationPincode(String destinationPincode) {
        this.destinationPincode = destinationPincode;
        notifyPropertyChanged(BR.destinationPincode);
    }




    public Double getWeight() {

       return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;

    }

    public Long getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Long pickupDate) {
        this.pickupDate = pickupDate;
    }

    @Bindable
    public String getEleWeight() {
        return eleWeight;
    }

    public void setEleWeight(String eleWeight) {
        this.eleWeight = eleWeight;
        try {
            setWeight(Double.parseDouble(eleWeight));
        }catch (NumberFormatException e){

        }
        notifyPropertyChanged(BR.eleWeight);
    }

    @Bindable
    public String getAnonymousDate() {
        return anonymousDate;
    }

    public void setAnonymousDate(String anonymousDate) {
        this.anonymousDate = anonymousDate;
        notifyPropertyChanged(BR.anonymousDate);
       try{
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        c.setTime(sdf.parse(anonymousDate));
        setPickupDate(c.getTimeInMillis());
       }catch (ParseException e){

       }

    }

    @Bindable
    public String getEleShowWeight() {
        return eleShowWeight;
    }

    public void setEleShowWeight(String eleShowWeight) {
        this.eleShowWeight = eleShowWeight;
        notifyPropertyChanged(BR.eleShowWeight);
    }





}
