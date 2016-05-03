import org.la4j.matrix.SparseMatrix;
import org.la4j.matrix.sparse.CCSMatrix;

import java.util.Vector;
import java.util.HashMap;



public class Grafo {

    /*
     * REPRESENTACIO DEL GRAF:
     * Les entitats (nodes) es guarden en els vectors del seu tipus corresponent
     * Les relacions directes ({"PA", "PC", "PT"} i les simetriques) entre entitats de tipus diferent
     * es representen a la matriu d'adjacencia corresponent, on la fila i correspon a l'index del Paper i,
     * i la columna j correspon a Autor/Conferencia/Terme (segons la matriu). L'index d'una entitat es
     * el mateix a la matriu i al seu vector.
     * El sistema guarda la darrera ID emprada per a poder assignar la immediatament major si l'usuari
     * introdueix entitats sense assignar-les-hi una ID
     * Sota aquesta representacio, els noms de les entitats son unics (fins i tot entre tipus diferents), les ID
     * poden ser compartides entre 2-4 entitats sempre i quan aquestes siguin de tipus diferent.
     */


    //ATRIBUTS

    /*Vectors d'entitats*/
    private Vector<stubPaper>	    vectorPaper;
    private Vector<stubAutor>		vectorAutor;
    private Vector<stubConferencia>	vectorConferencia;
    private Vector<Termino>		    vectorTermino;

    /*Matrius d'adjacencia*/
    private SparseMatrix        matrizPaperAutor;
    private SparseMatrix	    matrizPaperConferencia;
    private SparseMatrix	    matrizPaperTermino;

    /*Darrera ID emprada*/
    private Integer lastId;

    /*Diccionari*/
    private HashMap<Integer, Integer>   idPIndex;
    private HashMap<Integer, Integer>   idAIndex;
    private HashMap<Integer, Integer>   idCIndex;
    private HashMap<Integer, Integer>   idTIndex;
    //private HashMap<String, Integer>    nomIndex;


    //CONSTRUCTORA

    public Grafo() {
        vectorAutor 			= new Vector<stubAutor>();
        vectorConferencia		= new Vector<stubConferencia>();
        vectorTermino 			= new Vector<Termino>();
        vectorPaper 			= new Vector<stubPaper>();

        matrizPaperAutor 		= new CCSMatrix(20000, 20000);
        matrizPaperConferencia  = new CCSMatrix(20000, 20000);
        matrizPaperTermino 		= new CCSMatrix(20000, 20000);

        idPIndex                = new HashMap<Integer, Integer>();
        idAIndex                = new HashMap<Integer, Integer>();
        idCIndex                = new HashMap<Integer, Integer>();
        idTIndex                = new HashMap<Integer, Integer>();
        nomIndex                = new HashMap<String, Integer>();

        lastId = -1;
    }


    //METODES PRIVATS

    /*----*/

    //METODES PUBLICS

    public boolean exists(String nom, Integer id, String tipus) {
        if (id != null) {
            switch (tipus) {
                case "Paper":
                    return idPIndex.containsKey(id);
                case "Autor":
                    return idAIndex.containsKey(id);
                case "Conferencia":
                    return idCIndex.containsKey(id);
                case "Termino":
                    return idTIndex.containsKey(id);
                default:
                    System.out.println("tipus incorrecte");
                    return false;
            }
        }
        return nomIndex.containsKey(nom);
    }

    public String getNombre_i(Integer index, String tipus) {
        switch (tipus) {
            case "Paper":
                return vectorPaper.get(index).getNombre();
            case "stubAutor":
                return vectorAutor.get(index).getNombre();
            case "Conferencia":
                return vectorConferencia.get(index).getNombre();
            case "Termino":
                return vectorTermino.get(index).getNombre();
            default:
                return null;
        }
    }

    public String getNombre_ID(Integer id, String tipus) {
        Integer index;
        switch (tipus) {
            case "Paper":
                if ((index = idPIndex.get(id)) == null) return null;
                return vectorPaper.get(index).getNombre();
            case "Autor":
                if ((index = idAIndex.get(id)) == null) return null;
                return vectorAutor.get(index).getNombre();
            case "Conferencia":
                if ((index = idCIndex.get(id)) == null) return null;
                return vectorConferencia.get(index).getNombre();
            case "Termino":
                if ((index = idTIndex.get(id)) == null) return null;
                return vectorTermino.get(index).getNombre();
            default:
                System.out.println("error en el tipus");
                return null;
        }
    }

