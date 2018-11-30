package guopuran.bwie.com.app5;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        final FlowLayout flow=findViewById(R.id.flow);
        final EditText edit=findViewById(R.id.edit);
        final Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=new TextView(MainActivity.this);
                tv.setText(edit.getText());
                tv.setTextColor(Color.RED);
                tv.setTextSize(20);
                tv.setBackgroundResource(R.drawable.bd_bb);
                flow.addView(tv);
            }
        });
    }
}
