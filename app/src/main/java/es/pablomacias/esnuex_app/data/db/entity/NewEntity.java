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

import es.pablomacias.esnuex_app.data.model.New;

/**
 * Created by pablomaciasmu on 13/11/17.
 */

@Entity(tableName = "New")
public class NewEntity implements New {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    @SerializedName("titulo")
    private String title;
    @ColumnInfo(name = "body")
    @SerializedName("cuerpo")
    private String body;
    @SerializedName("imagen")
    @ColumnInfo(name = "image")
    private String image;
    @SerializedName("fecha")
    @ColumnInfo(name = "date")
    private String date;
    @SerializedName("link")
    @ColumnInfo(name = "link")
    private String link;


    public NewEntity() {
    }

    public NewEntity(int id, String title, String image, String date, String link) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.date = date;
        this.link = link;
    }

    public NewEntity(int id, String title, String image, String date) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.date = date;
    }

    public NewEntity(String title, String image, String date) {
        this.title = title;
        this.image = image;
        this.date = date;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
