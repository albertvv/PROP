package com.albertval;

import org.la4j.matrix.SparseMatrix;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Albert on 09/04/2016.
 */
public class QueryClustering extends Query {
    private int ngrups;
    private SparseMatrix m1;
    private SparseMatrix m2;
    private SparseMatrix m3;
    public QueryClustering(String path, int numgrups, Vector<String> vs, SparseMatrix m1, SparseMatrix m2, SparseMatrix m3,Metrica m){
        super(path,vs,m);
        this.ngrups = numgrups;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    public Vector<Vector<String>> Cerca(int it){
        Vector<Vector<String>> vvs = new Vector<>();
        randommedioides(vvs);
        assignagrups(vvs);
        for(int i=0;i<it;++i) { // es podria fer un while escentren on fos una var bool retornada pel conjunt de centramedioide
            for(int j=0;j<ngrups;++j) centramedioide(vvs.get(j)); //es podria fer que retornes un bool per si es recentren o ja no es mouen mes
            reassigna(vvs);
        }
        return vvs;
    }

    public void reassigna(Vector<Vector<String>> vvs) {
        for (int i = 0; i < vvs.size() ; i++) { //recorrem la matriu
            int j=1;
            while(j<vvs.get(i).size()){
                double relmedioide=0;
                int num=0;
                for (int k = 0; k < ngrups ; k++) {
                    double aux = dist(vvs.get(i).get(j), vvs.get(k).get(0)); // vs no conté els medioides
                    System.out.println("rellevancia grup :"+k+" es de :"+aux);
                    if (aux > relmedioide) {
                        num = k; //num es el grup on va
                        relmedioide = aux;
                    }
                }
                System.out.println("La "+vvs.get(i).get(j)+" anirà al grup :"+num);
                if(num!=i) {
                    vvs.get(num).add(vvs.get(i).get(j)); //Afegim al nou grup
                    vvs.get(i).remove(j); // l'esborrem del antic
                }else ++j;

            }
        }
    }

    public void assignagrups(Vector<Vector<String>> vvs) {
        for (int i = 0; i < vs.size() ; i++) {
            double relmedioide=0;
            int num=0;
            for (int j = 0; j < ngrups ; j++) {
                double aux = dist(vs.get(i), vvs.get(j).get(0)); // vs no conté els medioides
//                System.out.println("Distancia entre :"+vs.get(i)+" i :"+vvs.get(j).get(0)+" amb rellevancia : "+aux);
                if (aux > relmedioide) {
                    num = j;
                    relmedioide = aux;
                }
//                System.out.println(" num :"+num);
            }
            vvs.get(num).add(vs.get(i));
        }
    }

    private double dist(String s, String s1) {
        return m.computaCamino(s,s1,path,m1,m2,m3);
    }

    public void randommedioides(Vector<Vector<String>> vvs) {
        Random r = new Random();
        for(int i=0;i<ngrups;++i){
            int randomint = r.nextInt(vs.size());
            vvs.add(new Vector<>());
            vvs.get(i).add(vs.get(randomint)); //Cada fila es un grup;
            vs.remove(randomint);
        }
    }

    public void centramedioide(Vector<String> vec) { // es podria fer que retornes un bool per si no ha fet falta recentrar i aixi no faria falta fer iteracions de centreig
        int posmed = 0;
        double dmitja = calculadistmitja(vec,0);
//        System.out.println("distmitja medioide :"+dmitja);  // dmitja del medioide
        for (int i = 1; i < vec.size() ; i++) {
            double aux = calculadistmitja(vec,i);
//            System.out.println("distmitja del element :"+i+" és :"+aux);
            if(aux>dmitja){
                posmed = i;
                dmitja=aux;
            }
        }
//        System.out.println("nou medioide posicio :"+posmed);
        if(posmed!=0) Collections.swap(vec,0,posmed);
        //calcula relevanciamitja per a cada element de la fila i busquem qui té la maxima que serà el nou medioide
        //relevanciamitja = Erelevancies/ #elements-1 (no es fa amb ell mateix)
    }

    private Double calculadistmitja(Vector<String> vec, int j) {
        double dmitja=0;
        for (int i = 0; i < vec.size() ; i++) {
            if(i!=j){
                dmitja+= m.computaCamino(vec.get(i),vec.get(j),path,m1,m2,m3);
            }
        }
        dmitja/= vec.size()-1; //no es fa sobre si mateix
        return dmitja;
    }

}
