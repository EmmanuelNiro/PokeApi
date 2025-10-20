package com.example.pokeapi.api;

import com.example.pokeapi.Modelos.ListaRespuesta;
import com.example.pokeapi.Modelos.PokemonDetalle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiServicio {
    @GET("pokemon")
    Call<ListaRespuesta> obtenerListaPokemons(
            @Query("limit") int limite,
            @Query("offset") int offset
    );

     @GET("pokemon/{nombre}")
     Call<PokemonDetalle> obtenerPokemon(
       @Path("nombre") String nombre
     );
}
