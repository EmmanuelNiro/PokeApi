package com.example.pokeapi;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pokeapi.Modelos.PokemonDetalle;
import com.example.pokeapi.api.PokeApiServicio;
import com.example.pokeapi.api.RetrofitCliente;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetalleFragment extends Fragment {
    private static final String ARG_NOMBRE = "nombre";
    public static PokemonDetalleFragment nuevo(String nombre) {
        Bundle b = new Bundle(); b.putString(ARG_NOMBRE, nombre);
        PokemonDetalleFragment f = new PokemonDetalleFragment(); f.setArguments(b);
        return f;
    }


    private ImageView imagen;
    private TextView tituloNombre, textoId, textoTipos, textoHabilidades, textoAlturaPeso;
    private ProgressBar cargando;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup cont, @Nullable Bundle b) {
        View v = inf.inflate(R.layout.fragment_pokemon_detalle, cont, false);
        imagen = v.findViewById(R.id.imagenDetalle);
        tituloNombre = v.findViewById(R.id.tituloNombre);
        textoId = v.findViewById(R.id.textoId);
        textoTipos = v.findViewById(R.id.textoTipos);
        textoHabilidades = v.findViewById(R.id.textoHabilidades);
        textoAlturaPeso = v.findViewById(R.id.textoAlturaPeso);
        cargando = v.findViewById(R.id.cargandoDetalle);

        String nombre = getArguments()!=null ? getArguments().getString(ARG_NOMBRE) : null;
        if (nombre != null) cargarDetalle(nombre);
        return v;

    }

    private void cargarDetalle(String nombre) {
        cargando.setVisibility(View.VISIBLE);
        PokeApiServicio api = RetrofitCliente.obtener().create(PokeApiServicio.class);
        api.obtenerPokemon(nombre).enqueue(new Callback<PokemonDetalle>(){
            @SuppressLint("SetTextI18n")
            @Override public void onResponse(Call<PokemonDetalle> call, Response<PokemonDetalle> resp) {
                cargando.setVisibility(View.GONE);
                if (!isAdded()) return;
                if (resp.isSuccessful() && resp.body()!=null){
                    PokemonDetalle p = resp.body();
                tituloNombre.setText(capitalizar(p.getName()));
                textoId.setText("ID: #" + p.getId());
                textoTipos.setText("Tipos: " + p.tiposComoCadena());
                textoHabilidades.setText("Habilidades: " + p.habilidadesComoCadena());
                textoAlturaPeso.setText("Altura: " + (p.getHeight()/10.0) + "m Peso: " + (p.getWeight()/10.0) + "kg");
                String urlImg = p.getSprites()!=null ? p.getSprites().getFront_default():null;
                if (urlImg==null) {
                    urlImg = "https://raw.githubusercontent.com/PokeApi/sprites/master/sprites/pokemon" + p.getId() + ".png";
                }
                Glide.with(requireContext()).load(urlImg).into(imagen);
            }else{
                Toast.makeText(requireContext(), "No se pudo cargar el detalle", Toast.LENGTH_SHORT).show();
            }
        }
        @Override public void onFailure(Call<PokemonDetalle> call, Throwable t){
            cargando.setVisibility(View.GONE);
            if (isAdded())
                Toast.makeText(requireContext(), "Fallo de red"+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}

private String capitalizar(String s){
    if (s==null || s.isEmpty()) return s;
    return s.substring(1,0).toUpperCase()+s.substring(1);
}


}