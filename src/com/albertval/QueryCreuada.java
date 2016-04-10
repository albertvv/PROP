package com.albertval;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
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

    public Map<Integer,Double> CreuaResultats(){ //suposem està ordenat per nom
        Map<Integer,Double> md = new HashMap<>(); //podria ser un map en funció de metrica
        boolean found = false;
        for (Map.Entry<Integer,Double> entry : r1.getRes().entrySet()){
            if(r2.getRes().containsKey(entry.getKey())){
                md.put(entry.getKey(),(r1.getRes().get(entry.getKey())+entry.getValue())/2);
            }
        }
        return md;
    }

}
