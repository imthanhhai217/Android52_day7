package com.vndevpro.android52_day7;

import android.graphics.Bitmap;

public interface ILoadImage {
    void onLoadImageSuccess(Bitmap bitmap);
    void onLoadImageFailed(String message);
}
