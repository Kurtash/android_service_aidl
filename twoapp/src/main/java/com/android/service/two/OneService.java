package com.android.service.two;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DELL on 2019/8/16.
 */

public class OneService extends Service{

    final String TAG = "OneService";

    private int mode = 0;

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

//        if (mode == 1){
//            Intent intent_two = new Intent();
//            intent_two.setPackage("com.android.service.one");
//            intent_two.setAction("TwoService");
//            startService(intent_two);
//        }
//
//        if (mode == 2){
//            Intent intent_two = new Intent();
//            intent_two.setPackage("com.android.service.two");
//            intent_two.setAction("TwoService");
//            startService(intent_two);
//        }

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

        return mIbinder;
    }

//    public class MyBind extends Binder{
//        public int num = 100;
//
//        public int getNum() {
//            return num;
//        }
//
//        public void setNum(int num) {
//            this.num = num;
//            mCallBackListener.callBack(num);
//        }
//
//        public OneService getService(){
//            return OneService.this;
//        }
//    }

    public interface CallBackListener{
        void callBack(int index);
    }
    public CallBackListener mCallBackListener;
    public void setOnCallBackListener(CallBackListener mCallBackListener){
        this.mCallBackListener = mCallBackListener;
    }

    AIDLTwoServiceOne.Stub mIbinder = new AIDLTwoServiceOne.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void successed() throws RemoteException {
            Log.d(TAG,"AIDLTwoServiceOne.Stub successed");
        }

        @Override
        public void failed() throws RemoteException {

        }
    };

}
