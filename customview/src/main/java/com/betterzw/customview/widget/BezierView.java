package com.betterzw.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by betterzw on 10/9/17.
 */

public class BezierView extends View{

    Paint paint;
    Path path;

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

       canvas.translate(0, 0);
        path = new Path();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL);
        //设置虚线效果
        paint.setPathEffect(new DashPathEffect(new float[] {15, 5}, 0));

//        path.moveTo(34, 259);
//        path.cubicTo(68, 151, 286, 350, 336, 252);
        //不加这个就是以起点(0,0)
        path.moveTo(100, 500);
        //画贝塞尔曲线,两个控制点
//        path.quadTo(300, 100, 600, 500);
        //画贝塞尔曲线,三个控制点
        path.cubicTo(100, 500, 300, 100, 600, 500);
        canvas.drawPath(path, paint);

//        drawOvalAndArrow(canvas);

    }

    private void drawOvalAndArrow(Canvas canvas){


        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(2);
        circlePaint.setColor(Color.CYAN);

        float centerWidth = canvas.getWidth()/2; //get center x of display
        float centerHeight = canvas.getHeight()/2; //get center y of display
        float circleRadius = 20; //set radius
        float circleDistance = 200; //set distance between both circles

        //draw circles
        canvas.drawCircle(centerWidth, centerHeight, circleRadius, circlePaint);
        canvas.drawCircle(centerWidth+circleDistance, centerHeight, circleRadius, circlePaint);


        //to draw an arrow, just lines needed, so style is only STROKE
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.RED);

        //create a path to draw on
        Path arrowPath = new Path();

        //create an invisible oval. the oval is for "behind the scenes" ,to set the path´
        //area. Imagine this is an egg behind your circles. the circles are in the middle of this egg
        final RectF arrowOval = new RectF();
        arrowOval.set(centerWidth,
                centerHeight-80,
                centerWidth + circleDistance,
                centerHeight+80);

        //add the oval to path
        arrowPath.addArc(arrowOval,-180,180);

        //draw path on canvas
        canvas.drawPath(arrowPath, circlePaint);


        //draw arrowhead on path start
//        arrowPath.moveTo(centerWidth,centerHeight ); //move to the center of first circle
//        arrowPath.lineTo(centerWidth-circleRadius, centerHeight-circleRadius);//draw the first arrowhead line to the left
//        arrowPath.moveTo(centerWidth,centerHeight );//move back to the center
//        arrowPath.lineTo(centerWidth+circleRadius, centerHeight-circleRadius);//draw the next arrowhead line to the right

        //same as above on path end
        arrowPath.moveTo(centerWidth+circleDistance,centerHeight );
        arrowPath.lineTo((centerWidth+circleDistance)-circleRadius, centerHeight-circleRadius);
        arrowPath.moveTo(centerWidth+circleDistance,centerHeight );
        arrowPath.lineTo((centerWidth+circleDistance)+circleRadius, centerHeight-circleRadius);

        //draw the path
        canvas.drawPath(arrowPath,circlePaint);

    }

}
