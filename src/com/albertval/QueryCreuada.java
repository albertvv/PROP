package com.albertval;

import javafx.util.Pair;

import java.util.Vector;

/**
 * Created by Albert on 09/04/2016.
 */
public class QueryCreuada extends Query {
        private ResImportant r1,r2;
        public QueryCreuada(ResImportant r1, ResImportant r2){
            this.r1 = r1;
            this.r2 = r2;
        }
    private void cercadico(String nome){

    }

    public Vector< Pair<Entidad,Double> > CreuaResultats(){ //suposem està ordenat per nom
        Vector< Pair<Entidad,Double> > vd = new Vector<>(); //podria ser un map en funció de metrica
        boolean found = false;
        for(int i=0;i<r1.getVe().size();++i){
            cercadico(r1.getVe().get(i).getKey().getNombre()); // si fos map es podria fer amb un find
        }
        return vd;
    }

}
