package me.crazyjiang.crazyread.ui.main.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.model.bean.StartImageBean;
import me.crazyjiang.crazyread.presenter.SplashPresenter;
import me.crazyjiang.crazyread.presenter.contract.SplashContract;
import me.crazyjiang.crazyread.ui.BaseActivity;
import me.crazyjiang.crazyread.util.ImageUtil;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {
    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void init() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void onSuccess(StartImageBean startImageBean) {
        ImageUtil.load(this, startImageBean.getImg(), ivWelcomeBg);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvWelcomeAuthor.setText(startImageBean.getText());
    }

    @Override
    public void onFinish() {
        // go to home page
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(ivWelcomeBg);
        super.onDestroy();
    }
}
