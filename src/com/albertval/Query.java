package com.albertval;

import java.util.List;
import java.util.Vector;

/**
 * Created by Albert on 08/04/2016.
 */
public abstract class Query {
    protected String path;
    protected Vector<String> vs; //fa falta que estigui a query?
    public Query() {

    }
    public Query(String path, Vector<String> vs) {
        this.path = path;
        this.vs = vs;
    }
}
