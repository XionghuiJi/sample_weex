package org.zsy.frame.weex.sample.config;

import android.os.Environment;
import org.zsy.frame.weex.sample.BuildConfig;

/**
 * 项目常量配置
 * @author: samy(hongfeiliuxing@gmail.com)
 * @datetime: 2015-09-13 11:52
 */
public class AppConstants {

    /**
     * Urls接口地址部分，考虑到可能比较多，直接封装再一个类中处理;BlogApi
     */
    public static final class Urls {
        //public static final String SERVER_IP = BlogApi.DEV_ARTICLES_URLS;
//        public static final String BAIDU_IMAGES_URLS = "http://image.baidu.com/data/imgs";
    }

    public static final class Paths {
        public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        public static final String IMAGE_LOADER_CACHE_PATH = BuildConfig.APPLICATION_ID+"/images/";
    }

    public static final class Integers {
        public static final int PAGE_LAZY_LOAD_DELAY_TIME_MS = 200;
    }

    /**
     * 用于Intent传值时的Extra_Key
     */
    public static final class ExtraKeys {
        public static final String Extra_Image_String_Array = "Extra_Image_String_Array";//图片Activity接收的图片地址们
        public static final String Extra_Image_Current_Position = "Extra_Image_Current_Position";//图片Activity刚进入时打开的图片
    }
}
