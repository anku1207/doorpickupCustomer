package com.uav.doorpickup.vo;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class LastmileVO extends BaseVO{

    private Integer lastMileId;
    private String name;

    public Integer getLastMileId() {
        return lastMileId;
    }

    public void setLastMileId(Integer lastMileId) {
        this.lastMileId = lastMileId;
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
