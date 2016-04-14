package com.albertval;

import javafx.util.Pair;
import org.la4j.matrix.SparseMatrix;

import java.util.List;
import java.util.Vector;

/**
 * Created by Albert on 08/04/2016.
 */
public class QueryRellevancia extends Query{
    public QueryRellevancia(String path, Vector<String> vs, Metrica m, SparseMatrix m1,SparseMatrix m2, SparseMatrix m3) {
        super(path, vs, m,m1,m2,m3);
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    } //falten matrius del graf

    public Double Cerca(){
        Double res=1.0;
        String aux;
        int i=0,iaux;
        while(i<vs.size()-1){ //penultim element
            aux = vs.get(i);
            iaux= i;
            ++i;
            while(vs.get(i)==null) ++i; // sabem que almenys l'ultim element no es null
            System.out.println("cami entre en "+aux+" i en "+vs.get(i)+" path "+path.substring(iaux,i+1));
            res *= m.computaCamino(aux,vs.get(i),path.substring(iaux,i+1),m1,m2,m3); //haurem de tallar aquest path
        }
        return res;
    }
}
