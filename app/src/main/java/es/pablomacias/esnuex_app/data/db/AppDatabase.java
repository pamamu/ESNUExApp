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

package es.pablomacias.esnuex_app.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import es.pablomacias.esnuex_app.data.db.converter.DateConverter;
import es.pablomacias.esnuex_app.data.db.converter.UriConverter;
import es.pablomacias.esnuex_app.data.db.dao.DelegationDao;
import es.pablomacias.esnuex_app.data.db.dao.EventDao;
import es.pablomacias.esnuex_app.data.db.dao.NewDao;
import es.pablomacias.esnuex_app.data.db.dao.PartnerDao;
import es.pablomacias.esnuex_app.data.db.dao.TripDao;
import es.pablomacias.esnuex_app.data.db.entity.DelegationEntity;
import es.pablomacias.esnuex_app.data.db.entity.EventEntity;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;
import es.pablomacias.esnuex_app.data.db.entity.PartnerEntity;
import es.pablomacias.esnuex_app.data.db.entity.TripEntity;

/**
 * Created by pablomaciasmu on 13/11/17.
 */

@Database(entities = {DelegationEntity.class, EventEntity.class, NewEntity.class,
        PartnerEntity.class, TripEntity.class},
        version = 1)
@TypeConverters({DateConverter.class, UriConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }

    static final String DATABASE_NAME = "ESN_UEX";

    public abstract DelegationDao delegationDao();

    public abstract EventDao eventDao();

    public abstract NewDao newDao();

    public abstract PartnerDao partnerDao();

    public abstract TripDao tripDao();
}
