package com.example.fhubo

import com.example.fhubo.City.City
import com.example.fhubo.CityLocation.CityLocation
import com.example.fhubo.Main.Main

object DataSource {
    val cities: List<City> = listOf(
        City("Barcelona", imageResource = R.drawable.city_barcelona),
        City("Paris", R.drawable.city_paris),
        City("Roma",  R.drawable.city_roma),
        City("Berlin", R.drawable.city_berlin)
    )

    val cityLocations: List<CityLocation> = listOf(
        CityLocation(
            "Les espaces d'abraxas (Jocs de la fam)",
            "R. du Clos des Aulnes, 93160 Noisy-le-Grand",
            R.drawable.location_hungergames_les_espaces_dabraxas
        ),
        CityLocation(
            "Museo del Louvre (El Codi Da Vinci)",
            "Museu del Louvre 75001 Paris, França",
            R.drawable.location_codigodavinci_louvre
        )
    )

    val films : List<Main> = listOf(
        Main(R.drawable.film_starwars,R.drawable.film_hungergames),
        Main(R.drawable.film_codigodavinci,R.drawable.film_harrypotter)
    )
}