import org.la4j.matrix.SparseMatrix;
import org.la4j.matrix.sparse.CCSMatrix;
import org.la4j.vector.sparse.CompressedVector;

import java.util.Vector;



public class Grafo {

    /*
     * REPRESENTACIO DEL GRAF:
     * Una entitat d'un tipus arbitrari te el mateix index a les matrius d'adjacencia (de fila si es Paper, de
     * columna altrament) que al seu vector corresponent. Sota aquesta implementacio, entre 2 i 4 nodes (entitats),
     * poden compartir index si son de tipus diferents. Les matrius d'adjacencia, per tant, no son simetriques, i
     * els nodes s'identifiquen pel seu index i tipus d'Entitat, o alternativament pel seu atribut ID.
     *
     * Les entitats s'emmagatzemen per ordre alfabetic creixent del nom de l'entitat, tant en el vector corresponent
     * com en les matrius en que hi figuri (a fi de mantenir l'invariant dels indexos compartits).
     *
     * El graf guarda la darrera ID emprada per assignar-ne la consecutiva a noves entitats.
     */


    //ATRIBUTS

    /*Vectors d'entitats*/
    private Vector<Paper>	    vectorPaper;
    private Vector<Autor>		vectorAutor;
    private Vector<Conferencia>	vectorConferencia;
    private Vector<Termino>		vectorTermino;

    /*Matrius d'adjacencia*/
    private SparseMatrix        matrizPaperAutor;
    private SparseMatrix	    matrizPaperConferencia;
    private SparseMatrix	    matrizPaperTermino;

    /*Darrera ID emprada*/
    private Integer lastId;


    //CONSTRUCTORA

    public Grafo() {
        vectorAutor 			= new Vector<Autor>();
        vectorConferencia		= new Vector<Conferencia>();
        vectorTermino 			= new Vector<Termino>();
        vectorPaper 			= new Vector<Paper>();

        matrizPaperAutor 		= new CCSMatrix();
        matrizPaperConferencia  = new CCSMatrix();
        matrizPaperTermino 		= new CCSMatrix();

        lastId = -1;
    }


    //METODES PRIVATS

