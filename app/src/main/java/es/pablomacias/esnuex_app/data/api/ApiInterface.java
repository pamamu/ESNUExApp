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

package es.pablomacias.esnuex_app.data.api;

import java.util.List;

import es.pablomacias.esnuex_app.data.db.entity.EventEntity;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;
import es.pablomacias.esnuex_app.data.db.entity.PartnerEntity;
import es.pablomacias.esnuex_app.data.db.entity.TripEntity;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pablomaciasmu on 18/11/17.
 */

public interface ApiInterface {

    @GET("api/new")
    Call<List<NewEntity>> listNews();

    @GET("api/partner")
    Call<List<PartnerEntity>> listPartners();

    @GET("api/event")
    Call<List<EventEntity>> listEvents();

    @GET("api/trip")
    Call<List<TripEntity>> listTrips();


}