    //retorna les ids de les entitats de tipus=tipus amb Nombre=nom
    public Vector<Integer> getID(String nom, String tipus) {
        int n;
        Vector<Integer> v = new Vector<Integer>();
        switch (tipus) {
            case "Paper":
                n = vectorPaper.size();
                for (int i = 0; i < n; i++) {
                    if (vectorPaper.get(i).getNombre().equals(nom))
                        v.add(vectorPaper.get(i).getID());
                }
            case "Autor":
                n = vectorAutor.size();
                for (int i = 0; i < n; i++) {
                    if (vectorAutor.get(i).getNombre().equals(nom))
                        v.add(vectorAutor.get(i).getID());
                }
            case "Conferencia":
                n = vectorConferencia.size();
                for (int i = 0; i < n; i++) {
                    if (vectorConferencia.get(i).getNombre().equals(nom))
                        v.add(vectorConferencia.get(i).getID());
                }
            case "Termino":
                n = vectorTermino.size();
                for (int i = 0; i < n; i++) {
                    if (vectorTermino.get(i).getNombre().equals(nom))
                        v.add(vectorTermino.get(i).getID());
                }
            default:
                System.out.println("error en el tipus");
        }
        return v;
    }

    public Integer getIndice(String nom) { return nomIndex.get(nom); }

    public boolean addEntidad(String nombre, Integer id, String tag, String tipoEntidad) {
        if (exists(nombre, id, tipoEntidad)) return false;
        Integer index;
        switch (tipoEntidad) {
            case "Paper":
                index = vectorPaper.size();
                if (id == null) id = ++lastId;
                stubPaper tmpStubPaper = new stubPaper(id, nombre, tag);
                idPIndex.put(id, vectorPaper.size());
                nomIndex.put(nombre, vectorPaper.size());
                vectorPaper.add(index, tmpStubPaper);
                return true;
            case "Autor":
                index = vectorAutor.size();
                if (id == null) id = ++lastId;
                stubAutor tmpStubAutor = new stubAutor(id, nombre, tag);
                idAIndex.put(id, vectorAutor.size());
                nomIndex.put(nombre, vectorAutor.size());
                vectorAutor.add(index, tmpStubAutor);
                return true;
            case "Conferencia":
                index = vectorConferencia.size();
                if (id == null) id = ++lastId;
                stubConferencia tmpConf = new stubConferencia(id, nombre, tag);
                idCIndex.put(id, vectorConferencia.size());
                nomIndex.put(nombre, vectorConferencia.size());
                vectorConferencia.add(index, tmpConf);
                return true;
            case "Termino":
                index = vectorTermino.size();
                if (id == null) id = ++lastId;
                Termino tmpTerm = new Termino(id, nombre);
                idTIndex.put(id, vectorTermino.size());
                nomIndex.put(nombre, vectorTermino.size());
                vectorTermino.add(index, tmpTerm);
                return true;
            default:
                System.out.println("tipus incorrecte");
                return false;
        }
    }

    public boolean deleteEntidad(String nombre, Integer id, String tipoEntidad) {
        Integer i = getIndice(nombre, tipoEntidad);
        org.la4j.Vector tmpVec;
        if (i == null) return false;
        switch (tipoEntidad) {
            case "Paper":
                if (id == null) id = vectorPaper.get(i).getId();
                idPIndex.remove(id);
                if (nombre == null) nombre = vectorPaper.get(i).getNombre();
                nomIndex.put(nombre, null);
                vectorPaper.set(i, null);
                matrizPaperAutor.setRow(i, 0);
                matrizPaperConferencia.setRow(i, 0);
                matrizPaperTermino.setRow(i, 0);
                if (lastId.equals(i)) lastId--;
                return true;
            case "Autor":
                if (id == null) id = vectorAutor.get(i).getId();
                idAIndex.remove(id);
                if (nombre == null) nombre = vectorAutor.get(i).getNombre();
                nomIndex.put(nombre, null);
                vectorAutor.set(i, null);
                matrizPaperAutor.setColumn(i, 0);
                if (lastId.equals(i)) lastId--;
                return true;
            case "Conferencia":
                if (id == null) id = vectorConferencia.get(i).getId();
                idCIndex.put(id, null);
                if (nombre == null) nombre = vectorConferencia.get(i).getNombre();
                nomIndex.put(nombre, null);
                vectorConferencia.set(i, null);
                matrizPaperConferencia.setColumn(i, 0);
                if (lastId.equals(i)) lastId--;
                return true;
            case "Termino":
                if (id == null) id = vectorTermino.get(i).getId();
                idTIndex.remove(id);
                if (nombre == null) nombre = vectorTermino.get(i).getNombre();
                nomIndex.remove(nombre);
                vectorTermino.remove(i);
                matrizPaperTermino.setColumn(i, 0);
                if (lastId.equals(i)) lastId--;
                return true;
            default:
                return false;
        }
    }

