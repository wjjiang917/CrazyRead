package me.crazyjiang.crazyread.model.http.api;

import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.model.bean.StartImageBean;
import me.crazyjiang.crazyread.model.bean.ZhiHuNewsBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
public interface ZhiHuApi {
    String HOST = "https://news-at.zhihu.com/api/4/";

    /**
     * ZhiHu start image
     * HTTP ERROR 500
     */
    @GET("start-image/{res}")
    Observable<StartImageBean> getStartImage(@Path("res") String res);

    /**
     * ZhiHu today news
     */
    @GET("news/latest")
    Observable<DailyStoriesBean> getLatestNews();

    @GET("news/before/{date}")
    Observable<DailyStoriesBean> getNews(@Path("date") String date);

    @GET("news/{id}")
    Observable<ZhiHuNewsBean> getNewsDetail(@Path("id") Integer id);
}
