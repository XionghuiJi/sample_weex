package org.zsy.frame.weex.sample.ui.ada;

import android.text.TextUtils;
import android.widget.ImageView;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import org.zsy.frame.weex.sample.uitils.ImageLoaderHelper;

public class ImageAdapter implements IWXImgLoaderAdapter {

  @Override public void setImage(final String url, final ImageView view, WXImageQuality quality,
      WXImageStrategy strategy) {

    WXSDKManager.getInstance().postOnUiThread(new Runnable() {
      @Override public void run() {
        if (view == null || view.getLayoutParams() == null) {
          return;
        }
        if (TextUtils.isEmpty(url)) {
          view.setImageBitmap(null);
          return;
        }
        String temp = url;
        if (url.startsWith("//")) {
          temp = "http:" + url;
        }
        if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
          return;
        }
        //ImageLoaderHelper.getInstance(GlobalApp.getInstance()).displayImage(temp,view);
        ImageLoaderHelper.getInstance(WXEnvironment.getApplication()).displayImage(temp,view);
      }
    }, 0);
  }
}
