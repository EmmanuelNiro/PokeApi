package com.example.pokeapi.Modelos;

import java.util.List;
public class ListaRespuesta {
    private int count;
    private String next;
    private String previous;
    private List<PokemonResumen> results;

    public int getCount() { return count; }
    public String getNext() { return next; }
    public String getPrevious() {return previous; }
    public List<PokemonResumen> getResults() { return results; }
}
