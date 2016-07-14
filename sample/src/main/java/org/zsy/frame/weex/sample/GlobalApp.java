package org.zsy.frame.weex.sample;

import android.app.Application;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import org.zsy.frame.weex.sample.ui.ada.ImageAdapter;

/**
 * Created by samy on 16/7/14.
 */
public class GlobalApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    WXEnvironment.addCustomOptions("appName", "TBSample");
    WXSDKEngine.initialize(this, new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build());
  }
}
