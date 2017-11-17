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

package es.pablomacias.esnuex_app.ui.main.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;

import es.pablomacias.esnuex_app.common.utils.FragmentsEnum;
import es.pablomacias.esnuex_app.common.utils.UserPreferencesUtil;
import es.pablomacias.esnuex_app.common.utils.models.Link;
import es.pablomacias.esnuex_app.ui.drawer.presenter.DrawerPresenter;
import es.pablomacias.esnuex_app.ui.main.activity.MainInterface;
import es.pablomacias.esnuex_app.ui.main.fragments.Information_Fragment;
import es.pablomacias.esnuex_app.ui.main.fragments.Management_Fragment;
import es.pablomacias.esnuex_app.ui.main.fragments.TabFragment;

/**
 * Created by pablomaciasmu on 15/11/17.
 */

public class MainPresenter {
    private Context context;
    private DrawerPresenter drawerPresenter;
    private final MainInterface mainInterface;
    private ArrayList<Link> links;
    private final String TAG = this.getClass().getSimpleName();
    UserPreferencesUtil userPreferencesUtil;

    public MainPresenter(Context context, DrawerPresenter drawerPresenter, MainInterface mainInterface) {
        this.mainInterface = mainInterface;
        this.context = context;
        this.drawerPresenter = drawerPresenter;
        links = drawerPresenter.getLinks();
        userPreferencesUtil = new UserPreferencesUtil(context);
    }

    public void onLinkClick(int pos) {
        Link link_pressed = links.get(pos);
        Fragment fragment = null;
        Log.i(TAG, "onLinkClick: ITEM" + pos);
        switch (pos) {
            case 0: //ESN UEX
                fragment = new Information_Fragment();
                mainInterface.setTitle(link_pressed.getName());
                break;
            case 1: //Events
                fragment = TabFragment.newInstance(FragmentsEnum.EVENT);
//                fragment = List_Fragment.newInstance(FragmentsEnum.EVENT, 0);
                mainInterface.setTitle(link_pressed.getName());
                break;
            case 2: //Trips
                fragment = TabFragment.newInstance(FragmentsEnum.TRIP);
//                fragment = List_Fragment.newInstance(FragmentsEnum.TRIP, 0);
                mainInterface.setTitle(link_pressed.getName());
                break;
            case 3: //Partners
                fragment = TabFragment.newInstance(FragmentsEnum.PARTNER);
//                fragment = List_Fragment.newInstance(FragmentsEnum.PARTNER, 0);
                mainInterface.setTitle(link_pressed.getName());
                break;
            case 4: //Contact
                return;
            case 5:
                if (userPreferencesUtil.getUser() != null) { //Management
                    fragment = new Management_Fragment();
                    mainInterface.setTitle(link_pressed.getName());
                } else { //Sign in
                    mainInterface.openSignUpActivity();
                    return;
                }
                break;
            case 6:
                logout();
                return;
        }
        mainInterface.openFragment(fragment);
        mainInterface.closeDrawer();
    }

    public void logout() {
        userPreferencesUtil.clearUser();
        mainInterface.updateUI();
    }


}
