package com.example.myapplication.data;

import com.example.myapplication.pojo.PostModel;

import java.util.List;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
    public Single<List<PostModel>> getPosts();
}
