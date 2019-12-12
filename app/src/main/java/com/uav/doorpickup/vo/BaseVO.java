package com.uav.doorpickup.vo;

import android.databinding.BaseObservable;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

public class BaseVO extends BaseObservable {

    private String statusCode;
    private List<String> errorMsgs;
    private String anonymousString;
    private Integer otpExpiredMobile;
    private Integer otpExpiredEmail;
    private String actionname;
    private String image;
    private Integer anonymousInteger;
    private String localCache;
    private String enachDetails;
    private CityVO destinationCity;
    private CityVO pickupCity;






    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getErrorMsgs() {
        return errorMsgs;
    }

    public void setErrorMsgs(List<String> errorMsgs) {
        this.errorMsgs = errorMsgs;
    }

    public String getAnonymousString() {
        return anonymousString;
    }

    public void setAnonymousString(String anonymousString) {
        this.anonymousString = anonymousString;
    }

    public Integer getOtpExpiredMobile() {
        return otpExpiredMobile;
    }

    public void setOtpExpiredMobile(Integer otpExpiredMobile) {
        this.otpExpiredMobile = otpExpiredMobile;
    }

    public Integer getOtpExpiredEmail() {
        return otpExpiredEmail;
    }

    public void setOtpExpiredEmail(Integer otpExpiredEmail) {
        this.otpExpiredEmail = otpExpiredEmail;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAnonymousInteger() {
        return anonymousInteger;
    }

    public void setAnonymousInteger(Integer anonymousInteger) {
        this.anonymousInteger = anonymousInteger;
    }

    public String getLocalCache() {
        return localCache;
    }

    public void setLocalCache(String localCache) {
        this.localCache = localCache;
    }

    public String getEnachDetails() {
        return enachDetails;
    }

    public void setEnachDetails(String enachDetails) {
        this.enachDetails = enachDetails;
    }

    public CityVO getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(CityVO destinationCity) {
        this.destinationCity = destinationCity;
    }

    public CityVO getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(CityVO pickupCity) {
        this.pickupCity = pickupCity;
    }

    public static void invokeAllSetters(Object setter, Object getter){
        try {
            Class<?> noparams[] = {};
            Method[] allSetterMethods = setter.getClass().getDeclaredMethods();

            for (Method setMethod : allSetterMethods) {
                if (setMethod.getName().startsWith("set")) {
                    String field = "get"+setMethod.getName().substring(3, setMethod.getName().length());
                    Method getMethod = getter.getClass().getMethod(field);
                    Object val = getMethod.invoke( getter,noparams);
                    setMethod.invoke(setter, val);
                }
            }
        }catch (Exception e){
            Log.w("Excep", e.getMessage());
        }
    }
}
