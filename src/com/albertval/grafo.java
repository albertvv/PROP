package com.albertval;

import org.la4j.matrix.SparseMatrix;
import java.util.Vector;

public class Grafo {

    //ATRIBUTS
	/*Vectors d'entitats*/
    private Vector<Autor>		vectorAutor;
    private Vector<Conferencia>	vectorConferencia;
    private Vector<Termino>		vectorTermino;
    private Vector<Articulo>	vectorArticulo;
    /*Matrius d'adjacencia*/
    private SparseMatrix	matrizPaperAutor;
    private SparseMatrix	matrizPaperConferencia;
    private SparseMatrix	matrizPaperTermino;
    /*Darrera ID emprada*/
    private Integer lastId;


    //CONSTRUCTORA
    public Grafo() {
        vectorAutor 			= new Vector<Autor>();
        vectorConferencia		= new Vector<Conferencia>();
        vectorTermino 			= new Vector<Termino>();
        vectorArticulo 			= new Vector<Articulo>();

        matrizPaperAutor 		= new SparseMatrix();
        matrizPaperConferencia  = new SparseMatrix();
        matrizPaperTermino 		= new SparseMatrix();

        lastId = -1;
    }


    //METODES

    /*Aquest mètode ara és privat (no té sentit que sigui públic)*/
    private int getIndice(String nombre, String tipo) {
        switch (tipo) {
            case "Articulo":
                int n = vectorArticulo.size();
                for (int i = 0; i < n; ++n)
                    if (vectorArticulo.get(i).getNombre().equals(nombre)) return i;
            case "Autor":
                int n = vectorAutor.size();
                for (int i = 0; i < n; ++n)
                    if (vectorAutor.get(i).getNombre().equals(nombre)) return i;
            case "Conferencia":
                int n = vectorConferencia.size();
                for (int i = 0; i < n; ++n)
                    if (vectorConferencia.get(i).getNombre().equals(nombre)) return i;
            case "Termino":
                int n = vectorTermino.size();
                for (int i = 0; i < n; ++n)
                    if (vectorTermino.get(i).getNombre().equals(nombre)) return i;
            default:
                System.out.println("Capsigrany! El tipus d'entitat ha de ser Articulo, Autor, Conferencia o Termino")
                return NULL;
        }
    }

