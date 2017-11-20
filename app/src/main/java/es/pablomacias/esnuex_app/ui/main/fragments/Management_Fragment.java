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

package es.pablomacias.esnuex_app.ui.main.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.ui.management.activity.ManagementActivity;
import es.pablomacias.esnuex_app.ui.management.activity.ManagementActivityForm;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class Management_Fragment extends Fragment {
    private static final String TAG = Management_Fragment.class.getSimpleName();

    @BindView(R.id.add_but1)
    ImageView add_1;
    @BindView(R.id.edit_but1)
    ImageView edit_1;
    @BindView(R.id.remove_but1)
    ImageView remove_1;
    @BindView(R.id.add_but2)
    ImageView add_2;
    @BindView(R.id.edit_but2)
    ImageView edit_2;
    @BindView(R.id.remove_but2)
    ImageView remove_2;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @OnClick(R.id.remove_but1)
    public void openManagement1() {
        Intent intent = new Intent(context, ManagementActivity.class);
        intent.putExtra("type", "TRIP");
        startActivity(intent);
    }

    @OnClick(R.id.remove_but2)
    public void openManagement2() {
        Intent intent = new Intent(context, ManagementActivity.class);
        intent.putExtra("type", "EVENT");
        startActivity(intent);
    }

    @OnClick(R.id.add_but1)
    public void openFormManagementAddTrip() {
        Intent intent = new Intent(context, ManagementActivityForm.class);
        intent.putExtra("action", "NEW");
        intent.putExtra("type", "TRIP");
        startActivity(intent);
    }

    @OnClick(R.id.add_but2)
    public void openFormManagementAddEvent() {
        Intent intent = new Intent(context, ManagementActivityForm.class);
        intent.putExtra("action", "UPDATE");
        intent.putExtra("type", "EVENT");
        startActivity(intent);
    }


    @OnClick(R.id.edit_but1)
    public void openFormManagementEditTrip() {
        Intent intent = new Intent(context, ManagementActivityForm.class);
        intent.putExtra("action", "EDIT");
        intent.putExtra("type", "TRIP");
        startActivity(intent);
    }

    @OnClick(R.id.edit_but2)
    public void openFormManagementEditEvent() {
        Intent intent = new Intent(context, ManagementActivityForm.class);
        intent.putExtra("action", "ADD");
        intent.putExtra("type", "EVENT");
        startActivity(intent);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_management_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
