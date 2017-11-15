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

package es.pablomacias.esnuex_app.ui.drawer.presenter;

import android.content.Context;

import java.util.ArrayList;

import es.pablomacias.esnuex_app.common.utils.LinkPreferencesUtil;
import es.pablomacias.esnuex_app.common.utils.UserPreferencesUtil;
import es.pablomacias.esnuex_app.common.utils.models.Link;

/**
 * Created by pablomaciasmu on 15/11/17.
 */

public class DrawerPresenter {
    private Context context;
    private LinkPreferencesUtil linkPreferencesUtil;


    public DrawerPresenter(Context context) {
        this.context = context;
        this.linkPreferencesUtil = new LinkPreferencesUtil(context);
    }

    public ArrayList<Link> getLinks() {
        ArrayList<Link> list = linkPreferencesUtil.getLinks();
        ArrayList<Link> filteredList = new ArrayList<>();
        UserPreferencesUtil userPreferencesUtil = new UserPreferencesUtil(context);
        if (userPreferencesUtil.getUser() == null) {
            for (Link link : list) {
                if (!link.isProtect())
                    filteredList.add(link);
            }
            return filteredList;
        }
        list.remove(list.size() - 1);
        return list;
    }

    public void onClick(String link) {

    }

}
