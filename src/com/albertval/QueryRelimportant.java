package com.albertval;

import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;
import org.la4j.vector.SparseVector;

import java.util.HashMap;
import java.util.Map;
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
    public Map<Integer,Double> Cerca(){  //vector ordenat de id de Entitats
         SparseVector v = m.computaMetrica(nomentitat,path);         //controlador crearà un objecte resultat i el guardarà a llista resultats
         Map<Integer,Double> m = new HashMap<>();
         Volcadades(m,v);
        return m;
    }

    private void Volcadades(Map<Integer,Double> m, SparseVector v) {
        for (int i = 0; i < v.cardinality(); i++) {
           if(!v.isZeroAt(i)) m.put(i,v.get(i));
        }
    }
}
