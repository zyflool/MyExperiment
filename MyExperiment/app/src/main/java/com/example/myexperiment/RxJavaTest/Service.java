package com.example.myexperiment.RxJavaTest;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Service {
    @GET("index")
    Observable<RBean> getdata(@Query("key") String key, @Query("title") String title);
}