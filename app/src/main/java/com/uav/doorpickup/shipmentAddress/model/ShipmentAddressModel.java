package com.uav.doorpickup.shipmentAddress.model;

import android.databinding.Bindable;


import com.uav.doorpickup.BR;
import com.uav.doorpickup.vo.BaseVO;

public class ShipmentAddressModel extends BaseVO {

    private Integer bookingId;

    //tranisent properties
    private Integer consigneeId;
    private String consigneeAddress;
    private String consigneeName;
    private String consigneeMobile;
    private String consigneeGmail;
    private String consigneePincode;
    private String consigneelandMark;
    private String consigneeCity;
    private String consigneeState;





    private Integer consignorId;
    private String consignorName;
    private String consignorAddress;
    private String consignorMobile;
    private String consignorGmail;
    private String consignorPincode;
    private String consignorlandMark;
    private String consignorCity;
    private String consignorState;





    public ShipmentAddressModel() {
    }


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Bindable
    public Integer getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Integer consigneeId) {
        this.consigneeId = consigneeId;
        notifyPropertyChanged(BR.consigneeId);
    }

    @Bindable
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
        notifyPropertyChanged(BR.consigneeAddress);
    }

    @Bindable
    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
        notifyPropertyChanged(BR.consigneeName);
    }

    @Bindable
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
        notifyPropertyChanged(BR.consigneeMobile);
    }

    @Bindable
    public String getConsigneeGmail() {
        return consigneeGmail;
    }

    public void setConsigneeGmail(String consigneeGmail) {
        this.consigneeGmail = consigneeGmail;
        notifyPropertyChanged(BR.consigneeGmail);
    }

    @Bindable
    public String getConsigneePincode() {
        return consigneePincode;
    }

    public void setConsigneePincode(String consigneePincode) {
        this.consigneePincode = consigneePincode;
        notifyPropertyChanged(BR.consigneePincode);
    }

    @Bindable
    public String getConsigneelandMark() {
        return consigneelandMark;
    }

    public void setConsigneelandMark(String consigneelandMark) {
        this.consigneelandMark = consigneelandMark;
        notifyPropertyChanged(BR.consigneelandMark);
    }

    @Bindable
    public Integer getConsignorId() {
        return consignorId;
    }

    public void setConsignorId(Integer consignorId) {
        this.consignorId = consignorId;
        notifyPropertyChanged(BR.consignorId);
    }


    @Bindable
    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
        notifyPropertyChanged(BR.consignorName);
    }


    @Bindable
    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress;
        notifyPropertyChanged(BR.consignorAddress);
    }


    @Bindable
    public String getConsignorMobile() {
        return consignorMobile;
    }

    public void setConsignorMobile(String consignorMobile) {
        this.consignorMobile = consignorMobile;
        notifyPropertyChanged(BR.consignorMobile);
    }


    @Bindable
    public String getConsignorGmail() {
        return consignorGmail;
    }

    public void setConsignorGmail(String consignorGmail) {
        this.consignorGmail = consignorGmail;
        notifyPropertyChanged(BR.consignorGmail);
    }


    @Bindable
    public String getConsignorPincode() {
        return consignorPincode;
    }

    public void setConsignorPincode(String consignorPincode) {
        this.consignorPincode = consignorPincode;
        notifyPropertyChanged(BR.consignorPincode);
    }

    @Bindable
    public String getConsignorlandMark() {
        return consignorlandMark;
    }

    public void setConsignorlandMark(String consignorlandMark) {
        this.consignorlandMark = consignorlandMark;
        notifyPropertyChanged(BR.consignorlandMark);
    }


    @Bindable
    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
        notifyPropertyChanged(BR.consigneeCity);
    }

    @Bindable
    public String getConsigneeState() {
        return consigneeState;
    }

    public void setConsigneeState(String consigneeState) {
        this.consigneeState = consigneeState;
        notifyPropertyChanged(BR.consigneeState);
    }

    @Bindable
    public String getConsignorCity() {
        return consignorCity;
    }

    public void setConsignorCity(String consignorCity) {
        this.consignorCity = consignorCity;
        notifyPropertyChanged(BR.consignorCity);
    }


    @Bindable
    public String getConsignorState() {
        return consignorState;
    }

    public void setConsignorState(String consignorState) {
        this.consignorState = consignorState;
        notifyPropertyChanged(BR.consignorState);
    }
}
