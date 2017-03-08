package me.crazyjiang.crazyread.presenter;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;

import javax.inject.Inject;

import me.crazyjiang.crazyread.common.RxBus;
import me.crazyjiang.crazyread.common.RxUtil;
import me.crazyjiang.crazyread.model.bean.DailyStoriesBean;
import me.crazyjiang.crazyread.model.http.ApiService;
import me.crazyjiang.crazyread.presenter.contract.DailyContract;
import me.crazyjiang.crazyread.util.DateUtil;
import me.crazyjiang.crazyread.util.Logger;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */

public class DailyPresenter extends RxPresenter<DailyContract.View> implements DailyContract.Presenter {

    @Inject
    public DailyPresenter(final ApiService apiService) {
        this.mApiService = apiService;

        // change date
        addSubscribe(RxBus.getInstance().toObservable(CalendarDay.class)
                .subscribeOn(Schedulers.io())
                .map(new Func1<CalendarDay, String>() { // CalendarDay to String
                    @Override
                    public String call(CalendarDay calendarDay) {
                        // 查询历史数据，需要往后加一天
                        Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
                        calendar.setTime(calendarDay.getDate());
                        calendar.add(Calendar.DATE, +1); // 得到前一天
                        return DateUtil.getDate(calendar.getTime(), "yyyyMMdd");
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if (s.equals(DateUtil.getTomorrow("yyyyMMdd"))) {  // max date: today (can not get future data)
                            getLatestNews();
                            return false;
                        }
                        return true;
                    }
                })
                .observeOn(Schedulers.io())   // io Thread to get data from network
                .flatMap(new Func1<String, Observable<DailyStoriesBean>>() {
                    @Override
                    public Observable<DailyStoriesBean> call(String date) {
                        return mApiService.fetchNews(date);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())    // change UI on ui thread
                .subscribe(new Action1<DailyStoriesBean>() {
                    @Override
                    public void call(DailyStoriesBean dailyStoriesBean) {
                        mView.onNewsLoaded(dailyStoriesBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e("", throwable);
                    }
                }));
    }

    @Override
    public void getLatestNews() {
        addSubscribe(mApiService.fetchLatestNews()
                .compose(RxUtil.<DailyStoriesBean>rxSchedulerHelper())
                .subscribe(new Action1<DailyStoriesBean>() {
                    @Override
                    public void call(DailyStoriesBean dailyStoriesBean) {
                        mView.onNewsLoaded(dailyStoriesBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e("", throwable);
                    }
                }));
    }
}
