package guopuran.bwie.com.app6;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomView extends LinearLayout {
    private Context context;
    private int mChildMaxHeight;
    private int mHSpace=20;
    private int mVSpace=20;
    public CustomView(Context context) {
        super(context);
        this.context=context;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到父容器推荐的宽和高以及计算模式
        int sizeWitdth=MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
        //测量孩子的大小
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //找到孩子中最高的孩子，找到的值放入mChildMaxHeight
        findMaxChildHeight();
        //初始化值
        int left=0,top=0;

        //循环所有的孩子
        int childcount=getChildCount();
        for (int i=0;i<childcount;i++){
            View view=getChildAt(i);
            //是否是一行的开头
            if (left!=0){
                //需要换行
                if (left+view.getMeasuredWidth()>sizeWitdth){
                    //计算下一行的top
                    top+=mChildMaxHeight+mVSpace;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+mHSpace;
        }
        setMeasuredDimension(sizeWitdth,(top+mChildMaxHeight)>sizeHeight?sizeHeight:top+mChildMaxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxChildHeight();
        //开始安排孩子的位置
        //初始化
        int left=0,top=0;
        int childcount=getChildCount();
        for (int i=0;i<childcount;i++){
            final View view=getChildAt(i);
            if (left!=0){
                if (left+view.getMeasuredWidth()>getWidth()){
                    top+=mChildMaxHeight+mVSpace;
                    left=0;
                }
            }
            //安排孩子
            view.layout(left,top,left+view.getMeasuredWidth(),top+mChildMaxHeight);
            left+=view.getMeasuredWidth()+mHSpace;

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void findMaxChildHeight() {
        mChildMaxHeight=0;
        //得到孩子的数量
        int childcount=getChildCount();
        for (int i=0;i<childcount;i++){
            View view=getChildAt(i);
            if (view.getMeasuredHeight()>mChildMaxHeight){
                mChildMaxHeight=view.getMeasuredHeight();
            }
        }
    }
}
