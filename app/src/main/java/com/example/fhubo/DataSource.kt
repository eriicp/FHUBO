package com.example.fhubo

import com.example.fhubo.City.City
import com.example.fhubo.CityLocation.CityLocation
import com.example.fhubo.Favorites.Favorites
import com.example.fhubo.Films.Films
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
        Main("star wars",R.drawable.film_starwars),
        Main("hunger games" , R.drawable.film_hungergames),
        Main("codigo da vinci",R.drawable.film_codigodavinci),
        Main("harry potter", R.drawable.film_harrypotter)
    )

    val favorites : List<Favorites> = listOf(
        Favorites("Les espaces d'abraxas (Jocs de la fam)",R.drawable.location_hungergames_les_espaces_dabraxas),
        Favorites( "Museo del Louvre (El Codi Da Vinci)", R.drawable.location_codigodavinci_louvre)
    )

    val filmLocations : List<Films> = listOf(
        Films("Hoth","Glaciar Hardangerjøkulen", R.drawable.location_starwars_hoth),
        Films("Tatooine", "Ajim, Tunisia", R.drawable.location_starwars_tatooine)
    )
}