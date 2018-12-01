package guopuran.bwie.com.app6;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText=findViewById(R.id.edit);
        Button button=findViewById(R.id.button);
        final CustomView customView=findViewById(R.id.cust);
        Button button1=findViewById(R.id.button02);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView textView = new TextView(MainActivity.this);
                textView.setTextSize(20);
                textView.setText(editText.getText());
                textView.setBackgroundResource(R.drawable.bg_bd);
                List<String> select = Dao.getInstance(MainActivity.this).select();
                Dao.getInstance(MainActivity.this).add(editText.getText().toString());
                textView.setTextColor(Color.parseColor("#cccfff"));
                customView.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View  v) {
                        Toast.makeText(MainActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //查询
        List<String> list = Dao.getInstance(MainActivity.this).select();
        for (int i=0;i<list.size();i++){
            TextView textView = new TextView(MainActivity.this);
            textView.setText(list.get(i));
            customView.addView(textView);
        }
        //删除
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dao.getInstance(MainActivity.this).del();
            }
        });
    }
}
