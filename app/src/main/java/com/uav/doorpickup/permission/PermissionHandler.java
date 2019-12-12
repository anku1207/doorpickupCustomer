package com.uav.doorpickup.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionHandler {

    public static String permissiontype;
    public static Context context1;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    static String[] mPermission = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.READ_CONTACTS};


    public static void checkpermission(Context context) {
        context1=context;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(context, mPermission[0])
                        != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[1])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[2])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[3])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[4])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[5])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[6])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[7])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[8])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[9])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[10])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[11])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[12])
                                != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, mPermission[13])
                                != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, mPermission, 100);
                    // If any permission aboe not allowed by user, this condition will execute every tim, else your else part will work
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static boolean checkpermissionmessage(Context context) {
        context1=context;
        int permissionSendMessage = ContextCompat.checkSelfPermission(context1,
                Manifest.permission.SEND_SMS);
        int receiveSMS = ContextCompat.checkSelfPermission(context1, Manifest.permission.RECEIVE_SMS);
        int readSMS = ContextCompat.checkSelfPermission(context1, Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context1,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;

    }

    public static boolean filedownloadandread(Context context) {
        context1=context;
        int permissionINTERNET = ContextCompat.checkSelfPermission(context1,Manifest.permission.INTERNET);
        int permissionWRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(context1, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionINTERNET != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (permissionWRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context1,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    public static boolean contactpermission(Context context) {
        context1=context;

        int receiveREAD_PHONE_STATE = ContextCompat.checkSelfPermission(context1, Manifest.permission.READ_CONTACTS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (receiveREAD_PHONE_STATE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context1,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
