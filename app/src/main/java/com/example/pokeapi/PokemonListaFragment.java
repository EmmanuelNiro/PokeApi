package com.example.pokeapi;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pokeapi.Modelos.ListaRespuesta;
import com.example.pokeapi.Modelos.PokemonResumen;
import com.example.pokeapi.api.PokeApiServicio;
import com.example.pokeapi.api.RetrofitCliente;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListaFragment extends Fragment {
    private ListView lista;
    private ProgressBar cargando;
    private AdaptadorPokemon adaptador;
    private final List<PokemonResumen> datos = new ArrayList();

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup cont, @Nullable Bundle b) {
        View v = inf.inflate(R.layout.fragment_pokemon_list, cont, false);
        lista = v.findViewById(R.id.listaPokemons);
        cargando = v.findViewById(R.id.cargando);
        adaptador = new AdaptadorPokemon(requireContext(), datos) {
            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        };
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener((parent, view, position, id) -> {
            PokemonResumen p = datos.get(position);
            PokemonDetalleFragment f = PokemonDetalleFragment.nuevo(p.getName());
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, f)
                    .addToBackStack(null)
                    .commit();
        });
        cargarDatos();
        return v;
    }

    private void cargarDatos(){
        cargando.setVisibility(View.VISIBLE);
        PokeApiServicio api = RetrofitCliente.obtener().create(PokeApiServicio.class);
        api.obtenerListaPokemons(151,0).enqueue(new Callback<ListaRespuesta>(){
        @Override public void onResponse(Call<ListaRespuesta> call, Response<ListaRespuesta> resp) {
            cargando.setVisibility(View.GONE);
            if(resp.isSuccessful() && resp.body()!=null) {
                datos.clear();
                datos.clear();
                datos.addAll(resp.body().getResults());
            }else{
            Toast.makeText(requireContext(), "Error al cargar", Toast.LENGTH_SHORT).show();
            }
        }
        @Override public void onFailure(Call<ListaRespuesta> call, Throwable t){
            cargando.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "Fallo de red: "+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }
}
