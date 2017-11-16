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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.BaseActivity;
import es.pablomacias.esnuex_app.ui.drawer.adapter.MenuLinkAdapter;
import es.pablomacias.esnuex_app.ui.drawer.presenter.DrawerPresenter;
import es.pablomacias.esnuex_app.ui.main.fragments.Home_Fragment;
import es.pablomacias.esnuex_app.ui.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MainInterface {
    @BindView(R.id.toolbar_drawer)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.drawer_list)
    ListView listView;

    DrawerPresenter drawerPresenter;
    MainPresenter mainPresenter;
    MenuLinkAdapter menuLinkAdapter;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        drawerPresenter = new DrawerPresenter(getBaseContext());
        mainPresenter = new MainPresenter(getBaseContext(), drawerPresenter, this);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        menuLinkAdapter = new MenuLinkAdapter(this, R.layout.drawer_list_item, drawerPresenter.getLinks());

        listView.setAdapter(menuLinkAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setItemChecked(i, true);
                mainPresenter.onLinkClick(i);
            }
        });

        if (savedInstanceState == null) {
            openFragment(new Home_Fragment());
        }
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().addToBackStack(null);// begin  FragmentTransaction
        ft.add(R.id.main_content_frame, fragment);
        ft.commit();
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    public void closeDrawer() {
        drawer.closeDrawers();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
