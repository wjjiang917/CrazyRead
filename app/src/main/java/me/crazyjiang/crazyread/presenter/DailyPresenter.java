package me.crazyjiang.crazyread.presenter;

import javax.inject.Inject;

import me.crazyjiang.crazyread.common.RxUtil;
import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.model.http.ApiService;
import me.crazyjiang.crazyread.presenter.contract.DailyContract;
import me.crazyjiang.crazyread.util.Logger;
import rx.functions.Action1;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    @Inject
    public DailyPresenter(ApiService apiService) {
        this.mApiService = apiService;

        // change date

    }

    @Override
    public void getLatestNews() {
        addSubscribe(mApiService.fetchLatestNews()
                .compose(RxUtil.<DailyStoriesBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyStoriesBean>() {
                    @Override
                    public void call(DailyStoriesBean dailyStoriesBean) {
                        mView.onLatestNewsLoaded(dailyStoriesBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e("", throwable);
                    }
                }));
    }
}
