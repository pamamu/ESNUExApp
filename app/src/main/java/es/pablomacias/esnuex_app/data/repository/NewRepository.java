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

import android.os.AsyncTask;

import java.util.List;

import es.pablomacias.esnuex_app.data.db.AppDatabase;
import es.pablomacias.esnuex_app.data.db.DataGenerator;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class NewRepository implements Repository<NewEntity> {
    private static NewRepository INSTANCE;
    private AppDatabase appDatabase;

    private NewRepository(final AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        initRepository();
    }

    public void resetRepository() {
//        appDatabase.newDao().truncateTable();
        new resetDb(this.appDatabase).execute();

    }

    public void initRepository() {
        appDatabase.newDao().insertAll(DataGenerator.generateNews());
    }

    public void initRepositoryByData(List<NewEntity> list) {
        appDatabase.newDao().insertAll(list);
    }

    public static NewRepository getInstance(final AppDatabase appDatabase) {
        if (INSTANCE == null) {
            INSTANCE = new NewRepository(appDatabase);
        }
        return INSTANCE;
    }

    @Override
    public List<NewEntity> getAll() {
        return appDatabase.newDao().loadAllsNews();
    }

    @Override
    public void add(NewEntity item) {
        appDatabase.newDao().insert(item);
    }

    @Override
    public void get(int id) {

    }

    @Override
    public void update(NewEntity item) {

    }


    @Override
    public void remove(NewEntity item) {

    }

    public void removeAll() {
        appDatabase.newDao().truncateTable();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        private PopulateDbAsync(AppDatabase mDb) {
            this.mDb = mDb;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    private static class resetDb extends AsyncTask<Void, Void, Void> {
        private final AppDatabase mDb;

        private resetDb(AppDatabase mDb) {
            this.mDb = mDb;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDb.newDao().truncateTable();
            return null;
        }
    }

}
