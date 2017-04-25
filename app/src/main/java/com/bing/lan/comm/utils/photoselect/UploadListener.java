package com.bing.lan.comm.utils.photoselect;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by win7 on 2017/4/25.
 */
public interface UploadListener {
    void uploadAvatar(ImageView viewById, Uri source);

}
