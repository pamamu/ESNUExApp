/*
 * Copyright (C) 2017 by Pablo Macias Munoz
 * pamaciasm@alumnos.unex.es
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 *
 * the Free Software Foundation; either version 2 of the License,
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public Lice
 * along with this program; if not, write to the
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package es.pablomacias.esnuex_app.ui.management.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.BaseActivity;
import es.pablomacias.esnuex_app.ui.management.presenter.ManagementFormPresenter;

public class ManagementActivityForm extends BaseActivity implements ManagementFormInterface {

    private int mYear, mMonth, mDay, mHour, mMinute;
    @BindView(R.id.toolbar_text)
    TextView toolbar_text;
    @BindView(R.id.input_date)
    EditText editText;
    @BindView(R.id.input_time)
    EditText timeText;

    ManagementFormPresenter managementFormPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managementFormPresenter = new ManagementFormPresenter(getApplicationContext(), this, getIntent());
    }


    @OnClick(R.id.input_date)
    void showDatePicker() {
//        DialogFragment dialogFragment = new DatePickerFragment();
//        dialogFragment.show(getFragmentManager(), "datePicker");
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> editText.setText(String.format(Locale.UK, "%d/%d/%d", dayOfMonth, monthOfYear + 1, year)), mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    @OnClick(R.id.input_time)
    void showTimePicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> timeText.setText(hourOfDay + ":" + minute), mHour, mMinute, false);
        timePickerDialog.show();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_management_form;
    }

    @Override
    public void setTitle(String title, String action) {
        toolbar_text.setText(action + " " + title + " FORM");
    }
}
