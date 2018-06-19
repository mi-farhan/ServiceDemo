package thof_e_ali.gss.com.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Boolean wasScreenOn;

    BroadcastReceiver app = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn,btn2;
        btn=(Button)findViewById(R.id.btn_battery);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Battery.class);
                startActivity(intent);
            }
        });

        btn2=(Button)findViewById(R.id.btn_sms);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Sms.class);
                startActivity(intent);
            }
        });

    }


}
