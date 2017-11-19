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

package es.pablomacias.esnuex_app.ui.splash.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import es.pablomacias.esnuex_app.common.utils.NetworkUtil;
import es.pablomacias.esnuex_app.common.utils.UserPreferencesUtil;
import es.pablomacias.esnuex_app.data.api.Listeners.EventServiceInteractor;
import es.pablomacias.esnuex_app.data.api.Listeners.NewServiceInteractor;
import es.pablomacias.esnuex_app.data.api.Listeners.PartnerServiceInteractor;
import es.pablomacias.esnuex_app.data.api.Listeners.TripServiceInteractor;
import es.pablomacias.esnuex_app.data.api.Services.EventService;
import es.pablomacias.esnuex_app.data.api.Services.NewService;
import es.pablomacias.esnuex_app.data.api.Services.PartnerService;
import es.pablomacias.esnuex_app.data.api.Services.TripService;
import es.pablomacias.esnuex_app.data.db.AppDatabase;
import es.pablomacias.esnuex_app.data.db.entity.EventEntity;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;
import es.pablomacias.esnuex_app.data.db.entity.PartnerEntity;
import es.pablomacias.esnuex_app.data.db.entity.TripEntity;
import es.pablomacias.esnuex_app.data.repository.EventRepository;
import es.pablomacias.esnuex_app.data.repository.NewRespository;
import es.pablomacias.esnuex_app.data.repository.PartnerRepository;
import es.pablomacias.esnuex_app.data.repository.TripRepository;
import es.pablomacias.esnuex_app.ui.splash.activity.SplashViewInterface;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by pablomaciasmu on 18/11/17.
 */

public class SplashPresenter implements
        PartnerServiceInteractor.CallBackListener,
        TripServiceInteractor.CallBackListener,
        NewServiceInteractor.CallBackListener,
        EventServiceInteractor.CallBackListener {
    private static final String TAG = SplashPresenter.class.getSimpleName();
    private NewRespository newRepository;
    private EventRepository eventRepository;
    private TripRepository tripRepository;
    private PartnerRepository partnerRepository;
    private Context context;
    private UserPreferencesUtil userPreferencesUtil;
    private int cont;
    SplashViewInterface splashViewInterface;

    public SplashPresenter(Context context, SplashViewInterface splashViewInterface) {
        this.context = context;
        this.splashViewInterface = splashViewInterface;
        cont = 0;
    }

    public void goToNextActivity() {
        NetworkUtil.newInstance(context);
        newRepository = NewRespository.getInstance(AppDatabase.getAppDatabase(context));
        newRepository.resetRepository();
        eventRepository = EventRepository.getInstance(AppDatabase.getAppDatabase(context));
        eventRepository.resetRepository();
        tripRepository = TripRepository.getInstance(AppDatabase.getAppDatabase(context));
        tripRepository.resetRepository();
        partnerRepository = PartnerRepository.getInstance(AppDatabase.getAppDatabase(context));
        partnerRepository.resetRepository();
        if (!NetworkUtil.isConnected()) { //NO HAY INTERNET
            newRepository.initRepository();
            loaded();
            eventRepository.initRepository();
            loaded();
            partnerRepository.initRepository();
            loaded();
            tripRepository.initRepository();
            loaded();
        } else { //HAY INTERNET
            Log.i(TAG, "goToNextActivity: CREANDO SERVICIOS");
            EventService eventService = new EventService(this);
            eventService.getEvents();
            NewService newService = new NewService(this);
            newService.getNews();
            PartnerService partnerService = new PartnerService(this);
            partnerService.getPartners();
            TripService tripService = new TripService(this);
            tripService.getTrips();
        }
    }

    private void loaded() {
        synchronized (this) {
            cont++;
            Log.i(TAG, "loaded: " + cont);
            if (cont >= 4) {
                this.splashViewInterface.goToHome();
            }
        }
    }


    @Override
    public void onResponseNew(Call<List<NewEntity>> call, Response<List<NewEntity>> response) {
        Log.d(TAG, "onResponseNew() called with: call = [" + call + "], response = [" + response.body().size() + "]");
        newRepository.initRepositoryByData(response.body());
        loaded();
    }

    @Override
    public void onFailureNew(Call<List<NewEntity>> call, Throwable t) {
        Log.d(TAG, "onFailureNew() called with: call = [" + call + "], t = [" + t + "]");
        newRepository.initRepository();
        loaded();
    }

    @Override
    public void onResponseTrip(Call<List<TripEntity>> call, Response<List<TripEntity>> response) {
        Log.d(TAG, "onResponseTrip() called with: call = [" + call + "], response = [" + response.body().size() + "]");
        tripRepository.initRepositoryByData(response.body());
        loaded();

    }

    @Override
    public void onFailureTrip(Call<List<TripEntity>> call, Throwable t) {
        Log.d(TAG, "onFailureTrip() called with: call = [" + call + "], t = [" + t + "]");
        tripRepository.initRepository();
        loaded();

    }

    @Override
    public void onResponseEvent(Call<List<EventEntity>> call, Response<List<EventEntity>> response) {
        Log.d(TAG, "onResponseEvent() called with: call = [" + call + "], response = [" + response.body().size() + "]");
        eventRepository.initRepositoryByData(response.body());
        loaded();

    }

    @Override
    public void onFailureEvent(Call<List<EventEntity>> call, Throwable t) {
        Log.d(TAG, "onFailureEvent() called with: call = [" + call + "], t = [" + t + "]");
        eventRepository.initRepository();
        loaded();

    }

    @Override
    public void onResponsePartner(Call<List<PartnerEntity>> call, Response<List<PartnerEntity>> response) {
        Log.d(TAG, "onResponsePartner() called with: call = [" + call + "], response = [" + response.body().size() + "]");
        partnerRepository.initRepositoryByData(response.body());
        loaded();

    }

    @Override
    public void onFailurePartner(Call<List<PartnerEntity>> call, Throwable t) {
        Log.d(TAG, "onFailurePartner() called with: call = [" + call + "], t = [" + t + "]");
        partnerRepository.initRepository();
        loaded();

    }
}
