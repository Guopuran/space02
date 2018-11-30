package guopuran.bwie.com.space02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
    private Paint mPaint;
    private float mTouchX=300;
    private float mTouchY=300;
    private float mcircle=50;
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        //监听事件
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取位置
                mTouchX=event.getX();
                mTouchY=event.getY();
                //刷新view
                invalidate();
                return true;
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //画圆
        //第一个参数，圆心x轴位置
        //第二个参数，圆心y轴位置
        //第三个参数，圆形半径
        //第四个参数，画笔
        canvas.drawCircle(mTouchX,mTouchY,mcircle,mPaint);
    }
}
