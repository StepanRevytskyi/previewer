package com.arondillqs5328.moappspreviewer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arondillqs5328.moappspreviewer.R;
import com.arondillqs5328.moappspreviewer.adapter.AppAdapter;
import com.arondillqs5328.moappspreviewer.common.Common;
import com.arondillqs5328.moappspreviewer.model.ApplicationListPesponse;
import com.arondillqs5328.moappspreviewer.model.UserApp;
import com.arondillqs5328.moappspreviewer.retrofit.IApi;
import com.arondillqs5328.moappspreviewer.retrofit.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppListActivity extends AppCompatActivity {

    @BindView(R.id.app_list_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SharedPreferences mPreferences;
    private IApi mApi;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AppListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        mApi = retrofit.create(IApi.class);

        mPreferences = getSharedPreferences(Common.APP_REFERENCES, MODE_PRIVATE);

        loadUserAppList();

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void loadUserAppList() {
        UserApp userApp = new UserApp(0, 1000, 0, mPreferences.getString(Common.APP_REFERENCES_USER_TOKEN, null));
        Call<ApplicationListPesponse> call = mApi.getUserApplications(userApp);
        call.enqueue(new Callback<ApplicationListPesponse>() {
            @Override
            public void onResponse(Call<ApplicationListPesponse> call, Response<ApplicationListPesponse> response) {
                if (response.isSuccessful()) {
                    mAdapter = new AppAdapter(response.body().getData());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ApplicationListPesponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                logOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logOut() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Common.APP_REFERENCES_USER_TOKEN, null);
        editor.apply();

        Intent intent = LoginActivity.newInstance(this);
        startActivity(intent);
        finish();
    }
}
