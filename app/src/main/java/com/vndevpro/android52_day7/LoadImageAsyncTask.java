package com.vndevpro.android52_day7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ILoadImage callback;

    public LoadImageAsyncTask(ILoadImage callback) {
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(strings[0]);
            InputStream inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            callback.onLoadImageSuccess(bitmap);
        } else {
            callback.onLoadImageFailed("Load image failed !");
        }
    }
}
