package me.crazyjiang.crazyread.model.http;

import me.crazyjiang.crazyread.model.bean.WelcomeBean;
import me.crazyjiang.crazyread.model.http.api.ZhiHuApi;
import rx.Observable;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */

public class ApiService {
    private ZhiHuApi mZhiHuApiService;

    public ApiService(ZhiHuApi api) {
        this.mZhiHuApiService = api;
    }

    public Observable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhiHuApiService.getWelcomeInfo(res);
    }
}
