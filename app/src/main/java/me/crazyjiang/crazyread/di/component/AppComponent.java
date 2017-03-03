package me.crazyjiang.crazyread.di.component;

import javax.inject.Singleton;

import dagger.Component;
import me.crazyjiang.crazyread.App;
import me.crazyjiang.crazyread.di.module.AppModule;
import me.crazyjiang.crazyread.di.module.HttpModule;
import me.crazyjiang.crazyread.di.module.PageModule;
import me.crazyjiang.crazyread.model.http.RetrofitHelper;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class, PageModule.class})
public interface AppComponent {
    App getContext();

    RetrofitHelper retrofitHelper();
}
