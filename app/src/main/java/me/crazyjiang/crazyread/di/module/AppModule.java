package me.crazyjiang.crazyread.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.crazyjiang.crazyread.App;
import me.crazyjiang.crazyread.model.http.RetrofitHelper;
import me.crazyjiang.crazyread.model.http.api.ZhihuApis;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(ZhihuApis zhihuApiService) {
        return new RetrofitHelper(zhihuApiService);
    }
}
