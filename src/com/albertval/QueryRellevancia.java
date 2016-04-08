package com.albertval;

import javafx.util.Pair;

import java.util.List;
import java.util.Vector;

/**
 * Created by Albert on 08/04/2016.
 */
public class QueryRellevancia extends Query{
    private Double resultat;

    public QueryRellevancia(String path, Vector<String> vs) {
        this.path = path;
        this.vs = vs;
    }

    public Double getResultat() {
        return resultat;
    }
    //Pre: la longitud del path es la mateix que la de la llista entitat
    //les entitats que no hem definit en el vector son nules
    //el primer i el ultim element de la llista no son nuls

    public Double Cerca(Metrica m/*,matriu a,matriu c,matriu t*/){
        Double res=1.0;
        String aux;
        Integer i=0,iaux;
        while(i<=vs.size()){
            aux = vs.get(i);
            iaux= i;
            ++i;
            while(vs.get(i)==null) ++i; // sabem que almenys l'ultim element no es null
            res *= m.computaCamino(aux,vs.get(i),path.substring(iaux,i+1)); //haurem de tallar aquest path
        }
        return res;
    }
}
