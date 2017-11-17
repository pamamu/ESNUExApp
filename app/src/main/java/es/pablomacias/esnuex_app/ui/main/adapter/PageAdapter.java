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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import es.pablomacias.esnuex_app.common.utils.FragmentsEnum;
import es.pablomacias.esnuex_app.ui.main.fragments.List_Fragment;


public class PageAdapter extends FragmentStatePagerAdapter {
    private int nTabs;
    private FragmentsEnum type;

    public PageAdapter(FragmentManager fm, int nTabs, FragmentsEnum type) {
        super(fm);
        this.nTabs = nTabs;
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        if (position > 2)
            return null;
        return List_Fragment.newInstance(type, position);

    }

    @Override
    public int getCount() {
        return nTabs;
    }
}
