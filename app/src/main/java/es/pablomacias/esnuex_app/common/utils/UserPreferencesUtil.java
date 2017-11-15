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

package es.pablomacias.esnuex_app.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import es.pablomacias.esnuex_app.common.utils.models.User;

/**
 * Created by pablomaciasmu on 15/11/17.
 */

public class UserPreferencesUtil {
    private final Context context;
    private SharedPreferences sharedPreferences;
    private final String SHARED_PREFERENCES_NAME = "sharedpreferences_user";
    private final String KEY_NAME = "User";

    public UserPreferencesUtil(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public User getUser() {
        Gson gson = GsonUtils.getGsonInstance();
        String jsonUser = this.sharedPreferences.getString(KEY_NAME, "");

        if (jsonUser.equals("")) {
            return null;
        } else {
            return gson.fromJson(jsonUser, User.class);
        }
    }

    public void commitUser(User mUser) {
        SharedPreferences.Editor editorUser = this.sharedPreferences.edit();

        Gson gson = GsonUtils.getGsonInstance();
        String jsonUser = gson.toJson(mUser);

        editorUser.putString(KEY_NAME, jsonUser);
        editorUser.apply();
    }

    public void clearUser() {
        SharedPreferences.Editor editorUser = this.sharedPreferences.edit();
        editorUser.clear().apply();
    }


}
