package com.example.pokeapi.Modelos;

import java.util.List;

public class PokemonDetalle {

    private int id;
    private String name;
    private int height;
    private int weight;
    private Sprites sprites;
    private  List<TipoEntry> types;
    private List<Tipo.HabilidadEntry> abilities;

    public int getId(){return id;}
    public String getName(){return name;}
    public int getHeight(){return height;}
    public int getWeight(){return weight;}
    public Sprites getSprites(){return sprites;}
    public List<TipoEntry> getTypes(){return types;}
    public List<Tipo.HabilidadEntry> getAbilities(){return abilities;}

    public String tiposComoCadena() {
        if (types == null || types.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<types.size();i++){
            sb.append(cap(types.get(i).getType().getName()));
            if (i<types.size()-1) sb.append(", ");
        }
        return sb.toString();
    }

    public String habilidadesComoCadena() {
        if (abilities == null || abilities.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<abilities.size();i++){
            sb.append(cap(abilities.get(i).getAbility().getName()));
            if (i<abilities.size()-1) sb.append(", ");
        }
        return sb.toString();
    }
    private String cap(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }


    public static class Sprites{
        private String front_default;
        public String getFront_default(){return front_default;}
    }
    public static class TipoEntry{
        private int slot;
        private Tipo type;
        public Tipo getType(){ return type; }
    }
    public static class Tipo{
        private String name;
        public String getName(){return name;}
    public static class HabilidadEntry{
            private Habilidad ability;
            public Habilidad getAbility(){ return ability; }
    }
    public static class Habilidad{
            private String name;
            public String getName(){return name; }
    }


    }
}
