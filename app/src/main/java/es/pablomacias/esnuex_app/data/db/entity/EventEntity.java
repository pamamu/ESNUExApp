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

package es.pablomacias.esnuex_app.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.pablomacias.esnuex_app.data.model.Event;

/**
 * Created by pablomaciasmu on 13/11/17.
 */

@Entity(tableName = "Event")
public class EventEntity implements Event, EtcType {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "nombre")
    private String name;
    @ColumnInfo(name = "ubicacion")
    private String address;
    @ColumnInfo(name = "delegacion")
    private int delegation;
    @ColumnInfo(name = "imagen")
    private Uri image;
    @ColumnInfo(name = "fecha_inicio")
    private Date dateTime;
    @ColumnInfo(name = "descripcion")
    private String description;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getDelegation() {
        return delegation;
    }

    @Override
    public String getSubtitle() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(this.dateTime);
    }

    public void setDelegation(int delegation) {
        this.delegation = delegation;
    }

    @Override
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public EventEntity(String name, String address, int delegation, Uri image, Date dateTime, String description) {
        this.name = name;
        this.address = address;
        this.delegation = delegation;
        this.image = image;
        this.dateTime = dateTime;
        this.description = description;
    }
}
