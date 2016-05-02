package prop.alvarHernandez;

import java.io.IOException;
import java.util.Vector;
import prop.domini.*;

public class ControladorGrafo {

    //ATRIBUTOS:

    private Grafo g;
    private ControladorGrafoPersist cgp;

    //METODOS PRIVADOS:

    private void cargarPapers() throws IOException {
        Vector<Vector<String>> mP = cgp.getPapers();
        for (int i = 0; i < mP.size(); ++i) {
            Integer ValorId = Integer.parseInt(mP.get(i).get(0));
            String ValorNombre = mP.get(i).get(1);
            Paper p = new Paper(ValorId, ValorNombre);
            if(mP.get(i).size() == 3) {
                String ValorEtiqueta = mP.get(i).get(2);
                p.setEtiqueta(ValorEtiqueta);
            }
            g.addPaper(p);
        }
    }

    private void cargarAutores() throws IOException {
        Vector<Vector<String>> mA = cgp.getAutores();
        for (int i = 0; i < mA.size(); ++i) {
            Integer ValorId = Integer.parseInt(mA.get(i).get(0));
            String ValorNombre = mA.get(i).get(1);
            Autor a = new Autor(ValorId, ValorNombre);
            if(mA.get(i).size() == 3) {
                String ValorEtiqueta = mA.get(i).get(2);
                a.setEtiqueta(ValorEtiqueta);
            }
            g.addAutor(a);
        }
    }

    private void cargarConferencias() throws IOException {
        Vector<Vector<String>> mC = cgp.getConferencias();
        for (int i = 0; i < mC.size(); ++i) {
            Integer ValorId = Integer.parseInt(mC.get(i).get(0));
            String ValorNombre = mC.get(i).get(1);
            Conferencia c = new Conferencia(ValorId, ValorNombre);
            if(mC.get(i).size() == 3) {
                String ValorEtiqueta = mC.get(i).get(2);
                c.setEtiqueta(ValorEtiqueta);
            }
            g.addConferencia(c);
        }
    }

    private void cargarTerminos() throws IOException {
        Vector<Vector<String>> mT = cgp.getTerminos();
        for (int i = 0; i < mT.size(); ++i) {
            Integer ValorId = Integer.parseInt(mT.get(i).get(0));
            String ValorNombre = mT.get(i).get(1);
            Termino t = new Termino(ValorId, ValorNombre);
            g.addTermino(t);
        }
    }

    private void cargarRelaciones() throws IOException {
        Vector<Vector<String>> PA = cgp.getPA();
        for (int i = 0; i < PA.size(); ++i) {
            String P = PA.get(i).get(0);
            String A = PA.get(i).get(1);
            g.addRelacion(P, A, "PA");
        }
        Vector<Vector<String>> PC = cgp.getPC();
        for (int i = 0; i < PC.size(); ++i) {
            String P = PC.get(i).get(0);
            String C = PC.get(i).get(1);
            g.addRelacion(P, C, "PC");
        }
        Vector<Vector<String>> PT = cgp.getPT();
        for (int i = 0; i < PT.size(); ++i) {
            String P = PT.get(i).get(0);
            String T = PT.get(i).get(1);
            g.addRelacion(P, T, "PT");
        }
    }

    private boolean guardarPapers() throws IOException {
        Vector<Paper> vectorPaper = g.getPapers();
        Vector<Vector<String>> Papers = new Vector<Vector<String>>();
        for (int i = 0; i < vectorPaper.size(); ++i) {
            Vector<String> vec = new Vector<String>();
            vec.add(Integer.toString(vectorPaper.get(i).getId()));
            vec.add(vectorPaper.get(i).getNombre());
            if (vectorPaper.get(i).getEtiqueta() != null) vec.add(vectorPaper.get(i).getEtiqueta());
            Papers.add(vec);
        }
        return cgp.savePapers(Papers);
    }

    private boolean guardarAutores() throws IOException {
        Vector<Autor> vectorAutor = g.getAutors();
        Vector<Vector<String>> Autores = new Vector<Vector<String>>();
        for (int i = 0; i < vectorAutor.size(); ++i) {
            Vector<String> vec = new Vector<String>();
            vec.add(Integer.toString(vectorAutor.get(i).getId()));
            vec.add(vectorAutor.get(i).getNombre());
            if (vectorAutor.get(i).getEtiqueta() != null) vec.add(vectorAutor.get(i).getEtiqueta());
            Autores.add(vec);
        }
        return cgp.saveAutores(Autores);
    }

