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

package es.pablomacias.esnuex_app.ui.detail.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import es.pablomacias.esnuex_app.ui.detail.activity.Detail_interface;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class DetailPresenter {
    private Context context;
    private String type;
    private final String TAG = this.getClass().getSimpleName();
    private Detail_interface detail_interface;

    public DetailPresenter(Context context, Intent intent, Detail_interface detail_interface) {
        this.context = context;
        this.detail_interface = detail_interface;
        createEtcObject(intent);
    }

    private void createEtcObject(Intent intent) {
        String[] information = intent.getStringArrayExtra("information");
        if (information.length > 0) {
            detail_interface.setImage(Uri.parse(information[0]));
            detail_interface.setTitle(information[1]);
            detail_interface.setSubtitle(information[2]);
            detail_interface.setDescription(information[3]);
            detail_interface.setPlace(information[4]);
        }


    }

}
