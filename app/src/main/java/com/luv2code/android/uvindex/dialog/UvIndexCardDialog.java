package com.luv2code.android.uvindex.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.luv2code.android.uvindex.R;
import com.luv2code.android.uvindex.entity.Location;
import com.luv2code.android.uvindex.entity.UvIndex;
import com.luv2code.android.uvindex.viewmodel.UserViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lzugaj on 5/31/2019
 */

@SuppressLint("ValidFragment")
public class UvIndexCardDialog extends DialogFragment {

    @BindView(R.id.tvLocation)
    TextView tvLocation;

    @BindView(R.id.tvUvIndexValue)
    TextView tvUvIndexValue;

    @BindView(R.id.tvDateValue)
    TextView tvDateValue;

    private UvIndex uvIndex;

    private UserViewModel userViewModel;

    public UvIndexCardDialog(UvIndex clickedCard) {
        this.uvIndex = clickedCard;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Test");

        LayoutInflater layoutInflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.uv_index_info, null);
        builder.setView(view);

        ButterKnife.bind(this, view);

        getCardContent();

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void getCardContent() {
        Location location = userViewModel.findLocation(this.uvIndex);
        tvLocation.setText(location.getCityName());
        tvUvIndexValue.setText(uvIndex.getUvIndex());
        tvDateValue.setText(uvIndex.getMeasurementDate());
    }
}