    private boolean guardarConferencias() throws IOException {
        Vector<Conferencia> vectorConferencia = g.getConferencias();
        Vector<Vector<String>> Conferencias = new Vector<Vector<String>>();
        for (int i = 0; i < vectorConferencia.size(); ++i) {
            Vector<String> vec = new Vector<String>();
            vec.add(Integer.toString(vectorConferencia.get(i).getId()));
            vec.add(vectorConferencia.get(i).getNombre());
            if (vectorConferencia.get(i).getEtiqueta() != null) vec.add(vectorConferencia.get(i).getEtiqueta());
            Conferencias.add(vec);
        }
        return cgp.saveConferencias(Conferencias);
    }

    private boolean guardarTerminos() throws IOException {
        Vector<Termino> vectorTermino = g.getTerminos();
        Vector<Vector<String>> Terminos = new Vector<Vector<String>>();
        for (int i = 0; i < vectorTermino.size(); ++i) {
            Vector<String> vec = new Vector<String>();
            vec.add(Integer.toString(vectorTermino.get(i).getId()));
            vec.add(vectorTermino.get(i).getNombre());
            Terminos.add(vec);
        }
        return cgp.saveTerminos(Terminos);
    }

    private boolean guardarRelPA() throws IOException {
        Vector<Autor> vectorAutor = g.getAutors();
        Vector<Vector<String>> PA = new Vector<Vector<String>>();
        for (int i = 0; i < vectorAutor.size(); ++i) {
            Vector<Entidad> vEnt = g.getRelacion(vectorAutor.get(i).getNombre(), "Autor");
            for (int j = 0; j < vEnt.size(); ++j) {
                Vector<String> vec = new Vector<String>();
                vec.add(Integer.toString(vEnt.get(j).getId()));
                vec.add(Integer.toString(vectorAutor.get(i).getId()));
                PA.add(vec);
            }
        }
        return cgp.savePA(PA);
    }

    private boolean guardarRelPC() throws IOException {
        Vector<Conferencia> vectorConferencia = g.getConferencias();
        Vector<Vector<String>> PC = new Vector<Vector<String>>();
        for (int i = 0; i < vectorConferencia.size(); ++i) {
            Vector<Entidad> vEnt = g.getRelacion(vectorConferencia.get(i).getNombre(), "Conferencia");
            for (int j = 0; j < vEnt.size(); ++j) {
                Vector<String> vec = new Vector<String>();
                vec.add(Integer.toString(vEnt.get(j).getId()));
                vec.add(Integer.toString(vectorConferencia.get(i).getId()));
                PC.add(vec);
            }
        }
        return cgp.savePC(PC);
    }

    private boolean guardarRelPT() throws IOException {
        Vector<Termino> vectorTermino = g.getTerminos();
        Vector<Vector<String>> PT = new Vector<Vector<String>>();
        for (int i = 0; i < vectorTermino.size(); ++i) {
            Vector<Entidad> vEnt = g.getRelacion(vectorTermino.get(i).getNombre(), "Termino");
            for (int j = 0; j < vEnt.size(); ++j) {
                Vector<String> vec = new Vector<String>();
                vec.add(Integer.toString(vEnt.get(j).getId()));
                vec.add(Integer.toString(vectorTermino.get(i).getId()));
                PT.add(vec);
            }
        }
        return cgp.savePT(PT);
    }

    //CREADORA

    public ControladorGrafo() {
        this.g = new Grafo();
        this.cgp = new ControladorGrafoPersist();
    }

    //METODOS PUBLICOS

    public void cargarGrafo() throws IOException {
        cargarPapers();
        cargarAutores();
        cargarConferencias();
        cargarTerminos();
        cargarRelaciones();
    }

    public boolean guardarGrafo() throws IOException {
        boolean p = guardarPapers();
        boolean a = guardarAutores();
        boolean c = guardarConferencias();
        boolean t = guardarTerminos();
        boolean pa = guardarRelPA();
        boolean pc = guardarRelPC();
        boolean pt = guardarRelPT();
        return (p || a || c || t || pa || pc || pt);
    }

    public Grafo getGrafo() {
        return g;
    }
}