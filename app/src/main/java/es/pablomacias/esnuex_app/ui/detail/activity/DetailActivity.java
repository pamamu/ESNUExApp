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

package es.pablomacias.esnuex_app.ui.detail.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.BindView;
import es.pablomacias.esnuex_app.R;
import es.pablomacias.esnuex_app.common.BaseActivity;
import es.pablomacias.esnuex_app.ui.detail.presenter.DetailPresenter;

public class DetailActivity extends BaseActivity implements Detail_interface {
    @BindView(R.id.view_image)
    ImageView image_view;
    @BindView(R.id.view_title)
    TextView title;
    @BindView(R.id.view_subtitle)
    TextView subtitle;
    @BindView(R.id.view_content)
    TextView content;
    @BindView(R.id.view_location_text)
    TextView location;
    @BindView(R.id.go_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.view_location)
    LinearLayout linearLayout;

    private Date date;

    DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailPresenter = new DetailPresenter(getApplicationContext(), getIntent(), this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailPresenter.addToCalendar();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailPresenter.goTo();
            }
        });

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void setTitle(String text) {
        title.setText(text);
    }

    @Override
    public void setImage(Uri image) {
        Picasso.with(getApplicationContext()).load(image).placeholder(R.drawable.loading_image)
                .error(R.drawable.esn_uex_color)
                .noFade().fit().centerCrop()
                .into(image_view);
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle.setText(subtitle);

    }

    @Override
    public void setDescription(String description) {
        this.content.setText(description);
    }

    @Override
    public void setPlace(String place) {
        this.location.setText(place);

    }

    @Override
    public void setButtonVisible(boolean buttonVisible) {
        if (buttonVisible)
            floatingActionButton.setVisibility(View.VISIBLE);
        else
            floatingActionButton.setVisibility(View.INVISIBLE);

    }
}
