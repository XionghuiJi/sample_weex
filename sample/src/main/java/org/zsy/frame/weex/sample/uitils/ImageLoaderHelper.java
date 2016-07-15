package org.zsy.frame.weex.sample.uitils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import java.io.File;

/**
 * Android Universal Image Loader 是一个强大的、可高度定制的图片缓存，本文简称为UIL。
 * 简单的说 UIL 就做了一件事——获取图片并显示在相应的控件上。
 *
 * UIL 的总体设计图。
 * 整个库分为ImageLoaderEngine，Cache及ImageDownloader，ImageDecoder，BitmapDisplayer，BitmapProcessor五大模块，
 * 其中Cache分为MemoryCache和DiskCache两部分。
 * 简单的讲就是
 * ImageLoader收到加载及显示图片的任务，并将它交给ImageLoaderEngine，
 * ImageLoaderEngine分发任务到具体线程池去执行，
 * 任务通过Cache及ImageDownloader获取图片，
 * 中间可能经过BitmapProcessor和ImageDecoder处理，
 * 最终转换为Bitmap交给BitmapDisplayer在ImageAware中显示。
 *
 * ps:添加网络权限。如果允许磁盘缓存，需要添加写外设的权限。
 *
 * 如果图片显示简单的话可以不用UIL显示，直接用Velloy自带的简单显示图片；
 * UniversalImageLoad加载显示类
 * 注意事项，必须在Application初始化配置一下；
 * ImageLoader.getInstance().init(ImgCacheUtil.getInstance().getImgLoaderConfig());
 *
 * @author samy(hongfeiliuxing@gmail.com)
 * @datetime 2015-09-15 20:50
 */
// public class ImageLoaderHelper {
public class ImageLoaderHelper implements IShowImage {

	private Context mContext = null;
	private static volatile ImageLoaderHelper instance = null;

	private ImageLoaderHelper(Context context) {
		mContext = context;
	}

	public static ImageLoaderHelper getInstance(Context context) {
		if (null == instance) {
			synchronized (ImageLoaderHelper.class) {
				if (null == instance) {
					Application app = context instanceof Application ? (Application) context : (Application) context.getApplicationContext();
					instance = new ImageLoaderHelper(app);
				}
			}
		}
		return instance;
	}

	/**
	 * 初始化默认配置--->图片显示样式
	 */
	public DisplayImageOptions getDisplayOptions() {
		return new DisplayImageOptions.Builder()// 开始构建, 显示的图片的各种格式
				.cacheInMemory(true)// 开启内存缓存
				.cacheOnDisk(true) // 开启硬盘缓存
				.considerExifParams(true)// 是否考虑JPEG图像EXIF参数（旋转，翻转）
				// .showImageOnLoading(R.color.default_image_background)
				// .showImageForEmptyUri(R.color.default_image_background)
				// .showImageOnFail(R.color.default_image_background)
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型;使用.bitmapConfig(Bitmap.config.RGB_565)代替ARGB_8888;
				.imageScaleType(ImageScaleType.EXACTLY)// 缩放级别
				// .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//这两种配置缩放都推荐
				// .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少；避免使用RoundedBitmapDisplayer.他会创建新的ARGB_8888格式的Bitmap对象；
				// .displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.displayer(new SimpleBitmapDisplayer())// 正常显示一张图片
				.build();
	}

