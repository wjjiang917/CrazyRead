package me.crazyjiang.crazyread;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;

import me.crazyjiang.crazyread.di.component.AppComponent;
import me.crazyjiang.crazyread.di.component.DaggerAppComponent;
import me.crazyjiang.crazyread.di.module.AppModule;
import me.crazyjiang.crazyread.di.module.HttpModule;
import me.crazyjiang.crazyread.di.module.PageModule;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
public class App extends Application {
    private static App mInstance;
    public static AppComponent appComponent;

    public static synchronized App getInstance() {
        return mInstance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        LeakCanary.install(this);
        BlockCanary.install(this, new BlockCanaryContext()).start();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mInstance))
                    .httpModule(new HttpModule())
                    .pageModule(new PageModule())
                    .build();
        }
        return appComponent;
    }
}
