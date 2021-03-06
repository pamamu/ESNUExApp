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

import com.google.gson.annotations.SerializedName;

import es.pablomacias.esnuex_app.data.model.Trip;

/**
 * Created by pablomaciasmu on 13/11/17.
 */
@Entity(tableName = "Trip")
public class TripEntity implements Trip, EtcType {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "nombre")
    @SerializedName("nombre")
    private String name;
    @ColumnInfo(name = "ubicacion")
    @SerializedName("ubicacion")
    private String place;
    @ColumnInfo(name = "delegacion")
    @SerializedName("delegacion")
    private int delegation;
    @ColumnInfo(name = "imagen")
    @SerializedName("imagen")
    private String image;
    @ColumnInfo(name = "fecha")
    @SerializedName("fecha")
    private String dateTime;
    @ColumnInfo(name = "descripcion")
    @SerializedName("descripcion")
    private String description;

    public TripEntity() {
    }

    public TripEntity(int id, String name, String place, int delegation, String image, String dateTime, String description) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.delegation = delegation;
        this.image = image;
        this.dateTime = dateTime;
        this.description = description;
    }

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

    @Override
    public String getAddress() {
        return this.place;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public int getDelegation() {
        return delegation;
    }

    @Override
    public String getSubtitle() {
        return this.dateTime;
    }

    public void setDelegation(int delegation) {
        this.delegation = delegation;
    }

    @Override
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
