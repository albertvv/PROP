package com.albertval;

import javafx.util.Pair;

import java.util.Vector;

/**
 * Created by Albert on 09/04/2016.
 */
public class QueryCreuada extends Query {
        private ResImportant r1,r2;
        QueryCreuada(ResImportant r1, ResImportant r2){
            this.r1 = r1;
            this.r2 = r2;
        }

    public Vector< Pair<Entidad,Double> > CreuaResultats(){
        Vector< Pair<Entidad,Double> > vd = new Vector<>();
        return vd;
    }
}
