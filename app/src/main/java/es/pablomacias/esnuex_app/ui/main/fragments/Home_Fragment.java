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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;
import es.pablomacias.esnuex_app.ui.main.adapter.NewsList_Adapter;

/**
 * Created by pablomaciasmu on 16/11/17.
 */

public class Home_Fragment extends Fragment {

    @BindView(R.id.news_list)
    RecyclerView newList;
    RecyclerView.LayoutManager manager;
    private NewsList_Adapter adapter;

    public Home_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_main, container, false);
        ButterKnife.bind(this, view);

        ArrayList<NewEntity> newEntities = new ArrayList<>();
        newEntities.add(new NewEntity("Noticia 1", null, Calendar.getInstance().getTime()));
        newEntities.add(new NewEntity("Noticia 2", null, Calendar.getInstance().getTime()));
        newEntities.add(new NewEntity("Noticia 3", null, Calendar.getInstance().getTime()));
        newEntities.add(new NewEntity("Noticia 4", null, Calendar.getInstance().getTime()));

        newList.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        newList.setLayoutManager(manager);
        adapter = new NewsList_Adapter(newEntities);
        newList.setAdapter(adapter);

        return view;
    }
}
