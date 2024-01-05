package com.example.twinkledhanak.cfg;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by twinkle dhanak on 7/25/2017.
 */

public interface GetBackAPI
{
    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/user.json")
    public void getUser(Callback<List<Person>> response);
}