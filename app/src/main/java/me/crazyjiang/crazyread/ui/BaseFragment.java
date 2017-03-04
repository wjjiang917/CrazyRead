package me.crazyjiang.crazyread.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.crazyjiang.crazyread.App;
import me.crazyjiang.crazyread.di.component.DaggerFragmentComponent;
import me.crazyjiang.crazyread.di.component.FragmentComponent;
import me.crazyjiang.crazyread.di.module.FragmentModule;
import me.crazyjiang.crazyread.presenter.BasePresenter;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Jiangwenjin on 2017/3/4.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {
    @Inject
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnbinder;
    protected boolean isInit = false;

    protected abstract int getLayoutResId();

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutResId(), null);
        inject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mUnbinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
            if (!isHidden()) {
                isInit = true;
                init();
            }
//        } else {
//            if (!isSupportHidden()) {
//                isInit = true;
//                init();
//            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInit && !hidden) {
            isInit = true;
            init();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    protected abstract void inject();

    protected abstract void init();
}
