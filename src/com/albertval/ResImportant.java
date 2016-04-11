package com.albertval;


import javafx.util.Pair;

import java.util.Map;
import java.util.Vector;

/**
 * Created by Albert on 10/04/2016.
 */
public class ResImportant {
    private Map<Integer,Double> res;
    
    public ResImportant(Query q,Map<Integer,Double> res){
        this.Query = q;
        this.res = res;
    }
    public Map<Integer, Double> getRes() {
        return res;
    }
}
