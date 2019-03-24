package phannguyen.com.trackingsystem;

import android.app.Application;

import phannguyen.com.trackingsystem.ui.parse.ParseHelper;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseHelper.ParseInit(this);
    }
}
