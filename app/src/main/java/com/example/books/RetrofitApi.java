package com.example.books;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {

        @GET("Books/{ID}")
        Call<DataModal> getData(@Path("ID") int ID);

        @POST("Books")
        Call<DataModal> createPost (@Body DataModal dataModal);

        @PUT("Books/{ID}")
        Call<DataModal> updateData(@Query("ID") int ID, @Body DataModal dataModal);
        @DELETE ("Books/{ID}")
        Call<DataModal> deleteData(@Path("ID") int ID);
}
