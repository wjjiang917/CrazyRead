package me.crazyjiang.crazyread.di.component;

import android.app.Activity;

import dagger.Component;
import me.crazyjiang.crazyread.di.module.ActivityModule;
import me.crazyjiang.crazyread.di.scope.ActivityScope;
import me.crazyjiang.crazyread.ui.main.activity.MainActivity;
import me.crazyjiang.crazyread.ui.main.activity.SplashActivity;
import me.crazyjiang.crazyread.ui.zhihu.activity.ZhiHuNewsActivity;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(SplashActivity splashActivity);

    void inject(MainActivity mainActivity);

    void inject(ZhiHuNewsActivity zhiHuNewsActivity);
}
