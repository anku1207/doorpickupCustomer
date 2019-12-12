package com.uav.doorpickup.rateList.model;





import android.databinding.Bindable;

import com.uav.doorpickup.BR;
import com.uav.doorpickup.vo.AgencyVO;
import com.uav.doorpickup.vo.BaseVO;
import com.uav.doorpickup.vo.LastmileVO;
import com.uav.doorpickup.vo.ServiceTypeVO;

public class RateListModel  extends BaseVO {
    private Integer bookingId;
    private Integer amount;
    private ServiceTypeVO serviceType;
    private LastmileVO lastmile;
    private AgencyVO agency;
    private String forwarderLogo;
    private String tat;



    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Bindable
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);
    }

    public ServiceTypeVO getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeVO serviceType) {
        this.serviceType = serviceType;
    }

    @Bindable
    public String getForwarderLogo() {
        return forwarderLogo;
    }

    public void setForwarderLogo(String forwarderLogo) {
        this.forwarderLogo = forwarderLogo;
        notifyPropertyChanged(BR.forwarderLogo);
    }

    @Bindable
    public String getTat() {
        return tat;
    }

    public void setTat(String tat) {
        this.tat = tat;
        notifyPropertyChanged(BR.tat);
    }


    public LastmileVO getLastmile() {
        return lastmile;
    }

    public void setLastmile(LastmileVO lastmile) {
        this.lastmile = lastmile;
    }

    public AgencyVO getAgency() {
        return agency;
    }

    public void setAgency(AgencyVO agency) {
        this.agency = agency;
    }
}
