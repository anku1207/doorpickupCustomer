package com.uav.doorpickup.vo;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class ServiceTypeVO extends BaseVO{

    private Integer serviceTypeId;
    private String name;

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }
}
