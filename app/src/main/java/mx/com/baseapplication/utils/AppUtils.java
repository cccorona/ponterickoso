package mx.com.baseapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;



import mx.com.baseapplication.R;



public class AppUtils {


    public static String SHARED_PATH = "ST";




    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public static void showGenericMessge(Context context,String title ,String message){


            new SmartDialogBuilder(context)
                    .setTitle(title)
                    .setSubTitle(message)
                    .setCancalable(false)

                    .setNegativeButtonHide(true) //hide cancel button
                    .setPositiveButton("OK", new SmartDialogClickListener() {
                        @Override
                        public void onClick(SmartDialog smartDialog) {

                            smartDialog.dismiss();
                        }
                    }).setNegativeButton("Cancel", new SmartDialogClickListener() {
                @Override
                public void onClick(SmartDialog smartDialog) {
                    smartDialog.dismiss();

                }
            }).build().show();

        
    }

    public static void showGenericMessge(Context context,String message){


        new SmartDialogBuilder(context)
                .setTitle(context.getString(R.string.app_name))
                .setSubTitle(message)
                .setCancalable(false)

                .setNegativeButtonHide(true) //hide cancel button
                .setPositiveButton("OK", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {

                        smartDialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                smartDialog.dismiss();

            }
        }).build().show();


    }


    public static void noInternet(Context context,String message){


        new SmartDialogBuilder(context)
                .setTitle(context.getString(R.string.app_name))
                .setSubTitle(context.getString(R.string.no_internet))
                .setCancalable(false)

                .setNegativeButtonHide(true) //hide cancel button
                .setPositiveButton("OK", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {

                        smartDialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                smartDialog.dismiss();

            }
        }).build().show();


    }



    public static void saveObjectToSharedPreference(Context context, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PATH, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PATH, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }





}
