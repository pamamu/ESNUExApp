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

import java.util.ArrayList;

import es.pablomacias.esnuex_app.common.utils.models.Link;
import es.pablomacias.esnuex_app.ui.drawer.presenter.DrawerPresenter;

/**
 * Created by pablomaciasmu on 15/11/17.
 */

public class MainPresenter {
    private Context context;
    private DrawerPresenter drawerPresenter;
    private ArrayList<Link> links;

    public MainPresenter(Context context, DrawerPresenter drawerPresenter) {
        this.context = context;
        this.drawerPresenter = drawerPresenter;
        links = drawerPresenter.getLinks();
    }

    public void onLinkClick(int pos) {
        Link link_pressed = links.get(pos);
    }


}
