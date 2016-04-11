package com.albertval;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Albert on 09/04/2016.
 */
public class QueryClustering extends Query {
    private int ngrups;
    public QueryClustering(String path,int numgrups, Vector<String> vs){
        super(path,vs);
        this.ngrups = numgrups;
    }

    public Vector<Vector<String>> Cerca(int it){
        Vector<Vector<String>> vvs = new Vector<>();
        randommedioides(vvs);
        assignagrups(vvs);
        for(int i=0;i<it;++i) {
            for(int j=0;j<ngrups;++j) centramedioide(j); //es podria fer que retornes un bool per si es recentren o ja no es mouen mes
            reassigna(vvs);
        }
        return null;
    }

    private void reassigna(Vector<Vector<String>> vvs) {
        for (int i = 0; i < vvs.size(); ++i){
            for( int j = 1 ; j < vvs.get(i).size(); ++j){
                for ( int k=0; k<ngrups;++k){
                    double aux = dist(vvs.get(i).get(j),vvs.get(k).get(0));
                    if (aux > relmedioide) {
                    num = j;
                    relmedioide = aux;
                    }
                }
                vvs.get(i).add(.....);
                vvs.delete(.....);
            }
        }
    }

    private void assignagrups(Vector<Vector<String>> vvs) {
        double relmedioide=0;
        int num=0;
        for (int i = 0; i < vs.size() ; i++) {
            for (int j = 0; j < ngrups ; j++) {
                double aux = dist(vs.get(i), vvs.get(j).get(0));
                if (aux > relmedioide) {
                    num = j;
                    relmedioide = aux;
                }
            }
            vvs.get(num).add(vs.get(i));
        }
    }

    private double dist(String s, String s1) {
        return m.computaCamino(s,s1,path);
    }

    private void randommedioides(Vector<Vector<String>> vvs) {
        Random r = new Random();
        for(int i=0;i<ngrups;++i){
            int randomint = r.nextInt(vs.size());
            vvs.add(new Vector<>());
            vvs.get(i).add(vs.get(randomint)); //Cada fila es un grup
            vs.remove(randomint);
        }
    }

    private void centramedioide(int j) {
        //calcula relevanciamitja per a cada element de la fila i busquem qui té la maxima que serà el nou medioide
        //relevanciamitja = Erelevancies/ #elements-1 (no es fa amb ell mateix)
    }

}
