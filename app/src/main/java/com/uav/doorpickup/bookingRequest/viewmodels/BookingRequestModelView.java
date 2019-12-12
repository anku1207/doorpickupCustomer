package com.uav.doorpickup.bookingRequest.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;

import com.google.gson.Gson;
import com.uav.doorpickup.bo.BookingBO;
import com.uav.doorpickup.bookingRequest.model.BookingModel;
import com.uav.doorpickup.bookingRequest.view.BookingRequest;
import com.uav.doorpickup.databinding.ActivityBookingRequestBinding;
import com.uav.doorpickup.override.ViewModelWrapper;

import com.uav.doorpickup.vo.ConnectionVO;
import com.uav.doorpickup.volley.VolleyResponseListener;
import com.uav.doorpickup.volley.VolleyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BookingRequestModelView extends BaseObservable {

    Gson gson=new Gson();


    ActivityBookingRequestBinding activityBookingRequestBinding;
    BookingRequest bookingRequest;
    BookingModel bookingModel;
    public  BookingRequestModelView(ActivityBookingRequestBinding activityBookingRequestBinding, BookingRequest bookingRequest){
        this.activityBookingRequestBinding = activityBookingRequestBinding;
        this.bookingRequest= bookingRequest;
        this.bookingModel = activityBookingRequestBinding.getBookingmodel();
    }

    public BookingRequestModelView() {
    }



    public  void getRateForServer(Context context, BookingModel bookingModel, ViewModelWrapper success ) throws Exception{

            HashMap<String, Object> params = new HashMap<String, Object>();
            ConnectionVO connectionVO = BookingBO.bookingCreate();
            BookingModel bookingModel1 =new BookingModel();
            bookingModel1.setPickupPincode(bookingModel.getPickupPincode());
            bookingModel1.setDestinationPincode(bookingModel.getDestinationPincode());
            bookingModel1.setWeight(Double.parseDouble(bookingModel.getEleWeight()));

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Timestamp ts = new Timestamp(((Date)df.parse(bookingModel.getAnonymousDate())).getTime());
            bookingModel1.setPickupDate(ts.getTime());

            String json = gson.toJson(bookingModel1);
            params.put("volley",json);
            connectionVO.setParams(params);

            VolleyUtils.makeJsonObjectRequest(context,connectionVO, new VolleyResponseListener() {
                @Override
                public void onError(String message) {

                }
                @Override
                public void onResponse(Object resp) throws JSONException {

                    JSONObject response = (JSONObject) resp;
                    Gson gson = new Gson();
                    BookingModel bookingModel = gson.fromJson(response.toString(), BookingModel.class);


                    if(bookingModel.getStatusCode().equals("400")){
                        ArrayList error = (ArrayList) bookingModel.getErrorMsgs();
                        StringBuilder sb = new StringBuilder();
                        for(int i=0; i<error.size(); i++){
                            sb.append(error.get(i)).append("\n");
                        }
                        success.onError(sb.toString());
                    }else {
                        success.onSuccess(bookingModel);
                    }
                }
            });


    }

}
