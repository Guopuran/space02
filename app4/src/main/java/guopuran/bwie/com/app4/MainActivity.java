package guopuran.bwie.com.app4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> list=new ArrayList<>();
    private EditText edit;
    private Button button;
    private CustomWaterView customWaterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.edit);
        button = findViewById(R.id.button);
        customWaterView = findViewById(R.id.cust);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed = edit.getText().toString();
                list.add(ed);
                customWaterView.setList(list);
            }
        });
    }
}
