package guopuran.bwie.com.app3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        final EditText editText=findViewById(R.id.edit);
        Button button=findViewById(R.id.button);
        final WaterView waterView=findViewById(R.id.waterview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框文字
                String str=editText.getText().toString();
                //将文字放入列表
                list.add(str);
                //设置数据
                waterView.setList(list);

            }
        });
    }
}
