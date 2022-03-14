package com.example.myapplication.network.main;

import com.example.myapplication.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    @GET("posts")
    Flowable<List<Post>> getPostsFromUser (
            @Query("userId") int userId
    );
}
