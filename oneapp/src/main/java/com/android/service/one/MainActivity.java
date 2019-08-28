package com.android.service.one;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String TAG_ONE = "OneService";

    private TextView showtxt;

    private OneService.MyBind myBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity2local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setPackage("com.android.service.one");
                intent.setAction("OneService");
                intent.putExtra("mode",0);
                startService(intent);
                bindService(intent,mainServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.local2local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setPackage("com.android.service.one");
                intent.setAction("OneService");
                intent.putExtra("mode",1);
                startService(intent);
            }
        });
        findViewById(R.id.local2other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setPackage("com.android.service.one");
                intent.setAction("OneService");
                intent.putExtra("mode",2);
                startService(intent);
                bindService(intent,mainServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.xiugai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBind.setNum(Integer.parseInt(showtxt.getText().toString()));
            }
        });
        showtxt = findViewById(R.id.showtxt);

    }

    private ServiceConnection mainServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG_ONE,"onServiceConnected");
            myBind = (OneService.MyBind) iBinder;
//            Toast.makeText(MainActivity.this,myBind.num+"",Toast.LENGTH_SHORT).show();
            showtxt.setText(myBind.num+"");
            myBind.getService().setOnCallBackListener(new OneService.CallBackListener() {
                @Override
                public void callBack(int index) {
                    Toast.makeText(MainActivity.this,index+"",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG_ONE,"onServiceDisconnected");
        }
    };

}
