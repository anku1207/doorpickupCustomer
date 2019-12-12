package com.uav.doorpickup.com.uav.doorpickup.override;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {
    private EditText current_text;
    private Integer length;
    private EditText next_ele;
    private TextWatcherInterface textWatcherInterface;
    public GenericTextWatcher(EditText current_text, Integer length, EditText next_ele, TextWatcherInterface textWatcherInterface) {
        this.current_text = current_text;
        this.length=length;
        this.next_ele=next_ele;
        this.textWatcherInterface=textWatcherInterface;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    public void afterTextChanged(Editable editable) {
        if(length!=null &&editable.length()>=length){
            textWatcherInterface.taskcomplete(current_text,next_ele);
        }

    }
}
