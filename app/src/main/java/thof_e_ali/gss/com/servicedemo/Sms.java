package thof_e_ali.gss.com.servicedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Sms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Log.d("Sms Activity","Started");

        SmsReciever.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                Log.d("message",messageText);
                Toast.makeText(Sms.this,messageText,Toast.LENGTH_LONG).show();
            }
        });
    }
}