    public boolean addRelacion(Integer id1, Integer id2, String tipoRelacion) {
        Integer index1, index2;
        switch (tipoRelacion) {
            case "AP":
                index1 = idAIndex.get(id1);
                index2 = idPIndex.get(id2);
                matrizPaperAutor.set(index2, index1, 1);
                return true;
            case "PA":
                index1 = idPIndex.get(id1);
                index2 = idAIndex.get(id2);
                matrizPaperAutor.set(index1, index2, 1);
                return true;
            case "CP":
                index1 = idCIndex.get(id1);
                index2 = idPIndex.get(id2);
                matrizPaperConferencia.set(index2, index1, 1);
                return true;
            case "PC":
                index1 = idPIndex.get(id1);
                index2 = idCIndex.get(id2);
                matrizPaperConferencia.set(index1, index2, 1);
                return true;
            case "TP":
                index1 = idTIndex.get(id1);
                index2 = idPIndex.get(id2);
                matrizPaperTermino.set(index2, index1, 1);
                return true;
            case "PT":
                index1 = idPIndex.get(id1);
                index2 = idTIndex.get(id2);
                matrizPaperConferencia.set(index1, index2, 1);
                return true;
            default:
                System.out.println("tipus incorrecte");
                return false;
        }
    }

    public boolean deleteRelacion(Integer id1, Integer id2, String tipoRelacion) {
        Integer index1, index2;
        switch (tipoRelacion) {
            case "AP":
                index1 = idAIndex.get(id1);
                index2 = idPIndex.get(id2);
                matrizPaperAutor.set(index2, index1, 0);
                return true;
            case "PA":
                index1 = idPIndex.get(id1);
                index2 = idAIndex.get(id2);
                matrizPaperAutor.set(index1, index2, 0);
                return true;
            case "CP":
                index1 = idCIndex.get(id1);
                index2 = idPIndex.get(id2);
                matrizPaperConferencia.set(index2, index1, 0);
                return true;
            case "PC":
                index1 = idPIndex.get(id1);
                index2 = idCIndex.get(id2);
                matrizPaperConferencia.set(index1, index2, 0);
                return true;
            case "TP":
                index1 = idTIndex.get(id1);
                index2 = idPIndex.get(id2);
                matrizPaperTermino.set(index2, index1, 0);
                return true;
            case "PT":
                index1 = idPIndex.get(id1);
                index2 = idTIndex.get(id2);
                matrizPaperConferencia.set(index1, index2, 0);
                return true;
            default:
                System.out.println("tipus incorrecte");
                return false;
        }

    }

    public boolean setID(String nom, String tipus, Integer newID) {
        Integer i, oldID;
        if ((i = nomIndex.get(nom)) == null) return false;
        if (exists(null, newID, tipus)) return false;
        switch (tipus) {
            case "Paper":
                oldID = vectorPaper.get(i).getId();
                idPIndex.remove(oldID);
                idPIndex.put(newID, i);
                if (newID > lastId) lastId = newID;
                vectorPaper.get(i).setId(newID);
                return true;
            case "Autor":
                oldID = vectorAutor.get(i).getId();
                idAIndex.remove(oldID);
                idAIndex.put(newID, i);
                if (newID > lastId) lastId = newID;
                vectorAutor.get(i).setId(newID);
                return true;
            case "Conferencia":
                oldID = vectorConferencia.get(i).getId();
                idCIndex.remove(oldID);
                idCIndex.put(newID, i);
                if (newID > lastId) lastId = newID;
                vectorConferencia.get(i).setId(newID);
                return true;
            case "Termino":
                oldID = vectorTermino.get(i).getId();
                idTIndex.remove(oldID);
                idTIndex.put(newID, i);
                if (newID > lastId) lastId = newID;
                vectorTermino.get(i).setId(newID);
                return true;
            default:
                System.out.println("error en el tipus");
                return false;
        }
    }

    public boolean setNom(String oldNom, String tipus, String newNom) {
        if (oldNom.equals(newNom)) return false;
        Integer i;
        if ((i = nomIndex.get(oldNom)) == null) return false;
        if (exists(newNom, null, tipus)) return false;
        switch (tipus) {
            case "Paper":
                nomIndex.remove(oldNom);
                nomIndex.put(newNom, i);
                vectorPaper.get(i).setNombre(newNom);
                return true;
            case "Autor":
                nomIndex.remove(oldNom);
                nomIndex.put(newNom, i);
                vectorAutor.get(i).setNombre(newNom);
                return true;
            case "Conferencia":
                nomIndex.remove(oldNom);
                nomIndex.put(newNom, i);
                vectorConferencia.get(i).setNombre(newNom);
                return true;
            case "Termino":
                nomIndex.remove(oldNom);
                nomIndex.put(newNom, i);
                vectorTermino.get(i).setNombre(newNom);
                return true;
            default:
                System.out.println("error en el tipus");
                return false;
        }
    }