    /*Pre: */
    /*Post: si existeix alguna entitat amb Nombre=nom al graf, retorna l'index corresponent a la seva primera
     *      aparicio, i repeticions es igual al nombre de vegades que hi apareix. Si no n'existeix cap retorna -1
     *      i pos conte la posicio que hauria d'ocupar */
    private int cercaDicotomica(String nom, String tipoEntidad, Integer repeticions, Integer pos) {
        int n = -1, l = 0, r, m = 0;
        boolean found = false;
        switch (tipoEntidad) {
            case "Paper":
                if (vectorPaper.isEmpty()) {pos = 0; return -1;}
                r = vectorPaper.size()-1;
                while (l <= r && !found) {
                    m = (l+r)/2;
                    if (nom.compareTo(vectorPaper.get(m).getNombre()) < 0) r = m - 1;
                    else if (nom.compareTo(vectorPaper.get(m).getNombre()) > 0) r = m + 1;
                    else {
                        found = true;
                        n = m;
                        repeticions = 1;
                        while (m < vectorPaper.size() && (vectorPaper.get(++m).getNombre()).equals(nom))
                            ++repeticions;
                        m = n;
                        while (m >= 0 && (vectorPaper.get(++m).getNombre()).equals(nom)) {
                            ++repeticions;
                            --n;
                        }
                    }
                }
                if (!found) {
                    pos = m;
                    if (pos < 0) pos = 0;
                    else if (pos >= vectorPaper.size()) pos = vectorPaper.size();
                }
                break;
            case "Autor":
                if (vectorAutor.isEmpty()) {pos = 0; return -1;}
                r = vectorAutor.size()-1;
                while (l <= r && !found) {
                    m = (l+r)/2;
                    if (nom.compareTo(vectorAutor.get(m).getNombre()) < 0) r = m - 1;
                    else if (nom.compareTo(vectorAutor.get(m).getNombre()) > 0) r = m + 1;
                    else {
                        found = true;
                        n = m;
                        repeticions = 1;
                        while (m < vectorAutor.size() && (vectorAutor.get(++m).getNombre()).equals(nom))
                            ++repeticions;
                        m = n;
                        while (m >= 0 && (vectorAutor.get(++m).getNombre()).equals(nom)) {
                            ++repeticions;
                            --n;
                        }
                    }
                }
                if (!found) {
                    pos = m;
                    if (pos < 0) pos = 0;
                    else if (pos >= vectorAutor.size()) pos = vectorAutor.size();
                }
            case "Conferencia":
                if (vectorConferencia.isEmpty()) {pos = 0; return -1;}
                r = vectorConferencia.size()-1;
                while (l <= r && !found) {
                    m = (l+r)/2;
                    if (nom.compareTo(vectorConferencia.get(m).getNombre()) < 0) r = m - 1;
                    else if (nom.compareTo(vectorConferencia.get(m).getNombre()) > 0) r = m + 1;
                    else {
                        found = true;
                        n = m;
                        repeticions = 1;
                        while (m < vectorConferencia.size() && (vectorConferencia.get(++m).getNombre()).equals(nom))
                            ++repeticions;
                        m = n;
                        while (m >= 0 && (vectorConferencia.get(++m).getNombre()).equals(nom)) {
                            ++repeticions;
                            --n;
                        }
                    }
                }
                if (!found) {
                    pos = m;
                    if (pos < 0) pos = 0;
                    else if (pos >= vectorConferencia.size()) pos = vectorConferencia.size();
                }
            case "Termino":
                if (vectorTermino.isEmpty()) {pos = 0; return -1;}
                r = vectorTermino.size()-1;
                while (l <= r && !found) {
                    m = (l+r)/2;
                    if (nom.compareTo(vectorTermino.get(m).getNombre()) < 0) r = m - 1;
                    else if (nom.compareTo(vectorTermino.get(m).getNombre()) > 0) r = m + 1;
                    else {
                        found = true;
                        n = m;
                        repeticions = 1;
                        while (m < vectorTermino.size() && (vectorTermino.get(++m).getNombre()).equals(nom))
                            ++repeticions;
                        m = n;
                        while (m >= 0 && (vectorTermino.get(++m).getNombre()).equals(nom)) {
                            ++repeticions;
                            --n;
                        }
                    }
                }
                if (!found) {
                    pos = m;
                    if (pos < 0) pos = 0;
                    else if (pos >= vectorTermino.size()) pos = vectorTermino.size();
                }
            default:
                System.out.println("Carallot! El tipus d'entitat ha de ser Paper, Autor, Conferencia o Termino");
                break;
        }
        return n;
    }


    //METODES PUBLICS (It's free!)

    /* redundant. necessaria? */
    public int getIndice(String nombre, String tipo) { return cercaDicotomica(nombre, tipo, null, null); }

