package com.android.service.one;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TwoService extends Service {

    final String TAG = "TwoService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
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
        return null;
    }

    public interface CallBackListener{
        void callBack(int index);
    }
    public OneService.CallBackListener mCallBackListener;
    public void setOnCallBackListener(OneService.CallBackListener mCallBackListener){
        this.mCallBackListener = mCallBackListener;
    }
}
