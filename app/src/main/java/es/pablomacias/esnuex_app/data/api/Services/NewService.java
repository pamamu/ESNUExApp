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

package es.pablomacias.esnuex_app.data.api.Services;

import android.util.Log;

import java.util.List;

import es.pablomacias.esnuex_app.data.api.ApiClient;
import es.pablomacias.esnuex_app.data.api.ApiInterface;
import es.pablomacias.esnuex_app.data.api.Listeners.NewServiceInteractor;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pablomaciasmu on 18/11/17.
 */

public class NewService implements NewServiceInteractor {
    private CallBackListener callBackListener;
    private static final String TAG = NewService.class.getSimpleName();

    public NewService(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    @Override
    public void getNews() {
        ApiInterface apiInterface = ApiClient.getClient();
        Call<List<NewEntity>> call = apiInterface.listNews();
        Log.i(TAG, "getNews: ");
        call.enqueue(new Callback<List<NewEntity>>() {
            @Override
            public void onResponse(Call<List<NewEntity>> call, Response<List<NewEntity>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: ");
                    callBackListener.onResponseNew(call, response);
                }
            }

            @Override
            public void onFailure(Call<List<NewEntity>> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
                callBackListener.onFailureNew(call, t);
            }
        });
    }
}
