package com.albertval;

import java.util.Random;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        System.out.print("hey gent ");
        String hey = "ho";
//        System.out.print(hey.substring(0,2));
//        String path = "hola";
//        Vector<String> vs = new Vector<>();
//        Metrica m = new Metrica();
//        Query q = new QueryRellevancia(path,vs,m);
        Random r = new Random();
        Vector<Vector<String>> vs = new Vector<>();
        for(int i=0;i<10;++i) {
            int randomint = r.nextInt(20);
            vs.add(new Vector<String>());
            vs.get(i).add("fac"+r);
            System.out.println(randomint+" "+vs.size());
        }
    }
}
