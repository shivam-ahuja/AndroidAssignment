package com.ahujasahab.androidassignment;

import java.util.List;
import java.util.Observable;

import retrofit2.http.GET;

/**
 * Created by hp on 18-08-2017.
 */

public interface MyApi {
    @GET("tutorial/jsonparsetutorial.txt")
    io.reactivex.Observable<DataPojoClass> getAllData();
}
