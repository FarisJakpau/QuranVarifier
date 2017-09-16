package com.example.farisjakpau.quranvarifier;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by FarisJakpau on 9/17/2017.
 */

public class ImageLoader extends AsyncTask<String,Void, Bitmap> {

    Uri imagerURL ;
    ImageView imageView;

    public ImageLoader(String imager, ImageView imageView) {
        Uri myUri = Uri.parse(imager);
        this.imagerURL = myUri;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urldisplay = imagerURL.toString();
        Bitmap bitmap= null;

        try {
            InputStream inputStream = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
