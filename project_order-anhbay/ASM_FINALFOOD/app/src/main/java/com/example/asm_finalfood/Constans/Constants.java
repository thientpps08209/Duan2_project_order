package com.example.asm_finalfood.Constans;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constants {


    public static final String Localhost     ="10.88.54.38";
    public static final String BASE_URL      ="http://"+Localhost+"";
    private static final String BASE_FOOD     ="http://"+Localhost+"/asm_rofood/product/";
    public static Retrofit retrofit;



    public static final String REGISTER_OPERATION        = "register";
    public static final String LOGIN_OPERATION           = "login";
    public static final String CHANGE_PASSWORD_OPERATION = "chgPass";

    public static final String SUCCESS      = "success";
    public static final String FAILURE      = "failure";
    public static final String IS_LOGGED_IN = "isLoggedIn";

    public static final String NAME      = "name";
    public static final String EMAIL     = "email";
    public static final String UNIQUE_ID = "unique_id";

    public static final String TAG = "LearnAPI";

    public static final String RESET_PASSWORD_INITIATE = "resPassReq";
    public static final String RESET_PASSWORD_FINISH   = "resPass";


    public static Retrofit getApiClient(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_FOOD)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }


}
