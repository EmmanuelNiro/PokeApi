package com.example.pokeapi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle estado) {
        super.onCreate(estado);
        setContentView(R.layout.activity_main);
        if (estado == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new PokemonListaFragment())
                    .commit();
        }
    }

}