    //Pre: tipus paper/conferencia/autor
    public boolean setTag(String nom, String tipus, String newTag) {
        Integer i;
        if ((i= nomIndex.get(nom)) == null) return false;
        switch (tipus) {
            case "Paper":
                vectorPaper.get(i).setEtiqueta(newTag);
                return true;
            case "Autor":
                vectorAutor.get(i).setEtiqueta(newTag);
                return true;
            case "Conferencia":
                vectorConferencia.get(i).setEtiqueta(newTag);
                return true;
            default:
                System.out.println("error en el tipus");
                return false;
        }
    }

    public Vector<stubEntidad> getRelacion(Integer id, String tipoEntidad) {
        Vector<stubEntidad> vR = new Vector<stubEntidad>();
        int i, j, n;
        switch (tipoEntidad) {
            case "Paper":
                i = idPIndex.get(id);
                n = vectorAutor.size();
                for (j = 0; j < n; ++j)
                    if (!matrizPaperAutor.isZeroAt(i, j)) vR.addElement(vectorAutor.elementAt(j));
                n = vectorConferencia.size();
                for (j = 0; j < n; ++j)
                    if (!matrizPaperConferencia.isZeroAt(i, j)) vR.addElement(vectorConferencia.elementAt(j));
                n = vectorTermino.size();
                for (j = 0; j < n; ++j)
                    if (!matrizPaperTermino.isZeroAt(i, j)) vR.addElement(vectorTermino.elementAt(j));
                return vR;
            case "Autor":
                j = idAIndex.get(nombre);
                n = vectorPaper.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperAutor.isZeroAt(i, j)) vR.addElement(vectorPaper.elementAt(i));
                return vR;
            case "Conferencia":
                j = idCIndex.get(nombre);
                n = vectorPaper.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperConferencia.isZeroAt(i, j)) vR.addElement(vectorConferencia.elementAt(i));
                return vR;
            case "Termino":
                j = idTIndex.get(nombre);
                n = vectorPaper.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperTermino.isZeroAt(i, j)) vR.addElement(vectorTermino.elementAt(i));
                return vR;
            default:
                System.out.println("tipus incorrecte");
                return null;
        }
    }

    public stubEntidad getEntidad(String nombre, Integer id, String tipoEntidad) {
        Integer i = getIndice(nombre, tipoEntidad);
        if (i != null) {
            switch (tipoEntidad) {
                case "Paper":
                    return vectorPaper.get(i);
                case "Autor":
                    return vectorAutor.get(i);
                case "Conferencia":
                    return vectorConferencia.get(i);
                case "Termino":
                    return vectorTermino.get(i);
                default:
                    System.out.println("tipus incorrecte");
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
                System.out.println("tipus incorrecte");
                return null;
        }
    }

    public boolean addPaper(stubPaper p) {
        if (exists(p.getNombre(), p.getId(), "stubPaper")) return false;
        Integer i = vectorPaper.size();
        idPIndex.put(p.getId(), i);
        nomIndex.put(p.getNombre(), i);
        vectorPaper.add(i, p);
        if (lastId < p.getId()) lastId = p.getId();
        return true;
    }

    public boolean addAutor(stubAutor a) {
        if (exists(a.getNombre(), a.getId(), "stubAutor")) return false;
        Integer i = vectorAutor.size();
        idAIndex.put(a.getId(), i);
        nomIndex.put(a.getNombre(), i);
        vectorAutor.add(i, a);
        if (lastId < a.getId()) lastId = a.getId();
        return true;
    }

    public boolean addConferencia(stubConferencia c) {
        if (exists(c.getNombre(), c.getId(), "stubConferencia")) return false;
        Integer i = vectorConferencia.size();
        idCIndex.put(c.getId(), i);
        nomIndex.put(c.getNombre(), i);
        vectorConferencia.add(i, c);
        if (lastId < c.getId()) lastId = c.getId();
        return true;
    }

    public boolean addTermino(Termino t) {
        if (exists(t.getNombre(), t.getId(), "Termino")) return false;
        Integer i = vectorTermino.size();
        idTIndex.put(t.getId(), i);
        nomIndex.put(t.getNombre(), i);
        vectorTermino.add(i, t);
        if (lastId < t.getId()) lastId = t.getId();
        return true;
    }

    public Vector<stubPaper> getPapers() { return vectorPaper; }

    public Vector<stubAutor> getAutors() { return vectorAutor; }

    public Vector<stubConferencia> getConferencias() { return vectorConferencia; }

    public Vector<Termino> getTerminos() { return vectorTermino; }

    public Integer getLastID() { return lastId; }
}