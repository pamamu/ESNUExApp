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

package es.pablomacias.esnuex_app.data.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.pablomacias.esnuex_app.data.db.entity.NewEntity;

/**
 * Created by pablomaciasmu on 14/11/17.
 */

public class DataGenerator {
    public static List<NewEntity> generateNews() {
        List<NewEntity> news = new ArrayList<>();
        news.add(new NewEntity(1, "Convenio con Servicio Internacional de Evaluación de la Lengua Española (SIELE)",
                "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/dsc_0139-01.jpg?itok=o8xoxFHO",
                Calendar.getInstance().getTime(),
                "https://www.esn-spain.org/news/convenio-con-servicio-internacional-de-evaluaci%C3%B3n-de-la-lengua-espa%C3%B1ola-siele"));
        news.add(new NewEntity(2, "Unión Europea: Premio Princesa de Asturias de la Concordia 2017😀",
                "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/26905160536_c9308d391c_q_1.jpg?itok=KI_5SEZc",
                Calendar.getInstance().getTime(),
                "https://www.esn-spain.org/news/uni%C3%B3n-europea-premio-princesa-de-asturias-de-la-concordia-2017"));

        news.add(new NewEntity(3, "Convenio con Servicio Internacional de Evaluación de la Lengua Española (SIELE)", "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/dsc_0139-01.jpg?itok=o8xoxFHO", Calendar.getInstance().getTime()));
        news.add(new NewEntity(4, "Unión Europea: Premio Princesa de Asturias de la Concordia 2017😀", "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/26905160536_c9308d391c_q_1.jpg?itok=KI_5SEZc", Calendar.getInstance().getTime()));

        news.add(new NewEntity(5, "Convenio con Servicio Internacional de Evaluación de la Lengua Española (SIELE)", "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/dsc_0139-01.jpg?itok=o8xoxFHO", Calendar.getInstance().getTime()));
        news.add(new NewEntity(6, "Unión Europea: Premio Princesa de Asturias de la Concordia 2017😀", "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/26905160536_c9308d391c_q_1.jpg?itok=KI_5SEZc", Calendar.getInstance().getTime()));

        news.add(new NewEntity(7, "Convenio con Servicio Internacional de Evaluación de la Lengua Española (SIELE)", "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/dsc_0139-01.jpg?itok=o8xoxFHO", Calendar.getInstance().getTime()));
        news.add(new NewEntity(8, "Unión Europea: Premio Princesa de Asturias de la Concordia 2017😀", "https://www.esn-spain.org/sites/default/files/styles/zoom/public/news/images/26905160536_c9308d391c_q_1.jpg?itok=KI_5SEZc", Calendar.getInstance().getTime()));


        return news;
    }


}
