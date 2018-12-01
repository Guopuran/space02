package guopuran.bwie.com.app7;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TitleBarView extends LinearLayout {
    private Context context;
    public TitleBarView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        //添加布局
        View view=View.inflate(context,R.layout.title,null);
        final ImageView imageView=view.findViewById(R.id.search_title);
        final EditText editText=view.findViewById(R.id.edit_title);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //第六步，在将要回调的地方，首先判断非空
                if (onButtonClickListener!=null){
                    //第七部：执行回调方法，传参
                    onButtonClickListener.onButtonClick(editText.getText().toString());
                }
            }
        });
        addView(view);
    }
    //第三步：设置成员变量
    OnButtonClickListener onButtonClickListener;
    //第四步：传入，并且给成员变量赋值
    //第五步：在想要接受回调的地方，调用set方法，设置监听
    public void setButtonClickListener(OnButtonClickListener onButtonClickListener){
        this.onButtonClickListener = onButtonClickListener;
    }
    //第一步：定义一个接口
    public interface OnButtonClickListener{
        //第二步：写好方法和回传参数
        void onButtonClick(String str);
    }
}
