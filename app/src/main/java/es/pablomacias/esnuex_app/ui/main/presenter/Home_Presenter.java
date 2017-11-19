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
import android.util.Log;

import java.util.List;

import es.pablomacias.esnuex_app.data.db.AppDatabase;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;
import es.pablomacias.esnuex_app.data.repository.NewRespository;
import es.pablomacias.esnuex_app.ui.main.fragments.Home_Interface;

/**
 * Created by pablomaciasmu on 16/11/17.
 */

public class Home_Presenter {
    private Context context;
    private final String TAG = this.getClass().getSimpleName();
    //    private Home_Interface home_interface;
    private NewRespository newRespository;

    public Home_Presenter(Context context, Home_Interface home_interface) {
        Log.i(TAG, "Home_Presenter: context = " + context);
        this.context = context;
//        this.home_interface = home_interface;
        newRespository = NewRespository.getInstance(AppDatabase.getAppDatabase(context));
    }

    public List<NewEntity> getNews() {
        return newRespository.getAll();
    }


}
