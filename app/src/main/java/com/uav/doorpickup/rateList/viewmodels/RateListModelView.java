package com.uav.doorpickup.rateList.viewmodels;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.uav.doorpickup.bo.BookingBO;
import com.uav.doorpickup.bookingRequest.model.BookingModel;
import com.uav.doorpickup.constant.GlobalApplication;
import com.uav.doorpickup.override.ViewModelWrapper;
import com.uav.doorpickup.rateList.model.RateListModel;
import com.uav.doorpickup.vo.AgencyVO;
import com.uav.doorpickup.vo.ConnectionVO;
import com.uav.doorpickup.vo.LastmileVO;
import com.uav.doorpickup.vo.ServiceTypeVO;
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

public class RateListModelView {
    Gson gson=new Gson();

    public static void saveShipmentBooking(Context context, RateListModel rateListModel, ViewModelWrapper success ){

        HashMap<String, Object> params = new HashMap<String, Object>();
        ConnectionVO connectionVO = BookingBO.bookingUpdateLastMile();
        RateListModel rateListModel1=new RateListModel();

        rateListModel1.setTat(rateListModel.getTat());
        rateListModel1.setAmount(rateListModel.getAmount());

        LastmileVO lastmileVO =new LastmileVO();
        lastmileVO.setLastMileId(rateListModel.getLastmile().getLastMileId());
        rateListModel1.setLastmile(lastmileVO);

        ServiceTypeVO serviceTypeVO =new ServiceTypeVO();
        serviceTypeVO.setServiceTypeId(rateListModel.getServiceType().getServiceTypeId());
        rateListModel1.setServiceType(serviceTypeVO);

        AgencyVO agencyVO =new AgencyVO();
        agencyVO.setAgencyId(rateListModel.getAgency().getAgencyId());
        rateListModel1.setAgency(agencyVO);

        rateListModel1.setBookingId(GlobalApplication.getBookingId());

        String json = new Gson().toJson(rateListModel1);

        Log.w("request_save",json);
        params.put("volley",json);
        connectionVO.setParams(params);

        VolleyUtils.makeJsonObjectRequest(context,connectionVO, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }
            @Override
            public void onResponse(Object resp) throws JSONException {

                JSONObject response = (JSONObject) resp;
                RateListModel rateListModel = new Gson().fromJson(response.toString(),RateListModel.class);

                if(rateListModel.getStatusCode().equals("400")){
                    ArrayList error = (ArrayList) rateListModel.getErrorMsgs();
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<error.size(); i++){
                        sb.append(error.get(i)).append("\n");
                    }
                    success.onError(sb.toString());
                }else {
                    success.onSuccess(rateListModel);
                }
            }
        });
    }
}
