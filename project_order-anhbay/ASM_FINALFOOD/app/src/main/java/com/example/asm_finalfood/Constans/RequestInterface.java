package com.example.asm_finalfood.Constans;

import com.example.asm_finalfood.Server.ServerRequest;
import com.example.asm_finalfood.Server.ServerResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("asm_rofood/")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
