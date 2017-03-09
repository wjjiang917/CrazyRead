package me.crazyjiang.crazyread.presenter;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.crazyjiang.crazyread.common.RxUtil;
import me.crazyjiang.crazyread.model.bean.NetEaseVideoBean;
import me.crazyjiang.crazyread.model.http.ApiService;
import me.crazyjiang.crazyread.presenter.contract.NetEaseVideosContract;
import me.crazyjiang.crazyread.util.Logger;
import rx.functions.Action1;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public class NetEaseVideosPresenter extends RxPresenter<NetEaseVideosContract.View> implements NetEaseVideosContract.Presenter {
    @Inject
    public NetEaseVideosPresenter(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void getVideoList(String categoryId, int startPage) {
        addSubscribe(mApiService.fetchVideoList(categoryId, startPage)
                .compose(RxUtil.<Map<String, List<NetEaseVideoBean>>>rxSchedulerHelper())
                .subscribe(new Action1<Map<String, List<NetEaseVideoBean>>>() {
                    @Override
                    public void call(Map<String, List<NetEaseVideoBean>> videos) {
                        mView.onVideosLoaded(videos);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e("", throwable);
                    }
                }));
    }
}
