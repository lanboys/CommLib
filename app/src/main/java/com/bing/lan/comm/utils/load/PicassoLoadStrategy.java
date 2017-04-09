package com.bing.lan.comm.utils.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.config.AppConfig;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.IOUtils;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.MD5Util;
import com.bing.lan.comm.utils.SPUtil;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.imagepipeline.image.ImageInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;

/**
 * @author 蓝兵
 * @time 2017/2/23  22:43
 */
public class PicassoLoadStrategy implements IBaseLoaderStrategy {

    protected static final LogUtil log = LogUtil.getLogUtil(PicassoLoadStrategy.class, LogUtil.LOG_VERBOSE);
    private static final int EMPTY_GONE = -1;
    private static final int EMPTY_DISABLE = -2;
    private static final int EMPTY_NONE = -3;

    private static void loadImage(Context context,
            ImageView imageView,
            String url,
            int empty,
            int loading,
            int error,
            boolean isCacheAndStore) {
        // 重置图片状态
        imageView.setVisibility(View.VISIBLE);
        // 根据
        if (TextUtils.isEmpty(url)) {
            //图片url为空时的状态
            switch (empty) {
                case EMPTY_GONE:
                    imageView.setVisibility(View.GONE);
                    break;
                case EMPTY_DISABLE:
                    imageView.setVisibility(View.INVISIBLE);
                    break;
                case EMPTY_NONE:
                    imageView.setBackgroundColor(AppUtil.getColor(R.color.colorPrimary));
                    break;
                default:
                    imageView.setImageResource(empty);
            }
            return;
        }

        if (SPUtil.getBoolean(AppConfig.SETTING_NO_IMAGE)) {
            imageView.setImageResource(loading);
            imageView.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
            return;
        } else {
            imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        }

        RequestCreator requestCreator = Picasso
                .with(context)
                .load(url)
                .placeholder(loading)
                .error(error)
                // .resize(AppUtil.dip2px(250),AppUtil.dip2px(250))
                // .fit()
                //         .centerCrop()//???
                .config(Bitmap.Config.RGB_565);
        log.d("loadImage(): " + requestCreator);

        if (!isCacheAndStore) {
            requestCreator.memoryPolicy(NO_CACHE/*, NO_STORE*/);
        }
        log.d("loadImage(): " + requestCreator);
        requestCreator.into(imageView);
    }

    public static void saveShareImage(String url, final SaveImageCallBack callBack) {
        final File file = new File(AppUtil.getAppContext().getExternalCacheDir(),
                MD5Util.MD5(url) + ".jpeg");
        if (file.exists()) {
            callBack.callBack(file);
            return;
        }
        Picasso.with(AppUtil.getAppContext()).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                OutputStream os = null;
                try {
                    os = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(os);
                }
                AppUtil.postTaskSafe(new Runnable() {
                    @Override
                    public void run() {
                        callBack.callBack(file);
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url) {
        loadImage(ctx, imageView, url, EMPTY_GONE,
                  R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                true);
    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, int reqWidth, int reqHeight) {

    }

    @Override
    public void loadImage(Context context, String url, int reqWidth, int reqHeight, IResult<Bitmap> loadImageResult) {

    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, ControllerListener<ImageInfo> controllerListener) {

    }

    @Override
    public void loadSmallImage(Context ctx, ImageView imageView, String url) {

    }

    @Override
    public void loadBigImage(Context ctx, ImageView imageView, String url) {
        loadImage(ctx, imageView, url, EMPTY_GONE,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                false);
    }

    public interface SaveImageCallBack {

        void callBack(File f);
    }
}
