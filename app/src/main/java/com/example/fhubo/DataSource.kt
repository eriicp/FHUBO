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

    val films: List<Main> = listOf(
        Main(id = 1, name = "Star Wars", category = "Pel·lícules", year = 1977, imagePath = "https://m.media-amazon.com/images/I/7140b3Mdf5L.jpg"),
        Main(id = 2, name = "Hunger Games", category = "Pel·lícules", year = 2012, imagePath = "https://m.media-amazon.com/images/M/MV5BMjA4NDg3NzYxMF5BMl5BanBnXkFtZTcwNjUxNjM3Nw@@._V1_.jpg"),
        Main(id = 3, name = "Harry Potter", category = "Pel·lícules", year = 2001, imagePath = "https://m.media-amazon.com/images/M/MV5BNmQ0ODBhMjUtNDRhOC00MGQzLTk5MTAtZDliODg5NmU5MjZhXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg")
    )

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
