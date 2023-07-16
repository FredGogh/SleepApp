package com.example.fredgogh.sleepapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
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
import static com.example.fredgogh.sleepapp.MainActivity.wakeuptime_data;

/**
 * Created by FredGogh on 2022/5/14.
 */

public class linechartActivity3 extends AppCompatActivity{
    //对应xml中的LineChartView
    private LineChartView lineChartView;
    // x轴数据
    private String[] data = {};
    // 每个点的y轴数据
    private Float[] score = {};
    // X轴对象
    private List<AxisValue> axisValues = new ArrayList<AxisValue>();
    // Y轴对象
    private List<PointValue> pointValues = new ArrayList<PointValue>();
    //折线集合  用于存放多个折线，即可以显示多条折线
    private List<Line> lines = new ArrayList<>();
    //折线图数据对象
    private LineChartData lData = new LineChartData();
    private TextView title;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linechart);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        score = wakeuptime_data.toArray(score);
        data = date.toArray(data);
        title = (TextView)findViewById(R.id.title);
        title.setText("醒来时间");
        lineChartView = (LineChartView)findViewById(R.id.linechart);
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
        v.top = 24;                            //最高点为40
        lineChartView.setMaximumViewport(v);   //给最大的视图设置 相当于原图
        lineChartView.setCurrentViewport(v);   //给当前的视图设置 相当于当前展示的图
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
        // 为线添加点
        mLine.setValues(pointValues);

        //设置线的颜色
        mLine.setColor(Color.parseColor("#FF4777"));

        //曲线是否平滑，即是曲线还是折线
        mLine.setCubic(false);

        //设置点的形状
        mLine.setShape(ValueShape.CIRCLE);

        //设置点击坐标提醒  平常不显示 点击才显示
        //mLine.setHasLabelsOnlyForSelected(true);

        //取消线
        //mLine.setHasLines(false);

        //是否填充面积
        mLine.setFilled(false);

        //设置坐标数据显示
        mLine.setHasLabels(false);

        //将所有的线都添加到线的集合中
        lines.add(mLine);
    }

    //设置横纵坐标
    private void createXAndY(){
        //设置X轴
        Axis axisX = new Axis(); //X轴
        //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setHasTiltedLabels(false);
        //设置字体颜色
        axisX.setTextColor(Color.parseColor("#000000"));
        //设置字体大小
        axisX.setTextSize(15);
        //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setMaxLabelChars(2);
        //填充X轴的坐标名称
        axisX.setValues(axisValues);
        axisX.setName("日期");
        //设置是否拥有轴的分界线
        axisX.setHasLines(true);

        lData.setAxisXBottom(axisX); //x 轴在底部

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("时间");//y轴标注
        axisY.setTextSize(15);//设置字体大小
        axisY.setTextColor(Color.parseColor("#000000"));
        lData.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        List<AxisValue> values = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            AxisValue value = new AxisValue(i);
            values.add(value.setLabel(i+"点"));
        }
        axisY.setValues(values);
        lData.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

    }

}

