package com.uav.doorpickup.rateList.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.uav.doorpickup.BR;
import com.uav.doorpickup.R;
import com.uav.doorpickup.bookingRequest.model.BookingModel;
import com.uav.doorpickup.checkOut.view.Check_Out;
import com.uav.doorpickup.databinding.RateListRecyclerviewBinding;
import com.uav.doorpickup.interfaces.CustomClickListener;
import com.uav.doorpickup.override.ViewModelWrapper;
import com.uav.doorpickup.rateList.model.RateListModel;
import com.uav.doorpickup.rateList.view.RateList;
import com.uav.doorpickup.shipmentAddress.view.Shipment_Address;

import java.util.ArrayList;
import java.util.List;

public class RateListRecyclerView extends RecyclerView.Adapter<RateListRecyclerView.ViewHolder> {

   // private List<DataModel> dataModelList;
    private Context context;
    List<RateListModel>list =new ArrayList<>();
    BookingModel bookingModel;

    public RateListRecyclerView(Context ctx, List<RateListModel> list,BookingModel bookingModel) {
        //this.dataModelList = dataModelList;
        this.context = ctx;
        this.list=list;
        this.bookingModel=bookingModel;
    }

    @Override
    public RateListRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RateListRecyclerviewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.rate_list_recyclerview, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        RateListModel  rateListModel =list.get(position);
        holder.itemRowBinding.setRatemodel(rateListModel);
        holder.bind(rateListModel);
        holder.itemRowBinding.setItemClickListener(new CustomClickListener((CustomClickListener.CustomClick) (view,obj)->{
           try{
                RateListModelView.saveShipmentBooking(context,((RateListModel)(obj)),new ViewModelWrapper((ViewModelWrapper.OnSuccess)(s)->{
                    Intent intent = new Intent(context, Shipment_Address.class);
                    intent.putExtra("bookingmodel",bookingModel);
                    context.startActivity(intent);
                }, (ViewModelWrapper.OnError)(s)->{
                    Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
                }));
           }catch (Exception e){
              Log.w("error",e.getMessage());
           }

        }));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public RateListRecyclerviewBinding itemRowBinding;

        public ViewHolder(RateListRecyclerviewBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.ratemodel, obj);
            itemRowBinding.executePendingBindings();
        }


    }



    @BindingAdapter("app:image_url")
    public static void loadImage(ImageView imageView, String url){
        Picasso.with(imageView.getContext()).load(url).resize(200,200).into(imageView);
    }


}