    public void addEntidad(String nombre, String tipoEntidad) {
        switch (tipoEntidad) {
            case "Articulo":
                Articulo tmpArticulo = new Articulo(getIndice(++lastId, nombre));
                vectorArticulo.addElement(index, tmpArticulo);
                Vector<double> tmpVec = new Vector<double>();
                matrizPaperAutor.insertRow(vectorArticulo.size()-1, tmpVec);
                matrizPaperConferencia.insertRow(vectorArticulo.size()-1, tmpVec);
                matrizPaperTermino.insertRow(vectorArticulo.size()-1, tmpVec);
                break;
            case "Autor":
                Autor tmpAutor = new Autor(++lastId, nombre);
                vectorAutor.addElement(index, tmpAutor);
                Vector<double> tmpVec = new Vector<double>();
                matrizPaperAutor.insertColumn(vectorAutor.size()-1, tmpVec);
                break;
            case "Conferencia":
                Conferencia tmpConferencia = new Conferencia(++lastId, nombre);
                vectorConferencia.addElement(index, tmpConferencia);
                Vector<double> tmpVec = new Vector<double>();
                matrizPaperConferencia.insertColumn(vectorAutor.size()-1, tmpVec);
                break;
            case "Termino":
                Termino tmpTermino = new Termino(++lastId, nombre);
                vectorTermino.addElement(tmpTermino);
                Vector<double> tmpVec = new Vector<double>();
                matrizPaperTermino.insertColumn(vectorAutor.size()-1, tmpVec);
                break;
            default:
                System.out.println("Tros d'ase! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public void deleteEntidad(String nombre, String tipoEntidad) {
        switch (tipoEntidad) {
            case "Articulo":
                int i = getIndice(nombre, "Articulo");
                vectorArticulo.set(i, NULL);
                matrizPaperAutor.removeRow(i);
                matrizPaperConferencia.removeRow(i);
                matrizPaperTermino.removeRow(i);
                if (i == lastId) --lastId;
                break;
            case "Autor":
                int i = getIndice(nombre, "Autor");
                vectorAutor.set(i, NULL);
                matrizPaperAutor.removeColumn(i);
                if (i == lastId) --lastId;
                break;
            case "Conferencia":
                int i = getIndice(nombre, "Conferencia");
                vectorConferencia.set(i, NULL);
                matrizPaperConferencia.removeColumn(i);
                if (i == lastId) --lastId;
                break;
            case "Termino":
                int i = getIndice(nombre, "Termino");
                vectorTermino.set(i, NULL);
                matrizPaperTermino.removeColumn(i);
                if (i == lastId) --lastId;
                break;
            default:
                System.out.println("Babau! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public void addRelacion(String nombre1, String nombre2, String tipoRelacion) {
        switch (tipoRelacion) {
            case "AP":
                Integer index1 = getIndice(nombre1, "Articulo"), index2 = getIndice(nombre2, "Autor");
                matrizPaperAutor.set(index1, index2, 1);
                break;
            case "CP":
                Integer index1 = getIndice(nombre1, "Articulo"), index2 = getIndice(nombre2, "Conferencia");
                matrizPaperConferencia.set(index1, index2, 1);
                break;
            case "TP":
                Integer index1 = getIndice(nombre1, "Articulo"), index2 = getIndice(nombre2, "Termino");
                matrizPaperTermino.set(index1, index2, 1);
                break;
            default:
                System.out.println("Capsigrany! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public void deleteRelacion(String nombreOrigen, String nombreDesti, String tipoRelacion) {
        switch (tipoRelacion) {
            case "Articulo":
                Integer index1 = getIndice(nombre1, "Articulo"), index2 = getIndice(nombre2, "Articulo");
                matrizPaperAutor.set(index1, index2, 0);
                matrizPaperAutor.set(index2, index1, 0);
                matrizPaperConferencia.set(index1, index2, 0);
                matrizPaperConferencia.set(index2, index1, 0);
                matrizPaperTermino.set(index1, index2, 0);
                matrizPaperTermino.set(index2, index1, 0);
                break;
            case "Autor":
                Integer index1 = getIndice(nombre1, "Autor"), index2 = getIndice(nombre2, "Autor");
                matrizPaperAutor.set(index1, index2, 0);
                matrizPaperAutor.set(index2, index1, 0);
                break;
            case "Conferencia":
                Integer index1 = getIndice(nombre1, "Conferencia"), index2 = getIndice(nombre2, "Conferencia");
                matrizPaperConferencia.set(index1, index2, 0);
                matrizPaperConferencia.set(index2, index1, 0);
                break;
            case "Termino":
                Integer index1 = getIndice(nombre1, "Termino"), index2 = getIndice(nombre2, "Termino");
                matrizPaperTermino.set(index1, index2, 0);
                matrizPaperTermino.set(index2, index1, 0);
                break;
            default:
                System.out.println("Tros de suro! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public Vector<Entidad> getRelacion(String nombre, String tipoEntidad) {
        Vector<Entidad> vR = new Vector<Entidad>();
        switch (tipoEntidad) {
            case "Articulo":
                int n, i;
                i = getIndice(nombre, "Articulo");
                //autors:
                n = vectorAutor.size();
                for (int j = 0; j < n; ++j)
                    if (!matrizPaperAutor.isZeroAt(i, j)) vR.addElement(vectorAutor.elementAt(j));
                //conferencies:
                n = vectorConferencia.size();
                for (int j = 0; j < n; ++j)
                    if (!matrizPaperConferencia.isZeroAt(i, j)) vR.addElement(vectorConferencia.elementAt(j));
                //termes:
                n = vectorTermino.size();
                for (int j = 0; j < n; ++j)
                    if (!matrizPaperTermino.isZeroAt(i, j)) vR.addElement(vectorTermino.elementAt(j));
                return vR;
            case "Autor":
                int n, j;
                j = getIndice(nombre, "Autor");
                n = vectorArticulo.size();
                for (int i = 0; i < n; ++i)
                    if (!matrizPaperAutor.isZeroAt(i, j)) vR.addElement(vectorArticulo.elementAt(i));
                return vR;
            case "Conferencia":
                int n, j;
                j = getIndice(nombre, "Conferencia");
                n = vectorArticulo.size();
                for (int i = 0; i < n; ++i)
                    if (!matrizPaperConferencia.isZeroAt(i, j)) vR.addElement(vectorConferencia.elementAt(i));
                return vR;
            case "Termino":
                int n, j;
                j = getIndice(nombre, "Termino");
                n = vectorArticulo.size();
                for (int i = 0; i < n; ++i)
                    if (!matrizPaperTermino.isZeroAt(i, j)) vR.addElement(vectorTermino.elementAt(i));
                return vR;
            default:
                System.out.println("Ruc! Cal triar un tipus d'entitat valid");
                return vR;
        }
    }

    public Entidad getEntidad(String nombre, String tipoEntidad) {
        switch (tipoEntidad) {
            case "Articulo":
                return vectorArticulo.get(getIndice(nombre, "Articulo"));
            case "Autor":
                return vectorAutor.get(getIndice(nombre, "Autor"));
            case "Conferencia":
                return vectorConferencia.get(getIndice(nombre, "Conferencia"));
            case "Termino":
                return vectorTermino.get(getIndice(nombre, "Termino"));
            default:
                System.out.println("Tanoca! Cal triar un tipus d'entitat valid");
                return NULL;
        }
    }

    public SparseMatrix getMatriz(String tipoFila, String tipoColumna) {
        if (tipoFila != "Articulo")/*atribut innecessari...?*/{
            System.out.println("Gamarús! El tipus de fila de totes les matrius és 'Paper'");
            return NULL;
        }
        switch (tipoColumna) {
            case "Autor":
                return matrizPaperAutor;
            case "Conferencia":
                return matrizPaperConferencia;
            case "Termino":
                return matrizPaperTermino;
            default:
                System.out.println("Talós! El tipus de columna ha de ser Autor, Conferencia o Termino");
                return NULL;
        }
    }
}
