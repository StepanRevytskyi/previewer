package com.arondillqs5328.moappspreviewer.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arondillqs5328.moappspreviewer.activity.AppWebActivity;
import com.arondillqs5328.moappspreviewer.R;
import com.arondillqs5328.moappspreviewer.model.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    private List<Datum> mDatumList;

    public AppAdapter(List<Datum> list) {
        mDatumList = list;
    }


    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        AppViewHolder holder = new AppViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AppViewHolder appViewHolder, final int i) {
        appViewHolder.bind(mDatumList.get(i));
        appViewHolder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AppWebActivity.newInstance(v.getContext(), mDatumList.get(i).getApplicationUrl());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatumList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.app_icon_imageView) ImageView mAppIcon;
        @BindView(R.id.app_name_TextView) TextView mAppName;
        @BindView(R.id.app_status_imageView) ImageView mAppStatusIcon;
        @BindView(R.id.app_status_textView) TextView mAppStatus;
        @BindView(R.id.app_paid_status_textView) TextView mAppPaidStatus;

        @BindView(R.id.item) ConstraintLayout mConstraintLayout;

        @BindDrawable(R.drawable.img) Drawable mImageLoadFailed;
        @BindDrawable(R.drawable.icon_nezakoncheno) Drawable mIconNezakoncheno;
        @BindDrawable(R.drawable.icon_zakoncheno) Drawable mIconZakoncheno;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Datum datum) {
            Picasso.get()
                    .load(datum.getApplicationIcoUrl())
                    .error(mImageLoadFailed)
                    .into(mAppIcon);
            mAppName.setText(datum.getApplicationName());
            if (datum.getPayment().equals("false")) {
                mAppPaidStatus.setText("Оплачено");
            } else {
                mAppPaidStatus.setText("Не оплачено");
            }
            if (datum.getApplicationStatus().equals("false")) {
                mAppStatus.setText("Завершено");
                mAppStatusIcon.setImageDrawable(mIconZakoncheno);
            } else {
                mAppStatus.setText("Не завершено");
                mAppStatusIcon.setImageDrawable(mIconNezakoncheno);
            }
        }
    }
}
