package com.example.fredgogh.sleepapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import static com.example.fredgogh.sleepapp.MainActivity.date;
import static com.example.fredgogh.sleepapp.MainActivity.sleeptime_data;

/**
 * Created by FredGogh on 2022/5/14.
 */

public class linechartActivity1 extends AppCompatActivity{
    //对应xml中的LineChartView
    private LineChartView lineChartView;
    // x轴数据
    private String[] data = new String[date.size()];
    // 每个点的y轴数据
    private Float[] score = new Float[sleeptime_data.size()];
    // X轴对象
    private List<AxisValue> axisValues = new ArrayList<AxisValue>();
    // Y轴对象
    private List<PointValue> pointValues = new ArrayList<PointValue>();

    //折线集合  用于存放多个折线，即可以显示多条折线
    private List<Line> lines = new ArrayList<>();
    //折线图数据对象
    private LineChartData lData = new LineChartData();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linechart);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        lineChartView = (LineChartView)findViewById(R.id.linechart);
        score = sleeptime_data.toArray(score);
        data = date.toArray(data);
        initData();
        createLine();
        createXAndY();
        lData.setLines(lines);
        //把数据对象设置到折线图中
        lineChartView.setLineChartData(lData);
        //设置是否允许平移和缩放
        //lineChartView.setInteractive(false);
        //设置缩放的轴
        lineChartView.setZoomType(ZoomType.HORIZONTAL);
        //设置缩放的倍数
        lineChartView.setMaxZoom(5);
        //创建一个图标视图 大小为控件的最大大小
        final Viewport v = new Viewport(lineChartView.getMaximumViewport());
        v.bottom = 0;
        v.top = 12*60;                            //最高点为40
        lineChartView.setMaximumViewport(v);   //给最大的视图设置
        lineChartView.setCurrentViewport(v);   //给当前的视图设置
    }

    //初始化X、Y轴数据
    private void  initData(){
        for (int i = 0; i < score.length; i++) {
            axisValues.add(new AxisValue(i).setLabel(data[i]));
            pointValues.add(new PointValue(i, score[i]));
        }
    }

    //创建一条线
    private void createLine(){
        Line mLine = new Line();
        // 添加点
        mLine.setValues(pointValues);

        //设置线的颜色
        mLine.setColor(Color.parseColor("#FF4777"));

        //曲线/折线
        mLine.setCubic(false);

        //设置点的形状
        mLine.setShape(ValueShape.CIRCLE);

        //点击展示标签
        mLine.setHasLabelsOnlyForSelected(true);

        //面积填充
        mLine.setFilled(false);

        //有无标签
        mLine.setHasLabels(false);

        //将所有的线都添加到线的集合中
        lines.add(mLine);
    }

    //设置横纵坐标
    private void createXAndY(){
        //X轴
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(false);
        axisX.setTextColor(Color.parseColor("#000000"));
        axisX.setTextSize(15);
        axisX.setMaxLabelChars(2);
        axisX.setValues(axisValues);
        axisX.setName("日期");
        axisX.setHasLines(true);
        lData.setAxisXBottom(axisX);

        //Y轴
        Axis axisY = new Axis();
        axisY.setName("时长");
        axisY.setTextSize(15);
        axisY.setTextColor(Color.parseColor("#000000"));
        lData.setAxisYLeft(axisY);

        List<AxisValue> values = new ArrayList<>();
        for(int i = 0; i < 12*60; i += 30){
            AxisValue value = new AxisValue(i);
            values.add(value.setLabel(i/60.0+"h"));
        }
        axisY.setValues(values);
        lData.setAxisYLeft(axisY);
    }

}
