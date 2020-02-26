package com.example.asm_finalfood.Constans;

import com.example.asm_finalfood.Model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by haerul on 15/03/18.
 */

public interface ApiInterface {


    @GET("get_food.php")
    Call<List<Food>> getFood();

    @FormUrlEncoded
    @POST("add_food.php")
    Call<Food> insertfood(
            @Field("key") String key,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_food.php")
    Call<Food> updatefood(
            @Field("key") String key,
            @Field("id") int id,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description,
            @Field("create_day") String create_day,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_food.php")
    Call<Food> deletefood(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Food> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);

}
