package me.crazyjiang.crazyread.ui.zhihu.activity;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.common.Constant;
import me.crazyjiang.crazyread.model.bean.ZhiHuNewsBean;
import me.crazyjiang.crazyread.presenter.ZhiHuNewsPresenter;
import me.crazyjiang.crazyread.presenter.contract.ZhiHuNewsContract;
import me.crazyjiang.crazyread.ui.BaseActivity;
import me.crazyjiang.crazyread.util.HtmlUtil;
import me.crazyjiang.crazyread.util.ImageUtil;
import me.crazyjiang.crazyread.util.PhoneUtil;

public class ZhiHuNewsActivity extends BaseActivity<ZhiHuNewsPresenter> implements ZhiHuNewsContract.View {
    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;

    private Integer newsId;
    private ZhiHuNewsBean zhiHuNewsBean;
    private boolean isBottomShow = true; // 底部是否显示
    private boolean isTransitionEnd = false; // 过渡效果结束
    private boolean isImageShow = false; // 图片是否显示

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_zhihu_news;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void init() {
        setToolBar(viewToolbar, "");

        newsId = (Integer) getIntent().getExtras().get(Constant.INTENT_EXTRA_NEWS_ID);
        // query news detail
        mPresenter.getNewsDetail(newsId);

        // webview setting
        WebSettings settings = wvDetailContent.getSettings();

        // cache
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        if (PhoneUtil.isNetworkConnected(this)) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }

        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvDetailContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    llDetailBottom.animate().translationY(llDetailBottom.getHeight());
                } else if (scrollY - oldScrollY < 0 && !isBottomShow) {    //上移出现
                    isBottomShow = true;
                    llDetailBottom.animate().translationY(0);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // set up listener for entering transition to finish it with full image
            (getWindow().getSharedElementEnterTransition()).addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    isTransitionEnd = true;
                    if (zhiHuNewsBean != null && zhiHuNewsBean.getImage() != null) {
                        isImageShow = true;
                        ImageUtil.load(mContext, zhiHuNewsBean.getImage(), detailBarImage);
                    }
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                }

                @Override
                public void onTransitionPause(Transition transition) {
                }

                @Override
                public void onTransitionResume(Transition transition) {
                }
            });
        } else {
            isTransitionEnd = true;
            if (zhiHuNewsBean != null && zhiHuNewsBean.getImage() != null) {
                isImageShow = true;
                ImageUtil.load(mContext, zhiHuNewsBean.getImage(), detailBarImage);
            }
        }
    }

    @Override
    public void onNewsDetailLoaded(ZhiHuNewsBean zhiHuNewsBean) {
        ImageUtil.load(mContext, zhiHuNewsBean.getImage(), detailBarImage);
        clpToolbar.setTitle(zhiHuNewsBean.getTitle());
        detailBarCopyright.setText(zhiHuNewsBean.getImage_source());

        String htmlData = HtmlUtil.createHtmlData(zhiHuNewsBean.getBody(), zhiHuNewsBean.getCss(), zhiHuNewsBean.getJs());
        wvDetailContent.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wvDetailContent.canGoBack()) {
            wvDetailContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        }
    }
}
