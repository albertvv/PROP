package com.albertval;


import org.la4j.vector.SparseVector;

import java.util.Map;

/**
 * Created by Albert on 10/04/2016.
 */
public class ResImportant {
    private SparseVector res;

    public ResImportant(SparseVector res) {
        this.res = res;
    }

    public SparseVector getRes() {
        return res;
    }
}
