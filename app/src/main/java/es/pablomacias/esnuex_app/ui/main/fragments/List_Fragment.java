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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.utils.FragmentsEnum;
import es.pablomacias.esnuex_app.data.db.entity.EtcType;
import es.pablomacias.esnuex_app.ui.detail.activity.DetailActivity;
import es.pablomacias.esnuex_app.ui.main.activity.Etc_Item_Listener;
import es.pablomacias.esnuex_app.ui.main.adapter.EtcList_Adapter;
import es.pablomacias.esnuex_app.ui.main.presenter.List_Presenter;

/**
 * Created by pablomaciasmu on 16/11/17.
 */

public class List_Fragment extends Fragment implements Etc_Item_Listener, EtcListInterface {
    private static final String TAG = List_Fragment.class.getName();

    @BindView(R.id.etc_list)
    RecyclerView item_list;

    RecyclerView.LayoutManager manager;
    private EtcList_Adapter adapter;
    private static final String fragmentType = "textForView";
    private static final String fragmentDelegation = "delegation";
    private String type;
    private int delegation;
    private Context context;
    private List_Presenter listPresenter;

    public List_Fragment() {
    }

    public static List_Fragment newInstance(FragmentsEnum fragmentsEnum, int delegation) {
        Bundle data = new Bundle();
        data.putString(fragmentType, fragmentsEnum.name());
        data.putInt(fragmentDelegation, delegation);
        List_Fragment list_fragment = new List_Fragment();
        list_fragment.setArguments(data);
        return list_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        listPresenter = new List_Presenter(context, this);
        if (bundle != null) {
            Log.i(TAG, "onCreate: " + bundle.getString(fragmentType));
            this.type = bundle.getString(fragmentType);
            this.delegation = bundle.getInt(fragmentDelegation);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_etc_list, container, false);
        ButterKnife.bind(this, view);

        List<EtcType> elements = listPresenter.getElements(this.type, this.delegation);

        item_list.setHasFixedSize(true);
        manager = new LinearLayoutManager(getContext());
        item_list.setLayoutManager(manager);
        adapter = new EtcList_Adapter(elements, getContext(), this);
        item_list.setAdapter(adapter);


        return view;
    }

    @Override
    public void itemclicked(EtcType item) {
        Intent intent = new Intent(context, DetailActivity.class);
        String information[] = {
                item.getImage(),
                item.getName(), item.getSubtitle(),
                item.getDescription(), item.getAddress(), type
        };
        intent.putExtra("information", information);
        startActivity(intent);
    }
}
