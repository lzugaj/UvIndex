package com.luv2code.android.uvindex.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.luv2code.android.uvindex.view.UserActivity;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Created by lzugaj on 5/31/2019
 */

public class DateFromDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String date = getDateFormat(dayOfMonth) + "." + getMonthFormat(monthOfYear + 1) + "." + year;
        UserActivity activity = (UserActivity) getActivity();
        assert activity != null;
        activity.setBtnDateFromText(date);
    }

    private String getDateFormat(int dayOfMonth) {
        if (dayOfMonth < 10) {
            return "0" + dayOfMonth;
        } else {
            return String.valueOf(dayOfMonth);
        }
    }

    private String getMonthFormat(int month) {
        if (month < 10) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
    }
}