	public DisplayImageOptions getDisplayOptions(boolean isCacheOnDisk) {
		return new DisplayImageOptions.Builder()
				// .showImageOnLoading(R.color.default_image_background)
				// .showImageForEmptyUri(R.color.default_image_background)
				// .showImageOnFail(R.color.default_image_background)
				.cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).cacheOnDisk(isCacheOnDisk).considerExifParams(true).build();
	}

	public DisplayImageOptions getDisplayOptions(Drawable drawable) {
		return new DisplayImageOptions.Builder().showImageOnLoading(drawable).showImageForEmptyUri(drawable).showImageOnFail(drawable).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).cacheOnDisk(true).considerExifParams(true).build();
	}

	public DisplayImageOptions getDisplayOptions(int round) {
		return new DisplayImageOptions.Builder().cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(DensityUtil.dip2px(mContext, round))).build();
	}

	public DisplayImageOptions getDisplayOptions(int round, Drawable drawable) {
		return new DisplayImageOptions.Builder().showImageOnLoading(drawable).showImageForEmptyUri(drawable).showImageOnFail(drawable).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(DensityUtil.dip2px(mContext, round))).build();
	}

	/**
	 * 初始化配置参数，参数configuration为ImageLoader的配置信息，包括图片最大尺寸、任务线程池、磁盘缓存、下载器、解码器等等。
	 */
	public ImageLoaderConfiguration getImageLoaderConfiguration(String filePath) {
		File cacheDir = null;
		if (!TextUtils.isEmpty(filePath)) {
			cacheDir = StorageUtils.getOwnCacheDirectory(mContext, filePath);
		}
		else {
			cacheDir = StorageUtils.getCacheDirectory(mContext);
		}

		ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(mContext);
		builder.denyCacheImageMultipleSizesInMemory();// 不缓存一张图片的多张尺寸; 设置加载的图片有多样的
		builder.tasksProcessingOrder(QueueProcessingType.LIFO);// 图片加载任务顺序

		builder.threadPriority(Thread.NORM_PRIORITY - 2);// 设置线程优先级
		builder.threadPoolSize(5);// 线程池内加载的数量 ;减少配置之中线程池的大小，(.threadPoolSize).推荐1-5；
		// builder.imageDownloader(new BaseImageDownloader(mContext));
		// builder.imageDecoder(new BaseImageDecoder(false));

		builder.diskCacheSize(256 * 1024 * 1024);// 缓存设置大小为256 Mb
		// builder.diskCacheExtraOptions(720, 1280, null);//设置缓存图片时候的宽高最大值，默认为屏幕宽高;保存的每个缓存文件的最大长宽
		builder.diskCache(new UnlimitedDiskCache(cacheDir));// 自定义缓存路径
		// .diskCacheFileNameGenerator(new HashCodeFileNameGenerator());//使用HASHCODE对UIL进行加密命名
		builder.diskCacheFileNameGenerator(new Md5FileNameGenerator());// 将保存的时候的URI名称用MD5 加密

		builder.memoryCacheSizePercentage(25);// 图片内存占应用的25%；
		builder.memoryCacheSize(20 * 1024 * 1024);// 内存设置大小为20 Mb
		// builder.memoryCacheExtraOptions(720, 1280);// 即保存的每个缓存文件的最大长宽
		builder.memoryCache(new LruMemoryCache(2 * 1024 * 1024));
		// builder.memoryCache(new WeakMemoryCache());//使用.memoryCache(new WeakMemoryCache())，不要使用.cacheInMemory();

		// builder.defaultDisplayImageOptions(DisplayImageOptions.createSimple());
		builder.defaultDisplayImageOptions(getDisplayOptions());// 如果需要打开缓存机制，需要自己builde一个option,可以是DisplayImageOptions.createSimple()
		//if (LibCoreConfig.API_URL.startsWith("https://")){
		//    builder.imageDownloader(new AuthImageDownloader(mContext));//用于https情况下加载图片
		//}
		return builder.build();// 构建完成
	}

	/**
	 * // 全局初始化此配置;mageLoaderConfiguration必须配置并且全局化的初始化这个配置ImageLoader.getInstance().init(config);
	 * 否则也会出现错误提示
	 */
	public void init(ImageLoaderConfiguration configuration) {
		ImageLoader.getInstance().init(configuration);
	}

	/**
	 * String imageUri = "http://site.com/image.png"; // from Web
	 * String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
	 * String imageUri = "content://media/external/audio/albumart/13"; // from content provider
	 * String imageUri = "assets://image.png"; // from assets
	 * String imageUri = "drawable://" + R.drawable.image; // from drawables (only images,
	 * non-9patch)
	 * 加载自定义配置的图片
	 */
	@Override
	public void displayImage(String imageUri, ImageView imageView) {
		ImageLoader.getInstance().displayImage(imageUri, imageView);
	}

	@Override
	public void displayImage(String imageUri, ImageView imageView, DisplayImageOptions options) {
		ImageLoader.getInstance().displayImage(imageUri, imageView, options);
	}

	@Override
	public void displayImage(String imageUri, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener) {
		ImageLoader.getInstance().displayImage(imageUri, imageView, options, listener);
	}

	@Override
	public void displayImage(String imageUri, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
		ImageLoader.getInstance().displayImage(imageUri, imageView, options, listener, progressListener);
	}
}
