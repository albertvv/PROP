package user_dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Grafo {
    public void addEntidad(String nom, String tipus, String etiq){System.out.println("Crida a addEntidad");}
    public void deleteEntidad(String nom, String tipus){System.out.println("Crida a deleteEntidad");}
    public void addRelacion(String nom1, String nom2, String tipus){System.out.println("Crida a addRelacion");}
    public void deleteRelacion(String nom1, String nom2, String tipus){System.out.println("Crida a deleteRelacion");}
    public Vector<Entidad> getRelacion(String nombre, String tipoEntidad){
        Vector<Entidad> result = new Vector<Entidad>();
        Autor autor = new Autor(1,"a","a");
        result.add(autor);
        Paper paper = new Paper(2,"b","b");
        result.add(paper);
        Termino term = new Termino(3,"c");
        result.add(term);
        return result;
    }
}
