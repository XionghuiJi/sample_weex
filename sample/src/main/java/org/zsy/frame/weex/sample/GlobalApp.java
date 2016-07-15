package org.zsy.frame.weex.sample;

import android.app.Application;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import org.zsy.frame.weex.sample.config.AppConstants;
import org.zsy.frame.weex.sample.ui.ada.ImageAdapter;
import org.zsy.frame.weex.sample.uitils.ImageLoaderHelper;

/**
 * Created by samy on 16/7/1.
 */
public class GlobalApp extends Application {
  private static GlobalApp instance;

  public static GlobalApp getInstance() {
    return instance;
  }

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    ImageLoaderHelper.getInstance(this)
        .init(ImageLoaderHelper.getInstance(this)
            .getImageLoaderConfiguration(AppConstants.Paths.IMAGE_LOADER_CACHE_PATH));

    WXEnvironment.addCustomOptions("appName", BuildConfig.APPLICATION_ID);
    WXSDKEngine.initialize(this,
        new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build());
  }
}
