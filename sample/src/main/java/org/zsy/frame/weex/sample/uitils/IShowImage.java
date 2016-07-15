package org.zsy.frame.weex.sample.uitils;

import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

/**
 * 显示图片方法综合
 * @author samy
 * @datetime 2015-3-24 下午5:41:16
 */
public interface IShowImage {
	/**默认的图片加载方式*/
	void displayImage(String imageUri, ImageView imageView);

	/**可以配置的图片加载方式*/
	void displayImage(String imageUri, ImageView imageView, DisplayImageOptions option);

	/**可以配置的图片加载监听*/
	void displayImage(String imageUri, ImageView imageView, DisplayImageOptions options,
			ImageLoadingListener listener);

	/**可以配置的图片加载监听，和加载时的监听*/
	void displayImage(String imageUri, ImageView imageView, DisplayImageOptions options,
			ImageLoadingListener listener, ImageLoadingProgressListener progressListener);
}
