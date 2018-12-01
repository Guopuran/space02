package guopuran.bwie.com.app7;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class CustomView extends LinearLayout {
    private int mChildMaxHeight;
    private int mVSpace=20;
    private int mHSpace=20;
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizewidth=MeasureSpec.getSize(widthMeasureSpec);
        int sizeheight=MeasureSpec.getSize(heightMeasureSpec);
        //测量孩子的大小
//        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //寻找孩子中最高的
        findMaxChildHeight();
        //初始化值
        int left=0,top=0;
        //循环所有的孩子
        int childcount=getChildCount();
        Log.i("dj", "---------------------------");
        for (int i=0;i<childcount;i++){
            View view=getChildAt(i);
            if (left!=0){
                if (left+view.getMeasuredWidth()>sizewidth){
                    //计算出下一行的高度
                    top+=mChildMaxHeight+mVSpace;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+mHSpace;
            Log.i("dj",  i + " mChildMaxHeight is " + mChildMaxHeight);
            Log.i("dj", i + " MeasuredWidth is " + view.getMeasuredWidth());
        }

        Log.i("dj", "top is " + top);
        Log.i("dj", "left is " + left);
        setMeasuredDimension(sizewidth,(top+mChildMaxHeight)>sizeheight?sizeheight:top+mChildMaxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxChildHeight();
        int left=0,top=0;
        int childcount=getChildCount();
        for (int i=0;i<childcount;i++){
            View view=getChildAt(i);
            if (left!=0){

                    if (left+view.getMeasuredWidth()>getWidth()){
                        top+=mChildMaxHeight+mVSpace;
                        left=0;
                }
            }
            //安排孩子的位置
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
        int childcount=getChildCount();
        for (int i=0;i<childcount;i++){
            View view =getChildAt(i);
            if (view.getMeasuredHeight()>mChildMaxHeight){
                mChildMaxHeight=view.getMeasuredHeight();
            }
        }
    }
}
