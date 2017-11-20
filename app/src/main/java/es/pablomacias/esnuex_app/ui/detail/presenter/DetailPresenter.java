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

package es.pablomacias.esnuex_app.ui.detail.presenter;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import es.pablomacias.esnuex_app.ui.detail.activity.Detail_interface;

/**
 * Created by pablomaciasmu on 17/11/17.
 */

public class DetailPresenter {
    private Context context;
    private String type;
    private final String TAG = this.getClass().getSimpleName();
    private Detail_interface detail_interface;
    private Date date;
    private boolean received;
    String[] information;

    public DetailPresenter(Context context, Intent intent, Detail_interface detail_interface) {
        this.context = context;
        this.detail_interface = detail_interface;
        createEtcObject(intent);
    }

    @SuppressLint("NewApi")
    private void createEtcObject(Intent intent) {
        information = intent.getStringArrayExtra("information");
        received = information.length > 0;
        if (received) {
            received = true;
            detail_interface.setImage(Uri.parse(information[0]));
            detail_interface.setTitle(information[1]);
            detail_interface.setSubtitle(information[2]);
            detail_interface.setDescription(information[3]);
            getDate(information[2]);
            type = information[5];
            if (Objects.equals(type, "PARTNER"))
                detail_interface.setButtonVisible(false);
            detail_interface.setPlace(information[4]);
        }
    }


    private void getDate(String s) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = df.parse(s);
        } catch (ParseException e) {
            date = Calendar.getInstance().getTime();
        }
    }

    public void addToCalendar() {
        if (received) {
            Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
            builder.appendPath("time");
            ContentUris.appendId(builder, date.getTime());
            Toast.makeText(context, "Adding event to Calendar App", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", date.getTime());
            intent.putExtra("title", information[1]);
            intent.putExtra("description", information[3]);
            intent.putExtra("eventLocation", information[4]);
            context.startActivity(intent);
        }
    }

    public void goTo() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + information[4]));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
