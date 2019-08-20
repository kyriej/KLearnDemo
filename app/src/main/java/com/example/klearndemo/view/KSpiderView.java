package com.example.klearndemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.klearndemo.R;

import java.util.ArrayList;
import java.util.List;

public class KSpiderView extends View {

    private static final String TAG = KSpiderView.class.getSimpleName();
    private Paint mPaint;
    private Context mContext;
    private int count = 6;
    private int pointRadius = 5;
    private float radius;
    private float centerX;
    private float centerY;
    private Paint textPaint;
    private Paint valuePaint;
    private List<String> mTitles;
    private List<Double> datas;
    private double maxValue = 100;
    private float angle;
    private Paint pointPaint;

    public KSpiderView(Context context){
        this(context,null);
    }

    public KSpiderView(Context context , AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public KSpiderView(Context context , AttributeSet attributeSet , int defStyle){
        super(context,attributeSet,defStyle);
        this.mContext = context;
        initView();
    }

    private void initView() {
        // 雷达区画笔
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);

        // 文本画笔
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(16);
        textPaint.setStrokeWidth(1);
        textPaint.setColor(Color.BLACK);

        // 数据区画笔
        valuePaint = new Paint();
        valuePaint.setColor(mContext.getResources().getColor(R.color.alpha_yellow));
        valuePaint.setAntiAlias(true);
        valuePaint.setStrokeWidth(3);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        pointPaint = new Paint();
        pointPaint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);

        //
        mTitles = new ArrayList<>();
        mTitles.add("语文");
        mTitles.add("数学");
        mTitles.add("英语");
        mTitles.add("物理");
        mTitles.add("化学");
        mTitles.add("生物");

        datas = new ArrayList<>();
        datas.add(90.0);
        datas.add(70.5);
        datas.add(63.5);
        datas.add(86.0);
        datas.add(47.0);
        datas.add(69.0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w,h) / 2 * 0.6f;

        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
        //drawLines(canvas);
        drawTitles(canvas);
        drawRegion(canvas);
    }

    private void drawTitles(Canvas canvas){

    }

    private void drawRegion(Canvas canvas) {
    }

    private void drawLines(Canvas canvas) {

    }

    private void drawPolygon(Canvas canvas) {

        Path path = new Path();
        // Math.sin Math.cos 传入参数需要是弧度 因此需要转换下 360 度 = 2 * Math.PI
        angle =(float) ((Math.PI * 2) / count); // n边对应n个内角 angle * n = 2 * Math.PI
        float gap = radius / (count - 1);

        for(int i = 0; i < count ; i++){
            // 该循环控制会多少个多边形
            float curR = gap  * (i + 1);
            path.reset();
            //linePath
            Path linePath = new Path();
            linePath.reset();
            Path dataPath = new Path();
            dataPath.reset();
            //textPaint
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

            for(int j = 0; j < count; j++){
                // 该循环控制每个多边形具体绘制实现
                if(j == 0){
                    float curX = centerX;
                    float curY = centerY - curR;
                    path.moveTo(curX,curY);
                    if(i == count - 1) {
                        linePath.moveTo(centerX, centerY);
                        linePath.lineTo(curX, curY);
                        float ratio = (float) ((datas.get(j)) / maxValue);
                        Log.e(TAG,"ratio------------------->"+ratio);
                        float dataY = (float) centerY - curR * ratio;
                        dataPath.moveTo(curX,dataY);
                        float textHeight = fontMetrics.descent - fontMetrics.ascent; // 建议高度
                        canvas.drawText(mTitles.get(j),curX,curY - textHeight / 5,textPaint);
                        canvas.drawCircle(curX,dataY,pointRadius,pointPaint);
                    }
                }else{
                    float otherX = (float)(centerX - curR * Math.sin( 2 * Math.PI / count * j ));
                    float otherY = (float)(centerY - curR * Math.cos( 2 * Math.PI / count * j));
                    path.lineTo(otherX,otherY);
                    //
                    if(i == count - 1) {
                        linePath.moveTo(centerX, centerY);
                        linePath.lineTo(otherX, otherY);

                        float ratio = (float) ((datas.get(j)) / maxValue);
                        Log.e(TAG,"ratio other------------------------------->"+ratio);
                        float dataX = (float)(centerX - curR * ratio * Math.sin( 2 * Math.PI / count * j ));
                        float dataY = (float)(centerY - curR * ratio * Math.cos( 2 * Math.PI / count * j));
                        dataPath.lineTo(dataX,dataY);
                        canvas.drawCircle(dataX,dataY,pointRadius,pointPaint);

                        float textX , textY;
                        float textWidth = textPaint.measureText(mTitles.get(j));
                        float textHeight = fontMetrics.descent - fontMetrics.ascent; // 建议高度 // bottom - top 真正高度
                        if(otherX < centerX){
                            textX = otherX - textWidth / 2;
                        }else{
                            textX = otherX + textWidth / 2;
                        }

                        if(otherY < centerY){
                            textY = otherY - textHeight / 5;
                        }else{
                            textY = otherY + textHeight * 1.2f;
                        }

                        canvas.drawText(mTitles.get(j),textX,textY,textPaint);
                    }
                }
            }
            path.close();
            linePath.close();
            dataPath.close();
            canvas.drawPath(path,mPaint);
            canvas.drawPath(linePath,mPaint);
            canvas.drawPath(dataPath,valuePaint);
        }

    }
}
