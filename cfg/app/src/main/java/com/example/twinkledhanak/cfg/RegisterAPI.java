package com.example.twinkledhanak.cfg;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by twinkle dhanak on 7/25/2017.
 */

    public interface RegisterAPI
{
        @FormUrlEncoded
        @POST("/insert.php")
        public void insertUser(
                @Field("name") String name,
                @Field("age") int age ,

                Callback<Response> callback);
}

