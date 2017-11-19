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

import java.util.ArrayList;
import java.util.List;

import es.pablomacias.esnuex_app.data.db.AppDatabase;
import es.pablomacias.esnuex_app.data.db.entity.EtcType;
import es.pablomacias.esnuex_app.data.db.entity.EventEntity;
import es.pablomacias.esnuex_app.data.db.entity.PartnerEntity;
import es.pablomacias.esnuex_app.data.db.entity.TripEntity;
import es.pablomacias.esnuex_app.data.repository.EventRepository;
import es.pablomacias.esnuex_app.data.repository.PartnerRepository;
import es.pablomacias.esnuex_app.data.repository.TripRepository;
import es.pablomacias.esnuex_app.ui.main.fragments.EtcListInterface;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class List_Presenter {
    private static final String TAG = List_Presenter.class.getSimpleName();
    private Context context;
    private PartnerRepository partnerRepository;
    private EventRepository eventRepository;
    private TripRepository tripRepository;
    private EtcListInterface etcListInterface;

    public List_Presenter(Context context, EtcListInterface etcListInterface) {
        this.context = context;
        this.etcListInterface = etcListInterface;
        eventRepository = EventRepository.getInstance(AppDatabase.getAppDatabase(context));
        tripRepository = TripRepository.getInstance(AppDatabase.getAppDatabase(context));
        partnerRepository = PartnerRepository.getInstance(AppDatabase.getAppDatabase(context));
    }


    public List<EtcType> getElements(String type, int delegation) {
        Log.d(TAG, "getElements() called with: type = [" + type + "], delegation = [" + delegation + "]");
        List<EtcType> etcTypes;
        switch (type) {
            case "TRIP":
                etcTypes = convertListTrips(tripRepository.getByDelegation(delegation));
                break;
            case "EVENT":
                etcTypes = convertListEvent(eventRepository.getByDelegation(delegation));
                break;
            case "PARTNER":
                etcTypes = convertListPartner(partnerRepository.getByDelegation(delegation));
                break;
            default:
                etcTypes = new ArrayList<>();
                break;
        }
        return etcTypes;
    }

    private List<EtcType> convertListPartner(List<PartnerEntity> list) {
        List<EtcType> etcTypes = new ArrayList<>();
        for (PartnerEntity partnerEntity :
                list) {
            etcTypes.add(partnerEntity);
        }
        return etcTypes;
    }

    private List<EtcType> convertListEvent(List<EventEntity> list) {
        List<EtcType> etcTypes = new ArrayList<>();
        for (EventEntity eventEntity :
                list) {
            etcTypes.add(eventEntity);
        }
        return etcTypes;
    }

    private List<EtcType> convertListTrips(List<TripEntity> list) {
        List<EtcType> etcTypes = new ArrayList<>();
        for (TripEntity tripEntity :
                list) {
            etcTypes.add(tripEntity);
        }
        return etcTypes;
    }
}
