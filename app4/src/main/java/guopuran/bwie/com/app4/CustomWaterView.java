package guopuran.bwie.com.app4;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomWaterView extends LinearLayout {
    private int mMaxSize=20;
    private Context context;
    private List<String> list=new ArrayList<>();
    public CustomWaterView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public CustomWaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    public void setList(List<String> list) {
        this.list = list;
        showData();
    }

    private void showData() {
        //移除
        removeAllViews();
        //添加横向布局
        LinearLayout linearLayout_h= (LinearLayout) View.inflate(context,R.layout.water_item_h,null);
        addView(linearLayout_h);
        int len=0;
        for (int i=0;i<list.size();i++){
            len+=list.get(i).length();
            if (len>mMaxSize){
                linearLayout_h= (LinearLayout) View.inflate(context,R.layout.water_item_h,null);
                addView(linearLayout_h);
                len=list.get(i).length();
            }
            //添加TextView
            View view=  View.inflate(context,R.layout.water_item,null);
            TextView textView = view.findViewById(R.id.text);
            textView.setText(list.get(i));
            linearLayout_h.addView(view);
            //设置权重
            LinearLayout.LayoutParams layoutParams= (LayoutParams) view.getLayoutParams();
            layoutParams.weight=1;
            view.setLayoutParams(layoutParams);
            final int index=i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, list.get(index), Toast.LENGTH_SHORT).show();
                }
            });
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
