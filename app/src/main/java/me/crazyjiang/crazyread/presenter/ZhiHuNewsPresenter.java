package me.crazyjiang.crazyread.presenter;

import javax.inject.Inject;

import me.crazyjiang.crazyread.common.RxUtil;
import me.crazyjiang.crazyread.model.bean.ZhiHuNewsBean;
import me.crazyjiang.crazyread.model.http.ApiService;
import me.crazyjiang.crazyread.presenter.contract.ZhiHuNewsContract;
import me.crazyjiang.crazyread.util.Logger;
import rx.functions.Action1;

/**
 * Created by Jiangwenjin on 2017/3/8.
 */

public class ZhiHuNewsPresenter extends RxPresenter<ZhiHuNewsContract.View> implements ZhiHuNewsContract.Presenter {
    @Inject
    public ZhiHuNewsPresenter(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void getNewsDetail(Integer id) {
        addSubscribe(mApiService.fetchNewsDetail(id)
                .compose(RxUtil.<ZhiHuNewsBean>rxSchedulerHelper())
                .subscribe(new Action1<ZhiHuNewsBean>() {
                    @Override
                    public void call(ZhiHuNewsBean zhiHuNewsBean) {
                        mView.onNewsDetailLoaded(zhiHuNewsBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e("", throwable);
                    }
                }));
    }
}
