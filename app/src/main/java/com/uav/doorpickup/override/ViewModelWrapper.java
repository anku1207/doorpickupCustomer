package com.uav.doorpickup.override;

import android.text.Editable;

public class ViewModelWrapper  implements  IViewModel{
    private OnSuccess onSuccess;
    private OnError onError;

    public ViewModelWrapper(OnSuccess onSuccess, OnError onError){
        this.onSuccess  = onSuccess;
        this.onError = onError;
    }

    @Override
    public void onSuccess(Object s){
        onSuccess.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        onError.onError(s);
    }

    public interface OnSuccess {
        void onSuccess(Object s);
    }

    public interface OnError {
        void onError(String s);
    }


}
