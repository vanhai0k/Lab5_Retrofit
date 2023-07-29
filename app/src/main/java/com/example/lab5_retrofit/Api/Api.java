package com.example.lab5_retrofit.Api;

import com.example.lab5_retrofit.model.Receiver;
import com.example.lab5_retrofit.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    Api api = new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api.class);

    @GET("users")
    Call<Receiver> getDanhsach();

    @DELETE("users/delete/{id}")
    Call<User> deleteUser(@Path("id") String id);

    @POST("users")
    Call<User> postData(@Body User user);

    @PUT("users/update/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);
}
