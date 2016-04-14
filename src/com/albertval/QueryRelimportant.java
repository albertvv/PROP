package com.albertval;

import org.la4j.matrix.SparseMatrix;
import org.la4j.vector.SparseVector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Albert on 09/04/2016.
 */
public class QueryRelimportant extends Query {
    private String nomentitat;
    public QueryRelimportant(String path, String nomE, Metrica m, SparseMatrix m1, SparseMatrix m2, SparseMatrix m3) {
        super(path,m,m1,m2,m3);
        this.nomentitat = nomE;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }
    public SparseVector Cerca(){  //vector ordenat de id de Entitats
        return m.computaMetrica(nomentitat,path,m1,m2,m3);
    }

}