    public void addEntidad(String nombre, String tipoEntidad) {
        Integer index = new Integer(0);
        cercaDicotomica(nombre, tipoEntidad, null, index);
        System.out.println("xivato: s'afegeix amb index " + index);
        org.la4j.Vector tmpVec1, tmpVec2, tmpVec3;
        switch (tipoEntidad) {
            case "Paper":
                Paper tmpPaper = new Paper(++lastId, nombre);
                vectorPaper.add(index, tmpPaper);
                tmpVec1 = new CompressedVector(vectorAutor.size());
                tmpVec2 = new CompressedVector(vectorConferencia.size());
                tmpVec3 = new CompressedVector(vectorTermino.size());
                matrizPaperAutor.insertRow(index, tmpVec1);
                matrizPaperConferencia.insertRow(index, tmpVec2);
                matrizPaperTermino.insertRow(index, tmpVec3);
                break;
            case "Autor":
                Autor tmpAutor = new Autor(++lastId, nombre);
                vectorAutor.add(index, tmpAutor);
                tmpVec1 = new CompressedVector(vectorPaper.size());
                matrizPaperAutor.insertColumn(index, tmpVec1);
                break;
            case "Conferencia":
                Conferencia tmpConferencia = new Conferencia(++lastId, nombre);
                vectorConferencia.add(index, tmpConferencia);
                tmpVec1 = new CompressedVector(vectorPaper.size());
                matrizPaperConferencia.insertColumn(index, tmpVec1);
                break;
            case "Termino":
                Termino tmpTermino = new Termino(++lastId, nombre);
                vectorTermino.add(index, tmpTermino);
                tmpVec1 = new CompressedVector(vectorPaper.size());
                matrizPaperTermino.insertColumn(index, tmpVec1);
                break;
            default:
                System.out.println("Tros d'ase! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public void deleteEntidad(String nombre, String tipoEntidad) {
        int i;
        switch (tipoEntidad) {
            case "Paper":
                i = getIndice(nombre, "Paper");
                vectorPaper.remove(i);
                matrizPaperAutor.removeRow(i);
                matrizPaperConferencia.removeRow(i);
                matrizPaperTermino.removeRow(i);
                if (lastId.equals(i)) lastId--;
                break;
            case "Autor":
                i = getIndice(nombre, "Autor");
                vectorAutor.remove(i);
                matrizPaperAutor.removeColumn(i);
                if (lastId.equals(i)) lastId--;
                break;
            case "Conferencia":
                i = getIndice(nombre, "Conferencia");
                vectorConferencia.remove(i);
                matrizPaperConferencia.removeColumn(i);
                if (lastId.equals(i)) lastId--;
                break;
            case "Termino":
                i = getIndice(nombre, "Termino");
                vectorTermino.remove(i);
                matrizPaperTermino.removeColumn(i);
                if (lastId.equals(i)) lastId--;
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
                index1 = cercaDicotomica(nombre1, "Paper", null, null);
                index2 = cercaDicotomica(nombre2, "Autor", null, null);
                matrizPaperAutor.set(index1, index2, 1);
                break;
            case "CP":
                index1 = cercaDicotomica(nombre1, "Paper", null, null);
                index2 = cercaDicotomica(nombre2, "Conferencia", null, null);
                matrizPaperConferencia.set(index1, index2, 1);
                break;
            case "TP":
                index1 = cercaDicotomica(nombre1, "Paper", null, null);
                index2 = cercaDicotomica(nombre2, "Termino", null, null);
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
                index1 = getIndice(nombreOrigen, "Paper");
                index2 = getIndice(nombreDesti, "Autor");
                matrizPaperAutor.set(index1, index2, 0);
                break;
            case "CP":
                index1 = getIndice(nombreOrigen, "Paper");
                index2 = getIndice(nombreDesti, "Conferencia");
                matrizPaperConferencia.set(index1, index2, 0);
                break;
            case "TP":
                index1 = getIndice(nombreOrigen, "Paper");
                index2 = getIndice(nombreDesti, "Termino");
                matrizPaperTermino.set(index1, index2, 0);
                break;
            default:
                System.out.println("Talós! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public Vector<Entidad> getRelacion(String nombre, String tipoEntidad) {
        Vector<Entidad> vR = new Vector<Entidad>();
        int i, j, n;
        switch (tipoEntidad) {
            case "Paper":
                i = getIndice(nombre, "Paper");
                //autors:
                n = vectorAutor.size();
                for (j = 0; j < n; ++j)
                    if (!matrizPaperAutor.isZeroAt(i, j)) vR.addElement(vectorAutor.elementAt(j));
                //conferencies:
                n = vectorConferencia.size();
                for (j = 0; j < n; ++j)
                    if (!matrizPaperConferencia.isZeroAt(i, j)) vR.addElement(vectorConferencia.elementAt(j));
                //termes:
                n = vectorTermino.size();
                for (j = 0; j < n; ++j)
                    if (!matrizPaperTermino.isZeroAt(i, j)) vR.addElement(vectorTermino.elementAt(j));
                return vR;
            case "Autor":
                j = getIndice(nombre, "Autor");
                n = vectorPaper.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperAutor.isZeroAt(i, j)) vR.addElement(vectorPaper.elementAt(i));
                return vR;
            case "Conferencia":
                j = getIndice(nombre, "Conferencia");
                n = vectorPaper.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperConferencia.isZeroAt(i, j)) vR.addElement(vectorConferencia.elementAt(i));
                return vR;
            case "Termino":
                j = getIndice(nombre, "Termino");
                n = vectorPaper.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperTermino.isZeroAt(i, j)) vR.addElement(vectorTermino.elementAt(i));
                return vR;
            default:
                System.out.println("Ruc! Cal triar un tipus d'entitat valid");
                return vR;
        }
    }

    public Entidad getEntidad(String nombre, String tipoEntidad) {
        Integer pos = cercaDicotomica(nombre, tipoEntidad, null, null);
        if (!pos.equals(-1)) {
            switch (tipoEntidad) {
                case "Paper":
                    return vectorPaper.get(pos);
                case "Autor":
                    return vectorAutor.get(pos);
                case "Conferencia":
                    return vectorConferencia.get(pos);
                case "Termino":
                    return vectorTermino.get(pos);
                default:
                    System.out.println("Tanoca! Cal triar un tipus d'entitat valid");
                    return null;
            }
        }
        return null;
    }

    public SparseMatrix getMatriz(String tipoColumna) {
        switch (tipoColumna) {
            case "Autor":
                return matrizPaperAutor;
            case "Conferencia":
                return matrizPaperConferencia;
            case "Termino":
                return matrizPaperTermino;
            default:
                System.out.println("Talós! El tipus de columna ha de ser Autor, Conferencia o Termino");
                return null;
        }
    }

    /*  parides que m'ha demanat l'Albert:    */
    public boolean exists(String nom, String tipoEntidad) { return (cercaDicotomica(nom, tipoEntidad, null, null) != -1); }

    public Integer getId(String nom, String tipusEntitat) {
        Integer i = cercaDicotomica(nom, tipusEntitat, null, null);
        if (!i.equals(-1)) {
            switch (tipusEntitat) {
                case "Paper":
                    return vectorPaper.get(i).getId();
                case "Autor":
                    return vectorAutor.get(i).getId();
                case "Conferencia":
                    return vectorConferencia.get(i).getId();
                case "Termino":
                    return vectorTermino.get(i).getId();
            }
        }
        return null;
    }

    /*  xorrades que vol l'Alvar:   */
    public Vector<Paper> getPapers() { return vectorPaper; }

    public Vector<Autor> getAutors() { return vectorAutor; }

    public Vector<Conferencia> getConferencias() { return vectorConferencia; }

    public Vector<Termino> getTerminos() { return vectorTermino; }

    public void addPaper(Paper a) {
        Integer i = new Integer(0);
        cercaDicotomica(a.getNombre(), "Paper", null, i);
        //matrius
        org.la4j.Vector tmpVec1, tmpVec2, tmpVec3;
        tmpVec1 = new CompressedVector(vectorAutor.size());
        tmpVec2 = new CompressedVector(vectorConferencia.size());
        tmpVec3 = new CompressedVector(vectorTermino.size());
        matrizPaperAutor.insertRow(i, tmpVec1);
        matrizPaperConferencia.insertRow(i, tmpVec2);
        matrizPaperTermino.insertRow(i, tmpVec3);
        //vector
        vectorPaper.add(i, a);
        //lastId
        if (lastId < a.getId()) lastId = a.getId();
    }

    public void addAutor(Autor a) {
        Integer i = new Integer(0);
        cercaDicotomica(a.getNombre(), "Paper", null, i);
        //matrius
        org.la4j.Vector tmpVec;
        tmpVec = new CompressedVector(vectorPaper.size());
        matrizPaperAutor.insertColumn(i, tmpVec);
        //vector
        vectorAutor.add(i, a);
        //lastId
        if (lastId < a.getId()) lastId = a.getId();
    }

    public void addConferencia(Conferencia c) {
        Integer i = new Integer(0);
        cercaDicotomica(c.getNombre(), "Paper", null, i);
        //matrius
        org.la4j.Vector tmpVec;
        tmpVec = new CompressedVector(vectorPaper.size());
        matrizPaperConferencia.insertColumn(i, tmpVec);
        //vector
        vectorConferencia.add(i, c);
        //lastId
        if (lastId < c.getId()) lastId = c.getId();
    }

    public void addTermino(Termino t) {
        Integer i = new Integer(0);
        cercaDicotomica(t.getNombre(), "Paper", null, i);
        //matrius
        org.la4j.Vector tmpVec;
        tmpVec = new CompressedVector(vectorPaper.size());
        matrizPaperTermino.insertColumn(i, tmpVec);
        //vector
        vectorTermino.add(i,t);
        //lastId
        if (lastId < t.getId()) lastId = t.getId();
    }
}
