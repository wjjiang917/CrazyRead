package me.crazyjiang.crazyread.util;

import android.content.Context;
import android.content.SharedPreferences;

import me.crazyjiang.crazyread.App;
import me.crazyjiang.crazyread.common.Constant;

/**
 * Created by Jiangwenjin on 2017/3/7.
 */

public class SPUtil {
    public static SharedPreferences getSP() {
        return App.getInstance().getSharedPreferences(Constant.NAME_SP, Context.MODE_PRIVATE);
    }

    /**
     * night mode
     */
    public static boolean getNightModeState() {
        return getSP().getBoolean(Constant.KEY_NIGHT_MODE, false);
    }

    /**
     * night mode
     */
    public static void setNightModeState(boolean state) {
        getSP().edit().putBoolean(Constant.KEY_NIGHT_MODE, state).apply();
    }

    public static int getCurrentPage() {
        return getSP().getInt(Constant.KEY_CURRENT_PAGE, Constant.TYPE_ZHIHU);
    }

    public static void setCurrentPage(int item) {
        getSP().edit().putInt(Constant.KEY_CURRENT_PAGE, item).apply();
    }
}
