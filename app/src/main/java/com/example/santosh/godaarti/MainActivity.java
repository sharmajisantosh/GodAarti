package com.example.santosh.godaarti;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.example.santosh.godaarti.adapter.GridAdapter;
import com.example.santosh.godaarti.helper.Utils;

public class MainActivity extends AppCompatActivity {

    GridView gridview;
    private Utils utils;
    int NUM_OF_COLUMNS = 2;
    int GRID_PADDING = 5;
    private int columnWidth;


    int[] images={R.drawable.ganesh1,R.drawable.ganesh1,R.drawable.ganesh1,R.drawable.ganesh1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //showing play icon on bar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.play_image, null);
        actionBar.setCustomView(v);

        gridview= (GridView) findViewById(R.id.gridView);
        utils = new Utils(this);

        InitilizeGridLayout();

        GridAdapter gridadapter=new GridAdapter(images,columnWidth,this);
        gridview.setAdapter(gridadapter);

    }
    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                GRID_PADDING, r.getDisplayMetrics());

        columnWidth = (int) ((utils.getScreenWidth() - ((NUM_OF_COLUMNS ) * padding)) / NUM_OF_COLUMNS);


        gridview.setNumColumns(NUM_OF_COLUMNS);
        gridview.setColumnWidth(columnWidth);
        //gridview.setStretchMode(GridView.NO_STRETCH);
        gridview.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridview.setHorizontalSpacing((int) padding);
        gridview.setVerticalSpacing((int) padding);
    }
}
