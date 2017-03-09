package me.crazyjiang.crazyread.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.crazyjiang.crazyread.App;
import me.crazyjiang.crazyread.model.http.ApiService;
import me.crazyjiang.crazyread.model.http.api.NetEaseApi;
import me.crazyjiang.crazyread.model.http.api.ZhiHuApi;

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
    ApiService provideApiService(ZhiHuApi zhiHuApi, NetEaseApi netEaseApi) {
        return new ApiService(zhiHuApi, netEaseApi);
    }
}
