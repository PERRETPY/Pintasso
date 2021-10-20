package com.example.pintasso;

import com.example.pintasso.model.UnsplashResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ImageSearchService {

    private ImageSearchRESTService pImageSearchRESTService;

    private ImageSearchService() {
        // Create GSON Converter that will be used to convert from JSON to Java
        Gson gsonConverter = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation().create();

        // Create Retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                // Using OkHttp as HTTP Client
                .client(new OkHttpClient())
                // Having the following as server URL
                .baseUrl("https://api.unsplash.com")
                // Using GSON to convert from Json to Java
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();

        // Use retrofit to generate our REST service code
        this.pImageSearchRESTService = retrofit.create(ImageSearchRESTService.class);
    }



    // Service describing the REST APIs
    public interface ImageSearchRESTService {

        //TODO : move client_id
        @GET("search?client_id=BhkFJwZmhXB4-Dkza6Be6LWisQ46Yq_QqFSopOv1MkQ")
        Call<UnsplashResult> searchForImages(@Query("query") String search);
    }


}
