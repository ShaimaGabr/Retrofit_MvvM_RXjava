package com.example.myapplication;

import androidx.annotation.CheckResult;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.Observer;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("CheckResult")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //cold vs hot
//       ConnectableObservable<Long> cold=ConnectableObservable.intervalRange(0,5,0,1, TimeUnit.SECONDS).publish();
//       cold.connect();
//        cold.subscribe(i-> Log.d("MainActivity","onCreate:output1"+i));
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        cold.subscribe(i-> Log.d("MainActivity","onCreate:output2"+i));

        /////////////////publish_Supject
        // PublishSubject<String> subject=PublishSubject.create();
//        BehaviorSubject<String> subject= BehaviorSubject.create();
//        subject.subscribe(i->Log.d("MainActivity","onCreat Student first"+i));
//        subject.onNext("A");
//        sleep(1000);
//        subject.onNext("B");
//        sleep(1000);
//        subject.onNext("C");
//        sleep(1000);
//        subject.onNext("D");
//        sleep(1000);
//        subject.subscribe(i->Log.d("MainActivity","onCreat Student second"+i));
//        subject.onNext("F");
//        sleep(1000);
//        subject.onNext("J");
//        sleep(1000);
//        subject.onNext("H");
//        sleep(1000);
//        subject.onNext("I");
//        sleep(1000);

        //Observer
//       Observer observer=new Observer() {
//           @Override
//           public void update(Observable observable, Object o) {
//
//           }
//       };

        /// Single Observer

//        io.reactivex.rxjava3.core.@NonNull Observable<Object> observable= io.reactivex.rxjava3.core.Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
//                for (int i=0;i<5;i++){
//                    emitter.onNext(i);
//                }
//                emitter.onComplete();
//
//            }
//        });
//        io.reactivex.rxjava3.core.Observer observer= new io.reactivex.rxjava3.core.Observer() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d("opserver","subscrip");
//
//            }
//
//            @Override
//            public void onNext(@NonNull Object o) {
//                Log.d("opserver","onNext");
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d("opserver","OnError");
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("opserver","onComplete");
//            }
//
//
//        };
//        observable.subscribe(observer);
        //////////////////////////////////////////////////////////////////////////////
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                binding.editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(charSequence.length()!=0)
                        emitter.onNext(charSequence);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
               }).doOnNext(c -> Log.d("final", "onUcreate" + c))
//                 .map(new Function<Object, Object>() {
//                    @Override
//                    public Object apply(Object o) throws Throwable {
//                        return Integer.parseInt(o.toString())*2;
//                    }
//                })
//                .debounce(2,TimeUnit.SECONDS)
//                .distinctUntilChanged()//
//                .filter(c->!c.toString().equals("Ali"))
                .flatMap(new Function<Object, ObservableSource<?>>() {

                    @Override
                    public ObservableSource<?> apply(Object o) throws Throwable {
                        return sendDataToAPI(o.toString());
                    }
                })
                .subscribe(s ->
                        {
                            Log.d("final", "onDcreate" + s);
                            sendDataToAPI(s.toString());
                        }
                );
    }

    public void sleep(int i) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Observable sendDataToAPI(String data){
        Observable observable=Observable.just("calling api 1 to send"+data);
        observable.subscribe(c -> Log.d("final", "onUcreate" + c));
        return observable;
    }
}