package com.example.pokeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokeapi.Modelos.PokemonResumen;

import java.util.List;

public abstract class AdaptadorPokemon implements ListAdapter {
    private final Context contexto;
    private final List<PokemonResumen> lista;
    private final LayoutInflater inflador;

    public AdaptadorPokemon(Context c, List<PokemonResumen> l){
        this.contexto = c; this.lista = l; this.inflador = LayoutInflater.from(c);
    }

    @Override public int getCount(){ return lista.size(); }
    @Override public Object getItem(int pos){ return lista.get(pos); }
    @Override public long getItemId(int pos){ return pos; }

    @Override
    public View getView(int pos, View v, ViewGroup parent){
        Vista vh;
        if (v==null){
            v = inflador.inflate(R.layout.item_pokemon, parent, false);
            vh = new Vista();
            vh.imagen = v.findViewById(R.id.imagenPokemon);
            vh.nombre = v.findViewById(R.id.nombrePokemon);
            v.setTag(vh);
        }else
            vh = (Vista) v.getTag();
        PokemonResumen p = lista.get(pos);
        vh.nombre.setText(capitalizar(p.getName()));

        int id = extraerIdDesdeUrl(p.getUrl());
        String urlImg = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
        Glide.with(contexto).load(urlImg).into(vh.imagen);
        return v;
        }

        static class Vista{ ImageView imagen; TextView nombre; }

    private int extraerIdDesdeUrl(String url){
        if (url==null) return 0;
        String s = url.replaceAll(".*/pokemon/(\\d+)/?$", "$1");
        try { return Integer.parseInt(s); } catch (Exception e){ return 0; }
    }
    private String capitalizar(String s){
        if (s==null||s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }



}
