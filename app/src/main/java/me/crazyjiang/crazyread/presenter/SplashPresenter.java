package me.crazyjiang.crazyread.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import me.crazyjiang.crazyread.common.RxUtil;
import me.crazyjiang.crazyread.model.bean.StartImageBean;
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

    @Inject
    public SplashPresenter(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void getWelcomeData() {
        addSubscribe(mApiService.fetchStartImage(RES)
                .compose(RxUtil.<StartImageBean>rxSchedulerHelper())
                .subscribe(new Action1<StartImageBean>() {
                    @Override
                    public void call(StartImageBean startImageBean) {
                        mView.onSuccess(startImageBean);
                        startCountDown();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onFinish();
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
                        mView.onFinish();
                    }
                }));
    }
}
