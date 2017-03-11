package me.crazyjiang.crazyread.ui.netease.activity;

import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.ui.SimpleActivity;

public class VideoPlayerActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void init() {
        setToolBar(mToolbar, "IJKPlayer");
    }
}
