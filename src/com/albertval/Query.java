package com.albertval;

import java.util.List;
import java.util.Vector;

/**
 * Created by Albert on 08/04/2016.
 */
public abstract class Query {
    protected String path;
    protected Vector<String> vs; //fa falta que estigui a query?
    protected Metrica m;

    protected Query(String path, Vector<String> vs,Metrica m /*,matriu a,matriu c,matriu t*/) {
        this.path = path;
        this.vs = vs;
        this.m = m;
    }

    protected Query() {
    }

    protected Query(String path, Metrica m) {
    }

    protected Query(String path,Vector<String> vs) {
    }
}
