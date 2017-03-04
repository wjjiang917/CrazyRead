package me.crazyjiang.crazyread.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.crazyjiang.crazyread.ui.zhihu.fragment.ZhiHuFragment;

/**
 * Created by Jiangwenjin on 2017/2/28.
 */
@Module
public class PageModule {
    @Singleton
    @Provides
    ZhiHuFragment provideZhiHu() {
        return new ZhiHuFragment();
    }
}
