package com.albertval;

import javafx.util.Pair;
import org.la4j.iterator.VectorIterator;
import org.la4j.vector.SparseVector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Albert on 09/04/2016.
 */
public class RelimpCreuada {
        private ResImportant r1,r2;
        public RelimpCreuada(ResImportant r1, ResImportant r2){
            this.r1 = r1;
            this.r2 = r2;
        }

//    public Map<Integer,Double> CreuaResultats(){ //suposem està ordenat per nom
//        Map<Integer,Double> md = new HashMap<>(); //podria ser un map en funció de metrica
//       // Iterator it = r1.getRes().keySet().iterator();
//        for(Map.Entry<Integer,Double> m : r1.getRes().entrySet()) {
//            if(r2.getRes().containsKey(m.getKey())){
//                md.put(m.getKey(),((m.getValue()+r2.getRes().get(m.getKey()))/2));
//            }
//        }
//        return md;
//    }
//  Potser es mes facil que retorni sparse vector i creues en un sol vector
    public Vector<Pair<Integer,Double>> CreuaResultats(){ //suposem està ordenat per nom
        Vector<Pair<Integer,Double>> vd = new Vector<>(); //podria ser un map en funció de metrica
        VectorIterator it = r1.getRes().nonZeroIterator();
        while(it.hasNext()){
            Double val = it.next();
            if(r2.getRes().nonZeroAt(it.index())){
                vd.add(new Pair<>(it.index(), (val+r2.getRes().get(it.index()))/2));
            }
        }
        return vd;
    }
}
