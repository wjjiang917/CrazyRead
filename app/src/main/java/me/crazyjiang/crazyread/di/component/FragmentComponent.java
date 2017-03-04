package me.crazyjiang.crazyread.di.component;

import android.app.Activity;

import dagger.Component;
import me.crazyjiang.crazyread.di.module.FragmentModule;
import me.crazyjiang.crazyread.di.scope.FragmentScope;
import me.crazyjiang.crazyread.ui.zhihu.fragment.DailyFragment;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(DailyFragment dailyFragment);
}
