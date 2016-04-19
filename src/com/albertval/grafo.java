import org.la4j.matrix.SparseMatrix;
import org.la4j.matrix.ColumnMajorSparseMatrix;
import org.la4j.matrix.sparse.CCSMatrix;
import org.la4j.vector.SparseVector;
import org.la4j.vector.sparse.CompressedVector;

//import Entidad; ???

import java.util.Vector;

public class grafo {

    //ATRIBUTS
	/*Vectors d'entitats*/
    private Vector<Autor>		vectorAutor;
    private Vector<Conferencia>	vectorConferencia;
    private Vector<Termino>		vectorTermino;
    private Vector<Articulo>	vectorArticulo;
    /*Matrius d'adjacencia*/
    private SparseMatrix    matrizPaperAutor;
    private SparseMatrix	matrizPaperConferencia;
    private SparseMatrix	matrizPaperTermino;
    /*Darrera ID emprada*/
    private Integer lastId;


    //CONSTRUCTORA
    public grafo() {
        vectorAutor 			= new Vector<Autor>();
        vectorConferencia		= new Vector<Conferencia>();
        vectorTermino 			= new Vector<Termino>();
        vectorArticulo 			= new Vector<Articulo>();

        matrizPaperAutor 		= new CCSMatrix();
        matrizPaperConferencia  = new CCSMatrix();
        matrizPaperTermino 		= new CCSMatrix();

        lastId = -1;
    }


    //METODES

    /*Aquest mètode ara és privat (no té sentit que sigui públic)*/
    private int getIndice(String nombre, String tipo) {
        int n;
        switch (tipo) {
            case "Articulo":
                n = vectorArticulo.size();
                for (int i = 0; i < n; ++n)
                    if (vectorArticulo.get(i).getNombre().equals(nombre)) return i;
            case "Autor":
                n = vectorAutor.size();
                for (int i = 0; i < n; ++n)
                    if (vectorAutor.get(i).getNombre().equals(nombre)) return i;
            case "Conferencia":
                n = vectorConferencia.size();
                for (int i = 0; i < n; ++n)
                    if (vectorConferencia.get(i).getNombre().equals(nombre)) return i;
            case "Termino":
                n = vectorTermino.size();
                for (int i = 0; i < n; ++n)
                    if (vectorTermino.get(i).getNombre().equals(nombre)) return i;
            default:
                System.out.println("Capsigrany! El tipus d'entitat ha de ser Articulo, Autor, Conferencia o Termino");
                return NULL;
        }
    }

    public void addEntidad(String nombre, String tipoEntidad) {
        Integer index = getIndice(nombre, tipoEntidad);
        Vector<Double> tmpVec1, tmpVec2, tmpVec3;
        switch (tipoEntidad) {
            case "Articulo":
                Articulo tmpArticulo = new Articulo(getIndice(++lastId, nombre));
                vectorArticulo.addElement(index, tmpArticulo);
                tmpVec1 = new Vector(vectorAutor.size());
                matrizPaperAutor.insertRow(vectorArticulo.size()-1, tmpVec1);
                tmpVec2 = new Vector(vectorConferencia.size());
                matrizPaperConferencia.insertRow(vectorArticulo.size()-1, tmpVec2);
                tmpVec3 = new Vector(vectorArticulo.size());
                matrizPaperTermino.insertRow(vectorArticulo.size()-1, tmpVec3);
                break;
            case "Autor":
                Autor tmpAutor = new Autor(++lastId, nombre);
                vectorAutor.addElement(index, tmpAutor);
                tmpVec1 = new Vector<Double>();
                matrizPaperAutor.insertColumn(vectorAutor.size()-1, tmpVec1);
                break;
            case "Conferencia":
                Conferencia tmpConferencia = new Conferencia(++lastId, nombre);
                vectorConferencia.addElement(index, tmpConferencia);
                tmpVec1 = new Vector<Double>();
                matrizPaperConferencia.insertColumn(vectorAutor.size()-1, tmpVec1);
                break;
            case "Termino":
                Termino tmpTermino = new Termino(++lastId, nombre);
                vectorTermino.addElement(tmpTermino);
                tmpVec1 = new Vector<Double>();
                matrizPaperTermino.insertColumn(vectorAutor.size()-1, tmpVec1);
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
        Integer index1, index2;
        switch (tipoRelacion) {
            case "AP":
                index1 = getIndice(nombre1, "Articulo");
                index2 = getIndice(nombre2, "Autor");
                matrizPaperAutor.set(index1, index2, 1);
                break;
            case "CP":
                index1 = getIndice(nombre1, "Articulo");
                index2 = getIndice(nombre2, "Conferencia");
                matrizPaperConferencia.set(index1, index2, 1);
                break;
            case "TP":
                index1 = getIndice(nombre1, "Articulo");
                index2 = getIndice(nombre2, "Termino");
                matrizPaperTermino.set(index1, index2, 1);
                break;
            default:
                System.out.println("Capsigrany! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public void deleteRelacion(String nombreOrigen, String nombreDesti, String tipoRelacion) {
        Integer index1, index2;
        switch (tipoRelacion) {
            case "AP":
                index1 = getIndice(nombre1, "Articulo");
                index2 = getIndice(nombre2, "Autor");
                matrizPaperAutor.set(index1, index2, 0);
                break;
            case "CP":
                index1 = getIndice(nombre1, "Articulo");
                index2 = getIndice(nombre2, "Conferencia");
                matrizPaperConferencia.set(index1, index2, 0);
                break;
            case "TP":
                index1 = getIndice(nombre1, "Articulo");
                index2 = getIndice(nombre2, "Termino");
                matrizPaperTermino.set(index1, index2, 0);
                break;
            default:
                System.out.println("Talós! Cal triar un tipus d'entitat valid");
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
        if (!tipoFila.equals("Articulo"))/*atribut innecessari...?*/{
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
    //funcions que m'ha demanat l'Albert
    public Integer getId(String nom, String tipusEntitat) {
        //
        return NULL;
    }
    public String getNombre(Integer id, String tipusEntitat) {
        //
        return NULL;
    }
}
