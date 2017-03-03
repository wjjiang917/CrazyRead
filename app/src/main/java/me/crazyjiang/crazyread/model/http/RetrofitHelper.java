package me.crazyjiang.crazyread.model.http;

import me.crazyjiang.crazyread.model.bean.WelcomeBean;
import me.crazyjiang.crazyread.model.http.api.ZhihuApis;
import rx.Observable;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */

public class RetrofitHelper {
    private ZhihuApis mZhihuApiService;

    public RetrofitHelper(ZhihuApis zhihuApiService) {
        this.mZhihuApiService = zhihuApiService;
    }

    public Observable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }
}
