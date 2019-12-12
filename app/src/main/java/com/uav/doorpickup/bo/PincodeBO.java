package com.uav.doorpickup.bo;


import com.uav.doorpickup.vo.ConnectionVO;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

public class PincodeBO implements Serializable {

    public static ConnectionVO getCityByPincode() {
        ConnectionVO connectionVO = new ConnectionVO();
        connectionVO.setMethodName("getCityByPincode");
        connectionVO.setRequestType(ConnectionVO.REQUEST_POST);
        return connectionVO;
    }

}
