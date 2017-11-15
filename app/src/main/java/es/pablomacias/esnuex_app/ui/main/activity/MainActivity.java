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

package es.pablomacias.esnuex_app.ui.main.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import butterknife.BindView;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.BaseActivity;
import es.pablomacias.esnuex_app.ui.drawer.adapter.MenuLinkAdapter;
import es.pablomacias.esnuex_app.ui.drawer.presenter.DrawerPresenter;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar_drawer)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.drawer_list)
    ListView listView;

    DrawerPresenter drawerPresenter;
    MenuLinkAdapter menuLinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        drawerPresenter = new DrawerPresenter(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        menuLinkAdapter = new MenuLinkAdapter(this, R.layout.drawer_list_item, drawerPresenter.getLinks());

        listView.setAdapter(menuLinkAdapter);

    }

    public void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
