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

package es.pablomacias.esnuex_app.ui.management.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.pablomacias.esnuex_app.data.db.AppDatabase;
import es.pablomacias.esnuex_app.data.db.entity.EtcType;
import es.pablomacias.esnuex_app.data.db.entity.EventEntity;
import es.pablomacias.esnuex_app.data.db.entity.TripEntity;
import es.pablomacias.esnuex_app.data.repository.EventRepository;
import es.pablomacias.esnuex_app.data.repository.TripRepository;
import es.pablomacias.esnuex_app.ui.management.activity.ManagementInterface;

/**
 * Created by pablomaciasmu on 19/11/17.
 */

public class ManagementPresenter {
    private static final String TAG = ManagementPresenter.class.getSimpleName();
    private Context context;
    private ManagementInterface managementInterface;
    String type;
    private EventRepository eventRepository;
    private TripRepository tripRepository;

    public ManagementPresenter(Context context, ManagementInterface managementInterface, Intent intent) {
        this.context = context;
        this.managementInterface = managementInterface;
        eventRepository = EventRepository.getInstance(AppDatabase.getAppDatabase(context));
        tripRepository = TripRepository.getInstance(AppDatabase.getAppDatabase(context));
        type = intent.getStringExtra("type");
    }

    public List<EtcType> getElements() {
        List<EtcType> etcTypes = new ArrayList<>();
        switch (type) {
            case "TRIP":
                etcTypes.addAll(tripRepository.getAll());
                break;
            case "EVENT":
                etcTypes.addAll(eventRepository.getAll());
                break;
        }
        return etcTypes;
    }

    @SuppressLint("NewApi")
    public void removeItem(EtcType item) {
        if (Objects.equals(type, "TRIP")) {
            tripRepository.remove((TripEntity) item);
        } else {
            eventRepository.remove((EventEntity) item);
        }
        this.managementInterface.updateUI();
    }

}
