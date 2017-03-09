package me.crazyjiang.crazyread.model.http.api;

import java.util.List;
import java.util.Map;

import me.crazyjiang.crazyread.model.bean.NetEaseVideoBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public interface NetEaseApi {
    String HOST = "http://c.m.163.com/";

    /**
     * ZhiHu start image
     * HTTP ERROR 500
     */
    @GET("nc/video/list/{categoryId}/n/{startPage}-10.html")
    @Headers("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
    Observable<Map<String, List<NetEaseVideoBean>>> getVideoList(@Path("categoryId") String categoryId, @Path("startPage") int startPage);
}
