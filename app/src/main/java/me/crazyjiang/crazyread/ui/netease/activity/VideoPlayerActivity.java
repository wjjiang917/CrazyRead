package me.crazyjiang.crazyread.ui.netease.activity;

import android.net.Uri;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.crazyjiang.crazyread.R;
import me.crazyjiang.crazyread.ui.SimpleActivity;
import me.crazyjiang.crazyread.widget.media.PlayerManager;

public class VideoPlayerActivity extends SimpleActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.video_player)
    JCVideoPlayerStandard mVideoPlayer;

    private PlayerManager player;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void init() {
        setToolBar(mToolbar, "Video on demand");

        mVideoPlayer.setUp("https://scontent.cdninstagram.com/t50.2886-16/17194843_1231022870338190_3375755899934081024_n.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
        mVideoPlayer.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
    }

    @Override
    public void onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
