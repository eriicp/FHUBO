package com.example.fhubo.Main

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

data class FilmRequest(
    val year: Int,
    val name: String,
    val category: String,
    val imagePath: String
)

interface filmsService {

    // Llegir Tots: GET /api/films
    @GET("api/films")
    suspend fun llistaFilms(): Response<List<Main>>

    // Llegir per ID: GET /api/films/{id}
    @GET("api/films/{id}")
    suspend fun getFilm(@Path("id") id: Long): Response<Main>

    // Crear: POST /api/films
    @POST("api/films")
    suspend fun insertFilm(@Body film: FilmRequest): Response<ResponseBody>

    // Actualitzar: PUT /api/films/{id}
    @PUT("api/films/{id}")
    suspend fun updateFilm(@Path("id") id: Long, @Body film: FilmRequest): Response<ResponseBody>

    // Esborrar un: DELETE /api/films/{id}
    @DELETE("api/films/{id}")
    suspend fun deleteFilm(@Path("id") id: Long): Response<ResponseBody>
    
    // Esborrar tot: DELETE /api/films
    @DELETE("api/films")
    suspend fun deleteAllFilms(): Response<ResponseBody>
}
