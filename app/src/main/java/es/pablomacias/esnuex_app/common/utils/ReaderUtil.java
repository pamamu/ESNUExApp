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

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by pablomaciasmu on 16/11/17.
 */

public class ReaderUtil {
    private Context context;
    private String description;

    public ReaderUtil(Context context) {
        this.context = context;
    }


    public String getDescription() {
        if (description == null || description.isEmpty()) {
            description = readFromFile("Description.txt");
        }
        return description;
    }

    private String readFromFile(String file_name) {
        String out = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(file_name)));
            while (reader.ready()) {
                out += reader.readLine() + "\n";
            }

        } catch (Exception e) {
            return out;
        }
        return out;
    }
}
