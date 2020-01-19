package mx.com.baseapplication;

import android.app.Application;

import ua.at.tsvetkov.util.ComponentLog;

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //enable loggin
        ComponentLog.enableComponentsChangesLogging(this);

    }


}
