package org.zsy.frame.weex.sample.config;

import android.os.Environment;
import org.zsy.frame.weex.sample.BuildConfig;

/**
 * 项目常量配置
 * @author samy(hongfeiliuxing@gmail.com)
 * @date 2014-09-13 11:52
 */
public class AppConstants {

    /**
     * Urls接口地址部分，考虑到可能比较多，直接封装再一个类中处理;BlogApi
     */
    public static final class Urls {

    }

    public static final class Paths {
        public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        public static final String IMAGE_LOADER_CACHE_PATH = BuildConfig.APPLICATION_ID+"/images/";
    }

    public static final class Integers {
        public static final int PAGE_LAZY_LOAD_DELAY_TIME_MS = 200;
    }

}
