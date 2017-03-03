package me.crazyjiang.crazyread.presenter;

import me.crazyjiang.crazyread.ui.BaseView;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */

public interface BasePresenter <T extends BaseView> {
    void attachView(T view);
    void detachView();
}
