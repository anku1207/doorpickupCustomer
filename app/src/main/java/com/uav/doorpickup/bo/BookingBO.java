package com.uav.doorpickup.bo;



import com.uav.doorpickup.vo.ConnectionVO;

import java.io.Serializable;

public class BookingBO implements Serializable {

    public static ConnectionVO bookingCreate() {
        ConnectionVO connectionVO = new ConnectionVO();
        connectionVO.setMethodName("bookingCreate");
        connectionVO.setRequestType(ConnectionVO.REQUEST_POST);
        return connectionVO;
    }

    public static ConnectionVO bookingUpdateLastMile() {
        ConnectionVO connectionVO = new ConnectionVO();
        connectionVO.setMethodName("bookingUpdateLastMile");
        connectionVO.setRequestType(ConnectionVO.REQUEST_POST);
        return connectionVO;
    }


    public static ConnectionVO bookingUpdateAddress() {
        ConnectionVO connectionVO = new ConnectionVO();
        connectionVO.setMethodName("bookingUpdateAddress");
        connectionVO.setRequestType(ConnectionVO.REQUEST_POST);
        return connectionVO;
    }

}
