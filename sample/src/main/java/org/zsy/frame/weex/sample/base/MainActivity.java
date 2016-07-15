package org.zsy.frame.weex.sample.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.HashMap;
import java.util.Map;
import org.zsy.frame.weex.sample.BuildConfig;
import org.zsy.frame.weex.sample.R;

public class MainActivity extends AppCompatActivity implements IWXRenderListener {

  RelativeLayout viewGroup;
  private static final String DEFAULT_IP = "your_current_IP";
  private static String CURRENT_IP = DEFAULT_IP; // your_current_IP
  private static final String WEEX_INDEX_URL =
      "http://" + CURRENT_IP + ":12580/examples/build/index.js";

  WXSDKInstance mWXSDKInstance;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    viewGroup = (RelativeLayout) findViewById(R.id.viewGroup);
    //WXSDKInstance mInstance = new WXSDKInstance(this);
    //mInstance.registerRenderListener(new IWXRenderListener() {
    //  @Override public void onViewCreated(WXSDKInstance instance, View view) {
    //    viewGroup.addView(view);
    //  }
    //
    //  @Override public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
    //
    //  }
    //
    //  @Override public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
    //
    //  }
    //
    //  @Override public void onException(WXSDKInstance instance, String errCode, String msg) {
    //
    //  }
    //});
    //renderPage(mInstance, getPackageName(), WXFileUtils.loadFileContent("gold.js", this),
    //    WEEX_INDEX_URL, null);

    mWXSDKInstance = new WXSDKInstance(this);
    mWXSDKInstance.registerRenderListener(this);

    mWXSDKInstance.render(BuildConfig.APPLICATION_ID, WXFileUtils.loadFileContent("tech_list.js", this), null,
        null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
  }

  /**
   * template 是.we transform 后的 js文件。
   * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
   * jsonInitData 可以为空。
   * width 为-1 默认全屏，可以自己定制。
   * height =-1 默认全屏，可以自己定制。
   * @param mInstance
   * @param packageName
   * @param template
   * @param source
   * @param jsonInitData
   */
  protected void renderPage(WXSDKInstance mInstance, String packageName, String template,
      String source, String jsonInitData) {
    Map<String, Object> options = new HashMap<>();
    options.put(WXSDKInstance.BUNDLE_URL, source);
    mInstance.render(packageName, template, options, jsonInitData, WXViewUtils.getScreenWidth(this),
        WXViewUtils.getScreenHeight(this), WXRenderStrategy.APPEND_ASYNC);
  }

  @Override public void onViewCreated(WXSDKInstance instance, View view) {
    setContentView(view);
  }

  @Override public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

  }

  @Override public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

  }

  @Override public void onException(WXSDKInstance instance, String errCode, String msg) {

  }

  @Override protected void onResume() {
    super.onResume();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityResume();
    }
  }

  @Override protected void onPause() {
    super.onPause();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityPause();
    }
  }

  @Override protected void onStop() {
    super.onStop();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityStop();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityDestroy();
    }
  }
}
