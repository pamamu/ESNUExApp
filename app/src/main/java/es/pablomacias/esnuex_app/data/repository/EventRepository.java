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
import es.pablomacias.esnuex_app.data.db.entity.EventEntity;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class EventRepository implements Repository<EventEntity> {
    private static EventRepository INSTANCE;
    private AppDatabase appDatabase;

    public EventRepository(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        resetRepository();
    }

    public void resetRepository() {
        appDatabase.eventDao().truncateTable();
    }


    public void initRepository() {
        //TODO INSERTAR DATOS DE MUESTRA
    }

    public void initRepositoryByData(List<EventEntity> list) {
        appDatabase.eventDao().insertAll(list);
    }

    public static EventRepository getInstance(final AppDatabase appDatabase) {
        if (INSTANCE == null) {
            INSTANCE = new EventRepository(appDatabase);
        }
        return INSTANCE;
    }

    @Override
    public List<EventEntity> getAll() {
        return appDatabase.eventDao().loadAll();
    }

    @Override
    public void add(EventEntity item) {
        appDatabase.eventDao().insert(item);
    }

    @Override
    public void get(int id) {

    }

    @Override
    public void update(EventEntity item) {
        appDatabase.eventDao().update(item);
    }

    @Override
    public void remove(EventEntity item) {
        appDatabase.eventDao().delete(item);
    }

    public List<EventEntity> getByDelegation(int del) {
        return appDatabase.eventDao().loadAllsByDelegation(del);
    }
}
