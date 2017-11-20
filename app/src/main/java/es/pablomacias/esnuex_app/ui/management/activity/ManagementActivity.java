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

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.BaseActivity;
import es.pablomacias.esnuex_app.data.db.entity.EtcType;
import es.pablomacias.esnuex_app.ui.main.activity.Etc_Item_Listener;
import es.pablomacias.esnuex_app.ui.main.adapter.EtcList_Adapter;
import es.pablomacias.esnuex_app.ui.management.presenter.ManagementPresenter;

public class ManagementActivity extends BaseActivity implements ManagementInterface, Etc_Item_Listener {

    private ManagementPresenter managementPresenter;
    RecyclerView.LayoutManager manager;
    private EtcList_Adapter adapter;


    @BindView(R.id.etc_list)
    RecyclerView item_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managementPresenter = new ManagementPresenter(getApplicationContext(), this, getIntent());

        updateUI();


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_etc_list;
    }

    @Override
    public void itemclicked(EtcType item) {
        managementPresenter.removeItem(item);
    }

    @Override
    public void updateUI() {
        List<EtcType> elements = managementPresenter.getElements();

        item_list.setHasFixedSize(true);
        manager = new LinearLayoutManager(getApplicationContext());
        item_list.setLayoutManager(manager);
        adapter = new EtcList_Adapter(elements, getApplicationContext(), this);
        item_list.setAdapter(adapter);
    }
}
