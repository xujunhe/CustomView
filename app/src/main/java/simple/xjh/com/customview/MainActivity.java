package simple.xjh.com.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import simple.xjh.com.customview.widget.CustomView01;

public class MainActivity extends AppCompatActivity {



    private Button btnText;
    private Button btnImageText;
    private Button btnCircl;
    private Button btnSound;

    private void assignViews() {
        btnText = (Button) findViewById(R.id.btn_text);
        btnImageText = (Button) findViewById(R.id.btn_ImageText);
        btnCircl = (Button) findViewById(R.id.btn_circl);
        btnSound = (Button) findViewById(R.id.btn_sound);
    }



    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        setListener();
    }

    private void setListener() {
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Custom01Activity.class));
            }
        });
        btnImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Custom02Activity.class));
            }
        });
        btnCircl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Custom03Activity.class));
            }
        });

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Custom04Activity.class));
            }
        });
    }
}
