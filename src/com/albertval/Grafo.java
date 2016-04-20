import org.la4j.matrix.SparseMatrix;
import org.la4j.matrix.sparse.CCSMatrix;
import org.la4j.vector.sparse.CompressedVector;

import java.util.TreeSet;
import java.util.Vector;



public class Grafo {

    /*
     *
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
     * Tambe es guarden les ID entre 0 i la darrera ID emprada, amb la mateixa finalitat, i, a mes a mes, per
     * poder comprovar si una ID s'esta fent servir per prevenir, per exemple, insercions sense sentit.
     *
     */

    //NOTA PER AL MARC DEL FUTUR:
    //Podries implementar una cua per guardar les IDs d'entitats eliminades, podent-les aprofitar en noves entitats

    //ATRIBUTS

    /* Vectors d'entitats */
    //REVISAR: L'estructura de dades "TreeSet" seria mes eficient que aquesta merda de vectors cutres
    private Vector<Articulo>	vectorArticulo;
    private Vector<Autor>	    vectorAutor;
    private Vector<Conferencia>	vectorConferencia;
    private Vector<Termino>	    vectorTermino;

    /* Matrius d'adjacencia */
    private SparseMatrix    matrizPaperAutor;
    private SparseMatrix	matrizPaperConferencia;
    private SparseMatrix	matrizPaperTermino;

    /* Informacio per assignar noves ID */
    private TreeSet<Integer>  IDorfenes;
    private Integer lastId;


    //CONSTRUCTORA
    public Grafo() {
        vectorAutor 			= new Vector<Autor>();
        vectorConferencia		= new Vector<Conferencia>();
        vectorTermino 			= new Vector<Termino>();
        vectorArticulo 			= new Vector<Articulo>();

        matrizPaperAutor 		= new CCSMatrix();
        matrizPaperConferencia  = new CCSMatrix();
        matrizPaperTermino 		= new CCSMatrix();

        IDorfenes = new TreeSet<Integer>();
        lastId = -1;
    }


    //METODES PRIVATS

    /*Pre: */
    /*Post: si existeix alguna entitat amb Nombre=nom al graf, retorna l'index corresponent a la seva primera
     *      aparicio, i repeticions es igual al nombre d'aparicions. Si no n'existeix cap retorna -1 */
    //NOTA PER AL MARC DEL FUTUR:
    //Si no es troba, la suggerencia "opcional" s'hauria de calcular aqui
    private int cercaDicotomica(String nom, String tipoEntidad, Integer repeticions) {
        int n = -1, rep = 0, l = 0, r, m;
        switch (tipoEntidad) {
            case "Articulo":
                r = vectorArticulo.size();
                m = (l+r)/2;
                //for (int i = 0; i < )
                break;
            case "Autor":
                break;
            case "Conferencia":
                break;
            case "Termino":
                break;
            default:
                System.out.println("Carallot! El tipus d'entitat ha de ser Articulo, Autor, Conferencia o Termino");
                break;
        }
        return n;
    }

    private boolean existsID(Integer id) {
        if (id > lastId) return false;
        return IDorfenes.contains(id);
    }

    //METODES PUBLICS (It's free!)

    /*Aquest mètode podria ser privat (el controlador del graf no l'hauria de necessitar)*/
    public int getIndice(String nombre, String tipo) {
        int n;
        switch (tipo) {
            case "Articulo":
                n = vectorArticulo.size();
                //substituir per dicotomica
                for (int i = 0; i < n; ++n) if (vectorArticulo.get(i).getNombre().equals(nombre)) return i;
            case "Autor":
                n = vectorAutor.size();
                //substituir per dicotomica
                for (int i = 0; i < n; ++n) if (vectorAutor.get(i).getNombre().equals(nombre)) return i;
            case "Conferencia":
                n = vectorConferencia.size();
                //substituir per dicotomica
                for (int i = 0; i < n; ++n) if (vectorConferencia.get(i).getNombre().equals(nombre)) return i;
            case "Termino":
                n = vectorTermino.size();
                //substituir per dicotomica
                for (int i = 0; i < n; ++n) if (vectorTermino.get(i).getNombre().equals(nombre)) return i;
            default:
                System.out.println("Capsigrany! El tipus d'entitat ha de ser Articulo, Autor, Conferencia o Termino");
                return -1;
        }
    }

