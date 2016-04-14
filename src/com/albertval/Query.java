package com.albertval;

import org.la4j.matrix.SparseMatrix;

import java.util.List;
import java.util.Vector;

/**
 * Created by Albert on 08/04/2016.
 */
public abstract class Query {
    protected String path;
    protected Vector<String> vs; //fa falta que estigui a query?
    protected Metrica m;
    protected SparseMatrix m1;
    protected SparseMatrix m2;
    protected SparseMatrix m3;
    protected Query(String path, Vector<String> vs,Metrica m, SparseMatrix m1, SparseMatrix m2, SparseMatrix m3) {
        this.path = path;
        this.vs = vs;
        this.m = m;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    protected Query(String path, Metrica m,SparseMatrix m1, SparseMatrix m2, SparseMatrix m3) {
        this.path = path;
        this.m = m;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    protected Query(String path,Vector<String> vs,SparseMatrix m1, SparseMatrix m2, SparseMatrix m3) {
        this.path = path;
        this.vs = vs;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }
}
