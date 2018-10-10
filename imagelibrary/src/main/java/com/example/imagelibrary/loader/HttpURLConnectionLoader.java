package com.example.imagelibrary.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.imagelibrary.utils.CloseUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 14512 on 2018/10/10
 */
public class HttpURLConnectionLoader implements HttpLoader {
    private static final int IO_BUFFER_SIZE = 8 * 1024;
    private HttpURLConnection mConnection;

    public HttpURLConnectionLoader() {
    }

    @Override
    public Bitmap download(String urlStr) {
        Bitmap bitmap = null;
        BufferedInputStream bis = null;
        try {
            final URL url = new URL(urlStr);
            mConnection = (HttpURLConnection) url.openConnection();
             bis = new BufferedInputStream(mConnection.getInputStream(),
                    IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(bis);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mConnection != null) {
                mConnection.disconnect();
            }
            CloseUtils.closeQuietly(bis);
        }
        return bitmap;
    }
}
