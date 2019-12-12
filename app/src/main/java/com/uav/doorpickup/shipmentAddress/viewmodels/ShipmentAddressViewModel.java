package com.uav.doorpickup.shipmentAddress.viewmodels;

import android.content.Context;

import com.google.gson.Gson;
import com.uav.doorpickup.bo.BookingBO;
import com.uav.doorpickup.bo.PincodeBO;
import com.uav.doorpickup.bookingRequest.model.BookingModel;
import com.uav.doorpickup.constant.GlobalApplication;
import com.uav.doorpickup.override.ViewModelWrapper;
import com.uav.doorpickup.shipmentAddress.model.ShipmentAddressModel;
import com.uav.doorpickup.vo.CityVO;
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

public class ShipmentAddressViewModel {
    Gson gson=new Gson();


    public  void getCityByPincode(Context context, String pincode, ViewModelWrapper success ) throws Exception{
            HashMap<String, Object> params = new HashMap<String, Object>();
            ConnectionVO connectionVO = PincodeBO.getCityByPincode();
            ShipmentAddressModel shipmentAddressModel1 =new ShipmentAddressModel();
            shipmentAddressModel1.setAnonymousString(pincode);
            String json = gson.toJson(shipmentAddressModel1);
            params.put("volley",json);
            connectionVO.setParams(params);

            VolleyUtils.makeJsonObjectRequest(context,connectionVO, new VolleyResponseListener() {
                @Override
                public void onError(String message) {

                }
                @Override
                public void onResponse(Object resp) throws JSONException {

                    JSONObject response = (JSONObject) resp;
                    CityVO cityVO = gson.fromJson(response.toString(), CityVO.class);

                    if(cityVO.getStatusCode().equals("400")){
                        ArrayList error = (ArrayList) cityVO.getErrorMsgs();
                        StringBuilder sb = new StringBuilder();
                        for(int i=0; i<error.size(); i++){
                            sb.append(error.get(i)).append("\n");
                        }
                        success.onError(sb.toString());
                    }else {
                        success.onSuccess(cityVO);
                    }
                }
            });
    }




    public static void bookingUpdateAddress(Context context, ShipmentAddressModel addressModel, ViewModelWrapper success ) throws Exception{
        HashMap<String, Object> params = new HashMap<String, Object>();
        ConnectionVO connectionVO = BookingBO.bookingUpdateAddress();
        ShipmentAddressModel shipmentAddressModel =new ShipmentAddressModel();

        shipmentAddressModel.setBookingId(GlobalApplication.getBookingId());

        shipmentAddressModel.setConsigneeName(addressModel.getConsigneeName());
        shipmentAddressModel.setConsigneeMobile(addressModel.getConsigneeMobile());
        shipmentAddressModel.setConsigneeAddress(addressModel.getConsigneeAddress());
        shipmentAddressModel.setConsigneelandMark(addressModel.getConsigneelandMark());
        shipmentAddressModel.setConsigneePincode(addressModel.getConsigneePincode());


        shipmentAddressModel.setConsignorName(addressModel.getConsignorName());
        shipmentAddressModel.setConsignorMobile(addressModel.getConsignorMobile());
        shipmentAddressModel.setConsignorAddress(addressModel.getConsignorAddress());
        shipmentAddressModel.setConsignorlandMark(addressModel.getConsignorlandMark());
        shipmentAddressModel.setConsignorPincode(addressModel.getConsignorPincode());

        String json = new Gson().toJson(shipmentAddressModel);
        params.put("volley",json);
        connectionVO.setParams(params);

        VolleyUtils.makeJsonObjectRequest(context,connectionVO, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }
            @Override
            public void onResponse(Object resp) throws JSONException {

                JSONObject response = (JSONObject) resp;
                ShipmentAddressModel shipmentAddressModel = new Gson().fromJson(response.toString(), ShipmentAddressModel.class);

                if(shipmentAddressModel.getStatusCode().equals("400")){
                    ArrayList error = (ArrayList) shipmentAddressModel.getErrorMsgs();
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<error.size(); i++){
                        sb.append(error.get(i)).append("\n");
                    }
                    success.onError(sb.toString());
                }else {
                    success.onSuccess(shipmentAddressModel);
                }
            }
        });
    }

}
