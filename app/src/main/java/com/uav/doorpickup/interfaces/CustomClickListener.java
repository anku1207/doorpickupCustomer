package com.uav.doorpickup.interfaces;

import android.text.Editable;
import android.view.View;

public class CustomClickListener {
    CustomClick customClick;
    Object object;

    public CustomClickListener(CustomClick customClick) {
        this.customClick = customClick;
    }

    public void CustomClick(View view,Object obj){
        this.object=obj;
        customClick.Click(view,obj);
    }

    public interface CustomClick{
      void Click(View view, Object f);
    }

}
