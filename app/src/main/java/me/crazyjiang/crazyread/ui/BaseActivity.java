package me.crazyjiang.crazyread.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.crazyjiang.crazyread.App;
import me.crazyjiang.crazyread.di.component.ActivityComponent;
import me.crazyjiang.crazyread.di.component.DaggerActivityComponent;
import me.crazyjiang.crazyread.di.module.ActivityModule;
import me.crazyjiang.crazyread.presenter.BasePresenter;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Jiangwenjin on 2017/2/28.
 * MVP base activity
 */
public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView {
    @Inject
    protected T mPresenter;
    protected Activity mContext;
    private Unbinder mUnBinder;

    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;

        // component inject
        inject();

        if (mPresenter != null)
            mPresenter.attachView(this);

        // load data, render, bind event
        init();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        mUnBinder.unbind();
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    protected abstract void inject();

    protected abstract void init();
}
