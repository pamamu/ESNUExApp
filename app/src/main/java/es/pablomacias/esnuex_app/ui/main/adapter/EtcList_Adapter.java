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
import es.pablomacias.esnuex_app.data.db.entity.EtcType;
import es.pablomacias.esnuex_app.ui.main.activity.Etc_Item_Listener;

/**
 * Created by pablomaciasmu on 16/11/17.
 */

public class EtcList_Adapter extends RecyclerView.Adapter<EtcList_Adapter.EtcViewHolder> {

    private static final String TAG = EtcList_Adapter.class.getName();
    private List<EtcType> objets;
    Context context;
    Etc_Item_Listener listener;


    @Override
    public EtcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_etc_list_item, parent, false);
        return new EtcViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EtcViewHolder holder, final int position) {
        holder.title.setText(objets.get(position).getName());
        holder.subtitle.setText(objets.get(position).getSubtitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemclicked(objets.get(position));
            }
        });
        ImageView imageView = holder.image;
        Picasso
                .with(context)
                .load(objets.get(position).getImage())
                .fit().centerCrop() // will explain later
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.event_ticket)
                .noFade()
                .into(imageView);
//        Picasso.with(context).load(objets.get(position).getImage()).centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return objets.size();
    }

    public static class EtcViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.etc_list_item_image)
        public ImageView image;
        @BindView(R.id.etc_list_item_title)
        public TextView title;
        @BindView(R.id.etc_list_item_subtitle)
        public TextView subtitle;

        public EtcViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public EtcList_Adapter(List<EtcType> objets, Context context, Etc_Item_Listener listener) {
        this.objets = objets;
        this.context = context;
        this.listener = listener;
    }
}
