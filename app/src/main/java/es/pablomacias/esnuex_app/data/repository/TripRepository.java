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

package es.pablomacias.esnuex_app.data.repository;

import java.util.List;

import es.pablomacias.esnuex_app.data.db.AppDatabase;
import es.pablomacias.esnuex_app.data.db.entity.TripEntity;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class TripRepository implements Repository<TripEntity> {
    private static TripRepository INSTANCE;
    private AppDatabase appDatabase;

    public TripRepository(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        initRepository();
    }

    private void initRepository() {
        appDatabase.tripDao().truncateTable();
    }

    public static TripRepository getInstance(final AppDatabase appDatabase) {
        if (INSTANCE == null)
            INSTANCE = new TripRepository(appDatabase);
        return INSTANCE;
    }


    @Override
    public List<TripEntity> getAll() {
        return null;
    }

    @Override
    public void add(TripEntity item) {
        appDatabase.tripDao().insert(item);
    }

    @Override
    public void get(int id) {
    }

    @Override
    public void update(TripEntity item) {
        appDatabase.tripDao().update(item);
    }

    @Override
    public void remove(TripEntity item) {
        appDatabase.tripDao().delete(item);
    }

    public List<TripEntity> getByDelegation(int del) {
        return appDatabase.tripDao().loadAllsByDelegation(del);
    }
}
