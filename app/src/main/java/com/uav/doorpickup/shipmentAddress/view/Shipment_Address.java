package com.uav.doorpickup.shipmentAddress.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.uav.doorpickup.R;
import com.uav.doorpickup.bookingRequest.model.BookingModel;
import com.uav.doorpickup.bookingRequest.viewmodels.BookingRequestModelView;
import com.uav.doorpickup.databinding.ActivityShipmentAddressBinding;
import com.uav.doorpickup.interfaces.CustomClickListener;
import com.uav.doorpickup.override.TextWatcherWrapper;
import com.uav.doorpickup.override.ViewModelWrapper;
import com.uav.doorpickup.rateList.view.RateList;
import com.uav.doorpickup.shipmentAddress.model.ShipmentAddressModel;
import com.uav.doorpickup.shipmentAddress.viewmodels.ShipmentAddressViewModel;
import com.uav.doorpickup.util.Utility;
import com.uav.doorpickup.vo.CityVO;

public class Shipment_Address extends AppCompatActivity {
    public  ActivityShipmentAddressBinding binding;
    BookingModel bookingModel;
    ShipmentAddressModel shipmentAddressModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment__address);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shipment__address);
        binding.setShipmentmodel(new ShipmentAddressModel());
        binding.executePendingBindings();

        shipmentAddressModel=binding.getShipmentmodel();

        bookingModel=(BookingModel)getIntent().getSerializableExtra("bookingmodel");
        shipmentAddressModel.setConsigneePincode(bookingModel.getDestinationPincode());
        shipmentAddressModel.setConsignorPincode(bookingModel.getPickupPincode());

        afterTextChanged(binding.cpincode);
        afterTextChanged(binding.ppincode);



        binding.setItemClickListener(new CustomClickListener((view,model) ->{
            if(view.getId()==binding.addaddress.getId()){
                validateEditText();
            }
        }));
    }


    public  void afterTextChanged(EditText et){
        if(et.getId()==binding.cpincode.getId()){
            et.addTextChangedListener( new TextWatcherWrapper(s ->{
                if(s.toString().length()>=6){
                    getConsigneeCityByPincode(Shipment_Address.this,bookingModel.getDestinationPincode(),binding.ccity,binding.cstate);
                }
            }));
        }else if(et.getId()==binding.ppincode.getId()){
            et.addTextChangedListener( new TextWatcherWrapper(s ->{
                if(s.toString().length()>=6){
                    getConsigneeCityByPincode(Shipment_Address.this,bookingModel.getPickupPincode(),binding.pcity,binding.pstate);
                }
            }));
        }
    }

    public  void getConsigneeCityByPincode(Context context , String pincode,EditText city,EditText state){
        try {
            ShipmentAddressViewModel shipmentAddressViewModel =new ShipmentAddressViewModel();
            shipmentAddressViewModel.getCityByPincode(context, pincode,new ViewModelWrapper((ViewModelWrapper.OnSuccess)(s)->{
                CityVO cityVO =(CityVO)s;
                city.setText(cityVO.getCityName());
                state.setText(cityVO.getStateRegion().getStateRegionName());
            }, (ViewModelWrapper.OnError)(s)->{
                city.setText(null);
                state.setText(null);
                Utility.showSingleButtonDialog(context,"Error !",s,false);
                // bm.invokeAllSetters(  bm,  gson.fromJson(object.toString(), BookingModel.class));
            }));
        }catch (Exception e){
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    public void validateEditText(){
        try {
            boolean isValid = true;
            isValid = setErrorMsg(shipmentAddressModel.getConsigneeName(),binding.consigneename,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsigneeMobile(),binding.cmobileno,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsigneeAddress(),binding.caddress,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsigneelandMark(),binding.clandmark,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsigneePincode(),binding.cpincode,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsigneeCity(),binding.ccity,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsigneeState(),binding.cstate,"Required",isValid);


            isValid = setErrorMsg(shipmentAddressModel.getConsignorName(),binding.pname,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsignorMobile(),binding.pmobileno,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsignorAddress(),binding.paddress,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsignorlandMark(),binding.plandmark,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsignorPincode(),binding.ppincode,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsignorPincode(),binding.pcity,"Required",isValid);
            isValid = setErrorMsg(shipmentAddressModel.getConsignorState(),binding.pstate,"Required",isValid);

            if(!isValid)return;


            ShipmentAddressViewModel.bookingUpdateAddress(this,shipmentAddressModel,new ViewModelWrapper((ViewModelWrapper.OnSuccess)(s)->{

            },(ViewModelWrapper.OnError)(e)->{

            }));

        }catch (Exception e){
            Log.w("error",e.getMessage());
        }
    }


    private static boolean setErrorMsg(String value, EditText et, String txt, boolean isValid){
        if(value == null || value.equals("")) {
            et.setError(txt);
            isValid=false;
        }
        if(!isValid) {
            return false;
        }
        return true;
    }






}
