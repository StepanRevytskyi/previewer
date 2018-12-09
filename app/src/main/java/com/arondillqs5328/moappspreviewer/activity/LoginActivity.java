package com.arondillqs5328.moappspreviewer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arondillqs5328.moappspreviewer.R;
import com.arondillqs5328.moappspreviewer.common.Common;
import com.arondillqs5328.moappspreviewer.model.LoginResponse;
import com.arondillqs5328.moappspreviewer.model.User;
import com.arondillqs5328.moappspreviewer.retrofit.IApi;
import com.arondillqs5328.moappspreviewer.retrofit.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_editText) EditText mEmailEditText;
    @BindView(R.id.password_editText) EditText mPasswordEditText;

    private SharedPreferences mPreferences;
    private IApi mApi;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        mApi = retrofit.create(IApi.class);

        mPreferences = getSharedPreferences(Common.APP_REFERENCES, MODE_PRIVATE);
        if (mPreferences.contains(Common.APP_REFERENCES_USER_TOKEN)) {
            Log.i("TAG", mPreferences.getString(Common.APP_REFERENCES_USER_TOKEN, "error"));
            navigateToAppList();
        }
    }

    @OnClick(R.id.log_in_button)
    void userAuthorization() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        if (!isFieldEmpty(email, password)) {
            User user = new User(email, password);
            Call<LoginResponse> call = mApi.authorizeUser(user);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        saveUserToken(loginResponse);
                        navigateToAppList();
                    } else {
                        //TODO: auth failed
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });


        }
    }

    private void saveUserToken(LoginResponse loginResponse) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Common.APP_REFERENCES_USER_TOKEN, loginResponse.getData());
        editor.apply();
    }

    private void navigateToAppList() {
        Intent intent = AppListActivity.newInstance(this);
        startActivity(intent);
        finish();
    }

    private boolean isFieldEmpty(String email, String password) {
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            return true;
        }
        return false;
    }

    @OnClick(R.id.info_textView)
    void showInfoAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        View view = getLayoutInflater().inflate(R.layout.info_dialog, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button ok = view.findViewById(R.id.ok_btn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
