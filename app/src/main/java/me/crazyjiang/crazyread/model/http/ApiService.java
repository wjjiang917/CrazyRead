package me.crazyjiang.crazyread.model.http;

import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.model.bean.StartImageBean;
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

    public Observable<StartImageBean> fetchStartImage(String res) {
        return mZhiHuApiService.getStartImage(res);
    }

    public Observable<DailyStoriesBean> fetchLatestNews() {
        return mZhiHuApiService.getLatestNews();
    }

    public Observable<DailyStoriesBean> fetchNews(String date) {
        return mZhiHuApiService.getNews(date);
    }
}
