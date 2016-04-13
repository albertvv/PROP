package com.albertval;

import org.la4j.matrix.SparseMatrix;
import org.la4j.vector.SparseVector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Albert on 09/04/2016.
 */
public class QueryRelimportant extends Query {
    protected String nomentitat;
    private SparseMatrix m1;
    private SparseMatrix m2;
    private SparseMatrix m3;
    public QueryRelimportant(String path, String nome, Metrica m, SparseMatrix m1, SparseMatrix m2, SparseMatrix m3) {
        super(path,m);
        this.nomentitat = nome;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }
    public SparseVector Cerca(){  //vector ordenat de id de Entitats
        // Map<Integer,Double> m = new HashMap<>();
        // Volcadades(m,v);
        return m.computaMetrica(nomentitat,path,m1,m2,m3);
    }

    private void Volcadades(Map<Integer,Double> m, SparseVector v) {
        for (int i = 0; i < v.length(); i++) { //es podria millorar una mica amb while m < v.cardinality
           if(!v.isZeroAt(i)) m.put(i,v.get(i));
        }
    }
}
