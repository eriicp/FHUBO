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

    // Lista de películas con categorías y AÑO
    val films : List<Main> = listOf(
        Main("Star Wars", R.drawable.film_starwars, "Pel·lícules", 1977),
        Main("Hunger Games" , R.drawable.film_hungergames, "Pel·lícules", 2012),
        Main("El Código Da Vinci", R.drawable.film_codigodavinci, "Llibres", 2003),
        Main("Harry Potter", R.drawable.film_harrypotter, "Llibres", 1997),
        Main("Dune", R.drawable.film_starwars, "Música", 2021) // Ejemplo para categoría Música
    )

    // Lista de favoritos corregida
    val favorites : List<Favorites> = listOf(
        Favorites("Les espaces d'abraxas", R.drawable.location_hungergames_les_espaces_dabraxas),
        Favorites("Museo del Louvre", R.drawable.location_codigodavinci_louvre),
        Favorites("Star Wars", R.drawable.film_starwars)
    )

    val filmLocations : List<Films> = listOf(
        Films("Hoth","Glaciar Hardangerjøkulen", R.drawable.location_starwars_hoth),
        Films("Tatooine", "Ajim, Tunisia", R.drawable.location_starwars_tatooine)
    )
}