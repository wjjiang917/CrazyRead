package me.crazyjiang.crazyread.model.http;

import java.util.List;
import java.util.Map;

import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.model.bean.NetEaseVideoBean;
import me.crazyjiang.crazyread.model.bean.StartImageBean;
import me.crazyjiang.crazyread.model.bean.ZhiHuNewsBean;
import me.crazyjiang.crazyread.model.http.api.NetEaseApi;
import me.crazyjiang.crazyread.model.http.api.ZhiHuApi;
import rx.Observable;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */

public class ApiService {
    private ZhiHuApi zhiHuApi;
    private NetEaseApi netEaseApi;

    public ApiService(ZhiHuApi zhiHuApi, NetEaseApi netEaseApi) {
        this.zhiHuApi = zhiHuApi;
        this.netEaseApi = netEaseApi;
    }

    public Observable<StartImageBean> fetchStartImage(String res) {
        return zhiHuApi.getStartImage(res);
    }

    public Observable<DailyStoriesBean> fetchLatestNews() {
        return zhiHuApi.getLatestNews();
    }

    public Observable<DailyStoriesBean> fetchNews(String date) {
        return zhiHuApi.getNews(date);
    }

    public Observable<ZhiHuNewsBean> fetchNewsDetail(Integer id) {
        return zhiHuApi.getNewsDetail(id);
    }

    public Observable<Map<String, List<NetEaseVideoBean>>> fetchVideoList(String categoryId, int startPage) {
        return netEaseApi.getVideoList(categoryId, startPage);
    }
}
