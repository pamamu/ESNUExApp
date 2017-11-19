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

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.BaseActivity;
import es.pablomacias.esnuex_app.common.utils.models.User;
import es.pablomacias.esnuex_app.ui.drawer.adapter.MenuLinkAdapter;
import es.pablomacias.esnuex_app.ui.drawer.presenter.DrawerPresenter;
import es.pablomacias.esnuex_app.ui.login.activity.SignupActivity;
import es.pablomacias.esnuex_app.ui.main.fragments.Home_Fragment;
import es.pablomacias.esnuex_app.ui.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MainInterface {
    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.toolbar_drawer)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.drawer_list)
    ListView listView;

    @BindView(R.id.main_content_frame)
    ViewGroup viewGroup;

    @BindView(R.id.profile_image)
    CircleImageView circleImageView;
    @BindView(R.id.profile_name)
    TextView profile_name;
    @BindView(R.id.profile_email)
    TextView profile_email;
    @BindView(R.id.drawer_header)
    LinearLayout linearLayout;

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
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        updateUI();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setItemChecked(i, true);
                mainPresenter.onLinkClick(i);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateUser() {
        User user = drawerPresenter.getUser();
        if (user != null) {
            circleImageView.setVisibility(View.VISIBLE);
            profile_email.setVisibility(View.VISIBLE);
            profile_name.setVisibility(View.VISIBLE);
            linearLayout.setBackground(this.getDrawable(R.color.esn_dark_blue));
            if (!user.getPhoto().isEmpty())
                Picasso.with(getApplicationContext())
                        .load(Uri.parse(user.getPhoto()))
                        .fit()
                        .placeholder(R.drawable.loading_image)
                        .error(R.drawable.esn)
                        .into(circleImageView);
            if (!user.getName().isEmpty()) {
                profile_name.setText(user.getName());
                profile_email.setText(user.getEmail());
            } else {
                profile_name.setText(user.getEmail());
                profile_email.setText("Sin Nombre - Sin fotografía");
            }
        } else {
            circleImageView.setVisibility(View.INVISIBLE);
            profile_email.setVisibility(View.INVISIBLE);
            profile_name.setVisibility(View.INVISIBLE);
            linearLayout.setBackground(this.getDrawable(R.drawable.esn_uex_color));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    public void openFragment(Fragment fragment) {
        Log.i(TAG, "onBackPressed: STACKINI " + getSupportFragmentManager().getBackStackEntryCount());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content_frame, fragment).addToBackStack(null);
        ft.commit();
        Log.i(TAG, "onBackPressed: STACKFIN " + getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onBackPressed() {
        drawer.closeDrawers();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            setTitle(getString(R.string.app_name));
            ft.replace(R.id.main_content_frame, new Home_Fragment());
            ft.commit();
        } else
            finish();
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    public void closeDrawer() {
        drawer.closeDrawers();
    }

    @Override
    public void openSignUpActivity() {
        startActivity(new Intent(MainActivity.this, SignupActivity.class));
    }


    public void updateUI() {
        drawer.closeDrawers();
        menuLinkAdapter = new MenuLinkAdapter(this, R.layout.drawer_list_item, drawerPresenter.getLinks());
        listView.setAdapter(menuLinkAdapter);
        setTitle(getString(R.string.app_name));
        openFragment(new Home_Fragment());
        viewGroup.invalidate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            updateUser();
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
