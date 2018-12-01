package guopuran.bwie.com.app7;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        final CustomView fl_search=findViewById(R.id.fl_search);
        final CustomView fl_hot=findViewById(R.id.fl_hot);
        TitleBarView title=findViewById(R.id.title);
        title.setButtonClickListener(new TitleBarView.OnButtonClickListener() {
            @Override
            public void onButtonClick(String str) {
                //随机字符串，当作唯一标识
                //当进行单个删除的时候
                UUID uuid = UUID.randomUUID();
                final TextView tv=new TextView(MainActivity.this);
                tv.setTag(uuid);
                tv.setTextColor(Color.parseColor("#cccfff"));
                tv.setText(str);
                //w往数据库里添加
                Dao.getInstance(MainActivity.this).add(new Bean(uuid.toString(),str));
                tv.setBackgroundResource(R.drawable.bg_bd);
                fl_search.addView(tv);
                //点击删除
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dao.getInstance(MainActivity.this).del(tv.getTag().toString());
                        fl_search.removeView(tv);
                    }
                });
            }
        });
        //查询
        List<Bean> select = Dao.getInstance(MainActivity.this).select();
        //循环添加
        for (int i=0;i<select.size();i++){
            final TextView textView=new TextView(MainActivity.this);
            textView.setText(select.get(i).getPass());
            textView.setTag(select.get(i).getName());
            textView.setTextColor(Color.parseColor("#ff6699"));
            textView.setBackgroundResource(R.drawable.bg_bd);
            fl_search.addView(textView);
            //删除历史
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dao.getInstance(MainActivity.this).del(v.getTag().toString());
                    fl_search.removeView(v);
                }
            });
        }
        //热门搜索
        for (int i=0;i<37;i++){
            TextView textView=new TextView(MainActivity.this);
            textView.setText("数据"+i);
            textView.setTextColor(Color.parseColor("#ff9966"));
            textView.setBackgroundResource(R.drawable.bg_bd);
            fl_hot.addView(textView);
        }
    }
}
