package com.arondillqs5328.moappspreviewer.retrofit;

import com.arondillqs5328.moappspreviewer.model.ApplicationListPesponse;
import com.arondillqs5328.moappspreviewer.model.LoginResponse;
import com.arondillqs5328.moappspreviewer.model.User;
import com.arondillqs5328.moappspreviewer.model.UserApp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IApi {

    @POST("api/account/login")
    Call<LoginResponse> authorizeUser(@Body User user);

    @POST("api/application")
    Call<ApplicationListPesponse> getUserApplications(@Body UserApp userApp);
}
