package com.example.myapplication.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.PostsClient;
import com.example.myapplication.pojo.PostModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();
    public  void getPosts(){
       Single observable= PostsClient.getINSTANCE().getPosts()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o-> postsMutableLiveData.setValue((List<PostModel>) o),e->  Log.d("Error","Erorr"+e));
    }
}
