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

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.data.db.entity.NewEntity;
import es.pablomacias.esnuex_app.ui.main.activity.New_Item_Listener;

/**
 * Created by pablomaciasmu on 16/11/17.
 */

public class NewsList_Adapter extends RecyclerView.Adapter<NewsList_Adapter.NewViewHolder> {
    private List<NewEntity> news;
    Context context;
    New_Item_Listener new_item_listener;

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_newslist_item, parent, false);
        return new NewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewViewHolder holder, final int position) {
        holder.title.setText(news.get(position).getTitle());
        holder.time.setText(news.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_item_listener.newclicked(news.get(position));
            }
        });
        ImageView imageView = holder.image;
        Picasso
                .with(context)
                .load(Uri.parse(news.get(position).getImage()))
                .fit().centerCrop() // will explain later
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.esn_uex_color)
                .noFade()
                .into(imageView);
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

    public NewsList_Adapter(List<NewEntity> news, Context context, New_Item_Listener new_item_listener) {
        this.context = context;
        this.news = news;
        this.new_item_listener = new_item_listener;
    }
}
