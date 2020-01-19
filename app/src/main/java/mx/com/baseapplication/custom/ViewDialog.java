package mx.com.baseapplication.custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.github.loadingview.LoadingView;

import mx.com.baseapplication.R;


public class ViewDialog {

    Activity activity;
    Dialog dialog;
    Context context;
    LoadingView loadingView;


    public ViewDialog(Activity activity) {
        this.activity = activity;
    }


    public ViewDialog(Context context) {
        this.activity = (Activity) context;
    }



    public void showDialog() {

        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(dialog.getWindow()!=null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        }
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading);





        dialog.show();
        loadingView = dialog.findViewById(R.id.loadingView);


    }


    public void start(){
        loadingView.start();
    }

    public void stop(){
        loadingView.stop();
    }



    public void hideDialog(){
        dialog.dismiss();
    }

}