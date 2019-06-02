package com.luv2code.android.uvindex.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luv2code.android.uvindex.R;
import com.luv2code.android.uvindex.dialog.UvIndexCardDialog;
import com.luv2code.android.uvindex.entity.Location;
import com.luv2code.android.uvindex.entity.UvIndex;
import com.luv2code.android.uvindex.view.UserActivity;
import com.luv2code.android.uvindex.viewmodel.UserViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.luv2code.android.uvindex.utils.AppConstants.UVINDEXCARD_DIALOG;

/**
 * Created by lzugaj on 5/31/2019
 */

public class UserUvIndexAdapter extends RecyclerView.Adapter<UserUvIndexAdapter.ViewHolder> {

    private List<UvIndex> uvIndexes;

    private UserViewModel userViewModel;

    private Context context;

    public UserUvIndexAdapter(List<UvIndex> uvIndexes, UserViewModel userViewModel, Context context) {
        this.uvIndexes = uvIndexes;
        this.userViewModel = userViewModel;
        this.context = context;
    }

    @NonNull
    @Override
    public UserUvIndexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uv_index, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserUvIndexAdapter.ViewHolder holder, int position) {
        final UvIndex uvIndex = uvIndexes.get(position);
        final Location location = userViewModel.findLocation(uvIndex);
        final UvIndex clickedCard = userViewModel.findUvIndex(uvIndex);

        holder.tvLocation.setText(location.getCityName());
        holder.tvUvIndexValue.setText(uvIndex.getUvIndex());
        holder.tvDateValue.setText(uvIndex.getMeasurementDate());
        holder.ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((UserActivity) context).getSupportFragmentManager();
                UvIndexCardDialog dialog = new UvIndexCardDialog(clickedCard, location.getCityName());
                dialog.show(fragmentManager, UVINDEXCARD_DIALOG);
                dialog.setCancelable(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uvIndexes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvLocation)
        TextView tvLocation;

        @BindView(R.id.tvUvIndexValue)
        TextView tvUvIndexValue;

        @BindView(R.id.tvDateValue)
        TextView tvDateValue;

        @BindView(R.id.ivInfo)
        ImageView ivInfo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
