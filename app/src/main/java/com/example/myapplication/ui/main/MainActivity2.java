package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMain2Binding;
import com.example.myapplication.pojo.PostModel;

import java.util.List;


public class MainActivity2 extends AppCompatActivity {
    PostViewModel postViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        postViewModel= ViewModelProviders.of(this).get(PostViewModel.class);
        final PostsAdapter adapter = new PostsAdapter();
        postViewModel.getPosts();
        binding.recycler.setAdapter(adapter);

        postViewModel.postsMutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                adapter.setList(postModels);
            }
        });


    }
}