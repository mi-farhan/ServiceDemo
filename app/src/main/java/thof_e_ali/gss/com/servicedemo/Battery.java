package thof_e_ali.gss.com.servicedemo;

import android.os.Bundle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Battery extends Activity {
    Button btn;
    IntentFilter intentfilter;
    int deviceStatus;
    String currentBatteryStatus="Battery Info";
    TextView tv;
    int lessCount=0;
    int enoughCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        btn=(Button)findViewById(R.id.btn_check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button","Pressed");
                registerReceiver(Battery.this.broadcastReceiver,intentfilter);
            }
        });
        tv=(TextView)findViewById(R.id.level);

    }

    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            Log.d("Battery", String.valueOf(deviceStatus));
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);


            tv.setText(Integer.toString(batteryLevel));
            if (batteryLevel <= 10 && lessCount==0){

                AlertDialog.Builder alertBuilder=new AlertDialog.Builder(Battery.this);
                alertBuilder.setMessage("Battery is Less Than 10%");
                AlertDialog alertDialog=alertBuilder.create();
                alertDialog.show();
                lessCount++;
            }else if (enoughCount==0){
                AlertDialog.Builder alertBuilder=new AlertDialog.Builder(Battery.this);
                alertBuilder.setMessage("Enough Battery");
                AlertDialog alertDialog=alertBuilder.create();
                alertDialog.show();
                enoughCount++;
            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
                Log.d("Battery","Charging");
                Toast.makeText(Battery.this,"Charging",Toast.LENGTH_LONG).show();

            }else if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){
                Log.d("Battery","Full");
                Toast.makeText(Battery.this,"Charging full",Toast.LENGTH_LONG).show();

            }else if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){
                Log.d("Battery","unkown");
                Toast.makeText(Battery.this,"Unknown",Toast.LENGTH_LONG).show();
            }else if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                Log.d("Battery","Not Charging");
                Toast.makeText(Battery.this,"Charger Removed",Toast.LENGTH_LONG).show();

            }else if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){
                Log.d("Battery","DisCharging");
                Toast.makeText(context,"Discharging",Toast.LENGTH_LONG).show();
            }else if(deviceStatus == BatteryManager.BATTERY_PLUGGED_USB){
                Log.d("Battery","usb Connected");
                Toast.makeText(context,"USB Connected",Toast.LENGTH_LONG).show();
            }

        }
    };
}
