package simple.xjh.com.customview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import simple.xjh.com.customview.widget.CustomView07;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class Custom07Actvity extends Activity{

    private CustomView07 topBar;

    private void assignViews() {
        topBar = (CustomView07) findViewById(R.id.topBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom07_layout);
        assignViews();
        topBar.setOnClickListener(new CustomView07.OnClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(getApplicationContext(),"left",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