    public void addEntidad(String nombre, String tipoEntidad) {
        Integer index = getIndice(nombre, tipoEntidad);
        org.la4j.Vector tmpVec1, tmpVec2, tmpVec3;
        switch (tipoEntidad) {
            case "Articulo":
                Articulo tmpArticulo = new Articulo(++lastId, nombre);
                vectorArticulo.add(index, tmpArticulo);
                tmpVec1 = new CompressedVector(vectorAutor.size());
                matrizPaperAutor.insertRow(vectorArticulo.size()-1, tmpVec1);
                tmpVec2 = new CompressedVector(vectorConferencia.size());
                matrizPaperConferencia.insertRow(vectorArticulo.size()-1, tmpVec2);
                tmpVec3 = new CompressedVector(vectorArticulo.size());
                matrizPaperTermino.insertRow(vectorArticulo.size()-1, tmpVec3);
                break;
            case "Autor":
                Autor tmpAutor = new Autor(++lastId, nombre);
                vectorAutor.add(index, tmpAutor);
                tmpVec1 = new CompressedVector();
                matrizPaperAutor.insertColumn(vectorAutor.size()-1, tmpVec1);
                break;
            case "Conferencia":
                Conferencia tmpConferencia = new Conferencia(++lastId, nombre);
                vectorConferencia.add(index, tmpConferencia);
                tmpVec1 = new CompressedVector();
                matrizPaperConferencia.insertColumn(vectorAutor.size()-1, tmpVec1);
                break;
            case "Termino":
                Termino tmpTermino = new Termino(++lastId, nombre);
                vectorTermino.add(index, tmpTermino);
                tmpVec1 = new CompressedVector();
                matrizPaperTermino.insertColumn(vectorAutor.size()-1, tmpVec1);
                break;
            default:
                System.out.println("Tros d'ase! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    public void deleteEntidad(String nombre, String tipoEntidad) {
        int i;
        switch (tipoEntidad) {
            case "Articulo":
                i = getIndice(nombre, "Articulo");
                vectorArticulo.remove(i);
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
                index1 = getIndice(nombreOrigen, "Articulo");
                index2 = getIndice(nombreDesti, "Autor");
                matrizPaperAutor.set(index1, index2, 0);
                break;
            case "CP":
                index1 = getIndice(nombreOrigen, "Articulo");
                index2 = getIndice(nombreDesti, "Conferencia");
                matrizPaperConferencia.set(index1, index2, 0);
                break;
            case "TP":
                index1 = getIndice(nombreOrigen, "Articulo");
                index2 = getIndice(nombreDesti, "Termino");
                matrizPaperTermino.set(index1, index2, 0);
                break;
            default:
                System.out.println("Talós! Cal triar un tipus d'entitat valid");
                break;
        }
    }

    //Aquest metode s'ha d'arreglar
    public Vector<Entidad> getRelacion(String nombre, String tipoEntidad) {
        Vector<Entidad> vR = new Vector<Entidad>();
        int i, j, n;
        switch (tipoEntidad) {
            case "Articulo":
                i = getIndice(nombre, "Articulo");
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
                n = vectorArticulo.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperAutor.isZeroAt(i, j)) vR.addElement(vectorArticulo.elementAt(i));
                return vR;
            case "Conferencia":
                j = getIndice(nombre, "Conferencia");
                n = vectorArticulo.size();
                for (i = 0; i < n; ++i)
                    if (!matrizPaperConferencia.isZeroAt(i, j)) vR.addElement(vectorConferencia.elementAt(i));
                return vR;
            case "Termino":
                j = getIndice(nombre, "Termino");
                n = vectorArticulo.size();
                for (i = 0; i < n; ++i)
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
                return null;
        }
    }

    public SparseMatrix getMatriz(String tipoFila, String tipoColumna) {
        if (!tipoFila.equals("Articulo"))/*atribut innecessari...?*/{
            System.out.println("Gamarús! El tipus de fila de totes les matrius és 'Paper'");
            return null;
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
                return null;
        }
    }

    /*  parides que m'ha demanat l'Albert:    */
    public boolean existsNom(String nom, String tipoEntidad) {
        boolean b = false;
        switch (tipoEntidad) {
            case "Articulo":
                //cerca
                return b;
            case "Autor":
                //cerca
                return b;
            case "Conferencia":
                //cerca
                return b;
            case "Termino":
                //cerca
                return b;
            default:
                System.out.println("Espavila! El tipus d'entitat no es correcte");
                return false;
        }
    }

    public String getNombre(Integer id, String tipusEntitat) {
        //
        return null;
    }

    /*  xorrades que vol l'Alvar:   */
    public Vector<Articulo> getArticulos() { return vectorArticulo; }

    public Vector<Autor> getAutors() { return vectorAutor; }

    public Vector<Conferencia> getConferencias() { return vectorConferencia; }

    public Vector<Termino> getTerminos() { return vectorTermino; }

    //NOTA PER AL MARC DEL FUTUR:
    //Dir-li a l'Alvar que no es pot fer aquest metode
    //perque hi ha conflicte amb l'herencia de classes
    //es necessita una funcio per cada tipus d'Entitat
    public void addEntidadFULL(Entidad e, String tipoEntidad) {
        //Cal comprovar que la ID de l'Entitat e no es repeteixi en el graf
        switch (tipoEntidad) {
            case "Articulo":
                vectorArticulo.addElement(e);
                if (e.getId() > lastId) {
                    //afegir intermitjos a la cua (opcional)
                    lastId = e.getId();
                }
                break;
            case "Autor":
                vectorAutor.addElement(e);
                if (e.getId() > lastId) {
                    //afegir intermitjos a la cua (opcional)
                    lastId = e.getId();
                }
                break;
            case "Conferencia":
                vectorConferencia.addElement(e);
                if (e.getId() > lastId) {
                    //afegir intermitjos a la cua (opcional)
                    lastId = e.getId();
                }
                break;
            case "Termino":
                vectorTermino.addElement(e);
                if (e.getId() > lastId) {
                    //afegir intermitjos a la cua (opcional)
                    lastId = e.getId();
                }
                break;
            default:
                System.out.println("Carallot! El tipus d'entitat ha de ser Articulo, Autor, Conferencia o Termino");
                break;
        }
    }
}
