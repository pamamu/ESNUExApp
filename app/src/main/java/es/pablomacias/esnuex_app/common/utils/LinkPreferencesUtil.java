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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.utils.models.Link;

/**
 * Created by pablomaciasmu on 15/11/17.
 */

public class LinkPreferencesUtil {
    private Context context;
    private SharedPreferences sharedPreferences;
    private static ArrayList<Link> mLinks = new ArrayList<>();
    public final String SHARED_PREFERENCE_NAME = "sharedpreferences_link";
    private final String KEY_NAME = "Links";

    public LinkPreferencesUtil(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public ArrayList<Link> getLinks() {
        if (mLinks == null || mLinks.isEmpty()) {
            mLinks = getLinksFromMemory();
        }
        return new ArrayList<>(mLinks);
    }

    private ArrayList<Link> getLinksFromMemory() {
//        String linkArrayListJson = sharedPreferences.getString(KEY_NAME, "");
//        return getArrayListFromJson(linkArrayListJson);
        if (mLinks.isEmpty()) {
            mLinks.add(new Link(context.getString(R.string.section_name), R.drawable.esn_star, false));
            mLinks.add(new Link(context.getString(R.string.events), R.drawable.event, false));
            mLinks.add(new Link(context.getString(R.string.trips), R.drawable.bus, false));
            mLinks.add(new Link(context.getString(R.string.partners), R.drawable.partner, false));
            mLinks.add(new Link(context.getString(R.string.contact), R.drawable.contact, false));
            mLinks.add(new Link(context.getString(R.string.management), R.drawable.management, true));
            mLinks.add(new Link(context.getString(R.string.login), R.drawable.signinup, false));
            mLinks.add(new Link(context.getString(R.string.logout), R.drawable.log_out, true));
        }
        return mLinks;
    }

    public ArrayList<Link> getArrayListFromJson(String json) {
        Gson gson = GsonUtils.getGsonInstance();
        Type type = new TypeToken<ArrayList<Link>>() {
        }.getType();
        ArrayList<Link> allLinks = gson.fromJson(json, type);

        if (allLinks != null && !allLinks.isEmpty()) {
            return allLinks;
        }

        return new ArrayList<>(0);
    }

}
