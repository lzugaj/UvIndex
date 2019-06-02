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
import com.luv2code.android.uvindex.entity.UvIndex;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lzugaj on 5/31/2019
 */

@SuppressLint("ValidFragment")
public class UvIndexCardDialog extends DialogFragment {

    @BindView(R.id.tvLocationInfo)
    TextView tvLocationInfo;

    @BindView(R.id.tvUvIndexInfo)
    TextView tvUvIndexInfo;

    @BindView(R.id.tvDateInfo)
    TextView tvDateInfo;

    private UvIndex uvIndex;

    private String locationName;

    public UvIndexCardDialog(UvIndex clickedCard, String locationName) {
        this.uvIndex = clickedCard;
        this.locationName = locationName;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.uvIndex_dialog_title) + locationName);

        LayoutInflater layoutInflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.uv_index_info, null);
        builder.setView(view);

        ButterKnife.bind(this, view);
        getCardContent(uvIndex);

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void getCardContent(UvIndex uvIndex) {
        tvLocationInfo.setText(locationName);
        tvUvIndexInfo.setText(uvIndex.getUvIndex());
        tvDateInfo.setText(uvIndex.getMeasurementDate());
    }
}
