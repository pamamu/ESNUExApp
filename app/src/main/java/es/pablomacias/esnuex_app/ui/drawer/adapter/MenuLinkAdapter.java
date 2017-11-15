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

package es.pablomacias.esnuex_app.ui.drawer.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.utils.models.Link;

/**
 * Created by pablomaciasmu on 15/11/17.
 */

public class MenuLinkAdapter extends ArrayAdapter<Link> {
    private Context context;
    public final int layoutResourceId;
    ArrayList<Link> list;

    public MenuLinkAdapter(Context context, int layoutResourceId, ArrayList<Link> links) {
        super(context, layoutResourceId, links);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = links;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();

        View v = layoutInflater.inflate(layoutResourceId, parent, false);

        ImageView imageView = v.findViewById(R.id.drawer_list_item_image);
        TextView textView = v.findViewById(R.id.drawer_list_item_text);

        Link link = list.get(position);

        imageView.setImageResource(link.getIcon());
        textView.setText(link.getName());

        return v;

    }
}
