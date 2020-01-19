package mx.com.baseapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import mx.com.baseapplication.custom.ViewDialog;


public class BaseActivity extends AppCompatActivity {



    private boolean backEnabled;
    private ViewDialog viewDialog;
    private  boolean isShowing = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backEnabled = true;

        viewDialog = new ViewDialog(BaseActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



    }



    public void enableNavigation(String name) {

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView title = findViewById(R.id.title_label);
        title.setText(name);
    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }


    public void gotoActivity(Class clase,boolean finish){
        startActivity(new Intent(this,clase));
        if(finish){
            finish();

        }
    }


    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isShowing) {
                    viewDialog.showDialog();
                    viewDialog.start();

                    isShowing = true;
                }
            }
        });


    }


    public void hideProgress() {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isShowing) {
                    viewDialog.hideDialog();
                    viewDialog.stop();

                    isShowing = false;

                }
            }
        });


    }


    public void setBackEnabled(boolean backEnabled) {
        this.backEnabled = backEnabled;
    }

    @Override
    public void onBackPressed() {

        if(backEnabled){
            super.onBackPressed();
        }else{
            moveTaskToBack(true);
        }

    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }


}
