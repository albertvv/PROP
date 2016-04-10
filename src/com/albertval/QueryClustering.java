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
            centramedioide();
            assignagrups(vvs);
        }
        return null;
    }

    private void assignagrups(Vector<Vector<String>> vvs) {
        for (int i = 0; i <ngrups ; i++) {
            if(dist(vs.get(i),vvs.get(i).get(0))>0){}
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
            vvs.get(i).add(vs.get(randomint));
        }
    }

    private void centramedioide() {
    }

}
