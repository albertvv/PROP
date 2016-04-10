package com.albertval;

import javafx.util.Pair;

import java.util.Vector;

/**
 * Created by Albert on 09/04/2016.
 */
public class QueryRelimportant extends Query {
    protected String nomentitat;

    public QueryRelimportant(String path, String nome, Metrica m) {
        super(path,m);
        this.nomentitat = nome;
    }
    public Vector<Pair<Entidad,Double>>Cerca(){  //potser es un map amb entitats o nom entitats
        return m.computaMetrica(nomentitat,path);         //controlador crearà un objecte resultat i el guardarà a llista resultats
    }
}
