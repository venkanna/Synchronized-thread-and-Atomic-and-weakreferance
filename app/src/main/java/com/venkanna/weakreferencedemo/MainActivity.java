package com.venkanna.weakreferencedemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private Object object = new Object();
    private Object getObject(){
        return object;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new TestAsysncTask(new WeakReference<Activity>(this)).execute();

        //Thread safe  and locking
        Object locker = new Object();
        synchronized (locker){
            //Logic ...

        }


        //Atomic object
        // for using success and failure thread counts
        //and threa safe and
        final AtomicInteger count = new AtomicInteger(0);
        final AtomicInteger errors = new AtomicInteger(0);

    }




    private class TestAsysncTask extends AsyncTask<Void, Void, Void> {

        private WeakReference<Activity> mactivity;

        public TestAsysncTask(WeakReference<Activity> activity) {
            mactivity = activity;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isCancelled()){

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(isCancelled()){
        return;
            }
            try {

                if (mactivity.get() != null) {
                    Toast.makeText(mactivity.get(), mactivity.get().getString(R.string.app_name)+getObject(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


}
