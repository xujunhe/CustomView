package simple.xjh.com.customview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<String> datas = Arrays.asList("自定义文本显示随机数","自定义图片文字显示",
            "圆环交替","音量控制","对TextView进行扩展","闪动的TextView","简易TopBar");
    private List<Class<? extends Activity>> activitys =  Arrays.asList
              (Custom01Activity.class,Custom02Activity.class,
                      Custom03Activity.class,Custom04Activity.class,
                      Custom05Activity.class,Custom06Activity.class,
                      Custom07Actvity.class);
    private ListView list;

    private ArrayAdapter adapter;


    private void assignViews() {
        list = (ListView) findViewById(R.id.list);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        setListener();
    }

    private void setListener() {
        adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,datas);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(activitys.get(position));
            }
        });
    }


    private void startActivity(Class<? extends Activity> c)
    {
        startActivity(new Intent(MainActivity.this,c));
    }
}
