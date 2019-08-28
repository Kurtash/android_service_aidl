package com.android.service.one;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.service.two.AIDLTwoServiceOne;

/**
 * Created by DELL on 2019/8/16.
 */

public class OneService extends Service{

    final String TAG = "OneService";

    private int mode = 0;

    private AIDLTwoServiceOne aidlTwoServiceOne;

    public OneService() {
        super();
        Log.d(TAG,"OneService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");

        mode = intent.getIntExtra("mode",0);
        Log.d(TAG,"onStartCommand:"+mode);

//        if (mode == 0){
//            return new MyBind();
//        }

        if (mode == 1){
            Intent intent_two = new Intent();
            intent_two.setPackage("com.android.service.one");
            intent_two.setAction("TwoService");
            startService(intent_two);
        }

        if (mode == 2){
            Intent intent_two = new Intent();
            intent_two.setPackage("com.android.service.two");
            intent_two.setAction("com.android.service.two.AIDLTwoServiceOne");
            startService(intent_two);
            bindService(intent_two,TwoServiceOne,BIND_AUTO_CREATE);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");

        return new MyBind();
    }

    public class MyBind extends Binder{
        public int num = 100;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
            mCallBackListener.callBack(num);
        }

        public OneService getService(){
            return OneService.this;
        }
    }

    public interface CallBackListener{
        void callBack(int index);
    }
    public CallBackListener mCallBackListener;
    public void setOnCallBackListener(CallBackListener mCallBackListener){
        this.mCallBackListener = mCallBackListener;
    }

    private ServiceConnection TwoServiceOne = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnected");
            aidlTwoServiceOne = AIDLTwoServiceOne.Stub.asInterface(iBinder);
            try {
                aidlTwoServiceOne.successed();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"onServiceDisconnected");
        }
    };
}
