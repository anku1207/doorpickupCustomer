package com.uav.doorpickup.rateList.view;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uav.doorpickup.R;
import com.uav.doorpickup.bookingRequest.model.BookingModel;
import com.uav.doorpickup.databinding.ActivityRateListBinding;
import com.uav.doorpickup.interfaces.CustomClickListener;
import com.uav.doorpickup.rateList.model.RateListModel;
import com.uav.doorpickup.rateList.viewmodels.RateListRecyclerView;
import com.uav.doorpickup.util.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateList extends AppCompatActivity  {
    public  ActivityRateListBinding activityBinding;
    static Gson gson =new Gson();
    List<RateListModel> rateListModels =new ArrayList<RateListModel>();
    BookingModel bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);


        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_rate_list);


        activityBinding.setItemClickListener(new CustomClickListener((CustomClickListener.CustomClick) (view,obj)->{
                if(view.getId()==activityBinding.editbtn.getId()){
                    finish();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }

        }));




        bm = (BookingModel) Utility.fromJson(getIntent().getStringExtra("model"),BookingModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activityBinding.recyclerview.setLayoutManager(linearLayoutManager);
        activityBinding.recyclerview.setNestedScrollingEnabled(true);
        activityBinding.recyclerview.setFocusable(false);

        try{
            JSONArray jsonArray=new JSONArray();
            JSONObject object =new JSONObject();
            object.put("key","Pickup Pincode");
            object.put("value",bm.getPickupPincode());
            jsonArray.put(object);

            object =new JSONObject();
            object.put("key","Pickup City");
            object.put("value",bm.getPickupCity().getCityName());
            jsonArray.put(object);

            object =new JSONObject();
            object.put("key","Destination Pincode");
            object.put("value",bm.getDestinationPincode());
            jsonArray.put(object);

            object =new JSONObject();
            object.put("key","Destination City");
            object.put("value",bm.getDestinationCity().getCityName());
            jsonArray.put(object);

            object =new JSONObject();
            object.put("key","Approx. Weight");
            object.put("value",bm.getWeight() +" Kg");
            jsonArray.put(object);

            object =new JSONObject();
            object.put("key","Pickup Date");
            object.put("value",Utility.convertDate2String(new Date(bm.getPickupDate()),"dd/MM/yyyy") );
            jsonArray.put(object);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                LinearLayout et = new LinearLayout(new ContextThemeWrapper(this,R.style.confirmation_dialog_layout));

                Typeface typeface = ResourcesCompat.getFont(this, R.font.poppinsmedium);


                TextView text = new TextView(new ContextThemeWrapper(this, R.style.confirmation_dialog_filed));
                text.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
                text.setText(jsonObject.getString("key"));
                text.setTypeface(typeface);
                text.setMaxLines(1);
                text.setEllipsize(TextUtils.TruncateAt.END);

                TextView text1 = new TextView(new ContextThemeWrapper(this, R.style.confirmation_dialog_filed));
                text1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,0));
                text1.setText(" : ");



                TextView value = new TextView(new ContextThemeWrapper(this, R.style.confirmation_dialog_value));
                value.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
                value.setText(jsonObject.getString("value"));
                value.setTypeface(typeface);

                et.addView(text);
                et.addView(text1);
                et.addView(value);
                activityBinding.mainlayout.addView(et);
            }


            JSONArray forwarder_rate_list=new JSONArray(bm.getAnonymousString());
            for(int i=0 ; i<forwarder_rate_list.length(); i++ ){
                JSONObject jsonObject =forwarder_rate_list.getJSONObject(i);
                RateListModel rateListModel = new Gson().fromJson(jsonObject.toString(), RateListModel.class);
                rateListModels.add(rateListModel);
            }
        }catch (Exception e){
            Log.w("error321",e.getMessage());
        }
        populateData(rateListModels);
    }

    private void populateData(List<RateListModel> rateListModels) {
        try {
            activityBinding.recyclerview.setAdapter(new RateListRecyclerView(this,rateListModels,bm));
        }catch (Exception e){

        }

   }
}
