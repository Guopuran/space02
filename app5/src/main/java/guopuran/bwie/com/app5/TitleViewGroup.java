package guopuran.bwie.com.app5;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class TitleViewGroup extends LinearLayout {
    private Context context;
    public TitleViewGroup(Context context) {
        super(context);
        this.context=context;
    }

    public TitleViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        View view=View.inflate(context,R.layout.title_viewgroup,null);
    }
}
