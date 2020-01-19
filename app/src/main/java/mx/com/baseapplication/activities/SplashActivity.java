package mx.com.baseapplication.activities;


import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import mx.com.baseapplication.BaseActivity;
import mx.com.baseapplication.MainActivity;
import mx.com.baseapplication.R;
import mx.com.baseapplication.model.GenericalError;
import mx.com.baseapplication.utils.NetHelper;


public class SplashActivity extends BaseActivity implements NetHelper.OnDataResultInterface {

    public static final String TAG = SplashActivity.class.getSimpleName();
    private static final long SPLASH_SCREEN_DELAY = 2000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Thread.currentThread()
                        .setName(this.getClass().getSimpleName() + ": " + Thread.currentThread().getName());


                gotoActivity(MainActivity.class,true);




            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);





    } // Fin onCreate()




    @Override
    public void onBackPressed() {
    }


    @Override
    public void OnResultOk(Object object) {
        gotoActivity(MainActivity.class,true);
    }

    @Override
    public void OnError(GenericalError error) {
        gotoActivity(MainActivity.class,true);

    }
}
