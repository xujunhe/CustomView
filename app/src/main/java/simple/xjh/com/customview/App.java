package simple.xjh.com.customview;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class App extends Application{
    private static final String TAG = "CustomView";
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG);
    }
}
