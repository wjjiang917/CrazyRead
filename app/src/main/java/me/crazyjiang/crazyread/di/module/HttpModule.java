package me.crazyjiang.crazyread.di.module;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.crazyjiang.crazyread.App;
import me.crazyjiang.crazyread.BuildConfig;
import me.crazyjiang.crazyread.common.Constant;
import me.crazyjiang.crazyread.di.qualifier.ServiceType;
import me.crazyjiang.crazyread.model.http.api.NetEaseApi;
import me.crazyjiang.crazyread.model.http.api.ZhiHuApi;
import me.crazyjiang.crazyread.util.FileUtil;
import me.crazyjiang.crazyread.util.PhoneUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
@Module
public class HttpModule {
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @ServiceType("ZhiHu")
    Retrofit provideZhiHuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhiHuApi.HOST);
    }

    @Singleton
    @Provides
    ZhiHuApi provideZhiHuService(@ServiceType("ZhiHu") Retrofit retrofit) {
        return retrofit.create(ZhiHuApi.class);
    }

    @Singleton
    @Provides
    @ServiceType("NetEase")
    Retrofit provideNetEaseRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, NetEaseApi.HOST);
    }

    @Singleton
    @Provides
    NetEaseApi provideNetEaseService(@ServiceType("NetEase") Retrofit retrofit) {
        return retrofit.create(NetEaseApi.class);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!PhoneUtil.isNetworkConnected(App.getInstance())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (PhoneUtil.isNetworkConnected(App.getInstance())) {
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + 0)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);

        File cacheDir = new File(FileUtil.getDiskCacheDir(App.getInstance()) + File.pathSeparator + Constant.CACHE_NET);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }

        if (cacheDir.exists()) {
            Cache cache = new Cache(cacheDir, 1024 * 1024 * 50); // 50M
            builder.cache(cache);
        }

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
