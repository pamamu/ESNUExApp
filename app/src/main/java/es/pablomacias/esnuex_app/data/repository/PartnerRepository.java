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
import es.pablomacias.esnuex_app.data.db.entity.PartnerEntity;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class PartnerRepository implements Repository<PartnerEntity> {
    private static PartnerRepository INSTANCE;
    private AppDatabase appDatabase;

    public PartnerRepository(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        resetRepository();
    }

    public void resetRepository() {
        new resetDb(this.appDatabase).execute();
//        appDatabase.partnerDao().truncateTable();
    }

    public void initRepository() {
        //TODO INSERTAR DATOS DE PRUEBA
    }

    public void initRepositoryByData(List<PartnerEntity> list) {
        appDatabase.partnerDao().insertAll(list);
    }

    public static PartnerRepository getInstance(final AppDatabase appDatabase) {
        if (INSTANCE == null)
            INSTANCE = new PartnerRepository(appDatabase);
        return INSTANCE;
    }

    @Override
    public List<PartnerEntity> getAll() {
        return null;
    }

    @Override
    public void add(PartnerEntity item) {

    }

    @Override
    public void get(int id) {

    }

    @Override
    public void update(PartnerEntity item) {

    }

    @Override
    public void remove(PartnerEntity item) {

    }

    public List<PartnerEntity> getByDelegation(int del) {
        return appDatabase.partnerDao().loadAllsByDelegation(del);
    }

    private static class resetDb extends AsyncTask<Void, Void, Void> {
        private final AppDatabase mDb;

        private resetDb(AppDatabase mDb) {
            this.mDb = mDb;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDb.partnerDao().truncateTable();
            return null;
        }
    }
}
