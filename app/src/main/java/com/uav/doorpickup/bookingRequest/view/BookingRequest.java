package com.uav.doorpickup.bookingRequest.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uav.doorpickup.R;
import com.uav.doorpickup.activity.Base_Activity;
import com.uav.doorpickup.bookingRequest.model.BookingModel;
import com.uav.doorpickup.bookingRequest.viewmodels.BookingRequestModelView;
import com.uav.doorpickup.constant.GlobalApplication;
import com.uav.doorpickup.databinding.ActivityBookingRequestBinding;
import com.uav.doorpickup.implementations.BindElement;
import com.uav.doorpickup.interfaces.UAVDialogInterface;
import com.uav.doorpickup.override.TextWatcherWrapper;
import com.uav.doorpickup.override.ITextWatcherInterface;
import com.uav.doorpickup.override.ViewModelWrapper;
import com.uav.doorpickup.rateList.view.RateList;
import com.uav.doorpickup.util.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookingRequest extends Base_Activity {

    public static ActivityBookingRequestBinding activityBinding;
    static Gson gson =new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking__request);
        activityBinding.setBookingmodel(new BookingModel());
        activityBinding.setBookingRequestModelView(new BookingRequestModelView(activityBinding,this));
        activityBinding.executePendingBindings();


        activityBinding.pickupDate.setKeyListener(null);
        getRate(activityBinding.bookingReceived);
        setPickupDateTouchListener(activityBinding.pickupDate);
        afterTextChanged(activityBinding.pickupPincode);
        afterTextChanged(activityBinding.destinationPincode);
        afterTextChanged(activityBinding.eleweight);
        setOnFocusChangeListener(activityBinding.eleweight);
    }



    public static void setPickupDateTouchListener(EditText editText){
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    EditText pickup_date = ((EditText)view);
                    switch (view.getId()){
                        case R.id.pickup_date:
                            if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                                Calendar c = Calendar.getInstance();
                                if(!pickup_date.getText().toString().equals("")){
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                                    c.setTime(sdf.parse(pickup_date.getText().toString()));
                                }
                                DatePickerDialog datePickerDialog = BindElement.bindCalendar(view.getContext(), pickup_date, c);
                                Date bookingDate = new Date();
                                datePickerDialog.getDatePicker().setMinDate(bookingDate.getTime()+ 86400000);
                                datePickerDialog.show();
                            }
                            break;
                    }
                }catch (Exception e){
                    Toast.makeText(view.getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }



    public static void setOnFocusChangeListener(final EditText text) {

        text.setOnFocusChangeListener( new EditText.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){}
                activityBinding.getBookingmodel().setEleShowWeight(!text.getText().toString().equals("")? Utility.decimalFormat.format(Double.valueOf(text.getText().toString())) + " Kg" :"");

            }
        });

    }





    public static void afterTextChanged(final EditText et){


        if(et.getId()==activityBinding.pickupPincode.getId()){
            et.addTextChangedListener( new TextWatcherWrapper(s ->{
                setFocus(et, activityBinding.destinationPincode);
            }));

        }else if(et.getId() == activityBinding.destinationPincode.getId()){
            et.addTextChangedListener( new TextWatcherWrapper(s ->{
                setFocus(et, activityBinding.eleweight);
            }));

        }else if(et.getId() == activityBinding.eleweight.getId()){
            et.addTextChangedListener( new TextWatcherWrapper(s ->{
                weightDecimalHandler(et, 3);
            }));
        }



    }


    public static void getRate(Button btn){

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    BookingModel bm = activityBinding.getBookingmodel();
                    boolean isValid = true;
                    isValid = setErrorMsg(bm.getPickupPincode(),activityBinding.pickupPincode,"Required",isValid);
                    isValid = setErrorMsg(bm.getDestinationPincode(),activityBinding.destinationPincode,"Required",isValid);
                    isValid = setErrorMsg(bm.getEleWeight(),activityBinding.eleweight,"Required",isValid);
                    isValid = setErrorMsg(bm.getAnonymousDate(),activityBinding.pickupDate,"Required",isValid);

                    if(!isValid)return;

                    JSONArray jsonArray=new JSONArray();
                    JSONObject object =new JSONObject();
                    object.put("key","Pickup Pincode");
                    object.put("value",bm.getPickupPincode());
                    jsonArray.put(object);

                    object =new JSONObject();
                    object.put("key","Destination Pincode");
                    object.put("value",bm.getDestinationPincode());
                    jsonArray.put(object);

                    object =new JSONObject();
                    object.put("key","Approx. Weight");
                    object.put("value",bm.getEleShowWeight());
                    jsonArray.put(object);

                    object =new JSONObject();
                    object.put("key","Pickup Date");
                    object.put("value",bm.getAnonymousDate());
                    jsonArray.put(object);


                    Utility.confirmationDialog(new UAVDialogInterface() {
                        @Override
                        public void confirm( Dialog dialog ) {
                            dialog.dismiss();

                            confirmationComplete(btn.getContext(),bm);

                        }

                        @Override
                        public void modify(Dialog dialog) {
                            dialog.dismiss();
                        }
                    },btn.getContext(),jsonArray,null,"Confirmation");

                }catch (Exception e){

                }
            }
        });
    }

    private static boolean setErrorMsg(String value, EditText et, String txt, boolean isValid){
        if(value == null || value.equals("")) {
            et.setError(txt);
            isValid=false;
        }
        if(!isValid) {
            return false;
        }
        return true;
    }
    public static void setFocus(EditText currEt, EditText nextEt){
        int maxLength = 0;
        for (InputFilter filter : currEt.getFilters()) {
            if (filter instanceof InputFilter.LengthFilter) {
                maxLength=((InputFilter.LengthFilter) filter).getMax();
            }
        }
        if(currEt.getText().toString().length() ==maxLength)  nextEt.requestFocus();
    }


    public static void weightDecimalHandler(EditText et,   int decimalPlaces) {
        String str = et.getText().toString();

        if(str.length()>1){
            String[] parts = str.split("\\.");
            if(parts.length>1 && parts[1].length()>decimalPlaces ) {
                activityBinding.getBookingmodel().setEleWeight(activityBinding.getBookingmodel().getEleWeight());
            }else{
                activityBinding.getBookingmodel().setEleWeight(str);
            }
        }else{
            activityBinding.getBookingmodel().setEleWeight(str);
        }
        activityBinding.getBookingmodel().setEleWeight(activityBinding.getBookingmodel().getEleWeight());
        et.setSelection(et.getText().toString().length());

    }

    public static void confirmationComplete(Context context ,BookingModel bm){
        try {
            BookingRequestModelView bookingRequestModelView =new BookingRequestModelView();
            bookingRequestModelView.getRateForServer(context, bm,new ViewModelWrapper((ViewModelWrapper.OnSuccess)(s)->{
                GlobalApplication.setBookingId(((BookingModel)(s)).getBookingId());
                Intent intent =new Intent(context,RateList.class);

                intent.putExtra("model",Utility.toJson(s));
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }, (ViewModelWrapper.OnError)(s)->{
                Utility.showSingleButtonDialog(context,"Error !",s,false);

                // bm.invokeAllSetters(  bm,  gson.fromJson(object.toString(), BookingModel.class));

            }));
        }catch (Exception e){
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }






}


