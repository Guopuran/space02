package guopuran.bwie.com.app3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WaterView extends LinearLayout {
    private final int mMaxSize=22;
    //传入的字符串数组
    private  List<String> list=new ArrayList<>();
    private Context context;
    public WaterView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        //设置最外层的LinertLayout为垂直布局
        setOrientation(VERTICAL);
    }

    public void setList(List<String> list) {
        //直接用新的列表，重新绘制
        this.list = list;
        showData();
    }

    private void showData() {
        //因为每一次都要重新作画，所以移除之前的布局
        removeAllViews();
        //优先向根布局添加一条横向布局
        LinearLayout linearLayout_h = (LinearLayout) View.inflate(context, R.layout.water_item_h, null);
        addView(linearLayout_h);
        //定义临时变量  用来计算最后一行已有的字符串长度
        int len=0;
        //遍历
        for(int i=0;i<list.size();i++){
            //将此字符串与记录的已有字符串长度相加
            len+=list.get(i).length();
            if (len>mMaxSize){
                //向根布局添加一条横向布局
                linearLayout_h = (LinearLayout) View.inflate(context, R.layout.water_item_h, null);
                addView(linearLayout_h);
                //因为换行，所以这一个字符串长度就最后一行长度
                len=list.get(i).length();
            }
            //添加TextView，并赋值
            View view=View.inflate(context,R.layout.water_item,null);
            TextView textView = view.findViewById(R.id.water_fall);
            textView.setText(list.get(i));
            linearLayout_h.addView(view);

            //设置权重，让每一行内所有空间相加充满整行，并合理分配
            LinearLayout.LayoutParams layoutParams= (LayoutParams) view.getLayoutParams();
            layoutParams.weight=1;
            view.setLayoutParams(layoutParams);
            final int index=i;
            //点击吐司
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,list.get(index) , Toast.LENGTH_SHORT).show();
                }
            });
            //长按删除
            view.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    list.remove(index);
                    showData();
                    return false;
                }
            });
        }
    }
}
