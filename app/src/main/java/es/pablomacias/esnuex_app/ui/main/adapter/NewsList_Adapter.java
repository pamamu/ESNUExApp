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

package es.pablomacias.esnuex_app.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;

/**
 * Created by pablomaciasmu on 16/11/17.
 */

public class NewsList_Adapter extends RecyclerView.Adapter<NewsList_Adapter.NewViewHolder> {
    private List<NewEntity> news;

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_newslist_item, parent, false);
        return new NewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewViewHolder holder, int position) {
        holder.title.setText(news.get(position).getTitle());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        holder.time.setText(df.format(news.get(position).getDate()));
        holder.image.setImageResource(R.drawable.bus);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class NewViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        @BindView(R.id.new_image)
        public ImageView image;
        @BindView(R.id.new_text)
        public TextView title;
        @BindView(R.id.time_new)
        public TextView time;

        public NewViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public NewsList_Adapter(List<NewEntity> news) {
        this.news = news;
    }
}
