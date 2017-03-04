package me.crazyjiang.crazyread.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import me.crazyjiang.crazyread.common.RxUtil;
import me.crazyjiang.crazyread.model.bean.WelcomeBean;
import me.crazyjiang.crazyread.model.http.ApiService;
import me.crazyjiang.crazyread.presenter.contract.SplashContract;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {
    private static final String RES = "1080*1776";
    private static final long DELAY = 2000L;

    private ApiService mApiService;

    @Inject
    public SplashPresenter(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void getWelcomeData() {
        addSubscribe(mApiService.fetchWelcomeInfo(RES)
                .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
                .subscribe(new Action1<WelcomeBean>() {
                    @Override
                    public void call(WelcomeBean welcomeBean) {
                        mView.showContent(welcomeBean);
                        startCountDown();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.jumpToMain();
                    }
                }));
    }

    /**
     * Go to MainActivity after 2s
     */
    private void startCountDown() {
        addSubscribe(Observable.timer(DELAY, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.jumpToMain();
                    }
                }));
    }
}
