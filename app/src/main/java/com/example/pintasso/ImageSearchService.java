package com.example.pintasso;

import android.util.Log;

import com.example.pintasso.event.EventBusManager;
import com.example.pintasso.event.SearchResultEvent;
import com.example.pintasso.model.UnsplashResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ImageSearchService {

    public static ImageSearchService INSTANCE = new ImageSearchService();

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

    public void searchImageFromQuery(final String search) {
        Log.d("SEARCHSERVICE", "Hello from searchImageFromQuery : " + search);
        // Call to the REST service
        pImageSearchRESTService.searchForImages(search).enqueue(new Callback<UnsplashResult>() {
            @Override
            public void onResponse(Call<UnsplashResult> call, Response<UnsplashResult> response) {
                // Post an event so that listening activities can update their UI
                if (response.body() != null && response.body().results != null) {
                    Log.d("SEARCHSERVICE", "body not null");
                    EventBusManager.BUS.post(new SearchResultEvent(response.body().results));
                } else {
                    Log.d("SEARCHSERVICE", "body null");
                    Log.d("SEARCHSERVICE", "call : " + call.toString());
                    Log.d("SEARCHSERVICE", "response : " + response.toString());
                    Log.d("SEARCHSERVICE", "body results : " + response.body().results);


                    // Null result
                    // We may want to display a warning to user (e.g. Toast)
                    //TODO : warning
                }
            }

            @Override
            public void onFailure(Call<UnsplashResult> call, Throwable t) {
                Log.d("SEARCHSERVICE", "fail");
                // Request has failed or is not at expected format
                // We may want to display a warning to user (e.g. Toast)
                //TODO : error
            }
        });
    }



    // Service describing the REST APIs
    public interface ImageSearchRESTService {

        //TODO : move client_id
        @GET("search?client_id=BhkFJwZmhXB4-Dkza6Be6LWisQ46Yq_QqFSopOv1MkQ")
        Call<UnsplashResult> searchForImages(@Query("query") String search);
    }


}
