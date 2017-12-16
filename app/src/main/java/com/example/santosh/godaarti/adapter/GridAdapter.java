package com.example.santosh.godaarti.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by SANTOSH on 12/15/2017.
 */

public class GridAdapter extends BaseAdapter {

    Context context;
    private final int[] images;
    private int imageWidth;

    public GridAdapter( int[] images,int imageWidth, Context context) {
        this.images = images;
        this.context = context;
        this.imageWidth = imageWidth;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageWidth));
        imageView.setImageResource(images[position]);
        return imageView;
    }
}
