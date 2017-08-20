package com.ahujasahab.androidassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private ArrayList<Worldpopulation> mylist=new ArrayList<>();
    private static final String TAG = "MainActivity";
    private MyAdapter myRecyclerViewadapter;
    private ProgressBar mMyProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyProgressBar=(ProgressBar)findViewById(R.id.myProgressBar);
        mMyProgressBar.setVisibility(View.VISIBLE);
        myRecyclerView=(RecyclerView)findViewById(R.id.myrecyclerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Log.d(TAG,"in oncreate");
        myRecyclerViewadapter=new MyAdapter(MainActivity.this,mylist);
        myRecyclerView.setAdapter(myRecyclerViewadapter);
        getData();



    }

    public  void getData() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://www.androidbegin.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
   MyApi myapi=retrofit.create(MyApi.class);
        io.reactivex.Observable<DataPojoClass> observable = myapi.getAllData().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<DataPojoClass>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DataPojoClass value) {
            // Toast.makeText(MainActivity.this, "in on next", Toast.LENGTH_LONG).show();

                 mylist= (ArrayList<Worldpopulation>) value.getWorldpopulation();

                myRecyclerViewadapter=new MyAdapter(MainActivity.this,mylist);
                myRecyclerView.setAdapter(myRecyclerViewadapter);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"in on errror");
                mMyProgressBar.setVisibility(View.GONE);

                }

            @Override
            public void onComplete() {
                Log.d(TAG,"in oncomplete");
                mMyProgressBar.setVisibility(View.GONE);

            }
        });

    }


}
